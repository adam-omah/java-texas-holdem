package People;

import CardPack.*;
import GameRounds.Round;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TestPlayer {
    public static void main(String[] args) {
        String out = "";
        Pro p1 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));

        ArrayList<Player> players = new ArrayList<>();
        // testing deck for different cards.
        Card[] cards = {new Card('H',2), new Card('H',3),new Card('H',4),new Card('H',5), new Card('S',7),new Card('S',10)};
        Deck d1 = new Deck(cards);

        Card[] hand = {new Card('H',6), new Card('S',9)};
        p1.setCards(hand);

        players.add(p1);

        Round round = new Round(players, d1);

        round.setTheFlop();
        round.setTheTurn();
        round.setTheRiver();

        players.get(0).setBestPlayerHand(round);

        out += "Hand Value: " +players.get(0).getHandValue() + "\n\nBest Hand: \n" + players.get(0).getBestPlayerHand();

        JOptionPane.showMessageDialog(null, out);

    }
}
