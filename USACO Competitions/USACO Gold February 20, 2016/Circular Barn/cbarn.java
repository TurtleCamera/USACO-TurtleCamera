import java.io.*;
import java.util.*;

class cbarn {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("cbarn.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
    	int rooms=Integer.parseInt(file.next());
    	int roomcount=rooms;
    	if(rooms>1)
    	{
    		BarnNode room=new BarnNode(null,null,Integer.parseInt(file.next()));
    		BarnNode initial=room;
    		rooms--;
 			int count=0;
    		while(count<rooms)
    		{
//    			System.out.println(room.getCows());
    			room.setNext(new BarnNode(null,room,Integer.parseInt(file.next())));
    			room=room.getNext();
    			count++;
    		}
    		room.setNext(initial);
    		initial.setBack(room);	//This is when we start traversing and moving cows
//    		System.out.println(initial.getBack().getCows()+" "+initial.getCows()+" "+initial.getNext().getCows());
    		room=initial;
    		rooms=roomcount;
    		count=0;
    		BarnNode longestzeros=room;
    		int longestcount=0;
    		while(count<rooms)
    		{
    			int zeros=0;
    			BarnNode traverse=room;
    			while(traverse.getCows()==0)
    			{
    				traverse=traverse.getNext();
    				zeros++;
    			}
    			if(zeros>=longestcount)
    			{
    				longestcount=zeros;
    				longestzeros=traverse.getBack();
    			}
    			room=room.getNext();
    			count++;
    		}
//    		System.out.println(longestzeros.getBack().getCows()+" "+longestzeros.getNext().getCows());
    		int energy=0;
    		room=longestzeros;
    		count=0;
    		while(count<rooms)
    		{
//    			System.out.println(room.getCows());
    			BarnNode traverse=room;
    			int traversecount=0;
    			while(traverse.getCows()==0)
    			{
//    				System.out.println(traverse.getCows());
    				traverse=traverse.getBack();
    				traversecount++;
    			}
//    			System.out.println("Hi");
    			BarnNode temp=room;
    			while(traversecount>0&&traverse.getCows()>0)
    			{
    				temp.setCows(1);
    				energy+=Math.pow(traversecount,2);
    				traversecount--;
    				traverse.setCows(traverse.getCows()-1);
    				temp=temp.getBack(); 
    			}
    			room=room.getBack();
    			count++;
    		}
    		out.println(energy);
    	}
    	else
    		out.println("0");
		out.close();
  	}
}

class BarnNode
{
	private BarnNode node;
	private BarnNode back;
	private int cows;
	
	public BarnNode(BarnNode b, BarnNode ba, int c)
	{
		node=b;
		back=ba;
		cows=c;
	}
	
	public void setBack(BarnNode b)
	{
		back=b;
	}
	
	public BarnNode getNext()
	{
		return node;
	}
	
	public BarnNode getBack()
	{
		return back;
	}
	
	public int getCows()
	{
		return cows;
	}
	
	public void setCows(int c)
	{
		cows=c;
	}
	
	public void setNext(BarnNode n)
	{
		node=n;
	}
}