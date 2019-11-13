package deques;

public class LinkedDeque<T> implements Deque<T> {
    private int size;
    private Node sentFront;
    private Node sentBack;

    public LinkedDeque() {
        sentFront = new Node((T) new Object());
        sentBack = new Node((T) new Object());
        size = 0;

        sentFront.next = sentBack;
        sentFront.prev = null;
        sentBack.prev = sentFront;
        sentBack.next = null;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    private class Node {
        private T value;
        private Node prev;
        private Node next;

        Node(T value) {
            this.value = value;
            //throw new UnsupportedOperationException("Not implemented yet.");
        }
    }

    public void addFirst(T item) {
        Node addItem = new Node(item);
        sentFront.next.prev = addItem;
        addItem.next = sentFront.next;
        addItem.prev = sentFront;
        sentFront.next = addItem;

        size += 1;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void addLast(T item) {
        Node addItem = new Node(item);
        sentBack.prev.next = addItem;
        addItem.prev = sentBack.prev;
        addItem.next = sentBack;
        sentBack.prev = addItem;

        size += 1;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node p = sentFront.next;
        sentFront.next = sentFront.next.next;
        sentFront.next.prev = sentFront;
        size -= 1;
        return p.value;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node p = sentBack.prev;
        sentBack.prev = sentBack.prev.prev;
        sentBack.prev.next = sentBack;
        size -= 1;
        return p.value;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public T get(int index) {
        if ((index > size) || (index < 0)) {
            return null;
        }
        int num = 0;
        Node p = sentFront.next;
        while (num < index) {
            p = p.next;
            num++;
        }
        return p.value;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int size() {
        return size;
    }
}
