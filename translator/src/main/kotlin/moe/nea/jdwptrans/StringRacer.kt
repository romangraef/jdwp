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

	fun peekChar(): Char? {
		return if (idx in backing.indices) backing[idx]
		else null
	}

	fun peek(count: Int): String {
		return backing.substring(minOf(idx, backing.length), minOf(idx + count, backing.length))
	}

	fun finished(): Boolean {
		return peekChar() == null
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
		// TODO: improve performance of this
		val p = peek(string.length)
		if (p != string)
			return false
		idx += p.length
		return true
	}

	fun consumeUntil(charArray: CharArray): String {
		val limit = backing.indexOfAny(charArray, idx)
		val oldIdx = idx
		if (limit < 0) {
			idx = backing.length
			return backing.substring(oldIdx, backing.length)
		}
		idx = limit
		return backing.substring(oldIdx, limit)
	}

	fun consumeWhile(shouldConsumeThisString: (CharSequence) -> Boolean): CharSequence {
		val lastString = StringBuilder()
		while (true) {
			val c = peekChar() ?: return lastString.toString()
			lastString.append(c)
			if (!shouldConsumeThisString(lastString)) {
				lastString.setLength(maxOf(0, lastString.length - 1))
				return lastString
			}
			idx++
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