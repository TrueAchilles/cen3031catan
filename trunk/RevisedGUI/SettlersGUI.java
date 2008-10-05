package SettlersGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

import Settlers.Tile;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

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
public class SettlersGUI extends javax.swing.JFrame implements ActionListener {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private MainBoard mainBoard;
	private JMenu gameMenu;
	private JMenuItem connectLan;
	private JCheckBoxMenuItem hideRollBox;
	private JMenuItem connectInternet;
	private JMenu connectTo;
	private JMenuItem editProfile;
	private JMenuItem makeProfile;
	private JSeparator sep4;
	private JMenuItem computerPlayer;
	private JMenuItem humanPlayer;
	private JMenu addPlayer;
	private JSeparator sep3;
	private JMenuItem exit;
	private JSeparator sep2;
	private JMenuItem saveBoard;
	private JMenu connection;
	private JMenuItem loadBoard;
	private JMenuItem remakeBoard;
	private JSeparator sep1;
	private JMenuItem loadGame;
	private JMenuItem saveGame;
	private JMenuItem newGame;
	private JMenu player;
	private JMenuBar settlersMenu;
	private BottomPanel bottomPanel;
	
	private SettlersController controller=null;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SettlersGUI inst = new SettlersGUI(null);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SettlersGUI(SettlersController _controller) {
		super();
		controller=_controller;
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setPreferredSize(new java.awt.Dimension(800, 850));
			this.setTitle("Settlers of Catan");
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			this.setMinimumSize(new java.awt.Dimension(800, 850));
			{
				settlersMenu = new JMenuBar();
				setJMenuBar(settlersMenu);
				{
					gameMenu = new JMenu();
					settlersMenu.add(gameMenu);
					gameMenu.setText("Game");
					{
						newGame = new JMenuItem();
						gameMenu.add(newGame);
						newGame.setText("New Game");
						newGame.addActionListener(this);
					}
					{
						saveGame = new JMenuItem();
						gameMenu.add(saveGame);
						saveGame.setText("Save Game");
					}
					{
						loadGame = new JMenuItem();
						gameMenu.add(loadGame);
						loadGame.setText("Load Game");
					}
					{
						sep1 = new JSeparator();
						gameMenu.add(sep1);
					}
					{
						remakeBoard = new JMenuItem();
						gameMenu.add(remakeBoard);
						remakeBoard.setText("Re-make Board");
						remakeBoard.addActionListener(this);
					}
					{
						loadBoard = new JMenuItem();
						gameMenu.add(loadBoard);
						loadBoard.setText("Load Board");
					}
					{
						saveBoard = new JMenuItem();
						gameMenu.add(saveBoard);
						saveBoard.setText("Save Board");
					}
					{
						sep2 = new JSeparator();
						gameMenu.add(sep2);
					}
					{
						hideRollBox = new JCheckBoxMenuItem();
						gameMenu.add(hideRollBox);
						hideRollBox.setText("Hide Roll Box");
						hideRollBox.setState(true);
						hideRollBox.addActionListener(this);
					}
					{
						sep3 = new JSeparator();
						gameMenu.add(sep3);
					}
					{
						exit = new JMenuItem();
						gameMenu.add(exit);
						exit.setText("Exit");
						exit.addActionListener(this);
					}
				}
				{
					player = new JMenu();
					settlersMenu.add(player);
					player.setText("Player");
					{
						addPlayer = new JMenu();
						player.add(addPlayer);
						addPlayer.setText("Add Player");
						{
							humanPlayer = new JMenuItem();
							addPlayer.add(humanPlayer);
							humanPlayer.setText("Add Human Player");
						}
						{
							computerPlayer = new JMenuItem();
							addPlayer.add(computerPlayer);
							computerPlayer.setText("Add Computer Player");
						}
					}
					{
						sep4 = new JSeparator();
						player.add(sep4);
					}
					{
						makeProfile = new JMenuItem();
						player.add(makeProfile);
						makeProfile.setText("Make Profile");
					}
					{
						editProfile = new JMenuItem();
						player.add(editProfile);
						editProfile.setText("Edit Profile");
					}
				}
				{
					connection = new JMenu();
					settlersMenu.add(connection);
					connection.setText("Connection");
					{
						connectTo = new JMenu();
						connection.add(connectTo);
						connectTo.setText("Connect To...");
						{
							connectLan = new JMenuItem();
							connectTo.add(connectLan);
							connectLan.setText("LAN");
						}
						{
							connectInternet = new JMenuItem();
							connectTo.add(connectInternet);
							connectInternet.setText("Internet");
						}
					}
				}
			}
			{
				bottomPanel = new BottomPanel();
				getContentPane().add(bottomPanel, new AnchorConstraint(835, 1023, 1001, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				bottomPanel.setPreferredSize(new java.awt.Dimension(802, 131));
			}
			{
				mainBoard = new MainBoard(this);
				getContentPane().add(mainBoard, new AnchorConstraint(2, 1000, 834, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				mainBoard.setPreferredSize(new java.awt.Dimension(794, 598));
				mainBoard.setBackground(new java.awt.Color(255,255,255));
				mainBoard.setSize(794, 600);
			}
			pack();
			this.setSize(800, 850);
			this.setVisible(true);
			this.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void write(String txt){
        this.bottomPanel.getTabbedPanel().getGameText().append(txt);
    }
    
    public void initialize(Tile[] tiles){
        this.mainBoard.getGameBoard().initialize(tiles);
    }
    
    public SettlersController getController(){
        return controller;
    }
    
    public JMenuBar getMenu(){
        return settlersMenu;
    }
    
    public void setAction(byte action){
        this.mainBoard.getGameBoard().setAction(action);
    }

	public GameBoard getBoard() {
		// TODO Auto-generated method stub
		return this.mainBoard.getGameBoard();
	}

	public void showError(String string) {
		// TODO Auto-generated method stub
		this.bottomPanel.getTabbedPanel().getErrorPanel().append(string);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == this.exit)
		{
			System.exit(0);
		}
		if(evt.getSource() == this.newGame)
		{
			this.initialize(controller.getTiles());
		}
		if(evt.getSource() == this.remakeBoard)
		{
			controller.reInitialize();
			this.initialize(controller.getTiles());
		}
		if(evt.getSource() == this.hideRollBox)
		{
			mainBoard.hideBox(hideRollBox.isSelected());
		}
	}

	public MainBoard getMainBoard() {
		// TODO Auto-generated method stub
		return this.mainBoard;
	}	
}
