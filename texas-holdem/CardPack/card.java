package CardPack;

public class card {

    // Suits can either be:
    // S for spades, D for diamonds, C for clubs, H for hearts and J for Joker.
    char suit;


    // Values range from 2 to 14.
    // Ace = 14, king = 13 queen = 12 Jack = 11
    // and 2-10 are their values respectively.
    int value;

    public card(){
        setSuit('J');
        setValue(2);
    }

    public card(char suit, int value){

    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        if (value >= 2 && value <= 14) {
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
