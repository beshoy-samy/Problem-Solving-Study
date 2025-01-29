fun arraySum(array: List<Int>): Int {
    if (array.isEmpty()) return 0
    return array.first() + arraySum(array.drop(1))
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
