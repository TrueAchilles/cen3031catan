package SettlersGUI;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class MainBoard extends javax.swing.JPanel {
	
	private JLabel statusBar;
	private RollBox rollBox1;
	private GameBoard gameBoard;
	private PlayerPanel playerPanel;
	private SettlersGUI parent;
	
	public MainBoard(SettlersGUI _parent) {
		super();
		parent = _parent;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setBackground(new java.awt.Color(255,255,255));
			}
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setSize(800, 600);
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setBackground(new java.awt.Color(255,255,255));
			{
				rollBox1 = new RollBox();
				this.add(rollBox1, new AnchorConstraint(835, 1000, 999, 876, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
				rollBox1.setPreferredSize(new java.awt.Dimension(99, 98));
			}
			{
				gameBoard = new GameBoard(this);
				this.add(gameBoard, new AnchorConstraint(29, 0, 0, 89, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL));
				gameBoard.setPreferredSize(new java.awt.Dimension(729, 571));
				gameBoard.setOpaque(false);
			}
			{
				playerPanel = new PlayerPanel();
				this.add(playerPanel, new AnchorConstraint(125, 80, 1002, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				playerPanel.setPreferredSize(new java.awt.Dimension(64, 526));
				playerPanel.setForeground(new java.awt.Color(0,0,0));
				playerPanel.setBackground(new java.awt.Color(255,255,255));
				playerPanel.setOpaque(true);
			}
			{
				statusBar = new JLabel();
				this.add(statusBar, new AnchorConstraint(0, 833, 40, 175, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL));
				statusBar.setText("Status Bar");
				statusBar.setHorizontalAlignment(SwingConstants.CENTER);
				statusBar.setHorizontalTextPosition(SwingConstants.CENTER);
				statusBar.setFont(new java.awt.Font("Tahoma",0,14));
				statusBar.setInheritsPopupMenu(false);
				statusBar.setPreferredSize(new java.awt.Dimension(526, 24));
				statusBar.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
				statusBar.setOpaque(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GameBoard getGameBoard() {
		// TODO Auto-generated method stub
		return gameBoard;
	}

	public SettlersController getController() {
		// TODO Auto-generated method stub
		return this.parent.getController();
	}

	public void hideBox(boolean value) {
		// TODO Auto-generated method stub
		this.rollBox1.setVisible(value);
	}
}
