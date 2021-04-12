import java.io.*;
import java.util.*;
import java.awt.Point;

public class moocast
{
    public moocast()
    {
    }
    public static void main(String[] args) throws IOException
    {
        Scanner in=new Scanner(new File("moocast.in"));
	    PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
	    int lines=Integer.parseInt(in.nextLine());
	    char [] ID=new char[lines];
	    Point [] points=new Point[lines];
	    int count=0;
	    char temp='A';
	    while(count<lines)
	    {
	    	ID[count]=temp;
	    	String [] temppoint=in.nextLine().split(" ");
	    	points[count]=new Point(Integer.parseInt(temppoint[0]),Integer.parseInt(temppoint[1]));
	    	count++;
	    	temp++;
	    }
//	    for(int i=0;i<points.length;i++)
//	    {
//	    	System.out.println(ID[i]+" "+points[i]);
//	    }
	    TreeMap <Double,ArrayList <String>> distances=new TreeMap <Double,ArrayList <String>>();
	    for(int i=0;i<ID.length;i++)
	    {
	    	for(int j=i+1;j<ID.length;j++)
	    	{
	    		double tempdistance=Math.sqrt(Math.pow(points[i].getX()-points[j].getX(),2)+Math.pow(points[i].getY()-points[j].getY(),2));
	    		if(distances.containsKey(tempdistance))
	    			distances.get(tempdistance).add(""+ID[i]+ID[j]+"");
	    		else
	    		{
	    			ArrayList <String> temp2=new ArrayList <String>();
	    			temp2.add(""+ID[i]+ID[j]+"");
	    			distances.put(tempdistance,temp2);
	    		}
	    	}
	    }
//	    for (Map.Entry<Double,ArrayList<String>> entry : distances.entrySet())
//	    {
//	    	System.out.println(entry.getKey()+" "+entry.getValue());
//	    }
	    TreeMap <String,Integer> links=new TreeMap <String,Integer>();
	    ArrayList <Double> selecteddistances=new ArrayList <Double>();	//Stores all distances that are selected as shortest
	    int IDcount=1;
	    for (Map.Entry<Double,ArrayList<String>> entry : distances.entrySet())
		{
			ArrayList <String> list=entry.getValue();
			System.out.println(entry.getKey()+" "+list);
			for(int i=0;i<list.size();i++)
			{
				String pack=list.get(i);
				String ID1=pack.substring(0,1);
				String ID2=pack.substring(1,2);
				double length=entry.getKey();
				if(links.containsKey(ID1)&&links.containsKey(ID2))	//Both points found
				{
					//If the IDs of those 2 points happen to form a loop, don't even create it
					if(links.get(ID1).equals(links.get(ID2)))	//Connects 2 sets together if found
					{
						int firstID=links.get(ID1);
						int secondID=links.get(ID2);
						for (Map.Entry<String,Integer> entry2 : links.entrySet())
						{
							if(links.get(entry2.getKey())==secondID)
								links.put(entry2.getKey(),firstID);
						}
					}
				}
				else if((links.containsKey(ID1)&&!links.containsKey(ID2)))	//If the first element is there but not the other
				{
					links.put(ID2,links.get(ID1));
					selecteddistances.add(length);
				}
				else if((!links.containsKey(ID1)&&links.containsKey(ID2)))	//If the second element is there but not the other
				{
					links.put(ID1,links.get(ID2));
					selecteddistances.add(length);
				}
				else	//If both elements aren't present
				{
					links.put(ID1,IDcount);
					links.put(ID2,IDcount);
					selecteddistances.add(length);
					IDcount++;
				}
			}
/*			if(links.size()==lines-1)
			{
				boolean start=true;
				int tempentry=0;
				for (Map.Entry<String,Integer> entry2 : links.entrySet())
				{
					if(!start)
					{
						if(tempentry!=entry2.getValue())
							break;
					}
					else
					{
						start=false;
						tempentry=entry2.getValue();
					}
				}
				break;
			}	*/
		}
	    for (Map.Entry<String,Integer> entry : links.entrySet())
	    {
	    	System.out.println(entry.getKey()+" "+entry.getValue());
	    }
	    for(int i=0;i<selecteddistances.size();i++)
		{
			System.out.println(selecteddistances.get(i));
		}
		int cost=0;
		for(int i=0;i<selecteddistances.size();i++)
		{
			cost+=Math.round(Math.pow(selecteddistances.get(i),2));
		}
		out.println(cost);
		out.close();
    }
}
