import java.util.Collection;
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

    public void addFirst(E element) {
        Node first, prev;

        first = new Node(element);
        prev = head;

        head = first;
        head.next = prev;

    }

    public void add(int index, E element) {

        if (!(index <= size()))
            throw new IndexOutOfBoundsException("this index not exists");

        Node prev = null, newNode, temp = head, after = null;

        newNode = new Node(element);

        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size()) {
            addLast(element);
            return;
        }


        int counter = 0;
        while (temp != null) {

            if (counter + 1 == index) {
                prev = temp;
                after = temp.next;
                break;
            }
            counter++;
            temp = temp.next;
        }

        if (prev != null && after != null) {

            prev.next = newNode;
            newNode.next = after;
        }

    }

    public E get(int index) {

        if (!(index < size())) {
            throw new IndexOutOfBoundsException("this index not exists");
        }
        Node temp = head;
        int counter = 0;

        while (temp != null) {
            if (counter == index)
                return temp.data;

            temp = temp.next;
            counter++;
        }
        return null;
    }

    public void addLast(E element) {
        add(element);
    }

    public void set(int index, E element) {

        if (!(index < size())) {
            throw new IndexOutOfBoundsException("this index not exists");
        }

        int counter = 0;
        Node temp = head;

        while (temp != null) {

            if (counter == index)
                temp.data = element;

            counter++;
            temp = temp.next;
        }
    }

    public void addAll(Collection<? extends E> collection) {

        if (collection.isEmpty())
            return;

        for (E e : collection)
            add(e);
    }

    public int size() {

        int counter = 0;

        Node current = head;

        while (current != null) {

            counter++;
            current = current.next;

        }
        return counter;
    }

}
