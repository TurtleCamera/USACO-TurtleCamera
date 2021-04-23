import java.io.*;
import java.util.*;

public class Distant {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static int A;
    public static int B;
    public static int worst = 0;
    public static Node [][] pastures;
    public static Boolean [][] visited;
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        in = new Scanner(new FileReader("distant.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("distant.out")));

        // Get input information
        N = in.nextInt();   // Sides of grid
        A = in.nextInt();   // Cost for same type
        B = in.nextInt();   // Cost for different type
        in.nextLine();

        // Make a list of nodes
        pastures = new Node[N][N];
        for(int row = 0; row < N; row ++) {
            String string = in.nextLine();
            for(int col = 0; col < N; col ++) {
                pastures[row][col] = new Node(row, col, Integer.MAX_VALUE, string.charAt(col));
            }
        }

        // Run Dijkstra's algorithm starting from each node
        for(int row = 0; row < N; row ++) {
            for(int col = 0; col < N; col ++) {
                dijkstras(row, col);
            }
        }

        // Print solution
        out.println(worst);
        
        // Close streams
        in.close();
        out.close();
    }

    // Dijkstra's algorithm
    public static void dijkstras(int startRow, int startCol) {
        // Reset the "graph"
        for(int row = 0; row < N; row ++) {
            for(int col = 0; col < N; col ++) {
                pastures[row][col].cost = Integer.MAX_VALUE;
            }
        }
        visited = new Boolean[N][N];

        // Create a priority queue and add the starting node
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pastures[startRow][startCol].cost = 0;
        pq.add(pastures[startRow][startCol]);

        while(!pq.isEmpty()) {
            // For debug purposes
            // printDebug();

            Node current = pq.poll();

            // Don't look at nodes that we've already visited (not including the first node)
            if(visited[current.row][current.col] != null) {
                continue;
            }
            visited[current.row][current.col] = true;

            // Is this the new worst distance?
            if(current.cost > worst) {
                worst = current.cost;
            }

            // Neighbors are just adjacent indices
            if(current.row - 1 >= 0) {
                // Left
                // Add cost depending on the type of pastures
                int newCost;
                if(pastures[current.row - 1][current.col].type == pastures[current.row][current.col].type) {
                    newCost = pastures[current.row][current.col].cost + A;
                }
                else {
                    newCost = pastures[current.row][current.col].cost + B;
                }
                pastures[current.row - 1][current.col].cost = (int) Math.min(newCost, pastures[current.row - 1][current.col].cost);

                pq.add(pastures[current.row - 1][current.col]);
            }

            if(current.row + 1 < N) {
                // Right
                // Add cost depending on the type of pastures
                int newCost;
                if(pastures[current.row + 1][current.col].type == pastures[current.row][current.col].type) {
                    newCost = pastures[current.row][current.col].cost + A;
                }
                else {
                    newCost = pastures[current.row][current.col].cost + B;
                }
                pastures[current.row + 1][current.col].cost = (int) Math.min(newCost, pastures[current.row + 1][current.col].cost);

                pq.add(pastures[current.row + 1][current.col]);
            }

            if(current.col - 1 >= 0) {
                // Down
                // Add cost depending on the type of pastures
                int newCost;
                if(pastures[current.row][current.col - 1].type == pastures[current.row][current.col].type) {
                    newCost = pastures[current.row][current.col].cost + A;
                }
                else {
                    newCost = pastures[current.row][current.col].cost + B;
                }
                pastures[current.row][current.col - 1].cost = (int) Math.min(newCost, pastures[current.row][current.col - 1].cost);

                pq.add(pastures[current.row][current.col - 1]);
            }

            if(current.col + 1 < N) {
                // Up
                // Add cost depending on the type of pastures
                int newCost;
                if(pastures[current.row][current.col+ 1].type == pastures[current.row][current.col].type) {
                    newCost = pastures[current.row][current.col].cost + A;
                }
                else {
                    newCost = pastures[current.row][current.col].cost + B;
                }
                pastures[current.row][current.col+ 1].cost = (int) Math.min(newCost, pastures[current.row][current.col+ 1].cost);

                pq.add(pastures[current.row][current.col+ 1]);
            }
        }
    }

    public static void printDebug() {
        for(int row = 0; row < N; row ++) {
            for(int col = 0; col < N; col ++) {
                System.out.print(pastures[row][col].cost + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// Represent each patch as a Node
class Node implements Comparable<Node> {
    public int row;
    public int col;
    public int cost;
    public char type;
  
    public Node(int row, int col, int cost, char type) {
        this.row = row;
        this.col = col;
        this.cost = cost;
        this.type = type;
    }
  
    @Override
    public int compareTo(Node other) {
        if (this.cost > other.cost) {
            return 1;
        }
        else if (this.cost < other.cost) {
            return -1;
        }

        return 0;
    }
}