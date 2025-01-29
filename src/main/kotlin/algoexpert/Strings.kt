package algoexpert

fun commonCharacters(strings: MutableList<String>): List<String> {
    // Write your code here.
    val charsMap = mutableMapOf<Char, Int>()
    val result = mutableListOf<String>()
    val stringsOfUniqueChars = mutableListOf<Set<Char>>()
    strings.forEach { stringsOfUniqueChars.add(it.toSet()) }


    stringsOfUniqueChars.forEach { set ->
        set.forEach {
            if (charsMap[it] != null) {
                charsMap[it] = charsMap[it]!!.plus(1)
            } else {
                charsMap[it] = 1
            }
        }
    }

    charsMap.keys.forEach {
        if (charsMap[it] == strings.size) result.add(it.toString())
    }

    return result
}