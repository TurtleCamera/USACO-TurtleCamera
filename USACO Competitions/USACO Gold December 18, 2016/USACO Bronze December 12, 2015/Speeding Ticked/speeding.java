/*
ID: zou.all1
LANG: JAVA
TASK: speeding
*/
import java.util.*;
import java.io.*;
public class speeding
{
  public static void main (String [] args) throws IOException {
	    Scanner file=new Scanner(new File("speeding.in"));
	  	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("speeding.out")));
	  	Map <Integer,Integer> data=new HashMap <Integer,Integer>();
	  	int lines=0;
	  	int count=0;
	  	int N=Integer.parseInt(file.next());
	  	int M=Integer.parseInt(file.next());
	  	while(lines<N)
	  	{
	  		int length=Integer.parseInt(file.next());
	  		int speed=Integer.parseInt(file.next());
	  		for(int i=0;i<length;i++)
	  		{
	  			data.put(count,speed);
	  			count++;
	  		}
	  		data.put(100,speed);
	  		lines++;
	  	}
	  	int drive=0;
	  	int max=0;
	  	lines=0;
	  	while(lines<M)
	  	{
	  		int length=Integer.parseInt(file.next());
	  		int speed=Integer.parseInt(file.next());
	  		for(int i=0;i<length;i++)
	  		{
	  			if(speed>data.get(drive))
	  				if(speed-data.get(drive)>max)
	  					max=speed-data.get(drive);
	  		drive++;
	  		}
	  		lines++;
	  	}
	  	out.println(max);
	  	out.close();
	}
}