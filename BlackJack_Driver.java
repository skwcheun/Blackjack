import java.util.Scanner;
public class BlackJack_Driver
{
    public static void main(String [] args)
    {
        System.out.print("\f");
        /*
        System.out.println("WELCOME TO CASINO BLACK JACK");
        System.out.println("Your starting balance is $100. ");
        System.out.println("Everytime you win a hand you get 2x your bet amount and for a loss you lose your bet.");
        System.out.println("The letter in the card is the suit");
        System.out.println("H = hearts, D = diamonds, S = spades, C = clubs");
        System.out.println("The ace is counted with a value of 11 and if you go bust it is changed to 1");
        System.out.println("ADVANCED RULES:");
        System.out.println("Double down means you can bet again up to your original bet in exchange for only hitting one card max");
        System.out.println("If the house's upcard is an Ace, you can insure up to half your original bet which will pay 2:1 if the house gets blackjack");
        //System.out.println("If you get a pair of cards, you have the option of splitting your hand into two seperate hands");
        //System.out.println("Then you will have to place another bet equal to your original on the newly split hand");
        //System.out.println("You can split up to 4 times maximum");
        */
        Scanner input = new Scanner(System.in);
        //System.out.println("Please enter the number of decks");
        //int numDecks = input.nextInt();
        Deck deck1 = new Deck(8);
        Player steven = new Player(100);
        House house1 = new House();
        String playAgain;
        boolean validInput;
        do
        {
           System.out.println("Your current balance is " + steven.getBalance());
           System.out.println("Everytime you win a hand you get 2x your bet amount and for a loss you lose your bet.");
           System.out.println("The letter in the card is the suit");
           System.out.println("H = hearts, D = diamonds, S = spades, C = clubs");
           System.out.println("The ace is counted with a value of 11 and if you go bust it is changed to 1");
           System.out.println("ADVANCED RULES:");
           System.out.println("Double down means you can bet again up to your original bet in exchange for only hitting one card max");
           System.out.println("If the house's upcard is an Ace, you can insure up to half your original bet which will pay 2:1 if the house gets blackjack");
           System.out.println("Surrendering means you stop the current hand and recieve half of your original bet back");
           steven.bet();
           house1.hit(deck1); //house hits
           house1.showHand();
           System.out.println();
           //house1.hit(deck1); //house hits second card but is not shown
           steven.hit(deck1);//hits the two cards at the beginning
           steven.hit(deck1);
           steven.showHand();
           System.out.println("Your hand total is " + steven.getHand());
           if(steven.getHand()==21&&!steven.isBust()) //checks for black jack
           {
               System.out.println("Black Jack! You win!");
               steven.winBlackJack();
               steven.nextHand();
               System.out.println("Press y to play again or n to stop");
               playAgain = input.next();
               while(!playAgain.equalsIgnoreCase("y")&&!playAgain.equalsIgnoreCase("n")) //checking for valid input
               {
                   System.out.println("Not valid input");
                   playAgain = input.next();
               }
               System.out.print("\f");
               continue;
           }
           
           if(house1.getHand()==11) //checks for a Ace in dealers first card to offer insurance
           {
               System.out.println("Would you like to place insurance?");
               System.out.println("Press y for yes or n for no");
               String temp = input.next();
               while(!temp.equalsIgnoreCase("y")&&!temp.equalsIgnoreCase("n")) //checking input
               {
                   System.out.println("Not valid input");
                   temp = input.next();
               }
               if(temp.equalsIgnoreCase("y")) //pays insurance if yes
                    steven.payInsurance();
           }
           System.out.println("Would you like to surrender? Press y for yes or n for no"); //offering surrender
           String chooseToSurrender = input.next();
           while(!chooseToSurrender.equalsIgnoreCase("y")&&!chooseToSurrender.equalsIgnoreCase("n")) //checking input
               {
                   System.out.println("Not valid input");
                   chooseToSurrender = input.next();
               }
           if(chooseToSurrender.equalsIgnoreCase("y")) //if yes, surrenders and prepares for next round
           {
               steven.surrender();
               System.out.println("Press y to play again or n to stop");
               playAgain = input.next();
               while(!playAgain.equalsIgnoreCase("y")&&!playAgain.equalsIgnoreCase("n")) //checking input
               {
                   System.out.println("Not valid input");
                   playAgain = input.next();
               }
               steven.nextHand(); //clearing hands
               house1.nextHand();
               System.out.print("\f"); //clears screen
               continue;
           }
           String chooseToDouble = "";
           if(!(steven.getBalance()==0)) //checks to see if player has sufficient funds to double down
           {
               System.out.println("Would you like to double down? Press y for yes or n for no");
               chooseToDouble = input.next();
               while(!chooseToDouble.equalsIgnoreCase("y")&&!chooseToDouble.equalsIgnoreCase("n")) //checking input
                   {
                       System.out.println("Not valid input");
                       chooseToDouble = input.next();
                   }
           }
           if(chooseToDouble.equalsIgnoreCase("y")) //doubles down
               {
                   steven.doubleDown(deck1);
                   steven.showHand();
                   System.out.println("Your hand total is " + steven.getHand());
                   //validInput = true;
               }
           else
           {           
                    while(!steven.isBust()) //if player isnt busted, chooses to hit or stay
                    {
                        System.out.println("Press y to hit or n to stay");
                        String temp = input.next();
                        while(!temp.equalsIgnoreCase("y")&&!temp.equalsIgnoreCase("n")) //checking input
                        {
                           System.out.println("Not valid input");
                           temp = input.next();
                        }
                        if(temp.equalsIgnoreCase("y")) //hit option to get new card
                        {
                            System.out.println("Player hitting now");
                            steven.hit(deck1);
                            steven.showHand();
                            System.out.println("Your hand total is " + steven.getHand());
                            System.out.println("Would you like to surrender? Press y for yes or n for no"); //offers surrender after each hit
                            chooseToSurrender = input.next();
                            while(!chooseToSurrender.equalsIgnoreCase("y")&&!chooseToSurrender.equalsIgnoreCase("n")) //checking input
                            {
                               System.out.println("Not valid input");
                               chooseToSurrender = input.next();
                            }
                            if(chooseToSurrender.equalsIgnoreCase("y"))
                            {
                               steven.surrender(); //this will set surrendering to true
                               break;
                            }
                        }
                        else if(temp.equalsIgnoreCase("n")) //stay option
                        {
                            System.out.println("You decide to stay with these cards ");
                            steven.showHand();
                            System.out.println("Your hand total is " + steven.getHand());
                            System.out.println();
                            break;
                        }
                   }
                   if(steven.surrendering()) //if the player surrendered earlier, this will end the current round
                   {
                       System.out.println("Press y to play again or n to stop");
                       playAgain = input.next();
                       while(!playAgain.equalsIgnoreCase("y")&&!playAgain.equalsIgnoreCase("n")) //checking input
                       {
                           System.out.println("Not valid input");
                           playAgain = input.next();
                       }
                       steven.nextHand(); //clearing hands
                       house1.nextHand();
                       System.out.print("\f"); //clears screen
                   }
            }
           //}
           if(!steven.isBust()) //if player hasnt already busted, dealer hits cards
           {
               System.out.println("The dealer is flipping cards now");
               house1.hit(deck1);
               if(house1.getHand()==21) //checks for dealer blackjack
               {
                   if(steven.isInsured()) //if dealer hits blackjack, checks to see if player was insured
                   {
                       System.out.println("Nice! You're insured");
                       steven.getInsurance();
                    }
                   System.out.println("Dealer hits blackjack!");
                   steven.nextHand(); //dealer has won, prepares for next round
                   house1.nextHand();
                   System.out.println("Press y to play again or n to stop"); //prompting replay
                   playAgain = input.next();
                   while(!playAgain.equalsIgnoreCase("y")&&!playAgain.equalsIgnoreCase("n")) //checking input
                   {
                       System.out.println("Not valid input");
                       playAgain = input.next();
                   }
                   System.out.print("\f"); //clears screen
                   continue;
                }
               while(!house1.over17()) //dealer has to stop at 17
               {
                   house1.hit(deck1);
               } 
               house1.showHand(); //reveals house's hand
               System.out.println("The dealer's hand total is " + house1.getHand());
               if(house1.isBust()) //if dealer busts you win
               {
                   System.out.println("Dealer busted, you win");
                   steven.winHand();
                   house1.nextHand(); //next hand methods clear hands for next round
                   steven.nextHand();
               } 
               else
               {
                   if(house1.getHand()==21) //dealer 21 beats player 21, prepares for next round
                   {
                       System.out.println("You lose!");
                       steven.nextHand();
                       house1.nextHand();
                   }
                   else if(steven.getHand()>house1.getHand()) //if player has a greater hand than house, prepares for next round
                   {
                       System.out.println("Congrats you win");
                       steven.winHand();
                       steven.nextHand();//next hand methods clear hands for next round
                       house1.nextHand();
                   }
                   else if(steven.getHand()==house1.getHand()) //checking for a tie , prepares for next round
                   {
                       System.out.println("Tie! Take your chips back");
                       steven.tieHand();
                       steven.nextHand();
                       house1.nextHand();
                   }
                   else
                   {
                       System.out.println("You lose"); //prepares for next round
                       steven.nextHand();
                       house1.nextHand();
                   }
               }
           }
           else
           {
               System.out.println("You busted!"); //prepares for next round
               steven.nextHand();
               house1.nextHand();
           }
           if(steven.getBalance()<=0) //if player is bankrupt, ends game
            break;
           System.out.println("Press y to play again or n to stop"); //prompts replay 
           playAgain = input.next();
           while(!playAgain.equalsIgnoreCase("y")&&!playAgain.equalsIgnoreCase("n")) //checking input
           {
               System.out.println("Not valid input");
               playAgain = input.next();
           }
           System.out.print("\f"); //clears screen
           if(deck1.getCardsLeft()<=10)
           {
               deck1 = new Deck(8);
           }
        } while(playAgain.equalsIgnoreCase("y"));
        System.out.println("Game over! Thanks for playing!");
        System.out.println("Made by: Steven Cheung");
    }
}