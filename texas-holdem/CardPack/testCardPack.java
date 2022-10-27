package CardPack;

import javax.swing.*;

public class testCardPack {
    public static void main(String[] args) {
        String output ="";
        Deck d1 = new Deck();

        output += d1;

        JOptionPane.showMessageDialog(null,output);

    }
}
