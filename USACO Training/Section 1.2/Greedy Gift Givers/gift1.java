/*
ID: zou.all1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;
import java.util.TreeMap;
import java.util.Scanner;

public class gift1 {

    public static void main(String[] args) throws IOException {
    	Scanner file=new Scanner(new File("gift1.in"));
    	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
        int lines=Integer.parseInt(file.nextLine());
        Map <String,Integer> data1=new TreeMap <String,Integer>();	//stores initial money
        Map <String,Integer> data2=new TreeMap <String,Integer>(); 	//stores final money
        int count=0;
        while(count<lines)
        {
        	String temp=file.nextLine();
        	data1.put(temp,0);
        	data2.put(temp,0);
        	count++;
        }
        count=0;
        while(count<lines)
        {
        	String name=file.nextLine();
        	String [] temp=file.nextLine().split(" ");
        	int initial=Integer.parseInt(temp[0]);
        	int people=Integer.parseInt(temp[1]);
        	int count2=0;
        	data1.put(name,initial);
	        if(people!=0)
	        {	
	        	data2.put(name,data2.get(name)+(initial%people));
	        	while(count2<people)
	        	{
	        		String temporary=file.nextLine();
	        		data2.put(temporary,data2.get(temporary)+(initial/people));
	        		count2++;
	        	}
	        }
	        count++;
        }
        count=0;
        Scanner file2=new Scanner(new File("gift1.in"));
        file2.nextLine();
        while(count<lines)
        {
        	String temp=file2.nextLine();
        	out.println(temp+" "+(data2.get(temp)-data1.get(temp)));
        	count++;
        }
        out.close();
    }
}
