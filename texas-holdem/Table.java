import GameRounds.GameSession;
import People.Player;

import javax.swing.*;

public class Table extends JFrame {
    private JLabel lblPlayer1;
    private JLabel lblPlayer2;
    private JLabel lblPlayer3;
    private JLabel lblPlayer4;
    private JLabel lblPlayer5;
    private JLabel lblRoundNo;
    private JLabel lblPool;
    private JPanel frmTable;
    private JLabel lblPlayer1Bet;
    private JLabel lblPlayer2Bet;
    private JLabel lblPlayer5Bet;
    private JLabel lblPlayer4Bet;
    private JLabel lblPlayer3Bet;
    private JTextPane txtPlayer3;
    private JTextPane txtPlayer4;
    private JTextPane txtPlayer5;
    private JTextPane txtPlayer2;
    private JTextPane txtPlayer1;
    private JTextPane txtCards;

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

        Player[] players = game.getPlayers();



        for (int i = 0; i < players.length; i++) {
            if(players[i] != null){
                switch (i){
                    case 0:
                        player1 = players[i];
                        updatePlayer(player1);
                        break;
                    case 1:
                        player2 = players[i];
                        updatePlayer(player2);
                        break;
                    case 2:
                        player3 = players[i];
                        updatePlayer(player3);
                        break;
                    case 3:
                        player4 = players[i];
                        updatePlayer(player4);
                        break;
                    case 4:
                        player5 = players[i];
                        updatePlayer(player4);
                        break;
                }
            }
        }

        setVisible(true);
    }

    public void updatePlayer(Player player){
        String text = "";

        text += "\n" + player.getName() + " Funds " + player.getFunds() + "\n\n";
        if (player.getStatus().equals("out")){
            text += "Is Out";
        } else if (player.getStatus().equals("fold")) {
            text += "Folded";
        }else{
            text += "Current Bet: " + player.getCurrentBet();
        }

        if(player == player1){
            txtPlayer1.setText(text);
        }

        if(player == player2){
            txtPlayer2.setText(text);
        }

        if(player == player3){
            txtPlayer3.setText(text);
        }

        if(player == player4){
            txtPlayer4.setText(text);
        }

        if(player == player5){
            txtPlayer5.setText(text);
        }


    }

}
