package settlers.game.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;


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
public class PlayerIcon extends javax.swing.JPanel {
	
	Color color;
	public PlayerIcon(Color _color) {
		super();
		initGUI();
		color = _color;
	}
	
	public void setColor(Color _color)
	{
		color = _color;
		this.setBackground(color);
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(60, 60));
			this.setSize(60, 60);
			this.setBackground(color);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
