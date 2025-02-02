data class ListNode<T>(var value: T, var next: ListNode<T>? = null)

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
