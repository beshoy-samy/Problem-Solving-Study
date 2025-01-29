package codesignal

import kotlin.math.*

data class ListNode(var value: Int) {
    var next: ListNode? = null;
}


fun solution(l: ListNode?, value: Int): ListNode? {
    val newNode = ListNode(value)
    var pointer = l

    if (pointer == null || newNode.value < pointer.value) {
        newNode.next = pointer
        return newNode
    }

    while (pointer?.next != null && pointer.next!!.value < newNode.value)
        pointer = pointer.next

    newNode.next = pointer?.next
    pointer?.next = newNode

    return l
}

fun solution(sentence: String): String {
    val list = sentence.split(" ")
    return list.reversed().joinToString(separator = " ")
}

/**
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 */
//[0,0,1,1,2,3,2,3,3]
fun removeDuplicates(nums: IntArray): Int {

    var uniqueNumbersCount = 0
    var lastUniqueNumCount = 0
    var lastUniqueNumIndex = 0
    var pointer = 0

    while (pointer < nums.size) {
        if (lastUniqueNumIndex == pointer) {
            uniqueNumbersCount++
            lastUniqueNumCount++
        } else if ((nums[lastUniqueNumIndex] == nums[pointer] && lastUniqueNumCount < 2) || nums[lastUniqueNumIndex] != nums[pointer]) {
            nums[lastUniqueNumIndex + 1] = nums[pointer]
            if (nums[lastUniqueNumIndex] != nums[pointer]) lastUniqueNumCount = 1
            else lastUniqueNumCount++
            uniqueNumbersCount++
            lastUniqueNumIndex++
        }
        pointer++
    }

    return uniqueNumbersCount
}

fun removeElement(nums: IntArray, `val`: Int): Int {

    var leftNumbersCount = 0
    var pointer = 0
    var aheadPointer = 0

    while (pointer < nums.size && aheadPointer < nums.size) {

        if (nums[pointer] != `val`) {
            pointer++
            leftNumbersCount++
            aheadPointer = pointer
        } else if (nums[aheadPointer] == `val`) aheadPointer++
        else {
            val temp = nums[pointer]
            nums[pointer] = nums[aheadPointer]
            nums[aheadPointer] = temp
            aheadPointer++
            pointer++
            leftNumbersCount++
        }

    }

    return leftNumbersCount
}

/*
fun rotate(nums: IntArray, k: Int) {
    var pointer = 0
    var carry = nums[pointer]
    val steps = k.rem(nums.size)
    repeat(nums.size) { index ->
        val nextIndex = pointer.plus(steps).rem(nums.size)
        val toBeChangedValue = nums[nextIndex]
        nums[nextIndex] = carry
        carry = toBeChangedValue

        println("${nums.joinToString()} with carry $carry and pointer $pointer")

        if (nums.size.rem(2) == 0 && nums.size.div(2) == steps && index.plus(1) == steps)
            pointer += steps.plus(1)
        else pointer += steps
    }

}*/
/**
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
7 6 5 4 3 2 1
 */
fun rotate(nums: IntArray, k: Int) {
    val rotations = k.rem(nums.size)
    reverse(nums)
    reverse(nums, endIndex = rotations.minus(1))
    reverse(nums, startIndex = rotations)

    println(nums.joinToString())
}

fun reverse(nums: IntArray, startIndex: Int = 0, endIndex: Int = nums.lastIndex) {
    var leftPointer = startIndex
    var rightPointer = endIndex

    while (leftPointer < rightPointer) {
        val temp = nums[leftPointer]
        nums[leftPointer] = nums[rightPointer]
        nums[rightPointer] = temp
        leftPointer++
        rightPointer--
    }
}

fun hasCycle(head: ListNode?): Boolean {
    var pointer = head
    var fastPointer = head?.next

    while (fastPointer != null) {
        if (fastPointer == pointer) return true
        pointer = pointer?.next
        fastPointer = fastPointer.next?.next
    }

    return false
}

fun reverseList(head: ListNode?): ListNode? {

    var pointer = head
    var prev: ListNode? = null

    while (pointer != null) {
        val temp = pointer.next
        pointer.next = prev
        prev = pointer
        pointer = temp
    }

    return prev
}

fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
    if (headA == null || headB == null) return null

    var pointerA = headA
    var pointerB = headB

    while (pointerA != pointerB) {
        pointerA = if (pointerA == null) headB else pointerA.next
        pointerB = if (pointerB == null) headA else pointerB.next
    }

    return pointerA
}

fun middleNode(head: ListNode?): ListNode? {
    var pointer = head
    var fastPointer = head

    while (fastPointer?.next != null) {
        pointer = pointer?.next
        fastPointer = fastPointer.next?.next
    }

    return pointer
}

fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    var headPointer = head
    var pointer = head
    var prev: ListNode? = null

    while (pointer != null) {
        if (headPointer?.value == `val`) headPointer = headPointer.next
        if (pointer.value == `val`) prev?.next = pointer.next
        else prev = pointer
        pointer = pointer.next
    }

    return headPointer
}

//[2,7,11,15]
//7 2 -3 -6
//9
fun twoSum(numbers: IntArray, target: Int): IntArray {

    var leftPointer = 0
    var rightPointer = numbers.lastIndex

    while (leftPointer < rightPointer) {
        val potential = numbers[leftPointer] + numbers[rightPointer]

        if (potential > target) rightPointer--
        if (potential < target) leftPointer++
        else return intArrayOf(leftPointer.inc(), rightPointer.inc())
    }

    return intArrayOf()
}

fun moveZeroes(nums: IntArray): Unit {

    var pointer = 0
    var nonZeroPointer = 0

    while (pointer < nums.size && nonZeroPointer < nums.size) {
        if (nums[nonZeroPointer] == 0 || pointer > nonZeroPointer) nonZeroPointer++
        else if (nums[pointer] == 0) {
            nums[pointer] = nums[nonZeroPointer]
            nums[nonZeroPointer] = 0
            pointer++
            nonZeroPointer++
        } else pointer++
        println(nums.joinToString())
    }

}

/**
 * coordinates -> (i, ai) and (i, 0)
 */
fun maxArea(height: IntArray): Int {

    var startPointer = 0
    var endPointer = height.lastIndex
    var maxArea = Int.MIN_VALUE

    while (startPointer < endPointer) {
        val containerHeight = minOf(height[startPointer], height[endPointer])
        val containerWidth = endPointer.minus(startPointer)
        val containerArea = containerHeight.times(containerWidth)
        maxArea = maxOf(containerArea, maxArea)
        println("$containerHeight * $containerWidth = $containerArea")
        if (height[startPointer] > height[endPointer]) endPointer--
        else startPointer++
    }

    return maxArea
}

/**
 * s = "()" -> 1
 * s = "(())" -> 2
 * s = "()()" -> 2
 * s = "(()(()))" -> 6
 */
fun scoreOfParentheses(s: String): Int {
    val arrayStack = ArrayDeque<Int>()
    var score = 0

    s.forEach { char ->
        score = if (char == '(') {
            arrayStack.addFirst(score)
            0
        } else arrayStack.removeFirst().plus(maxOf(score * 2, 1))
    }

    return score
}

class MinStack() {

    private var minVal = Int.MAX_VALUE
    private val myStack = ArrayDeque<Pair<Int, Int>>()

    fun push(`val`: Int) {
        myStack.addFirst(Pair(`val`, minVal))
        minVal = minOf(minVal, `val`)
    }

    fun pop() {
        minVal = myStack.removeFirst().second
    }

    fun top(): Int = myStack.first().first

    fun getMin(): Int = minVal

}

/**
 * "()()"
 * "(())()"
 */
fun removeOuterParentheses(s: String): String {

    val myStack = ArrayDeque<Char>()
    val stringBuilder = StringBuilder()
    s.forEach { char ->
        if (char == '(') {
            if (myStack.isNotEmpty()) stringBuilder.append(char)
            myStack.addFirst(char)
        } else {
            myStack.removeFirst()
            if (myStack.isNotEmpty()) stringBuilder.append(char)
        }
    }

    return stringBuilder.toString()
}

/**
 * FIFO
 */
class MyQueue() {

    private val primaryStack = ArrayDeque<Int>()
    private val secondaryStack = ArrayDeque<Int>()
    private var front = 0

    fun push(x: Int) {
        if (primaryStack.isEmpty()) front = x
        primaryStack.addFirst(x)
    }

    fun pop(): Int {
        return if (secondaryStack.isNotEmpty()) secondaryStack.removeFirst()
        else {
            while (primaryStack.isNotEmpty()) secondaryStack.addFirst(primaryStack.removeFirst())
            secondaryStack.removeFirst()
        }
    }

    fun peek(): Int {
        return if (secondaryStack.isNotEmpty()) secondaryStack.first()
        else front
    }

    fun empty(): Boolean = primaryStack.isEmpty() && secondaryStack.isEmpty()

}

/**
 * abbaca -> ca
 */
fun removeDuplicates(s: String): String {

    val myStack = ArrayDeque<Char>()

    s.forEach { char ->
        if (myStack.firstOrNull() != char) myStack.addFirst(char)
        else myStack.removeFirst()
    }

    return myStack.reversed().joinToString(separator = "")
}

/**
 * "[)"
 */
fun regularBracketSeq(sequence: String): Boolean {

    val myStack = ArrayDeque<Char>()

    sequence.forEach { char ->
        if (char == '(' || char == '[') myStack.addFirst(char)
        else if (char == ')' && myStack.firstOrNull() == '(') myStack.removeFirst()
        else if (char == ']' && myStack.firstOrNull() == '[') myStack.removeFirst()
    }

    return myStack.isEmpty()
}

fun threeSum(nums: IntArray): List<List<Int>> {
    if (nums.isEmpty()) return listOf()

    nums.sort()

    val target = 0
    val threeSum = mutableListOf<List<Int>>()

    for (i in 0..nums.lastIndex.minus(1)) {
        if (i > 0 && nums[i] == nums[i - 1]) continue

        val complement = target.minus(nums[i])
        var leftPointer = i.plus(1)
        var rightPointer = nums.lastIndex

        while (leftPointer < rightPointer) {
            val currentSum = nums[leftPointer] + nums[rightPointer]
            if (currentSum == complement) {
                threeSum.add(listOf(nums[i], nums[leftPointer], nums[rightPointer]))
                while (leftPointer < rightPointer && nums[leftPointer] == nums[leftPointer + 1]) leftPointer++
                while (leftPointer < rightPointer && nums[rightPointer] == nums[rightPointer - 1]) rightPointer--
                leftPointer++
                rightPointer--
            } else if (currentSum > complement) rightPointer--
            else leftPointer++
        }

    }

    return threeSum
}

fun containsDuplicate(nums: IntArray): Boolean {
    val uniqueNumbers = mutableSetOf<Int>()

    nums.forEach {
        if (uniqueNumbers.add(it).not()) return false
    }

    return false
}

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val charsMap = mutableMapOf<Char, Int>()

    s.forEach { char ->
        val count = charsMap.getOrDefault(char, 0)
        charsMap[char] = count.inc()
    }

    t.forEach { char ->
        val count = charsMap[char]
        if (count == null || count == 0) return false
        else charsMap[char] = count.dec()
    }

    return true
}

fun frequencySort(s: String): String {

    val charsMap = mutableMapOf<Char, Int>()
    val stringBuilder = StringBuilder()

    s.forEach { char ->
        val count = charsMap.getOrDefault(char, 0)
        charsMap[char] = count.inc()
    }

    val keysSorted = charsMap.keys.sortedByDescending { charsMap[it] }
    keysSorted.forEach { char ->
        repeat(charsMap[char] ?: 0) {
            stringBuilder.append(char)
        }
    }

    return stringBuilder.toString()
}

class LRUCache(val capacity: Int) {

    private val cache = mutableMapOf<Int, LRUNode>()
    private var head: LRUNode = LRUNode(-1, -1)
    private var tail: LRUNode = LRUNode(-1, -1)

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val lruNode = cache[key]
        if (lruNode != null) {
            remove(lruNode)
            add(lruNode)
        }
        return lruNode?.value ?: -1
    }

    fun put(key: Int, value: Int) {
        if (cache[key] != null) {
            val node = cache[key]!!
            node.value = value
            remove(node)
            add(node)
            cache[key] = node
            return
        } else if (cache.size >= capacity) {
            val lru = tail.prev!!
            cache.remove(lru.key)
            remove(lru)
        }
        val node = LRUNode(key, value)
        add(node)
        cache[key] = node
    }

    private fun remove(node: LRUNode) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
    }

    private fun add(node: LRUNode) {
        node.next = head.next
        head.next?.prev = node
        head.next = node
        node.prev = head
    }

    internal class LRUNode(val key: Int, var value: Int, var next: LRUNode? = null, var prev: LRUNode? = null)

}

fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return listOf()
    return getLetterCombinations(digits)
}

fun getLetterCombinations(
    digits: String,
    digitIndex: Int = 0,
    combination: String = "",
    lettersMap: Map<Char, String> =
        mapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )
): List<String> {

    val result = mutableListOf<String>()
    if (combination.length == digits.length) return listOf(combination)

    lettersMap[digits[digitIndex]]?.forEach { char ->
        result.addAll(
            getLetterCombinations(
                digits,
                digitIndex.inc(),
                StringBuilder(combination).append(char).toString()
            )
        )
    }

    return result
}

fun majorityElement(nums: IntArray): Int {

    var candidate = nums[0]
    var candidateCount = 1

    for (i in 1..nums.lastIndex) {

        if (nums[i] == candidate) candidateCount++
        else candidateCount--

        if (candidateCount == 0) {
            candidate = nums[i]
            candidateCount++
        }
    }

    return candidate
}

fun isHappy(n: Int): Boolean {
    var digits = n.toList()
    val sums = mutableSetOf<Int>()
    var curSum = 0

    while (curSum != 1) {
        curSum = digits.map { it.times(it) }.sum()
        digits = curSum.toList()

        if (sums.add(curSum).not()) return false
    }

    return true
}

private fun Int.toList() = toString().map { it.toInt() - '0'.toInt() }

fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    val array1NumsFreqMap = mutableMapOf<Int, Int>()
    val result = mutableListOf<Int>()

    nums1.forEach { number ->
        val freq = array1NumsFreqMap[number] ?: 0
        array1NumsFreqMap[number] = freq.inc()
    }

    nums2.forEach { number ->
        val freq = array1NumsFreqMap[number] ?: 0
        if (freq != 0) {
            result.add(number)
            array1NumsFreqMap[number] = freq.dec()
        }
    }

    return result.toIntArray()
}

fun missingNumber(nums: IntArray): Int {
    var expectedSum = 0

    for (i in 0..nums.size) expectedSum += i

    nums.forEach { expectedSum -= it }

    return expectedSum
}

/**
 * Floyd's Cycle Detection
 * https://leetcode.com/problems/find-the-duplicate-number/solution/
 *  @see <a href="https://www.youtube.com/watch?v=wjYnzkAhcNk">link</a>
 */
fun findDuplicate(nums: IntArray): Int {
    var fast = nums[0]
    var slow = nums[0]

    // Find the intersection point of the two runners.
    do {
        slow = nums[slow]
        fast = nums[nums[fast]]
    } while (slow != fast)

    // Find the entrance "the duplicate" to the cycle.
    slow = nums[0]
    while (slow != fast) {
        slow = nums[slow]
        fast = nums[fast]
    }

    return slow
}