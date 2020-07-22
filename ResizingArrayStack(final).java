public class ResizingArrayStack<Item> {
    private String[] items = new String[2];
    private int n = 0;

    /*
     * // empty stack initiation public Stack() { items = new String[2]; n = 0; }
     */

    public boolean isEmpty() {
        return n == 0;
    }

    // resize the underlying array holding the elements
    void resize(int capacity) {
        assert capacity >= n;
        String[] temp = new String[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // push a new string item on the stack
    public void push(String item) {
        if (n == items.length) {
            resize(2 * items.length);
        }
        items[n] = item;
        n++;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Empty Stack");
        }
        String item = items[n - 1];
        items[n - 1] = null;
        n--;
        if ((n > 0) && (n == (items.length / 4))) {
            resize(items.length / 2);
        }
        return item;
    }

    public int size() {
        return n;
    }

    // return the top item on the stack
    public String peek() {
        if (isEmpty()) {
            return "Empty Stack";
        } else {
            return items[n - 1];
        }
    }
}
/*
 * public static void main(String[] args) { // ResizingArrayStack<String> stack
 * = new ResizingArrayStack<>(); stack.push("Cecilia");
 * System.out.println(stack.peek()); // "Cecilia" stack.push("CC");
 * stack.push("12"); System.out.println(stack.pop());
 * System.out.println(stack.size()); }
 */
