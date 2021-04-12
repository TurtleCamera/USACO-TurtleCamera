import java.io.*;
import java.util.*;

class div7 {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("div7.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
//    	ArrayList <Integer> data=new ArrayList <Integer>();
    	int lines=Integer.parseInt(file.next());
		int [] data=new int[lines];
    	int count=0;
    	int sum=0;
    	int max=0;
 /*   	while(count<lines)
    	{
    		data.add(Integer.parseInt(file.next()));
    		count++;
    	}
    	for(int i=0;i<data.size();i++)
    	{
    		if(data.size()-i>max)
    		{
    			for(int j=i;j<i+max;j++)
    				sum+=data.get(j);
    			for(int j=i+max;j<data.size();j++)
    			{
    				sum+=data.get(j);
    				if(sum%7==0)
    					if(max<j-i+1)
    						max=j-i+1;
    			}
    		}
    		sum=0;
    	}	*/
    	while(count<lines)
    	{
 			data[count]=Integer.parseInt(file.next());
    		count++;
    	}
/*    	for(int i=0;i<data.length;i++)
    	{
    		if(data.length-i>max)
    		{
    			for(int j=i;j<i+max;j++)
    				sum+=data[j];
    			for(int j=i+max;j<data.length;j++)
    			{
    				sum+=data[j];
    				if(sum%7==0)
    					if(max<j-i+1)
    						max=j-i+1;
    			}
    		}
    		sum=0;
    	}	*/
    	for(int i=data.length-1;i>=0;i--)
    	{
    		if(1+i>max)
    		{
    			for(int j=i;j>i-max;j--)
    				sum+=data[j];
    			for(int j=i-max;j>=0;j--)
    			{
    				sum+=data[j];
    				if(sum%7==0)
    					if(max<i-j+1)
    						max=i-j+1;
    			}
    		}
    		sum=0;
    	}
    	out.println(max);
    	out.close();
  	}
}