package structy

fun fib(n: Int, memory: MutableMap<Int, Int> = mutableMapOf(0 to 0, 1 to 1)): Int {
    if (memory[n] != null) return memory[n]!!
    val nthFib = fib(n-1, memory) + fib(n-2, memory)
    memory[n] = nthFib
    return nthFib
}
