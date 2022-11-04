import GameRounds.Round;
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

    public PlayerTurn(Player player, Round round){

        setContentPane(frmPlayerTurn);
        setTitle("Please Take your Turn");
        setSize(450,450);

        lblPlayerName.setText(player.getName());
        if (round.getCurrentCall() == player.getCurrentBet()){
            lblCurrentCall.setText("You Can Check Or Bet");
        }else{
            lblCurrentCall.setText("Current Call is : " + (round.getCurrentCall() - player.getCurrentBet()) +"\nYou can Call, Raise Or Fold.");
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
                    player.setStatus("allin");

                    // checks if bet is greater than current call.
                    if(betInt >= round.getCurrentCall()){
                        //set new call
                        round.setCurrentCall(betInt);
                    }

                }else{
                    // checks if bet is greater than current call.
                    if(betInt >= round.getCurrentCall()){
                        //set new call
                        round.setCurrentCall(betInt);
                    }else{
                        if (round.getCurrentCall() <= player.getFunds()){
                            // player betting less than current call turns into a call.
                            betInt = round.getCurrentCall();
                        }
                    }
                }

                // checks if bet is greater than current call.

                //set bet to new bet
                player.setCurrentBet(player.getCurrentBet() + betInt);
                // remove bet from player
                player.setFunds(player.getFunds() - betInt);

                //add bet to pool:
                round.setPool(round.getPool() + betInt);

                //checks again if player is all in:
                if (player.getFunds() == 0){
                    player.setStatus("allin");
                }

                setTurnTaken(true);
                setVisible(false);
            }
        });
        btnCall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call button / check.


                if(player.getCurrentBet() == round.getCurrentCall()){
                    //player checks.
                    setTurnTaken(true);
                    setVisible(false);
                }else{
                    //check player funds first.
                    if (player.getFunds() >= round.getCurrentCall()){

                        player.setFunds(round.getCurrentCall() - player.getCurrentBet());

                        //add bet to pool:
                        round.setPool(round.getPool() + (round.getCurrentCall() - player.getCurrentBet()));

                        player.setCurrentBet(round.getCurrentCall());




                    }else {
                        // player must go all in to call if not enough funds.
                        player.setStatus("allin");
                        
                        //add bet to pool:
                        round.setPool(round.getPool() + player.getFunds());

                        // set new current bet for player:
                        player.setCurrentBet(player.getCurrentBet() + player.getFunds());
                        player.setFunds(0);

                    }
                    setTurnTaken(true);
                    setVisible(false);
                }




            }
        });
        btnFold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // player Folds!
                player.setStatus("fold");

                setTurnTaken(true);
                setVisible(false);

            }
        });
    }
}
