fun bubbleSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    return doABubbleSort(array, array.lastIndex)
}

fun doABubbleSort(array: MutableList<Int>, endIndex: Int): List<Int> {
    var didASwap = false
    for (i in 0 until endIndex) {
        if (array[i] > array[i + 1]) {
            val swap = array[i + 1]
            array[i + 1] = array[i]
            array[i] = swap
            didASwap = true
        }
    }
    return if (didASwap) doABubbleSort(array, endIndex.minus(1)) else array
}

fun insertionSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    for (i in 1..array.lastIndex) {
        if (array[i] < array[i - 1]) insert(array, i)
    }
    return array
}

private fun insert(array: MutableList<Int>, start: Int) {
    for (i in start downTo 1) {
        if (array[i] < array[i - 1]) {
            val swap = array[i - 1]
            array[i - 1] = array[i]
            array[i] = swap
        }
    }
}

fun selectionSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    var unsortedListStartIndex = 0
    while (unsortedListStartIndex != array.lastIndex) {
        var smallestNumIndex = unsortedListStartIndex
        for (i in unsortedListStartIndex.plus(1)..array.lastIndex) {
            if (array[i] < array[smallestNumIndex]) smallestNumIndex = i
        }
        val swap = array[unsortedListStartIndex]
        array[unsortedListStartIndex] = array[smallestNumIndex]
        array[smallestNumIndex] = swap
        unsortedListStartIndex += 1
    }
    return array
}

fun mergeSort(array: List<Int>): List<Int> {
    if (array.size <= 1) return array

    val firstHalf = array.subList(0, array.size.div(2))
    val secondHalf = array.subList(array.size.div(2), array.size)

    return merge(mergeSort(firstHalf), mergeSort(secondHalf))
}

private fun merge(leftList: List<Int>, rightList: List<Int>): List<Int> {
    var leftIndex = 0
    var rightIndex = 0
    val mergedList = mutableListOf<Int>()

    while (leftIndex < leftList.size && rightIndex < rightList.size) {
        if (leftList[leftIndex] < rightList[rightIndex]) {
            mergedList.add(leftList[leftIndex])
            leftIndex++
        } else {
            mergedList.add(rightList[rightIndex])
            rightIndex++
        }
    }

    /*remaining items in either list means they are the bigger so we just need to add them adding remaining items */
    for (i in leftIndex until leftList.size)
        mergedList.add(leftList[i])

    for (i in rightIndex until rightList.size)
        mergedList.add(rightList[i])
    return mergedList
}