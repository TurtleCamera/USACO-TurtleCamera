import java.io.*;
import java.util.*;

class angry {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("angry.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
    	int hay=Integer.parseInt(file.next());			//N value
    	int cows=Integer.parseInt(file.next());			//K value
    	int count=0;
    	ArrayList <Integer> data=new ArrayList <Integer> ();
//    	boolean [] data;
    	if(hay>0)
    	{
    		while(count<hay)
    		{
    			data.add(0,Integer.parseInt(file.next()));
	  			int i=0;
	  			while(i<data.size()-1)
				{
					if(data.get(i)>data.get(i+1))
					{
						int temp=data.get(i+1);
						data.set(i+1,data.get(i));
						data.set(i,temp);
					}
					i++;
				}
				count++;
    		}
    		if(data.get(data.size()-1)-data.get(0)<=2)
    			out.println("1");
    		else
    		{
    			int min=0;	//used to make this program faster
    			for(int i=0;i<data.size()-1;i++)
    			{
    				int sub=data.get(i+1)-data.get(i);
    				if(sub%2==1)
    					sub++;
    				if(sub<min)
    					min=sub;
    			}
    			int cowsleft=cows;
    			int left=0;
    			int right=2;
    			int diameter=min;
    			boolean found=false;
    			while(!found)
    			{
    				while(0<cowsleft)
	    			{
	    				for(int i=0;i<data.size();i++)
	    					if(right<data.get(i)||i==data.size()-1)
	    					{
	    						left=data.get(i);
	    						right=left+diameter;
	    						cowsleft--;
	    						if(i==data.size()-1)
	    							found=true;
	    						i=data.size();
	    					}
					System.out.println("Cows Left Loop "+cowsleft);
	    			}
	    			if(!found)
	    			{
		    			diameter+=2;
		    			left=0;
		    			right=diameter;
	    			}
	    			cowsleft=cows;
	    			System.out.println("Found loop");
    			}
    			out.println(diameter/2);
    		}
/*    		max=Integer.parseInt(file.next());
    		temp.add(min);
    		while(count<hay)
    		{
    			int t=Integer.parseInt(file.next());
    			max=Math.max(t,max);
    			temp.add(t);
    			count++;
    		}
    		data=new boolean[max+1];
    		for(int i=0;i<temp.size();i++)
    			data[temp.get(i)]=true;	*/
    		
    	}
    	else
    		out.println("0");
    	out.close();
  	}
}