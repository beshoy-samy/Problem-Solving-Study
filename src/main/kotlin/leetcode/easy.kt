package leetcode

fun romanToInt(s: String): Int {

    val roman = mutableMapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    var romanNum = 0
    var last = 1000

    s.forEach {
        val value = roman[it] ?: 0
        if (value > last) romanNum -= last * 2
        romanNum += value
        last = value
    }

    return romanNum

}

fun intToRoman(num: Int): String {

    val roman = mutableMapOf(
        1000 to "M",
        900 to "CM",
        500 to "D",
        400 to "CD",
        100 to "C",
        90 to "XC",
        50 to "L",
        40 to "XL",
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
    )

    var reminder = num
    val romanNum = StringBuilder()

    roman.keys.forEach { key ->

        val charValueCount = reminder.div(key)

        roman[key]?.repeat(charValueCount)?.let {
            romanNum.append(it)
        }

        reminder %= key
    }

    return romanNum.toString()
}

fun searchInsert(nums: IntArray, target: Int): Int {
    return searchInsertHelper(nums, target)
}

fun searchInsertHelper(nums: IntArray, target: Int, start: Int = 0, end: Int = nums.lastIndex): Int {
    if (nums.isEmpty() || start > end) return start

    val targetIndex = start.plus(end).div(2)

    return if (target == nums[targetIndex]) targetIndex
    else if (target > nums[targetIndex]) searchInsertHelper(nums, target, targetIndex + 1, end)
    else if (target < nums[targetIndex]) searchInsertHelper(nums, target, start, targetIndex - 1)
    else start
}

class ListNode(var `val`: Int, var next: ListNode? = null)

fun ListNode?.value() = this?.`val` ?: 0

fun ListNode?.print() {
    var next = this
    while (next != null) {
        print(next.`val`)
        next = next.next
        if (next != null) print("->")
    }
    println()
}

/* EX
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807
*/
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

    val head = ListNode(0)
    var curNode: ListNode? = head

    var leftNode = l1
    var rightNode = l2

    var reminder = 0

    while (leftNode != null || rightNode != null) {

        val totalSum =
            leftNode?.`val`?.plus(rightNode?.`val` ?: 0)?.plus(reminder)
                ?: rightNode!!.`val`.plus(leftNode?.`val` ?: 0).plus(reminder)

        val sum = totalSum.rem(10)
        reminder = totalSum.div(10)

        curNode?.next = ListNode(sum)

        curNode = curNode?.next
        leftNode = leftNode?.next
        rightNode = rightNode?.next
    }

    if (reminder != 0) curNode?.next = ListNode(reminder)

    return head.next

}

/*"11", "1"*/
fun addBinary(a: String, b: String): String {

    val stingBuilder = StringBuilder()

    var i = a.lastIndex
    var j = b.lastIndex
    var carry = 0

    while (i >= 0 || j >= 0) {
        var totalSum = carry

        if (i >= 0) {
            totalSum += a[i] - '0'
            i--
        }

        if (j >= 0) {
            totalSum += b[j] - '0'
            j--
        }

        val binarySum = totalSum.rem(2)
        carry = totalSum.div(2)
        stingBuilder.append(binarySum)
    }

    if (carry != 0) stingBuilder.append(carry)

    return stingBuilder.reverse().toString()
}

/* EX lengthOfLongestSubstring //sliding window
Input: s = "abcad"
Output: 4
*/
fun lengthOfLongestSubstring(s: String): Int {

    var max = 0
    var firstPointer = 0
    var secondPointer = 0
    val substring = mutableSetOf<Char>()

    while (secondPointer < s.length) {

        if (substring.add(s[secondPointer])) {
            max = maxOf(max, substring.size)
            secondPointer++
        } else {
            substring.remove(s[firstPointer])
            firstPointer++
        }
    }

    return max
}

