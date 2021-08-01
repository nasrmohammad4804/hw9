import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {

    private class Node {

        E data;
        Node next;

        Node(E element) {
            data = element;
            next = null;
        }

    }

    public void add(E element) {
        Node node = new Node(element);
//        node.next=null;

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    private Node head = null;
    private Node tail = null;


    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            Node first = head;

            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                E data = first.data;
                first = first.next;
                return data;
            }
        };
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void remove(E element) {

        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == element) {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of
        // the previous node as we need to change temp.next
        while (temp != null && temp.data != element) {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null)
            return;

        // Unlink the node from linked list
        prev.next = temp.next;


    }

}
