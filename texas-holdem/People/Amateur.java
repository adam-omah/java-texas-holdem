package People;

import CardPack.Card;

import java.util.Arrays;
import java.util.GregorianCalendar;

public class Amateur extends Player {
    private int funds;
    private int ranking;

    private Card[] cards;

    private int currentBet;

    private  String status;


    public Amateur(String name, String address, GregorianCalendar dateOfBirth) {
        super(name, address, dateOfBirth);
        setFunds(1000);
        setRanking(playerTotal+1);
        playerTotal++;
        setStatus("playing");
        setCurrentBet(0);

    }

    @Override
    public String toString() {
        return "amateur{" +
                "funds=" + funds +
                ", ranking=" + ranking +
                ", cards=" + Arrays.toString(cards) +
                "} " + super.toString();
    }

    @Override
    public String getStatus() {
        return status;
    }


    // Status added to make it more visible if player is folded, all in or still playing the game.
    @Override
    public void setStatus(String status) {
        if(status.equals("playing")|| status.equals("newTurn")|| status.equals("fold") || status.equals("allin") || status.equals("out")){
            this.status = status;
        }else
            this.status = "fold";

    }

    public void setFunds(int funds) {
        if(funds >= 0){
            this.funds = funds;
        }else
            this.funds = 0;

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

    public void setRanking(int ranking) {
        if(ranking >= 1){
            this.ranking = ranking;
        }else{
            ranking = playerTotal;
        }

    }

    @Override
    public String getCategory() {
        return "Ameture";
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
