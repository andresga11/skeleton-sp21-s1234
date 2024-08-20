package deque;

/** Deque list, linked list based. */
public class LinkedListDeque<T> {
    private Node sentinel = new Node(null, null, null);
    private int size;

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;

        }
    }

    public LinkedListDeque(T x) {
        Node current = new Node(x, sentinel, sentinel);
        sentinel.next = current;
        sentinel.prev = current;
        size += 1;
    }

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }


    public void addFirst(T item) {
        size += 1;
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

    }

    public void addLast(T item) {
        size += 1;
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        size -= 1;
        T  firstItem = sentinel.next.item;

        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;

        return firstItem;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }

        size -= 1;
        T  lastItem = sentinel.prev.item;

        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        return lastItem;
    }

    public T get(int index) {
        if (index < 0 || size <= index) {
            return null;
        }

        int steps = 0;
        Node p = sentinel.next;

        while (steps < index) {
            p = p.next;
            steps += 1;
        }

        return p.item;
    }

    private T getRecursiveHelper(Node p, int index) {

        if (index == 0) {
            return p.item;
        }

        return getRecursiveHelper(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || size <= index) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }
}
