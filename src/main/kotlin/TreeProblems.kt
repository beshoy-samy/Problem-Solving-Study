data class BinaryTree(val value: Int) {
    var left: BinaryTree? = null
    var right: BinaryTree? = null
}

fun calculateBranchSums(
    node: BinaryTree? = null,
    sum: Int = 0,
    sums: MutableList<Int> = mutableListOf()
): MutableList<Int> {
    if (node == null) return sums
    val currentSum = node.value.plus(sum)
    if (node.left == null && node.right == null) return sums.apply { add(currentSum) }
    calculateBranchSums(node.left, currentSum, sums)
    return calculateBranchSums(node.right, currentSum, sums)
}

fun calculateNodeDepth(node: BinaryTree? = null, depth: Int = 0): Int {
    if (node == null) return 0
    println("currentDepth of node $node is $depth")
    if (node.left == null && node.right == null) return depth

    val leftDepth = calculateNodeDepth(node.left, depth.inc())
    val rightDepth = calculateNodeDepth(node.right, depth.inc())
    println("left depth is $leftDepth, right depth is $rightDepth")
    return leftDepth.plus(rightDepth).plus(depth)
}

class Node(val name: String) {
    val children = mutableListOf<Node>()

    fun depthFirstSearch(): List<String> {
        // Write your code here.
        val dfs = mutableListOf<String>()
        goDeep(this, dfs)
        return dfs
    }

    fun goDeep(node: Node, dfs: MutableList<String>){
        dfs.add(node.name)
        println(dfs)
        node.children.forEach { goDeep(it, dfs) }
    }
}