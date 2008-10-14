//this class will be responsible for setting up the gui.  right now, it just ends whoever's turn just began

package settlers.game.gui;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.events.*;

public class Gui implements EventListener
{
	
	SettlersGUI gui;
	SettlersController sc;
	
	public void eventCalled(Event e)
	{
		String event = e.getEvent();
		if (event.equals("PLAYER_INITTURN_START"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("Player init turn: " + pe.player.getID());
			System.out.println("	Player " + pe.player.getID() + " attempting to place settlement");
			sc.buttonSettlement();
//			PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
//			EventManager.callEvent(n);
		}
		else if (event.equals("PLAYER_INIT_SETTLEMENT_FAIL"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("	Player " + pe.player.getID() + " please place the settlement correctly");

			PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
			EventManager.callEvent(n);
		}
		else if (event.equals("PLAYER_INIT_SETTLEMENT_SUCCESS"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("	Player " + pe.player.getID() + " placed settlement");
			System.out.println("	Player " + pe.player.getID() + " attempting to place road");
			sc.buttonRoad();
			//PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_ROAD", pe.player);
			//EventManager.callEvent(n);
		}
		else if (event.equals("PLAYER_INIT_ROAD_FAIL"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("	Player " + pe.player.getID() + " please place the road correctly");

			PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
			EventManager.callEvent(n);
		}
		else if (event.equals("PLAYER_INIT_ROAD_SUCCESS"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("	Player " + pe.player.getID() + " placed road");
			System.out.println("	Player " + pe.player.getID() + " initial turn ends");
			PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_END", pe.player);
			EventManager.callEvent(n);
		}
		else if (event.equals("PLAYER_TURN_START"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("Player turn: " + pe.player.getID());
			// in theory, something should happen here
			if (pe.player.getID() != GameState.players.size()) // should be players.size(), but right now players is implemented in Logic, not its correct class (nonexistent atm)
			{
				PlayerEvent n = new PlayerEvent("PLAYER_TURN_END", pe.player);
				EventManager.callEvent(n);
			}
		}
		else if(event.equals("GAME_END"))
		{
			System.out.println("Game is ending...");
			JOptionPane.showMessageDialog(gui, GameState.players.get(0).getName() + " wins!");
			System.exit(0);			
		}
	}

	public Gui()
	{
		EventManager.registerEvent("PLAYER_INITTURN_START", this);
		EventManager.registerEvent("PLAYER_TURN_START", this);
		EventManager.registerEvent("PLAYER_INIT_SETTLEMENT_SUCCESS", this);
		EventManager.registerEvent("PLAYER_INIT_SETTLEMENT_FAIL", this);
		EventManager.registerEvent("PLAYER_INIT_ROAD_SUCCESS", this);
		EventManager.registerEvent("PLAYER_INIT_ROAD_FAIL", this);
		EventManager.registerEvent("GAME_END", this);
		
		sc = new SettlersController();
		gui = new SettlersGUI(sc);
		sc.setGui(gui);
		gui.initialize(sc.getTiles());		
	}
	
}
