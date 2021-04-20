import java.io.*;
import java.util.*;

public class Perimeter {
    public static char [][] ice;
    public static Boolean [][] visited;
    public static int area;
    public static int perimeter;
    public static int N;
    public static Scanner in;
    public static PrintWriter out;
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        in = new Scanner(new FileReader("perimeter.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        // Get input information
        N = in.nextInt();   // Length of sides of matrix
        in.nextLine();

        // Index by 0
        ice = new char [N][N];
        for(int row = 0; row < N; row ++) {
            String rowString = in.nextLine();
            for(int col = 0; col < N; col ++) {
                ice[row][col] = rowString.charAt(col);
            }
        }

        // Probably should make a visited matrix too, so we don't revisit the same locations
        visited = new Boolean [N][N];

        // Start going through all slots in the matrix
        int maxPerimeter = 0;
        int maxArea = 0;
        for(int row = 0; row < N; row ++) {
            for(int col = 0; col < N; col ++) {
                if(visited[row][col] == null) {
                    // Reset area and perimeter, and get informatio about this blob
                    area = 0;
                    perimeter = 0;
                    recursion(row, col);

                    // Check if the area is bigger 
                    if(maxArea < area) {
                        maxArea = area;
                        maxPerimeter = perimeter;
                    }
                    else if(maxArea == area) {
                        // Farmer John apparently wants the lower perimeter
                        if(perimeter < maxPerimeter) {
                            maxPerimeter = perimeter;
                        }
                    }
                }
            }
        }

        // Print solution
        out.println(maxArea + " " + maxPerimeter);
        
        // Close streams
        in.close();
        out.close();
    }

    public static void recursion(int row, int col) {
        // For debug purposes
        // printDebug();
        // System.out.println(row + " " + col);
        // System.out.println();

        // First, make sure we're not out of bounds
        if(row < 0 || row >= N || col < 0 || col >= N) {
            perimeter ++;
        }
        else if(ice[row][col] == '.') {
            // Check if this is a dot or a pound symbol, and
            // don't go in all 4 directions if we see a dot
            perimeter ++;
        }
        else if(visited[row][col] == null) {
            // Only do something if we haven't visited this slot yet
            visited[row][col] = true;
            area ++;

            // For debug purposes
            // ice[row][col] = 'V';

            // Go in all 4 directions
            recursion(row + 1, col);
            recursion(row - 1, col);
            recursion(row, col + 1);
            recursion(row, col - 1);
        }
    }

    public static void printDebug() {
        for(int row = 0; row < N; row ++) {
            for(int col = 0; col < N; col ++) {
                System.out.print(ice[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("Area: " + area);
        System.out.println("Perimeter: " + perimeter);
    }
}