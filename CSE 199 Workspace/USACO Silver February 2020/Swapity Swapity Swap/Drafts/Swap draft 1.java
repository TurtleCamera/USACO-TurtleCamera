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

        // Initialize "swap" array
        int [] swap = new int [N];
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
            int L = in.nextInt() - 1;
            int R = in.nextInt() - 1;

            // Start swapping the values in the indices from L to R
            int stoppingIndex = (R - L) / 2;
            for(int index = 0; index <= stoppingIndex; index ++) {
                int temp = swap[L + index];
                swap[L + index] = swap[R - index];
                swap[R - index] = temp;
            }
        }

        for(int i = 0; i < swap.length; i ++) {
            System.out.print(swap[i] + " ");
        }
        System.out.println();

        // // Initialize the solution array and copy over all the elements from
        // // swap (this counts as one iteration out of all K iterations).
        // int [] solution = new int [N];
        // for(int i = 0; i < solution.length; i ++) {
        //     solution[i] = swap[i];
        // }

        // We may not need to compute all K iterations of th M swaps. This is because we
        // may come across repeating indices. For example, if we keep swapping the cow at index
        // 0, then that cow may eventually go back to index 0, so we don't need to calculate
        // anymore.
        int [][] indices = new int [N][K + 1]; 
        for(int i = 0; i < indices.length; i ++) {
            indices[i][0] = i;
        }

        // Start computing the "shorter answer."
        for(int n = 0; n < indices.length; n ++) {
            // Since array "swap" contains the resulting locations of indices after doing
            // all M exercise swaps, we can just use those indices to compute our answer rather
            // than to brute force. K is the limit because we don't want this program to run
            // forever.
            int k = 0;
            System.out.println(swap[indices[n][k]]);
            System.out.println(n);
            System.out.println(indices[n][0]);
            System.out.println("Test");
            if(n == 3) {
                System.out.println(3);
            }
            while(k < K && (swap[indices[n][k]] != indices[n][0])) {
                indices[n][k + 1] = swap[indices[n][k]];

                // // Temporarily make a new array so we don't lose the values in the indices
                // // in the original solution array.
                // int [] updatedSolution = new int[N];
                
                // // Start moving the values one by one
                // for(int n = 0; n < swap.length; n ++) {
                //     updatedSolution[n] = solution[swap[n]];
                // }

                // // Replace solution with updatedSolution so we can use these new cow indices
                // // in the (k + 1)th ieration.
                // solution = updatedSolution;

                k ++;
                System.out.println(k + " " + n);
            }

            k ++;
            if(k > K) {
                indices[n][K] = K + 1;
            }
            else {
                indices[n][K] = k;
            }
        }

        // The final solution is the cow indices + 1
        for(int n = 0; n < indices.length; n ++) {
            System.out.println(indices[n][K]);

            // Be careful of mod by 0
            int answer;
            // if(indices[n][indices[n][K]] == 0) {
            //     answer = 0;
            // } 
            // else {
                answer = indices[n][K % indices[n][K]]; 
            // }

            out.println(answer + 1);
        }

        // Close streams
        in.close();
        out.close();
    }
}