import java.io.*;
import java.util.*;

public class Berries {
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("berries.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));

        // Get input information
        int N = in.nextInt();   // Number of trees
        int K = in.nextInt();   // Number of buckets

        // Stores the number of berries on each tree
        int [] berries = new int [N];
        int mostBerries = 0;
        for(int n = 0; n < N; n ++) {
            berries[n] = in.nextInt();

            if(mostBerries < berries[n]) {
                mostBerries = berries[n];
            }
        }
 
        // We can make an O(N^2) solution because N <= 1,000, so let's just try every possible
        // berry threshold for our buckets
        int maxBessieBerries = 0;
        for(int threshold = mostBerries; threshold >= 0; threshold --) {
            // Copy over the array
            int [] pickingFromTree = new int [N];
            for(int n = 0; n < N; n ++) {
                pickingFromTree[n] = berries[n];
            }

            // Sort the array
            Arrays.sort(pickingFromTree);

            // Keep looping until we either run out of buckets or we run out of berries to pick
            int [] buckets = new int [K];
            Boolean berriesRemain = true;  // Tracks if we still have berries on the tree
            int bucketIndex = 0;
            while(berriesRemain) {
                berriesRemain = false;

                // That array is sorted in ascending order, so let's go backwards
                for(int i = pickingFromTree.length - 1; i >= 0; i --) {
                    // Do we still have berries left? If so, pick from tree
                    if(pickingFromTree[i] > 0) {
                        // Only pick the threshold number
                        if(pickingFromTree[i] >= threshold) {
                            // This means we still have berries to pick
                            berriesRemain = true;

                            while(pickingFromTree[i] >= threshold && bucketIndex < buckets.length) {
                                buckets[bucketIndex] = threshold;
                                pickingFromTree[i] -= threshold;

                                bucketIndex ++;
                            }
                        }
                        else {
                            buckets[bucketIndex] = pickingFromTree[i];
                            pickingFromTree[i] = 0;

                            bucketIndex ++;
                        }
                    }

                    // Stop picking if we filled our buckets
                    if(bucketIndex == buckets.length) {
                        berriesRemain = false;
                        break;
                    }
                }
            }

            // How many berries did Bessie get? Discard the first K/2
            int berriesCount = 0;
            for(int i = buckets.length / 2; i < buckets.length; i ++) {
                berriesCount += buckets[i];
            }

            // Is this the new best?
            if(maxBessieBerries < berriesCount) {
                maxBessieBerries = berriesCount;
            }
        }

        // Print the solution
        out.println(maxBessieBerries);

        // Close streams
        in.close();
        out.close();
    }
}