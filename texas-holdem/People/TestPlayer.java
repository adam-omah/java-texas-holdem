package People;

import CardPack.*;
import GameRounds.Round;

import javax.swing.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TestPlayer {
    public static void main(String[] args) {
        String out = "" , out2 = "";
        Pro p1 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p2 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));

        ArrayList<Player> players = new ArrayList<>();
        // testing deck for different cards.
        // Cards 1 Tests for Royal Flush
        Card[] cards1 = {new Card('H',10), new Card('H',11),new Card('H',12),new Card('H',13), new Card('S',7),new Card('S',10)};
        Deck d1 = new Deck(cards1);

        Card[] hand1 = {new Card('H',9), new Card('H',14)};
        p1.setCards(hand1);
        players.add(p1);

        // Cards 2 Tests for Straight Flush with Ace start.
        Card[] cards2 = {new Card('H',2), new Card('H',3),new Card('H',4),new Card('H',5), new Card('S',7),new Card('S',10)};
        Deck d2 = new Deck(cards2);

        Card[] hand2 = {new Card('H',9), new Card('H',14)};
        p2.setCards(hand2);
        players.add(p2);

        // Round 1 Cards (royal flush)
        Round round1 = new Round(players, d1);
        round1.setTheFlop();
        round1.setTheTurn();
        round1.setTheRiver();

        // Round 2 Cards (Straight Flush Ace start)
        Round round2 = new Round(players, d2);
        round2.setTheFlop();
        round2.setTheTurn();
        round2.setTheRiver();

        players.get(0).setBestPlayerHand(round1);

        players.get(1).setBestPlayerHand(round2);

        out += "Hand1 Player1 (Royal Flush)\n\nHand Value: " +players.get(0).getHandValue() + "\n\nBest Hand: \n" + players.get(0).getBestPlayerHand();

        out += "\n\nHand2 Player2 (Straight Flush Ace start)\n\nHand Value: " +players.get(1).getHandValue() + "\n\nBest Hand: \n" + players.get(1).getBestPlayerHand();
        JOptionPane.showMessageDialog(null, out);

    }
}
