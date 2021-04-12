import java.io.*;
import java.util.*;

class angry {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("angry.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
    	int bales=Integer.parseInt(file.next());
    	int count=0;
    	int [] map=new int[bales];	
    	while(count<bales)	//Adds all coordinates
    	{
    		map[count]=Integer.parseInt(file.next());
    		count++;
    	}
    	for(int i=0;i<map.length;i++)	//Sorts all coordinates from low to high
    	{
    		int low=map[i];
    		int index=i;
    		for(int j=i+1;j<map.length;j++)
    			if(map[j]<low)
    			{
    				low=map[j];
    				index=j;
    			}
    		map[index]=map[i];
    		map[i]=low;
    	}
//    	for(int i=0;i<map.length;i++)
//    		System.out.println(map[i]);
		int max=0;	//Checks all hay bales to answer the question
		for(int i=0;i<map.length;i++)
		{
			int checked=check(map,i,1,1);
			System.out.println("Map value "+map[i]+" Count value "+checked);
			if(max<checked)
				max=checked;
		}	
//		System.out.println(check(map,4,1,1));
		out.println(max);
		out.close();
 	}
 	
 	public static int check(int [] m, int index, int count, int radius)	//recursion method
 	{
 		int low=m[index]-radius;	//lower range of explosion
 		int high=m[index]+radius;	//upper range of explosion
 		int loop=index-1;	//loop index
 		boolean blast=true;	//false if a hay is out of the blast radius
 		int left=index;	//stores index of left most hay in explosion
 		int right=index;//stores index of right most hay in explosion
 		System.out.println(high);
 		while(loop!=-1&&blast)
 		{
 			System.out.println("Test1");
 			if(m[loop]>=low)
 			{
 				count++;
 				left=loop;
 				System.out.println("Test1left");
 			}
 			else
 				blast=false;
 			loop--;
 		}
 		
 		loop=index+1;
 		blast=true;
 		while(loop!=m.length&&blast)
 		{
 			System.out.println("Test2");
 			if(m[loop]<=high)
 			{
 				count++;
 				right=loop;
 				System.out.println("Test2right");
 			}
 			else
 				blast=false;
 			loop++;
 		}
 		System.out.println("Check count "+count);
 		if(left!=index)
 			count+=left(m,left,0,2);
 		if(right!=index)
 			count+=right(m,right,0,2);
 		return count;
 	}
 	
 	public static int left(int [] m, int index, int count, int radius)
 	{
 		boolean blast=true;
 		int x=m[index]-radius;
 		index--;
 		int left=-1;
 		while(index!=-1&&blast)
 		{
 			System.out.println("left recursion");
 			if(m[index]>=x)
 			{
 				count++;
 				left=index;
 			}
 			else
 				blast=false;
 			index--;
 		}
 		System.out.println("Left recursion count "+count);
 		if(index==-1||left==-1)
 			return count;
 		else
 			return left(m,left,count,radius+1);
 	}
 	
 	public static int right(int [] m, int index, int count, int radius)
 	{
 		boolean blast=true;
 		int x=m[index]+radius;
 		index++;
 		int right=-1;
 		while(index!=m.length&&blast)
 		{
 			System.out.println("Right recursion");
 			if(m[index]<=x)
 			{
 				System.out.println(m[index]);
 				count++;
 				right=index;
 			}
 			else
 				blast=false;
 			index++;
 		}
 		System.out.println("Right recursion count "+count);
 		if(index==m.length||right==-1)
 			return count;
 		else
 			return right(m,right,count,radius+1);
 	}
}
