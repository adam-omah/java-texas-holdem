import CardPack.*;
import People.*;
import GameRounds.*;

import javax.swing.*;
import java.util.ArrayList;
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



        Player[] startingPlayers = newGame.getPlayers();

        Table newTable = new Table(newGame);

        ArrayList<Player> activePlayers = new ArrayList<Player>();
        for (Player player:startingPlayers
             ) {
            activePlayers.add(player);
        }


        for (int i = 0; i < rounds.length; i++) {

            activePlayers.get(1).setStatus("out");
            //check if player has funds to play.
            for (Player player:startingPlayers
                 ) {
                if(player.getFunds() <= 0 || player.getStatus().equals("out")){
                    player.setStatus("out");
                    newTable.updatePlayer(player);
                    activePlayers.remove(player);
                }else{
                    player.setStatus("newTurn");
                    player.setCurrentBet(0);
                    newTable.updatePlayer(player);
                }
            }

            output+="Before:\n";
            for (Player player:activePlayers
            ) {
                output += player.toString() + "\n";
            }

            if( i != 0){
                Player temp;
                int small = activePlayers.indexOf(rounds[i].getSmallBlind());

                if(small != -1){
                    temp = activePlayers.get(small);

                    if(activePlayers.get(0) == temp){
                        // moving small blind to back of the array
                        activePlayers.remove(temp);
                        activePlayers.add(temp);
                    }
                }
            }

            output+="\n\nafter\n";
            for (Player player:activePlayers
                 ) {
                output += player.toString() + "\n";
            }



            boolean bettingOver = false;

            // setting blinds:
            int bigBlind = 50;
            int smallBlind = 25;

            // get index for small blind:
            for (int j = 0; j < activePlayers.size(); j++) {
                if(!activePlayers.get(j).getStatus().equals("out")){

                    activePlayers.get(j).setCurrentBet(smallBlind);
                    // remove funds from small blind
                    activePlayers.get(j).setFunds(activePlayers.get(j).getFunds() - smallBlind);
                    newTable.updatePlayer(activePlayers.get(j));
                    rounds[i].setBlindIndex(j);
                    rounds[i].setSmallBlind(activePlayers.get(j));
                    break;
                }
            }
            // set big blind:
            for (int j = rounds[i].getBlindIndex() +1; j < activePlayers.size(); j++) {
                if(!activePlayers.get(j).getStatus().equals("out")){
                    activePlayers.get(j).setCurrentBet(bigBlind);
                    // remove funds from blind.
                    activePlayers.get(j).setFunds(activePlayers.get(j).getFunds() - bigBlind);
                    newTable.updatePlayer(activePlayers.get(j));

                    rounds[i].setBlindIndex(j);
                    break;
                }
            }

            output+="\nBlind ind +1 : " + (rounds[i].getBlindIndex() +1) +"\n\nPlayer is: " +activePlayers.get((rounds[i].getBlindIndex() +1)).getName();
            JOptionPane.showMessageDialog(null, output);



            rounds[i].setCurrentCall(bigBlind);
            rounds[i].setPool(bigBlind + smallBlind);

            //set Up table.
            newTable.setUpTable(rounds,i);



            DealCards(activePlayers, rounds[i]);
            // each round dealing ends here, players hands change after each round.

            //first round of betting.
            //This is technically not right as in poker the first player to bet should be after
            // the big blind then swing back around to small + big blind.


            for (int j = (rounds[i].getBlindIndex() + 1); j < activePlayers.size(); j++) {
                // if player is not out they will take a turn.
                if(!activePlayers.get(j).getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(activePlayers.get(j), rounds[i]);
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(startingPlayers[j]);
                }
            }
            for (int j = 0; j < (rounds[i].getBlindIndex() + 1); j++) {
                // if player is not out they will take a turn.
                if(!activePlayers.get(j).getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(activePlayers.get(j), rounds[i]);
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(startingPlayers[j]);
                }
            }


            playersBetting(rounds[i], activePlayers, newTable);

            // the flop.



            rounds[i].setTheFlop();
            newTable.updateFlop(rounds[i]);


            // second round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds[i], activePlayers, newTable);

            // the street or the turn.
            rounds[i].setTheTurn();
            newTable.updateTheTurn(rounds[i]);

            // third round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds[i], activePlayers, newTable);


            // the river.
            rounds[i].setTheRiver();
            newTable.updateTheRiver(rounds[i]);

            //last round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds[i], activePlayers, newTable);

            // Show Hands:

            for (Player player: startingPlayers
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




    private static void newPlayerTurns(ArrayList<Player> activePlayers) {
        for (Player player: activePlayers
             ) {
            if (player.getStatus().equals("playing")){
                player.setStatus("newTurn");
            }
        }
    }

    private static void playersBetting(Round round, ArrayList<Player> activePlayers, Table table) {
        // if all players not "Done" enter new loop until all are done.
        boolean allPlayersDone = false;
        // checks if each player is up to the current bet to continue.
        int playerDone = 0;


        while(!allPlayersDone){
            for (Player player: activePlayers
            ) {
                if ((player.getCurrentBet() == round.getCurrentCall() && !player.getStatus().equals("newTurn")) || player.getStatus().equals("fold") || player.getStatus().equals("out") || player.getStatus().equals("allin")) {
                    playerDone++;
                }
            }

            if (playerDone == activePlayers.size()){
                allPlayersDone = true;
            }else{
                for (Player player: activePlayers
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

    private static void DealCards(ArrayList<Player> activePlayers, Round round) {
        Card[] roundCards = round.getCardDeck().getCards();

        int j;
        for (j = 0; j < 2; j++) {
            // changed to active players as now the array is a list and can vary.
            for (int k = 0; k < activePlayers.size(); k++) {
                Card[] temp = {null,null};
                if (activePlayers.get(k).getCards()!=null){
                    temp = activePlayers.get(k).getCards();
                }
                temp[j] = roundCards[round.getCardIndex()];

                // Deals each player a card by setting their hand to this.
                activePlayers.get(k).setCards(temp);
                round.setCardIndex(round.getCardIndex()+1);
            }
        }
    }

}
