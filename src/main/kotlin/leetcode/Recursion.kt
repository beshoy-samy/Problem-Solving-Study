package leetcode

fun addTwoNumbersRecursion(l1: ListNode?, l2: ListNode?, carry: Int = 0): ListNode? {
    if (l1 == null && l2 == null) return if (carry == 0) null else ListNode(carry)
    val sum = l1.value().plus(l2.value()).plus(carry)
    val nodeValue = sum.mod(10)
    return ListNode(nodeValue, addTwoNumbersRecursion(l1?.next, l2?.next, sum.div(10)))
}