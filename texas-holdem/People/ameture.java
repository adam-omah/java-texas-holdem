package People;

import CardPack.card;

import java.util.GregorianCalendar;

public class ameture extends player{
    private int funds;
    private int ranking;

    private card[] cards;



    public ameture(String name, String address, GregorianCalendar dateOfBirth) {
        super(name, address, dateOfBirth);
        setFunds(1000);
        setRanking(playerTotal+1);
        playerTotal++;
    }

    public void setFunds(int funds) {
        if(funds >= 0){
            this.funds = funds;
        }else
            this.funds = 0;

    }

    @Override
    public card[] getCards() {
        return cards;
    }

    public void setCards(card[] cards) {
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
