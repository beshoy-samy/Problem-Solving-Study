package structy

import ListNode
import value
import kotlin.math.max

fun linkedListValues(head: ListNode<String>?, values: MutableList<String> = mutableListOf()): MutableList<String> {
    if (head == null) return mutableListOf()
    values.add(head.value)
    linkedListValues(head.next, values)
    return values
}

fun sumList(head: ListNode<Int>?, sum: Int = 0): Int {
    if (head == null) return sum
    return sumList(head.next, head.value.plus(sum))
}

fun <T> linkedListFind(head: ListNode<T>?, target: T): Boolean {
    if (head == null) return false
    if (head.value == target) return true
    return linkedListFind(head.next, target)
}

// hint we could have decremented the index in each recursive call instead and the base case would be checking when it reaches 0
fun <T> getNodeValue(head: ListNode<T>?, index: Int, curIndex: Int = 0): T? {
    if (head == null) return null
    if (curIndex == index) return head.value
    return getNodeValue(head.next, index, curIndex.inc())
}

fun <T> reverseList(head: ListNode<T>?): ListNode<T>? {
    var headPointer = head
    var prev: ListNode<T>? = null

    while (headPointer != null) {
        val next = headPointer.next
        headPointer.next = prev
        prev = headPointer
        headPointer = next
    }
    return prev
}

fun <T> reverse(head: ListNode<T>?, prev: ListNode<T>? = null): ListNode<T>? {
    if (head == null) return prev
    val next = head.next
    head.next = prev
    return reverse(next, head)
}

fun <T> zipperLists(head1: ListNode<T>?, head2: ListNode<T>?, count: Int = 0): ListNode<T>? {
    if (head1 == null) return head2
    if (head2 == null) return head1

    if (count.mod(2) == 0) {
        head1.next = zipperLists(head1.next, head2, count.inc())
        return head1
    } else {
        head2.next = zipperLists(head1, head2.next, count.inc())
        return head2
    }
}

fun mergeLists(head1: ListNode<Int>?, head2: ListNode<Int>?): ListNode<Int>? {
    if (head1 == null) return head2
    if (head2 == null) return head1

    if (head1.value < head2.value) {
        head1.next = mergeLists(head1.next, head2)
        return head1
    } else {
        head2.next = mergeLists(head1, head2.next)
        return head2
    }
}

fun <T> isUnivalueList(head: ListNode<T>?, value: T? = head?.value): Boolean {
    if (head == null) return true
    return head.value == value && isUnivalueList(head.next, head.value)
}

fun <T> longestStreak(head: ListNode<T>?, value: T? = head?.value, streak: Int = 0): Int {
    if (head == null) return streak
    return if (head.value == value) longestStreak(head.next, head.value, streak = streak.inc())
    else max(streak, longestStreak(head.next, head.value, streak = 1))
}

fun <T> removeNode(head: ListNode<T>?, targetVal: T): ListNode<T>? {
    if (head?.value == targetVal) return head?.next
    head?.next = removeNode(head?.next, targetVal)
    return head
}

// a b c d
// 0 1 2 3
//
fun <T> insertNode(head: ListNode<T>?, value: T, index: Int): ListNode<T>? {
    if (index == 0) return ListNode(value, head)
    head?.next = insertNode(head?.next, value, index.dec())
    return head
}

// hint instead of dropping from the list which is O(N) leading to a total of O(N^2) we can use an index param
fun <T> createLinkedList(values: List<T>): ListNode<T>? {
    if (values.isEmpty()) return null
    val head = ListNode(values.first(), createLinkedList(values.drop(1)))
    return head
}

// now this is total of O(N)
fun <T> createLinkedList(values: List<T>, index: Int = 0): ListNode<T>? {
    if (index == values.size) return null
    val head = ListNode(values[index], createLinkedList(values, index.inc()))
    return head
}

fun addLists(head1: ListNode<Int>?, head2: ListNode<Int>?, carry: Int = 0): ListNode<Int>? {
    if (head1 == null && head2 == null) return if (carry > 0) ListNode(carry) else null
    val sum = head1.value(default = 0) + head2.value(default = 0) + carry
    return ListNode(sum.mod(10), addLists(head1?.next, head2?.next, sum.div(10)))
}

fun hasCycle(head: ListNode<Int>): Boolean {
    var slow: ListNode<Int>? = head
    var fast: ListNode<Int>? = head

    while (fast?.next != null) {
        fast = fast.next?.next
        if (fast == slow) return true
        slow = slow?.next
    }

    return false
}
