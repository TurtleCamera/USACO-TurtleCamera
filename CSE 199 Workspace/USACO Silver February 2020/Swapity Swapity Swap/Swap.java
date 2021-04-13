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
                // Now that I figured out this shortcut, it's pretty clear that I don't need the Node
                // class, oops.
                Node [] chainOfNodes = new Node [N + 1];
                chainOfNodes[0] = nodes[nodeIndex];
                Node next = nodes[swap[nodeIndex]];
                int chainIndex = 1;
                isComputed[nodeIndex] = true;

                // Keep expanding the chain of nodes until we reach the original "start" node
                while(chainOfNodes[0] != next) {
                    // Tell the program to not visit this node later
                    isComputed[next.index] = true;

                    // Get the next node and link it
                    chainOfNodes[chainIndex] = next;
                    next = nodes[swap[next.index]];

                    chainIndex ++;
                }

                // We don't need to traverse a cycle more than once, and we can achieve that by modding K by the chainIndex (which is the
                // chain length of the nodes). This works because we're pretty much just ignoring the times we went through the cycle
                // due to the fact that they're just repeats of a path we're taking. Additionally, because we stored the chain of nodes,
                // we can compute the solution directly for all the nodes that we just traversed. 
                int modK = K % chainIndex;
                for(int i = 0; i < chainIndex; i ++) {
                    // Remember that we're indexing by 1, so the final chainIndex value does store the index of the last node in the N + 1
                    // length array -- that is, chainOfNodes.
                    int finalChainIndex = (i + modK) % chainIndex;
                    chainOfNodes[i].finalLocation = chainOfNodes[finalChainIndex];
                }
            }
        }

        // Print out the solutions
        for(int nodeIndex = 1; nodeIndex < nodes.length; nodeIndex ++) {
            out.println(nodes[nodeIndex].finalLocation.index);
        }

        // Close streams
        in.close();
        out.close();
    }
}

class Node {
    public int index;   // Index in the "swap" array
    public Node finalLocation;  // We can jump to the solution of K iterations

    public Node(int index) {
        this.index = index;
    }
}