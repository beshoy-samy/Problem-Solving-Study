package structy

fun anagram(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false
    val s1Map = mutableMapOf<Char, Int>()
    s1.forEach {
        val count = s1Map.getOrElse(it) { 0 }
        s1Map[it] = count.inc()
    }
    s2.forEach {
        val count = s1Map[it]
        if (count == null || count == 0) return false
        else s1Map[it] = count.dec()
    }
    return true
}

fun mostFrequentChar(s: String): Char {
    val charsCount = mutableMapOf<Char, Int>()
    s.forEach {
        val count = charsCount.getOrDefault(it, 0)
        charsCount[it] = count.inc()
    }
    var mostFrequent = s[0]
    s.forEach {
        if (charsCount[it]!! > charsCount[mostFrequent]!!) {
            mostFrequent = it
        }
    }
    return mostFrequent
}

fun pairSum(numbers: List<Int>, target: Int): List<Int> {
    val potentialPairMap = mutableMapOf<Int, Int>()
    numbers.forEachIndexed { index, i ->
        if (potentialPairMap[i] != null) {
            return listOf(potentialPairMap[i]!!, index)
        } else {
            potentialPairMap[target - i] = index
        }
    }
    return listOf()
}

fun pairProduct(numbers: List<Int>, target: Int): List<Int> {
    val potentialPairMap = mutableMapOf<Int, Int>()
    numbers.forEachIndexed { index, i ->
        if (potentialPairMap[i] != null) {
            return listOf(potentialPairMap[i]!!, index)
        } else if (target.mod(i) == 0) {
            potentialPairMap[target.div(i)] = index
        }
    }
    return listOf()
}

fun uncompress(s: String): String {
    val output = StringBuilder()
    var i = 0
    s.forEachIndexed { index, char ->
        if (char.isDigit().not()) {
            val charCount = s.substring(i, index).toInt()
            repeat(charCount) { output.append(char) }
            i = index.inc()
        }
    }
    return output.toString()
}

fun compress(s: String): String {
    val output = StringBuilder()
    var i = 0
    s.forEachIndexed { index, char ->
        if (char != s[i]) {
            val count = index - i
            if (count > 1) output.append(count)
            output.append(s[i])
            i = index
        }
    }
    val count = s.length - i
    if (count > 1) output.append(count)
    output.append(s[i])
    return output.toString()
}

fun intersection(listA: List<Int>, listB: List<Int>): List<Int> {
    val listASet = listA.toSet()
    val intersection = mutableListOf<Int>()
    listB.forEach {
        if (listASet.contains(it)) intersection.add(it)
    }
    return intersection
}

fun fiveSort(array: MutableList<Int>): List<Int> {
    var rightPointer = array.lastIndex
    var leftPointer = 0

    while (leftPointer < rightPointer) {
        if (array[rightPointer] == 5) rightPointer--
        else if (array[leftPointer] == 5) {
            array[leftPointer] = array[rightPointer]
            array[rightPointer] = 5
            leftPointer++
            rightPointer--
        } else {
            leftPointer++
        }
    }

    return array
}
