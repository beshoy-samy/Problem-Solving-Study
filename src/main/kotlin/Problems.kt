import kotlin.math.abs
import kotlin.math.absoluteValue

fun minimumWaitingTime(queries: MutableList<Int>): Int {
    // Write your code here.
    queries.sort()
    var waitingTime = 0
    var lastRunQueryIndex = -1
    var totalWaitingTime = 0
    queries.forEach {
        if (lastRunQueryIndex != -1)
            waitingTime += queries[lastRunQueryIndex]
        totalWaitingTime += waitingTime
        lastRunQueryIndex++
    }
    return totalWaitingTime
}

fun classPhotos(redShirtHeights: MutableList<Int>, blueShirtHeights: MutableList<Int>): Boolean {
    // Write your code here.
    redShirtHeights.sort()
    blueShirtHeights.sort()
    val isRedInFront = redShirtHeights[0] < blueShirtHeights[0]
    val inFrontStudents = if (isRedInFront) redShirtHeights else blueShirtHeights
    val inBackStudents = if (isRedInFront) blueShirtHeights else redShirtHeights
    inBackStudents.forEachIndexed { index, inBackStudentHeight ->
        if (inFrontStudents[index] >= inBackStudentHeight) return false
    }
    return true
}

fun tandemBicycle(redShirtSpeeds: MutableList<Int>, blueShirtSpeeds: MutableList<Int>, fastest: Boolean): Int {
    // Write your code here.
    redShirtSpeeds.sort()
    blueShirtSpeeds.sort()
    val speeds = if (fastest.not()) {
        val minSpeeds = mutableListOf<Int>()
        redShirtSpeeds.forEachIndexed { index, redSpeed ->
            val blueSpeed = blueShirtSpeeds[index]
            minSpeeds.add(maxOf(redSpeed, blueSpeed))
        }
        minSpeeds
    } else {
        val maxSpeeds = mutableListOf<Int>()
        val lastIndex = redShirtSpeeds.lastIndex
        for (i in lastIndex downTo 0) {
            val redSpeed = redShirtSpeeds[i] //max speed of red
            val blueSpeed = blueShirtSpeeds[Math.abs(i.minus(lastIndex))] //min speed of blue
            maxSpeeds.add(maxOf(redSpeed, blueSpeed))
        }
        maxSpeeds
    }
    return speeds.sum()
}

data class LinkedList(val value: Int) {
    var next: LinkedList? = null
}

fun removeDuplicatesFromLinkedList(linkedList: LinkedList): LinkedList {
    // Write your code here.
    var curNode: LinkedList? = linkedList
    var nextNode: LinkedList?
    while (curNode != null) {
        nextNode = curNode.next
        if (nextNode?.value == curNode.value)
            curNode.next = nextNode.next
        else curNode = curNode.next
    }
    return linkedList
}

fun calculateFib(nth: Int): Int {
    return if (nth == 0 || nth == 1) nth
    else calculateFib(nth.minus(1)) + calculateFib(nth.minus(2))
}

fun calculateProductSum(array: List<*>, depth: Int = 1): Int {
    var sum = 0
    array.forEach { item ->
        if (item is List<*>) {
            val currentDepth = depth.inc()
            sum += currentDepth.times(calculateProductSum(item, currentDepth))
        } else if (item is Int) sum += item
    }
    return sum
}

fun findThreeLargestNumbers(array: List<Int>): List<Int> {
    // Write your code here.
    val largestItems = MutableList(3) { Int.MIN_VALUE }
    array.forEach { number ->
        when {
            number > largestItems[2] -> {
                largestItems.shiftByOne()
                largestItems[2] = number
            }
            number > largestItems[1] -> {
                largestItems.shiftByOne(stopAtIndex = 1)
                largestItems[1] = number
            }
            number > largestItems[0] -> largestItems[0] = number
        }
    }
    return largestItems
}

private fun <T> MutableList<T>.shiftByOne(stopAtIndex: Int = lastIndex) = apply {
    for (index in 0..stopAtIndex.minus(1)) this[index] = this[index.plus(1)]
}

fun doBinarySearch(array: List<Int>, target: Int, startIndex: Int = 0, endIndex: Int = array.lastIndex): Int {
    val pickedIndex = startIndex.plus(endIndex).div(2)
    val pickedNumber = array[pickedIndex]
    return when {
        target == pickedNumber -> pickedIndex
        startIndex == endIndex -> -1
        target > pickedNumber -> doBinarySearch(array, target, pickedIndex.plus(1), endIndex)
        target < pickedNumber -> doBinarySearch(array, target, startIndex, pickedIndex.minus(1))
        else -> -1
    }
}

fun isPalindrome(string: String): Boolean {
    // Write your code here.
    var startIndex = 0
    var endIndex = string.lastIndex
    while (startIndex < endIndex) {
        if (string[startIndex] != string[endIndex]) return false
        else {
            startIndex += 1
            endIndex -= 1
        }
    }
    return true
}

fun caesarCipherEncryptor(string: String, key: Int): String {
    // Write your code here.
    val caesarCipher = mutableListOf<Char>()
    val shiftBy = key.rem(26)

    string.forEach { char ->
        var encrypted = char.toInt().plus(shiftBy).rem(123)
        if (encrypted < 97) encrypted += 97
        caesarCipher.add(encrypted.toChar())
    }
    return caesarCipher.joinToString("")
}

fun runLengthEncoding(string: String): String {
    // Write your code here.
    val runCountsList = mutableListOf<String>()
    var curCountingChar = string[0]
    var curCountingCharCount = 1

    for (i in 1 until string.length) {
        if (curCountingCharCount == 9 || curCountingChar != string[i]) {
            runCountsList.add("$curCountingCharCount$curCountingChar")
            curCountingCharCount = 1
            curCountingChar = string[i]
        } else curCountingCharCount += 1
    }

    runCountsList.add("$curCountingCharCount$curCountingChar")

    return runCountsList.joinToString("")
}

fun generateDocument(characters: String, document: String): Boolean {
    // Write your code here.
    val charactersMap = characters.countUniqueChars()
    val documentMap = document.countUniqueChars()
    documentMap.forEach { (char, count) ->
        val originalCharCount = charactersMap.getOrDefault(char, -1)
        if (originalCharCount < count) return false
    }
    return true
}

private fun String.countUniqueChars(): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    forEach { char ->
        val charCount = map.getOrDefault(char, 0)
        map[char] = charCount.inc()
    }
    return map
}

fun firstNonRepeatingCharacter(string: String): Int {
    // Write your code here.
    val charsFreq = mutableMapOf<Char, Int>()
    string.forEach { char ->
        val freq = charsFreq.getOrDefault(char, 0)
        charsFreq[char] = freq.inc()
    }
    string.forEachIndexed { index, char ->
        if (charsFreq[char] == 1) return index
    }
    return -1
}

fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    // Write your code here
    val result = mutableListOf<List<Int>>()

    array.sort() // first sort the array
    array.forEachIndexed { index, number ->
        var left = index + 1
        var right = array.lastIndex

        while (left < right) {
            val curSum = array[left] + array[right] + number
            when {
                curSum > targetSum -> right--
                curSum < targetSum -> left++
                else -> {
                    val triplets = listOf(number, array[left], array[right])
                    result.add(triplets)
                    left++
                    right--
                }
            }
        }
    }
    return result
}

fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
    // Write your code here.
    arrayOne.sort()
    arrayTwo.sort()

    var left = 0
    var right = 0
    var result = listOf(arrayOne[left], arrayTwo[right])
    var minDifference = Math.abs(arrayOne[left] - arrayTwo[right])

    while (left < arrayOne.size && right < arrayTwo.size) {
        val lNum = arrayOne[left]
        val rNum = arrayTwo[right]

        if (lNum == rNum) return listOf(lNum, rNum)

        val diff = Math.abs(lNum - rNum)

        if (diff < minDifference) {
            minDifference = diff
            result = listOf(lNum, rNum)
        }

        if (lNum < rNum) left++
        else right++
    }

    return result
}

fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    // Write your code here.
    var endingPointer = array.lastIndex
    var startingPointer = 0

    while (startingPointer < endingPointer) {

        if (array[endingPointer] == toMove) endingPointer--
        else if (array[startingPointer] != toMove) startingPointer++
        else {
            array[startingPointer] = array[endingPointer]
            array[endingPointer] = toMove
            endingPointer--
            startingPointer++
        }
    }

    return array
}