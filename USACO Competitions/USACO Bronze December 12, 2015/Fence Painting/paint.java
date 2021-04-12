/*
ID: zou.all1
LANG: JAVA
TASK: paint
*/
import java.util.*;
import java.io.*;
public class paint
{
  public static void main (String [] args) throws IOException {
  	Scanner file=new Scanner(new File("paint.in"));
  	PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));
  	int a=Integer.parseInt(file.next());
    int b=Integer.parseInt(file.next());
    int c=Integer.parseInt(file.next());
    int d=Integer.parseInt(file.next());
    double min=Math.min(Math.min(Math.min(a,b),c),d);
    double max=Math.max(Math.max(Math.max(a,b),c),d);
    int count=0;
	double loop=min+0.5;
	while(loop<max)
	{
		if((loop>a&&loop<b)||(loop>c&&loop<d))
			count++;
		loop++;
	}
    out.println(count);
    out.close();
  	}
}