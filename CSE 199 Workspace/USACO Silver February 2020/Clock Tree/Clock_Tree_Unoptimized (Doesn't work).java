import java.io.*;
import java.util.*;

public class Clock_Tree_Unoptimized {
    public static ClockNodeUnoptimized [] nodes;
    public static int countNodes;
    public static void main(String[] args) throws IOException {
        // NOTE: THIS DOESN'T WORK! USE MY OTHER SOLUTION.

        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("clocktree.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));

        // Get input information
        int N = in.nextInt();   // Number coordinates

        // Read in the clock handle times of each node
        nodes = new ClockNodeUnoptimized [N];
        for(int n = 0; n < N; n ++) {
            nodes[n] = new ClockNodeUnoptimized(n, in.nextInt());
        }

        // Create the edges of the graph
        for(int n = 0; n < N - 1; n ++) {
            // Remember: I'm using index by 0 for this problem
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;

            nodes[first].adjacents.add(second);
            nodes[second].adjacents.add(first);
        }

        // Run DFS on all nodes
        for(int index = 0; index < nodes.length; index ++) {
            nodes[index].tempTime = nodes[index].initialTime;

            if(dfs(nodes[index], -1, true) >= 12) {
                countNodes ++;
            }
        }

        // Print solution
        out.println(countNodes);
        
        // Close streams
        in.close();
        out.close();
    }

    // DFS algorithm
    public static int dfs(ClockNodeUnoptimized current, int parent, Boolean isRoot) {
        // If this is a leaf node, try to clear it by "going back and forth" between the
        // leaf node and its parent node
        if(current.adjacents.size() == 1 && !isRoot) {
            // Do the "going back and forth" thing
            int numIncrement = 12 - current.tempTime;
            current.tempTime = 12;
            nodes[parent].tempTime += numIncrement;

            // Don't mod if the tempTime is 12, only if it's greater than 12
            if(nodes[parent].tempTime > 12) {
                nodes[parent].tempTime %= 12;
            }
        }
        else {
            // Traverse each child node
            int size = current.adjacents.size();
            for(int i = 0; i < size; i ++) {
                // Don't revisit the parent node
                if(current.adjacents.get(i) != parent) {
                    // Reset tempTime
                    nodes[current.adjacents.get(i)].tempTime = nodes[current.adjacents.get(i)].initialTime; 

                    // Since we're traversing the node, we increment it by 1
                    nodes[current.adjacents.get(i)].tempTime += 1;

                    // Call DFS on it
                    dfs(nodes[current.adjacents.get(i)], current.index, false);

                    // Increment it by 1 again since we're going back to it
                    current.tempTime += 1;
                }
            }

            // Once we're done traversing all the children of this node, then this itself
            // becomes a leaf node, so do the same thing as we did in the base case.
            if(parent != -1) {
                int numIncrement = 12 - current.tempTime;
                current.tempTime = 12;
                nodes[parent].tempTime += numIncrement;

                // Don't mod if the tempTime is 12, only if it's greater than 12
                if(nodes[parent].tempTime > 12) {
                    nodes[parent].tempTime %= 12;
                }
            }
        }

        // Note that the only return statement that should matter is the one that returns for
        // the root node
        return current.tempTime;
    }
}

class ClockNodeUnoptimized {
    public int index;   // Index of this node
    public int initialTime;    // Time of this node
    public ArrayList<Integer> adjacents = new ArrayList<Integer>(); // Stores adjacent nodes
    public int tempTime;    // Used to help with the DFS algorithm; stores the adjusted time

    public ClockNodeUnoptimized(int index, int initialTime) {
        this.index = index;
        this.initialTime = initialTime;
    }
}