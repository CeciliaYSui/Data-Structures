public class LinkedListStack<Item> {
    int n; // size
    Node first; // top of stack

    class Node {
        Item item;
        Node next;
    }

    public void Stack() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow");
        }
        Item item = first.item; // save item to return
        first = first.next; // delete first node
        n--;
        return item; // return the saved item
    }

    public Item peek() {
        if (isEmpty()) {
            System.out.println("Stack underflow");
        }
        return first.item;
    }
}

/*
 * public static void main(String[] args) { // LinkedListStack<String> stack =
 * new LinkedListStack<>(); stack.push("12");
 * System.out.println(stack.size());// 1 stack.push("10"); for (int i = 0; i <
 * 8; i++) { stack.push(Integer.toString(i)); }
 * System.out.println(stack.size());// 10 System.out.println(stack.pop()); // 7
 * System.out.println(stack.peek()); // 6 }
 */
