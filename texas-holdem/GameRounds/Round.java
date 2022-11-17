package GameRounds;

import CardPack.*;
import People.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    private ArrayList<Player> players;

    private ArrayList<Card> tableCards;



    public Round( ArrayList<Player> players) {
        setPlayers(players);
        setCardDeck(new Deck());
        setCardIndex(0);
    }

    public Round( ArrayList<Player> players, Deck deck) {
        setPlayers(players);
        setCardDeck(deck);
        setCardIndex(0);
    }

    @Override
    public String toString() {
        return "\nRound{" +
                ", theFlop=" + Arrays.toString(theFlop) +
                ", theTurn=" + getTheTurn() +
                ", theRiver=" + getTheRiver() +
                ", currentCall=" + getCurrentCall() +
                ", players="  +
                '}';
    }


    public ArrayList<Card> getTableCards() {
        return tableCards;
    }

    public void setTableCards(ArrayList<Card> tableCards) {
        this.tableCards = tableCards;
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

        ArrayList<Card> tableCards = new ArrayList<>();
        Collections.addAll(tableCards, flop);

        setTableCards(tableCards);

    }

    public Card getTheTurn() {
        return theTurn;
    }

    public void setTheTurn() {
        Card[] cards = this.getCardDeck().getCards();
        Card c1 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        theTurn = c1;
        getTableCards().add(c1);
    }

    public Card getTheRiver() {
        return theRiver;
    }

    public void setTheRiver() {
        Card[] cards = this.getCardDeck().getCards();
        Card c1 = cards[this.getCardIndex()];
        this.setCardIndex(this.getCardIndex()+1);
        theRiver = c1;
        getTableCards().add(c1);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
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


    public ArrayList<Player> findWinner(ArrayList<Player> activePlayers){
        ArrayList<Player> playersLeft = new ArrayList<>();
        ArrayList<Player> winners = new ArrayList<>();
        int maxCategory = 0;
        int highCard = 0;
        int kicker = 0;


        for (Player player:activePlayers
             ) {
            if(player.getStatus().equals("playing") || player.getStatus().equals("allin")){
                playersLeft.add(player);
            }
        }

        for (int i = 0; i < playersLeft.size(); i++) {
            if(i == 0){
                maxCategory = playersLeft.get(i).getHandValue();
            }else{
                if(playersLeft.get(i).getHandValue() > maxCategory){
                    maxCategory = playersLeft.get(i).getHandValue();
                }
            }
        }

        // sort out winners!!!!

        // if royal Flush.
        if (maxCategory == 1000){
            for (Player player: playersLeft){
                if (player.getHandValue() == 1000){
                    winners.add(player);
                }
            }
        } else if (maxCategory >= 900) {
            // straight Flush category
            // add all players in this category
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 900 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                } else if (player.getHandValue() >= 900 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }
            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
        } else if (maxCategory >= 800) {
            // 4 of a kind Category.
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 800 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 800 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }
            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                // check highest kicker.
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
            // all winners now have the same High card & Kicker.

        } else if (maxCategory >= 700) {
            // Full House (No straight possible).
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 700 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 700 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }
            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
            // all winners now have the same High card & Kicker.
        } else if (maxCategory >= 600) {
            // Flush category (no straight)
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 600 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 600 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }
            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        } else if (maxCategory >= 500) {
            // Flush category (no straight)
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 500 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 500 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }

            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        }else if (maxCategory >= 400) {
            // Triples
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 400 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 400 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }

            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        }else if (maxCategory >= 300) {
            // Two Pair
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 300 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 300 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }

            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        }else if (maxCategory >= 200) {
            // Pair
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 200 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 200 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }

            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        }else if (maxCategory >= 100) {
            // Triples
            for (Player player : playersLeft) {
                if (player.getHandValue() >= 100 && highCard == 0) {
                    winners.add(player);
                    highCard = player.getHighCard();
                    kicker = player.getKicker();
                } else if (player.getHandValue() >= 100 && player.getHighCard() >= highCard) {
                    winners.add(player);
                    highCard = player.getHighCard();
                }
            }

            // remove winners that don't have the high card.
            removeWinnersByHighCard(playersLeft, winners, highCard);
            // check kicker if high card is still not enough
            if(winners.size() >= 2){
                removeWinnersByKicker(playersLeft, winners, kicker);
            }
        }


        return winners;
    }

    private static void removeWinnersByHighCard(ArrayList<Player> playersLeft, ArrayList<Player> winners, int highCard) {
        for (Player player : playersLeft) {
            if (player.getHighCard() != highCard){
                winners.remove(player);
            }
        }
    }

    private static void removeWinnersByKicker(ArrayList<Player> playersLeft, ArrayList<Player> winners, int kicker) {
        // check highest kicker.
        for (Player player : winners){
            if(player.getKicker() >= kicker){
                kicker = player.getKicker();
            }
        }

        // remove any player that does not have highest kicker.
        for (Player player : playersLeft) {
            if (player.getKicker() != kicker){
                winners.remove(player);
            }
        }
    }

}
