import leetcode.balanceBST
import structy.*

fun main(args: Array<String>) {
    //printTree(fakeBST)
    //printTree(balanceBST(fakeBSTHard).first)
    println(
        bestBridge(
            listOf(
                listOf("W", "W", "W", "L", "L"),
                listOf("L", "L", "W", "W", "L"),
                listOf("L", "L", "L", "W", "L"),
                listOf("W", "L", "W", "W", "W"),
                listOf("W", "W", "W", "W", "W"),
                listOf("W", "W", "W", "W", "W")
            )
        )
    )
}
