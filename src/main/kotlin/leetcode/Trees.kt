package leetcode

import Node
import QueueArray
import calculateNodeDepth
import com.sun.org.apache.bcel.internal.generic.SWAP
import isLeaf
import structy.howHigh
import kotlin.math.abs
import kotlin.math.max

/*fun <T> balanceBST(root: Node<T>?): Node<T>? {
    if (root == null) return null
    var rootNode = root to 0
    var swap: Node<T>? = null
    val queue = QueueArray(root to 0)
    while (queue.isNotEmpty()) {
        val (node, level) = queue.dequeue()!!
        if (level <= rootNode.second) {
            rootNode = node to level
        }
        if (swap != null && node.left == null) {
            node.left = swap
            swap = null
        } else if (swap != null && node.right == null) {
            node.right = swap
            swap = null
        }
        if (node.left == null && node.right != null && node.right!!.isLeaf().not()) {
            queue.enqueue(node.right!! to level)
            node.right = null
            swap = node
        } else if (node.right == null && node.left != null && node.left!!.isLeaf().not()) {
            queue.enqueue(node.left!! to level)
            node.left = null
            swap = node
        } else {
            node.left?.let { queue.enqueue(it to level.inc()) }
            node.right?.let { queue.enqueue(it to level.inc()) }
        }
    }
    return rootNode.first
}*/

fun balanceBST(root: Node<Int>?): Pair<Node<Int>?, Int> {
    if (root == null) return null to -1
    if (root.isLeaf()) return root to 0
    val (left, lDepth) = balanceBST(root.left)
    root.left = left
    val (right, rDepth) = balanceBST(root.right)
    root.right = right
    if (lDepth.minus(rDepth) > 1) {
        root.left = null
        val (balanced, depth) = balance(left, root, lDepth)
        return if (depth > lDepth) balanceBST(balanced)
         else balanced to depth
    } else if (rDepth.minus(lDepth) > 1) {
        root.right = null
        val (balanced, depth) = balance(right, root, rDepth)
        return if (depth > rDepth) balanceBST(balanced)
        else balanced to depth
    } else {
        return root to max(lDepth, rDepth).inc()
    }
}

fun balance(root: Node<Int>?, swap: Node<Int>, depth: Int): Pair<Node<Int>?, Int> {
    if (root == null) return null to depth
    if (swap.value > root.value && root.right == null) {
        root.right = swap
        return root to depth
    } else if (swap.value < root.value && root.left == null) {
        root.left = swap
        return root to depth
    }
    return root to if (swap.value >= root.value) {
        balance(root.right, swap, depth.inc()).second
    } else {
        balance(root.left, swap, depth.inc()).second
    }
}
