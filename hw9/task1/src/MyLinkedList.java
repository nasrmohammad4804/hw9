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

}
