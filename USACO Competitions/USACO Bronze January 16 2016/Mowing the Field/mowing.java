import java.io.*;
import java.util.*;

class mowing {
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("mowing.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mowing.out")));
    	int lines=Integer.parseInt(file.next());
    	int min=-1;
    	int count=0;
    	int [][] map=new int [2001][2001];
    	int x=1000;
    	int y=1000;
    	int elapsed=1;
    	map[x][y]=elapsed;
    	while(count<lines)
    	{
    		String direction=file.next();
    		int time=Integer.parseInt(file.next());
    		if(direction.equals("N"))
			{
				int steps=0;
				while(steps<time)
				{
					elapsed++;
					System.out.println(map[y-1][x]);
					if(map[y-1][x]!=0)
						if(min==-1)
							min=elapsed-map[y-1][x];
						else
							if(elapsed-map[y-1][x]<min)
								min=elapsed-map[y-1][x];
					map[y-1][x]=elapsed;
					steps++;
					y--;
					System.out.println("N "+min);
				}
			}
    		else if(direction.equals("S"))
			{
				int steps=0;
				while(steps<time)
				{
					elapsed++;
					if(map[y+1][x]!=0)
						if(min==-1)
							min=elapsed-map[y+1][x];
						else
							if(elapsed-map[y+1][x]<min)
								min=elapsed-map[y+1][x];
					map[y+1][x]=elapsed;
					steps++;
					y++;
					System.out.println("S "+min);
				}
			}
			else if(direction.equals("E"))
			{
				int steps=0;
				while(steps<time)
				{
					elapsed++;
					if(map[y][x+1]!=0)
						if(min==-1)
							min=elapsed-map[y][x+1];
						else
							if(elapsed-map[y][x+1]<min)
								min=elapsed-map[y][x+1];
					map[y][x+1]=elapsed;
					x++;
					steps++;
				}
					System.out.println("E "+min);
			}
			else if(direction.equals("W"))
			{
				int steps=0;
				while(steps<time)
				{
					elapsed++;
					if(map[y][x-1]!=0)
						if(min==-1)
							min=elapsed-map[y][x-1];
						else
							if(elapsed-map[x-1][y]<min)
								min=elapsed-map[y][x-1];
					map[y][x-1]=elapsed;
					steps++;
					x--;
					System.out.println("W "+min);
				}
			}
			count++;
    	}
    	out.println(min);
    	out.close();
 	}
}
