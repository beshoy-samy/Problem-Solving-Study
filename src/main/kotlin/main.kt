import structy.prereqsPossible

fun main(args: Array<String>) {
    //printTree(fakeBST)
    //printTree(balanceBST(fakeBSTHard).first)
    println(
        prereqsPossible(
            6,
            listOf(
                listOf(0, 1),
                listOf(2, 3),
                listOf(0, 2),
                listOf(1, 3),
                listOf(4, 5),
                listOf(3, 0)
            )
        )
    )
}
