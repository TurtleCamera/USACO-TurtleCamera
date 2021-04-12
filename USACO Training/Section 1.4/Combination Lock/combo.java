/*
ID: all.zou1
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

public class combo
{
    public combo()
    {
    }
    
    public static void removeDuplicate(ArrayList <String> a)
    {
    	for(int i=0;i<a.size();i++)
    	{
    		for(int j=i+1;j<a.size();j++)
    			if(a.get(i).equals(a.get(j)))
    			{
    				a.remove(j);
    				j--;
    			}
    	}
    }
    
    public static void main(String[] args) throws IOException
    {
        Scanner in=new Scanner(new File("combo.in"));
	    PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
	    int total=in.nextInt();
	    ArrayList <Integer> farmercombo=new ArrayList <Integer>();
	    ArrayList <Integer> mastercombo=new ArrayList <Integer>();
	    for(int i=0;i<3;i++)
	    	farmercombo.add(in.nextInt());
	    for(int i=0;i<3;i++)
	    	mastercombo.add(in.nextInt()); 
	    ArrayList <Integer> numbers=new ArrayList <Integer>();	//On a seperate sheet of paper, I had to extend the arraylist by 4 everytime to make sure the first 2 and last 2 numbers are always counted.
	    for(int i=1;i<=total;i++)
	    	numbers.add(i);
	    for(int i=0;i<4;i++)	//The for loop that extends the arraylist.
	    	numbers.add(numbers.size(),numbers.get(i));
	    System.out.println(numbers.toString());
	    ArrayList <Integer> farmer1=new ArrayList <Integer>(); //Represents all the possible digits for the first digit in farmer's combo
	    ArrayList <Integer> farmer2=new ArrayList <Integer>(); //Represents all the possible digits for the second digit in farmer's combo
	    ArrayList <Integer> farmer3=new ArrayList <Integer>(); //Represents all the possible digits for the third digit in farmer's combo
	    ArrayList <Integer> master1=new ArrayList <Integer>(); //Represents all the possible digits for the first digit in master's combo
	    ArrayList <Integer> master2=new ArrayList <Integer>(); //Represents all the possible digits for the second digit in master's combo
	    ArrayList <Integer> master3=new ArrayList <Integer>(); //Represents all the possible digits for the third digit in master's combo
	    for(int i=2;i<numbers.size()-2;i++)
	    {
	    	if(numbers.get(i)==farmercombo.get(0)&&farmer1.size()==0)
	    	{
	    		farmer1.add(numbers.get(i-2));
	    		farmer1.add(numbers.get(i-1));
	    		farmer1.add(numbers.get(i));
	    		farmer1.add(numbers.get(i+1));
	    		farmer1.add(numbers.get(i+2));
	    	}
	    	if(numbers.get(i)==farmercombo.get(1)&&farmer2.size()==0)
	    	{
	    		farmer2.add(numbers.get(i-2));
	    		farmer2.add(numbers.get(i-1));
	    		farmer2.add(numbers.get(i));
	    		farmer2.add(numbers.get(i+1));
	    		farmer2.add(numbers.get(i+2));
	    	}
	    	if(numbers.get(i)==farmercombo.get(2)&&farmer3.size()==0)
	    	{
	    		farmer3.add(numbers.get(i-2));
	    		farmer3.add(numbers.get(i-1));
	    		farmer3.add(numbers.get(i));
	    		farmer3.add(numbers.get(i+1));
	    		farmer3.add(numbers.get(i+2));
	    	}
	    	if(numbers.get(i)==mastercombo.get(0)&&master1.size()==0)
	    	{
	    		master1.add(numbers.get(i-2));
	    		master1.add(numbers.get(i-1));
	    		master1.add(numbers.get(i));
	    		master1.add(numbers.get(i+1));
	    		master1.add(numbers.get(i+2));
	    	}
	    	if(numbers.get(i)==mastercombo.get(1)&&master2.size()==0)
	    	{
	    		master2.add(numbers.get(i-2));
	    		master2.add(numbers.get(i-1));
	    		master2.add(numbers.get(i));
	    		master2.add(numbers.get(i+1));
	    		master2.add(numbers.get(i+2));
	    	}
	    	if(numbers.get(i)==mastercombo.get(2)&&master3.size()==0)
	    	{
	    		master3.add(numbers.get(i-2));
	    		master3.add(numbers.get(i-1));
	    		master3.add(numbers.get(i));
	    		master3.add(numbers.get(i+1));
	    		master3.add(numbers.get(i+2));
	    	}
/*	    	if(numbers.get(i)>=farmercombo.get(0)-2&&numbers.get(i)<=farmercombo.get(0)+2)	//Checks if the numbers is within 2 of the digit
	    		farmer1.add(numbers.get(i));
	    	if(numbers.get(i)>=farmercombo.get(1)-2&&numbers.get(i)<=farmercombo.get(1)+2)	//This algorithm misses numbers outside of the range, like 49 and 50
	    		farmer2.add(numbers.get(i));
	    	if(numbers.get(i)>=farmercombo.get(2)-2&&numbers.get(i)<=farmercombo.get(2)+2)
	    		farmer3.add(numbers.get(i));
	    	if(numbers.get(i)>=mastercombo.get(0)-2&&numbers.get(i)<=mastercombo.get(0)+2)
	    		master1.add(numbers.get(i));
	    	if(numbers.get(i)>=mastercombo.get(1)-2&&numbers.get(i)<=mastercombo.get(1)+2)
	    		master2.add(numbers.get(i));
	    	if(numbers.get(i)>=mastercombo.get(2)-2&&numbers.get(i)<=mastercombo.get(2)+2)
	    		master3.add(numbers.get(i));	*/
	    }
	    ArrayList <String> farmerpossible=new ArrayList <String>();	//Holds all possible combinations (all digits are combined into one string) for farmer's lock
	    ArrayList <String> masterpossible=new ArrayList <String>();	//Holds all possible combinations (all digits are combined into one string) for masters's lock
/*	    System.out.println(farmer1.toString());
	    System.out.println(farmer2.toString());
	    System.out.println(farmer3.toString());
	    System.out.println(master1.toString());
	    System.out.println(master2.toString());
	    System.out.println(master3.toString());	*/
	    for(int i=0;i<farmer1.size();i++)
	    	for(int j=0;j<farmer2.size();j++)
	    		for(int k=0;k<farmer3.size();k++)
	    			farmerpossible.add(farmer1.get(i)+" "+farmer2.get(j)+" "+farmer3.get(k));	//Generates all lock combinations for the farmer's lock
	    for(int i=0;i<master1.size();i++)
	    	for(int j=0;j<master2.size();j++)
	    		for(int k=0;k<master3.size();k++)
	    			masterpossible.add(master1.get(i)+" "+master2.get(j)+" "+master3.get(k));	//Generates all lock combinations for the master's lock
		removeDuplicate(farmerpossible);
		removeDuplicate(masterpossible);
	    for(int i=0;i<farmerpossible.size();i++)
	    	if(masterpossible.indexOf(farmerpossible.get(i))!=-1)
	    		masterpossible.remove(masterpossible.indexOf(farmerpossible.get(i)));
	    for(int i=0;i<masterpossible.size();i++)
	    	if(farmerpossible.indexOf(masterpossible.get(i))!=-1)
	    		farmerpossible.remove(farmerpossible.indexOf(masterpossible.get(i)));
	    out.println(masterpossible.size()+farmerpossible.size());
	    out.close();
    }
}
