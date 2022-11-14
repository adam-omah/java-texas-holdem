package People;

import CardPack.Card;
import GameRounds.Round;

import java.util.ArrayList;
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

    public Player(String name, String address, GregorianCalendar dateOfBirth) {
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
    }

    public ArrayList<Card> getBestPlayerHand() {
        return bestPlayerHand;
    }

    public void setBestPlayerHand(Round round) {
        ArrayList<Card> tableCards = round.getTableCards();
        ArrayList<Card> bestPlayerHand = new ArrayList<>();
        // sort which 5 cards the player would use:
        // is royal flush possible?
        // is straight flush possible?
        // is Four of a kind possible?
        // is Four of a kind possible?
        // is Four of a kind possible?
        // is a full house possible?
        // is a flush possible?
        // is a straight possible?
        // is triples possible?
        // is two pair possible?
        // is pair possible?
        // Pick highest cards if not even pairs possible.


        this.bestPlayerHand = bestPlayerHand;
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
