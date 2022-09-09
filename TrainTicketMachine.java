import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

public class TrainTicketMachine {
 public static void main(String args[]) {
  Scanner input = new Scanner(System.in);
  double[] jerseyPrices = { 20.90, 17.75, 15.00 };
  double[] nyPrices = { 22.50, 19.75, 17.50 };
  int[] ticketCount = { 10, 10, 10, 10, 10, 10 };
  // array of the accepted value amounts.
  double[] acceptedAmounts = { 100.00, 50.00, 20.00, 10.00, 5.00, 1.00, .25, .10, .05 };
  // represents index of the ticket that user is trying to buy, will be used in
  // ticket count
  int ticketIndex = 0;
  // current price of the ticket index
  double price = 0;
  // maximum amount of tickets
  int numOfTickets = 60;
  // state choices
  String city = "";
  // while loop for which ticket user wants. Mahcine will keep running until no
  // more tickets available
  while (numOfTickets > 0) {
   // checking to see which tickts are sold out
   for (int i = 0; i < ticketCount.length; i++) {
    // if count is less than or = to 0 then sold out
    if (ticketCount[i] <= 0) {
     // first 3 are new jersey, last three are NY
     if (i < 3) {
      System.out.println("New Jersey ticket class " + (char) ('A' + i) + " are sold out ");
      // NY's turn >>
     } else {
      System.out.println("New York ticket class " + (char) ('A' + i - 3) + " are sold out ");
     }
    }
   }
   // this is where we ask for destination. user has to input destination as
   // integer
   System.out.println("What is your destination, (1) New York or (2)New Jersey?");
   int destination = input.nextInt();
   // we do same thing for ticket class. We accept class of ticket as integer
   System.out.println("Which ticket class you want to buy (1: A, 2: B, 3: C)");
   int ticketClass = input.nextInt();
   // NY Prices
   if (destination == 1) {
    // an array index
    price = nyPrices[ticketClass - 1];
    // NY section of the array. we add 2 to get ticket index
    ticketIndex = ticketClass + 2;
    city = "New York";
    // Jersey's turn >>>
   } else if (destination == 2) {
    price = jerseyPrices[ticketClass - 1];
    ticketIndex = ticketClass - 1;
    city = "New Jersey";
   }
   // checking if ticket count is less than or = to 0
   if (ticketCount[ticketIndex] <= 0) {
    System.out.println("Tickets are sold out, please choose another ticket.");
    // restarts the loop
    continue;
   }
   // printing out the price of the tixket
   System.out.println("The price of " + city + " ticket class " + (char) ('A' + ticketClass - 1) + " is : $" + price);
   // keeping track of the current price.
   double currentPrice = price;
   // remember only certain amount of bills/money is accepted!!
   boolean validPrice = false;
   // how much money we are giving back to user
   double change = 0;
   System.out.print("Insert money in the machine: ");
   // asking user to input certain amount of money until it's 0
   while (currentPrice > 0) {
    double inputAmount = input.nextDouble();
    // checking to see if amount is valid
    validPrice = false;
    for (int i = 0; i < acceptedAmounts.length; i++) {
     if (acceptedAmounts[i] == inputAmount) {
      validPrice = true;
     }
    }
    // if price is not valid, machine rejects it and user has to insert more
    if (!validPrice) {
     System.out.println("REJECTED, you have $" + (price - currentPrice) + " insert more! ");
    } else {
     // we're taking current price and decreasing it by what user is inputting
     currentPrice -= inputAmount;
     if (currentPrice <= 0) {
      // get out of the loop
      break;
     }
     System.out.println("You have $" + (price - currentPrice) + " insert more! ");
    }

   }
   // calculate change
   change = currentPrice * -1;
   // we are subtracting ticket for each user
   ticketCount[ticketIndex]--;
   System.out.println("Thank you, please take your ticket");
   System.out.println("Your change is: $" + change + " please take it!");
  }
  // while loop ends. there's no more tickets to be sold!!!
  System.out.println("Machine closed, all tickets currently sold out!");
 }
}
