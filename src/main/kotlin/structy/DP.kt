package structy

import kotlin.math.max
import kotlin.math.sqrt

fun fib(n: Int, memory: MutableMap<Int, Int> = mutableMapOf(0 to 0, 1 to 1)): Int {
    if (memory[n] != null) return memory[n]!!
    val nthFib = fib(n - 1, memory) + fib(n - 2, memory)
    memory[n] = nthFib
    return nthFib
}

fun tribonacci(n: Int, memory: MutableMap<Int, Int> = mutableMapOf(0 to 0, 1 to 0, 2 to 1)): Int {
    if (memory.containsKey(n)) return memory[n]!!
    if (n <= 1) return 0

    val tribonacci = tribonacci(n - 1, memory) + tribonacci(n - 2, memory) + tribonacci(n - 3, memory)
    memory[n] = tribonacci
    return tribonacci
}

fun sumPossible(
    amount: Int,
    numbers: List<Int>,
    memory: MutableMap<Int, Boolean> = mutableMapOf(),
): Boolean {
    if (amount == 0) return true
    if (amount < 0) return false

    if (memory.containsKey(amount)) return memory[amount]!!


    numbers.forEach { n ->
        if (sumPossible(amount - n, numbers, memory)) {
            memory[amount] = true
            return true
        }
    }

    memory[amount] = false
    return false
}

fun minChange(amount: Int, coins: List<Int>, memory: MutableMap<Int, Int> = mutableMapOf()): Int {
    if (amount == 0) return 0
    if (amount < 0) return -1
    if (memory.containsKey(amount)) return memory[amount]!!

    var minimum = -1

    coins.forEach {
        val result = minChange(amount - it, coins, memory)
        if (result != -1) {
            val tries = result.inc()
            if (tries < minimum || minimum == -1) {
                minimum = tries
            }
        }
    }
    memory[amount] = minimum
    return minimum
}

fun countPaths(
    grid: List<List<String>>,
    x: Int = 0,
    y: Int = 0,
    memory: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
): Int {
    if (x > grid.first().lastIndex || y > grid.lastIndex) return 0
    if (grid[y][x] == "X") return 0
    if (x == grid.first().lastIndex && y == grid.lastIndex) return 1
    if (memory.containsKey(x to y)) return memory[x to y]!!
    val paths = countPaths(grid, x.inc(), y, memory) + countPaths(grid, x, y.inc(), memory)
    memory[x to y] = paths
    return paths
}

fun maxPathSum(
    grid: List<List<Int>>,
    x: Int = 0,
    y: Int = 0,
    memory: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
): Int {
    if (x > grid.first().lastIndex || y > grid.lastIndex) return 0
    if (x == grid.first().lastIndex && y == grid.lastIndex) return grid[y][x]
    if (memory.containsKey(x to y)) return memory[x to y]!!
    var sum = grid[y][x]
    sum += max(maxPathSum(grid, x + 1, y, memory), maxPathSum(grid, x, y + 1, memory))
    memory[x to y] = sum
    return sum
}

// [1] -> 1
// [2, 3] -> 3
// [1, 2, 3] -> non adjacent {me + index+2...lastIndex or index+1...lastIndex}
fun nonAdjacentSum(nums: List<Int>, index: Int = 0, memory: MutableMap<Int, Int> = mutableMapOf()): Int {
    if (nums.isEmpty() || index > nums.lastIndex) return 0
    if (memory.containsKey(index)) return memory[index]!!

    val max = max(
        nums[index] + nonAdjacentSum(nums, index + 2),
        nonAdjacentSum(nums, index + 1)
    )

    memory[index] = max
    return max
}

/**
 * Write a method, summingSquares, that takes a target number as an argument. The method should return the minimum number of perfect squares that sum to the target. A perfect square is a number of the form (i*i) where i >= 1.
 *
 * For example: 1, 4, 9, 16 are perfect squares, but 8 is not perfect square.
 */
fun summingSquares(
    n: Int,
    memory: MutableMap<Int, Int> = mutableMapOf()
): Int {
    if (n < 0) return Int.MAX_VALUE
    if (n == 0) return 0
    if (memory.containsKey(n)) return memory[n]!!

    var minimum = Int.MAX_VALUE

    for (i in 1..sqrt(n.toFloat()).toInt()) {
        val square = i.times(i)
        val count = summingSquares(n - square, memory) + 1
        if (count < minimum) minimum = count
    }

    memory[n] = minimum
    return minimum
}

/**
 * Write a method, countingChange, that takes in an amount and an array of coins. The method should return the number of different ways it is possible to make change for the given amount using the coins.
 *
 * You may reuse a coin as many times as necessary.
 */
fun countingChange(
    amount: Int,
    coins: List<Int>,
    at: Int = 0,
    memory: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(),
): Int {
    if (amount == 0) return 1
    if (at > coins.lastIndex) return 0
    if (memory.containsKey(amount to at)) return memory[amount to at]!!

    var ways = 0
    val coinValue = coins[at]
    var i = 0
    var qty = 0
    while (qty <= amount) {
        ways += countingChange(amount - qty, coins, at.inc(), memory)
        i++
        qty = i * coinValue
    }
    memory[amount to at] = ways
    return ways
}
