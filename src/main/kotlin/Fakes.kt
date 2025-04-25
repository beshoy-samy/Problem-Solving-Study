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

//      -1
//     /   \
//   -6    -5
//   / \     \
//  -1  0    -13
//      /       \
//     -1       -2
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

//       a
//        \
//         b
//          \
//           c
//            \
//             d
val fakeLongPathBinaryTree by lazy {
    val a = Node("a")
    val b = Node("b")
    val c = Node("c")
    val d = Node("d")

    a.right = b
    b.right = c
    c.right = d
    a
}

//       a
//        \
//         b
//        / \
//       d   c
val fakeLongPathBinaryTree2 by lazy {
    val a = Node("a")
    val b = Node("b")
    val c = Node("c")
    val d = Node("d")

    a.right = b
    b.right = c
    b.left = d
    a
}

//       a
//      /
//     10
//    / \
//   4  17
//    \
//     5
val fakeIntBinaryTree2 by lazy {
    val a = Node(19)
    val b = Node(10)
    val c = Node(4)
    val d = Node(17)
    val e = Node(5)

    a.left = b
    b.left = c
    b.right = d
    c.right = e
    a
}

//  1
//   \
//    2
//     \
//      3
//       \
//        4
val fakeBST by lazy {
    val a = Node(1)
    val b = Node(2)
    val c = Node(3)
    val d = Node(4)

    a.right = b
    b.right = c
    c.right = d
    a
}

//  12
//  /
// 9
//  \
//   11
val fakeBST2 by lazy {
    val a = Node(12)
    val b = Node(9)
    val c = Node(11)

    a.left = b
    b.right = c
    a
}

//          1
//         / \
//       14   15
//       /     \
//      7       17
//     / \
//    2  12
//     \ /
//     3 9
//        \
//         11
val fakeBSTHard by lazy {
    val a = Node(1)
    val b = Node(14)
    val c = Node(15)
    val d = Node(7)
    val e = Node(17)
    val f = Node(2)
    val g = Node(12)
    val h = Node(3)
    val j = Node(9)
    val k = Node(11)

    a.left = b
    a.right = c
    b.left = d
    c.right = e
    d.left = f
    d.right = g
    f.right = h
    g.left = j
    j.right = k
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

val graph: Map<String, List<String>> = mapOf(
    "f" to listOf("g", "i"),
    "g" to listOf("h"),
    "h" to listOf(),
    "i" to listOf("g", "k"),
    "j" to listOf("i"),
    "k" to listOf()
)
