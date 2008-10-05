package SettlersGUI;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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
public class TabbedPanel extends javax.swing.JPanel {
	private JTabbedPane tabbedPanel;
	private JTextArea errorText;
	private JPanel cardPanel;
	private JTextArea rollText;
	private JTextArea gameText;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TabbedPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public TabbedPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(445, 150));
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setSize(445, 150);
			{
				tabbedPanel = new JTabbedPane();
				this.add(tabbedPanel, new AnchorConstraint(3, 1001, 1003, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				tabbedPanel.setTabPlacement(JTabbedPane.LEFT);
				tabbedPanel.setPreferredSize(new java.awt.Dimension(445, 150));
				{
					gameText = new JTextArea();
					tabbedPanel.addTab("Game Text", null, gameText, null);
					gameText.setText("Game Text");
					gameText.setEditable(false);
				}
				{
					rollText = new JTextArea();
					tabbedPanel.addTab("Roll History", null, rollText, null);
					rollText.setText("Roll History");
					rollText.setPreferredSize(new java.awt.Dimension(378, 140));
					rollText.setEditable(false);
				}
				{
					errorText = new JTextArea();
					tabbedPanel.addTab("Errors", null, errorText, null);
					errorText.setText("Errors");
					errorText.setEditable(false);
				}
				{
					cardPanel = new JPanel();
					tabbedPanel.addTab("Your Cards", null, cardPanel, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextArea getGameText() {
		// TODO Auto-generated method stub
		return gameText;
	}

	public JTextArea getErrorPanel() {
		// TODO Auto-generated method stub
		return this.errorText;
	}

}
