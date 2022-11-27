package edu.touro.cs.mcon364;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DLinkedList extends AbstractSequentialList<String> {

    private final Node head = new Node(), tail = new Node(head);
    private int size;

    private static class Node {
        private String data;
        private Node prev, next;

        private Node(String s) {
            data = s;
        }

        private Node() {
        }

        private Node(Node p) {
            prev = p;
        }
    }

    private Node findNode(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
        }

        Node curr;

        if (index < size / 2) {
            curr = head;
            for (int i = -1; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = size; i > index; i--) {
                curr = curr.prev;
            }
        }

        return curr;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return new MyLIterator(index);
    }

    protected class MyLIterator implements ListIterator<String> {

        private Node pointer, lastReturned = null;
        private int beforeIndex;

        public MyLIterator(int index) {
            pointer = findNode(index);
            beforeIndex = index;
        }

        @Override
        public boolean hasNext() {
            return beforeIndex != size;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = pointer;
            pointer = pointer.next;
            beforeIndex++;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return beforeIndex > 0;
        }

        @Override
        public String previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            lastReturned = pointer = pointer.prev;
            beforeIndex--;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return beforeIndex;
        }

        @Override
        public int previousIndex() {
            return beforeIndex - 1;
        }

        private void checkReturnedState() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public void remove() {
            checkReturnedState();

            Node twiceAgoReturned = lastReturned.next;

            // Remove Node
            lastReturned.prev.next = lastReturned.next;
            lastReturned.next.prev = lastReturned.prev;

            // Ensure Iterator stays correct
            if (lastReturned == pointer) { // Which means that we last called `previous()`.
                // Move the pointer to its previous valid location which now has the same index.
                pointer = twiceAgoReturned;
            } else {
                // Shift the indices down one.
                // If we removed a later Node, we don't need to shift because the indices aren't set.
                beforeIndex--;
            }

            lastReturned = null;
            size--;
        }

        @Override
        public void set(String s) {
            checkReturnedState();

            lastReturned.data = s;
        }

        @Override
        public void add(String s) {
            Node newNode = new Node(s);
            newNode.next = pointer;

            pointer.prev.next = newNode;
            newNode.prev = pointer.prev;
            pointer.prev = newNode;

            size++;
            beforeIndex++;
            lastReturned = null;
        }
    }

    @Override
    public int size() {
        return size;
    }
}