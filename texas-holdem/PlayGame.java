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



        ArrayList<Player> activePlayers = new ArrayList<Player>();
        Player[] startingPlayers = {p1,p2,p3,p4};

        for (Player player:startingPlayers
        ) {
            activePlayers.add(player);
        }
        GameSession newGame = new GameSession(activePlayers);


        ArrayList<Round> rounds = newGame.getRounds();

        String output = "";


        Table newTable = new Table(newGame);
        int currentRound;



        while(!newGame.getGameOver()) {
            currentRound = newGame.getCurrentRound();


            if( newGame.getCurrentRound() != 0){
                Player temp;
                int small = activePlayers.indexOf(rounds.get(newGame.getCurrentRound()).getSmallBlind());

                if(small != -1){
                    temp = activePlayers.get(small);

                    if(activePlayers.get(0) == temp){
                        // moving small blind to back of the array
                        activePlayers.remove(temp);
                        activePlayers.add(temp);
                    }
                }
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
                    newTable.updatePlayer(activePlayers.get(j), rounds.get(currentRound));
                    rounds.get(currentRound).setBlindIndex(j);
                    rounds.get(currentRound).setSmallBlind(activePlayers.get(j));
                    break;
                }
            }
            // set big blind:
            for (int j = rounds.get(currentRound).getBlindIndex() +1; j < activePlayers.size(); j++) {
                if(!activePlayers.get(j).getStatus().equals("out")){
                    activePlayers.get(j).setCurrentBet(bigBlind);
                    // remove funds from blind.
                    activePlayers.get(j).setFunds(activePlayers.get(j).getFunds() - bigBlind);
                    newTable.updatePlayer(activePlayers.get(j), rounds.get(currentRound));

                    rounds.get(currentRound).setBlindIndex(j);
                    break;
                }
            }


            rounds.get(currentRound).setCurrentCall(bigBlind);
            rounds.get(currentRound).setPool(bigBlind + smallBlind);

            //set Up table.
            newTable.setUpTable(rounds,currentRound);



            DealCards(activePlayers, rounds.get(currentRound));
            // each round dealing ends here, players hands change after each round.

            //first round of betting.
            //This is technically not right as in poker the first player to bet should be after
            // the big blind then swing back around to small + big blind.


            for (int j = (rounds.get(currentRound).getBlindIndex() + 1); j < activePlayers.size(); j++) {
                // if the player count is 2 this loop will immediately exit, found this via testing.
                // however this is good as it ends up with the game behavior that I want.
                // as now the small blind takes the first call (start of next section).


                // if player is not out they will take a turn.
                if(!activePlayers.get(j).getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(activePlayers.get(j), rounds.get(currentRound));
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(activePlayers.get(j), rounds.get(currentRound));

                }

            }
            for (int j = 0; j < (rounds.get(currentRound).getBlindIndex() + 1); j++) {
                // if player is not out they will take a turn.
                if(!activePlayers.get(j).getStatus().equals("out")) {

                    PlayerTurn newTurn = new PlayerTurn(activePlayers.get(j), rounds.get(currentRound));
                    newTurn.setTurnTaken(false);
                    while (!newTurn.getTurnTaken()) {
                        // wait for turn to be taken.
                    }

                    newTable.updatePlayer(activePlayers.get(j), rounds.get(currentRound));
                }
            }


            playersBetting(rounds.get(currentRound), activePlayers, newTable);


            // Check if only one player remains.
            if(!onlyOnePlayer(activePlayers)){
                // the flop.
                rounds.get(currentRound).setTheFlop();
                newTable.updateFlop(rounds.get(currentRound));

                // second round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds.get(currentRound), activePlayers, newTable);

                // the street or the turn.
                rounds.get(currentRound).setTheTurn();
                newTable.updateTheTurn(rounds.get(currentRound));

                // third round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds.get(currentRound), activePlayers, newTable);


                // the river.
                rounds.get(currentRound).setTheRiver();
                newTable.updateTheRiver(rounds.get(currentRound));

                //last round of betting.
                //set players to new turn
                newPlayerTurns(activePlayers);
                // set betting again after flop.
                playersBetting(rounds.get(currentRound), activePlayers, newTable);

                // Show Hands:

                for (Player player: startingPlayers
                ) {
                    newTable.showPlayersHands(player);
                }

                // check round winner!!
                for (Player player:activePlayers
                     ) {
                    if(player.getStatus().equals("playing") || player.getStatus().equals("allin")){
                        player.setBestPlayerHand(rounds.get(currentRound));
                    }
                }


            }else{
                onePlayerleft(activePlayers, newGame);
            }

            // Check game over! And if Player can continue.
            if(!isGameOver(activePlayers)){
                //check if player has funds to play.
                for (Player player:startingPlayers
                ) {
                    if(player.getFunds() <= 0 || player.getStatus().equals("out")){
                        player.setStatus("out");
                        newTable.updatePlayer(player, rounds.get(currentRound));
                        if (player.bestPlayerHand != null){
                            player.bestPlayerHand.clear();
                        }

                        activePlayers.remove(player);
                    }else{
                        player.setStatus("newTurn");
                        player.setCurrentBet(0);
                        newTable.updatePlayer(player, rounds.get(currentRound));
                    }
                }
                // Create New round for players
                Round round = new Round(activePlayers);
                newGame.getRounds().add(round);
                newGame.setCurrentRound(newGame.getCurrentRound() + 1);
            }else{
                newGame.setGameOver(true);
                // assign over all winner.
            }



            // this is just a reminder that after the full itteration the players hand when called by
            // newgame.tostring will show the same hand for each round as it's retrieving the current hand.
            JOptionPane.showMessageDialog(null,"Round Over!!!\n" + newGame.toString());
        }


        // End of Game, all rounds have finished:
        JOptionPane.showMessageDialog(null,"Game over!!!\n" + newGame.toString());

    }

    private static boolean isGameOver( ArrayList<Player> activePlayers) {
        boolean result = false;
        int playerCount =0;
        for (Player player: activePlayers
             ) {
            if (player.getFunds() > 0){
                playerCount++;
            }
        }

        if (playerCount <=1){
            result = true;
        }

        return result;
    }

    private static void onePlayerleft(ArrayList<Player> activePlayers, GameSession newGame) {
        Player winner = null;

        for (Player player:
             activePlayers) {
            if(player.getStatus().equals("playing")||player.getStatus().equals("allin")){
                winner = player;
            }
        }
        if (newGame.getRoundWinners() == null && winner != null){
            ArrayList<String> winners = new ArrayList<>();
            winners.add(winner.getName());
            newGame.setRoundWinners(winners);
        } else if(winner != null && newGame.getRoundWinners() == null){
            newGame.getRoundWinners().add(winner.getName());
        }
    }

    private static boolean onlyOnePlayer(ArrayList<Player> activePlayers) {
        int playersPlaying= 0;
        boolean result = false;


        for (Player player:activePlayers
             ) {
            if (player.getStatus().equals("playing") || player.getStatus().equals("allin")){
                playersPlaying++;
            }
        }
        if(playersPlaying == 1){
            result = true;
        }
        return  result;
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
                        table.updatePlayer(player,round);
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
