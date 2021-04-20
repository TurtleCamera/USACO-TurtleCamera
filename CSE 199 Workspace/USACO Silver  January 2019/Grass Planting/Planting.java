import java.io.*;
import java.util.*;

public class Planting {
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));

        // Get input information
        int N = in.nextInt();   // Number of nodes

        // Make list of nodes that we'll use to count the number of adjacent edges
        int [] edges = new int [N];
        int maxEdges = 0;

        for(int n = 0; n < N - 1; n ++) {
            // Index by 0
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            edges[first] ++;
            edges[second] ++;

            // New greatest number of edges?
            if(edges[first] > maxEdges) {
                maxEdges = edges[first];
            }
            if(edges[second] > maxEdges) {
                maxEdges = edges[second];
            }
        }

        // Print solution
        out.println(maxEdges + 1);
        
        // Close streams
        in.close();
        out.close();
    }
}