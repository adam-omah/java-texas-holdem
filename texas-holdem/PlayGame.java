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

        Player[] currPlayers = newGame.getPlayers();


        for (int i = 0; i < rounds.length; i++) {



            if(i == 0 ){
            } else{
                // swapping order of players for blinds etc.
                Player[] temp = {currPlayers[1],currPlayers[2], currPlayers[3], currPlayers[0]};
                currPlayers = temp;
            }
            boolean bettingOver = false;

            // setting blinds:
            int bigBlind = 50;
            int smallBlind = 25;
            int indexOfBlind = 0;

            // get index for big blind:
            for (int j = 0; j < currPlayers.length; j++) {
                if(!currPlayers[j].getStatus().equals("out")){
                    currPlayers[j].setCurrentBet(bigBlind);
                    // remove funds from blind.
                    currPlayers[j].setFunds(currPlayers[j].getFunds() - bigBlind);
                    indexOfBlind = j;
                    break;
                }
            }
            // set small blind:
            for (int j = indexOfBlind; j < currPlayers.length; j++) {
                if(!currPlayers[j].getStatus().equals("out")){
                    currPlayers[j].setCurrentBet(smallBlind);
                    // remove funds from small blind
                    currPlayers[j].setFunds(currPlayers[j].getFunds() - smallBlind);
                    break;
                }
            }



            rounds[i].setCurrentCall(bigBlind);
            rounds[i].setPool(bigBlind + smallBlind);

            DealCards(currPlayers, rounds[i]);
            // each round dealing ends here, players hands change after each round.

            //first round of betting.
            //This is technically not right as in poker the first player to bet should be after
            // the big blind then swing back around to small + big blind.
            /*while(!bettingOver){*/
                int playerDone = 0;
                for (Player player: currPlayers
                ) {
                    // if player is not out they will take a turn.
                    if(!player.getStatus().equals("out")) {
                        player.setStatus("playing");
                        PlayerTurn newTurn = new PlayerTurn(player, rounds[i]);
                        newTurn.setTurnTaken(false);
                        while (!newTurn.getTurnTaken()) {
                            // wait for turn to be taken.
                        }
                    }
                }

                // checks if each player is up to the current bet to continue.
                // if players current bet is 0
                for (Player player: currPlayers
                     ) {
                    if (player.getCurrentBet() == rounds[i].getCurrentCall()|| player.getStatus().equals("fold") || player.getStatus().equals("out") || player.getStatus().equals("allin")){
                        playerDone++;
                    }
                }
                JOptionPane.showMessageDialog(null,"players done: " + playerDone);
                /*if(playerDone == currPlayers.length){
                    bettingOver = true;
                }
            }*/


            // the flop.

            // second round of betting.

            // the street or the turn.

            // third round of betting.

            // the river.

            //last round of betting.

            // check winner!!


            // this is just a reminder that after the full itteration the players hand when called by
            // newgame.tostring will be the same for each round as its retrieving the current hand.

            JOptionPane.showMessageDialog(null,newGame.toString());
        }


        // End of Game, all rounds have finished:
        JOptionPane.showMessageDialog(null,newGame.toString());

    }

    private static void DealCards(Player[] currPlayers, Round round) {
        Card[] roundCards;
        roundCards = round.getCardDeck().getCards();
        int currentCard = 0;

        int j;
        for (j = 0; j < 2; j++) {
            // for the moment all players are in the game, later will check how many players remain.
            for (int k = 0; k < currPlayers.length; k++) {
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
    }

}
