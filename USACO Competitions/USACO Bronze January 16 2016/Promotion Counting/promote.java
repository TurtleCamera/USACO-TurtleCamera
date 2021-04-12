import java.io.*;
import java.util.*;
class promote 
{
 	public static void main (String [] args) throws IOException 
 	{
    	Scanner file=new Scanner(new File("promote.in"));
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promote.out")));
    	 	
    	int bb=Integer.parseInt(file.next());			//bronze value
    	int ba=Integer.parseInt(file.next());			//after value
    
    	int sb=Integer.parseInt(file.next());			//silvervalue
    	int sa=Integer.parseInt(file.next());			//after value
    
    	int gb=Integer.parseInt(file.next());			//gold value
    	int ga=Integer.parseInt(file.next());			//after value
    
    	int pb=Integer.parseInt(file.next());			//platinum value
    	int pa=Integer.parseInt(file.next());			//after value
    
    	int newsilver=0;
    	int newgold=0;
    	int newplat=0;
    	
    	System.out.println(bb);
    	System.out.println(ba);
    	System.out.println(sb);
    	System.out.println(sa);
    	System.out.println(gb);
    	System.out.println(ga);
    	System.out.println(pb);
    	System.out.println(pa);
    	
    	int newgroup =(ba+sa+ga+pa)-(bb+sb+gb+pb);
    	bb+=newgroup;
    	
    	System.out.println(newgroup);

		if(gb>ga)
		{
			while(gb>ga&&pb<pa)
			{
				pb++;
				gb--;
				newplat++;
			}
		}
		
		if(sb>sa)
		{
			while(sb>sa&&pb<pa)
			{
				sb--;
				pb++;
				newgold++;
				newplat++;
			}
			
			while(sb>sa&&gb<ga)
			{
				sb--;
				gb++;
				newgold++;
			}
		}
		
		if(bb>ba)
		{
			while(bb>ba&&pb<pa)
			{
				bb--;
				pb++;
				newsilver++;
				newgold++;
				newplat++;
			}
			
			while(bb>ba&&gb<ga)
			{	
				bb--;
				gb++;
				newgold++;
				newsilver++;
			}
			
			while(bb>ba&&sb<sa)
			{	
				bb--;
				sb++;
				newsilver++;
			}
		}
		
		out.println(newsilver);
		out.println(newgold);
		out.println(newplat);
    	out.close();
  	}
}