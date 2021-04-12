/*
ID: zou.all1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride
{
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    Scanner file = new Scanner(new File("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    String [] data=file.nextLine().split("");
    int comet=1;
    int group=1;
    for(int i=0;i<data.length;i++)
    	comet*=(data[i].compareTo("A")+1);
    comet%=47;
    data=file.nextLine().split("");
    for(int i=0;i<data.length;i++)
    	group*=(data[i].compareTo("A")+1);
    group%=47;
    if(comet==group)
    	out.println("GO");
    else
    	out.println("STAY");

    out.close();                             // close the output file
  }
}