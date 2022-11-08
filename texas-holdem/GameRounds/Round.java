package GameRounds;

import CardPack.*;
import People.Player;

import java.util.Arrays;

public class Round {
    private int cardIndex;
    private Deck cardDeck;

    private int blindIndex;

    private Player smallBlind;

    private Card[] theFlop;
    private Card theTurn;
    private Card theRiver;

    private int currentCall;

    private int pool;

    private Player[] players;

    public Round(int bigBlindPos, int littleBlindPos, Player[] players) {
        setPlayers(players);
        setCardDeck(new Deck());
        setCardIndex(0);
    }

    @Override
    public String toString() {
        return "\nRound{" +
                ", theFlop=" + Arrays.toString(theFlop) +
                ", theTurn=" + theTurn +
                ", theRiver=" + theRiver +
                ", currentCall=" + currentCall +
                ", players=" + Arrays.toString(players) +
                '}';
    }




    public int getCurrentCall() {
        return currentCall;
    }

    public void setCurrentCall(int currentCall) {
        this.currentCall = currentCall;
    }

    public int getPool() {
        return pool;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public Deck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(Deck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card[] getTheFlop() {
        return theFlop;
    }

    public void setTheFlop() {
        Card[] cards = this.getCardDeck().getCards();

        Card c1 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        Card c2 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        Card c3 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);

        Card[] flop = {c1,c2,c3};

        this.theFlop = flop;
    }

    public Card getTheTurn() {
        return theTurn;
    }

    public void setTheTurn() {
        Card[] cards = this.getCardDeck().getCards();
        Card c1 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        theTurn = c1;
    }

    public Card getTheRiver() {
        return theRiver;
    }

    public void setTheRiver() {
        Card[] cards = this.getCardDeck().getCards();
        Card c1 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        theRiver = c1;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getBlindIndex() {
        return blindIndex;
    }

    public void setBlindIndex(int blindIndex) {
        this.blindIndex = blindIndex;
    }

    public Player getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(Player smallBlind) {
        this.smallBlind = smallBlind;
    }
}
