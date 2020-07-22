// import java.util.*; 
import java.lang.Exception;
import java.lang.Integer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Integer; 

class Graph{
    // 
    int numVertices = 0; 
    int numEdges = 0; 
    ArrayList<Integer> adjList = new ArrayList<Integer>(); 
    HashMap<Integer, ArrayList<Integer>> adjListsMap = new HashMap<Integer, ArrayList<Integer>>(); 

    // constuctor 
    public Graph(){
        int key;
        int v;  
        adjList = new ArrayList<Integer>(); 
        Scanner sc = new Scanner(System.in); 
        v = Integer.parseInt(sc.nextLine());  
        // scan in the number of vertices, i.e. number of loop times e.g. 6
        for (int i = 0; i < v; i++){
            String line = sc.nextLine(); 
            String[] input = line.split(" "); // eg. "0:", "1", "2"
            int[] numbers = new int[input.length - 1]; 
            for (int j = 1; j < input.length; j++){
                numbers[j] = Integer.parseInt(input[j]); 
            }
            

        }


    }



    // Set lists 
    public void setList(int length){
        int key;
        testlist = new ArrayList<Integer>(); // add this and it will ensure that it's always a differen list
        Scanner xyz = new Scanner(System.in);
        for(int i=0;i<length;i++){
            testlist.add(xyz.nextInt());
        }
        System.out.println("Enter the key");
        key=xyz.nextInt();
        setMap(testlist, key); // it's always the same list if you don't create a new one
    }

    // set the whole adjListsMap
    public void setMap(ArrayList<Integer> adjList, int key){
        testmap.put(key, adjList);
        System.out.println(adjListsMap);
    }



    public int getNumVertices(){
        return numVertices; 
    }

    public int getNumEdges(){
        return numEdges; 
    }

    public void setNumVertices(int v){
        numVertices = v; 
    }

    public void setVertices(int v){
        for (int i = 0; i < v; i++){
            addVertex();
        }
    }

    public void setNumEdges(int e){
        numEdges = e; 
    }

    public void addVertex() {
        int v = getNumVertices(); 
        ArrayList<Integer> neighbors = new ArrayList<Integer>(); 
        adjListsMap.put(v, neighbors); 
        setNumVertices(v+1); 
    }

    public void removeVertex(){
        // remove vertex at the end 
        int numV = getNumVertices(); 
        if (numV == 0){
            System.out.println("Vertex Out of Bounds");  
        }
        else {
            adjListsMap.remove(numV); 
            setNumVertices(numV - 1);
        }
    }
    // 

    public void addEdge(int v, int w){
        // 
        int numV = getNumVertices(); 
        if (numV <= v || numV <= w){
            System.out.println("Vertex Out of Bounds"); 
        }
        else {
            (adjListsMap.get(v)).add(w); 
            setNumEdges(getNumEdges()+1);
        }
    }



    public int getInDegree(int v) {
        int numV = getNumVertices();
        if (numV <= v) {
            System.out.println("Vertex Out of Bounds"); 
        }
          int count = 0;
          for (int i = 0; i < getNumVertices(); i++) {
              if ( (adjListsMap.get(i)).contains(v) ) {
                  count++;
              }
          }
          return count;
    }


    public List<Integer> getNeighbors(int v) {
        int numV = getNumVertices();
        if (numV <= v) {
            System.out.println("Vertex Out of Bounds"); 
            List<Integer> neighbors = new ArrayList<Integer>();
            return neighbors; 
        } 
        else {       
          List<Integer> neighbors = new ArrayList<Integer>();
          for (Integer x : adjListsMap.get(v)) {
            neighbors.add(x);
          }      
          return neighbors;
        }
    }


    public List<Integer> getNeighborsTwoApart(int v) {
        int numV = getNumVertices();
        if (numV <= v){
            System.out.println("Vertex Out of Bounds"); 
            ArrayList<Integer> twoApart = new ArrayList<Integer>();
            return twoApart; 
        }
        else 
        {
            //
            List<Integer> oneApart = getNeighbors(v);
            ArrayList<Integer> twoApart = new ArrayList<Integer>();
            // For each integer within one hop of v...
            for (int i = 0; i < oneApart.size(); i++) {
                for (Integer x : oneApart) {
                    twoApart.add(x);
                }
            }
            return twoApart; 
        }
    }
       
      /*
      public int getOutDegree(int v) {
        int numV = getNumVertices();
        if (numV <= v) {
            System.out.println("Vertex Out of Bounds");
            return 0;  
        }
        else {
            return (adjListsMap.get(v)).size();
        }
      }

    
    public List<Integer> getDegreeSeq() {
        List<Integer> degreeSeq = new ArrayList<Integer>();
        int degrees = 0;
        for (int i = 0; i < getNumVertices(); i++) {
            degrees = getInDegree(i) + getOutDegree(i);
            degreeSeq.add(degrees);
        }
        Collections.sort(degreeSeq);
        Collections.reverse(degreeSeq);
        return degreeSeq;
    }

    
    public void removeEdge(int v, int w) {
        // 
        int numV = getNumVertices(); 
        if (numV <= v || numV <= w) {
            System.out.println("Vertex Out of Bounds"); 
        }
        else {
            (adjListsMap.get(v)).remove(w); 
            setNumEdges(getNumEdges() +1);
        }
    }

   

      public List<Integer> getNeighborsTwoApart(int v) throws VertexOutOfBoundsException {
        int numV = getNumVertices();
        if (numV <= v) throw new VertexOutOfBoundsException();
        
          List<Integer> oneApart = getNeighbors(v);
          ArrayList<Integer> twoApart = new ArrayList<Integer>();
          // For each integer within one hop of v...
          for (int i = 0; i < oneApart.size(); i++) {
              for (Integer x : oneApart) {
                  twoApart.add(x);
              }
          }
          return twoApart;
      }
      */ 
    //
}



//
public class Try3 {
    public static void main (String[] args){
        Graph G = new Graph(); 
        G.setNumEdges(0);
        G.setNumVertices(0);
        for (int i = 0; i < 6; i++){
            G.addVertex();
        }
        G.addEdge(0, 2);
        G.addEdge(0, 1);
        G.addEdge(1, 5);
        G.addEdge(2, 5);
        System.out.println(G.getNumEdges()); 
        System.out.println(G.getNumVertices());
        System.out.println(G.getNeighborsTwoApart(0)); 
    }

}
