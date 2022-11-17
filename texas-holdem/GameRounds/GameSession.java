package GameRounds;

import People.Player;

import java.util.ArrayList;

public class GameSession {
    private ArrayList<Round> rounds;
    private ArrayList<Player> players;
    private ArrayList<Player> roundWinners;

    private Player winner;



    private int currentRound;

    private Boolean gameOver;

    public GameSession(ArrayList<Player> players) {
        setPlayers(players);
        ArrayList<Round> gameRounds = new ArrayList<Round>();
        ArrayList<Player> winners = new ArrayList<Player>();

        // first round always added when new Game session.
        Round round = new Round(players);
        gameRounds.add(round);

        setRounds(gameRounds);
        setRoundWinners(winners);
        setGameOver(false);

        setCurrentRound(0);

    }

    @Override
    public String toString() {
        String out = "GameSession{" +
                "\nrounds=" + rounds +
                "\n players=" + players +
                "\n winners=";
        if(roundWinners != null){
            for (Player winner: roundWinners
            ) {
                out += winner.getName();
            }

        }

        return  out;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getRoundWinners() {
        return roundWinners;
    }

    public void setRoundWinners(ArrayList<Player> roundWinners) {
        this.roundWinners = roundWinners;
    }
}
