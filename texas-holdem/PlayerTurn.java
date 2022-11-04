import People.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerTurn extends JFrame{
    private JPanel frmPlayerTurn;
    private JLabel lblPlayerName;
    private JLabel lblCurrentCall;
    private JButton btnCall;
    private JButton btnFold;
    private JButton btnBet;
    private JButton btnAllin;

    private boolean turnTaken;

    public boolean getTurnTaken() {
        return turnTaken;
    }

    public void setTurnTaken(boolean turnTaken) {
        this.turnTaken = turnTaken;
    }

    public PlayerTurn(Player player, int currCall){

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
        btnBet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // bet action:
                // player input for bet:
                String bet = JOptionPane.showInputDialog("Please Make A Bet!");
                boolean isValidBet = false;

                while(!isValidBet){
                    int correctChars = 0;
                    for (int i = 0; i < bet.length(); i++) {
                        if(bet.charAt(i) >= '0' && bet.charAt(i) <= '9'){
                            correctChars++;
                        }
                    }
                    if(correctChars == bet.length()){
                        isValidBet = true;
                    }else
                        bet = JOptionPane.showInputDialog("Invalid Bet! bets must be positive numbers only!");
                }
                // change bet into int:
                int betInt = Integer.parseInt(bet);

                if (betInt >= player.getFunds()){
                    // player is gone all in
                    betInt = player.getFunds();
                }

                player.setCurrentBet(betInt);
                setTurnTaken(true);
                setVisible(false);
            }
        });
    }
}
