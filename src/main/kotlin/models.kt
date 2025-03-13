data class ListNode<T>(var value: T, var next: ListNode<T>? = null)

fun <T> ListNode<T>?.value(default: T) = this?.value ?: default

data class Node<T>(var value: T, var left: Node<T>? = null, var right: Node<T>? = null)

val fakeBinaryTree by lazy {
    val b = Node('b')
    val c = Node('c')
    val d = Node('d')
    val e = Node('e')
    val f = Node('f')
    b.left = d
    b.right = e
    c.right = f
    Node('a', b, c)
}

val fakeBinaryIntTree by lazy {
    val b = Node(11)
    val c = Node(3)
    val d = Node(4)
    val e = Node(2)
    val f = Node(1)
    b.left = d
    b.right = e
    c.right = f
    Node(5, b, c)
}

val fakeNegativesBinaryTree by lazy {
    val b = Node(-6)
    val c = Node(-5)
    val d = Node(-1)
    val e = Node(0)
    val f = Node(-13)
    val g = Node(-1)
    val h = Node(-2)
    b.left = d
    b.right = e
    c.right = f
    e.left = g
    f.right = h
    Node(-1, b, c)
}

//       a
//     /    \
//    b      c
//     \
//      d
//      /
//     e
//      \
//       f
val fakeLongBinaryTree by lazy {
    val a = Node("a")
    val b = Node("b")
    val c = Node("c")
    val d = Node("d")
    val e = Node("e")
    val f = Node("f")

    a.left = b
    a.right = c
    b.right = d
    d.left = e
    e.right = f
    a
}

val fakeLeetListNode by lazy {
    val a = leetcode.ListNode(5)
    val b = leetcode.ListNode(7)
    val c = leetcode.ListNode(10)
    a.next = b
    b.next = c
    a
}
val fakeLeetListNode2 by lazy {
    val a = leetcode.ListNode(6)
    val b = leetcode.ListNode(8)
    val c = leetcode.ListNode(9)
    a.next = b
    b.next = c
    a
}

val fakeLeetListNodeString by lazy {
    val a = ListNode("a")
    val b = ListNode("b")
    val c = ListNode("c")
    val d = ListNode("d")
    a.next = b
    b.next = c
    c.next = d
    a
}

val fakeLeetListNodeString2 by lazy {
    val a = ListNode("e")
    val b = ListNode("f")
    val c = ListNode("g")
    val d = ListNode("j")
    val e = ListNode("k")
    a.next = b
    b.next = c
    c.next = d
    d.next = e
    a
}

val fakeListNode by lazy {
    val a = ListNode(7)
    val b = ListNode(7)
    val c = ListNode(7)
    a.next = b
    b.next = c
    a
}
val fakeListNode2 by lazy {
    val a = ListNode(6)
    val b = ListNode(8)
    val c = ListNode(9)
    a.next = b
    b.next = c
    a
}
