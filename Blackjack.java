import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class blackjack
{
    public static void main (String[] args) throws IOException
    {
        String Player1;
       
        int P1sum = 0;
        int P2sum = 0;
        String type;
        int cardNum = 0;
        String call = "stay";
        int num1 =0;      
        String call2 = null;
        String ace;
        ArrayList<Integer>  Cards = new ArrayList<Integer>();
                
        System.out.println ("Enter your name.");
        Player1 = getString ();        
                
        System.out.println ();
        
        Cards = shuffle();
        int count = 0;
                
        while(true)
        {  
          cardNum = Cards.get(count);
        	Cards.remove(count);            
            type = type (cardNum);  
            count++;
            
        	displayPlayer1(cardNum, Player1, type);
        	num1 = cardNum % 13;        	
        	if(num1 == 11 || num1 == 12 || num1 == 0)
        	{
        		num1 = 10;
        	}
        	else if(num1 == 1)
        	{
        		System.out.println("1 or 11?");
        		ace = getString();
        		if(ace.equals("1"))
        		{
        			num1 = 1;
        		}
        		else if (ace.equals("11"))
        		{
        			num1 = 11;
        		}
        		else
        		{
        			System.out.println("Your answer is undefinied.");
        		}
        	}
        	
        	P1sum = P1sum + num1;
        	call = call(Player1, P1sum);
        	call2 = call2(P2sum);
        	        	       	
        	cardNum = Cards.get(count);
        	Cards.remove(count);
            count++;
        	P2sum = Player2sum(cardNum);  
        	
        	if(call.equals("stay") && call2.equals("stay"))
        	{        		
        		
        		Result(Player1, P2sum, P1sum);
        		break;
        	}
        	else if(call.equals("stay") && !call2.equals("stay"))
        	{
        		
        	}
        }   
    }   
    
    public static int card ()
    {
    	int card = 0;
        Random generator = new Random ();
        card = generator.nextInt (52) + 1;
        return card;
    }

    public static String call(String player, int sum) throws IOException
    {
    	String call;
    	if(sum > 21)
    	{
    		System.out.println("Computer has won the game." + player + " your score is over 21.");      		
    		System.out.println(player + ": " + sum);
    		return "stay";
    	}
    	else if(sum == 21)
    	{
    		System.out.println(player + " has won the game.");
    		System.out.println(player + ": " + sum);
    		return "stay";
    	}
    	else
    	{
    	System.out.println(player + ": Your total sum is: "+ sum);
    	System.out.println("Stay or Hit?");
    	call = getString().toLowerCase();
    	return call;
    	}
    }
    
    public static String call2(int sum) 
    {
    	if(sum < 18)
    	{
    		return "hit";
    	}
    	else
    	{
    		return "stay";
    	}
    }
    
    public static void displayPlayer1 (int cardNum, String Player1, String type) 
    {
    	int div = cardNum % 13;

		String name = name(cardNum);
    	
    	if(div ==1 || div == 0 || div== 11 || div== 13 || div == 12)
    	{    		
    		System.out.println(Player1 +": " + name + " of " + type);
    	}
    	else
    	{    		
    		System.out.println(Player1 +": " + div + " of " + type);
    	}    	
    	
    }
    
    
    public static void Result(String Player1, int P2sum, int P1sum)
    {      	
    	if(P1sum > 21 || P2sum == 21 || P2sum > P1sum)
    	{
    		System.out.println("Computer has won the game.");
    		System.out.println("Computer:" + P2sum);
    		System.out.println(Player1 + ": " + P1sum);    		        		
    	}    
    	else if(P2sum > 21 && P2sum > P1sum )
    	{
    		System.out.println(Player1 + " has won the game.");
    		System.out.println("Computer:" + P2sum);
    		System.out.println(Player1 + ": " + P1sum);   
    	}
    	else if(P1sum > 21 && P1sum > P2sum )
    	{
    		System.out.println("Computer has won the game.");
    		System.out.println("Computer:" + P2sum);
    		System.out.println(Player1 + ": " + P1sum);    		
    	}
    	else if(P2sum > 21 || P1sum > P2sum || P1sum == 21)
    	{
    		System.out.println(Player1 + " has won the game.");
    		System.out.println("Computer:" + P2sum);
    		System.out.println(Player1 + ": " + P1sum);    		        		
    	} 
    	else
    	{
    		System.out.println("Game is a draw.");
    		System.out.println("Computer:" + P2sum);
    		System.out.println(Player1 + ": " + P1sum);    	
    	}    	
    }
    
    public static int Player2sum(int cardNum)
    {
    	String call;
    	int P2sum = 0;
    	int num2;
    	
    	do 
    	{ 
    	num2 = cardNum % 13;
    	if(num2 == 11 || num2 == 12 || num2 == 0)
    	{
    		num2 = 10;
    	}
    	P2sum = P2sum + num2;
    	call = call2(P2sum);       	       	
    	if(P2sum >= 21)
    	{
    		break;
    	}	    	
    	}while((call.equals("hit"))); 
    	
    	return P2sum;
    }
    
    public static ArrayList<Integer> shuffle ()
    {
    	int num = 0;
    	ArrayList <Integer>  cards = new ArrayList <Integer>();
    	int tmp = 0;
		do
		{
			tmp = card();
			if(!cards.contains(tmp))
			{
				cards.add(tmp);
				num++;
			}
		}while(num < 52);
    	
    	return cards;    	   	
    }
    
    
    public static String type (int num)
    {
        int cardNum = (int) num / 13;
        String type = null;
        String[] type1 = new String [4];
        type1 [0] = "Spades";
        type1 [1] = "Hearts";
        type1 [2] = "Clubs";
        type1 [3] = "Diamonds";

        type = type1 [cardNum];

        return type;
    }

    public static String name (int num)
    {
        String name = null;
        int div = num % 13;        
        if(div == 0)
        {
        	name = "King";
        }
        else if (div == 1)
        {
            name = "Ace";
        }
        else if (div == 11)
        {
            name = "Jack";
        }
        else if (div == 12)
        {
            name = "Queen";
        }
        return name;
    }

    public static String getString () throws IOException
    {
        InputStreamReader isr = new InputStreamReader (System.in);
        BufferedReader br = new BufferedReader (isr);
        String s = br.readLine ();
        return s;
    }
}
