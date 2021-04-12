/*
ID: zou.all1
LANG: JAVA
TASK: transform
*/
import java.util.*;
import java.io.*;
public class transform
{
	public static void main (String [] args) throws IOException {
    	Scanner file=new Scanner(new File("transform.in"));
  		PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
/*  	String [] [] array={{"@","-","@"},{"-","-","-"},{"@","@","-"}};
  		String [] [] array2={{"@","-","@"},{"-","-","-"},{"@","@","@"}};
  		array2[2][2]="-";	*/
  		int lines=Integer.parseInt(file.nextLine());
  		String [] [] array=new String[lines] [lines];	//Original
  		int count=0;
  		while(count<lines)
  		{
  			array[count]=file.nextLine().split("");
  			count++;
  		}
  		String [] [] match=new String[lines] [lines];	//The matrix to be checked
  		count=0;
  		while(count<lines)
  		{
  			match[count]=file.nextLine().split("");
  			count++;
  		}
  		
  		String [] [] array1=new String[lines] [lines];	//Option 1
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array1[j][array.length-1-i]=array[i][j];
  		
  		String [] [] array2=new String[lines] [lines];	//Option 2
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array2[j][array.length-1-i]=array1[i][j];
  			
  		String [] [] array3=new String[lines] [lines];	//Option 3
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array3[j][array.length-1-i]=array2[i][j];
  		
  		String [] [] array4=new String[lines] [lines];	//Option 4
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array4[i][array.length-1-j]=array[i][j];
  		
  		String [] [] array41=new String[lines] [lines];	//Option 5, 1 and 4
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array41[j][array.length-1-i]=array4[i][j];
  		
  		String [] [] array42=new String[lines] [lines];	//Option 5, 2 and 4
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array42[j][array.length-1-i]=array41[i][j];
  			
  		String [] [] array43=new String[lines] [lines];	//Option 5, 3 and 4
  		for(int i=0;i<array.length;i++)
  			for(int j=0;j<array.length;j++)
  				array43[j][array.length-1-i]=array42[i][j];
  			
   		if(isEqual(match,array2))
  			out.println("2");
  		else if(isEqual(array,match))
  			out.println("6");
  		else if(isEqual(match,array1))
  			out.println("1");
  		else if(isEqual(match,array3))
  			out.println("3");
  		else if(isEqual(match,array4))
  			out.println("4");
  		else if(isEqual(match,array41))
  			out.println("5");
  		else if(isEqual(match,array42))
  			out.println("5");
  		else if(isEqual(match,array43))
  			out.println("5");
  		else
  			out.println("7");
  		out.close();
	}
	
	public static boolean isEqual(String [][] a, String [][] b)
	{
		boolean check=true;
		for(int i=0;i<a.length&&check;i++)
			for(int j=0;j<a[0].length&&check;j++)
				if(!(a[i][j].equals(b[i][j])))
					check=false;
		return check;
	}
}