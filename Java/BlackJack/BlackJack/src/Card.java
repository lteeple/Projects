
public class Card {
    final Suit suit;
    final String value;

    Card(Suit suit ,String value)
    {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() 
    {
        return suit;
    }

    public String getValue()
    {
        return value;
    }

    public int getRank()
    {
        if (value.equals("Ace"))
            return 11;
        char firstLetter = value.charAt(0);
        
        if (!Character.isDigit(firstLetter))
            return 10;
        return Integer.parseInt(value); 

    }
}
