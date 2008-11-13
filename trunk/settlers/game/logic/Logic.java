//this class will hold the logic portion of the code, or at least be the main class for it

package settlers.game.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.gui.*;
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
            RoadEvent se = (RoadEvent) e;
            //re.road.
            se.road.buildRoad();
            PlayerEvent n = new PlayerEvent("PLAYER_INIT_ROAD_SUCCESS", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        
        else if (event.equals("PLAYER_ROLLED"))
        {
            //When the player has already rolled
            b.roll_roll.setEnabled(false);
            b.roll_next.setEnabled(true);
            b.roll_next.grabFocus();
        }
        
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
        else if(event.equals("PLAYER_BUILD_SETTLEMENT"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildSettlement();
            
            GameState.setActionState(-1);
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILT_SETTLEMENT", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_ROAD"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildRoad();
            
            GameState.setActionState(-1);
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILT_ROAD", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_DEV_CARD"))
        {
            
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildDevCard();
            
			pe.player.getDevCards().addCard(GameState.getGui().gui.getMainBoard().getGameBoard().getBoardDevCards().drawCard());
			
            GameState.setActionState(-1);
        }
        else if(event.equals("PLAYER_PLAY_DEVELOPMENTCARD"))
        {
            //Handles the development card in play.
            DevelopmentEvent de = (DevelopmentEvent) e;
            
            //Code to handle Knight Card
            if (de.getDType() == 4)
            {
                DevelopmentEvent n = new DevelopmentEvent("PLAYER_PLAY_KNIGHT", 4);
                EventManager.callEvent(n);                
            }
            
            //Other Dev Cards waiting to be implemented.
        }
        else if (event.equals("PLAYER_KNIGHT_PLACED"))
        {
            //Waiting To be Implemented:
            //Needs to call logic functions to determine whose settlements are next to the robber 
            //and send to GUI so that the players choice of who is robbed can be determined.
            //This settlement needs to be highlighted and then send to GUI
            
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_CHOOSE_KNIGHTED", 4);
            EventManager.callEvent(n);
        }
        else if(event.equals("PLAYER_KNIGHTED_CHOSEN"))
        {
            //Needs to update the resources after the Player recieves the pillaged resource
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_KNIGHT_SUCCESS", 4);
            EventManager.callEvent(n);
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
            Event n = new Event("DICE_ROLLED");
            EventManager.callEvent(n);

        }
        if(evt.getSource() == b.roll_thief && GameState.getCurPlayer().getDevCards().hasType(1) > 0) 
        {
            //Throws PLAYER_PLAY_DEVELOPMENTCARD Event to Logic, with the Knight card
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_PLAY_DEVELOPMENTCARD", 1);
            EventManager.callEvent(n);
        }
        ///Remove Once all dev cards are playable
        else if (evt.getSource() == b.roll_thief && GameState.getCurPlayer().getDevCards().hasType(1) == 0)
        {
            System.out.println("You have a dev card that isn't yet playable, sorry bud.  Wait till you get a knight card :)");
        }
        if(evt.getSource() == b.trade_player)
        {
            TradeWindow t = new TradeWindow(GameState.players.size());
        }
        
        if(evt.getSource() == b.trade_bank)
        {
            BankTradeWindow bt = new BankTradeWindow();
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
            BuildEvent b = new BuildEvent("BUILD_REQUEST", 4);
            EventManager.callEvent(b);
        }
        if(evt.getSource() == b.build_settlement)
        {
            BuildEvent b = new BuildEvent("BUILD_REQUEST", 1);
            EventManager.callEvent(b);
        }
        if(evt.getSource() == b.build_road)
        {
            BuildEvent b = new BuildEvent("BUILD_REQUEST", 3);
            EventManager.callEvent(b);
        }
        if(evt.getSource() == GameState.getGui().gui.getBottomPanel().startButton)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_ROLL_PHASE_BEGIN", GameState.getCurPlayer());
            EventManager.callEvent(n);
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
        EventManager.registerEvent("PLAYER_ROLLED", this);
        EventManager.registerEvent("PLAYER_TRADE_PHASE_END", this);
        EventManager.registerEvent("PLAYER_BUILD_DEV_CARD", this);
        EventManager.registerEvent("PLAYER_BUILD_ROAD", this);
        EventManager.registerEvent("PLAYER_BUILD_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_BUILD_REQUEST", this);
        EventManager.registerEvent("PLAYER_PLAY_DEVELOPMENTCARD", this);
        
        //Knight Registered Events
        EventManager.registerEvent("PLAYER_KNIGHT_PLACED", this);
        EventManager.registerEvent("PLAYER_KNIGHTED_CHOSEN", this);
    }
}
