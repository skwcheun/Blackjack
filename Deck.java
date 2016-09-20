import java.util.Random;
import java.util.ArrayList;
public class Deck
{
    String temp;
    ArrayList<String> allCards = new ArrayList<String>();
    int cardsLeft;
    
    /**
     * @param numDecks This number is the number of decks for the game
     * Method adds every card in a deck * param number
     */
    public Deck(int numDecks)
    {
        cardsLeft = 52*numDecks;
        for(int x=0;x<numDecks;x++) //controls number of decks
        {
            for(int y=2;y<=10;y++) //adds all the number cards
            {
                allCards.add("H" + y);
                allCards.add("D" + y);
                allCards.add("C" + y);
                allCards.add("S" + y);
            }
            allCards.add("HA"); //this manually adds in all royalty cards and aces
            allCards.add("HK");
            allCards.add("HQ");
            allCards.add("HJ");
            allCards.add("DA");
            allCards.add("DK");
            allCards.add("DQ");
            allCards.add("DJ");
            allCards.add("CA");
            allCards.add("CK");
            allCards.add("CQ");
            allCards.add("CJ");
            allCards.add("SA");
            allCards.add("SK");
            allCards.add("SQ");
            allCards.add("SJ");
        }
    }
    
    /**
     * Method uses a random generator to pick a card out of the total card arraylist, removes the card from the list then returns it
     */
    public String drawCard()
    {
        Random gen = new Random(); //generates random number 
        int chooseCard = gen.nextInt(cardsLeft);
        temp = allCards.get(chooseCard);//takes a random card out
        allCards.remove(chooseCard);//removes that card from deck
        cardsLeft-=1;
        return temp;//returns card
    }
    
    /**
     * Method was used for testing, used to print out the deck
     */
    public void printDeck() //prints deck out
    {
        int deckSize = allCards.size();
        for(int x =0;x<deckSize;x++)
        {
            System.out.print(allCards.get(x) + " ");
        }
    }
    
    /**
     * Method returns number of cards left
     */
    public int getCardsLeft()
    {
        return cardsLeft;
    }
}