## Contains little helpers for extracting Java Code from the docs


### Extract an enum table

```javascript
[...temp0.querySelectorAll('tr')].map(it => ({label: it.children[0].innerText, tag: it.children[1].innerText, description: it.children[2].innerText})).slice(1).map(it => `/**\n* ${it.description}\n*/\n${it.label}(${it.tag}),`).join("\n")
```