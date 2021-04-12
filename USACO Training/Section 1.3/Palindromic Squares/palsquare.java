/*
ID: zou.all1
LANG: JAVA
TASK: palsquare
*/
import java.util.*;
import java.io.*;
public class palsquare
{
    public static void main (String [] args) throws IOException {
  		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    	Scanner file=new Scanner(new File("palsquare.in"));
  		//Note that Integer.toString(a, b) converts 'a' (base 10 number) into base 'b'
  		//Integer.valueOf(a, b) converts the number 'a' which is already in base 'b' back to base 10
  		//convert the given value to base 10, then convert it back to its original base
  		int base=Integer.parseInt(file.nextLine());
  		for(int i=1;i<301;i++)
  			if(isPalindrome(Integer.toString((int)Math.pow(i,2),base)))
  				out.println((Integer.toString(i,base)+" "+Integer.toString((int)Math.pow(i,2),base)).toUpperCase());
  		out.close();
	}
	
	public static boolean isPalindrome(String check)
	{
		int low=0;
		int high=check.length()-1;
		while(low<high)
		{
			if(!check.substring(low,low+1).equals(check.substring(high,high+1)))
				return false;
			low++;
			high--;
		}
		return true;
	}
}