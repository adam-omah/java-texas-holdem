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
    private JLabel lblPlayerFunds;
    private JLabel lblCurrentBet;

    private boolean turnTaken;

    public boolean getTurnTaken() {
        return turnTaken;
    }

    public void setTurnTaken(boolean turnTaken) {
        this.turnTaken = turnTaken;
    }

    public PlayerTurn(Player player, Round round){

        setContentPane(frmPlayerTurn);
        setTitle("please take your turn");
        setSize(450,450);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        lblPlayerName.setText(player.getName() + " " + player.getStatus());
        lblPlayerFunds.setText("Your Funds: " + player.getFunds());
        lblCurrentBet.setText("Your Current Bet is: " + player.getCurrentBet());
        if (round.getCurrentCall() == player.getCurrentBet()){
            lblCurrentCall.setText("You Can Check Or Bet");
        }else{
            lblCurrentCall.setText("To Call is : " + (round.getCurrentCall() - player.getCurrentBet()) +"\nYou can Call, Raise Or Fold.");
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
                    if (bet.equals(null)){
                        bet = JOptionPane.showInputDialog("Invalid Bet! bets must be positive numbers only!");
                    }else{

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
                }
                // change bet into int:
                int betInt = Integer.parseInt(bet);

                if (betInt >= player.getFunds()){
                    // player is gone all in
                    betInt = player.getFunds();
                    player.setStatus("allin");

                    // checks if bet is greater than current call.
                    if((betInt + player.getCurrentBet()) >= round.getCurrentCall()){
                        //set new call
                        round.setCurrentCall(betInt + player.getCurrentBet());
                    }

                }else{
                    // checks if bet is greater than current call.
                    if((betInt + player.getCurrentBet()) >= round.getCurrentCall()){
                        //set new call
                        round.setCurrentCall(player.getCurrentBet() + betInt);
                    }else{
                        if (round.getCurrentCall() <= (player.getFunds() + player.getCurrentBet())){
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
                    if ((player.getFunds() + player.getCurrentBet()) >= round.getCurrentCall()){

                        player.setFunds(player.getFunds() - (round.getCurrentCall() - player.getCurrentBet()));

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
        btnAllin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //player goes all in.

                // checks if players all in is greater than current call.
                if((player.getCurrentBet() + player.getFunds()) >= round.getCurrentCall()){
                    // set new call
                    round.setCurrentCall(player.getCurrentBet()+ player.getFunds());
                }

                // set new bet for player.
                player.setCurrentBet(player.getCurrentBet()+ player.getFunds());
                //remove funds from player.
                player.setFunds(0);

                player.setStatus("allin");

                setTurnTaken(true);
                setVisible(false);
            }
        });
    }
}
