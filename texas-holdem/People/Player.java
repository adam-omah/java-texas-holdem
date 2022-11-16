package People;

import CardPack.Card;
import GameRounds.Round;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public abstract class Player implements Person {
    private String name;
    private String address;
    private GregorianCalendar dateOfBirth;

    public static int playerTotal = 0;



    public abstract String getCategory();
    public abstract int getFunds();
    public abstract int getRanking();
    public abstract int getCurrentBet();

    public abstract String getStatus();

    public abstract Card[] getCards();

    public ArrayList<Card> bestPlayerHand;

    public int handValue;

    public Player(String name, String address, GregorianCalendar dateOfBirth) {
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
    }

    public ArrayList<Card> getBestPlayerHand() {
        return bestPlayerHand;
    }

    public void setBestPlayerHand(Round round) {
        String output = "";
        ArrayList<Card> tableCards = new ArrayList<>();
        ArrayList<Card> bestPlayerHand = new ArrayList<>();
        ArrayList<Card> possibleCards = new ArrayList<>();
        ArrayList<Card> temp = new ArrayList<>();


        int heartSuit = 0;
        int clubSuit = 0;
        int spadeSuit = 0;
        int diamondSuit =0;
        boolean flushPossible = false;
        char flushSuite = 'J';
        int tempValue = 0;
        Card[] cards = this.getCards();

        tableCards = round.getTableCards();
        possibleCards.addAll(tableCards);
        for (Card card: cards
             ) {
            possibleCards.add(card);
        }

        output += "Table cards:\n";
        for (Card card: tableCards
        ) {
            output += card.getValue() + "\n";
        }

        possibleCards.sort(Comparator.comparing(Card::getValue));




        output += "\nAvailable cards:\n";
        for (Card card: possibleCards
             ) {
            output += card.getValue() + "\n";
        }





        for (Card card : possibleCards) {
            switch (card.getSuit()) {
                case 'H' -> heartSuit++;
                case 'C' -> clubSuit++;
                case 'D' -> diamondSuit++;
                case 'S' -> spadeSuit++;
            }
        }



        if (heartSuit >= 5){
            flushSuite = 'H';
            flushPossible = true;
        } else if (clubSuit >= 5) {
            flushSuite = 'C';
            flushPossible =true;
        } else if (diamondSuit >= 5) {
            flushSuite = 'D';
            flushPossible =true;
        } else if (spadeSuit >= 5) {
            flushSuite = 'S';
            flushPossible =true;
        }

        // sort which 5 cards the player would use:
        // hand value -- represents the card value (needed for determining who wins in a category).

        // is royal flush possible? Hand Value 1000!
            if(flushPossible){
                // pick highest cards if not royal or str8 check if 4 of kind or full available.
                for (Card card: possibleCards
                     ) {
                    if (card.getValue() == 14 && card.getSuit() == flushSuite){
                        temp.add(card);
                        tempValue += 14;
                    } else if (card.getValue() == 13 && card.getSuit() == flushSuite) {
                        temp.add(card);
                        tempValue += 13;
                    }else if (card.getValue() == 12 && card.getSuit() == flushSuite) {
                        temp.add(card);
                        tempValue += 12;
                    }else if (card.getValue() == 11 && card.getSuit() == flushSuite) {
                        temp.add(card);
                        tempValue += 11;
                    }else if (card.getValue() == 10 && card.getSuit() == flushSuite) {
                        temp.add(card);
                        tempValue += 10;
                    }
                }
                if(tempValue == 60){
                    //if the temp value is 60 then player has a royal flush!
                    this.setHandValue(1000);
                    this.bestPlayerHand = temp;
                    return;
                }else{
                    // if not reset temp's!
                    temp.clear();
                    tempValue = 0;
                }
                // is straight flush possible? Hand Value 9--!

                //check each card for sequence.
                // make temp and remove duplicates.

                for (Card card: possibleCards
                     ) {
                    if(card.getSuit() == flushSuite){
                        temp.add(card);
                    }
                }
// Testing for if temp cards are in order.
//                output += "\nTemps:\n";
//                for (Card card: temp
//                ) {
//                    output += card.getValue() + "\n";
//                }
//
//
//                JOptionPane.showMessageDialog(null, output);

                // check if temp in sequence of 5?
                // high straight:
                for (int i = (temp.size()-1); i > 0; i--) {
                    if(i-4 == -1){
                        break;
                    }
                    if((temp.get(i).getValue() == (temp.get(i-1).getValue()+1)
                            && temp.get(i-1).getValue() == (temp.get(i-2).getValue()+1)
                            && temp.get(i-2).getValue() == (temp.get(i-3).getValue()+1)
                            && temp.get(i-3).getValue() == (temp.get(i-4).getValue()+1))
                            ){
                        ArrayList<Card> temp2 = new ArrayList<>();
                        temp2.add(temp.get(i-4));
                        temp2.add(temp.get(i-3));
                        temp2.add(temp.get(i-2));
                        temp2.add(temp.get(i-1));
                        temp2.add(temp.get(i));

                        temp2.sort(Comparator.comparing(Card::getValue));

                        tempValue = 900;
                        for (Card card:
                             temp) {
                                tempValue += card.getValue();
                        }
                        this.setHandValue(tempValue);
                        this.bestPlayerHand = temp2;
                        return;
                    }

                }
                //low straight.
                if(temp.get(0).getValue() == 2
                        && temp.get(1).getValue() == 3
                        && temp.get(2).getValue() == 4
                        && temp.get(3).getValue() == 5
                        && temp.get(temp.size() -1).getValue() == 14
                ){
                    // This section is testing if Straight with Ace at start is possible.
                    ArrayList<Card> temp2 = new ArrayList<>();
                    temp2.add(temp.get(temp.size() -1));
                    temp2.add(temp.get(0));
                    temp2.add(temp.get(1));
                    temp2.add(temp.get(2));
                    temp2.add(temp.get(3));

                    temp2.sort(Comparator.comparing(Card::getValue));

                    tempValue = 915;
                    this.setHandValue(tempValue);
                    this.bestPlayerHand = temp2;
                    return;
                }

            }

        // is Four of a kind possible? Hand Value 8--!
        // is a full house possible? Hand Value 7--!
        // is a flush possible? Hand Value 6--!
        // is a straight possible? Hand Value 5--!
            // check if temp in sequence of 5?
            //check each card for sequence.
            // make temp and remove duplicates.
            for (int i = 0; i < possibleCards.size(); i++) {
                int tempInd = 0;
                if(i == 0){
                    temp.add(possibleCards.get(i));
                }else {
                    if (temp.get(tempInd).getValue() == possibleCards.get(i).getValue()){

                    }else{
                        temp.add(possibleCards.get(i));
                        tempInd++;
                    }
                }
            }

            output += "\nTemps:\n";
            for (Card card: temp
            ) {
                output += card.getValue() + "\n";
            }


            JOptionPane.showMessageDialog(null, output);

            // check if temp in sequence of 5?
            // high straight:
            for (int i = (temp.size()-1); i > 0; i--) {
                if(i-4 == -1){
                    break;
                }
                if((temp.get(i).getValue() == (temp.get(i-1).getValue()+1)
                        && temp.get(i-1).getValue() == (temp.get(i-2).getValue()+1)
                        && temp.get(i-2).getValue() == (temp.get(i-3).getValue()+1)
                        && temp.get(i-3).getValue() == (temp.get(i-4).getValue()+1))
                ){
                    ArrayList<Card> temp2 = new ArrayList<>();
                    temp2.add(temp.get(i-4));
                    temp2.add(temp.get(i-3));
                    temp2.add(temp.get(i-2));
                    temp2.add(temp.get(i-1));
                    temp2.add(temp.get(i));

                    temp2.sort(Comparator.comparing(Card::getValue));

                    tempValue = 500;
                    for (Card card:
                            temp) {
                        tempValue += card.getValue();
                    }
                    this.setHandValue(tempValue);
                    this.bestPlayerHand = temp2;
                    return;
                }

            }
            //low straight.
            if(temp.get(0).getValue() == 2
                    && temp.get(1).getValue() == 3
                    && temp.get(2).getValue() == 4
                    && temp.get(3).getValue() == 5
                    && temp.get(temp.size() -1).getValue() == 14
            ){
                // This section is testing if Straight with Ace at start is possible.
                ArrayList<Card> temp2 = new ArrayList<>();
                temp2.add(temp.get(temp.size() -1));
                temp2.add(temp.get(0));
                temp2.add(temp.get(1));
                temp2.add(temp.get(2));
                temp2.add(temp.get(3));

                temp2.sort(Comparator.comparing(Card::getValue));

                tempValue = 515;
                this.setHandValue(tempValue);
                this.bestPlayerHand = temp2;
                return;
            }

        // is triples possible? Hand Value 4--!
        // is two pair possible? Hand Value 3--!
        // is pair possible? Hand Value 2--!
        // Pick highest cards if not even pairs possible. Hand Value 1--!

        if(temp != null){
            this.bestPlayerHand = temp;
        }else{
            this.bestPlayerHand = round.getTableCards();
        }
        possibleCards.clear();

    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract void setCards(Card[] cards);

    public abstract void setCurrentBet(int currBet);

    public  abstract void setStatus(String status);

    public abstract void setFunds(int newFunds);
}
