package CardPack;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private Card[] usedCards;

    public Deck() {
        cards = newDeck();
        usedCards = new Card[52];
    }

    public Deck (Card[] cards){
        this.cards = cards;
        usedCards = new Card[52];
    }

    private Card[] newDeck() {
        Card[] newDeck = new Card[52];
        int current = 0;

        for (int i = 0; i < 4; i++) {

            for (int j = 2; j < 15; j++) {
                switch (i) {
                    case 0 -> {
                        newDeck[current] = new Card('H', j);
                        current++;
                    }
                    case 1 -> {
                        newDeck[current] = new Card('S', j);
                        current++;
                    }
                    case 2 -> {
                        newDeck[current] = new Card('D', j);
                        current++;
                    }
                    default -> {
                        newDeck[current] = new Card('C', j);
                        current++;
                    }
                }
            }

        }

        shuffleDeck(newDeck);


        return newDeck;

    }

    private void shuffleDeck(Card[] newDeck) {
        Card tempCard;
        int randomSwap;

        Random rand = new Random();

        for (int i = 0; i < newDeck.length; i++) {
            randomSwap = rand.nextInt(newDeck.length);
            tempCard = newDeck[i];
            newDeck[i] = newDeck[randomSwap];
            newDeck[randomSwap] = tempCard;
        }
    }

    @Override
    public String toString() {
        String cardString = "", usedCardString = "";
        int cardCount = 0, usedCardsCount =0;

        for (int i = 0; i < 52; i++) {
            if (cards[i]!=null){
                cardString += cards[i] + ",";
                if(i%10 == 0 && i != 0){
                    cardString += "\n";
                }
                cardCount++;
            }
        }

        for (int i = 0; i < 52; i++) {
            if (usedCards[i]!=null) {
                usedCardString += usedCards[i] + ",";
                if (i % 10 == 0 && i != 0) {
                    usedCardString += "\n";
                }
                usedCardsCount++;
            }
        }

        return
                "cards:\n" + cardString +"\nCard Count: " + cardCount +
                        "\n\nusedCards: " + usedCardString +"\nUsed Card Count: " + usedCardsCount
                ;
    }

    public Deck(Card[] cards, Card[] usedCards) {
        setCards(cards);
        setUsedCards(usedCards);
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getUsedCards() {
        return usedCards;
    }

    public void setUsedCards(Card[] usedCards) {
        this.usedCards = usedCards;
    }


}
