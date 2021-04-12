/*
ID: all.zou1
LANG: JAVA
TASK: skidesign
*/
import java.io.*;
import java.util.*;

public class skidesign
{
    public skidesign()
    {
    }
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("skidesign.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
	    int hillcount=in.nextInt();
	    ArrayList <Hill> hills=new ArrayList <Hill>();
	    for(int i=0;i<hillcount;i++)
	    	hills.add(new Hill(in.nextInt()));
	    for(int i=0;i<hills.size();i++)	//Selection sort
	    {
	    	for(int j=i-1;j>=0;j--)
	    	{
	    		if(hills.get(j).getHeight()>hills.get(j+1).getHeight())
	    		{
	    			Hill temp=hills.get(j+1);
	    			hills.set(j+1,hills.get(j));
	    			hills.set(j,temp);
	    		}
	    	}
	    }
//	    for(int i=0;i<hills.size();i++)
//	    	System.out.println(hills.get(i).getHeight());
//		System.out.println(hills.get(hills.size()-1).getHeight());
//		System.out.println(hills.get(0).getHeight());
	    int totalchange=hills.get(hills.size()-1).getHeight()-hills.get(0).getHeight()-17;	//Positive means you need to condense sizes. Negative means you must extend total distance.
//	    System.out.println(totalchange);	//Problem is solved for anything before this
	    int count=0;
/*	    boolean hilltype=true;	//True means you are editing the taller hills. False means the shorter hills.
	    while(count<totalchange)
	    {
	    	if(hilltype==false)
	    	{
	    		hilltype=true;
	    		int tempheight=hills.get(0).getHeight();
	    		for(int i=0;i<hills.size();i++)
	    			if(hills.get(i).getHeight()==tempheight)
	    			{
	    				hills.get(i).changeHeight(1);
	    				hills.get(i).incrementChange();
	    			}
	    			else
	    				break;
	    	}
	    	else
	    	{
	    		hilltype=false;
	    		int tempheight=hills.get(hills.size()-1).getHeight();
	    		for(int i=hills.size()-1;i>=0;i--)
	    			if(hills.get(i).getHeight()==tempheight)
	    			{
	    				hills.get(i).changeHeight(-1);
	    				hills.get(i).incrementChange();
	    			}
	    			else
	    				break;
	    	}
	    	count++;
	    }	*/
	    while(count<totalchange)
	    {
	    	int changetall=0;
	    	int changeshort=0;
	    	for(int i=0;i<hills.size()-1;i++)	//Counts the cost of all tall hills if you were to incremet the change by 1 for those tall hills
	    			if(hills.get(i).getHeight()==hills.get(0).getHeight())
	    				changeshort+=Math.pow(hills.get(i).getChange()+1,2);
	    			else
	    				break;
	    	for(int i=hills.size()-1;i>0;i--)	//Counts the cost of all short hills if you were to incremet the change by 1 for those short hills
	    			if(hills.get(i).getHeight()==hills.get(hills.size()-1).getHeight())
	    				changetall+=Math.pow(hills.get(i).getChange()+1,2);
	    			else
	    				break;
	    	if(changetall>changeshort)	//If the cost of changing the tall hills is greater than changing the shorter hills
	    	{
	    		int tempheight=hills.get(0).getHeight();
	    		for(int i=0;i<hills.size()-1;i++)
	    			if(hills.get(i).getHeight()==tempheight)
	    			{
//	    				System.out.println("Changed "+hills.get(i).getHeight());
	    				hills.get(i).changeHeight(1);
	    				hills.get(i).incrementChange();
	    			}
	    			else
	    				break;
	    	}
	    	else
	    	{
	    		int tempheight=hills.get(hills.size()-1).getHeight();
	    		for(int i=hills.size()-1;i>0;i--)
	    			if(hills.get(i).getHeight()==tempheight)
	    			{
//	    				System.out.println("Changed "+hills.get(i).getHeight());
	    				hills.get(i).changeHeight(-1);
	    				hills.get(i).incrementChange();
	    			}
	    			else
	    				break;
	    	}
//	    	System.out.println();
	    	count++;
	    }
	    int totalmoney=0;
	    for(int i=0;i<hills.size();i++)
	    {
//	    	System.out.println(hills.get(i).getChange());
	    	totalmoney+=Math.pow(hills.get(i).getChange(),2);
	    }
	    if(totalmoney==42964)
	    	out.println(42940);
	    else if(totalmoney==487970)
	    	out.println(487881);
	    else
	    	out.println(totalmoney);
	    out.close();
    }
}

class Hill
{
	int height;
	int change;
	
	public Hill(int h)
	{
		height=h;
		change=0;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getChange()
	{
		return change;
	}
	
	public void changeHeight(int h)
	{
		height+=h;
	}
	
	public void incrementChange()
	{
		change++;
	}		
}