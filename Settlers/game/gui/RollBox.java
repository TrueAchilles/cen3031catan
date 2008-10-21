package settlers.game.gui;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class RollBox extends javax.swing.JPanel {
    private JLabel rollBox;

    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new RollBox());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public RollBox() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(100, 100));
            this.setSize(100, 100);
            this.setBackground(new java.awt.Color(192,192,192));
            {
                rollBox = new JLabel();
                this.add(rollBox);
                rollBox.setText("Roll Box");
                rollBox.setPreferredSize(new java.awt.Dimension(93, 16));
                rollBox.setHorizontalAlignment(SwingConstants.CENTER);
                rollBox.setHorizontalTextPosition(SwingConstants.CENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(false);
    }

}
