/*
ID: zou.all1
LANG: JAVA
TASK: beads
*/
import java.util.*;
import java.io.*;
public class beads
{
  public static void main (String [] args) throws IOException {
    Scanner file = new Scanner(new File("beads.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int amt=Integer.parseInt(file.nextLine());
    String beads=file.nextLine();
    int max=0;
    int count=0;
    if(!((beads.indexOf("r")==-1)||(beads.indexOf("b")==-1)))
    {
	    while(count<amt)
	    {
	    	int cut;
	    	int tempbead=0;
	    	String temp;
	    	beads=beads.substring(1)+beads.charAt(0);
	    	if(beads.substring(0,1).equals("b"))
	    	{
	    		temp=beads.replace("w","b");
	    		tempbead+=temp.indexOf("r");
	    		cut=temp.indexOf("r");
	    	}
	    	else if(beads.substring(0,1).equals("r"))
	    	{
	    		temp=beads.replace("w","r");
	    		tempbead+=temp.indexOf("b");
	    		cut=temp.indexOf("b");
	    	}
	    	else
	    	{
	    		String ref;
	    		if(beads.indexOf("b")-beads.indexOf("r")<0)
	    		{
	    			temp=beads.replace("w","b");
	    			ref="r";	//find first instance that is not blue
	    		}
	    		else
	    		{
	    			temp=beads.replace("w","r");
	    			ref="b";	//find first instance that is not red
	    		}
	    		tempbead+=temp.indexOf(ref);
	    		cut=temp.indexOf(ref);
	    	}
	    	String beads2="";
	    	String beads3=beads.substring(cut);
	    	for(int i=beads3.length()-1;i>=0;i--)
	    		beads2+=beads3.charAt(i);
	    	if(beads2.indexOf("b")==-1||beads2.indexOf("r")==-1)
	    	{
	    		tempbead+=beads2.length();
	    	}
	    	else if(beads2.substring(0,1).equals("b"))
	    	{
	    		temp=beads2.replace("w","b");
	    		tempbead+=temp.indexOf("r");
	    	}
	    	else if(beads2.substring(0,1).equals("r"))
	    	{
	    		temp=beads2.replace("w","r");
	    		tempbead+=temp.indexOf("b");
	    	}
	    	else
	    	{
	    		String ref;
	    		if(beads2.indexOf("b")-beads2.indexOf("r")<0)
	    		{
	    			temp=beads2.replace("w","b");
	    			ref="r";	//find first instance that is not blue
	    		}
	    		else
	    		{
	    			temp=beads2.replace("w","r");
	    			ref="b";	//find first instance that is not red
	    		}
	    		tempbead+=temp.indexOf(ref);
	    	}
	    	if(tempbead>max)
	    		max=tempbead;
	    	count++;
	    }
    out.println(max);
    }
    else
    	out.println(beads.length());
    out.close();
  }
}