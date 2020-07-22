public class SingleLinkedList<Item> {
    private Node head; 

    class Node {
        public Item value; 
        public Node next; 
        public Node prev; 
    }


    public void insert (Item a){
        Node tmp = new Node(); 
        tmp.value = a; 
        tmp.next = null; 

        if (head == null) {
            head = tmp; 
        }
        else {
            Node curr = head; 
            while (curr.next != null) {
                curr = curr.next; 
            }
            curr.next = tmp; 
        }
    }

    public void remove (Item a){
        Node prev = head; 
        Node curr = head; 
        Boolean found = false; 
        if (head.next == null) {
            head.value = null; 
        }
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


    public Item get(int a){
        Node tmp = head; 
        int count = 1; 
        Item found = null; 
        while (tmp != null) {
            if (count == a) {
                found = tmp.value; 
                break;
            }
            tmp = tmp.next; 
            count++; 
        }
        return found; 
    }

    public int getSize() {
        Node tmp = head; 
        int count = 1; 
        while (tmp.next != null) {
            tmp = tmp.next; 
            count++; 
        }
        return count; 
    }

    public Boolean find (Item a){
        Node tmp = head; 
        Boolean found = false; 
        while (tmp != null){
            if (tmp.value == a) {
                found = true; 
                break; 
            }
            tmp = tmp.next; 
        }
        return found; 
    }

    public Item last() {
        Node tmp = head; 
        Item value = null; 
        while (tmp != null) {
            if (tmp.next == null) {
                value = tmp.value; 
                break; 
            }
            tmp = tmp.next; 
        }
        return value; 
    }

    public Item secondLast() {
        Node tmp = head; 
        Item lastValue = null;
        if (tmp.next == null) {
            return lastValue; 
        }
        tmp = tmp.next; 
        while (tmp != null) {
            if (tmp.next == null) {
                break; 
            }
            lastValue = tmp.value; 
            tmp = tmp.next; 
        }
        return lastValue; 
    }

    public void printList(){
        Node curr = head; 
        System.out.println("----------------------------"); 
        while (curr != null) {
            System.out.println(curr.value); 
            curr = curr.next; 
        }
        System.out.println("----------------------------"); 
    }

    public void reverse(){
        SingleLinkedList<Item> TempList = new SingleLinkedList<Item>(); 
        Item value = null; 
        while (this.head.value != null){
            value = this.last(); 
            TempList.insert(value); 
            this.remove(value); 
        }
        int i = TempList.getSize(); 
        for (int j = 1; j <= i; ++j){
            value = TempList.get(j); 
            if (j == 1){
                head.value = value; 
            }
            else {
                this.insert(value); 
            }
        }
    }
}
