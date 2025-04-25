package structy

import Node
import QueueArray
import StackArray
import kotlin.math.max

fun depthFirstValues(root: Node<String>?): List<String> {
    if (root == null) return listOf()
    val stack = StackArray<Node<String>>()
    stack.push(root)
    val values = mutableListOf<String>()
    while (stack.isEmpty().not()) {
        val node = stack.pop()!!
        values.add(node.value)
        if (node.right != null) stack.push(node.right!!)
        if (node.left != null) stack.push(node.left!!)
    }
    return values
}

fun dfs(root: Node<String>?, values: MutableList<String> = mutableListOf()): List<String> {
    if (root == null) return values
    values.add(root.value)
    dfs(root.left, values)
    dfs(root.right, values)
    return values
}

fun <T> breadthFirstValues(root: Node<T>?): List<T> {
    if (root == null) return listOf()
    val queue = QueueArray(root)
    val values = mutableListOf<T>()
    while (queue.isNotEmpty()) {
        val node = queue.dequeue()!!
        values.add(node.value)
        if (node.left != null) queue.enqueue(node.left!!)
        if (node.right != null) queue.enqueue(node.right!!)
    }
    return values
}

fun treeSum(root: Node<Int>?): Int {
    if (root == null) return 0
    return root.value + treeSum(root.left) + treeSum(root.right)
}

fun treeIncludes(root: Node<String>?, target: String): Boolean {
    if (root == null) return false
    if (root.value == target) return true
    return treeIncludes(root.left, target) or treeIncludes(root.right, target)
}

fun treeMinValue(root: Node<Double>?): Double {
    if (root == null) return Double.POSITIVE_INFINITY
    return minOf(root.value, treeMinValue(root.left), treeMinValue(root.right))
}

fun maxPathSum(root: Node<Double>?): Double {
    if (root == null) return Double.NEGATIVE_INFINITY
    if (root.left == null && root.right == null) return root.value
    return root.value + maxOf(maxPathSum(root.left), maxPathSum(root.right))
}

fun <T> pathFinder(root: Node<T>?, target: T, path: MutableList<T> = mutableListOf()): List<T>? {
    if (root == null) return null
    path.add(root.value)
    if (root.value == target) return path
    return pathFinder(root.left, target, path) ?: pathFinder(root.right, target, path)
}

fun <T> treeValueCount(root: Node<T>?, target: T): Int {
    if (root == null) return 0
    val count = if (root.value == target) 1 else 0
    return count + treeValueCount(root.left, target) + treeValueCount(root.right, target)
}

fun <T> howHigh(root: Node<T>?): Int {
    if (root == null) return -1
    if (root.left == null && root.right == null) return 0
    val left = howHigh(root.left).inc()
    val right = howHigh(root.right).inc()
    return max(left, right)
}

/*
 * don't always force to solve in recursion
 * for example here bfs is most suited
 */
fun <T> bottomRightValue(root: Node<T>): T {
    val queue = QueueArray(root)
    var rightBottomNode = root.value
    while (queue.isNotEmpty()) {
        val node = queue.dequeue()!!
        rightBottomNode = node.value
        node.left?.let { queue.enqueue(it) }
        node.right?.let { queue.enqueue(it) }
    }
    return rightBottomNode
}

fun <T> allTreePaths(root: Node<T>?, path: List<T> = listOf()): List<List<T>> {
    if (root == null) return listOf()
    val currentPath = path + root.value
    if (root.left == null && root.right == null) return listOf(currentPath)
    val left = allTreePaths(root.left, currentPath)
    val right = allTreePaths(root.right, currentPath)
    return left + right
}

fun <T> treeLevels(root: Node<T>?): List<List<T>> {
    if (root == null) return listOf()
    val levels = mutableListOf<MutableList<T>>()
    val queue = QueueArray(root to 0)
    while (queue.isNotEmpty()) {
        val (node, level) = queue.dequeue()!!
        if (levels.size <= level) levels.add(mutableListOf())
        levels[level] = levels[level].apply { add(node.value) }
        node.left?.let { queue.enqueue(it to level.inc()) }
        node.right?.let { queue.enqueue(it to level.inc()) }
    }
    return levels
}

fun levelAverages(root: Node<Int>?): List<Double> {
    if (root == null) return listOf()
    val levels = mutableListOf<Pair<Int, Int>>()
    val queue = QueueArray(root to 0)
    while (queue.isNotEmpty()) {
        val (node, level) = queue.dequeue()!!
        if (levels.size <= level) levels.add(node.value to 1)
        else levels[level] = levels[level].first.plus(node.value) to levels[level].second.inc()
        node.left?.let { queue.enqueue(it to level.inc()) }
        node.right?.let { queue.enqueue(it to level.inc()) }
    }

    val averages = levels.map { (sum, count) -> sum.toDouble().div(count) }

    return averages
}

fun <T> leafList(root: Node<T>?): List<T> {
    if (root == null) return listOf()
    if (root.left == null && root.right == null) return listOf(root.value)
    return leafList(root.left) + leafList(root.right)
}
