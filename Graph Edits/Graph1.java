import java.lang.Exception;
import java.lang.Integer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

class DFSPaths {
    boolean[] marked; 
    int[] edgeTo; 
    int s; // source 
    Stack<Integer> dfsStack = new Stack<Integer>(); 

    public DFSPaths(Graph G, int s){
        marked = new boolean[G.V()]; 
        // edgeTo = new int [G.V()]; 
        this.s = s; 
        dfs(G,s); 
        System.out.println("Love Cecilia"); 
        System.out.println(dfsStack); 
    }

    void dfs (Graph G, int v){
        // marked[v] = true;
        dfsStack.push(v); 
        while (!dfsStack.empty()){
            v = dfsStack.pop(); 
            System.out.println("printout:v " + v); 
            if (!marked[v]) {
                marked[v] = true; 
                // System.out.println(marked[v]); 
                System.out.println("HAHA: "); 
                System.out.println(G.adjListsMap.get(v)); 
                for(int x: G.adjListsMap.get(v)){
                    dfsStack.push(x); 
                    System.out.println("x pushed: " + x); 
                }
            }
        }


        for (int w : G.adjListsMap.get(v)) {
            if (!marked[w]){
                edgeTo[w] = v; 
                dfs(G,w); 
            }
        } 
    }

    boolean hasPathTo(int v){
        return marked[v]; 
    }

    public Stack<Integer> pathTo(int v){
        if (!hasPathTo(v)) {
            return null; 
        }
        else {
            Stack<Integer> path = new Stack<Integer>(); 
            /* 
            for (int x = v; x != s; x {
                System.out.println(edgeTo[x]); 
                if (x == v){
                }
                else {
                    path.push(x); 
                    System.out.println(x); 
                }
            }
            // path.push(s); 
            */ 
            return path; 

        }
    }
}


public class Graph{
    // global variables 
    int numVertices = 0; 
    int numEdges = 0; 
    ArrayList<Integer> adjList = new ArrayList<Integer>(); 
    HashMap<Integer, ArrayList<Integer>> adjListsMap = new HashMap<Integer, ArrayList<Integer>>(); 

    // constuctor 
    public Graph(){
        int key;
        int v;   
        Scanner sc = new Scanner(System.in); 
        v = Integer.parseInt(sc.nextLine());  
        numVertices = v; 
        // scan in the number of vertices, i.e. number of loop times e.g. 6
        for (int i = 0; i < v; i++){
            adjList = new ArrayList<Integer>();
            String line = sc.nextLine(); 
            String[] input = line.split(" "); // eg. "11:", "9", "12"
            int endIn = input[0].length(); 
            String k = input[0].substring(0, endIn-1); 
            key = Integer.parseInt(k); 
            // key used for the HashMap
            for (int j = 1; j < input.length; j++){
                adjList.add(Integer.parseInt(input[j])); 
            }
            adjListsMap.put(key,adjList); 
            if (i > 0){
                for (int tmp = 0; tmp < adjListsMap.size(); tmp++){
                    if ((adjListsMap.get(tmp).contains(key)) && (!(adjListsMap.get(key).contains(tmp)))){
                        addEdge(key, tmp);
                    }
                }
            }
        }
        sc.close();
    }

    public void printGraph(){
        System.out.println(adjListsMap); 
    }

    public void addEdge(int v, int w){
        int numV = this.numVertices;  // v set for this instance 
        if (numV <= v || numV <= w) {
            System.out.println("Vertex Out of Bound"); 
        }
        else {
            (adjListsMap.get(v)).add(w);
            (adjListsMap.get(w)).add(v);  
        }
    }


    public int V(){
        return numVertices; 
    }

    public int E() {
        return numEdges; 
    }


    // int type is required, so only one distance away 
    public int shortestDistance (int v, int w){
        //
        if ((adjListsMap.get(v) == null) || (adjListsMap.get(w) == null)){
            return -1; 
        }
        else if (w == v){
            return v; 
        }
        else if (v > w){
            return shortestDistance(w, v); 
        }
        else if ((adjListsMap.get(v).contains(w)) || (adjListsMap.get(w).contains(v))){
            return 1; 
        }

        else {
        DFSPaths search = new DFSPaths(this, v); 
        Stack<Integer> tmp = new Stack<Integer>(); 
        tmp = search.pathTo(w); 
        tmp.pop(); 
        System.out.println(tmp); 
        if (tmp.empty()){
            return -1; 
        }
        else {
            int count = 1; 
            while(!tmp.empty()){
                int t = tmp.peek();
                if (adjListsMap.get(t).contains(w)){
                    count++; 
                    //tmp.pop(); 
                    break; 
                }
                tmp.pop(); 
            }
            return count; 
        }
        }
    }

    // return no. of connected components 
    public void countCC(){
        DFSPaths check =  new DFSPaths(this, 0); 

    }


    public static void main (String[] args){
        Graph G = new Graph(); 
        G.printGraph();
        //G.addEdge(0, 3);
        //G.printGraph();
        //System.out.println(G.shortestDistance(1, 5));  
        System.out.println(G.shortestDistance(4, 1));  
        //System.out.println(G.shortestDistance(1, 12));
        // System.out.println(G.shortestDistance(0, 100)); 
        //System.out.println(G.shortestDistance(3, 4));
        //System.out.println(G.shortestDistance(1, 2));
    }


}


