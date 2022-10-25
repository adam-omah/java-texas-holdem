package People;

import java.util.GregorianCalendar;

public class pro extends player{
    private int funds;
    private int ranking;


    public pro(String name, String address, GregorianCalendar dateOfBirth) {
        super(name, address, dateOfBirth);
        setFunds(750);
        setRanking(playerTotal+1);
        playerTotal++;
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
