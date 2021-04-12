import java.io.*;
import java.util.*;

class cbarn {
	static ArrayList <Integer> barn;
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("cbarn.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    	int rooms=Integer.parseInt(file.next());
    	barn=new ArrayList <Integer>();
    	int count=0;
    	while(count<rooms)
    	{
    		barn.add(Integer.parseInt(file.next()));
    		count++;
    	}
    	int distance=-1;
    	for(int i=0;i<barn.size();i++)
    	{
    		if(distance==-1)
    			distance=countdistance();
    		else
    			distance=Math.min(distance,countdistance());
    		barn.add(0,barn.remove(barn.size()-1));
    	}
    	out.println(distance);
		out.close();
  	}
  	
  	public static int countdistance()
  	{
  		int distance=0;
  		for(int i=0;i<barn.size();i++)
  			distance+=i*barn.get(i);
  		return distance;
  	}
}