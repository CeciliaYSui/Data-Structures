import java.util.*; 

class Union {
    int array_size, loop_times; 
    int[] parents; 
    String[] output; 

    // find the parent of the required index (1 to M)
    int find(int a){
        while (a != parents[a]){
            a = find(parents[a]); 
        }
        return a; 
    }

    // check whether a & b are connected
    boolean connected(int a, int b){
        return find(a) == find(b); 
    }

    // connect the two indices' parents
    void union (int a, int b){
        int p = find(a); 
        int q = find(b); 
        if (p != q){
            parents[p] = q; 
        }
    }
}

class UnionFind {
    public static void main (String[] args) {

        //-------------------------
        //initial input from Users 
        //-------------------------
        Union UF = new Union(); 
        Scanner sc = new Scanner (System.in); 
        UF.array_size = sc.nextInt();  // array size  labeled as 1 to M 
        UF.loop_times = sc.nextInt();  // loop times 

        //------------------------
        //initialization of array 
        //------------------------
        UF.parents = new int[UF.array_size + 1];  
        for (int i = 1; i <= UF.array_size; i++){
            UF.parents[i] = i; 
        }
         

        //------------
        // Union Find 
        //------------ 
        String op;
        int first, second; 
        UF.output = new String[UF.loop_times]; 
        for (int j = 0; j < UF.loop_times ; j++){
            op = sc.next(); 
            first = Integer.parseInt(sc.next()); 
            second = Integer.parseInt(sc.next()); 

            //--------------
            // Operations 
            //--------------
            if (op.equals("#")) {
                UF.union(first, second); 
                UF.output[j] = ""; 
            }
            else if (op.equals("?")) {
                if (UF.connected(first, second)){
                    UF.output[j] = "yes"; 
                }
                else {
                    UF.output[j] = "no"; 
                }
            }
            else {
                UF.output[j] = ""; 
                System.out.println("INVALID INPUT"); 
            }
        }

        sc.close(); 

        //--------
        // Output 
        //--------
        for (int n = 0; n < UF.loop_times; n++){
            if (UF.output[n] != "") {
                System.out.println(UF.output[n]); 
            }
        }

    }
}


