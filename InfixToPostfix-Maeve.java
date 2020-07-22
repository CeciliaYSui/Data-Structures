import java.util.Scanner;
import java.util.Queue; 
import java.util.LinkedList; 

public class InfixToPostfix{ 


    public static boolean isFunc(String function) {
        switch (function) {
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

    public static boolean isOp(String token) {
        boolean value;
       if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*") || token.equals("^")) {
           value = true; 
       }
        else { 
            value = false;
        }
        return value;
    }


    //  PRECEDENCE FUNCTION
    public static int precedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1; 
        }
        else if (token.equals("/") || token.equals("*")) {
            return 2;
        }
        else if (token.equals("^")) {
            return 3;
        }
        else { 
            return -1;
        }
    }

    // right or left association function
    public static boolean isLeft(String token){
        boolean value;
        if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
            value = true;
        }
        else {
            value = false;
        }
        return value; 
    }

    public static boolean isAlpha(String token){
        char[] characters = token.toCharArray();

        for(char c: characters){
            if(!Character.isLetter(c)){
                return false;
            }    
        }
        return true;
    }
    
    /*public static class Stack{ 
        public Node first;
        public int n;

        public Stack(){
            System.out.println("Initializing stack");
            n = 0;
            Node newNode = new Node();
            newNode.item = "";
            newNode.next = null;
            this.first = newNode;  
            System.out.println(first == newNode);
        }

        private class Node { 
            String item; 
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int size() { 
            return n;
        }

        public void push(String item) {
            Node oldfirst = first; 
            first = new Node(); 
            first.item = item; 
            first.next = oldfirst;
            n++;
        }

        public String pop(){ 
            String item = first.item;
            first = first.next; 
            n--;
            return item;
        }

        public String peek(){
            if (first.item == null || first == null) { 
                return "";
            }
            else { 
                return first.item;
            }
        }

        // public String peek(){
        //     String value = pop();
        //     push(value);
        //     return value;
        // }

    }*/

public static class Stack {
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
            return "";
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

    public static void Convert(String arr[]) {

        Stack stack = new Stack();
        Queue<String> queue = new LinkedList<>(); 


            // iterating through each character in the string
            for(int i = 0; i < arr.length; i++) {
                String token = arr[i];

                
                if(Character.isDigit(token.charAt(0)) || isAlpha(token)){
                    // print digit OR append to the end of a list
                    if(!isFunc(token)){
                        queue.add(token);
                    }
                }

                //if character being indexed is a function (i.e. sin, cos, tan, etc), push it to the end of the operator stack) 
                if (isFunc(token)){
                    // push it onto the operator stack
                    stack.push(token);
                }

                // if the character being indexed is +, -, /, *, 
                else if (isOp(token)) {
                    while(isFunc(stack.peek()) || (precedence(stack.peek()) > precedence(token)) || (precedence(stack.peek()) == precedence(token) && isLeft(stack.peek())) && (!stack.peek().equals("("))){
                        queue.add(stack.pop());
                    }
                    stack.push(token);
                    /*  while ((there is a function at the top of the operator stack)
                    or (there is an operator at the top of the operator stack with greater precedence)
                    or (the operator at the top of the operator stack has equal precedence and is left associative))
                    and (the operator at the top of the operator stack is not a left bracket):
                    pop operators from the operator stack onto the output queue.
                    push it onto the operator stack.
                    */
                }

                else if (token.equals("(")) {
                    // push it onto the operator stack 
                    stack.push(token);
                }

                else if (token.equals(")")){
                    /*while the operator at the top of the operator stack is not a left bracket:
                    pop the operator from the operator stack onto the output queue.
                    pop the left bracket from the stack.
                    */
                    while (!stack.peek().equals("(")) {
                        queue.add(stack.pop());
                    }
                    stack.pop();
                }

             }
             while (!stack.isEmpty()) { 
                 queue.add(stack.pop());
             }
        
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");  
        }
        
        }
        


    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);

        String input [] = sc.nextLine().split(" ");
        Convert(input); 

    }
}