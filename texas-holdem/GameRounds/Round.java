package GameRounds;

import CardPack.*;
import People.Player;

import java.util.Arrays;

public class Round {
    private int bigBlindPos;
    private int littleBlindPos;
    private Deck cardDeck;

    private Card[] theFlop;
    private Card theTurn;
    private Card theRiver;

    private int currentCall;

    private Player[] players;

    public Round(int bigBlindPos, int littleBlindPos, Player[] players) {
        setBigBlindPos(bigBlindPos);
        setLittleBlindPos(littleBlindPos);
        setPlayers(players);
        setCardDeck(new Deck());
    }

    @Override
    public String toString() {
        return "\nRound{" +
                "bigBlindPos=" + bigBlindPos +
                ", littleBlindPos=" + littleBlindPos +
                ", theFlop=" + Arrays.toString(theFlop) +
                ", theTurn=" + theTurn +
                ", theRiver=" + theRiver +
                ", currentCall=" + currentCall +
                ", players=" + Arrays.toString(players) +
                '}';
    }

    public int getBigBlindPos() {
        return bigBlindPos;
    }

    public int getCurrentCall() {
        return currentCall;
    }

    public void setCurrentCall(int currentCall) {
        this.currentCall = currentCall;
    }

    public void setBigBlindPos(int bigBlindPos) {
        this.bigBlindPos = bigBlindPos;
    }

    public int getLittleBlindPos() {
        return littleBlindPos;
    }

    public void setLittleBlindPos(int littleBlindPos) {
        this.littleBlindPos = littleBlindPos;
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

    public void setTheFlop(Card[] theFlop) {
        this.theFlop = theFlop;
    }

    public Card getTheTurn() {
        return theTurn;
    }

    public void setTheTurn(Card theTurn) {
        this.theTurn = theTurn;
    }

    public Card getTheRiver() {
        return theRiver;
    }

    public void setTheRiver(Card theRiver) {
        this.theRiver = theRiver;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
