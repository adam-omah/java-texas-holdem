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


        Round[] rounds = newGame.getRounds();

        String output = "";

        Player[] currPlayers = newGame.getPlayers();

        Table newTable = new Table(newGame);




        for (int i = 0; i < rounds.length; i++) {


            //check if player has funds to play.
            for (Player player:currPlayers
                 ) {
                if(player.getFunds() <= 0){
                    player.setStatus("out");
                }else{
                    player.setStatus("newTurn");
                    player.setCurrentBet(0);
                    newTable.updatePlayer(player);
                }
            }


            if (i != 0) {
                // swapping order of players for blinds etc.
                Player[] temp = {currPlayers[1],currPlayers[2], currPlayers[3], currPlayers[0]};
                currPlayers = temp;
            }
            boolean bettingOver = false;

            // setting blinds:
            int bigBlind = 50;
            int smallBlind = 25;

            // get index for small blind:
            for (int j = 0; j < currPlayers.length; j++) {
                if(!currPlayers[j].getStatus().equals("out")){

                    currPlayers[j].setCurrentBet(smallBlind);
                    // remove funds from small blind
                    currPlayers[j].setFunds(currPlayers[j].getFunds() - smallBlind);
                    newTable.updatePlayer(currPlayers[j]);
                    rounds[i].setBlindIndex(j);
                    break;
                }
            }
            // set big blind:
            for (int j = rounds[i].getBlindIndex() +1; j < currPlayers.length; j++) {
                if(!currPlayers[j].getStatus().equals("out")){
                    currPlayers[j].setCurrentBet(bigBlind);
                    // remove funds from blind.
                    currPlayers[j].setFunds(currPlayers[j].getFunds() - bigBlind);
                    newTable.updatePlayer(currPlayers[j]);

                    rounds[i].setBlindIndex(j);
                    break;
                }
            }



            rounds[i].setCurrentCall(bigBlind);
            rounds[i].setPool(bigBlind + smallBlind);

            //set Up table.
            newTable.setUpTable(rounds,i);



            DealCards(currPlayers, rounds[i]);
            // each round dealing ends here, players hands change after each round.

            //first round of betting.
            //This is technically not right as in poker the first player to bet should be after
            // the big blind then swing back around to small + big blind.


            for (int j = (rounds[i].getBlindIndex() + 1); j < currPlayers.length; j++) {
                // if player is not out they will take a turn.
                if(!currPlayers[j].getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(currPlayers[j], rounds[i]);
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(currPlayers[j]);
                }
            }
            for (int j = 0; j < rounds[i].getBlindIndex() + 1; j++) {
                // if player is not out they will take a turn.
                if(!currPlayers[j].getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(currPlayers[j], rounds[i]);
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(currPlayers[j]);
                }
            }


            /*for (Player player: currPlayers
            ) {
                // if player is not out they will take a turn.
                if(!player.getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(player, rounds[i]);
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(player);
                }
            }*/

            playersBetting(rounds[i], currPlayers, newTable);

            // the flop.



            rounds[i].setTheFlop();
            newTable.updateFlop(rounds[i]);


            // second round of betting.
                //set players to new turn
                newPlayerTurns(currPlayers);
                // set betting again after flop.
                playersBetting(rounds[i], currPlayers, newTable);

            // the street or the turn.
            rounds[i].setTheTurn();
            newTable.updateTheTurn(rounds[i]);

            // third round of betting.
                //set players to new turn
                newPlayerTurns(currPlayers);
                // set betting again after flop.
                playersBetting(rounds[i], currPlayers, newTable);


            // the river.
            rounds[i].setTheRiver();
            newTable.updateTheRiver(rounds[i]);

            //last round of betting.
                //set players to new turn
                newPlayerTurns(currPlayers);
                // set betting again after flop.
                playersBetting(rounds[i], currPlayers, newTable);

            // Show Hands:

            for (Player player: currPlayers
                 ) {
                newTable.showPlayersHands(player);
            }

            // check winner!!



            // this is just a reminder that after the full itteration the players hand when called by
            // newgame.tostring will show the same hand for each round as it's retrieving the current hand.
            JOptionPane.showMessageDialog(null,"Round Over!!!\n" + newGame.toString());
        }


        // End of Game, all rounds have finished:
        JOptionPane.showMessageDialog(null,"Game over!!!\n" + newGame.toString());

    }




    private static void newPlayerTurns(Player[] currPlayers) {
        for (Player player: currPlayers
             ) {
            if (player.getStatus().equals("playing")){
                player.setStatus("newTurn");
            }
        }
    }

    private static void playersBetting(Round round, Player[] currPlayers, Table table) {
        // if all players not "Done" enter new loop until all are done.
        boolean allPlayersDone = false;
        // checks if each player is up to the current bet to continue.
        int playerDone = 0;


        while(!allPlayersDone){
            for (Player player: currPlayers
            ) {
                if ((player.getCurrentBet() == round.getCurrentCall() && !player.getStatus().equals("newTurn")) || player.getStatus().equals("fold") || player.getStatus().equals("out") || player.getStatus().equals("allin")) {
                    playerDone++;
                }
            }

            if (playerDone == currPlayers.length){
                allPlayersDone = true;
            }else{
                for (Player player: currPlayers
                ) {
                    // if player is not out they will take a turn.
                    if(player.getStatus().equals("playing") || player.getStatus().equals("newTurn")) {
                        player.setStatus("playing");
                        PlayerTurn newTurn = new PlayerTurn(player, round);
                        newTurn.setTurnTaken(false);
                        while (!newTurn.getTurnTaken()) {
                            // wait for turn to be taken.
                        }
                        table.updatePlayer(player);
                    }
                }
                playerDone = 0;
            }

        }
    }

    private static void DealCards(Player[] currPlayers, Round round) {
        Card[] roundCards = round.getCardDeck().getCards();

        int j;
        for (j = 0; j < 2; j++) {
            // for the moment all players are in the game, later will check how many players remain.
            for (int k = 0; k < currPlayers.length; k++) {
                Card[] temp = {null,null};
                if (currPlayers[k].getCards()!=null){
                    temp = currPlayers[k].getCards();
                }
                temp[j] = roundCards[round.getCardIndex()];

                // Deals each player a card by setting their hand to this.
                currPlayers[k].setCards(temp);
                round.setCardIndex(round.getCardIndex()+1);
            }
        }
    }

}
