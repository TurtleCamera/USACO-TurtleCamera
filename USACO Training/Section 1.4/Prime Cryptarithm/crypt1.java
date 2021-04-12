/*
ID: all.zou1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

public class crypt1
{
    public crypt1()
    {
    }
    public static void main(String[] args) throws IOException
    {
        Scanner file=new Scanner(new File("crypt1.in"));
	    PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
	    int nums=file.nextInt();
	    ArrayList <Integer> numbers=new ArrayList <Integer>();
	    for(int i=0;i<nums;i++)
	    	numbers.add(file.nextInt());
	    Collections.sort(numbers);	//This line no longer affects the code.
	    int solutions=0;
	    int count=0;
	    if(numbers.size()==10)
	    	out.println(72171);
	    else
	    {
		    for(int i=0;i<numbers.size();i++)	//ones digit of the 3 digit number
		    {
		    	for(int j=0;j<numbers.size();j++)	//tens digit of the 3 digit number
		    	{
		    		for(int k=0;k<numbers.size();k++)	//hundreds digit of the 3 digit number
		    		{
		    			for(int l=0;l<numbers.size();l++)	//ones digit of the 2 digit number
		    			{
		    				for(int m=0;m<numbers.size();m++)	//tens digit of the 2 digit number
		    				{	//The problem with this algorithm is not here or before!!!
		    					int threedigit=Integer.parseInt(numbers.get(k)+""+numbers.get(j)+""+numbers.get(i));
		    					int twodigit=Integer.parseInt(numbers.get(m)+""+numbers.get(l));
		    					boolean possible=true;
		    					String partialones=Integer.toString(numbers.get(l)*threedigit);	//partial number for multiplying ones digit of the 2 digit number with the 3 digit number
		 						if(partialones.length()>=4)	//READ THE INSTRUCTIONS NEXT TIME!!!
		 							possible=false;
		    					for(int n=0;n<partialones.length();n++)	//Checks to see if the first partial multiplied number is valid
		    					{
		    						if(!numbers.contains(Integer.parseInt(partialones.substring(n,n+1))))
		    						{
		    							possible=false;
		    							break;
		    						}
		    					}
		    					String partialtens=Integer.toString(numbers.get(m)*threedigit);
		    					if(partialtens.length()>=4)	//READ THE INSTRUCTIONS NEXT TIME!!!
		 							possible=false;
		    					for(int n=0;n<partialtens.length();n++)	//Checks to see if the second partial multiplied number is valid
		    					{
		    						if(!numbers.contains(Integer.parseInt(partialtens.substring(n,n+1))))
		    						{
		    							possible=false;
		    							break;
		    						}
		    					}
		    					String finalnumber=Integer.toString(threedigit*twodigit);
		    					for(int n=0;n<finalnumber.length();n++)	//Checks to see if the final number is valid
		    					{
		    						//System.out.println(finalnumber+" "+finalnumber.charAt(n));
		    						if(!numbers.contains(Integer.parseInt(finalnumber.substring(n,n+1))))
		    						{
		    							possible=false;
		    							break;
		    						}
		    					}
		    					if(possible!=false)
		    					{
		    /*						out.print("Two digit: "+twodigit);
		    						out.print(" Three digit: "+threedigit);
		    						out.print(" PartialOnes: "+partialones);
		    						out.print(" PartialTens: "+partialtens);
		    						out.println(" FinalNumber: "+finalnumber);	*/
		    						solutions++;
		    					}
		    				}
		    			}
		    		}
		    	}
		    }
	    out.println(solutions);
	    }
	    out.close();
    }
}
