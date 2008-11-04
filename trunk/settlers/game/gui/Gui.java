//this class will be responsible for setting up the gui.  right now, it just ends whoever's turn just began

package settlers.game.gui;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.events.*;

public class Gui implements EventListener
{
	
    public SettlersGUI gui;
    
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
            //PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
            //EventManager.callEvent(n);
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
            
            gui.getBottomPanel().turnStart();
            
            gui.getBottomPanel().startButton.grabFocus();
            
        }
        else if(event.equals("PLAYER_ROLL_PHASE_BEGIN"))
        {
        	gui.getBottomPanel().hideTurnStart();
            
            gui.getBottomPanel().getButtonPanel().roll_next.setEnabled(false);
            gui.getBottomPanel().getButtonPanel().roll_roll.setEnabled(true);
            gui.getBottomPanel().getButtonPanel().roll_roll.grabFocus();
            
            //Talk to ButtonPanel and tell it to switch
            gui.getBottomPanel().getButtonPanel().switchPanel("ROLL");            
        }
        else if (event.equals("PLAYER_TRADE_PHASE_BEGIN"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            //Trade phase commences, player can build, use development cards, or end turn
            //Therefore need else statements here for these interactions
            //For now the trade Phase immediately ends
            
            //Talk to ButtonPanel and tell it to switch
            gui.getBottomPanel().getButtonPanel().switchPanel("TRADE");
            gui.getBottomPanel().getButtonPanel().trade_next.grabFocus();
            
        }
        else if(event.equals("PLAYER_BUILD_PHASE_BEGIN")) 
        {
            PlayerEvent pe = (PlayerEvent) e;
            //Player requests to build an object on to the board
            
            //Talk to ButtonPanel and tell it to switch
            gui.getBottomPanel().getButtonPanel().switchPanel("BUILD");
            gui.getBottomPanel().getButtonPanel().build_next.grabFocus();
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILD_REQUEST", pe.player);
            EventManager.callEvent(E);
            
        }
        else if(event.equals("BUILD_REQUEST"))
        {
        	BuildEvent b = (BuildEvent) e;
        	
        	switch(b.buildType)
        	{
	        	case 1:
	        	{
	        		System.out.println("Trying to build a settlement");
	        		if(GameState.getCurPlayer().canBuySettlement())
	        		{
	        			System.out.println("Making the settlement...");
	        			GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
	        		}
	        		break;

	        	}
	        	case 2:
	        	{
	        		System.out.println("Trying to build a city");
	        		if(GameState.getCurPlayer().canBuyCity())
	        		{
	        			System.out.println("Making the city...");
	        			GameState.setActionState(GlobalVar.ACTION_ADD_CITY);
	        		}
	        		break;

	        	}
	        	case 3:
	        	{
	        		System.out.println("Trying to build a road");
	        		if(GameState.getCurPlayer().canBuyRoad())
	        		{
	        			System.out.println("Making the road...");
	        			GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
	        		}
        			break;

	        	}
	        	case 4:
	        	{
	        		System.out.println("Trying to build a dev card");
	        		if(GameState.getCurPlayer().canBuyDevCard())
	        		{
	        			System.out.println("Making a dev card...");
	        			PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_DEV_CARD", GameState.getCurPlayer());
	        			EventManager.callEvent(pe);
	        		}	
	        		break;
	        	}
        	}
        }
        else if(event.equals("PLAYER_REQUEST_BUILD_SUCCESS")) 
        {
            PlayerEvent pe = (PlayerEvent) e;
            //Player was successful and his turn automatically ends
            
        }
        else if(event.equals("GAME_END"))
        {
            System.out.println("Game is ending...");
            JOptionPane.showMessageDialog(gui, GameState.getCurPlayer().getName() + " wins!");
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
        EventManager.registerEvent("PLAYER_ROLL_PHASE_BEGIN", this);
        EventManager.registerEvent("PLAYER_TRADE_PHASE_BEGIN", this);
        EventManager.registerEvent("PLAYER_BUILD_PHASE_BEGIN", this);
        EventManager.registerEvent("BUILD_REQUEST", this);
        EventManager.registerEvent("PLAYER_REQUEST_BUILD_SUCCESS", this);
		
        gui = new SettlersGUI();
        gui.initialize();		
    }
}
