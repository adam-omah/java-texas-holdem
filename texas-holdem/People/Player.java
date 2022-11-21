package People;

import CardPack.Card;
import GameRounds.Round;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.io.Serializable;


public abstract class Player implements Serializable{
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

    // high card and Kicker, introduced to determine winner within categories.
    // this is the poker terminologoy for these card values too.
    public int highCard;
    public int kicker;

    public int roundWins;

    public int GameWins;

    public int getRoundWins() {
        return roundWins;
    }

    public void setRoundWins(int roundWins) {
        this.roundWins = roundWins;
    }

    public int getGameWins() {
        return GameWins;
    }

    public void setGameWins(int gameWins) {
        GameWins = gameWins;
    }

    public int getHighCard() {
        return highCard;
    }

    public void setHighCard(int highCard) {
        this.highCard = highCard;
    }

    public int getKicker() {
        return kicker;
    }

    public void setKicker(int kicker) {
        this.kicker = kicker;
    }

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
        int twos = 0,threes =0,fours=0, fives = 0, sixes =0, sevens =0, eights =0,
                nines =0, tens = 0, jacks=0,queens = 0, kings = 0, aces =0;
        boolean flushPossible = false;
        char flushSuite = 'J';
        int tempValue = 0;
        Card[] cards = this.getCards();

        this.setKicker(0);
        this.setHighCard(0);

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
                        this.setHighCard(temp2.get(4).getValue());
                        setHandTempValue(temp2, tempValue);
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
                    this.setHighCard(5);
                    this.setKicker(4);
                    this.setHandValue(tempValue);
                    this.bestPlayerHand = temp2;
                    return;
                }

            }

        // is Four of a kind possible? Hand Value 8--!
        int fourOfAKind = 0 , trips1 =0, trips2 =0, pair1 =0, pair2 = 0, pair3 =0;

        for (Card card:
             possibleCards) {
            switch (card.getValue()) {
                case 2 -> {
                    twos++;
                    // pairs
                    if(twos == 2 && pair1 == 0){
                        pair1 = 2;
                    } else if (twos == 2 && pair2 == 0) {
                        pair2 = 2;
                    } else if (twos == 2) {
                        pair3 = 2;
                    }
                    
                    // triples.
                    if (twos == 3 && trips1 == 0) {
                        trips1 = 2;
                    }else if(twos == 3){
                        trips2 = 2;
                    }
                    if (twos == 4) {
                        fourOfAKind = 2;
                    }
                }
                case 3 -> {
                    threes++;

                    // pairs
                    if(threes == 2 && pair1 == 0){
                        pair1 = 3;
                    } else if (threes == 2 && pair2 == 0) {
                        pair2 = 3;
                    } else if (threes == 2) {
                        pair3 = 3;
                    }

                    // triples.
                    
                    if (threes == 3 && trips1 == 0) {
                        trips1 = 3;
                    }else if(threes == 3){
                        trips2 = 3;
                    }
                    if (threes == 4) {
                        fourOfAKind = 3;
                    }
                }
                case 4 -> {
                    fours++;

                    // pairs
                    if(fours == 2 && pair1 == 0){
                        pair1 = 4;
                    } else if (fours == 2 && pair2 == 0) {
                        pair2 = 4;
                    } else if (fours == 2) {
                        pair3 = 4;
                    }

                    // triples.
                    if (fours == 3 && trips1 == 0) {
                        trips1 = 4;
                    }else if(fours == 3){
                        trips2 = 4;
                    }
                    if (fours == 4) {
                        fourOfAKind = 4;
                    }
                }
                case 5 -> {
                    fives++;
                    // pairs
                    if(fives == 2 && pair1 == 0){
                        pair1 = 5;
                    } else if (fives == 2 && pair2 == 0) {
                        pair2 = 5;
                    } else if (fives == 2) {
                        pair3 = 5;
                    }

                    // triples.
                    if (fives == 3 && trips1 == 0) {
                        trips1 = 5;
                    }else if(fives == 3){
                        trips2 = 5;
                    }
                    if (fives == 4) {
                        fourOfAKind = 5;
                    }
                }
                case 6 -> {
                    sixes++;
                    // pairs
                    if(sixes == 2 && pair1 == 0){
                        pair1 = 6;
                    } else if (sixes == 2 && pair2 == 0) {
                        pair2 = 6;
                    } else if (sixes == 2) {
                        pair3 = 6;
                    }

                    // triples.
                    if (sixes == 3 && trips1 == 0) {
                        trips1 = 6;
                    }else if(sixes == 3){
                        trips2 = 6;
                    }
                    if (sixes == 4) {
                        fourOfAKind = 6;
                    }
                }
                case 7 -> {
                    sevens++;
                    // pairs
                    if(sevens == 2 && pair1 == 0){
                        pair1 = 7;
                    } else if (sevens == 2 && pair2 == 0) {
                        pair2 = 7;
                    } else if (sevens == 2) {
                        pair3 = 7;
                    }

                    // triples.
                    if (sevens == 3 && trips1 == 0) {
                        trips1 = 7;
                    }else if(sevens == 3){
                        trips2 = 7;
                    }
                    if (sevens == 4) {
                        fourOfAKind = 7;
                    }
                }
                case 8 -> {
                    eights++;
                    // pairs
                    if(eights == 2 && pair1 == 0){
                        pair1 = 8;
                    } else if (eights == 2 && pair2 == 0) {
                        pair2 = 8;
                    } else if (eights == 2) {
                        pair3 = 8;
                    }

                    // triples.
                    if (eights == 3 && trips1 == 0) {
                        trips1 = 8;
                    }else if(eights == 3){
                        trips2 = 8;
                    }
                    if (eights == 4) {
                        fourOfAKind = 8;
                    }
                }
                case 9 -> {
                    nines++;
                    // pairs
                    if(nines == 2 && pair1 == 0){
                        pair1 = 9;
                    } else if (nines == 2 && pair2 == 0) {
                        pair2 = 9;
                    } else if (nines == 2) {
                        pair3 = 9;
                    }

                    // triples.
                    if (nines == 3 && trips1 == 0) {
                        trips1 = 9;
                    }else if(nines == 3){
                        trips2 = 9;
                    }
                    if (nines == 4) {
                        fourOfAKind = 9;
                    }
                }
                case 10 -> {
                    tens++;
                    // pairs
                    if(tens == 2 && pair1 == 0){
                        pair1 = 10;
                    } else if (tens == 2 && pair2 == 0) {
                        pair2 = 10;
                    } else if (tens == 2) {
                        pair3 = 10;
                    }

                    // triples.
                    if (tens == 3 && trips1 == 0) {
                        trips1 = 10;
                    }else if(tens == 3){
                        trips2 = 10;
                    }
                    if (tens == 4) {
                        fourOfAKind = 10;
                    }
                }
                case 11 -> {
                    jacks++;
                    // pairs
                    if(jacks == 2 && pair1 == 0){
                        pair1 = 11;
                    } else if (jacks == 2 && pair2 == 0) {
                        pair2 = 11;
                    } else if (jacks == 2) {
                        pair3 = 11;
                    }

                    // triples.
                    if (jacks == 3 && trips1 == 0) {
                        trips1 = 11;
                    }else if(jacks == 3){
                        trips2 = 11;
                    }
                    if (jacks == 4) {
                        fourOfAKind = 11;
                    }
                }
                case 12 -> {
                    queens++;
                    // pairs
                    if(queens == 2 && pair1 == 0){
                        pair1 = 12;
                    } else if (queens == 2 && pair2 == 0) {
                        pair2 = 12;
                    } else if (queens == 2) {
                        pair3 = 12;
                    }

                    // triples.
                    if (queens == 3 && trips1 == 0) {
                        trips1 = 12;
                    }else if(queens == 3){
                        trips2 = 12;
                    }
                    if (queens == 4) {
                        fourOfAKind = 12;
                    }
                }
                case 13 -> {
                    kings++;
                    // pairs
                    if(kings == 2 && pair1 == 0){
                        pair1 = 13;
                    } else if (kings == 2 && pair2 == 0) {
                        pair2 = 13;
                    } else if (kings == 2) {
                        pair3 = 13;
                    }

                    // triples.
                    if (kings == 3 && trips1 == 0) {
                        trips1 = 13;
                    }else if(kings == 3){
                        trips2 = 13;
                    }
                    if (kings == 4) {
                        fourOfAKind = 13;
                    }
                }
                case 14 -> {
                    aces++;
                    // pairs
                    if(aces == 2 && pair1 == 0){
                        pair1 = 14;
                    } else if (aces == 2 && pair2 == 0) {
                        pair2 = 14;
                    } else if (aces == 2) {
                        pair3 = 14;
                    }

                    // triples.
                    if (aces == 3 && trips1 == 0) {
                        trips1 = 14;
                    }else if(aces == 3){
                        trips2 = 14;
                    }
                    if (aces == 4) {
                        fourOfAKind = 14;
                    }
                }
            }
        }

        if(fourOfAKind != 0){
            // set 4 of kind card value to high card.
            setHighCard(fourOfAKind);
            temp.clear();

            for (Card card: possibleCards
                 ) {
                if(card.getValue() == fourOfAKind){
                    temp.add(card);
                }else{
                    // if not the four of a kind check kicker to be added. (highest will be added after).
                    if(this.getKicker() == 0){
                        setKicker(card.getValue());
                    }else if(this.getKicker() < card.getValue()){
                        setKicker(card.getValue());
                    }
                }
            }

            // add kicker to temp.
            for (Card card: possibleCards){
                if(card.getValue() == this.getKicker()){
                    temp.add(card);
                    break;
                }
            }
            // Set Best Hand + Hand Value.
            tempValue = 800;
            setHandTempValue(temp, tempValue);
            return;
        }

        // is a full house possible? Hand Value 7--!
        int highTriple = 0;
        int highPair = 0;
        if(trips1 != 0 && trips2 != 0){
            if(trips1 > trips2){
                highTriple = trips1;
                highPair = trips2;
            }else {
                highTriple = trips2;
                highPair = trips1;
            }
        } else if (trips1 != 0 && pair2 !=0) {
            if (pair1 == trips1){
                highTriple = trips1;
                if (pair3 !=0){
                    // intellij suggested using math max here (was an if - else statement before).
                    highPair = Math.max(pair2, pair3);
                }else {
                    highPair = pair2;
                }
            }
        }
        // if the above was entered high pair is always chosen. so only need to test if high trips is !=0
        if (highTriple != 0){
            this.setHighCard(highTriple);
            this.setKicker(highPair);
            int pairCount = 0;
            for (Card card: possibleCards){
                if (card.getValue() == highTriple){
                    temp.add(card);
                }
                if (card.getValue() == highPair && pairCount < 2){
                    temp.add(card);
                    pairCount++;
                }
            }

            tempValue = 700;
            setHandTempValue(temp, tempValue);
            return;
        }
        // is a flush possible? Hand Value 6--!

        if(flushPossible){
            for (Card card: possibleCards
                 ) {
                if(card.getSuit() == flushSuite){
                    temp.add(card);
                }
            }

            temp.sort(Comparator.comparing(Card::getValue));

//            JOptionPane.showMessageDialog(null, "Made it here");

            if(temp.size() >= 5){
                if(temp.size() > 5){
                    while(temp.size() > 5){
                        temp.remove(0);
                    }
                }

                // flush only goes to high card wins.
                setHighCard(temp.get(4).getValue());
                setKicker(temp.get(3).getValue());

                tempValue = 600;
                setHandTempValue(temp, tempValue);
                return;
            }
        }


        // is a straight possible? Hand Value 5--!
            // check if temp in sequence of 5?
            //check each card for sequence.
            // make temp and remove duplicates.
            for (int i = 0; i < possibleCards.size(); i++) {
                int tempInd = 0;
                if(i == 0){
                    temp.add(possibleCards.get(i));
                }else {
                    if (temp.get(temp.size()-1).getValue() == possibleCards.get(i).getValue()){

                    }else{
                        temp.add(possibleCards.get(i));
                        tempInd++;
                    }
                }
            }
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
                    setHighCard(temp2.get(4).getValue());
                    setKicker(temp2.get(3).getValue());
                    setHandTempValue(temp2, tempValue);
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
                this.setHighCard(5);
                this.setKicker(4);
                this.setHandValue(tempValue);
                this.bestPlayerHand = temp2;
                return;
            }
        // clear temp. After Straight not possible.
        temp.clear();

        // is triples possible? Hand Value 4--!
        if(trips1 != 0){

            int cardsAdded = 0;
            for (Card card:possibleCards
                 ) {
                if(card.getValue() == trips1){
                    temp.add(card);
                }
            }
            for (int i = possibleCards.size()-1; i > 0; i--) {
                if(possibleCards.get(i).getValue() != trips1){
                    temp.add(possibleCards.get(i));
                    cardsAdded++;
                    if(cardsAdded ==2){
                        break;
                    }
                }
            }

            setHighCard(trips1);
            setKicker(temp.get(3).getValue());

            tempValue = 400;
            setHandTempValue(temp, tempValue);
            return;

        }
        
        
        // is two pair possible? Hand Value 3--!
        int lowPair = 0;
        if (pair1 != 0 && pair2 !=0){
            if (pair3 == 0){
                if (pair1 > pair2){
                    highPair = pair1;
                    lowPair = pair2;
                }else{
                    highPair = pair2;
                    lowPair = pair1;
                }
            }else{
                if (pair1 > pair2 && pair1 > pair3){
                    highPair = pair1;
                    lowPair = Math.max(pair2, pair3);
                } else if (pair2 > pair3 && pair2 > pair1) {
                    highPair = pair2;
                    lowPair = Math.max(pair1, pair3);
                }else{
                    highPair = pair3;
                    lowPair = Math.max(pair1, pair2);
                }
            }

            for (Card card: possibleCards){
                if(card.getValue() == highPair || card.getValue() == lowPair){
                    temp.add(card);
                }
            }

            for (int i = possibleCards.size()-1; i > 0; i--) {
                if(possibleCards.get(i).getValue() == highPair || possibleCards.get(i).getValue() == lowPair){

                }else{
                    temp.add(possibleCards.get(i));
                    break;
                }
            }

            setKicker(Math.max(temp.get(4).getValue(), lowPair));
            setHighCard(highPair);
            tempValue = 300;
            setHandTempValue(temp, tempValue);
            return;

        }

        // is pair possible? Hand Value 2--!

        if(pair1 !=0){

            int cardsAdded = 0;

            for (Card card: possibleCards){
                if(card.getValue() == pair1){
                    temp.add(card);
                }
            }

            for (int i = possibleCards.size()-1; i > 0; i--) {
                if(possibleCards.get(i).getValue() != pair1){
                    temp.add(possibleCards.get(i));
                    cardsAdded++;
                    if(cardsAdded ==3){
                        break;
                    }
                }
            }


            setHighCard(pair1);
            setKicker(temp.get(2).getValue());

            tempValue = 200;
            setHandTempValue(temp, tempValue);
            return;
        }

        // Pick highest cards if not even pairs possible. Hand Value 1--!
        int cardsAdded = 0;

        for (Card card: possibleCards){
            if(card.getValue() == pair1){
                temp.add(card);
            }
        }

        for (int i = possibleCards.size()-1; i > 0; i--) {
                temp.add(possibleCards.get(i));
                cardsAdded++;
                if(cardsAdded ==5){
                    break;
                }
            }

        temp.sort(Comparator.comparing(Card::getValue));

        setHighCard(temp.get(4).getValue());
        setKicker(temp.get(3).getValue());

        tempValue = 100;
        setHandTempValue(temp, tempValue);



    }

    private void setHandTempValue(ArrayList<Card> temp, int tempValue) {
        for (Card card:
                temp) {
            tempValue += card.getValue();
        }
        this.setHandValue(tempValue);
        this.bestPlayerHand = temp;
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public GregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }


    public void setDateOfBirth(GregorianCalendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract void setCards(Card[] cards);

    public abstract void setCurrentBet(int currBet);

    public  abstract void setStatus(String status);

    public abstract void setFunds(int newFunds);
}
