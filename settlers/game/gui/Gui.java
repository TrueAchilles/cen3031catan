//this class will be responsible for setting up the gui.  right now, it just ends whoever's turn just began

package settlers.game.gui;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import settlers.game.GameState;
import settlers.game.events.*;

import settlers.game.*;
import settlers.game.logic.*;

import settlers.game.*;
import settlers.game.events.*;

public class Gui extends javax.swing.JFrame implements EventListener
{
    
    public SettlersGUI gui;
    //This is used to initially set up the player info panel
    private boolean playerInfoInitialized = false;
    private PlayerInfo playerInfo;
    
    public void eventCalled(Event e)
    {
        String event = e.getEvent();
        if (event.equals("PLAYER_INITTURN_START"))
        {

            PlayerEvent pe = (PlayerEvent) e;

            if (pe.player.getID() == 1)
            {
                gui.toggleComponent("Player", false);
            }

            System.out.println("Player init turn: " + pe.player.getID());
            System.out.println("    Player " + pe.player.getID() + " attempting to place settlement");
            GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
            MainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": INITIAL SETTLEMENT BUILD PHASE");
            //Once the first "PLAYER_INITTURN_START" has been thrown, initialize the player information panel
            if (!(playerInfoInitialized))
            {
            
                playerInfo = new PlayerInfo();
                playerInfo.setPlayerInfoInitialized(true);
                playerInfoInitialized = true;
                playerInfo.setLocation(800, 0);
                System.out.println("The location of the window is: " + playerInfo.getLocation());
            
            }
            
            //sc.buttonSettlement();
            //PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
            //EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INIT_SETTLEMENT_FAIL"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            System.out.println("    Player " + pe.player.getID() + " please place the settlement correctly");  
            
            PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
            EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INIT_SETTLEMENT_SUCCESS"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            System.out.println("    Player " + pe.player.getID() + " placed settlement");
            System.out.println("    Player " + pe.player.getID() + " attempting to place road");
            GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
            MainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": INITIAL ROAD BUILD PHASE");
            //sc.buttonRoad();
            //PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_ROAD", pe.player);
            //EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INIT_ROAD_FAIL"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            System.out.println("    Player " + pe.player.getID() + " please place the road correctly");
            
            PlayerEvent n = new PlayerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", pe.player);
            EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INIT_ROAD_SUCCESS"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            System.out.println("    Player " + pe.player.getID() + " placed road");
            System.out.println("    Player " + pe.player.getID() + " initial turn ends");
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
            
            //If Player has a development Card, allows him to play it if he so chooses to.
            if (GameState.getCurPlayer().getDevCards().getSize() > 0)
                gui.getBottomPanel().getButtonPanel().roll_thief.setEnabled(true);
            else
                gui.getBottomPanel().getButtonPanel().roll_thief.setEnabled(false);
            

            gui.getBottomPanel().getButtonPanel().roll_roll.setEnabled(true);
            gui.getBottomPanel().getButtonPanel().roll_roll.grabFocus();
            
            //Talk to ButtonPanel and tell it to switch
            gui.getBottomPanel().getButtonPanel().switchPanel("ROLL");            
        }
        else if (event.equals("PLAYER_TRADE_PHASE_BEGIN"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            //Talk to ButtonPanel and tell it to switch
            gui.getBottomPanel().getButtonPanel().switchPanel("TRADE");
            gui.getBottomPanel().getButtonPanel().trade_next.grabFocus();
            
        }
        else if(event.equals("PLAYER_BUILD_PHASE_BEGIN")) 
        {
            PlayerEvent pe = (PlayerEvent) e;
            //Player requests to build an object on to the board
    
            if (pe.player.canBuySettlement() == false)
            {
                gui.getBottomPanel().getButtonPanel().build_road.setEnabled(false);
            }

            if (pe.player.canBuyCity() == false)
            {
                gui.getBottomPanel().getButtonPanel().build_city.setEnabled(false);
            }

            if (pe.player.canBuyDevCard() == false)
            {
                gui.getBottomPanel().getButtonPanel().build_dev.setEnabled(false);
            }
        
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
        else if(event.equals("PLAYER_PLAY_KNIGHT"))
        {
            //Allows for current Player to move the robber
            GameState.setActionState(GlobalVar.ACTION_MOVE_ROBBER);
            
            //Throws PLAYER_KNIGHT_PLACED
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_KNIGHT_PLACED", 4);
            EventManager.callEvent(n);
        }
        else if(event.equals("PLAYER_CHOOSE_KNIGHTED"))
        {
            //Player chooses which settlement to steal from
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_KNIGHTED_CHOSEN", 4);
            EventManager.callEvent(n);
        }
        else if(event.equals("PLAYER_KNIGHT_SUCCESS"))
        {
            ///Things to implement:
            //*Resources will be updated as given from logic with new stolen resource
            //*Largest Army increments by 1
            
            //Buttons are now updated, only 1 dev card can be played at a time
            gui.getBottomPanel().getButtonPanel().roll_thief.setEnabled(false);
            
            //Knight Card is longer in the Current Player's hand
            GameState.getCurPlayer().getDevCards().turnOver(1);
            
            //Checks to see if Player rolled or not, if roll has not yet executed then the player will still be able to roll but not go to the next phase
            if (gui.getBottomPanel().getButtonPanel().roll_roll.isEnabled() == true)
                gui.getBottomPanel().getButtonPanel().roll_next.setEnabled(false);
            else 
                gui.getBottomPanel().getButtonPanel().roll_next.setEnabled(true);
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
        
        //Knight Registered Events
        EventManager.registerEvent("PLAYER_PLAY_KNIGHT", this);
        EventManager.registerEvent("PLAYER_CHOOSE_KNIGHTED", this);
        EventManager.registerEvent("PLAYER_KNIGHT_SUCCESS", this);
        
        
        gui = new SettlersGUI();
        gui.initialize();        
    }

}
