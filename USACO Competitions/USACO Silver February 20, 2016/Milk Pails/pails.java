import java.io.*;
import java.util.*;

class pails
{
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("pails.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
    	int one=Integer.parseInt(file.next());
    	int two=Integer.parseInt(file.next());
    	int operations=Integer.parseInt(file.next());
    	int m=Integer.parseInt(file.next());
    	twopails data=new twopails(one,two,m);
    	out.println(conductoperations(data, operations));
		out.close();
  	}
  	
  	public static int conductoperations(twopails p, int opsleft)
  	{
  		int distance=p.getdistance();
  		if(opsleft!=0)
  		{
	  		if(p.getone()==0&&p.gettwo()==0)
	  		{
	  			twopails recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.fillone();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			recursion.emptyone();
	  			recursion.filltwo();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  		}
	  		else
	  		{
	  			twopails recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.fillone();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			
	  			recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.filltwo();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			
	  			recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.emptyone();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			
	  			recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.emptytwo();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			
	  			recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.pouronetwo();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  			
	  			recursion=new twopails(p.getonemax(),p.gettwomax(),p.getM());
	  			recursion.setone(p.getone());
	  			recursion.settwo(p.gettwo());
	  			recursion.pourtwoone();
	  			distance=Math.min(distance,recursion.getdistance());
	  			distance=Math.min(conductoperations(recursion,opsleft-1),distance);
	  		}
  		}
  		return distance;
  	}
}

class twopails
{
	int pail1;
	int pail2;
	int onemax;
	int twomax;
	int m;
	
	public twopails(int one, int two, int target)
	{
		onemax=one;
		twomax=two;
		pail1=0;
		pail2=0;
		m=target;
	}
	
	public void setone(int o)
	{
		pail1=o;
	}
	
	public void settwo(int t)
	{
		pail2=t;
	}
	
	public int getM()
	{
		return m;
	}
	
	public int getonemax()
	{
		return onemax;
	}
	
	public int gettwomax()
	{
		return twomax;
	}
	
	public void fillone()
	{
		pail1=onemax;
	}
	
	public void filltwo()
	{
		pail2=twomax;
	}
	
	public void emptyone()
	{
		pail1=0;
	}
	
	public void emptytwo()
	{
		pail2=0;
	}
	
	public void pouronetwo()
	{
		int temp=twomax-pail2;
		if(pail1<=temp)
		{
			pail2+=pail1;
			pail1=0;
		}
		else
		{
			pail1-=temp;
			pail2=twomax;
		}
	}
	
	public void pourtwoone()
	{
		int temp=onemax-pail1;
		if(pail2<=temp)
		{
			pail1+=pail2;
			pail2=0;
		}
		else
		{
			pail2-=temp;
			pail1=onemax;
		}
	}
	
	public int getone()
	{
		return pail1;
	}
	
	public int gettwo()
	{
		return pail2;
	}
	
	public int getdistance()
	{
		return Math.abs(m-(pail1+pail2));
	}
}