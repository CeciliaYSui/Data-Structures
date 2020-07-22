public class Main {
    public static void main (String[] args) {
        SinglyLinkedList <Integer> list = new SinglyLinkedList<Integer>(); 
        for (int i = 1; i < 11; i++){
            list.insert(i); 
        }
        list.remove(7); 
        list.print();
        list.reverse();
        list.print();
        System.out.println(list.last()); 
        System.out.println(list.secondLast()); 
        if (list.find(9)){
            System.out.println("Found 9."); 
        }

    }
}