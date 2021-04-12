/*
ID: zou.all1
LANG: JAVA
TASK: namenum
*/
import java.util.*;
import java.io.*;
public class namenum
{
    static ArrayList <String> possible=new ArrayList <String>();
  	static ArrayList <String> dictionary=new ArrayList <String>();
  	
	public static void main (String [] args) throws IOException {
  		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    	Scanner file=new Scanner(new File("namenum.in"));
  		Scanner fileD=new Scanner(new File("dict.txt"));
  		String number=file.nextLine();
  		int length=number.length();
  		String letter1="";
  		String letter2="";
  		String letter3="";
  		if(number.substring(0,1).equals("2"))
		{
			letter1="A";
			letter2="B";
			letter3="C";
		}
  		else if(number.substring(0,1).equals("3"))
		{
			letter1="D";
			letter2="E";
			letter3="F";
		}
  		else if(number.substring(0,1).equals("4"))
		{
			letter1="G";
			letter2="H";
			letter3="I";
		}
  		else if(number.substring(0,1).equals("5"))
		{
			letter1="J";
			letter2="K";
			letter3="L";
		}
  		else if(number.substring(0,1).equals("6"))
		{
			letter1="M";
			letter2="N";
			letter3="O";
		}
  		else if(number.substring(0,1).equals("7"))
		{
			letter1="P";
			letter2="R";
			letter3="S";
		}
  		else if(number.substring(0,1).equals("8"))
		{
			letter1="T";
			letter2="U";
			letter3="V";
		}
  		else if(number.substring(0,1).equals("9"))
		{
			letter1="W";
			letter2="X";
			letter3="Y";
		}

  		while(fileD.hasNext())
  		{
  			String temp=fileD.nextLine();
  			if((temp.substring(0,1).equals(letter1)||temp.substring(0,1).equals(letter2)||temp.substring(0,1).equals(letter3))&&temp.length()==length)
  				dictionary.add(temp);
  		}
  		
  		if(createNames(number,"").indexOf("1")==-1)
  			out.println("NONE");
  		else
  			for(int i=0;i<possible.size();i++)
  				out.println(possible.get(i));
/*	  	boolean found=false;
	 	if(number.length()<=8)
	  	{
	  		for(int i=0;i<possible.size();i++)
	  			if(binarySearchpossible(possible.get(i))!=-1)
	  			{
	  				out.println(possible.get(i));
	  				found=true;
	  			}
	  	}	*/
/*  	else
  		{
  			for(int i=0;i<dictionary.size();i++)
	  			if(binarySearchdictionary(dictionary.get(i))!=-1)
	  			{
	  				out.println(dictionary.get(i));
	  				found=true;
	  			}
  		}	*/
  		out.close();
	}
	
	public static int binarySearchpossible(String name)
	{
		int low=0;
		int high=dictionary.size()-1;
		while(low<=high)
		{
			int mid=(low+high)/2;
			if(dictionary.get(mid).equals(name))
				return mid;
			else if(dictionary.get(mid).compareTo(name)<0)
				low=mid+1;
			else
				high=mid-1;
		}
		return -1;
	}
	
/*	public static int binarySearchdictionary(String name)
	{
		int low=0;
		int high=possible.size()-1;
		while(low<=high)
		{
			int mid=(low+high)/2;
			if(possible.get(mid).equals(name))
				return mid;
			else if(possible.get(mid).compareTo(name)<0)
				low=mid+1;
			else
				high=mid-1;
		}
		return -1;
	}	*/
	
	public static String createNames(String numbers, String generated)	//Use recursion to generate all possible names
	{
		if(numbers.length()==0)
		{
			
			if(binarySearchpossible(generated)!=-1)
			{
				possible.add(generated);
				return "1";
			}
//			possible.add(generated);
		}
		else if(numbers.substring(0,1).equals("2"))
			return "0"+createNames(numbers.substring(1),generated+"A")+createNames(numbers.substring(1),generated+"B")+createNames(numbers.substring(1),generated+"C");
		else if(numbers.substring(0,1).equals("3"))
			return "0"+createNames(numbers.substring(1),generated+"D")+createNames(numbers.substring(1),generated+"E")+createNames(numbers.substring(1),generated+"F");
		else if(numbers.substring(0,1).equals("4"))
			return "0"+createNames(numbers.substring(1),generated+"G")+createNames(numbers.substring(1),generated+"H")+createNames(numbers.substring(1),generated+"I");
		else if(numbers.substring(0,1).equals("5"))
			return "0"+createNames(numbers.substring(1),generated+"J")+createNames(numbers.substring(1),generated+"K")+createNames(numbers.substring(1),generated+"L");
		else if(numbers.substring(0,1).equals("6"))
			return "0"+createNames(numbers.substring(1),generated+"M")+createNames(numbers.substring(1),generated+"N")+createNames(numbers.substring(1),generated+"O");
		else if(numbers.substring(0,1).equals("7"))
			return "0"+createNames(numbers.substring(1),generated+"P")+createNames(numbers.substring(1),generated+"R")+createNames(numbers.substring(1),generated+"S");
		else if(numbers.substring(0,1).equals("8"))
			return "0"+createNames(numbers.substring(1),generated+"T")+createNames(numbers.substring(1),generated+"U")+createNames(numbers.substring(1),generated+"V");
		else if(numbers.substring(0,1).equals("9"))
			return "0"+createNames(numbers.substring(1),generated+"W")+createNames(numbers.substring(1),generated+"X")+createNames(numbers.substring(1),generated+"Y");
		return "";
	}
}