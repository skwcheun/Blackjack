import java.util.Scanner;
import java.util.ArrayList;
public class Player
{
    boolean isBust = false;
    //boolean win = false;
    boolean hasAce = false;
    boolean lose = false;
    boolean insured = false;
    boolean surrender = false;
    double balance;
    double currentBet;
    double insuranceAmount;

    Scanner input = new Scanner(System.in);
    ArrayList<String> playerHand = new ArrayList<String>();
    ArrayList<Integer> cardValues = new ArrayList<Integer>();
    /**
     * Constructs player objects with a starting balance
     */
    public Player(int startBalance)
    {
        balance = startBalance;
    }
    
    /**
     * Prompts user input to bet for current hand, only allows bets that are not greater than balance (player cannot bet more than their balance)
     */
    public void bet()
    {
        System.out.println("Please enter your bet");
        String temp = input.next();
        boolean validInput = false;
        String validNum = checkInput(temp);
        double tempDouble = Double.parseDouble(validNum);
        if(tempDouble > balance) //checks to see if player is betting too much
        {
            while(tempDouble > balance) // doesnt let player bet until a valid amount
            {
                System.out.println("Sorry, you do not have enough left in your balance");
                System.out.println("Please bet again");
                temp = input.next();
                validNum = checkInput(temp);
                tempDouble = Double.parseDouble(validNum);
            }
        }
        balance-=tempDouble;
        currentBet = tempDouble;
    }
    
    /**
     * @param tempDeck Takes in a deck for method to "hit" or draw card from
     * Method draws a random card from the deck given and adds the card to players hand, 
     * Checks to find value of card and then adds to array of cardValues
     */
    public void hit(Deck tempDeck)
    {
        String temp = tempDeck.drawCard(); //stores the random card in a temp
        playerHand.add(temp); //adds the temp into player hand
        String temp2 = temp.substring(1); //cuts the string after the first (removes the suite)
        int cardValue;
        if(temp2.equalsIgnoreCase("a")) //gives aces a value of 11 originally
            cardValue = 11;
        else if(temp2.equalsIgnoreCase("j")||temp2.equalsIgnoreCase("q")||temp2.equalsIgnoreCase("k")) //checks for royalty cards
            cardValue = 10;
        else 
            cardValue = Integer.parseInt(temp2); //assigns cards the face value
        cardValues.add(cardValue); //adds the value into card Value array
    }
    
    /**
     * @param tempDeck Takes in a random deck for the method to draw a card from
     * Method allows user to add a bet up to the original bet (provided they have sufficient funds)
     * Adding this additional bet is made in exchange for hitting one card only
     */
    public void doubleDown(Deck tempDeck)
    {
        System.out.println("You chose to doubledown!");
        System.out.println("Please enter a bet up to your original bet");
        String temp = input.next();
        String validNum = checkInput(temp);
        double tempDouble = Double.parseDouble(validNum);
        while(tempDouble>currentBet||tempDouble>balance)
        {
            System.out.println("Invalid bet! Please try again");
            temp = input.next();
            validNum = checkInput(temp);
            tempDouble = Double.parseDouble(validNum);
        }
        currentBet+=tempDouble;
        balance-=tempDouble;
        hit(tempDeck);
    }
    
    public void split() //IN PROGRESS
    {
        ArrayList<String>tempHand = new ArrayList<String>();
        ArrayList<Integer>tempValues = new ArrayList<Integer>();
        ArrayList<String>tempHand2 = new ArrayList<String>();
        ArrayList<Integer>tempValues2 = new ArrayList<Integer>();
        tempHand.add(playerHand.get(0));
        tempValues.add(cardValues.get(0));
        tempHand2.add(playerHand.get(1));

        
        
    }
    
    /**
     * If the player chooses to "insure" themselves, this controls the insurance the user places, up to a max of half the bet (provided they have sufficient funds)
     * Gives the player an "insured" status which is checked later if dealer hits blackjack
     */
    public void payInsurance()
    {
        System.out.println("You chose to pay for insurance!");
        System.out.println("Please enter a bet up to half your original bet");
        String temp = input.next();
        String validNum = checkInput(temp);
        double tempInt = Integer.parseInt(validNum);
        double halfBet=currentBet/2;
        while(tempInt>halfBet||tempInt>balance)
        {
            System.out.println("Invalid bet! Please try again");
            temp = input.next();
            validNum = checkInput(temp);
            tempInt = Integer.parseInt(validNum);
        }
        insuranceAmount+=tempInt;
        insured = true;
        balance-=tempInt;
    }
    
    public void surrender()
    {
        System.out.println("You chose to surrender");
        balance+=currentBet/2;
        surrender = true;
    }
    
    /**
     * Method resets all boolean's and clears arrays in preparation of next hand
     */
    public void nextHand() //resets everything for next hand
    {
        isBust=false;
        //win=false;
        insured = false;
        surrender = false;
        currentBet=0;
        playerHand.clear(); //these clear arrays
        cardValues.clear();
    }
    
    /**
     * Method prints out the player's hand (array)
     */
    public void showHand()
    {
        System.out.println("These are your cards");
        int cardsInHand = playerHand.size();
        for(int x=0; x<cardsInHand;x++) //prints out the player hand
            System.out.print(playerHand.get(x) + " ");
        System.out.println();
    }
    
    /**
     * Method is used to check for valid input
     */
    public String checkInput(String temp)
    {
        boolean validInput = false;
        while(!validInput)
        {
            if(temp.isEmpty())
            {
                System.out.println("Not valid input, try again");
                temp = input.next();
                continue;
            }
            for(int x=0;x<temp.length();x++)
            {
                char tempChar = temp.charAt(x);
                if(!Character.isDigit(tempChar) && tempChar != '.')
                {
                    validInput = false;
                    break;
                }
                validInput = true;
            }
            if(!validInput)
            {
                System.out.println("Not valid input, try again");
                temp = input.next();
            }
        }
        return temp;
    }
    
    /**
     * Method adds twice the bet to players hand for winning (returns bet + profit equal to bet)
     */
    public void winHand() //winning a hand adds double your bet
    {
        balance+=currentBet*2;
    }
    
    /**
     * Method adds 2.5 the bet to players hand for hitting black jack (returns bet + profit equal to 1.5*bet)
     */
    public void winBlackJack()
    {
        balance+=currentBet*2.5;
    }
   
    /**
    * Method returns bet to player in a tie situation
    */
    public void tieHand()
    {
        balance+=currentBet;
    }
    
     /**
      * Method returns insurance to player if dealer hits blackJack and player is insured
      */
     public void getInsurance()
     {
         balance+=2*insuranceAmount;
     }
    
    /**
     * Method returns if player is insured
     */
    public boolean isInsured()
    {
        return insured;
    }
    
    /**
     * Method returns if player is surrendering
     */
    public boolean surrendering()
    {
        return surrender;
    }
    
    /**
     * Method returns the total hand value of player
     */
    public int getHand()
    {
        int handTotal=0;
        for(int x=0;x<cardValues.size();x++)
        {
            int temp = cardValues.get(x);
            handTotal+=temp; //adds up all values in cardValues array
        }
        return handTotal; //returns total
    }
    
    /**
     * Method returns player's balance
     */
    public double getBalance()
    {
        return balance;
    }
    
    /*
    public boolean lose(int balance)
    {
        if(balance==0)
        {    
            lose = true;
            return lose;
        }
        else 
        {
            return lose;
        }
    }
    */
    
    /**
    * Method checks to see if player is bust (hand over 21)
    * If player is bust, also checks to see if player has ace and then rechecks
    */
    public boolean isBust()
    {
        int temp = getHand();
        if(temp>21) //ACE IS STILL IN PROGRESS!!!
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
     * Method checks to see if player has an ace, the first ace found will change the value to 1 and return hasAce as true
     * This method is only used when the player has busted
     */
    public boolean hasAce()
    {
        for(int x=0;x<cardValues.size();x++) //checks through cardValues
        {
            int temp = cardValues.get(x);
            if(temp==11) //if a card is a value of 11 (ace)
                {
                    cardValues.set(x,1); //changes value to 1
                    hasAce=true;
                    break;
                }
            else
                hasAce=false;
        }
        return hasAce;
    }
}
    
        
