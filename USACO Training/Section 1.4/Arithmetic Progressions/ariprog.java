/*
ID: all.zou1
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

public class ariprog
{
    public ariprog()
    {
    }
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("ariprog.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
	    double prog=(double)in.nextInt();
	    int bi=in.nextInt();
	    ArrayList <Integer> bisquares=new ArrayList <Integer>();
	    for(int i=0;i<=bi;i++)	//Generates all possible bisquares
	    {
	    	for(int j=0;j<=bi;j++)
	    	{
	    		int temp=(int)(Math.pow(i,2)+Math.pow(j,2));
	    		if(!bisquares.contains(temp))
	    			bisquares.add(temp);
	    	}
	    }
	    Collections.sort(bisquares);
//	    System.out.println(bisquares.toString());	    
	    for(int i=0;i<bisquares.size();i++)	//Checks all possible progressions
	    {
	    	int maxdifference=(int)Math.ceil((bisquares.get(bisquares.size()-1)-bisquares.get(i))/prog);	//The max difference between each number in a progression
	//    	System.out.println(maxdifference);
	    	for(int j=1;j<=maxdifference;j++)
	    	{
	    		for(int k=0;k<prog;k++)
	    		{
	    			boolean works=true;
		    		if(!bisquares.contains(bisquares.get(i)+prog*maxdifference))
		    		{
		    			works=false;
		    			break;
		    		}
	    			if(works)
	    				out.println(bisquares.get(i)+" "+j);
	    		}
	    	}
	    }
	    out.close();
    }
}
