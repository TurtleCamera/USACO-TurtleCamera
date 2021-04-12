/*
ID: zou.all1
LANG: JAVA
TASK: namenum
*/
import java.util.*;
import java.io.*;
public class namenum
{
    static TreeMap <String,String> key=new TreeMap <String,String>();
  	static ArrayList <String> dictionary=new ArrayList <String>();
  	static String number;
  	
	public static void main (String [] args) throws IOException {
  		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    	Scanner file=new Scanner(new File("namenum.in"));
  		Scanner fileD=new Scanner(new File("dict.txt"));
  		number=file.nextLine();
  		int length=number.length();
  		key.put("A","2");
  		key.put("B","2");
  		key.put("C","2");
  		key.put("D","3");
  		key.put("E","3");
  		key.put("F","3");
  		key.put("G","4");
  		key.put("H","4");
  		key.put("I","4");
  		key.put("J","5");
  		key.put("K","5");
  		key.put("L","5");
  		key.put("M","6");
  		key.put("N","6");
  		key.put("O","6");
  		key.put("P","7");
  		key.put("R","7");
  		key.put("S","7");
  		key.put("T","8");
  		key.put("U","8");
  		key.put("V","8");
  		key.put("W","9");
  		key.put("X","9");
  		key.put("Y","9");
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
  			if((temp.substring(0,1).equals(letter1)||temp.substring(0,1).equals(letter2)||temp.substring(0,1).equals(letter3))&&temp.length()==length&&temp.indexOf("Q")==-1&&temp.indexOf("Z")==-1)
  				dictionary.add(temp);
  		}
  		
  		boolean check=false;
  		if(dictionary.size()==0)
  			out.println("NONE");
  		else
	  		for(int i=0;i<dictionary.size();i++)
	  			if(check(dictionary.get(i)))
	  			{
	  				check=true;
	  				out.println(dictionary.get(i));
	  			}
	  			
  		if(!check)
  			out.println("NONE");
  		out.close();
	}
	
	public static boolean check(String d)
	{
		boolean check=true;
		for(int i=0;i<d.length()&&check;i++)
			if(!key.get(d.substring(i,i+1)).equals(number.substring(i,i+1)))
				check=false;
		return check;
	}
}