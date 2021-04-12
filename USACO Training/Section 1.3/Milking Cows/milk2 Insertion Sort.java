/*
ID: zou.all1
LANG: JAVA
TASK: milk2
*/

import java.util.*;
import java.io.*;

class milk2
{
	public static void main(String [] args) throws IOException
	{
		Scanner file=new Scanner(new File("milk2.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		int lines=Integer.parseInt(file.next());
		ArrayList <Interval> data=new ArrayList <Interval>();
		int low=-1;
		int high=-1;
		int count=0;
		if(lines>0)
		{
//			low=Integer.parseInt(file.next());
//			high=Integer.parseInt(file.next());
			data.add(new Interval(Integer.parseInt(file.next()),Integer.parseInt(file.next())));
			count=1;
		}	
//		System.out.println("Line "+lines);
		int milk=0;
		int idle=0;
		while(count<lines)
		{
//			int s=Integer.parseInt(file.next());
//			int e=Integer.parseInt(file.next());
//			System.out.println(count+" "+s+" "+e);
			int i=0;
/*			for(int i=0;i<data.size();i++)
			{
				index=i;
				if(s<data.get(i).getStart())
					i=data.size();	
			}	*/
			data.add(0,new Interval(Integer.parseInt(file.next()),Integer.parseInt(file.next())));
			while(i<data.size()-1)
			{
				if(data.get(i+1).getStart()<data.get(i).getStart())
				{
					Interval temp=data.get(i+1);
					data.set(i+1,data.get(i));
					data.set(i,temp);
				}
				i++;
			}
			count++;
		}
//		for(int i=0;i<data.size();i++)
//			System.out.println(data.get(i).getStart()+" "+data.get(i).getEnd());
//		System.out.println(data.size());
		int loopstart=0;
		if(data.size()>0)
		{
			low=data.get(0).getStart();
			high=data.get(0).getEnd();
			loopstart=1;
		}
		for(int i=loopstart;i<data.size();i++)
		{
//			int templow=Integer.parseInt(file.next());
//			int temphigh=Integer.parseInt(file.next());
			int templow=data.get(i).getStart();
			int temphigh=data.get(i).getEnd();
			if(low<=templow&&templow<=high)
			{
				if(temphigh>high)
					high=temphigh;
//				System.out.println("Success");
			}
			else
			{
				if(milk<high-low)
					milk=high-low;
				if(idle<templow-high)
				{
					idle=templow-high;
//					System.out.println(templow+" "+high);
				}
				low=templow;
				high=temphigh;
			}
//			count++;
		}
		if(high-low>milk)
			milk=high-low;
		out.println(milk+" "+idle);
		out.close();
	}
}

class Interval
{
	private int start;
	private int end;
	
	public Interval(int s, int e)
	{
		start=s;
		end=e;
	}
	
	public int getStart()
	{
		return start;
	}
	
	public int getEnd()
	{
		return end;
	}
}