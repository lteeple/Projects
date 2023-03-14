import java.util.ArrayList;

public class Dealer {
	ArrayList<Card> hand;
	int score;
	boolean bust = false;
	boolean blackJack = false;
	
	Dealer()
	{
		hand = new ArrayList<Card>();
	}
	
	public void calculateScore()
    {
        int handValue = 0;
        for (int i = 0; i < hand.size(); i++)
            handValue += hand.get(i).getRank();
        score = handValue;
    }
	
    public void showScore()
    {
    	calculateScore();
    	System.out.println("The dealer's score is " + score);
    }
    
    public void showFirstCard()
    {
    	System.out.println("The dealer has a " + hand.get(0).getRank() + " of " + hand.get(0).getSuit() + " and ???");
    }
    
    public void showHand()
    {
    	System.out.print("The dealer currently holds: " + hand.get(0).getValue() + " of " + hand.get(0).getSuit());
    	for (int i = 1; i < hand.size(); i++)
			System.out.print(", " + hand.get(i).getValue() + " of " + hand.get(i).getSuit());
    	System.out.println();
    }
    
	public void recieveCard(Card card)
	{
		hand.add(card);
		calculateScore();
	}
	
	public boolean bust()
	{
		if (score > 21)
			bust = true;
		return bust;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public boolean hasBlackJack()
	{
		if (score == 21)
			blackJack = true;
		return blackJack;
	}
}
