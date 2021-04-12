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
		int lines=Integer.parseInt(file.nextLine());
		TreeMap <Integer,Boolean> data=new TreeMap <Integer,Boolean> ();
		ArrayList <Interval> intervals=new ArrayList <Interval> ();
		int count=0;
//		Scanner file2=new Scanner(new File("milk2.in"));	//problem is here
		int min=0;
/*		if(Integer.parseInt(file2.next())!=0)
		{ 
			min=Integer.parseInt(file2.next());
		}	*/
		int max=0;
		boolean check=false;
		while(count<lines)
		{
			int low=Integer.parseInt(file.next());
			int high=Integer.parseInt(file.next());
			if(!check)
			{
				check=true;
				min=low;
			}
			else
				min=Math.min(min,low);	//Problem is here
			max=Math.max(max,high);
			intervals.add(new Interval(low,high));
			count++;
		}
		count=min;
		while(min<=max)
		{
			data.put(min,false);
			for(int i=0;i<intervals.size();i++)
				if(min>=intervals.get(i).getStart()&&min<=intervals.get(i).getEnd())
				{
//					System.out.println(min);
					data.put(min,true);
					i=intervals.size();
				}
			min++;
		}
		int maxtemp=max;
		int max2=0;	//Stores highest amount of time when a cow is not milked
		max=0;
//		int tempt=0;
//		int tempf=0;
		int first=-1;
		int last=-1;
		boolean swap=true;
		while(count<=maxtemp)
		{
			if(data.get(count))
			{
				if(!swap)
				{
					last=-1;
					first=-1;
				}
				swap=true;
				if(first==-1)
					first=count;
				last=count;
/*				tempt++;
				if(tempf>max2)
					max2=tempf;
				tempf=0;
				System.out.println(tempt);	*/
			}
			else
			{
				if(swap)
				{
					last=-1;
					first=-1;
				}
				swap=false;
				if(first==-1)
					first=count;
				last=count;
/*				tempf++;
				if(tempt>max)
				{
					max=tempt-1;
					System.out.println("Success");
				}
				tempt=0;	*/
			}
			System.out.println("First "+first+" Last "+last+" swap "+swap);
			if(swap)
			{
				if(max<last-first)
					max=last-first;
			}
			else
			{
				if(max2<last-first)
					max2=last-first+2;
			}
			count++;
			System.out.println(count);
//			System.out.println(tempt+" "+tempf);
//          System.out.println(max);
		}
		
/*		if(tempt>max)
			max=tempt-1;
		if(tempf>max2)
			max2=tempf;		*/
		out.println(max+" "+max2);
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