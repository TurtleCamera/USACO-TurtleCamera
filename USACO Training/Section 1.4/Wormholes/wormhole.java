/*
ID: all.zou1
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;

public class wormhole
{
	static int [] xcoords;
	static int [] ycoords;
	static int holes;
	static int [] pairs;	//This will change a alot. It's used to find all possible pairs during the recursion method.
	static int [] portal_to_the_right;
    
    public wormhole()
    {
    }
    
    public static boolean isCycle()
    {
    	for(int i=1;i<holes+1;i++)
    	{
    		int position=i;	//Starting position (on a portal)
    		for(int j=0;j<holes+1;j++)
    		{
    			position=pairs[position];	//moves the position through the portal into the other side's portal
    			position=portal_to_the_right[position];	//Sets the position to the closest portal directly to the right (is 0 if there is no portal, meaning there is no cycle)
    		}
    		if(position!=0)
    			return true;
    	}
    	return false;
    }
    
    public static int findPairs()
    {
    	int count=0;
		int i;
		for(i=1;i<holes+1;i++)	//Initiating the search for possible pairs
		{
			if(pairs[i]==0)	//This is one of the places that shows why pairs needs to leave index 0 open
				break;	//This is here because we want to find an open spot to make a pair with
		}
		
		if(i>holes)	//Base case if all pairs have been made in the pairs array
		{
			if(isCycle())	//Check each pair to see if it has a possible endless cycle
				return 1;
			return 0;
		}
		for(int j=i+1;j<holes+1;j++)	//Attempts to pair index j's portal to index i's portal
		{
			if(pairs[j]==0)
			{
				pairs[i]=j;
				pairs[j]=i;
				count+=findPairs();	//Once a pair has been made, go back to the beginning to find another pair
				findPairs();
				pairs[i]=0;		//Once all pairs have been made, reset this specific part to find new possible pairs
				pairs[j]=0;
			}
		}
		return count;
    }
    
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("wormhole.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
	    holes=in.nextInt();
	    xcoords=new int[holes+1];	//Must be +1 because you are storing indexes in the pairs array, and it will interfere with some code if the index is 0
	    ycoords=new int[holes+1];
	    pairs=new int[holes+1];
	    for(int i=1;i<holes+1;i++)
	    {
	    	xcoords[i]=in.nextInt();
	    	ycoords[i]=in.nextInt();
	    }
	    portal_to_the_right=new int[holes+1];
	    for(int i=1;i<holes+1;i++)
	    	for(int j=1;j<holes+1;j++)
	    		if(xcoords[j]>xcoords[i]&&ycoords[i]==ycoords[j])	//Checks if the portal is on the right and has the same y coordinate
	    			if(xcoords[j]-xcoords[i]<xcoords[portal_to_the_right[i]]-xcoords[i]||		//Checks if the new portal found on the right has a smaller distance than the previous portal that was found on the right (hence the xcoord[portal_on_the_right] is the previous portal found)
	    				portal_to_the_right[i]==0)	//or checks if there is no portal found yet
	    				portal_to_the_right[i]=j;	//Sets the new portal that's to the the right if one of these conditions is satisfied
	    out.println(findPairs());
	    out.close();
//		System.out.println(findPairs());	//Successful check
    }
}
