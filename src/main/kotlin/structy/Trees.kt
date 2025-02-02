package structy

import Node
import QueueArray
import ifNotEmpty
import kotlin.math.max

fun dfs(root: Node<Char>?) {
    if (root == null) return
    print(root.value)
    dfs(root.left)
    dfs(root.right)
}

fun bfs(root: Node<Char>?) {
    if (root == null) return

    val queue = QueueArray<Node<Char>>()
    queue.enqueue(root)

    while (queue.isEmpty().not()) {
        val current = queue.dequeue()!!
        print(current.value)
        current.left?.also { queue.enqueue(it) }
        current.right?.also { queue.enqueue(it) }
    }

}

fun treeSum(root: Node<Int>?): Int {
    if (root == null) return 0
    return root.value + treeSum(root.left) + treeSum(root.right)
}

fun <T> treeIncludes(root: Node<T>?, value: T): Boolean {
    if (root == null) return false

    if (root.value == value) return true

    return treeIncludes(root.left, value) || treeIncludes(root.right, value)
}

fun treeMinValue(root: Node<Int>?): Int? {
    if (root == null) return null

    return minOf(root.value, treeMinValue(root.left) ?: Int.MAX_VALUE, treeMinValue(root.right) ?: Int.MAX_VALUE)
}

fun maxPathSum(root: Node<Int>?): Int {
    if (root == null) return Int.MIN_VALUE

    if (root.left == null && root.right == null) return root.value

    return root.value + max(maxPathSum(root.left), maxPathSum(root.right))
}


/**
 * T -> O(n^2) because of the list appending to alternate this we can append normally to the list and build a function on top to reverse it
 * S -> O(n)
 */
fun <T> pathFinder(root: Node<T>?, target: T): List<T>? {
    if (root == null) return null

    if (root.value == target) return listOf(target)

    val left = pathFinder(root.left, target)
    val right = pathFinder(root.right, target)

    return if (left != null) listOf(root.value) + left else if (right != null) listOf(root.value) + right else null
}

fun binaryTreePaths(root: Node<Char>?, path: String = String()): MutableList<String> {
    if (root == null) return mutableListOf()

    if (root.left == null && root.right == null) return mutableListOf(path.ifNotEmpty { "$path->" } + root.value)

    return binaryTreePaths(root.left, path.ifNotEmpty { "$path->" } + root.value).apply {
        addAll(binaryTreePaths(root.right, path.ifNotEmpty { "$path->" } + root.value))
    }
}

fun <T> treeValueCount(root: Node<T>?, target: T): Int {
    if (root == null) return 0

    return (if (root.value == target) 1 else 0) + treeValueCount(root.left, target) + treeValueCount(root.right, target)
}

fun <T> howHigh(root: Node<T>?): Int {
    if (root == null) return -1

    return 1 + maxOf(howHigh(root.left), howHigh(root.right))
}

fun <T> bottomRightValue(root: Node<T>): T {
    val queue = QueueArray(root)

    var current = root
    while (queue.isEmpty().not()) {
        current = queue.dequeue()!!

        if (current.left != null)
            queue.enqueue(current.left!!)

        if (current.right != null)
            queue.enqueue(current.right!!)

    }

    return current.value
}

fun <T> treeLevels(root: Node<T>?): List<List<T>> {
    if (root == null) return listOf()

    val queue = QueueArray(root to 0)
    val levels: MutableList<MutableList<T>> = mutableListOf()

    while (queue.isNotEmpty()) {
        val (node, levelIndex) = queue.dequeue()!!


        val level = levels.getOrNull(levelIndex) ?: mutableListOf()
        level.add(node.value)
        if (levels.size < levelIndex.inc()) {
            levels.add(levelIndex, level)
        } else {
            levels[levelIndex] = level
        }

        node.left?.let { queue.enqueue(it to levelIndex.inc()) }
        node.right?.let { queue.enqueue(it to levelIndex.inc()) }
    }

    return levels
}

fun <T> leafValues(root: Node<T>?): List<T> {
    if (root == null) return listOf()

    if (root.left == null && root.right == null) return listOf(root.value)

    return leafValues(root.left) + leafValues(root.right)
}
