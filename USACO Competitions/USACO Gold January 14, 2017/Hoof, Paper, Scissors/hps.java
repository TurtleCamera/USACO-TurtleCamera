import java.util.*;
import java.io.*;

public class hps
{
	static String [] moves;
    public hps()
    {
    }
    public static void main(String[] args) throws IOException
    {
    	Scanner file = new Scanner(new File("hps.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
	    String [] temp=file.nextLine().split(" ");
	    int lines=Integer.parseInt(temp[0]);
	    int switches=Integer.parseInt(temp[1]);
	    moves=new String[lines];
	    for(int i=0;i<lines;i++)
	    {
	    	moves[i]=file.nextLine();
	    }
	    
	    out.println(recur(moves,switches,0,0));
	    out.close();
    }
    
/*    public static int recur(String [] gest, int swaps, int index, int count)
    {
    	int max=count;
    	while(index<gest.legnth)
    	{
    		String gesture=gest[index];
    		for(int i=index+1;i<gest.legnth;i++)
    		{
    			if(!gesture.equals(gest[i]))
    				max=Math.max(recur(gest,swaps-1,i,i-index),max);
    		}
    	}
    }	*/
/*
    public static int recur(String [] gest, int swaps, int index, int count)
    {
    	int max=count;
    	if(index<gest.length)
    	{
    		if(swaps>0)
    		{
    			int H=0;
    			int P=0;
    			int S=0;
    			for(int i=index;i<gest.length;i++)
    			{
    				if(gest[i].equals("H"))
    					H++;
    				else if(gest[i].equals("P"))
    					P++;
    				else if(gest[i].equals("S"))
    					S++;
    				max=Math.max(recur(gest,swaps-1,i+1,count+Math.max(H,Math.max(P,S))),max);
    			}
    		}
    		else
    		{
    			int H=0;
    			int P=0;
    			int S=0;
    			for(int i=index;i<gest.length;i++)
    			{
    				if(gest[i].equals("H"))
    					H++;
    				else if(gest[i].equals("P"))
    					P++;
    				else if(gest[i].equals("S"))
    					S++;
    			}
    			return count+Math.max(H,Math.max(P,S));
    		}
    	}
    	return max;
    }
*/
	public static int recur(String [] gest, int swaps, int index, int count)
    {
    	int max=count;
    	if(index<gest.length)
    	{
    		if(swaps>0)
    		{
    			int H=0;
    			int P=0;
    			int S=0;
    			String current=gest[index];
    			for(int i=index;i<gest.length;i++)
    			{
    				if(gest[i].equals("H"))
    					H++;
    				else if(gest[i].equals("P"))
    					P++;
    				else if(gest[i].equals("S"))
    					S++;
    				if(!gest[i].equals(current))
    				{
    					current=gest[i];
    					max=Math.max(recur(gest,swaps-1,i+1,count+Math.max(H,Math.max(P,S))),max);
    				}
    			}
    		}
    		else
    		{
    			int H=0;
    			int P=0;
    			int S=0;
    			for(int i=index;i<gest.length;i++)
    			{
    				if(gest[i].equals("H"))
    					H++;
    				else if(gest[i].equals("P"))
    					P++;
    				else if(gest[i].equals("S"))
    					S++;
    			}
    			return count+Math.max(H,Math.max(P,S));
    		}
    	}
    	return max;
    }
}
