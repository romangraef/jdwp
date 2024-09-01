package moe.nea.jdwptrans

// TODO: spec conforming according to https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.7.9.1
sealed interface Signature {
	sealed interface JavaTypeSignature : Signature
	sealed interface ReferenceTypeSignature : JavaTypeSignature
	data class ClassTypeSignature(
		val packageSpecifier: PackageSpecifier,
		val clazz: SimpleClassTypeSignature,
		val appendages: List<SimpleClassTypeSignature>,
	) : ReferenceTypeSignature {
		override fun toString(): String {
			val nests = appendages.joinToString("") { ".$it" }
			return "L$packageSpecifier$clazz${nests};"
		}
	}

	data class TypeVariableSignature(
		val identifier: Identifier
	) : ReferenceTypeSignature {
		override fun toString(): String {
			return "T$identifier;"
		}
	}

	data class ArrayTypeSignature(
		val component: JavaTypeSignature
	) : ReferenceTypeSignature {
		override fun toString(): String {
			return "[$component"
		}
	}

	data class Identifier(val name: String) : Signature {
		override fun toString(): String {
			return name
		}
	}

	data class SimpleClassTypeSignature(
		val name: Identifier,
		val typeArguments: TypeArguments,
	) : Signature {
		override fun toString(): String {
			return "$name$typeArguments"
		}
	}

	data class TypeArguments(
		val list: List<TypeArgument>
	) : Signature {
		override fun toString(): String {
			if (list.isEmpty()) return ""
			return list.joinToString("", "<", ">")
		}
	}


	sealed interface TypeArgument : Signature
	data object StarProjectionReference : TypeArgument {
		override fun toString(): String {
			return "*"
		}
	}

	data class TypeArgumentReference(
		val wildCardIndicator: WildCardIndicator?,
		val bound: ReferenceTypeSignature,
	) : TypeArgument {
		override fun toString(): String {
			val wildcard = wildCardIndicator?.toString() ?: ""
			return wildcard + bound
		}
	}

	enum class WildCardIndicator : Signature {
		PLUS, MINUS, ;

		override fun toString(): String {
			return when (this) {
				PLUS -> "+"
				MINUS -> "-"
			}
		}
	}

	data class PackageSpecifier(
		val slashedPackageName: String
	) : Signature {
		init {
			require(slashedPackageName.isEmpty() || slashedPackageName.endsWith("/")) { "$slashedPackageName does not end with /" }
		}

		override fun toString(): String {
			return slashedPackageName
		}
	}


	enum class BaseType(val desc: Char) : JavaTypeSignature {
		BOOLEAN('Z'),
		BYTE('B'),
		INT('I'),
		DOUBLE('D'),
		LONG('J'),
		SHORT('S'),
		FLOAT('F'),
		CHAR('C'),
		;

		override fun toString(): String {
			return desc.toString()
		}

		companion object {
			val byDesc = entries.associateBy { it.desc }
		}
	}

	data class ClassSignature(
		val typeParameters: TypeParameters,
		val superClassSignature: ClassTypeSignature,
		val superInterfaceSignatures: List<ClassTypeSignature>,
	) : Signature {
		override fun toString(): String {
			return "$typeParameters$superClassSignature${superInterfaceSignatures.joinToString("")}"
		}
	}

	data class TypeParameters(
		val parameters: List<TypeParameter>
	) : Signature {
		override fun toString(): String {
			if (parameters.isEmpty()) return ""
			return parameters.joinToString("", "<", ">")
		}
	}

	data class TypeParameter(
		val identifier: Identifier,
		val classBound: ReferenceTypeSignature?,
		val interfaceBound: List<ReferenceTypeSignature>
	) : Signature {
		init {
			require(classBound != null || interfaceBound.isNotEmpty())
		}

		override fun toString(): String {
			val classBoundOrEmpty = classBound?.toString() ?: ""
			val interfaces = interfaceBound.joinToString("") { ":$it" }
			return "$identifier:$classBoundOrEmpty$interfaces"
		}
	}

	companion object {
		fun parse(signature: String): Signature {
			return SignatureParser(StringRacer(signature)).parseReferenceTypeSignature()
		}

		fun parseGeneric(signature: String): Signature {
			return SignatureParser(StringRacer(signature)).parseClassSignature()
		}
	}

	data object Void : Signature {
		override fun toString(): String {
			return "V"
		}
	}
}

class SignatureParser(val racer: StringRacer) {

	fun parseClassSignature(): Signature.ClassSignature {
		val typeParameters = parseTypeParameters()
		val superClassSignature = parseClassTypeSignature()
		val superInterfaceSignatures = buildList {
			while (racer.peekChar() == 'L')
				add(parseClassTypeSignature())
		}
		return Signature.ClassSignature(typeParameters, superClassSignature, superInterfaceSignatures)
	}

	fun parseTypeParameters(): Signature.TypeParameters {
		if (!racer.tryConsume("<")) return Signature.TypeParameters(listOf())
		val list = buildList {
			while (!racer.tryConsume(">")) {
				add(parseTypeParameter())
			}
		}
		return Signature.TypeParameters(list)
	}

	fun parseTypeParameter(): Signature.TypeParameter {
		val ident = parseIdentifier()
		racer.expect(":", "Expected : after type identifierin TypeParameter")
		val classBound = if (racer.peekChar() != ':')
			parseReferenceTypeSignature() else null
		val interfaceBound = buildList {
			while (racer.tryConsume(":"))
				add(parseReferenceTypeSignature())
		}
		return Signature.TypeParameter(ident, classBound, interfaceBound)
	}

	companion object {
		private val nonIdentifierChars = ":;/.><".toCharArray()
	}

	fun parseIdentifier(): Signature.Identifier {
		return Signature.Identifier(racer.consumeUntil(nonIdentifierChars).also {
			if (it.isEmpty()) racer.error("Expected identifier")
		})
	}

	fun parseTypeVariableSignature(): Signature.TypeVariableSignature {
		racer.expect("T", "TypeVariableSignature starts with T")
		val ident = parseIdentifier()
		racer.expect(";", "TypeVariableSignature ends with a ;")
		return Signature.TypeVariableSignature(ident)
	}

	fun parseArrayTypeSignature(): Signature.ArrayTypeSignature {
		racer.expect("[", "ArrayTypeSignature starts with [")
		return Signature.ArrayTypeSignature(parseJavaTypeSignature())
	}

	fun parseWildcardIndicator(): Signature.WildCardIndicator? {
		if (racer.tryConsume("+")) return Signature.WildCardIndicator.PLUS
		if (racer.tryConsume("-")) return Signature.WildCardIndicator.MINUS
		return null
	}

	fun parseTypeArguments(): Signature.TypeArguments {
		if (!racer.tryConsume("<")) return Signature.TypeArguments(listOf())
		val list = buildList {
			while (!racer.tryConsume(">")) {
				add(parseTypeArgument())
			}
		}
		return Signature.TypeArguments(list)
	}

	fun parseTypeArgument(): Signature.TypeArgument {
		if (racer.tryConsume("*")) return Signature.StarProjectionReference
		val indicator = parseWildcardIndicator()
		val reference = parseReferenceTypeSignature()
		return Signature.TypeArgumentReference(indicator, reference)
	}

	fun parsePackageSpecifier(): Signature.PackageSpecifier {
		val endOfClass = racer.backing.indexOfAny(":;.><".toCharArray(), racer.idx)
		if (endOfClass < 0) racer.error("Could not find end of class while parsing package")
		val packageEnd = racer.backing.lastIndexOf('/', endOfClass)
		if (packageEnd < racer.idx) return Signature.PackageSpecifier("")
		return Signature.PackageSpecifier(racer.consumeCountReq(packageEnd - racer.idx + 1)!!)
	}

	fun parseSimpleClassTypeSignature(): Signature.SimpleClassTypeSignature {
		val ident = parseIdentifier()
		val typeArguments = parseTypeArguments()
		return Signature.SimpleClassTypeSignature(ident, typeArguments)
	}

	fun parseClassTypeSignature(): Signature.ClassTypeSignature {
		racer.expect("L", "ClassTypeSignature starts with L")
		val pack = parsePackageSpecifier()
		val mainClass = parseSimpleClassTypeSignature()
		val nested = buildList {
			while (!racer.tryConsume(";")) {
				add(parseClassTypeSignatureSuffix())
			}
		}
		return Signature.ClassTypeSignature(pack, mainClass, nested)
	}

	fun parseClassTypeSignatureSuffix(): Signature.SimpleClassTypeSignature {
		racer.expect(".", "ClassTypeSignatureSuffix starts with .")
		return parseSimpleClassTypeSignature()
	}

	fun parseReferenceTypeSignature(): Signature.ReferenceTypeSignature {
		return when (racer.peekChar()) {
			'L' -> parseClassTypeSignature()
			'T' -> parseTypeVariableSignature()
			'[' -> parseArrayTypeSignature()
			else -> racer.error("Expected ReferenceTypeSignature")
		}
	}

	fun amIPeekingAReferenceTypeSignature(): Boolean {
		return (racer.peekChar() ?: return false) in "LT["
	}

	fun parseJavaTypeSignature(): Signature.JavaTypeSignature {
		if (amIPeekingAReferenceTypeSignature()) return parseReferenceTypeSignature()
		val p = racer.peekChar()
		val baseType = Signature.BaseType.byDesc[p]
			?: racer.error("Expected a JavaTypeSignature")
		racer.advance(1)
		return baseType
	}
}

