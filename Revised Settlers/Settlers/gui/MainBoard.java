package Settlers.gui;

import Settlers.*;

import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainBoard extends javax.swing.JPanel {
	
	private JLabel statusBar;
	private SettlersGUI parent;
	private GameBoard gameBoard;
	private PlayerPanel playerPanel;
	
	private boolean playerPanel_created = false;

	public MainBoard()
	{
		super();
		parent = null;
		initGUI();
	}
	
	public MainBoard(SettlersGUI _parent) {
		super();
		parent = _parent;
		initGUI();
	}
	
	private void initGUI() {
		try 
		{
			this.setBackground(new java.awt.Color(255,255,255));
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			this.setSize(800, 600);
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setBackground(new java.awt.Color(255,255,255));
			gameBoard = new GameBoard(this);
			gameBoard.setPreferredSize(new java.awt.Dimension(802, 574));	//732, 574
			statusBar = new JLabel();
			this.add(statusBar, BorderLayout.NORTH);
			statusBar.setText("Status Bar");
			statusBar.setHorizontalAlignment(SwingConstants.CENTER);
			statusBar.setHorizontalTextPosition(SwingConstants.CENTER);
			statusBar.setFont(new java.awt.Font("Tahoma",0,14));
			statusBar.setPreferredSize(new java.awt.Dimension(409, 26));
			statusBar.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
			this.add(gameBoard, BorderLayout.CENTER);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public GameBoard getGameBoard()
	{
		return this.gameBoard;
	}
	
	public PlayerPanel getPlayerPanel()
	{
		return playerPanel;
	}
	
	public JLabel getStatusBar()
	{
		return statusBar;
	}
	
	public void makePlayerPanel()
	{
		playerPanel = new PlayerPanel();
		this.add(playerPanel, BorderLayout.WEST);
		this.playerPanel_created = true;
	}
	
	public boolean isPlayerPanel()
	{
		return this.playerPanel_created;
	}

	public SettlersController getController() {
		return this.parent.getController();
	}
}
