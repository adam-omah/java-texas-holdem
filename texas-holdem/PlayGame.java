import CardPack.*;
import People.*;
import GameRounds.*;

import javax.swing.*;
import java.util.GregorianCalendar;

public class PlayGame {
    public static void main(String[] args) {
        Pro p1 = new Pro("John","Tralee", new GregorianCalendar(1982,10,10));
        Pro p2 = new Pro("James","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p3 = new Amateur("Adam","Tralee", new GregorianCalendar(1982,10,10));
        Amateur p4 = new Amateur("Gary","Tralee", new GregorianCalendar(1982,10,10));

        Player[] players = {p1,p2,p3,p4};


        GameSession newGame = new GameSession(players);


        JOptionPane.showMessageDialog(null,newGame.toString());

        Round[] rounds = newGame.getRounds();

        String output = "";




        for (int i = 0; i < rounds.length; i++) {

            Card[] roundCards;
            roundCards = rounds[i].getCardDeck().getCards();
            output+= rounds[i].getCardDeck().toString();
            int currentCard = 0;
            Player[] currPlayers = newGame.getPlayers();
            int j;
            for (j = 0; j < 2; j++) {
                // for the moment all players are in the game, later will check how many players remain.
                for (int k = 0; k < players.length; k++) {
                    Card[] temp = {null,null};
                    if (currPlayers[k].getCards()!=null){
                        temp = currPlayers[k].getCards();
                    }
                    temp[j] = roundCards[currentCard];

                    // Deals each player a card by setting their hand to this.
                    currPlayers[k].setCards(temp);
                    currentCard++;
                }
            }
            // each round dealing ends here, players hands change after each round.
            // this is just a reminder that after the full itteration the players hand when called by
            // newgame.tostring will be the same for each round as its retrieving the current hand.

            JOptionPane.showMessageDialog(null,newGame.toString());
        }


        // End of Game, all rounds have finished:
        JOptionPane.showMessageDialog(null,newGame.toString());

    }

}
