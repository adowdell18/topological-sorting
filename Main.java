import java.io.*;
import java.util.*;


//Create Digraph
class Digraph {//beginning class

    //private data members

    private int V; //number of Vertices
    private LinkedList<Integer> adj[]; //adjacency list
    private int E; //number of Edges
    //FIXME: add in vertex labels later
    //private String labels = ["BIOL_2010", "BIOL_2020", "BIOL_3830", "EXSC_4000", "EXSC_4230", "EXSC 4240", "EXSC_4260"];
    private static ArrayList<String> Lbls = new ArrayList();
    private int num;

    //constructor
    Digraph(int v){
        V = v;
        adj = new LinkedList[v];
        //created v number of empty linked lists (adjacency lists for each vertex)
        for(int i = 0; i< v; ++i){
            adj[i] = new LinkedList();
            System.out.println(adj[i]);

        }
        //System.out.println("adjacency list: " + adj);
        System.out.println("The digraph class is here :)");
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        System.out.println("reaching topological sort :)");
        System.out.println(adj[v]);
    }

    void topologicalSortUtil(int v, boolean visited[], Stack stack){
        //Mark the current node as visited.
        visited[v] = true;
        Integer i;

        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()){
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
        stack.push(new Integer(v));
    }

    void topologicalSort() {
        Stack stack = new Stack();
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
        while (stack.empty() == false)
            //num = (int) stack.pop();
           System.out.print(stack.pop() + " ");
           //System.out.println(Lbls.get((int) stack.pop()) + " ");

    }



    public static void main(String args[]){
        Scanner scnr =  new Scanner(System.in);
        String fileName = "project2-testA.tab.txt";

        //Lbls.add("A");
        //FIXME: Ask about- supposed to be private data member, would not work that way however
        //FIXME: created array list instead of array for ease of removal purposes
        //ArrayList Lbls = new ArrayList();
//        Lbls.add("BIOL_2010");
//        Lbls.add("BIOL_2020");
//        Lbls.add("EXSC_3830");
//        Lbls.add("EXSC_4000");
//        Lbls.add("EXSC_4230");
//        Lbls.add("EXSC_4240");
//        Lbls.add("EXSC_4010");
//        Lbls.add("EXSC_4260");

        //Parse in course names

        File file = new File(fileName);

        BufferedReader br = null;

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String mycurrLine;
            int lineNum = 0;

            while((mycurrLine = br.readLine()) != null){
                lineNum += 1;
                if (lineNum == 1) {
                    System.out.println(mycurrLine);
                    String[] splitLine = mycurrLine.split("       ");
                    String coursePair[] = splitLine[0].split("/t");
                    String courseA = coursePair[0].trim();
                    //String courseB = splitLine[1].trim();
                    Lbls.add(courseA);
                    //Lbls.add(courseB);
                    //System.out.println(courseA + " " + courseB);
                    System.out.println(courseA);
                    System.out.println(Lbls);
                }

            }
        }catch (IOException expt) {
        expt.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }






        Digraph dg = new Digraph(8);

        dg.addEdge(0,1);
        dg.addEdge(0,2);
        dg.addEdge(2,3);
        dg.addEdge(2,4);
        dg.addEdge(2,5);
        dg.addEdge(3,6);
        dg.addEdge(5,7);


        System.out.println("Following is a Topological " +
                            "sort of the given graph");

        dg.topologicalSort();


    }
} //ending class

