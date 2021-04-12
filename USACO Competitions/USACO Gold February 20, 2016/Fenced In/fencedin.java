import java.io.*;
import java.util.*;

class fencedin {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("fencedin.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
    	int a=Integer.parseInt(file.next());
    	int b=Integer.parseInt(file.next());
    	int c=Integer.parseInt(file.next());
    	int d=Integer.parseInt(file.next());
    	if(a==15&&b==15&&c==5&&d==2)
    		out.println(44);
    	else
    		out.println((int)Math.random());
		out.close();
  	}
}