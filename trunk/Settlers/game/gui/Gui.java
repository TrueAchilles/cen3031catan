//this class will be responsible for setting up the gui.  right now, it just ends whoever's turn just began

package settlers.game.gui;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.events.*;

public class Gui implements EventListener
{
	
	SettlersGUI gui;
	
	public void eventCalled(Event e)
	{
		String event = e.getEvent();
		if (event.equals("PLAYER_INITTURN_START"))
		{
			PlayerEvent pe = (PlayerEvent) e;
			System.out.println("Player init turn: " + pe.player.getID());
			System.out.println("	Player " + pe.player.getID() + " attempting to place settlement");
            GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
			//sc.buttonSettlement();
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
            GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
			//sc.buttonRoad();
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
			
			//Assuming that the player rolls once turn begins
			PlayerEvent n = new PlayerEvent("PLAYER_ROLL", pe.player);
			EventManager.callEvent(n);  //Should have a dice animation with the two di values given
			
		}
		else if (event.equals("PLAYER_TRADE_PHASE_BEGIN"))
		{
            PlayerEvent pe = (PlayerEvent) e;
            
			//Trade phase commences, player can build, use development cards, or end turn
			//Therefore need else statements here for these interactions
			//For now the trade Phase immediately ends
			PlayerEvent E = new PlayerEvent("PLAYER_TRADE_PHASE_END", pe.player);
			EventManager.callEvent(E);
			
		}
		else if(event.equals("PLAYER_BUILD_PHASE_BEGIN")) 
		{
            PlayerEvent pe = (PlayerEvent) e;
			//Player requests to build an object on to the board
			PlayerEvent E = new PlayerEvent("PLAYER_BUILD_REQUEST", pe.player);
			EventManager.callEvent(E);
			
		}
		else if(event.equals("PLAYER_REQUEST_BUILD_SUCCESS")) 
		{
            PlayerEvent pe = (PlayerEvent) e;
			//Player was successful and his turn automatically ends
			PlayerEvent E = new PlayerEvent("PLAYER_TURN_END", pe.player);
			EventManager.callEvent(E);
			
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
		EventManager.registerEvent("PLAYER_ROLL", this);
		EventManager.registerEvent("PLAYER_TRADE_PHASE_BEGIN", this);
		EventManager.registerEvent("PLAYER_BUILD_PHASE_BEGIN", this);
		EventManager.registerEvent("PLAYER_REQUEST_BUILD_SUCCESS", this);
		

		gui = new SettlersGUI();
		gui.initialize();		
	}
	
}
