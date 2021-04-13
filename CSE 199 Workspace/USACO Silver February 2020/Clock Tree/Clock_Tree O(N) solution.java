import java.io.*;
import java.util.*;

public class Clock_Tree {
    public static ClockNode [] nodes;
    public static int countNodes;
    public static void main(String[] args) throws IOException {
        // One thing to note about this question is that N is at most 2,500. USACO usually wants
        // some of the most optimized solutions..., but this is silver we're talking about. Surely
	// they're fine with an O(N^2) solution because N is relatively small, right? I did think
	// of a O(N) solution, though, so I'll code that up.

        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("clocktree.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));

        // Get input information
        int N = in.nextInt();   // Number coordinates

        // Read in the clock handle times of each node
        nodes = new ClockNode [N];
        for(int n = 0; n < N; n ++) {
            nodes[n] = new ClockNode(n, in.nextInt());
        }

        // Create the edges of the graph
        for(int n = 0; n < N - 1; n ++) {
            // Remember: I'm using index by 0 for this problem
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;

            nodes[first].adjacents.add(second);
            nodes[second].adjacents.add(first);
        }

        // Run DFS on one of the nodes and one of the adjacent nodes. Use countNodes to
        // preemptively count how many possible solutions there could be.
        int index = 0;
        countNodes = 0;
        nodes[index].tempTime = nodes[index].initialTime;
        Boolean firstNodeSolvable = dfs(nodes[index], -1, true, true) >= 12;
        int firstNodeCount = countNodes;

        // Run DFS on one of the adjacent nodes
        index = nodes[index].adjacents.get(0);
        countNodes = 0;
        nodes[index].tempTime = nodes[index].initialTime;
        Boolean secondNodeSolvable = dfs(nodes[index], -1, true, true) >= 12;
        int secondNodeCount = countNodes;

        // Debug
        // for(int i = 0; i < nodes.length; i ++) {
        //     System.out.println(nodes[i].tempTime + " " + (i + 1));
        // }

        // Rule: if a node is solvable, then all nodes of even distance from it are also solvable
        if(firstNodeSolvable && secondNodeSolvable) {
            // This means all nodes are solvable
            out.println(nodes.length);
        }
        else if(firstNodeSolvable){
            // Only nodes of even distance from the first node are solvable
            out.println(firstNodeCount);
        }
        else if(secondNodeSolvable){
            // Only nodes of even distance from the second node are solvable
            out.println(secondNodeCount);
        }
        else {
            // No nodes are solvable
            out.println(0);
        }

        // Close streams
        in.close();
        out.close();
    }

    // DFS algorithm
    public static int dfs(ClockNode current, int parent, Boolean isRoot, Boolean countNode) {
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
                    dfs(nodes[current.adjacents.get(i)], current.index, false, !countNode);

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

        // Count this node if it could be a potential solvable node (based on the "even" rule)
        if(countNode) {
            countNodes ++;
        }

        // Note that the only return statement that should matter is the one that returns for
        // the root node
        return current.tempTime;
    }
}

class ClockNode {
    public int index;   // Index of this node
    public int initialTime;    // Time of this node
    public ArrayList<Integer> adjacents = new ArrayList<Integer>(); // Stores adjacent nodes
    public int tempTime;    // Used to help with the DFS algorithm; stores the adjusted time

    public ClockNode(int index, int initialTime) {
        this.index = index;
        this.initialTime = initialTime;
    }
}