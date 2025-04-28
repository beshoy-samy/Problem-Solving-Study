import leetcode.balanceBST
import structy.*

fun main(args: Array<String>) {
    //printTree(fakeBST)
    //printTree(balanceBST(fakeBSTHard).first)
    println(
        hasCycle(
            mapOf(
                "a" to listOf("b"),
                "b" to listOf("c"),
                "c" to listOf("d"),
                "d" to listOf("b")
            )
        )
    )
}
