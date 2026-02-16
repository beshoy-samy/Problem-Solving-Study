inline fun String.ifNotEmpty(transform: String.() -> String): String =
    if (this.isNotEmpty()) this.transform() else String()

fun <T> Node<T>.isLeaf(): Boolean = left == null && right == null

fun <T> printTree(node: Node<T>?, prefix: String = "", isLeft: Boolean = false) {
    if (node == null) return

    node.right?.let { printTree(it, prefix + (if (isLeft) "    " else "│   "), false) }

    val connector = if (prefix.isEmpty()) "" else if (isLeft) "└── " else "├── "
    println(prefix + connector + node.value)

    node.left?.let { printTree(it, prefix + (if (isLeft) "    " else "│   "), true) }
}

// r to c
fun Pair<Int, Int>.neighbours() =
    listOf(first to second.dec(), first to second.inc(), first.dec() to second, first.inc() to second)

// r to c
fun Pair<Int, Int>.downAndRight() = listOf(first to second.inc(), first.inc() to second)
