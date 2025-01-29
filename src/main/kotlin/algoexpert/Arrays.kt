package algoexpert

fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {
    // Write your code here.
    val map = mutableMapOf<Int, Int>()
    val result = mutableListOf<Int>()
    // create the sum map
    for (number in array) {
        val valueNeededToSumUpToTarget = targetSum.minus(number)

        if (map[number] == null)
            map[valueNeededToSumUpToTarget] = number
        else {
            result.add(number)
            result.add(map[number]!!)
        }
    }
    return result
}

fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
    // Write your code here.
    var pointer = 0
    for (number in array) {
        if (number == sequence.getOrNull(pointer)) pointer++
    }
    return pointer == sequence.size
}

fun sortedSquaredArray(array: List<Int>): List<Int> {
    // Write your code here.
    val result = mutableListOf<Int>()
    for (number in array){
        result.add(number.times(number))
    }
    return result
}
