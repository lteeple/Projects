import java.util.ArrayList;

public class Player {
    int score = 0;
    int balance = 0;
    int winnings = 0;
    int bet;
    String name;
    ArrayList<Card> hand;
    boolean bust = false;
    boolean blackJack = false;
    
    Player(String name)
    {
        hand = new ArrayList<Card>();
        this.name = name;
    }

    public void addToBalance(int bal)
    {
        balance += bal;
    }

    public void recieveCard(Card card)
    {
        hand.add(card);
        calculateScore();
    }
    
    public void showHand()
    {
    	System.out.print("You currently hold: " + hand.get(0).getValue() + " of " + hand.get(0).getSuit());
    	if (hand.size() > 1)
    	{
    		for (int i = 1; i < hand.size(); i++)
    			System.out.print(", " + hand.get(i).getValue() + " of " + hand.get(i).getSuit());
    		System.out.println();
    	} else System.out.println();
    }
    
    public void showScore()
    {
    	calculateScore();
    	System.out.println("Your score is " + score);
    }

    public void calculateScore()
    {
        int handValue = 0;
        for (int i = 0; i < hand.size(); i++)
            handValue += hand.get(i).getRank();
        score = handValue;

    }

    public void setBet(int bet)
    {
        if (bet > balance)
            System.out.println("Your bet cannot excede your balance");
        this.bet = bet;
    }
    
    public void bet()
    {
    	balance = balance - bet;
    }
    
    public void addWinnings()
    {
    	winnings += bet;
    	balance = balance + bet;
    }
    
    public void playerWins()
    {
    	addToBalance(bet * 2);
		System.out.println("YOU WIN $" + (bet * 2) + "!");
		System.out.println("Your new balance is " + balance);
    }
    
    public void playerLoses()
    {
    	System.out.println("The dealer wins :(");
    }

    public int getBalance()
    {
        return balance;
    }

    public int getWinnings()
    {
        return winnings;
    }

    public boolean bust()
    {
        if (score > 21)
        	bust = true;
    	return bust;
    }

    public String getName()
    {
        return name;
    }

    public int getScore()
    {
        return score;
    }
    
    public int getBet()
    {
    	return bet;
    }
    
    public boolean hasBlackJack()
    {
    	if (score == 21)
			blackJack = true;
    	return blackJack;
    }
    
    
}
