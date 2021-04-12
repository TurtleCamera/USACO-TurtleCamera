/*
ID: all.zou1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.*;

public class barn1
{
    public barn1()
    {
    }
    public static void main(String[] args) throws IOException
    {
        Scanner file=new Scanner(new File("barn1.in"));
	    PrintWriter output=new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
	    int boards=file.nextInt();
	    int totalstalls=file.nextInt();
	    int [] stalls=new int[file.nextInt()];
	    for(int i=0;i<stalls.length;i++)
	    	stalls[i]=file.nextInt();
	    Arrays.sort(stalls);
	    totalstalls-=(stalls[0]-1+(totalstalls-stalls[stalls.length-1]));
	    int [] breakoffs=new int[boards];
	    ArrayList <Integer> emptyspaces=new ArrayList <Integer>();
	    for(int i=0;i<stalls.length-1;i++)
	    {
	    	if(stalls[i+1]-stalls[i]-1!=0)
	    		emptyspaces.add(stalls[i+1]-stalls[i]-1);
	    }
	    Collections.sort(emptyspaces);
	    int count=0;
	    for(int i=1;i<boards&&i<=emptyspaces.size();i++)
 			totalstalls-=emptyspaces.get(emptyspaces.size()-i);
		output.println(totalstalls);
		output.close();
    }
}
