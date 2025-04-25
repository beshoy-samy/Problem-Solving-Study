data class ListNode<T>(var value: T, var next: ListNode<T>? = null)

fun <T> ListNode<T>?.value(default: T) = this?.value ?: default

data class Node<T>(var value: T, var left: Node<T>? = null, var right: Node<T>? = null)

class QueueArray<T>(vararg elements: T) {

    private val dynamicArray = ArrayDeque(elements.toList())

    fun enqueue(value: T) = dynamicArray.addLast(value)

    fun dequeue(): T? = dynamicArray.removeFirstOrNull()

    fun front(): T? = dynamicArray.firstOrNull()

    fun isEmpty(): Boolean = dynamicArray.isEmpty()

    fun isNotEmpty(): Boolean = dynamicArray.isNotEmpty()

    fun print() = println(dynamicArray.joinToString())

    override fun toString(): String = dynamicArray.joinToString()

}

class StackArray<T> {

    private val dynamicArray = ArrayDeque<T>()

    fun push(value: T) {
        dynamicArray.addFirst(value)
    }

    fun pop(): T? = dynamicArray.removeFirstOrNull()

    fun peek(): T? = dynamicArray.firstOrNull()

    fun isEmpty(): Boolean = dynamicArray.isEmpty()

    override fun toString(): String = dynamicArray.joinToString()

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