import java.util.*;
import java.util.Arrays;
import java.io.*;

public class bphoto
{
    public bphoto()
    {
    }
    public static void main(String[] args) throws IOException
    {
    	BufferedReader file = new BufferedReader(new FileReader("bphoto.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
	    int lines=Integer.parseInt(file.readLine());
	    int [] cows=new int[lines];
	    ArrayList <Integer> editleft=new ArrayList <Integer>();
	    ArrayList <Integer> editright=new ArrayList <Integer>();
	    for(int i=0;i<lines;i++)
	    {
	    	int temp=Integer.parseInt(file.readLine());
	    	cows[i]=temp;
	    	editright.add(temp);
	    }
	    int unbalanced=0;
	    for(int i=0;i<lines;i++)
	    {
	    	int right=0;
	    	int left=0;
	    	int current=cows[i];
	    	if(i!=0)
	    	{
	    		editleft.add(current);
	    		Collections.sort(editleft);
	    		Collections.sort(editright);
	    		int indexright=binarySearch(current, editright);
	    		int indexleft=binarySearch(current, editleft);
	    		right=editright.size()-1-indexright;
	    		left=editleft.size()-1-indexleft;
	    		editright.remove(indexright);
	    	}
	    	else
	    	{
	    		Collections.sort(editright);
	    		int index=binarySearch(current, editright);
	    		right=editright.size()-1-index;
	    		left=0;
	    		editleft.add(current);
	    		editright.remove(index);
	    	}
	  //   	int indexleft=binarySearch(cows[i], editleft);
	  //  	int indexright=binarySearch(cows[i], editleft);
	  //  	int right=edit.size()-1-index;
	  //  	int right=edit.size()-1-index;
	  //  	int [] leftcows=copyOfRange(cows,0,i);
	  //  	int [] rightcows=copyOfRange(cows,i,cows.length-1);
	  //  	sort(leftcows);
	  // 	sort(rightcows);
	  //  	int leftindex=binarySearch(leftcows,cows[i]);
	  //  	int rightindex=binarySearch(rightcows,cows[i]);
	  //  	int leftcount=leftcows.length-1-leftindex;
	  //  	int rightcount=rightcows.length-1-rightindex;
	  /* // 	for(int j=0;j<lines;j++)
	    	{
	    		if(j<i)
	    		{
	    			if(cows[j]>current)
	    				left++;
	    		}
	    		else if(j>i)
	    		{
	    			if(cows[j]>current)
	    				right++;
	    		}
	    //	}	*/	
	    	if(right>2*left||left>2*right)
	    		unbalanced++;
	    }
	    out.println(unbalanced);
	    out.close();
    }
    
    public static int binarySearch(int key, ArrayList <Integer> temp)
    {
    	int low=0;
    	int high=temp.size()-1;
    	while(high>=low)
    	{
    		int middle=(low+high)/2;
    		if(temp.get(middle)==key)
    			return middle;
    		if(temp.get(middle)<key)
    			low=middle+1;
    		if(temp.get(middle)>key)
    			high=middle-1;
    	}
    	return -1;
    }
}

/*
import java.util.*;
import java.io.*;

public class bphoto
{
    public bphoto()
    {
    }
    public static void main(String[] args) throws IOException
    {
    	Scanner file = new Scanner(new File("bphoto.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
	    int lines=Integer.parseInt(file.nextLine());
	    int [] cows=new int[lines];
	    for(int i=0;i<lines;i++)
	    {
	    	cows[i]=Integer.parseInt(file.nextLine());
	    }
	    int unbalanced=0;
	    for(int i=0;i<lines;i++)
	    {
	    	int current=cows[i];
	    	int left=0;
	    	int right=0;
	    	for(int j=0;j<lines;j++)
	    	{
	    		if(j<i)
	    		{
	    			if(cows[j]>current)
	    				left++;
	    		}
	    		else if(j>i)
	    		{
	    			if(cows[j]>current)
	    				right++;
	    		}
	    	}
	    	if(right>2*left||left>2*right)
	    		unbalanced++;
	    }
	    out.println(unbalanced);
	    out.close();
    }
}
*/