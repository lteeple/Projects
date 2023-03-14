import java.util.Collections;
import java.util.Stack;

public class Deck {
    Stack<Card> cards; 

    Deck() 
    {
        this.cards = new Stack<Card>();

        for (Suit suit : Suit.values())
        {
            for (int i = 2; i < 10; i++)
            {
                cards.add(new Card(suit, Integer.toString(i)));
            }
            cards.add(new Card(suit, "Ace"));
            cards.add(new Card(suit, "King"));
            cards.add(new Card(suit, "Queen"));
            cards.add(new Card(suit, "Jack"));    
        }
        Collections.shuffle(cards);
    }
    
    public Card giveCard()
    {
    	return cards.pop();
    }
}
