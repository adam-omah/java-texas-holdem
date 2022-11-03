package People;

import CardPack.Card;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class Pro extends Player {
    private int funds;
    private int ranking;

    private Card[] cards;
    private int currentBet;

    public Pro(String name, String address, GregorianCalendar dateOfBirth) {
        super(name, address, dateOfBirth);
        setFunds(750);
        setRanking(playerTotal+1);
        playerTotal++;
    }

    @Override
    public String toString() {
        return "pro{" +
                "funds=" + funds +
                ", ranking=" + ranking +
                ", cards=" + Arrays.toString(cards) +
                "} " + super.toString();
    }

    @Override
    public int getCurrentBet() {
        return currentBet;
    }


    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    @Override
    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public void setFunds(int funds) {
        if(funds >= 0){
            this.funds = funds;
        }else
            this.funds = 0;

    }

    public void setRanking(int ranking) {
        if(ranking >= 1){
            this.ranking = ranking;
        }else{
            ranking = playerTotal;
        }
    }

    @Override
    public String getCategory() {
        return "Pro";
    }

    @Override
    public int getFunds() {
        return funds;
    }

    @Override
    public int getRanking() {
        return ranking;
    }
}
