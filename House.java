import java.util.ArrayList;
public class House
{
    boolean over17 = false;
    boolean isBust = false;
    boolean hasAce = false;
    ArrayList<String> houseCards = new ArrayList<String>();
    ArrayList<Integer> cardValues = new ArrayList<Integer>();
    public House()
    {
        
    }
    
    /**
     * @param tempDeck Takes in a deck for method to "hit" or draw card from
     * Method draws a random card from the deck given and adds the card to dealers hand, 
     * Checks to find value of card and then adds to array of cardValues
     */
    public void hit(Deck tempDeck)
    {
        String temp = tempDeck.drawCard();
        houseCards.add(temp);
        String temp2 = temp.substring(1);
        int cardValue;
        if(temp2.equalsIgnoreCase("a"))
            cardValue = 11;
        else if(temp2.equalsIgnoreCase("j")||temp2.equalsIgnoreCase("q")||temp2.equalsIgnoreCase("k"))
            cardValue = 10;
        else
            cardValue = Integer.parseInt(temp2);
        cardValues.add(cardValue);
    }
    
    /**
     * Method prints out the house's cards (hand)
     */
    public void showHand()
    {
        System.out.println("The house cards");
        int cardsInHand = houseCards.size();
        for(int x=0; x<cardsInHand;x++) //prints out the player hand
            System.out.print(houseCards.get(x) + " ");
        System.out.println();
    }
    
    /**
     * Method resets all boolean values and clears dealer's hand in preperation for next round
     */
    public void nextHand()
    {
        isBust=false;
        over17=false;
        hasAce=false;
        houseCards.clear();
        cardValues.clear();
    }
    
    /**
     * Method returns hand total
     */
    public int getHand()
    {
        int handTotal=0;
        for(int x=0;x<cardValues.size();x++)
        {
            int temp = cardValues.get(x);
            handTotal+=temp;
        }
        return handTotal;
    }
    
    /**
     * Method checks to see if the house's hand is 17 or higher, controls the house's hitting
     */
    public boolean over17() //stops hitting at 17 or higher
    {
        int temp = getHand();
        if(temp < 17)
            return over17;
        else
        {
            over17=true;
            return over17;
        }
    }
    
    /**
     * Method checks to see if house is bust
     */
    public boolean isBust()
    {
        int temp = getHand();
        if(temp>21)
        {
            if(hasAce()) //if player busts, checks to see if player has ACE
            {
                if(temp > 21) //checks again to see if player is still bust after changing ace
                {
                    isBust = true;
                    return isBust;
                }
                else
                {
                    System.out.println("Your hand total is " + getHand());
                    return isBust;
                }
            }
            else
            {
                isBust = true;
                return isBust;
            }
        }
        else
            return isBust;
    }
    
    /**
     * Checks if the dealer has an ace
     */
     public boolean hasAce()
    {
        for(int x=0;x<houseCards.size();x++)
        {
            int temp = cardValues.get(x);
            if(temp==11)
            {
                cardValues.set(x,1);
                hasAce=true;
                break;
            }
            else
                hasAce=false;
        }
        return hasAce;
    }
}