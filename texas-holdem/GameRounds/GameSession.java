package GameRounds;

import People.Player;

import java.util.Arrays;

public class GameSession {
    private Round[] rounds;
    private Player[] players;
    private String[] winners;

    public GameSession(Player[] players) {
        Round[] rounds = new Round[4];
        String[] winners = new String[4];
        int roundCount =1;
        int bigBlind = 0;
        int smallBlind= 3;
        setPlayers(players);

        for (int i = 0; i < rounds.length; i++) {
            if (bigBlind == 4){
                bigBlind = 0;
            }
            if(smallBlind == 4){
                smallBlind = 0;
            }

            Round round = new Round(bigBlind,smallBlind, players);
            smallBlind++;
            bigBlind++;
            roundCount++;
            rounds[i] = round;
        }

        setRounds(rounds);
        setWinners(winners);

    }

    @Override
    public String toString() {
        return "GameSession{" +
                "rounds=" + Arrays.toString(rounds) +
                ",\nplayers=" + Arrays.toString(players) +
                ",\nwinners=" + Arrays.toString(winners) +
                '}';
    }

    public Round[] getRounds() {
        return rounds;
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public String[] getWinners() {
        return winners;
    }

    public void setWinners(String[] winners) {
        this.winners = winners;
    }
}
