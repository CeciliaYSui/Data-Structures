import java.lang.Exception;
import java.lang.Integer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

class DFSPath {
    boolean[] marked; 
    int s; // source 
    int w; 
    Stack<Integer> dfsStack = new Stack<Integer>(); 
    ArrayList<Integer> pathList = new ArrayList<>(); 
    ArrayList<Integer> visited = new ArrayList<>(); 

    public DFSPath(Graph G, int s, int w){
        marked = new boolean[G.V()]; 
        this.s = s; 
        this.w = w; 
        dfs(G,s,w); 
        //System.out.println("dfs Stack"); 
        //System.out.println(dfsStack); 
    }

    void dfs (Graph G, int v, int w){
        // marked[v] = true;
        dfsStack.push(v); 
        while (!dfsStack.empty()){
            v = dfsStack.pop(); 
            if (!marked[v]) {
                marked[v] = true; 
                if (v != w){
                    //System.out.println("path: " + v); 
                    pathList.add(v); 
                }
                else {
                    break; 
                }
                //System.out.println("adj List: "); 
                //System.out.println(G.adjListsMap.get(v)); 
                for(int x: G.adjListsMap.get(v)){
                    dfsStack.push(x); 
                    //System.out.println("x pushed & visited: " + x); 
                    visited.add(x); 
                }
            }
        } 
        pathList.add(w); 
    }

    int shortestPath() {
        return pathList.size() -1; 
    }

    boolean hasPathTo() {
        return (visited.contains(this.w)); 
    }
}


class BFSPath {
    boolean[] marked; 
    Queue<Integer> bfsQueue = new LinkedList<>(); 
    ArrayList<Integer> pathList = new ArrayList<>(); 
    ArrayList<Integer> visited = new ArrayList<>(); 
    int countCC;
    int v; 
    int w;

    public BFSPath(Graph G){
        marked = new boolean[G.V()]; 
        // System.out.println(marked.length); 
        for (int a = 0; a < marked.length; a++){
            marked[a] = false; 
        } 
        this.v = 0; 
        this.w = G.V(); 
        bfs(G);  
    }

    void bfs (Graph G){ 
        countCC = 0; 
        int k = notDone(); 
        while (k != 0){
            bfsQueue.add(v); 
                while (!bfsQueue.isEmpty()){
                    v = bfsQueue.remove(); 
                    if (!marked[v]) {
                        marked[v] = true; 
                        if (v != w){
                            pathList.add(v); 
                        }
                        else {
                            break; 
                        }
                        for(int x: G.adjListsMap.get(v)){
                            bfsQueue.add(x); 
                            visited.add(x); 
                        }
                    }
                } 
                for (int n = 0; n < marked.length; n++){
                    if (marked[n] == false){
                        v = n;
                        break;  
                    }
                }
            // System.out.println("new v : " + v); 
            k = notDone(); 
            // System.out.println("new k: " + k); 
            countCC++; 
        }
        pathList.add(w); 
    }

    int shortestPath() {
        return pathList.size() -1; 
    }

    boolean hasPathTo() {
        return (visited.contains(this.w)); 
    }

    int notDone(){
        int count = 0; 
        for (int i = 0; i < marked.length; i++){
            if (marked[i] == false){
                count++; 
            }
        }
        return count; 
    }

    int count(){
        return countCC; 
    }

    ArrayList<Integer> getCC() {
        return pathList; 
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
            DFSPath path = new DFSPath(this, v, w); 
            if (path.hasPathTo()) {
                return path.shortestPath(); 
            }
            else {
                return -1; 
            }
        }
    }

    // return no. of connected components
    public int countCC(){
        BFSPath check =  new BFSPath(this);
        return check.count();  
    } 

    public ArrayList<Integer> getCC(int v) {
        BFSPath connectedCom = new BFSPath(this);  
        return connectedCom.getCC(); 
    }

    public static void main (String[] args){
        Graph G = new Graph(); 
        //G.printGraph();
        //G.addEdge(0, 3);
        G.printGraph();
        //System.out.println(G.shortestDistance(1, 5));  
        //System.out.println(G.shortestDistance(4, 1));  
        //System.out.println(G.shortestDistance(1, 12));
        //System.out.println(G.shortestDistance(0, 100)); 
        //System.out.println(G.shortestDistance(3, 4));
        System.out.println(G.countCC());
        System.out.println(G.getCC(0)); 
    }


}


