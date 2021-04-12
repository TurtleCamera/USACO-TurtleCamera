/*
ID: zou.all1
LANG: JAVA
TASK: milk2
*/

import java.util.*;
import java.io.*;

class milk2
{
	private Interval [] array;
    private Interval [] tempMergArr;
    
	public static void main(String [] args) throws IOException
	{
		Scanner file=new Scanner(new File("milk2.in"));
		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		int lines=Integer.parseInt(file.next());
		Interval [] data=new Interval[lines];
		int low=-1;
		int high=-1;
		int count=0;
		if(lines>0)
		{
			data[0]=new Interval(Integer.parseInt(file.next()),Integer.parseInt(file.next()));
			count=1;
		}	
		int milk=0;
		int idle=0;
		while(count<lines)
		{
//			int i=0;
			data[count]=new Interval(Integer.parseInt(file.next()),Integer.parseInt(file.next()));
/*			while(i<data.size()-1)
			{
				if(data.get(i+1).getStart()<data.get(i).getStart())
				{
					Interval temp=data.get(i+1);
					data.set(i+1,data.get(i));
					data.set(i,temp);
				}
				i++;
			}	*/
			count++;
		}
		
		milk2 sorting=new milk2();
		sorting.sort(data);
		
		int loopstart=0;
		if(data.length>0)
		{
			low=data[0].getStart();
			high=data[0].getEnd();
			loopstart=1;
		}
		for(int i=loopstart;i<data.length;i++)
		{
			int templow=data[i].getStart();
			int temphigh=data[i].getEnd();
			if(low<=templow&&templow<=high)
			{
				if(temphigh>high)
					high=temphigh;
			}
			else
			{
				if(milk<high-low)
					milk=high-low;
				if(idle<templow-high)
				{
					idle=templow-high;
				}
				low=templow;
				high=temphigh;
			}
		}
		if(high-low>milk)
			milk=high-low;
		out.println(milk+" "+idle);
		out.close();
	}
	
	public void sort(Interval [] inputArr)
	{
        array = inputArr;
        tempMergArr = new Interval[inputArr.length];
        doMergeSort(0, inputArr.length-1);
    }
	
	private void doMergeSort(int lowerIndex, int higherIndex)
	{
     	if (lowerIndex < higherIndex)
     	{
        	int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
        	doMergeSort(lowerIndex, middle);
        	doMergeSort(middle + 1, higherIndex);
       		mergeParts(lowerIndex, middle, higherIndex);
    	}
    }
    
    private void mergeParts(int lowerIndex, int middle, int higherIndex) 
    { 
        for (int i = lowerIndex; i <= higherIndex; i++)
            tempMergArr[i] = array[i];
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) 
        {
            if (tempMergArr[i].getStart() <= tempMergArr[j].getStart()) 
            {
                array[k] = tempMergArr[i];
                i++;
            } 
            else
            {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle)
        {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
 
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