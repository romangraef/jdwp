import dataclasses
import re
import typing
from pathlib import Path

import bs4.element
from bs4 import BeautifulSoup

base_url = 'https://docs.oracle.com/en/java/javase/17/docs/specs/jdwp/jdwp-protocol.html#'
soup = BeautifulSoup(open('JDWP.html').read(), features='lxml')


@dataclasses.dataclass
class CommandSet:
    name: str
    set_id: int
    url: str
    children: typing.List['Command']
    docs: str


@dataclasses.dataclass
class Command:
    name: str
    command_id: int
    url: str
    parent: 'CommandSet'
    docs: str
    request: 'JDWPElement'
    reply: 'JDWPElement'


@dataclasses.dataclass
class CommandBaseMeta:
    command_set_id: int
    command_id: int


JDWPElement = typing.Union[
    'Repetition', 'Primitive', 'Composite', typing.Literal['todo'], CommandBaseMeta]


@dataclasses.dataclass
class Repetition:
    by: str
    children: [JDWPElement]


@dataclasses.dataclass
class Primitive:
    typ: str
    name: str
    description: str


@dataclasses.dataclass
class Composite:
    children: typing.List[JDWPElement]


def read_jdwp_element_table(dd_element) -> JDWPElement:
    if dd_element.text.strip() == '(None)':
        return Composite([])
    dd_element: bs4.element.Tag
    table = dd_element.findChild('table')
    assert table.name == 'table'
    tbody = table.findChild('tbody') or table
    rows = list(tbody.children)[1:]
    element_stack = [[]]
    for row in rows:
        if not row.name: continue
        el = row.findChildren(recursive=False)
        first_child = el[0].findChildren("div")
        if first_child:
            first_child = first_child[0]
        else:
            first_child = {}
        indent = re.match('indent(\\d)', ' '.join(first_child.get('class', [])))
        indent = int(indent.group(1)) if indent else 0
        while indent + 1 < len(element_stack):
            del element_stack[-1]
        if indent >= len(element_stack):
            return 'todo'
        current_object = element_stack[-1]
        if len(el) == 3:
            t = el[0].text.strip()
            n = el[1].text.strip()
            d = el[2].text.strip()
            current_object.append(Primitive(t, n, d))
        elif len(el) == 1:
            match = re.match('^Repeated ([^ ]+) times:$', el[0].text)
            child_object = []
            current_object.append(Repetition(match.group(1), child_object))
            element_stack.append(child_object)
        else:
            return 'todo'
    return Composite(element_stack[0])


def main():
    current_command_set = None
    current_command = None
    main = soup.find('main')

    all_command_sets = []
    for child in main.children:
        if child.name == 'h2':
            command_set_match = re.match('^([^ ]+) Command Set \\((\\d+)\\)$', child.text.strip())
            if command_set_match:
                current_command = None
                current_command_set = CommandSet(command_set_match.group(1), int(command_set_match.group(2)),
                                                 base_url + child['id'], [], '')
                all_command_sets.append(current_command_set)
        elif child.name == 'h3':
            command_match = re.match('^([^ ]+) Command \\((\\d+)\\)$', child.text.strip())
            if command_match:
                current_command = Command(command_match.group(1), command_match.group(2), base_url + child["id"],
                                          current_command_set, '', None, None)
                current_command_set.children.append(current_command)
        elif child.name is None:
            t = current_command or current_command_set
            if t: t.docs += child.text
        elif child.name == 'dl':
            dchildren = list(child.children)
            for dchild in dchildren:
                if dchild.name == 'dt' and dchild.text.strip() == 'Out Data':
                    current_command.request = read_jdwp_element_table(dchild.next_sibling)
                if dchild.name == 'dt' and dchild.text.strip() == 'Reply Data':
                    current_command.reply = read_jdwp_element_table(dchild.next_sibling)

    return all_command_sets


name_counter = 0


def next_class_name() -> str:
    global name_counter
    name_counter += 1
    return 'ToDoNameThis' + str(name_counter)


def next_element_name() -> str:
    global name_counter
    name_counter += 1
    return 'todoNameThis' + str(name_counter)


class Emitter:
    def __init__(self, context_prefix: str):
        self.stream = ""
        self.context_prefix = context_prefix
        self.is_failing = False
        self.also: typing.List['Emitter'] = []
        self.indent = 0

    def finish(self):
        for a in self.also:
            self.stream += "\n" + a.finish()
            self.is_failing = self.is_failing or a.is_failing
        return self.stream

    def emit_line(self, line: str = ""):
        self.stream += '    ' * self.indent + line + '\n'

    def emit_docs(self, docs: str | JDWPElement):
        if hasattr(docs, "description"):
            return self.emit_docs(docs.description)
        if not isinstance(docs, str):
            return
        self.emit_line("/**")
        for doc in re.sub("(^|\n)\n*", "\\1", docs).strip().split("\n"):
            self.emit_line(" * " + doc)
        self.emit_line(" */")

    def emit_root_imports(self):
        self.emit_line("import moe.nea.jdwp.*")
        self.emit_line("import moe.nea.jdwp.primitives.*")
        self.emit_line("import moe.nea.jdwp.struct.base.*")
        self.emit_line()

    def child_emitter(self) -> 'Emitter':
        emitter = Emitter(self.context_prefix)
        self.also.append(emitter)
        return emitter

    def emit_element(self, element: JDWPElement, name_hint: str = None, interfaces: typing.List[str] = []) -> (
            bool, str):
        if not name_hint:
            name_hint = next_class_name()
        if element == "todo":
            self.is_failing = True
            return (False, "TODO()")
        if isinstance(element, Composite):
            return (False, self.emit_composite(name_hint, element, interfaces))
        if isinstance(element, Repetition):
            repetition_site = self.emit_composite(
                self.context_prefix + element.by[0].upper() + element.by[1:] + "Element",
                Composite(element.children), ref_usesite=True)
            return (True, "JDWPExternalVector(this::" + element.by + ", " + repetition_site + ")")
        if isinstance(element, Primitive):
            if element.typ == "value":
                return (False, "JDWPValue()")
            if element.typ == "tagged-objectID":
                return (False, "JDWPTaggedObjectId()")
            if element.typ == "location":
                return (False, "JDWPLocation()")
            if element.typ == "arrayregion":
                return (False, "JDWPArrayRegion()")
            return (True, "JDWP" + re.sub('-(.)', lambda x: x.group(1).upper(),
                                          re.sub('ID$', 'Id', element.typ[0].upper() + element.typ[1:])) + "()")

    def get_name(self, element: JDWPElement):
        if hasattr(element, "name"):
            if element.name == "object":
                return "`object`"
            return element.name
        if hasattr(element, 'by'):
            return element.by + 'Elements'
        return next_element_name()

    def emit_composite(self, name: str, composite: Composite, interfaces: typing.List[str] = [],
                       ref_usesite: bool = False) -> str:
        self.emit_line("class " + name + " : " + ', '.join(['JDWPComposite()'] + interfaces) + " {")
        self.indent += 1
        for element in composite.children:
            if isinstance(element, CommandBaseMeta):
                self.emit_line("override val reply = " + name + "Reply()")
                self.emit_line("override val commandId: UByte get() = " + str(element.command_id) + ".toUByte()")
                self.emit_line("override val commandSetId: UByte get() = " + str(element.command_set_id) + ".toUByte()")
                continue
            mutable, use_site = self.child_emitter().emit_element(element)
            self.emit_docs(element)
            self.emit_line(("var " if mutable else "val ") + self.get_name(element) + " by useField(" + use_site + ")")
        self.indent -= 1
        self.emit_line('}')
        if ref_usesite:
            return "::" + name
        return name + "()"

    def emit_package_declaration(self, package: str):
        self.emit_line("package " + package)
        self.emit_line()


if __name__ == '__main__':
    struct = main()
    for command_set in struct:
        for command in command_set.children:
            emitter = Emitter(command.name)
            emitter.emit_package_declaration("moe.nea.jdwp.struct." + command.parent.name.lower())
            emitter.emit_root_imports()
            emitter.emit_docs(command.docs)
            if hasattr(command.request, "children"):
                command.request.children.append(
                    CommandBaseMeta(command.parent.set_id, command.command_id))
            emitter.emit_element(command.request, name_hint=command.name,
                                 interfaces=["JDWPCommandPayload<" + command.name + "Reply>"])
            emitter.emit_docs("Reply for [" + command.name + "]")
            emitter.emit_element(command.reply, name_hint=command.name + "Reply",
                                 interfaces=["JDWPReplyPayload"])
            file = Path("src/main/java/moe/nea/jdwp/struct/") / command.parent.name.lower() / (command.name + ".kt")
            file.parent.mkdir(exist_ok=True, parents=True)
            file.write_text(emitter.finish(), encoding='utf-8')
            if emitter.is_failing:
                print(f"Failing for {command.name} in {command.parent.name} in {file}")
