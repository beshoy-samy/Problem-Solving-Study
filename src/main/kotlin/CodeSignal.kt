fun firstDuplicate(a: MutableList<Int>): Int {

    val seen = hashSetOf<Int>()

    a.forEachIndexed { index, num ->
        if (seen.contains(a[index])) return a[index]
        else seen.add(num)
    }

    return -1
}

fun firstNotRepeatingCharacter(s: String): Char {
    val charsFreq = mutableMapOf<Char, Int>()

    s.forEach { char ->
        val freq = charsFreq.getOrDefault(char, 0)
        charsFreq[char] = freq.inc()
    }

    return charsFreq.entries.firstOrNull { it.value == 1 }?.key ?: '_'
}

fun rotateImage(a: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    //transposing the image making rows are columns
    val size = a.size
    for (i in 0 until size) {
        for (j in i until size) {
            val swap = a[i][j]
            a[i][j] = a[j][i]
            a[j][i] = swap

        }
    }

    //swapping columns
    for (i in 0 until size) {
        for (j in 0 until size.div(2)) {
            val swap = a[i][j]
            a[i][j] = a[i][size - j - 1]
            a[i][size - j - 1] = swap

        }
    }
    return a
}

fun sudoku2(grid: MutableList<MutableList<Char>>): Boolean {
    val seen = mutableSetOf<String>()

    for (i in 0 until 9) {
        for (j in 0 until 9) {
            val curChar = grid[i][j]
            if (curChar != '.') {
                val isAdded =
                    seen.add("$curChar found in row $i") && seen.add("$curChar found in column $j") &&
                            seen.add("$curChar found in box ${i.div(3)}-${j.div(3)}")
                if (isAdded.not()) return false
            }
        }
    }
    return true
}

fun isCryptSolution(crypt: MutableList<String>, solution: MutableList<MutableList<Char>>): Boolean {
    val charsMapping = mutableMapOf<Char, Char>()
    solution.forEach { charsMapping[it[0]] = it[1] }

    for (i in 0 until crypt.size) {
        charsMapping.forEach { (char, enc) ->
            crypt[i] = crypt[i].replace(char, enc)
        }
    }

    crypt.forEach {
        if (it.first() == '0' && it.length > 1) return false
    }

    return crypt[0].toLong() + crypt[1].toLong() == crypt[2].toLong()
}


fun <T> List<T>.toListNode(): ListNode<T> {
    require(this.isNotEmpty()) { "list can't be empty" }
    var node: ListNode<T>? = null
    var pointer: ListNode<T>? = null
    forEach {
        if (node == null) {
            node = ListNode(it)
            pointer = node
        } else {
            pointer?.next = ListNode(it)
            pointer = pointer?.next
        }
    }
    return node!!
}

fun isListPalindrome(l: ListNode<Int>?): Boolean {

    var listMiddleNode = l
    var cursor = l

    while (cursor?.next != null) {
        listMiddleNode = listMiddleNode?.next
        cursor = cursor.next?.next
    }

    var reversed = structy.reverse(listMiddleNode)
    reversed.print()
    cursor = l

    while (reversed != null) {

        if (cursor?.value != reversed.value) return false

        cursor = cursor.next
        reversed = reversed.next
    }

    return true
}

fun <T> ListNode<T>?.print() {
    var next = this
    while (next != null) {
        print(next.value)
        next = next.next
        if (next != null) print("->")
    }
    println()
}

fun <T> reverse(head: ListNode<T>?): ListNode<T>? {
    var prev: ListNode<T>? = null
    var start = head
    while (start != null) {
        val next = start.next
        start.next = prev
        prev = start
        start = next
    }
    return prev
}

class StackArray<T> {

    private val dynamicArray = ArrayDeque<T>()

    fun push(value: T) {
        dynamicArray.addFirst(value)
    }

    fun pop(): T? = dynamicArray.removeFirstOrNull()

    fun peek(): T? = dynamicArray.firstOrNull()

    fun isEmpty(): Boolean = dynamicArray.isEmpty()

    fun print() = println(dynamicArray.joinToString())

}

class StackLinkedList<T> {

    private var head: Node<T>? = null

    fun push(value: T) {
        val node = Node(value)
        node.next = head
        head = node
    }

    fun pop(): T? {
        val pop = head
        head = head?.next
        return pop?.value
    }

    fun peek(): T? = head?.value

    fun isEmpty(): Boolean = head == null

    fun print() {
        var pointer = head
        while (pointer != null) {
            print("${pointer.value} -> ")
            pointer = pointer.next
        }
        print("${pointer?.value}")
    }

    private class Node<T>(val value: T) {
        var next: Node<T>? = null
    }
}

class QueueArray<T>(vararg elements: T) {

    private val dynamicArray = ArrayDeque(elements.toList())

    fun enqueue(value: T) = dynamicArray.addLast(value)

    fun dequeue(): T? = dynamicArray.removeFirstOrNull()

    fun front(): T? = dynamicArray.firstOrNull()

    fun isEmpty(): Boolean = dynamicArray.isEmpty()

    fun isNotEmpty(): Boolean = dynamicArray.isNotEmpty()

    fun print() = println(dynamicArray.joinToString())

}

class QueueLinkedList<T> {

    private var front: Node<T>? = null
    private var tail: Node<T>? = null

    fun enqueue(value: T) {
        val node = Node(value)
        if (front == null) front = node
        tail?.next = node
        tail = node
    }

    fun dequeue(): T? {
        val inFront = front?.value
        front = front?.next
        if (front == null) tail = null
        return inFront
    }

    fun front(): T? = front?.value

    fun isEmpty(): Boolean = front == null

    fun print() {
        var pointer = front
        while (pointer != null) {
            print("${pointer.value} -> ")
            pointer = pointer.next
        }
        print("${pointer?.value}")
    }

    private class Node<T>(val value: T) {
        var next: Node<T>? = null
    }
}