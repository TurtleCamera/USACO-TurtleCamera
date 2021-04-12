/*
ID: zou.all1
LANG: JAVA
TASK: friday
*/
import java.util.*;
import java.io.*;
public class friday
{
  public static void main (String [] args) throws IOException {
    Scanner file = new Scanner(new File("friday.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
    Map<Integer,Integer>data=new HashMap<Integer,Integer>();
    int temp=0;
    date test=new date(Integer.parseInt(file.nextLine()));
    data.put(1,1);	//December 13, 1900 is on a Saturday to start with
    data.put(2,0);
    data.put(3,0);
    data.put(4,0);
    data.put(5,0);
    data.put(6,0);
    data.put(0,0);	//Because 7%7 yeilds 0
    while(temp!=-1)
    {
    	temp=test.increment();
    	if(temp>-1)
    		data.put(temp,data.get(temp)+1);
    		
    }
    out.println(data.get(1)+" "+data.get(2)+" "+data.get(3)+" "+data.get(4)+" "+data.get(5)+" "+data.get(6)+" "+data.get(0));		//0 because 7%7 or multiples of 7 yeilds 0
    out.close();    // close the output file
  }
}

class date
{
	private int month=1;
	private int day=1;	//starts here because it's january and the 13th is on a saturday so line 63 will incrememnt this to 1
	private int year=1900;
	private int endyear;
	
	public date(int end)
	{
		endyear=end+1900-1;
	}
	
	public int increment()
	{
		if(month>12)
		{
			month=1;
			year++;
		}
		
		if(month==2)	//February
		{
			if((year%4==0&&year%100!=0)||year%400==0)
				day++;	//29%7 else	28%7 which does nothing
		}
		else if(month==4||month==6||month==9||month==11)
			day+=2;		//30%7
		else
			day+=3;		//31%7
		month++;
		if(month==13&&year==endyear)
			return -1;
		else
			return day%7;
	}
/*	public int increment()
	{
		if(month==12&&dayn==31)
		{
			month=1;
			dayn=1;
			year++;
			maxday=31;
		}
		else if(dayn==maxday)
		{
			dayn=1;
			month++;
			if(month==2)
				if(year%400==0||(year%100!=0&&year%4==0))
					maxday=29;
				else
					maxday=28;
			else if(month==4||month==6||month==9||month==11)
				maxday=30;
			else
				maxday=31;
		}
		else if(dayn<maxday)
			dayn++;
		
		if(dayw==7)
			dayw=1;
		else
			dayw++;
		
		if(dayn==13)
			return dayw;
		if(month==12&&dayn==31&&year==endyear)
			return -1;
		return 0;	
	}
	*/
}