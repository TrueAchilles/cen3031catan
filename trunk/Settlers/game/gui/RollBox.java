package settlers.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import settlers.game.GameState;

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
	private JTextArea rollHistory;
    Image[] img = new Image[6]; // dice images are loaded into here. dice side 1 is img[0], side 2 is img[1], and so on
    MediaTracker mt;
    
    GameBoard parent;
    
    Random rand;
	int i;
	private boolean rolled;
	
	int dice1, dice2;
	private int rollValue;
	
	public RollBox(GameBoard _parent) {
		super();
		initGUI();
		
		parent = _parent;
		
		rand = new Random();
		
		rolled = false;
		
	    mt = new MediaTracker(this);
	    for (i = 0; i <= 5; i++) {
			img[i] = new ImageIcon(getClass().getResource("/settlers/game/images/d"+(i+1)+".png")).getImage();
			mt.addImage(img[i],i);
		}
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(120, 120));
			this.setSize(120, 120);
			
			
			
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
	
	public void update(Graphics g){
			paint(g);
	}
	
    public void paint(Graphics g) {
		this.setBorder(new LineBorder(Color.black, 3));
    	if(rolled)
    	{
			for (int x = 0; x < 10; x++) {
				if (img[rand.nextInt(6)] != null) 
				{ // makes sure there's something in each of the array positions
					g.drawImage(img[rand.nextInt(6)],0,30,this); // where the first die is displayed in the frame
					g.drawImage(img[rand.nextInt(6)],60,50,this); // where the second die is displayed in the frame
					this.validate();
					try {
						Thread.currentThread();
						Thread.sleep(75);
						} // length of pauses between graphic cycles
					catch (InterruptedException e) {e.printStackTrace();}
				}  
			}
			g.drawImage(img[dice1 - 1],0,30,this); // displays the final dice 1
			g.drawImage(img[dice2 - 1],60,50,this); // displays the final dice 2
			this.validate();
    	}
    	rolled = false;
	}

	public int rollDice() {
		// TODO Auto-generated method stub
		rolled = true;
		
		dice.roll(2);
		
		dice1 = dice.getD1();
		dice2 = dice.getD2();
		
		rollValue = dice1 + dice2;
		
		System.out.println("Dice 1 is " + dice1 + ".");
		System.out.println("Dice 2 is " + dice2 + ".\n");
		repaint();
		
		return rollValue;
	}	
}