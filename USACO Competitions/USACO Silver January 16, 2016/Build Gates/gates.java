import java.io.*;
import java.util.*;

class gates {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("gates.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
    	boolean [][] map=new boolean[4001][4001];
    	int x=2000;
    	int y=2000;
    	map[y][x]=true;
    	String previous="";
    	int lines=Integer.parseInt(file.next());
    	String [] path=file.next().split("");
    	boolean potential=true;
    	int gates=0;
		for(int i=0;i<path.length;i++)
		{
			if(path[i].equals(previous))
				potential=false;
			else
				potential=true;
				
			if(path[i].equals("N"))
			{
				if(map[y-1][x]==false&&map[y-2][x]==true)
					gates++;
				map[y-1][x]=true;
				map[y-2][x]=true;
				y-=2;
				previous="S";
			}
			else if(path[i].equals("S"))
			{
				if(map[y+1][x]==false&&map[y+2][x]==true)
					gates++;
				map[y+1][x]=true;
				map[y+2][x]=true;
				y+=2;
				previous="N";
			}
			else if(path[i].equals("E"))
			{
				if(map[y][x+1]==false&&map[y][x+2]==true)
					gates++;
				map[y][x+1]=true;
				map[y][x+2]=true;
				x+=2;
				previous="W";
			}
			else if(path[i].equals("W"))
			{
				if(map[y][x-1]==false&&map[y][x-2]==true)
					gates++;
				map[y][x-1]=true;
				map[y][x-2]=true;
				x-=2;
				previous="E";
			}
		}
		out.println(gates);
		out.close();
  	}
}