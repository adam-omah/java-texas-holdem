import People.Player;

import javax.swing.*;

public class PlayerTurn extends JFrame{
    private JPanel frmPlayerTurn;
    private JLabel lblPlayerName;
    private JLabel lblCurrentCall;
    private JButton btnCall;
    private JButton btnFold;
    private JButton btnBet;
    private JButton btnCheck;

    public PlayerTurn(Player player,int currCall){

        setContentPane(frmPlayerTurn);
        setTitle("Please Take your Turn");
        setSize(450,450);

        lblPlayerName.setText(player.getName());
        if (currCall == player.getCurrentBet()){
            lblCurrentCall.setText("You Can Check Or Bet");
        }else{
            lblCurrentCall.setText("Current Call is : " + (currCall - player.getCurrentBet()) +"\nYou can Call, Raise Or Fold.");
        }


        setVisible(true);
    }
}
