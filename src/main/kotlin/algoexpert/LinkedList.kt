package algoexpert

data class LinkedList(val value: Int) {
    var next: LinkedList? = null
}

fun middleNode(linkedList: LinkedList): LinkedList {
    // Write your code here.
    val size = linkedList.count()

    if (size == 1) return linkedList

    val middleIndex = size.div(2)
    var curIndex = 1

    var node = linkedList.next
    while (node != null) {
        if (curIndex == middleIndex) break
        else {
            node = node.next
            curIndex++
        }
    }
    return node!!
}

fun LinkedList.count(defaultSize: Int = 1): Int {
    var size = defaultSize
    var node: LinkedList? = next
    while (node != null) {
        node = node.next
        size++
    }
    return size
}