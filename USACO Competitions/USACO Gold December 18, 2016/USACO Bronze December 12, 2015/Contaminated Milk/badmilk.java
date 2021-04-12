/*
ID: zou.all1
LANG: JAVA
TASK: badmilk
*/
import java.util.*;
import java.io.*;
public class badmilk
{
  public static void main (String [] args) throws IOException {
	    Scanner file=new Scanner(new File("badmilk.in"));
	  	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("badmilk.out")));
	  	int N=Integer.parseInt(file.next());
	  	file.next();
	  	int D=Integer.parseInt(file.next());
	  	int S=Integer.parseInt(file.next());
	  	ArrayList <pmt> potential=new ArrayList <pmt>();
	  	int count=0;
	  	while(count<D)
	  	{
	  		potential.add(new pmt(Integer.parseInt(file.next()),Integer.parseInt(file.next()),Integer.parseInt(file.next())));
	  		count++;
	  	}
	  	ArrayList <pmt> sick=new ArrayList <pmt>();
	  	count=0;
	  	while(count<S)
	  	{
	  		sick.add(new pmt(Integer.parseInt(file.next()),-1,Integer.parseInt(file.next())));
	  		count++;
	  	}
	  	for(int i=0;i<sick.size();i++)		//Step 1, check the time the milk was drank compared to when people got stick
	  	{
	  		int p=sick.get(i).getPerson();
	  		int t=sick.get(i).getTime();
	  		for(int j=0;j<potential.size();j++)
	  			if(potential.get(i).getPerson()==p)
	  				if(potential.get(i).getTime()>=t)
	  				{
	  					potential.remove(j);
	  					j--;
	  				}
	  	}
	  	Map <Integer,Boolean> sickcheck=new HashMap <Integer,Boolean>();	//Step 2, check if every sick person drank a particular milk
	  	for(int i=0;i<sick.size();i++)	
	  		sickcheck.put(sick.get(i).getPerson(),false);
	  	count=0;
	  	for(int i=0;i<potential.size();i++)
	  	{
	  		int m=potential.get(i).getMilk();
	  		for(int j=0;j<potential.size();j++)
	  			if(potential.get(j).getMilk()==m)
	  				sickcheck.put(potential.get(j).getPerson(),true);
	  		boolean check=true;
	  		for(int j=0;j<sick.size()&&check;j++)
	  			if(sickcheck.get(sick.get(j).getPerson())==false)
	  				check=false;
	  		if(!check)
	  		{
	  			i--;
	  			for(int j=0;j<potential.size();j++)
	  				if(potential.get(j).getMilk()==m)
	  				{
	  					potential.remove(j);
	  					j--;
	  				}
	  		}
	  		for(int j=0;j<sick.size();j++)
	  			sickcheck.put(sick.get(j).getPerson(),false);
	  	}
	  	ArrayList <Integer> contaminate=new ArrayList <Integer>();	//Check all contaminated milk
	  	for(int i=0;i<potential.size();i++)
	  		if(!contaminate.contains(potential.get(i).getMilk()))
	  			contaminate.add(potential.get(i).getMilk());
	  	int max=0;
	  	Map <Integer,Boolean> sickcheck2=new HashMap <Integer,Boolean>();
	  	for(int i=0;i<potential.size();i++)
	  		if(!sickcheck2.containsKey(potential.get(i).getPerson()))
	  			sickcheck2.put(potential.get(i).getPerson(),false);
		for(int j=0;j<contaminate.size();j++)
		{	
			int m=contaminate.get(j);
		  	for(int i=0;i<potential.size();i++)
		  	{
		  		if(potential.get(i).getMilk()==m)
		  		{
		  			sickcheck2.put(potential.get(i).getPerson(),true);
		  		}
		  	}
		  	int temp=0;
	  		for(int i=1;i<=N;i++)
	  		{
		  		if(sickcheck2.get(i)==true)
	  			{
	  				temp++;
	  				sickcheck2.put(i,false);
	  			}
	  		}
	  		if(temp>max)
	  			max=temp;
		}
		out.println(max);
		out.close();
	}
}

class pmt
{
	private int person;
	private int milk;	//if this value is -1, that means this pmt object is in the sick people ArrayList
	private int time;
	
	public pmt(int p, int m, int t)
	{
		person=p;
		milk=m;
		time=t;
	}

	public int getPerson()
	{
		return person;
	}

	public int getMilk()
	{
		return milk;
	}
	
	public int getTime()
	{
		return time;
	}
}