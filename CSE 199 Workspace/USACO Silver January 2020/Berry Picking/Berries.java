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
 
        // Sort the array
        Arrays.sort(berries);

        // Check every threshold to see which one produces the most berries
        int maxBerries = 0;
        for(int threshold = mostBerries; threshold > 0; threshold --) {
            // How many buckets can we fill up to the threshold?
            int bucketsFilled = 0;
            for(int i = 0; i < berries.length; i ++) {
                bucketsFilled += berries[i] / threshold;
            }

            // There are several cases to deal with
            if(bucketsFilled >= K) {
                // Basically, we pretty much have our solution to this threshold because
                // all buckets have the same number of berries in them
                if(maxBerries < threshold * K / 2) {
                    maxBerries = threshold * K / 2;
                }
            }
            else if(bucketsFilled >= K / 2) {
                // Now, we only need to compute the buckets of berries remaining for Bessie
                int bessieBerries = (bucketsFilled - (K / 2)) * threshold;

                // Copy over the array to see what number of berries we have left.
                int [] remainingBerries = new int [N];
                for(int n = 0; n < N; n ++) {
                    remainingBerries[n] = berries[n] % threshold;
                }
                
                // Sort this array
                Arrays.sort(remainingBerries);

                // Keep looping until we fill all our buckets or run out of trees
                // Go backwards because this array is sorted in ascending order
                for(int n = N - 1; n >= 0 && (bucketsFilled + (N - n - 1) < K); n --) {
                    bessieBerries += remainingBerries[n];
                }

                // New best?
                if(maxBerries < bessieBerries) {
                    maxBerries = bessieBerries;
                }
            }
            else {
                // Our threshold caused us to fill less than half our buckets with the
                // "threshold" number of berries, so lets just get the rest of the berries from
                // the trees. The code is pretty similar to the one in the else if statement above.
                // Bessie gets nothing to start of in this case.
                int bessieBerries = 0;

                // Copy over the array to see what number of berries we have left.
                int [] remainingBerries = new int [N];
                for(int n = 0; n < N; n ++) {
                    remainingBerries[n] = berries[n] % threshold;
                }
                
                // Sort this array
                Arrays.sort(remainingBerries);

                // Keep looping until we fill all our buckets or run out of trees
                // Go backwards because this array is sorted in ascending order
                for(int n = N - 1; n >= 0 && (bucketsFilled + (N - n - 1) < K); n --) {
                    // Can't start adding berries until we finish giving baskets to Elsie
                    if(bucketsFilled + (N - n - 1) >= K / 2) {
                        bessieBerries += remainingBerries[n];
                    }
                }

                // New best?
                if(maxBerries < bessieBerries) {
                    maxBerries = bessieBerries;
                }
            }
        }

        // Print the solution
        out.println(maxBerries);

        // Close streams
        in.close();
        out.close();
    }
}