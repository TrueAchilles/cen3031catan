//this class will hold the logic portion of the code, or at least be the main class for it

package settlers.game.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.events.*;
import settlers.game.elements.*;
import settlers.game.gui.ButtonPanel;

public class Logic implements EventListener, ActionListener
{
	private ButtonPanel b;
	
    public int iteration; // 1 means first init iteration, 2 means 2nd init iteration, 3 means game iterations (don't need to increment after 3)
    
    public void eventCalled(Event e)
    {
        String event = e.getEvent();
        String[] parts = event.split("_");
        if (event.equals("GAME_START")) // make the first player begin their first init turn
        {
            iteration = 1;
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            
            b = GameState.getGui().gui.getBottomPanel().getButtonPanel();
            
            PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_START", p);
            EventManager.callEvent(n);
        }
        else if (parts[0].equals("PLAYER"))
        {
            handlePlayerEvent(e);
        }
    }
    
    public void handlePlayerEvent(Event e) // right now, computes who goes next based on the last player and what iteration we are on
    {
        String event = e.getEvent();
        
        if (event.equals("PLAYER_INITTURN_END"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            if (iteration == 1)
            {
                if (p.getID() == GameState.players.size()) // just finished first iteration!  same player goes again, move to 2nd iteration
                {
                    iteration++;
                    PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_START", p);
                    EventManager.callEvent(n);
                }
                else
                {
                    int next = p.getID() + 1;
                    Player nextP = GameState.players.get(next - 1);
                    GameState.setCurPlayer(nextP);
                    PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_START", nextP);
                    EventManager.callEvent(n);
                }
            }
            else if (iteration == 2)
            {
                if (p.getID() == 1) // done with init turns!  same player goes again, but now it's the real deal
                {
                    iteration++;
                    GameState.setGamePhase(GlobalVar.GAME_STARTED);
                    PlayerEvent n = new PlayerEvent("PLAYER_TURN_START", p);
                    EventManager.callEvent(n);
                    //Event ne = new Event("GAME_END");
                    //EventManager.callEvent(ne);
                }
                else 
                {
                    int next = p.getID() - 1;
                    Player nextP = GameState.players.get(next - 1);
                    GameState.setCurPlayer(nextP);
                    PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_START", nextP);
                    EventManager.callEvent(n);
                }
            }
        }
        else if (event.equals("PLAYER_INIT_ATTEMPT_SETTLEMENT"))
        {
            SettlementEvent se = (SettlementEvent) e;
            se.settlement.buildSettlement();
            PlayerEvent n = new PlayerEvent("PLAYER_INIT_SETTLEMENT_SUCCESS", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INIT_ATTEMPT_ROAD"))
        {
            SettlementEvent se = (SettlementEvent) e;
            //re.road.
            se.settlement.buildRoad(se.settlement2);
            PlayerEvent n = new PlayerEvent("PLAYER_INIT_ROAD_SUCCESS", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
//        else if (event.equals("PLAYER_ROLL"))
//        {
//        	//call the method for dice roll
//
//        	//Proceed to end of roll finish, and begin trade phase
//        	PlayerEvent E = new PlayerEvent("PLAYER_TRADE_PHASE_BEGIN", GameState.getCurPlayer());
//        	EventManager.callEvent(E);
//        }
        else if(event.equals("PLAYER_TRADE_PHASE_END")) 
        {
            //Trade phase ends and immediately build phase begins
            PlayerEvent E = new PlayerEvent("PLAYER_BUILD_PHASE_BEGIN", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_REQUEST")) 
        {
            //When player requests, he may either succeed or fail, this assumes he always succeeds
            PlayerEvent E = new PlayerEvent("PLAYER_REQUEST_BUILD_SUCCESS", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if (event.equals("PLAYER_TURN_END"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            int next = (p.getID() == GameState.players.size()) ? 1 : p.getID() + 1;
            Player nextP = GameState.players.get(next - 1);
            GameState.setCurPlayer(nextP);
            PlayerEvent n = new PlayerEvent("PLAYER_TURN_START", nextP);
            EventManager.callEvent(n);
        }
    }
    
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        if(evt.getSource() == b.roll_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TRADE_PHASE_BEGIN", GameState.getCurPlayer());
            EventManager.callEvent(n); 
        }
        if(evt.getSource() == b.roll_roll)
        {
        	int roll = GameState.getGui().gui.getMainBoard().getRollBox().roll();
            
            System.out.println("So we rolled a " + roll);
            
            GameState.getGui().gui.getMainBoard().getGameBoard().diceRollResources(roll);
            
            b.roll_roll.setEnabled(false);
            b.roll_next.setEnabled(true);
        }
        if(evt.getSource() == b.trade_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TRADE_PHASE_END", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        if(evt.getSource() == b.build_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TURN_END", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        if(evt.getSource() == b.build_dev)
        {
            //
        }
        if(evt.getSource() == b.build_settlement)
        {
            GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
        }
        if(evt.getSource() == b.build_road)
        {
            GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
        }
    }
    
    public Logic() // registers the events logic needs
    {
        EventManager.registerEvent("GAME_START", this);
        EventManager.registerEvent("PLAYER_INITTURN_END", this);
        EventManager.registerEvent("PLAYER_TURN_END", this);
        EventManager.registerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_INIT_ATTEMPT_ROAD", this);
        EventManager.registerEvent("PLAYER_ROLL", this);
        EventManager.registerEvent("PLAYER_TRADE_PHASE_END", this);
        EventManager.registerEvent("PLAYER_BUILD_REQUEST", this);
	}
}
