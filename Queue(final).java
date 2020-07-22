public class Queue<Item> {
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
            System.out.println("Empty Queue");
        }
        return q[first];
    }

    public void printQueue() {
        for (int i = 0; i < n; ++i) {
            System.out.print(q[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue newqueue = new Queue<String>();
        System.out.println(newqueue.isEmpty()); // true
        System.out.println(newqueue.size()); // 0
        newqueue.enqueue("Cecilia");
        newqueue.enqueue("loves");
        newqueue.enqueue("cc");
        newqueue.printQueue(); // Cecilia loves cc
        System.out.println(newqueue.peek()); // Cecilia
        System.out.println(newqueue.dequeue()); // delete Cecilia -- oldest item
        System.out.println(newqueue.isEmpty()); // false
        System.out.println(newqueue.size()); // 2

    }

}
