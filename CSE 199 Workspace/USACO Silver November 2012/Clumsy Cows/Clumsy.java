import java.io.*;
import java.util.*;
import java.awt.Point;

public class Clumsy {
    public static Scanner in;
    public static PrintWriter out;
    public static String string;
    public static void main(String[] args) throws IOException {
        // One thing to note in this file is that I accidentally treated rows as X coordinates
        // and columns as Y coordinates, oops. It's consistent throughout the code, though.

        // Initialize readers and writers
        in = new Scanner(new FileReader("clumsy.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("clumsy.out")));

        // Wait, no N this time?
        string = in.nextLine();   

        // Use a stack for this
        Stack<Character> parentheses = new Stack<Character>();

        // Go through the whole string of parentheses
        int numChange = 0;
        for(int i = 0; i < string.length(); i ++) {
            if(string.charAt(i) == '(') {
                // Push '(' onto the stack
                parentheses.push('(');
            }
            else {
                // Otherwise, pop something from the stack if we get a ')'.
                // Make sure the stack isn't empty, though.
                if(parentheses.size() == 0) {
                    // This means we need to replace a ')' with '('
                    numChange ++;
                    parentheses.push('(');
                }
                else {
                    parentheses.pop();
                }
            }
        }

        // Print solution. The question implies an even number of characters.
        out.println(numChange + parentheses.size() / 2);
        
        // Close streams
        in.close();
        out.close();
    }
}