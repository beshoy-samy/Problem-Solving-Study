package structy

import ListNode

fun arraySum(array: List<Int>): Int {
    if (array.isEmpty()) return 0
    return array.last() + arraySum(array.dropLast(1))
}

fun reverseString(s: String): String {
    if (s.isEmpty()) return ""
    return s.last() + reverseString(s.dropLast(1))
}

fun palindrome(s: String): Boolean {
    if (s.isEmpty()) return true
    if (s.length == 1) return true
    if (s.first() != s.last()) return false
    return palindrome(s.substring(1, s.lastIndex))
}

fun fibonacci(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    return fibonacci(n-1) + fibonacci(n-2)
}

fun addTwoNumbers(l1: ListNode<Int>?, l2: ListNode<Int>?, carry: Int = 0): ListNode<Int>? {
    fun ListNode<Int>?.value() = this?.value ?: 0

    if (l1 == null && l2 == null && carry == 0) return null

    val sum = l1.value() + l2.value() + carry

    return ListNode(sum % 10).apply {
        next = addTwoNumbers(l1?.next, l2?.next, sum / 10)
    }
}

fun isPowerOfTwo(n: Int): Boolean {
    if (n == 1) return true
    if (n == 0) return false
    if (n.rem(2) != 0) return false
    return isPowerOfTwo(n.div(2))
}

fun isPalindrome(head: ListNode<Int>?): Boolean {
    var left: ListNode<Int>? = ListNode(0, head)

    fun isPalindromeRecursive(head: ListNode<Int>?): Boolean {
        if (head == null) return true
        val right = isPalindromeRecursive(head.next)
        left = left?.next
        return right && left?.value == head.value
    }

    return isPalindromeRecursive(head)
}

fun sumList(head: ListNode<Int>?): Int {
    if (head == null) return 0
    return head.value + sumList(head.next)
}

fun listNodeSearch(head: ListNode<Int>?, value: Int): Boolean {
    if (head == null) return false
    if (head.value == value) return true
    return listNodeSearch(head.next, value)
}

fun valueAt(head: ListNode<Int>?, index: Int): Int? {
    require(index >= 0) { "index can't be negative" }
    if (head == null) return null
    if (index == 0) return head.value
    return valueAt(head.next, index.dec())
}

fun reverse(head: ListNode<Int>?, prev: ListNode<Int>? = null): ListNode<Int>? {
    if (head == null) return prev
    val next = head.next
    head.next = prev
    return reverse(next, head)
}

fun zip(head1: ListNode<Int>?, head2: ListNode<Int>?): ListNode<Int>? {
    if (head1 == null) return head2
    if (head2 == null) return head1

    val next = head1.next
    val next2 = head2.next

    head1.next = head2
    head2.next = zip(next, next2)
    return head1
}

fun <T> subsets(elements: List<T>): List<List<T>> {
    if (elements.isEmpty()) return listOf(listOf())

    val element = elements.first()
    val droppedSubsets = subsets(elements.drop(1))

    val subsets = mutableListOf<List<T>>()
    subsets.addAll(droppedSubsets)

    droppedSubsets.forEach {
        val set = it.toMutableList()
        set.add(element)
        subsets.add(set)
    }

    return subsets
}

fun <T> permutations(elements: List<T>): List<List<T>> {
    if (elements.isEmpty()) return mutableListOf(listOf())

    val all = mutableListOf<List<T>>()
    val element = elements.first()
    val permutations = permutations(elements.drop(1))

    permutations.forEach {
        for (i in 0..it.size) {
            val list = mutableListOf<T>()
            val left = it.subList(0, i)
            val right = it.subList(i, it.size)
            list.addAll(left)
            list.add(element)
            list.addAll(right)
            all.add(list)
        }
    }

    return all
}

fun <T> createCombinations(elements: List<T>, k: Int): List<List<T>> {
    if (elements.isEmpty()) return listOf(listOf())
    if (k == 0) return listOf(listOf())
    if (elements.size == k) return listOf(elements)
    if (k > elements.size) return listOf(listOf())

    val all = mutableListOf<List<T>>()
    val element = elements.first()
    val smallerCombinations = createCombinations(elements.drop(1), k.dec())
    val combinations = createCombinations(elements.drop(1), k)
    all.addAll(combinations)
    smallerCombinations.forEach {
        val comb = it.toMutableList()
        comb.add(element)
        all.add(comb)
    }

    return all
}
