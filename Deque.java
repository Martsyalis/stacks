/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;

    private Node<Item> last;

    private int size = 0;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item item) {
        validateItem(item);
        Node<Item> newFirst = new Node<>();
        if (first != null) {
            first.prev = newFirst;
        }
        newFirst.item = item;
        newFirst.next = first;
        first = newFirst;
        size++;
        if (first.next == null) {
            last = first;
        }
    }

    public void addLast(Item item) {
        validateItem(item);
        Node<Item> newLast = new Node<>();
        newLast.item = item;
        if (last != null) {
            last.next = newLast;
        }
        newLast.prev = last;
        last = newLast;
        size++;
        if (last.prev == null) {
            first = last;
        }
    }

    public Item removeFirst() {
        validateStackSize();
        size--;
        Item oldFirst = first.item;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        }
        else {
            last = null;
            first = null;
        }
        return oldFirst;
    }

    public Item removeLast() {
        validateStackSize();
        size--;
        Item oldLast = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        }
        else {
            last = null;
            first = null;
        }
        return oldLast;

    }

    private void validateStackSize() {
        if (first == null && last == null) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new Deque<>();
        stack.addFirst(1);
        stack.addLast(5);
        stack.addLast(10);
        for (Integer a : stack) {
            StdOut.println(a);
        }
        stack.removeFirst();
        stack.removeFirst();
        StdOut.println(stack.size());
        stack.removeLast();
        StdOut.println(stack.isEmpty());
    }
}
