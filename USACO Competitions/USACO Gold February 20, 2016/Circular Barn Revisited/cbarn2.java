import java.io.*;
import java.util.*;

class cbarn2 {
	
	private static BarnNode [] sorting;
    private static BarnNode [] tempMergArr;
    private static BarnNode [] keyspots;
    private static int length;
    
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("cbarn2.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
    	int rooms=Integer.parseInt(file.next());
    	int keys=Integer.parseInt(file.next());
    	int roomcount=rooms;
    	sorting=new BarnNode[rooms];
    	if(rooms>1)
    	{
    		BarnNode room=new BarnNode(null,null,Integer.parseInt(file.next()),0);
    		BarnNode initial=room;
    		sorting[0]=room;
    		rooms--;
 			int count=0;
    		while(count<rooms)
    		{
    			room.setNext(new BarnNode(null,room,Integer.parseInt(file.next()),count+1));
    			room=room.getNext();
    			sorting[count+1]=room;
    			count++;
    		}
    		sort(sorting);
/*			highestcounts=new BarnNode[keys];
    		int minus=0;
    		while(minus<keys)
    		{
    			highestcounts[minus]=sorting[sorting.length-1-minus];
    			minus++;
    		}
    		sortID(highestcounts); */		
    		room.setNext(initial);
    		initial.setBack(room);	//This is when we start traversing
    		keyspots=new BarnNode[keys];
    		keyspots[0]=sorting[sorting.length-1];
    		keyspots[0].setpotential(true);
    		int counting=1;
    		if(keys>1)
    		{
	    		while(counting<keys)
	    		{
	    			keyspots[counting]=searchID(keyspots,counting);
	    			counting++;
	    		}
	    		int distance=0;
	    		for(int i=0;i<keyspots.length-1;i++)
    			{
    				if(i==keyspots.length-1)
    				{
    					BarnNode traverse=keyspots[i];
	    				int counting2=0;
	    				while(traverse.getID()!=keyspots[0].getID())
	    				{
	    					distance+=counting2*traverse.getCows();
	    					traverse=traverse.getNext();
	    					counting2++;
	    				}
    				}
    				else
    				{	
	    				BarnNode traverse=keyspots[i];
	    				int counting2=0;
	    				while(traverse.getID()!=keyspots[i+1].getID())
	    				{
	    					distance+=counting2*traverse.getCows();
	    					traverse=traverse.getNext();
	    					counting2++;
	    				}
    				}
    			}
    			out.println(distance);
    		}
    		else
    		{
    			int distance=0;
	    				BarnNode traverse=keyspots[0].getNext();
	    				int counting2=1;
	    				while(traverse.getID()!=keyspots[0].getID())
	    				{
	    					distance+=counting2*traverse.getCows();
	    					traverse=traverse.getNext();
	    					counting2++;
	    				}
    			out.println(distance);
    		}
/*    		int distance=0;
    		if(keys<=1)
    		{
    			int i=highestcounts[0].getID();
    			int counting=1;
    			BarnNode traverse=highestcounts[0].getNext();
    			while(traverse.getID()!=i)
    			{
    				distance+=counting*traverse.getCows();
    				counting++;
    				traverse=traverse.getNext();
    			}
    		}
    		else
    		{
    			for(int i=0;i<highestcounts.length;i++)
    			{
    				if(i==highestcounts.length-1)
    				{
    					BarnNode traverse=highestcounts[i];
	    				int counting=0;
	    				while(traverse.getID()!=highestcounts[0].getID())
	    				{
	    					distance+=counting*traverse.getCows();
	    					traverse=traverse.getNext();
	    					counting++;
	    				}
    				}
    				else
    				{	
	    				BarnNode traverse=highestcounts[i];
	    				int counting=0;
	    				while(traverse.getID()!=highestcounts[i+1].getID())
	    				{
	    					distance+=counting*traverse.getCows();
	    					traverse=traverse.getNext();
	    					counting++;
	    				}
    				}	
    			}
    		}	*/
    	}
    	else
    		out.println("0");
		out.close();
  	}
  	
  	public static BarnNode searchID(BarnNode [] key, int keyamount)
  	{
  		int lowestdistance=-1;
  		BarnNode lowestIDlocation=null;
  		BarnNode temp=key[0].getNext();
  		int count=0;
  		while(temp.getID()!=key[0].getID())
  		{
  			if(!temp.getpotential())
  			{
  				int distance=0;

	  				key[keyamount]=temp;
	  				for(int i=0;i<key.length-1;i++)
	    			{
	    				if(i==key.length-1)
	    				{
	    					BarnNode traverse=key[i];
		    				int counting=0;
		    				while(traverse.getID()!=key[0].getID())
		    				{
		    					distance+=counting*traverse.getCows();
		    					traverse=traverse.getNext();
		    					counting++;
		    				}
	    				}
	    				else
	    				{	
		    				BarnNode traverse=key[i];
		    				int counting=0;
		    				while(traverse.getID()!=key[i+1].getID())
		    				{
		    					distance+=counting*traverse.getCows();
		    					traverse=traverse.getNext();
		    					counting++;
		    				}
	    				}
	    			}
  				
    			if(distance<lowestdistance)
    			{
    				lowestIDlocation=key[keyamount];
    				lowestdistance=distance;
    			}
  			}
  			temp=temp.getNext();
  		}
  		key[keyamount].setpotential(true);
  		return key[keyamount];
  	}
  	
/*  public static void sortID(BarnNode [] inputArr)
  	{
        length = inputArr.length;
        tempMergArr = new BarnNode[length];
        doMergeSortID(0, length - 1);
    }	
    
    private static void doMergeSortID(int lowerIndex, int higherIndex)
    { 
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergePartsID(lowerIndex, middle, higherIndex);
        }
    }
    
    private static void mergePartsID(int lowerIndex, int middle, int higherIndex)
    {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = highestcounts[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i].getID() <= tempMergArr[j].getID()) {
                highestcounts[k] = tempMergArr[i];
                i++;
            } else {
                highestcounts[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            highestcounts[k] = tempMergArr[i];
            k++;
            i++;
        }
 
    }	*/
  	
  	public static void sort(BarnNode [] inputArr)
  	{
        length = inputArr.length;
        tempMergArr = new BarnNode[length];
        doMergeSort(0, length - 1);
    }
    
    private static void doMergeSort(int lowerIndex, int higherIndex)
    { 
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    
    private static void mergeParts(int lowerIndex, int middle, int higherIndex)
    {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = sorting[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i].getCows() <= tempMergArr[j].getCows()) {
                sorting[k] = tempMergArr[i];
                i++;
            } else {
                sorting[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            sorting[k] = tempMergArr[i];
            k++;
            i++;
        }
 
    }
}

class BarnNode
{
	private BarnNode node;
	private BarnNode back;
	private int ID;
	private int cows;
	private boolean potential=false;
	
	public BarnNode(BarnNode b, BarnNode ba, int c, int I)
	{
		node=b;
		back=ba;
		cows=c;
		ID=I;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setpotential(boolean p)
	{
		potential=p;
	}
	
	public boolean getpotential()
	{
		return potential;
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