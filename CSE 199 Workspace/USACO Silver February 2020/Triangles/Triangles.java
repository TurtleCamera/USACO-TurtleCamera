import java.io.*;
import java.util.*;

public class Triangles {
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        Scanner in = new Scanner(new FileReader("triangles.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        // Get input information
        int N = in.nextInt();   // Number coordinates

        // Store all the coordinates
        Point [] posts = new Point [N];
        
        // Debug
        for(int i = 0; i < posts.length; i ++) {
            posts[i] = new Point(in.nextInt(), in.nextInt());
        }

        // Sort by Y coordinates first
        Arrays.sort(posts);

        // Debug
        // for(int i = 0; i < posts.length; i ++) {
        //     System.out.println(posts[i].x + " " + posts[i].y);
        // }
        // System.out.println();

        // Start computing the total X edges
        int start = 0;
        int end;
        while(start < N) {
            // Keep moving through the array until we get a Y coordinate that's
            // different
            end = start;
            while(end + 1 < N && posts[end].y == posts[end + 1].y) {
                end ++;
            }

            // Calculate the initial total X edge for the coordinate at index start
            int startingXCoordinate = posts[start].x;
            for(int i = start + 1; i <= end; i ++) {
                posts[start].totalXEdges += posts[i].x - startingXCoordinate;
            }

            // Use the initial total X edge lengths to compute the total X edge lengths
            // of the other coordinates with the same Y coordinate
            int totalEdges = end - start;   // Used to determine how many tempDistances
                                            // we "trim" or "add"
            for(int i = start + 1; i <= end; i ++) {
                int tempDistance = posts[i].x - posts[i - 1].x;
                int indexFromStart = i - start; // Used with totalEdges
                posts[i].totalXEdges = posts[i - 1].totalXEdges - (totalEdges - indexFromStart) * tempDistance +
                                       (indexFromStart - 1) * tempDistance; 
            }

            start = end + 1;
        }

        // Debug
        // for(int i = 0; i < posts.length; i ++) {
        //     System.out.println(posts[i].totalXEdges);
        // }
        // System.out.println();

        // Now sort by X coordinates
        Point.prioritizeX = true;
        Arrays.sort(posts);

        // Debug
        // for(int i = 0; i < posts.length; i ++) {
        //     System.out.println(posts[i].x + " " + posts[i].y);
        // }
        // System.out.println();

        // Start computing the total Y edges
        start = 0;
        while(start < N) {
            // Keep moving through the array until we get an X coordinate that's
            // different
            end = start;
            while(end + 1 < N && posts[end].x == posts[end + 1].x) {
                end ++;
            }

            // Calculate the initial total Y edge for the coordinate at index start
            int startingYCoordinate = posts[start].y;
            for(int i = start + 1; i <= end; i ++) {
                posts[start].totalYEdges += posts[i].y - startingYCoordinate;
            }

            // Use the initial total Y edge lengths to compute the total Y edge lengths
            // of the other coordinates with the same X coordinate
            int totalEdges = end - start;   // Used to determine how many tempDistances
                                            // we "trim" or "add"
            for(int i = start + 1; i <= end; i ++) {
                int tempDistance = posts[i].y - posts[i - 1].y;
                int indexFromStart = i - start; // Used with totalEdges
                posts[i].totalYEdges = posts[i - 1].totalYEdges - (totalEdges - indexFromStart) * tempDistance +
                                       (indexFromStart - 1) * tempDistance; 
            }

            start = end + 1;
        }

        // Debug
        // for(int i = 0; i < posts.length; i ++) {
        //     System.out.println(posts[i].totalYEdges);
        // }
        // System.out.println();

        // Now multiply all the lengths total X and Y edges together
        // No need to sort again
        long totalArea = 0;
        int mod = 1000000007;
        for(int i = 0; i < posts.length; i ++) {
            // Remember that this number can be huge, so we have to mod by 10^9 + 7
            totalArea += (posts[i].totalXEdges * posts[i].totalYEdges) % mod;

            // Debug
            // System.out.println(posts[i].totalXEdges + " " + posts[i].totalYEdges);
            // if(totalArea > mod) {
            //     System.out.println("Too big");
            // }
            // if(totalArea < 0) {
            //     System.out.println("Negative " + totalArea);
            // }

            totalArea %= mod;
        }

        // Print solution
        out.println((int) totalArea);

        // Close streams
        in.close();
        out.close();
    }
}

class Point implements Comparable<Point> {
    public static Boolean prioritizeX = false;  // Determines which coordinate to sort by
    public int x;
    public int y;
    public long totalXEdges; // Stores all X distances between this point and other points
                             // with the same Y value
    public long totalYEdges; // Stores all Y distances between this point and other points
                             // with the same X value

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.totalXEdges = 0;
        this.totalYEdges = 0;
    }

    // We want to be able to choose whether to sort by X or by Y first
    @Override
    public int compareTo(Point p) {
        // Depending on which coordinate is the focus of the sort, we change the compareTo
        // function
        if(!prioritizeX) {
            // Sort by Y first
            if(this.y > p.y) {
                return 1;
            }
            else if(this.y < p.y) {
                return -1;
            }
            else {
                // Break tie by sorting by X
                if(this.x > p.x) {
                    return 1;
                }
                else {
                    // Note that all points are distinct
                    return -1;
                }
            }
        }
        else {
            // Sort by X first
            if(this.x > p.x) {
                return 1;
            }
            else if(this.x < p.x) {
                return -1;
            }
            else {
                // Break tie by sorting by y
                if(this.y > p.y) {
                    return 1;
                }
                else {
                    // Note that all points are distinct
                    return -1;
                }
            }
        }
    }
}