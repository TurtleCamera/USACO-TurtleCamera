import java.io.*;
import java.util.*;

public class Mountains {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        in = new Scanner(new FileReader("mountains.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));

        // Get input information
        N = in.nextInt();   

        // Convert all the peak points to intervals
        Interval [] intervals = new Interval[N];
        for(int n = 0; n < N; n ++) {
            int x = in.nextInt();
            int y = in.nextInt();
            intervals[n] = new Interval(x - y, x + y);
        }

        // Sort this array
        Arrays.sort(intervals);

        // Start counting the peaks that are visible to Bessie
        int peakCount = 0;
        int furthestEndPoint = -1;
        for(int i = 0; i < intervals.length; i ++) {
            if(furthestEndPoint < intervals[i].end) {
                peakCount ++;
                furthestEndPoint = intervals[i].end;
            }
        }

        // Print solution
        out.println(peakCount);
        
        // Close streams
        in.close();
        out.close();
    }
}

// This is basically the famous interval scheduling problem (greedy version), although
// with a very slight twist to it.
class Interval implements Comparable<Interval>{
    public int start;
    public int end;

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public int compareTo(Interval other) {
        // We need this function to sort by starting points for our greedy algorithm
        if(this.start > other.start) {
            return 1;
        }
        else if(this.start == other.start) {
            // This is the edge case. Prioritize the interval with the further ending point
            // to prevent selecting an interval that's already bounded by another interval.
            // USACO states that this problem can't have duplicate points, so we don't need
            // to check for that.
            if(this.end < other.end) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return -1;
        }
    }
}