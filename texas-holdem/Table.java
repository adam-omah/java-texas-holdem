import CardPack.Card;
import GameRounds.GameSession;
import GameRounds.Round;
import People.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Table extends JFrame {
    private JLabel lblRoundNo;
    private JLabel lblPool;
    private JPanel frmTable;

    private JTextPane txtPlayer3;
    private JTextPane txtPlayer4;
    private JTextPane txtPlayer5;
    private JTextPane txtPlayer2;
    private JTextPane txtPlayer1;
    private JTextPane txtCards;
    private JButton btnEndGame;

    private String cardsText = "";

    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;

    public Table(GameSession game){
        setContentPane(frmTable);
        setTitle("No Limit Texas Holdem!");
        setSize(700,450);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        ArrayList<Player> players = game.getPlayers();
        ArrayList<Round> rounds = game.getRounds();


        for (int i = 0; i < players.size(); i++) {
            if(players.get(i) != null){
                switch (i){
                    case 0:
                        player1 = players.get(i);
                        updatePlayer(player1, rounds.get(0));
                        break;
                    case 1:
                        player2 = players.get(i);
                        updatePlayer(player2, rounds.get(0));
                        break;
                    case 2:
                        player3 = players.get(i);
                        updatePlayer(player3, rounds.get(0));
                        break;
                    case 3:
                        player4 = players.get(i);
                        updatePlayer(player4, rounds.get(0));
                        break;
                    case 4:
                        player5 = players.get(i);
                        updatePlayer(player4, rounds.get(0));
                        break;
                }
            }
        }

        setVisible(true);
        btnEndGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setGameOver(true);
            }
        });
    }

    public void updatePlayer(Player player , Round round){
        String text = "";

        text += "\n" + player.getName() + " Funds " + player.getFunds() + "\n\n";
        if (player.getStatus().equals("out")){
            text += "Is Out";
        } else if (player.getStatus().equals("fold")) {
            text += "Folded";
        } else if (player.getStatus().equals("allin")) {
            text += "Current Bet: " + player.getCurrentBet() + " All-In!";
        }else {
            text += "Current Bet: " + player.getCurrentBet();
        }

        lblPool.setText("Pool: " + round.getPool());
        setPlayerText(player, text);
    }



    public void showPlayersHands(Player player){
        String text = "";

        text += "\n" + player.getName() + " Funds " + player.getFunds() + "\n\n";
        if (player.getStatus().equals("out")){
            text += "Is Out";
        } else if (player.getStatus().equals("fold")) {
            text += "Folded";
        }else if (player.getStatus().equals("allin")) {
            text += "Current Bet: " + player.getCurrentBet() + " All-In!\n\nCards Are: ";
            Card[] cards = player.getCards();
            for (Card card:cards
            ) {
                text += "\n" + card.toString();
            }
        }else {
            text += "Current Bet: " + player.getCurrentBet() + "\n\nCards Are: ";
            Card[] cards = player.getCards();
            for (Card card:cards
                 ) {
                text += "\n" + card.toString();
            }
        }

        setPlayerText(player, text);

    }

    public void updateFlop(Round round){
        // get the flop from the round given.
        Card[] flop = round.getTheFlop();

        cardsText += "\nThe Flop:";
        for (Card card:
             flop) {
            cardsText += "\n" + card.toString();
        }

        txtCards.setText(cardsText);
    }

    public void updateTheTurn(Round round){
        Card c1 = round.getTheTurn();
        cardsText += "\n\nThe Turn:\n" + c1.toString();

        txtCards.setText(cardsText);
    }

    public void updateTheRiver(Round round){
        Card c1 = round.getTheRiver();
        cardsText += "\n\nThe River:\n" + c1.toString();

        txtCards.setText(cardsText);
    }

    public void setUpTable(ArrayList<Round> rounds, int i){
        lblPool.setText("Pool: " + rounds.get(i).getPool());
        lblRoundNo.setText("Round " + (i+1));

        cardsText = "";
        txtCards.setText(cardsText);
    }

    private void setPlayerText(Player player, String text) {
        if(player == player1){
            txtPlayer1.setText(text);
        }else if(player == player2){
            txtPlayer2.setText(text);
        }else if(player == player3){
            txtPlayer3.setText(text);
        }else if(player == player4){
            txtPlayer4.setText(text);
        }else if(player == player5){
            txtPlayer5.setText(text);
        }
    }

}
