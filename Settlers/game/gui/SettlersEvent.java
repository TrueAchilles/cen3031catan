package settlers.game.gui;

import settlers.game.SettlersController;
import javax.swing.JOptionPane;



public class SettlersEvent {
	
	SettlersGUI gui;
	
	SettlersController sc;
	
	MainBoard mainBoard;
	BottomPanel bottomPanel;
	
	public SettlersEvent(SettlersGUI _gui)
	{
		gui = _gui;
		init();
	}
	
	private void init()
	{
		sc = gui.getController();
		mainBoard = gui.getMainBoard();
		bottomPanel = gui.getBottomPanel();
	}
	
	public void startNewGame()
	{
		if(sc.getStarted() == false)
		{
			if(sc.getPlayers().size() > 1)
			{
				bottomPanel.getButtonPanel().startNewGame();
				bottomPanel.getTabbedPanel().startNewGame();
				sc.gameStarted();
				this.bottomPanel.getButtonPanel().setEvent(this);
				mainBoard.getGameBoard().initialize(sc.getTiles());
			}
			else
				JOptionPane.showMessageDialog(gui, "There aren't enought players...add some!"); 
		}
		else
		{
			int value = JOptionPane.showConfirmDialog(gui, "Do you want to start a new game?");
			remakeBoard();
		}
	}
	
	public void remakeBoard() {
		// TODO Auto-generated method stub
		if(sc.getStarted())
		{
			sc.reInitialize();
			gui.initialize(sc.getTiles());
		}
		else
		{
			JOptionPane.showMessageDialog(gui, "Can't re-make the board if a game hasn't been created");
		}
	}

	public void addPlayer()
	{
		if(!sc.getStarted())
		{
			if(mainBoard.isPlayerPanel() == false)
			{
				//Then we haven't made a game board yet...do so
				mainBoard.makePlayerPanel();
			}
			if(sc.getPlayers().size() < 4)
			{
				String name = JOptionPane.showInputDialog("Please enter the name of Player " + (sc.getPlayers().size() + 1) + ".");
				sc.addPlayer(name);
				mainBoard.getPlayerPanel().addPlayer(name);
			}
			else
			{
				JOptionPane.showMessageDialog(gui, "There are already 4 players, more cannot be added!");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(gui, "Cannot add players after a game has started!");
		}
	}

	public void buttonEvent(int i, java.awt.CardLayout card) {
		// TODO Auto-generated method stub
		if(i == 0)
		{
			//Then the button was Opening's Next
			
		}
		else if (i == 1)
		{
			//Then it was Roll's Next
			card.show(bottomPanel.getButtonPanel(), "TRADE");
			mainBoard.getStatusBar().setText("TRADE PHASE");
		}
		else if (i == 2)
		{
			//Then it was Trade's Next
			card.show(bottomPanel.getButtonPanel(), "BUILD");
			mainBoard.getStatusBar().setText("BUILD PHASE");
		}
		else if (i == 3)
		{
			//Then it was Build's Next
			card.show(bottomPanel.getButtonPanel(), "ROLL");
			mainBoard.getStatusBar().setText("ROLL PHASE");
		}
		else if(i == 4)
		{
			//The Build's Dev Card
		}
	}
}
