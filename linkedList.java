/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */


public class linkedList<Item> {

    // Node first;
    private Node<Item> latest;
    private int size;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public void push(Item item) {
        Node newLatest = new Node<Item>();
        newLatest.next = latest;
        newLatest.item = item;
        latest = newLatest;
        size++;
    }

    public Item pop() {
        Item item = latest.item;
        latest = latest.next;
        size--;
        return item;
    }

    public int size() {
        return size;
    }


    // Node first;
    // Node last;
    // int length;

    public static void main(String[] args) {
        // linkedList stack = new linkedList();
        // stack.push("1");
        // stack.push(5);
        // stack.push("hello candy");
        // int a = 5;
        // System.out.println(stack.pop());
    }
}
