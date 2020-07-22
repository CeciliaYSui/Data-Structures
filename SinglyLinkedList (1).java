public class SinglyLinkedList<Item> {
    class Node {
        public Item value;
        public Node next;
        public Node prev;
    }

    private Node head;
    private Node tail;

    public void insert(Item a) {
        Node tmp = new Node();
        tmp.value = a;
        tmp.next = null;
        if (head == null) {
            head = tmp;
            tail = tmp;
        } else {
            tmp.prev = tail;
            tail.next = tmp;
            tail = tail.next;
        }
    }

    public Item last() {
        return tail.value;
    }

    public Item secondLast() {
        return tail.prev.value;
    }

    public void remove(Item a) {
        Node prev = head;
        Node curr = head;
        Boolean found = false;
        // when only 1 Node in list
        if (head.next == null) {
            head.value = null;
        }
        // 1+ Nodes
        else {
            while (curr != null) {
                if (curr.value == a) {
                    found = true;
                    break;
                }
                prev = curr;
                curr = curr.next;
            }
            if (found) {
                prev.next = curr.next;
            }
        }
    }

    public Boolean find(Item a) {
        Node tmp = head;
        Boolean found = false;
        while (tmp != null) {
            if (tmp.value == a) {
                found = true;
                break;
            }
            tmp = tmp.next;
        }
        return found;
    }

    public void reverse() {
        Node curr = head;
        Node prev = curr.prev;
        Node nex = curr.next;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = nex;
            if (nex != null) {
                nex = curr.next;
            }
        }
        head = prev;
    }

    public void print() {
        Node curr = head;
        System.out.println("----------------------------");
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
        System.out.println("----------------------------");
    }

}
