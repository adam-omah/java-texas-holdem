package CardPack;

import java.util.Arrays;
import java.util.Random;

public class deck {
    private card[] cards;
    private card[] usedCards;

    public deck() {
        cards = newDeck();
        usedCards = new card[52];
    }

    private card[] newDeck() {
        card[] newDeck = new card[52];
        int current = 0;

        for (int i = 0; i < 4; i++) {

            for (int j = 2; j < 15; j++) {
                switch (i) {
                    case 0 -> {
                        newDeck[current] = new card('H', j);
                        current++;
                    }
                    case 1 -> {
                        newDeck[current] = new card('S', j);
                        current++;
                    }
                    case 2 -> {
                        newDeck[current] = new card('D', j);
                        current++;
                    }
                    default -> {
                        newDeck[current] = new card('C', j);
                        current++;
                    }
                }
            }

        }

        shuffleDeck(newDeck);


        return newDeck;

    }

    private void shuffleDeck(card[] newDeck) {
        card tempCard;
        int randomSwap;

        Random rand = new Random();

        for (int i = 0; i < newDeck.length; i++) {
            randomSwap = rand.nextInt(newDeck.length);
            tempCard = newDeck[i];
            newDeck[i] = newDeck[randomSwap];
            newDeck[randomSwap] = newDeck[i];
        }
    }

    @Override
    public String toString() {
        String cardString = "", usedCardString = "";
        int cardCount = 0, usedCardsCount =0;

        for (int i = 0; i < 52; i++) {
            if (cards[i]!=null){
                cardString += cards[i] + ",";
                if(i%10 == 0){
                    cardString += "\n";
                }
                cardCount++;
            }
        }

        for (int i = 0; i < 52; i++) {
            if (usedCards[i]!=null) {
                usedCardString += usedCards[i] + ",";
                if (i % 10 == 0) {
                    usedCardString += "\n";
                }
                usedCardsCount++;
            }
        }

        return
                "cards: " + cardString +"\nCard Count: " + cardCount +
                        "\n\nusedCards: " + usedCardString +"\nUsed Card Count: " + usedCardsCount
                ;
    }

    public deck(card[] cards, card[] usedCards) {
        setCards(cards);
        setUsedCards(usedCards);
    }

    public card[] getCards() {
        return cards;
    }

    public void setCards(card[] cards) {
        this.cards = cards;
    }

    public card[] getUsedCards() {
        return usedCards;
    }

    public void setUsedCards(card[] usedCards) {
        this.usedCards = usedCards;
    }


}
