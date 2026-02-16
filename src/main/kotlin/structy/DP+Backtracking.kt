package structy

/**
 * Write a method, subsets, that takes in a list as an argument. The method should return a 2D list where each subarray represents one of the possible subsets of the list.
 *
 * The elements within the subsets and the subsets themselves may be returned in any order.
 *
 * You may assume that the input list contains unique elements.
 */
fun subsets(
    elements: List<String>,
    memory: MutableMap<List<String>, MutableList<List<String>>> = mutableMapOf()
): List<List<String>> {
    if (elements.isEmpty()) return listOf(listOf())
    if (memory.containsKey(elements)) return memory[elements]!!

    val elementOne = elements.first()
    val subsets = subsets(elements.drop(1), memory)

    val result = mutableListOf<List<String>>()
    result.addAll(subsets)

    subsets.forEach {
        val subset = it.toMutableList()
        subset.add(elementOne)
        result.add(subset)
    }

    memory[elements] = result
    return result
}
