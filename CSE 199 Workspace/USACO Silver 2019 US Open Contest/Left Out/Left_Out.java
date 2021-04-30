import java.util.*;
import java.io.*;

class Left_Out {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static char [][] grid;

    public static void main(String[] args) throws IOException{
        // Initialize readers and writers
        in = new Scanner(new FileReader("leftout.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));

        // Get the length of the sides
        N = in.nextInt();
        in.nextLine();

        // Fill in the grid
        grid = new char[N][N];
        for(int row = 0; row < N; row ++) {
            String rowInput = in.nextLine();
            for(int col = 0; col < N; col ++) {
                grid[row][col] = rowInput.charAt(col);
            }
        }

        // Use this variable to see if there's no offending cow
        Boolean hasOffendingCow = true;

        // Figure out which row is different
        int [] differentCount = new int [N];    // Use this to keep track of how many
                                                // times a row differs
        int potentialRow = -1;
        for(int row = 0; row < N; row ++) {
            // Increment different count for both rows if this row differs from our first row
            if(!checkRowMatch(row)) {
                differentCount[row] ++;
                differentCount[0] ++;
                potentialRow = row;
            }
        }

        // The first row could be different, so we must check it
        if(differentCount[0] > 1) {
            potentialRow = 0;
        }

        // Check to make sure we have an offending cow
        if(!checkOffendingCow(differentCount)) {
            hasOffendingCow = false;
        }

        // Figure out which column is different
        differentCount = new int [N];    // Use this to keep track of how many
                                                // times a row differs
        int potentialColumn = -1;
        for(int col = 0; col < N; col ++) {
            // Increment different count for both rows if this row differs from our first row
            if(!checkColMatch(col)) {
                differentCount[col] ++;
                differentCount[0] ++;
                potentialColumn = col;
            }
        }

        // The first col could be different, so we must check it
        if(differentCount[0] > 1) {
            potentialColumn = 0;
        }

        // Check to make sure we have an offending cow
        if(!checkOffendingCow(differentCount)) {
            hasOffendingCow = false;
        }

        // Print out the solution, but make sure we're indexed by 1
        // N == 2 case is not possible
        if(N == 2) {
            out.println(-1);
        }
        else if(hasOffendingCow) {
            out.println((potentialRow + 1) + " " + (potentialColumn + 1));
        }
        else {
            out.println(-1);
        }

        // Close streams
        in.close();
        out.close();
    }

    public static Boolean checkRowMatch(int row) {
        // Now we check to see if these are matching rows
        int matchCount = 0;
        int diffCount = 0;
        for(int c = 0; c < N; c ++) {
            if(grid[0][c] == grid[row][c]) {
                matchCount ++;
            }
            else {
                diffCount ++;
            }
        }

        if(matchCount == 0 || diffCount == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean checkColMatch(int col) {
        int matchCount = 0;
        int diffCount = 0;
        for(int r = 0; r < N; r ++) {
            if(grid[r][0] == grid[r][col]) {
                matchCount ++;
            }
            else {
                diffCount ++;
            }
        }

        if(matchCount == 0 || diffCount == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean checkOffendingCow(int [] differentCount) {
        int numDifferent = 0;
        for(int i = 0; i < differentCount.length; i ++) {
            if(differentCount[i] != 0) {
                numDifferent ++;
            }
        }

        if(numDifferent == 2 || numDifferent == N) {
            return true;
        }
        else {
            // Something is wrong, which shouldn't be the case
            return false;
        }
    }
}