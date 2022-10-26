package GameRounds;

import CardPack.*;
import People.player;

public class Round {
    private int bigBlindPos;
    private int littleBlindPos;
    private deck cardDeck;

    private card[] theFlop;
    private card theTurn;
    private card theRiver;

    private int currentCall;

    private player[] players;

    public Round(int bigBlindPos, int littleBlindPos, player[] players) {
        setBigBlindPos(bigBlindPos);
        setLittleBlindPos(littleBlindPos);
        setPlayers(players);
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

    public deck getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(deck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public card[] getTheFlop() {
        return theFlop;
    }

    public void setTheFlop(card[] theFlop) {
        this.theFlop = theFlop;
    }

    public card getTheTurn() {
        return theTurn;
    }

    public void setTheTurn(card theTurn) {
        this.theTurn = theTurn;
    }

    public card getTheRiver() {
        return theRiver;
    }

    public void setTheRiver(card theRiver) {
        this.theRiver = theRiver;
    }

    public player[] getPlayers() {
        return players;
    }

    public void setPlayers(player[] players) {
        this.players = players;
    }
}
