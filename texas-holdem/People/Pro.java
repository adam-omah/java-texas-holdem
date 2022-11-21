/**
 * An instantiable class which defines a pro player.
 * Pro extends Player class
 * There is not much difference between Pro and Amateur
 * Although I had hoped to make a rankings system that allowed
 * multiple players and ranking in each category.
 * @author Adam O'Mahony.
 */


package People;

import CardPack.Card;

import java.util.Arrays;
import java.util.GregorianCalendar;


public class Pro extends Player {

    private int funds;
    private int ranking;

    private Card[] cards;
    private int currentBet;

    private String status;

    /**
     * Pro 3 argument Constructor
     * @param name The Name of the player.
     * @param address The player's address.
     * @param dateOfBirth the players date of birth.
     * ranking was never really implemented.
     */
    public Pro(String name, String address, GregorianCalendar dateOfBirth) {
        super(name, address, dateOfBirth);
        setFunds(750);
        setRanking(playerTotal);
        playerTotal++;
        setStatus("playing");
        setCurrentBet(0);
        setRoundWins(0);
        setGameWins(0);
    }

    /**
     * Method to override the toString function.
     * @return a String value containing the information of a player.
     */
    @Override
    public String toString() {
        return "pro{" +
                "funds=" + funds +
                ", ranking=" + ranking +
                ", cards=" + Arrays.toString(cards) +
                "} " + super.toString();
    }
    /**
     * Method to return the current bet of the player.
     * @return a Integer value containing the current bet.
     */
    @Override
    public int getCurrentBet() {
        return currentBet;
    }

    /**
     * Method to return the player's status
     * @return a String value containing the status of the player.
     * players can have the status of 'out', 'allin', 'fold', 'playing'
     * this value is used in the logic of the game.
     */
    @Override
    public String getStatus() {
        return status;
    }


    /**
     * Method to Set the player's status
     * @param status Sets a String value containing the status of the player.
     * players can have the status of 'out', 'allin', 'fold', 'playing'
     * this value is used in the logic of the game.
     */
    // Status added to make it more visible if player is folded, all in or still playing the game.
    @Override
    public void setStatus(String status) {
        if(status.equals("playing")|| status.equals("newTurn")|| status.equals("fold") || status.equals("allin") || status.equals("out")){
            this.status = status;
        }else
            this.status = "fold";

    }
    /**
     * Method to Set the player's the current bet of the player
     * @param currentBet Integer value containing the current bet.
     */

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    /**
     * Method to return the current Cards of the player.
     * @return an Array of Card Objects which represent the 2 cards in a players hand..
     */
    @Override
    public Card[] getCards() {
        return cards;
    }
    /**
     * Method to set the current Cards of the player.
     * @param cards an Array of Card Objects which represent the 2 cards in a players hand.
     */

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    /**
     * Method to set the current Funds of the player.
     * @param funds an Integer Representing the funds a player has available.
     */

    public void setFunds(int funds) {
        this.funds = Math.max(funds, 0);
    }

    /**
     * Method to set the current rank of the player.
     * @param ranking an Integer Representing the rank of a player.
     */

    public void setRanking(int ranking) {
        if(ranking >= 1){
            this.ranking = ranking;
        }else{
            ranking = playerTotal;
        }
    }


    /**
     * Method to set the current Category of the player.
     * @return a String Representing the Category of a player. (Pro or  Amateur).
     */
    @Override
    public String getCategory() {
        return "Pro";
    }
    /**
     * Method to get the current Funds of the player.
     * @return returns the current funds of a player.
     */
    @Override
    public int getFunds() {
        return funds;
    }
    /**
     * Method to get the Rank of the player.
     * @return returns the current rank of a player.
     */

    @Override
    public int getRanking() {
        return ranking;
    }

}
