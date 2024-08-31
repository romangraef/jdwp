package moe.nea.jdwptrans

import java.util.Stack

class StringRacer(val backing: String) {
	var idx = 0
	val stack = Stack<Int>()

	fun pushState() {
		stack.push(idx)
	}

	fun popState() {
		idx = stack.pop()
	}

	fun discardState() {
		stack.pop()
	}

	fun peek(count: Int): String {
		return backing.substring(minOf(idx, backing.length), minOf(idx + count, backing.length))
	}

	fun finished(): Boolean {
		return peek(1).isEmpty()
	}

	fun peekReq(count: Int): String? {
		val p = peek(count)
		if (p.length != count)
			return null
		return p
	}

	fun consumeCountReq(count: Int): String? {
		val p = peekReq(count)
		if (p != null)
			idx += count
		return p
	}

	fun advance(by: Int) {
		idx = minOf(idx + by, backing.length)
	}

	fun tryConsume(string: String): Boolean {
		val p = peek(string.length)
		if (p != string)
			return false
		idx += p.length
		return true
	}

	fun consumeWhile(shouldConsumeThisString: (String) -> Boolean): String {
		var lastString: String = ""
		while (true) {
			val nextString = lastString + peek(1)
			if (!shouldConsumeThisString(nextString)) {
				return lastString
			}
			idx++
			lastString = nextString
		}
	}

	fun expect(search: String, errorMessage: String) {
		if (!tryConsume(search))
			error(errorMessage)
	}

	fun error(errorMessage: String): Nothing {
		kotlin.error("Failed to parse something at ($idx): $errorMessage\n" +
				             backing.substring(0, idx) + "<CURSOR>" + backing.substring(idx))
	}

}