/*
ID: zou.all1
LANG: JAVA
TASK: ride
*/
/*
import java.util.*;
import java.io.*;
class ride
{
	public static void main(String [] args) throws IOException
	{
		BufferedReader file=new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		StringTokenizer s = new StringTokenizer(file.readLine());
		int comet=1;
		int group=1;
		while(s.hasMoreTokens())
			comet*=(s.nextToken().compareTo("A")+1);
		comet%=47;
		s = new StringTokenizer(file.readLine());
		while(s.hasMoreTokens())
			group*=(s.nextToken().compareTo("A")+1);
		group%=47;
		if(comet==group)
			out.println("GO");
		else
			out.println("STAY");
		out.close();
	}
}
*/
/*
import java.io.*;
import java.util.*;

public class ride
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
*/

import java.util.*;
import java.io.*;
public class ride
{
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    Scanner file = new Scanner(new File("ride.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    String data=file.nextLine();
    int comet=1;
    int group=1;
    while(data.length()>0)
    {
    	String temp=data.substring(0,1);
    	data=data.substring(1);
    	comet*=(temp.compareTo("A")+1);
    }
    comet%=47;
    data=file.nextLine();
    while(data.length()>0)
    {
    	String temp=data.substring(0,1);
    	data=data.substring(1);
    	group*=(temp.compareTo("A")+1);
    }    
    	group%=47;
    if(comet==group)
    	out.println("GO");
    else
    	out.println("STAY");

    out.close();                             // close the output file
  }
}