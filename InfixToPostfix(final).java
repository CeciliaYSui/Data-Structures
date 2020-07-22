import java.util.Scanner;

// Stack
class Stack<Item> {
    private String[] items;
    private int n;

    // empty stack initiation
    public Stack() {
        items = new String[2];
        n = 0;
    }

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

// Queue
class Queue<Item> {
    private String[] q;
    private int n;
    private int first;
    private int last;

    // empty queue initialization
    public Queue() {
        q = new String[2];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        String[] temp = new String[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = n;
    }

    // add item to end
    public void enqueue(String item) {
        if (n == q.length) {
            resize(2 * q.length);
        }
        q[last++] = item;
        if (last == q.length) {
            last = 0;
        }
        n++;
    }

    // delete item (oldest one)
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Empty Queue");
        }
        String item = q[first];
        q[first] = null;
        n--;
        first++;
        if (first == q.length) {
            first = 0;
        }
        if (n > 0 && n == (q.length / 4)) {
            resize(q.length / 2);
        }
        return item;
    }

    // return oldest item added in queue
    public String peek() {
        if (isEmpty()) {
            return "Empty Queue";
        }
        return q[first];
    }

    public void printQueue() {
        for (int i = 0; i < n; ++i) {
            System.out.print(q[i] + " ");
        }
        System.out.println();
    }

}

// Infix To Postfix & MAIN
public class InfixToPostfix {
    static int Order(String c) {
        switch (c) {
        case "+":
        case "-":
            return 2;
        case "*":
        case "/":
            return 3;
        case "^":
            return 4; // right associative
        }
        return 1;
    }

    static boolean isOp(String c) {
        if (Order(c) != 1) {
            return true;
        }
        return false;
    }

    static boolean isFunc(String c) {
        switch (c) {
        case "tan":
        case "sin":
        case "cos":
        case "exp":
        case "min":
        case "max":
        case "sqrt":
        case "tanh":
            return true;
        }
        return false;
    }

    static boolean isVariable(String c) {
        char[] cc = c.toCharArray();
        for (char r : cc) {
            if (!Character.isLetter(r)) {
                return false;
            }
        }
        return true;
    }

    static boolean isLeft(String c) {
        switch (c) {
        case "+":
        case "-":
        case "*":
        case "/":
            return true;
        }
        return false;
    }

    static void infix2postfix(String str) {
        Stack stack = new Stack<String>();
        Queue queue = new Queue<String>();

        String[] arr = str.split("\\s");

        for (int i = 0; i < arr.length; ++i) {
            String c = arr[i];
            // token is a number / id
            if (Character.isDigit(c.charAt(0)) || isVariable(c)) {
                // push it to output queue
                if (!isFunc(c)) {
                    queue.enqueue(c);
                }
            }

            // token is a function
            if (isFunc(c)) {
                stack.push(c);
            }

            // token is an operator
            else if (isOp(c)) {
                // if cons satisfied, pop from stack to queue
                while (isFunc(stack.peek()) || Order(stack.peek()) > Order(c)
                        || (Order(stack.peek()) == Order(c) && isLeft(stack.peek())) && (!stack.peek().equals("("))) {
                    queue.enqueue(stack.pop());
                }
                // push to the stack
                stack.push(c);
            }

            else if (c.equals("(")) {
                stack.push(c);
            }

            else if (c.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    queue.enqueue(stack.pop());
                }
                stack.pop();
            }

        }

        // FOR loop ends here!!!

        // no more tokens to read from the str
        // still tokens on stack
        while (!stack.isEmpty()) {
            // pop operator from stack to queue
            queue.enqueue(stack.pop());
        }

        // print output
        queue.printQueue();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String test = sc.nextLine();
        infix2postfix(test);
        sc.close();
    }

}