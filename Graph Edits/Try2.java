import java.util.*; 

class Graph {
    // 
    int n; 
    List<Integer>[] adj; 
    AdjacencyList(int n0){
        // 
        n = n0; 
        adj = (List<Integer>[]) new List[n]; 
        for (int i = 0; i < n; i++){
            adj[i] = new ArrayStack<Integer>(Integer.class);
        }
    }

    void addEdge (int i, int j){
        adj[i].add(j); 
    }

    void removeEdge(int i, int j){
        Iterator<Integer> it = adj[i].iterator(); 
        while (it.hasNext()){
            // 
            if(it.next() == j){
                it.remove();
                return; 
            }
        }
    }

    // 
    boolean hasEdge(int i, int j){
        // 
        return adj[i].contains(j); 
    }

    List<Integer> outEdge (int i){
        // 
        return adj[i]; 
    }

    List<Integer> inEdges(int i){
        List<Integer> edges = new ArrayStack<Integer>(Integer.class); 
        for (int j = 0; j < n; j++){
            //
            if (adj[j].contains(i)) {
                edges.add(j); 
            }
        }
        return edges; 
    }
}

public class Try2 {
    public static void main(String[] args){
        Graph G = new Graph(); 
        G.AdjacencyList(5); 
        G.addEdge(0, 4);
        System.out.println(G.hasEdge(0, 4)); 
    }
}