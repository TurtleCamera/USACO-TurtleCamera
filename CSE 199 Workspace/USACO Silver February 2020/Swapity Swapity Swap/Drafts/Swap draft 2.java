import java.io.*;
import java.util.*;

public class Swap {
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("swap.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));

        // Get input information
        int N = in.nextInt();   // Number of cows
        int M = in.nextInt();   // Number of swaps in input file
        int K = in.nextInt();   // Number of times to repeat swaps

        // Initialize "swap" array.
        // It's too much of a hassle to think of this in terms of 0 indexing, so I'm
        // going to ignore the 0th index.
        int [] swap = new int [N + 1];
        for(int i = 0; i < swap.length; i ++) {
            // The input file indexes by 1, but I have a hard time programming it to
            // work that way
            swap[i] = i;
        }

        // Start swapping
        for(int m = 0; m < M; m ++) {
            // Note that because we only need to compute the swaps once, we don't need
            // to store any of the (L, R) pairs given in the input file. They're also
            // indexed by 1, so let's decrement them.
            int L = in.nextInt();
            int R = in.nextInt();

            // Start swapping the values in the indices from L to R
            int stoppingIndex = (R - L) / 2;
            for(int index = 0; index <= stoppingIndex; index ++) {
                int temp = swap[L + index];
                swap[L + index] = swap[R - index];
                swap[R - index] = temp;
            }
        }

        // From what I understand, "swap" contains the indices that each cow would land on after
        // completing all M exervise routines; however, we may run into loops before doing all K runs.
        // Furthermore, it looks like we can reduce the number of calculations even more by making a
        // graph to show where each cow goes, and stopping additional calculations if we reach an
        // index in swap that already exists in the graph.

        // Initialize all nodes (ignore index 0 again)
        Node [] nodes = new Node [N + 1];
        for(int i = 1; i < nodes.length; i ++) {
            nodes[i] = new Node(i);
        }

        // Don't revisit nodes that already exist in the graph, so we reduce the number of nodes we
        // have to calculate the "loops" for
        Boolean [] isComputed = new Boolean[N + 1];

        // Start expanding the graph
        for(int nodeIndex = 1; nodeIndex < nodes.length; nodeIndex ++) {
            // Ignore if this node already exists in the graph
            if(isComputed[nodeIndex] == null) {
                Node start = nodes[nodeIndex];
                Node next = nodes[swap[nodeIndex]];
                start.nextIndex = next;
                isComputed[nodeIndex] = true;
                int chainLength = 1;

                // Keep expanding the chain of nodes until we reach the original "start" node
                while(next != start) {
                    // Get the next node and link it
                    next.nextIndex = nodes[swap[next.index]];
                    next = next.nextIndex;

                    // Tell the program to not visit this node later
                    isComputed[next.index] = true;

                    chainLength ++;
                }

                // Later when we compute the solution, we don't want to loop through the graph more
                // than once, so we'll mod K by however long the chain of nodes is. We'll need to
                // go through all these nodes again to set the node loop count (chainLength), though.
                next = nodes[swap[nodeIndex]];
                start.chainLength = chainLength;
                while(next != start) {
                    next.chainLength = chainLength;
                    next = next.nextIndex;
                }
            }
        }

        // Print out the solutions
        for(int nodeIndex = 1; nodeIndex < nodes.length; nodeIndex ++) {
            Node solutionNode = nodes[nodeIndex];
            int modK = K % solutionNode.chainLength;

            // Find the final location of this cow
            for(int k = 0; k < modK; k ++) {
                solutionNode = solutionNode.nextIndex;
            }

            out.println(solutionNode.index);
        }

        // Close streams
        in.close();
        out.close();
    }
}

class Node {
    public int index;   // Index in the "swap" array
    public Node nextIndex;  // Each node should only have one edge pointing in and out of it
    public int chainLength; // The number of nodes we traverse before reaching itself again
                            // (this count includes itself)

    public Node(int index) {
        this.index = index;
    }
}