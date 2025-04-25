package structy

fun longestWord(sentence: String): String {
    var longest = ""
    val words = sentence.split(" ")

    words.forEach {
        if (it.length >= longest.length) longest = it
    }

    return longest
}

fun fizzBuzz(n: Int): List<Any> {
    val list = mutableListOf<Any>()
    for (i in 1..n) {
        if (i.mod(3) == 0 && i.mod(5) == 0) {
            list.add("fizzbuzz")
        } else if (i.mod(3) == 0) {
            list.add("fizz")
        } else if (i.mod(5) == 0) {
            list.add("buzz")
        } else {
            list.add(i)
        }
    }
    return list
}

fun pairs(elements: List<String>): List<List<String>> {
    val list = mutableListOf<List<String>>()

    elements.forEachIndexed { index, s ->
        for (i in index.inc()..elements.lastIndex) {
           list.add(listOf(s, elements[i]))
        }
    }

    return list
}
