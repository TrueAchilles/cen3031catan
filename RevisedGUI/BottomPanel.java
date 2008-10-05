package SettlersGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComponent;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;

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
public class BottomPanel extends javax.swing.JPanel {
	private ButtonPanel buttonPanel;
	private TabbedPanel tabbedPanel;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BottomPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public BottomPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(800, 150));
			this.setSize(800, 150);
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setBackground(new java.awt.Color(255,255,255));
			{
				tabbedPanel = new TabbedPanel();
				this.add(tabbedPanel, new AnchorConstraint(0, 0, 0, 350, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
				tabbedPanel.setPreferredSize(new java.awt.Dimension(450, 150));
			}
			{
				buttonPanel = new ButtonPanel();
				this.add(buttonPanel, new AnchorConstraint(0, 438, 0, 0, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TabbedPanel getTabbedPanel() {
		// TODO Auto-generated method stub
		return tabbedPanel;
	}

}
