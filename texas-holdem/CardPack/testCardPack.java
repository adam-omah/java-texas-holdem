package CardPack;

import javax.swing.*;

public class testCardPack {
    public static void main(String[] args) {
        String output ="";
        deck d1 = new deck();

        output += d1;

        JOptionPane.showMessageDialog(null,output);

    }
}
