import java.util.*;
import java.io.*;
import java.lang.Math;

class Fence_Plan {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static int M;
    public static int minX;
    public static int maxX;
    public static int minY;
    public static int maxY;
    public static int smallestPerimeter = Integer.MAX_VALUE;
    public static Node [] nodes;

    public static void main(String[] args) throws IOException{
        // Initialize readers and writers
        in = new Scanner(new FileReader("fenceplan.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));

        // Get the length of the sides
        N = in.nextInt();
        M = in.nextInt();

        // Get all the nodes
        nodes = new Node[N + 1];
        for(int n = 1; n <= N; n ++) {
            nodes[n] = new Node(in.nextInt(), in.nextInt(), N);
        }

        // Connect these nodes
        for(int m = 0; m < M; m ++) {
            // Remember to index by 1
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            Node first = nodes[index1];
            Node second = nodes[index2];
            first.adjacents.add(index2);
            second.adjacents.add(index1);
        }

        // Start going through all the nodes, and call BFS on nodes that we
        // haven't visited yet
        for(int i = 1; i < nodes.length; i ++) {
            if(nodes[i].visited == false) {
                // Reset the parameters
                minX = nodes[i].x;
                maxX = nodes[i].x;
                minY = nodes[i].y;
                maxY = nodes[i].y;
                BFS(nodes[i]);

                // Compute the perimeter, and check if this is the smallest perimeter
                smallestPerimeter = (int) Math.min(smallestPerimeter, ((maxX - minX) + (maxY - minY)) * 2);
            }
        }

        // Print out the solution
        out.println(smallestPerimeter);

        // Close streams
        in.close();
        out.close();
    }

    public static void BFS(Node current) {
        // Make sure we don't go through this node if we've already visited it
        if(!current.visited) {
            // First, mark the node as visited
            current.visited = true;

            // Update the parameters of our perimeter
            minX = (int) Math.min(current.x, minX);
            maxX = (int) Math.max(current.x, maxX);
            minY = (int) Math.min(current.y, minY);
            maxY = (int) Math.max(current.y, maxY);

            // Traverse all the neighbors
            for(int i = 0; i < current.adjacents.size(); i ++) {
                if(nodes[current.adjacents.get(i)] != null) {
                    BFS(nodes[current.adjacents.get(i)]);
                }
                else {
                    break;
                }
            }
        }
    }
}

class Node {
    public int x;
    public int y;
    public ArrayList<Integer> adjacents;
    public int index;   // Keeps track of the next empty index in adjacents
    public Boolean visited;

    public Node(int x, int y, int N) {
        this.x = x;
        this.y = y;
        this.adjacents = new ArrayList<Integer>();
        visited = false;
    }
}