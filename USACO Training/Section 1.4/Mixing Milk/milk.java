/*
ID: all.zou1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.*;

class milk
{
	public static void main (String [] args) throws IOException
	{
	    Scanner in = new Scanner(new File("milk.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	    int totalmilk=in.nextInt();
	    int totalfarmers=in.nextInt();
	    int count=0;
	    int totalcost=0;
	    HashMap <Integer,Integer> farmers=new HashMap <Integer,Integer>();
	    while(count<totalfarmers)
	    {
	    	int price=in.nextInt();
	    	if(farmers.containsKey(price))
	    		farmers.put(price,farmers.get(price)+in.nextInt());
	    	else	
	    		farmers.put(price,in.nextInt());
	    	count++;
	    }
	    for (Map.Entry<Integer,Integer> temp : farmers.entrySet())
	    {
	    	if(totalmilk>=1)
	    	{
	    		if(temp.getValue()>totalmilk)	//If you can buy milk but you don't need to buy all of them.
	    		{
	    			totalcost+=temp.getKey()*totalmilk;
	    			break;
	    		}
	    		else	//If you can buy all of the milk.
	    		{
	    			totalcost+=temp.getKey()*temp.getValue();
	    		}
	    		totalmilk-=temp.getValue();
	    	}
	    }
	    out.println(totalcost);
	    out.close();
	}
}