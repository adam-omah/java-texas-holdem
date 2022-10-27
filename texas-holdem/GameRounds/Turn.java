package GameRounds;

import People.Player;

public class Turn {
    private Player playerTurn;
    private static String action;
    private int bet;

    private int call;

    public Turn(Player playerTurn, int call) {
        setBet(0);
        setPlayerTurn(playerTurn);
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public static String getAction() {
        return action;
    }

    public static void setAction(String action) {
        // action will either be Check, Call, Fold or
        Turn.action = action;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {

        // A bet must be greater than the call value.

        if(bet >= getCall()){
            this.bet = bet;
        }else
            this.bet = getCall();

    }
}
