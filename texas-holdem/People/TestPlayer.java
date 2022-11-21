package People;

import CardPack.*;
import GameRounds.Round;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TestPlayer {
    public static void main(String[] args) {

        // testing the loading of the game
        // the boilerplate for serialization code was taken from lab sheet 15, and modified for its current use.
        File inFile	= new File("players_test.data");

        try {
            FileInputStream inStream = new FileInputStream(inFile);

            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            ArrayList<Player> playerList = (ArrayList<Player>) objectInStream.readObject();

            String fileString = "";

            Player ap1 = playerList.get(0);
            Player ap2 = playerList.get(2);


            JOptionPane.showMessageDialog(null, "Testing Loaded Players from Stream: " + ap1.getName() + "\n" + ap2.getName());

            inStream.close();
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File could not be found!",
                    "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File could not be read!",
                    "Problem Reading From File!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not find the appropriate class!",
                    "Problem Finding the Class!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassCastException cce) {
            cce.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!",
                    "Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
        }



        String out = "" , out2 = "";
        Pro p1 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p2 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p3 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p4 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p5 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));

        Amateur p6 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p7 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p8 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p9 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p10 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        
        

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Player> players2 = new ArrayList<>();
        
        
        // testing deck for different cards.
        // Cards 1 Tests for Royal Flush
        Card[] cards1 = {new Card('H',10), new Card('H',11),new Card('H',12),new Card('H',13), new Card('S',7),new Card('S',10)};
        Deck d1 = new Deck(cards1);

        Card[] hand1 = {new Card('H',9), new Card('H',14)};
        p1.setCards(hand1);
        players.add(p1);

        // Cards 2 Tests for Straight Flush with Ace start.
        Card[] cards2 = {new Card('H',2), new Card('H',3),new Card('H',4),new Card('H',5), new Card('S',2),new Card('S',4)};
        Deck d2 = new Deck(cards2);
        
        Card[] hand2 = {new Card('H',8), new Card('H',14)};
        p2.setCards(hand2);
        players.add(p2);

        // Cards 3 Tests for 4 of a kind
        Card[] cards3 = {new Card('H',2), new Card('C',2),new Card('H',4),new Card('C',4), new Card('S',2),new Card('S',4)};
        Deck d3 = new Deck(cards3);
        
        Card[] hand3 = {new Card('D',2), new Card('S',4)};
        p3.setCards(hand3);
        players.add(p3);

        // Cards 4 Tests for Full House
        Card[] cards4 = {new Card('H',2), new Card('C',2),new Card('H',4),new Card('C',4), new Card('S',6),new Card('S',4)};
        Deck d4 = new Deck(cards4);
        
        Card[] hand4 = {new Card('D',2), new Card('S',4)};
        p4.setCards(hand4);
        players.add(p4);

        // Cards 5 Tests for Flush
        Card[] cards5 = {new Card('H',2), new Card('H',5),new Card('H',10),new Card('H',7), new Card('S',6),new Card('S',4)};
        Deck d5 = new Deck(cards5);
        
        Card[] hand5 = {new Card('H',3), new Card('H',9)};
        p5.setCards(hand5);
        players.add(p5);

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
        // Round 3 Cards (4 of a kind)
        Round round3 = new Round(players, d3);
        round3.setTheFlop();
        round3.setTheTurn();
        round3.setTheRiver();
        // Round 4 Cards (Full House)
        Round round4 = new Round(players, d4);
        round4.setTheFlop();
        round4.setTheTurn();
        round4.setTheRiver();
        // Round 5 Cards (Flush non-straight)
        Round round5 = new Round(players, d5);
        round5.setTheFlop();
        round5.setTheTurn();
        round5.setTheRiver();

        players.get(0).setBestPlayerHand(round1);
        players.get(1).setBestPlayerHand(round2);
        players.get(2).setBestPlayerHand(round3);
        players.get(3).setBestPlayerHand(round4);
        players.get(4).setBestPlayerHand(round5);

        out += "Hand1 Player1 (Royal Flush)\n\nHand Value: " +players.get(0).getHandValue() + "\n\nBest Hand: \n" + players.get(0).getBestPlayerHand();

        out += "\n\nHand2 Player2 (Straight Flush Ace start)\n\nHand Value: " +players.get(1).getHandValue() + "\n\nBest Hand: \n" + players.get(1).getBestPlayerHand();

        out += "\n\nHand3 Player3 (4 Of A Kind)\n\nHand Value: " +players.get(2).getHandValue() + "\n\nBest Hand: \n" + players.get(2).getBestPlayerHand();

        out += "\n\nHand4 Player4 (Full House)\n\nHand Value: " +players.get(3).getHandValue() + "\n\nBest Hand: \n" + players.get(3).getBestPlayerHand();

        out += "\n\nHand5 Player5 (Full House)\n\nHand Value: " +players.get(4).getHandValue() + "\n\nBest Hand: \n" + players.get(4).getBestPlayerHand();



        JOptionPane.showMessageDialog(null, out);
        
        // Second Lot of Messages / testing:
        
        // testing deck for different cards.
        // Cards 6 Tests for Straight Not Low not flushed
        Card[] cards6 = {new Card('S',10), new Card('D',11),new Card('H',12),new Card('D',13), new Card('S',10),new Card('S',10)};
        Deck d6 = new Deck(cards6);

        Card[] hand6 = {new Card('H',9), new Card('S',14)};
        p6.setCards(hand6);
        players2.add(p6);

        // Cards 7 Tests for triples
        Card[] cards7 = {new Card('H',2), new Card('S',3),new Card('H',4),new Card('D',12), new Card('S',2),new Card('S',4)};
        Deck d7 = new Deck(cards7);

        Card[] hand7 = {new Card('S',11), new Card('D',2)};
        p7.setCards(hand7);
        players2.add(p7);

        // Cards 8 Tests for 2 Pairs (3 possible Pairs in hand)
        Card[] cards8 = {new Card('H',2), new Card('C',6),new Card('H',4),new Card('C',6), new Card('S',8),new Card('S',4)};
        Deck d8 = new Deck(cards8);

        Card[] hand8 = {new Card('D',2), new Card('S',4)};
        p8.setCards(hand8);
        players2.add(p8);

        // Cards 9 Tests for Full House
        Card[] cards9 = {new Card('H',2), new Card('C',9),new Card('H',4),new Card('C',6), new Card('S',8),new Card('S',4)};
        Deck d9 = new Deck(cards9);

        Card[] hand9 = {new Card('D',10), new Card('S',4)};
        p9.setCards(hand9);
        players2.add(p9);

        // Cards 10 Tests for Flush
        Card[] cards10 = {new Card('H',2), new Card('D',5),new Card('S',10),new Card('H',7), new Card('S',6),new Card('S',4)};
        Deck d10 = new Deck(cards10);

        Card[] hand10 = {new Card('H',3), new Card('D',9)};
        p10.setCards(hand10);
        players2.add(p10);


        // Round 6 Cards (Straight)
        Round round6 = new Round(players, d6);
        round6.setTheFlop();
        round6.setTheTurn();
        round6.setTheRiver();
        // Round 7 Cards (Straight Flush Ace start)
        Round round7 = new Round(players, d7);
        round7.setTheFlop();
        round7.setTheTurn();
        round7.setTheRiver();
        // Round 3 Cards (4 of a kind)
        Round round8 = new Round(players, d8);
        round8.setTheFlop();
        round8.setTheTurn();
        round8.setTheRiver();
        // Round 4 Cards (Full House)
        Round round9 = new Round(players, d9);
        round9.setTheFlop();
        round9.setTheTurn();
        round9.setTheRiver();
        // Round 5 Cards (Flush non-straight)
        Round round10 = new Round(players, d10);
        round10.setTheFlop();
        round10.setTheTurn();
        round10.setTheRiver();

        players2.get(0).setBestPlayerHand(round6);
        players2.get(1).setBestPlayerHand(round7);
        players2.get(2).setBestPlayerHand(round8);
        players2.get(3).setBestPlayerHand(round9);
        players2.get(4).setBestPlayerHand(round10);


        out2 += "Hand6 Player6 (Normal Straight)\n\nHand Value: " +players2.get(0).getHandValue() + "\n\nBest Hand: \n" + players2.get(0).getBestPlayerHand();
        out2 += "\n\nHand7 Player7 (Triples no doubles.)\n\nHand Value: " +players2.get(1).getHandValue() + "\n\nBest Hand: \n" + players2.get(1).getBestPlayerHand();
        out2 += "\n\nHand8 Player8 (Two pair / 3 possible)\n\nHand Value: " +players2.get(2).getHandValue() + "\n\nBest Hand: \n" + players2.get(2).getBestPlayerHand();
        out2 += "\n\nHand9 Player9 (Single Pair)\n\nHand Value: " +players2.get(3).getHandValue() + "\n\nBest Hand: \n" + players2.get(3).getBestPlayerHand();
        out2 += "\n\nHand10 Player10 (No pair possible, high cards.)\n\nHand Value: " +players2.get(4).getHandValue() + "\n\nBest Hand: \n" + players2.get(4).getBestPlayerHand();



        JOptionPane.showMessageDialog(null, out2);

        File outFile = new File("players_test.data");

        try {
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            Pro pr1 = p1;
            Pro pr2 = p2;


            Amateur pa3 = p6;
            Amateur pa4 = p7;
            ArrayList<Player> playersStored = new ArrayList<>();

            playersStored.add(pr1);
            playersStored.add(pr2);
            playersStored.add(pa3);
            playersStored.add(pa4);

            objectOutStream.writeObject(playersStored);

            outStream.close();
        }
        catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be found!",
                    "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }
    }
}
