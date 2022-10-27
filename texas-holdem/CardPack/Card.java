package CardPack;

public class Card {

    // Suits can either be:
    // S for spades, D for diamonds, C for clubs, H for hearts and J for Joker.
    char suit;


    // Values range from 1 to 14.
    // Ace = 14 or 1 , king = 13 queen = 12 Jack = 11
    // and 2-10 are their values respectively.
    int value;

    public Card(){
        setSuit('J');
        setValue(2);
    }

    @Override
    public String toString() {
        String suitFormat = "",valueFormat ="";

        switch (suit){
            case 'S' -> suitFormat = "Spades";
            case 'C' -> suitFormat = "Clubs";
            case 'H' -> suitFormat = "Hearts";
            case 'D' -> suitFormat = "Diamonds";

        }

        switch (value){
            case 11 -> valueFormat = "Jack";
            case 12 -> valueFormat = "Queen";
            case 13 -> valueFormat = "King";
            case 14 -> valueFormat = "Ace";
            default -> valueFormat += value;

        }

        return
                " "+ valueFormat + " of " + suitFormat;
    }

    public Card(char suit, int value){
        setSuit(suit);
        setValue(value);
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        if (value >= 1 && value <= 14) {
            this.value = value;
        }
    }

    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
