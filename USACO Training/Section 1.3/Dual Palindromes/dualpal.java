/*
ID: zou.all1
LANG: JAVA
TASK: dualpal
*/
import java.util.*;
import java.io.*;
public class dualpal
{
    public static void main (String [] args) throws IOException {
  		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
    	Scanner file=new Scanner(new File("dualpal.in"));
  		//Note that Integer.toString(a, b) converts 'a' (base 10 number) into base 'b'
  		//Integer.valueOf(a, b) converts the number 'a' which is already in base 'b' back to base 10
  		//convert the given value to base 10, then convert it back to its original base
  		int amount=Integer.parseInt(file.next());
  		int start=Integer.parseInt(file.next())+1;
  		int count=0;
  		int check=0;
  		while(count<amount)
  		{
	  		for(int i=2;i<=10;i++)
	  			if(isPalindrome(Integer.toString(start,i)))
	  				check++;
	  		if(check>=2)
	  		{
	  			out.println(start);
	  			count++;
	  		}
	  		check=0;
	  		start++;
  		}
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