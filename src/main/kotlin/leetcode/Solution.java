package leetcode;

public class Solution {


    public static void main(String[] args) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.insertAtHead(1);
        doublyLinkedList.insertAtHead(2);
        doublyLinkedList.insertAtHead(3);
        doublyLinkedList.insertAtTail(0);
        doublyLinkedList.insertAtTail(-1);
        doublyLinkedList.delete(1);
        Node found = doublyLinkedList.find(-1);
        System.out.println(found.value);
        doublyLinkedList.print();
    }

}

class DoublyLinkedList {

    private Node head;
    private Node tail;

    void insertAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        if (head != null) head.prev = newNode;
        head = newNode;
        if (tail == null) tail = head;
    }

    void insertAtTail(int value) {
        if (tail == null) return;
        Node newNode = new Node(value);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    Node find(int value) {
        Node pointer = head;
        while (pointer != null) {
            if (pointer.value == value) return pointer;
            pointer = pointer.next;
        }
        return null;
    }

    Boolean delete(int value) {

        Node pointer = head;

        while (pointer != null) {
            if (pointer.value == value) {
                if (pointer.prev != null) pointer.prev.next = pointer.next;
                if (pointer.next != null) pointer.next.prev = pointer.prev;
                pointer.next = null;
                pointer.prev = null;
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    void print() {
        Node pointer = head;

        while (pointer != null) {
            if (pointer.prev != null) System.out.print("<-");
            System.out.print(pointer.value);
            if (pointer.next != null) System.out.print("->");
            pointer = pointer.next;
        }
    }

}

class Node {

    int value;

    Node next;
    Node prev;

    Node(int value) {
        this.value = value;
    }
}

