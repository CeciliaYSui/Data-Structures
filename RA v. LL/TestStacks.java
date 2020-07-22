import java.util.Random;

// Resizing Array: isEmpty(), push(String), pop(), size(), peek()
class ResizingArrayStack<Item> {
    private String[] items = new String[2];
    private int n = 0;

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

// Linked List: isEmpty(), push(String), pop(), size(), peek()
class LinkedListStack<Item> {
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

// StdRandom
final class StdRandom {
    static Random random;
    static long seed;

    static {
        seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    StdRandom() {
    }

    public static int uniform(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("argument must be positive" + n);
        return random.nextInt(n);
    }

    public static int uniform(int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform(b - a);
    }

}

// Stopwatch
class Stopwatch {
    private final long start;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000000.0;
    }
}

public class TestStacks {

    // RA_push
    public static double RA_push(int N, ResizingArrayStack<String> stack) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        for (int j = 0; j < N; j++) {
            stack.push(Integer.toString(a[j]));
        }
        return timer.elapsedTime();
    }

    // RA_pushpop
    public static double RA_pushpop(int N, ResizingArrayStack<String> stack) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        for (int j = 0; j < N; j++) {
            stack.push(Integer.toString(a[j]));
        }
        for (int k = 0; k < N; k++) {
            stack.pop();
        }
        return timer.elapsedTime();
    }

    // LL_push
    public static double LL_push(int N, LinkedListStack<String> stack) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        for (int j = 0; j < N; j++) {
            stack.push(Integer.toString(a[j]));
        }
        return timer.elapsedTime();
    }

    // LL_pushpop
    public static double LL_pushpop(int N, LinkedListStack<String> stack) {
        int MAX = 20;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        for (int j = 0; j < N; j++) {
            stack.push(Integer.toString(a[j]));
        }
        for (int k = 0; k < N; k++) {
            stack.pop();
        }
        return timer.elapsedTime();
    }

    // Main Class
    public static void main(String[] args) {
        ResizingArrayStack<String> stack1 = new ResizingArrayStack<>();
        LinkedListStack<String> stack2 = new LinkedListStack<>();
        for (int N = 1; true; N += N) {
            // double time1 = RA_push(N, stack1);
            // double time2 = LL_push(N, stack2);
            double time1 = RA_pushpop(N, stack1);
            double time2 = LL_pushpop(N, stack2);
            System.out.printf("N = %7d  time1: %5.6f, time2: %5.6f \n", N, time1, time2);
        }
    }
}
