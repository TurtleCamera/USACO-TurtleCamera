/*
ID: zou.all1
LANG: JAVA
TASK: milk
*/

import java.util.*;
import java.io.*;

class milk
{
	public static void main(String [] args) throws IOException
	{
		Scanner file=new Scanner(new File("milk.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		int units=Integer.parseInt(file.next());
		int farmercount=Integer.parseInt(file.next());
		int count=0;
		TreeMap <Integer,Integer> farmers=new TreeMap <Integer,Integer>(); 
		int cost=0;
		while(count<farmercount)
		{
			farmers.put(Integer.parseInt(file.next()),Integer.parseInt(file.next()));
			count++;
		}
		for(int f: farmers.keySet())
		{
			if(farmers.get(f)<=units)
			{
				cost+=f*farmers.get(f);
				units-=farmers.get(f);
			}
			else
				while(units>0)
				{
					cost+=f;
					units--;
				}
		}
		out.println(cost);
		out.close();
	}
}