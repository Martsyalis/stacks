/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] array;
    private int last = -1;

    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        validateItem(item);
        last++;
        if (size != 0 && array.length <= last) resizeArr(size * 2);
        array[last] = item;
        size++;
    }

    public Item dequeue() {
        validateQueueSize();
        int randomIndex = StdRandom.uniform(size);
        Item randomItem = array[randomIndex];
        array[randomIndex] = array[last];
        array[last] = null;
        last--;
        size--;
        if (size != 0 && array.length >= size * 4) resizeArr(array.length / 2);
        return randomItem;
    }

    private void resizeArr(int newSize) {
        Item[] resizedArray = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            resizedArray[i] = array[i];
        }
        array = resizedArray;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateQueueSize();
        int randomIndex = StdRandom.uniform(size);
        return array[randomIndex];
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(array);
    }

    private class LinkedIterator implements Iterator<Item> {
        private int current;
        private Item[] randomizedArray;

        public LinkedIterator(Item[] array) {
            current = 0;
            randomizedArray = (Item[]) new Object[size];
            System.arraycopy(array, 0, randomizedArray, 0, size);
            for (int i = 0; i < randomizedArray.length; i++) {
                int randomIndex = StdRandom.uniform(i + 1);
                Item swap = randomizedArray[randomIndex];
                randomizedArray[randomIndex] = randomizedArray[i];
                randomizedArray[i] = swap;
            }
        }

        public boolean hasNext() {
            return current < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = randomizedArray[current];
            current++;
            return item;
        }
    }

    private void validateItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
    }

    private void validateQueueSize() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Queue is empty");
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<>();
        for (int i = 0; i < 3; i++) test.enqueue(i);
        System.out.println(test.size());
        for (Integer a : test) StdOut.println(a);
        for (Integer a : test) StdOut.println(a);
        for (int i = 0; i < 3; i++) StdOut.println(test.dequeue());
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        test.enqueue(5);
        System.out.println(test.size());
        test.dequeue();
        System.out.println(test.size());
        test.enqueue(7);

    }
}
