//this class will be responsible for setting up the gui.  right now, it just ends whoever's turn just began

package settlers.game;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import settlers.game.GameState;
import settlers.game.events.*;

import settlers.game.*;
import settlers.game.elements.*;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.gui.*;
import settlers.game.events.*;
import settlers.game.elements.*;
import settlers.game.gui.ButtonPanel;


public class GamePlay implements EventListener
{

    public SettlersGUI gui;
    //This is used to initially set up the player info panel
    private boolean playerInfoInitialized = false;
    private boolean secondRoad ;
    private PlayerInfo playerInfo;
    private MonopolyPanel monopolyPanel;
    public int iteration;
    private ButtonPanel b;

    
    public boolean eventCalled(Event e)
    {
        String event = e.getEvent();
        if (event.equals("GAME_START")) // make the first player begin their first init turn
        {
            iteration = 1;
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            
            b = ContainerGUI.bottomPanel.getButtonPanel();
            
            PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_START", p);
            EventManager.callEvent(n);
        }
        else if (event.equals("PLAYER_INITTURN_START"))
        {

            PlayerEvent pe = (PlayerEvent) e;

                
            if (pe.player.getID() == 1)
            {
                ContainerGUI.settlersGUI.toggleComponent("Player", false);
            }
            
            if(pe.player.getName() != "AIVeryEasy")
            {
                System.out.println("Player init turn: " + pe.player.getID());
                System.out.println("	Player " + pe.player.getID() + " attempting to place settlement");
                GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
                MainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": INITIAL SETTLEMENT BUILD PHASE");
            }
            else
            {
            	System.out.println("Comp Player init turn: " + pe.player.getID());
                System.out.println("	Comp Player " + pe.player.getID() + " attempting to place settlement");
                GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
                pe.player.initSettlementPlacement();
                PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_END", pe.player);
            }

            //Once the first "PLAYER_INITTURN_START" has been thrown, initialize the player information panel
            
            if (!(playerInfoInitialized))
            {
            
                playerInfo = new PlayerInfo(gui);
                playerInfo.setPlayerInfoInitialized(true);
                playerInfoInitialized = true;
                playerInfo.setLocationRelativeTo(ContainerGUI.settlersGUI);
                //YearOfPlentyPanel yearPanel = new YearOfPlentyPanel();
            
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
            if(pe.player.getName() != "AIVeryEasy")
            {
        		System.out.println("	Player " + pe.player.getID() + " placed settlement");
        		System.out.println("	Player " + pe.player.getID() + " attempting to place road");
        		GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
        		MainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": INITIAL ROAD BUILD PHASE");
            }
        	else
        	{
        		System.out.println("Comp Player init turn: " + pe.player.getID());
                System.out.println("	Comp Player " + pe.player.getID() + " attempting to place road");
                GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
                pe.player.initRoadPlacement();
        	}
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
            if(pe.player.getName() != "AIVeryEasy")
            {
        		System.out.println("	Player " + pe.player.getID() + " placed road");
            	System.out.println("	Player " + pe.player.getID() + " initial turn ends");
            	PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_END", pe.player);
            	EventManager.callEvent(n);
            }
        	else
        	{
        		System.out.println("	Comp Player " + pe.player.getID() + " placed road");
            	System.out.println("	Comp Player " + pe.player.getID() + " initial turn ends");
            	PlayerEvent n = new PlayerEvent("PLAYER_INITTURN_END", pe.player);
            	EventManager.callEvent(n);
        	}
            
        }
        else if (event.equals("PLAYER_TURN_START"))
        {
            
            PlayerEvent pe = (PlayerEvent) e;
			
        	if(pe.player.getName() != "AIVeryEasy")
            {
        		System.out.println("Player turn: " + pe.player.getID());
        		
        		ContainerGUI.bottomPanel.turnStart();
            
        		ContainerGUI.bottomPanel.startButton.grabFocus();
            }
        	else
        	{
        		System.out.println("Comp Player turn: " + pe.player.getID());
        		pe.player.startTurn();
        		ContainerGUI.bottomPanel.turnStart();
        	}
            
        }
        else if(event.equals("PLAYER_ROLL_PHASE_BEGIN"))
        {
            PlayerEvent pe = (PlayerEvent) e;
        	if(pe.player.getName() != "AIVeryEasy")
            {
                ContainerGUI.settlersGUI.getBottomPanel().hideTurnStart();
                
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_next.setEnabled(false);
                
                //If Player has a development Card, allows him to play it if he so chooses to.
                if (GameState.getCurPlayer().getDevCards().hasType(1) > 0)
                    ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_thief.setEnabled(true);
                else
                    ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_thief.setEnabled(false);

                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_roll.setEnabled(true);
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_roll.grabFocus();
                
                //Talk to ButtonPanel and tell it to switch
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().switchPanel("ROLL");    
            }
        	else
        	{
        		pe.player.roll();
        		pe.player.rollToTrade();
        	}
            
                        
        }
        else if (event.equals("PLAYER_TRADE_PHASE_BEGIN"))
        {
            PlayerEvent pe = (PlayerEvent) e;
        	if(pe.player.getName() != "AIVeryEasy")
            {
        		//Trade phase commences, player can build, use development cards, or end turn
        		//Therefore need else statements here for these interactions
        		//For now the trade Phase immediately ends
            
        		//Talk to ButtonPanel and tell it to switch
        		
            }
        	else
        	{
        		pe.player.tradeToBuild();
        	}
            //Talk to ButtonPanel and tell it to switch
            ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().switchPanel("TRADE");
            ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().trade_next.grabFocus();
            //Talk to ButtonPanel and tell it to switch
            
            
        }
        else if(event.equals("PLAYER_BUILD_PHASE_BEGIN")) 
        {
            PlayerEvent pe = (PlayerEvent) e;
            //Player requests to build an object on to the board
            if(pe.player.getName() != "AIVeryEasy")
            {
				updateBuildButtons();
				
                //Talk to ButtonPanel and tell it to switch
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().switchPanel("BUILD");
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_next.grabFocus();
                
                PlayerEvent E = new PlayerEvent("PLAYER_BUILD_REQUEST", pe.player);
                EventManager.callEvent(E);
            } else
        	{
        		pe.player.actBuild();
        		pe.player.turnEnd();
        	}
            
        }
        else if(event.equals("BUILD_REQUEST"))
        {
            BuildEvent be = (BuildEvent) e;
            
            switch(be.buildType)
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
                case 5:
                {
                    System.out.println("Trying to build a comp settlement");
                    if(GameState.getCurPlayer().canBuySettlement())
                    {
                        System.out.println("Making the settlement...");
                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_SETTLEMENT);
                    }
                    break;

                }
                case 6:
                {
                    System.out.println("Trying to build a comp city");
                    if(GameState.getCurPlayer().canBuyCity())
                    {
                        System.out.println("Making the city...");
                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_CITY);
                    }
                    break;

                }
                case 7:
                {
                    System.out.println("Trying to build a comp road");
                    if(GameState.getCurPlayer().canBuyRoad())
                    {
                        System.out.println("Making the road...");
                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_ROAD);
                    }
                    break;

                }
                case 8:
                {
                    System.out.println("Trying to build a comp dev card");
                    if(GameState.getCurPlayer().canBuyDevCard())
                    {
                        System.out.println("Making a dev card...");
                        PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_DEV_CARD", GameState.getCurPlayer());
                        EventManager.callEvent(pe);
                    }    
                    break;
                }
            }
			
			updateBuildButtons();
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
            ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_thief.setEnabled(false);
            
            //Knight Card is longer in the Current Player's hand
            GameState.getCurPlayer().getDevCards().turnOver(1);
            
            //Checks to see if Player rolled or not, if roll has not yet executed then the player will still be able to roll but not go to the next phase
            if (ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_roll.isEnabled() == true)
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_next.setEnabled(false);
            else 
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_next.setEnabled(true);
        }
        else if (event.equals("PLAYER_ROADBULDING_SUCCESS"))
        {
            if (ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_roll.isEnabled() == true)
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_next.setEnabled(false);
            else 
                ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().roll_next.setEnabled(true);
        }
        else if(event.equals("GAME_END"))
        {
            System.out.println("Game is ending...");
            Player curPlayer = GameState.getCurPlayer();
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, curPlayer.getName() + " wins!");

            if (curPlayer.getPlayerProfile() != null)
            {
                curPlayer.getPlayerProfile().addWin();
                Profile.saveProfile(curPlayer.getPlayerProfile().getFilepath(), curPlayer.getPlayerProfile());
            }

            for (Player player : GameState.players)
            {
                if (player == curPlayer)
                {
                    continue;
                }

                if (player.getPlayerProfile() == null)
                {
                    player.getPlayerProfile().addLoss();
                    Profile.saveProfile(player.getPlayerProfile().getFilepath(), player.getPlayerProfile());
                }
            }

            System.exit(0);            
        }
        else if (event.equals("PLAYER_INITTURN_END"))
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
        else if (event.equals("PLAYER_ATTEMPT_ROAD"))
        {
            RoadEvent se = (RoadEvent) e;
            //re.road.
            se.road.buildRoad();

        }
        else if (event.equals("PLAYER_ATTEMPT_SETTLEMENT"))
        {
            SettlementEvent se = (SettlementEvent) e;
            //re.road.
            se.settlement.buildSettlement();
        }
        
        else if (event.equals("PLAYER_ROLLED"))
        {
            //When the player has already rolled

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
			
			updateBuildButtons();
            
            GameState.setActionState(-1);
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILT_SETTLEMENT", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_CITY"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildCity();
			
			updateBuildButtons();
            
            GameState.setActionState(-1);
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILT_CITY", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_ROAD"))
        {
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildRoad();
			
			updateBuildButtons();
            
            GameState.setActionState(-1);
            
            PlayerEvent E = new PlayerEvent("PLAYER_BUILT_ROAD", GameState.getCurPlayer());
            EventManager.callEvent(E);
        }
        else if(event.equals("PLAYER_BUILD_DEV_CARD"))
        {
            
            PlayerEvent pe = (PlayerEvent) e;
            
            pe.player.buildDevCard();
            
			pe.player.getDevCards().addCard(ContainerGUI.gameBoard.getBoardDevCards().drawCard());
			
			updateBuildButtons();
			
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
        //////////NEW EVENTS//////////////
        else if (event.equals("PLAYER_WISHESTO_START_TURN")) 
        {
            System.out.println("Player wishes to start turn.");
        }
        else if (event.equals("PLAYER_WISHESTO_ROLL"))
        {
            System.out.println("Player wishes roll.");
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            
            if (p != GameState.getCurPlayer())
            {
                System.out.println("You can't roll, you are not the current player!");
                return false;
            }
            if (GameState.diceHasBeenRolledDuringTurn == true)
            {
                System.out.println("You have already rolled a dice this turn!");
                return false;
            }
            b.roll_roll.setEnabled(false);
            GameState.diceHasBeenRolledDuringTurn = true;
            int value = ContainerGUI.rollBox.roll();

            return true;
            
        }
        else if ( event.equals("PLAYER_ROLLED_SUCCESSFULLY") )
        {
            System.out.println("Player has successfully finished rolling dice");
            PlayerEvent pe = (PlayerEvent) e;
            Player p = pe.player;
            int value = (Integer)pe.object;
            
            ContainerGUI.gameBoard.diceRollResources(value);
            ContainerGUI.bottomPanel.getTabbedPanel().setRandomDiceRoll(GameState.getCurPlayer().getName() + " rolled: " + value +"\n");
            
            
            if (value == 7 && GlobalVar.ROBBER == true) 
            {
                System.out.println("All players with over yay cards must remove half their deck.");
                RobberRemoveCardsWindow rrcw = new RobberRemoveCardsWindow();
            }
            else {
                b.roll_next.setEnabled(true);
                b.roll_next.grabFocus();
            }
        }
        else if (event.equals("PLAYER_WISHESTO_BUILD_ROAD"))
        {
            System.out.println("Player wishes to build road.");
        }
        else if (event.equals("PLAYER_WISHESTO_BUILD_SETTLEMENT"))
        {
            System.out.println("Player wishes to build settlement.");
        }
        else if (event.equals("PLAYER_WISHESTO_BUILD_CITY"))
        {
            System.out.println("Player wishes to build city.");
        }
        else if (event.equals("PLAYER_WISHESTO_BUY_ROAD"))
        {
            System.out.println("Player wishes to buy road.");
        }
        else if (event.equals("PLAYER_WISHESTO_BUY_SETTLEMENT"))
        {
            System.out.println("Player wishes to buy settlement");
        }
        else if (event.equals("PLAYER_WISHESTO_BUY_CITY"))
        {
            System.out.println("Player wishes to buy city");
        }
        else if (event.equals("PLAYER_WISHESTO_BUY_DEV"))
        {
            System.out.println("Player wishes to buy a development card.");
        }
        else if (event.equals("PLAYER_WISHESTO_TRADE"))
        {
            System.out.println("Player wishes to trade.");
        }
        else if (event.equals("PLAYER_WISHESTO_PLAY_DEV"))
        {
            System.out.println("Player wishes to play development card.");
        }
        else if (event.equals("PLAYER_WISHESTO_PLAY_ROAD"))
        {
            System.out.println("Player wishes to play road building card.");
        }
        else if (event.equals("PLAYER_WISHESTO_PLAY_MONOPOLY"))
        {
            System.out.println("Player wishes to play monopoly.");
        }
        else if (event.equals("PLAYER_WISHESTO_PLAY_KNIGHT"))
        {
            System.out.println("Player wishes to play knight.");
        }
        else if (event.equals("PLAYER_WISHESTO_PLAY_DISCOVERY"))
        {
            System.out.println("Player wishes to play discovery card.");
        }
        else if (event.equals("PLAYER_MUST_START_TURN"))
        {
            System.out.println("Player, you must start your turn.");
        }
        else if (event.equals("PLAYER_MUST_ROLL"))
        {
            System.out.println("Player, you must roll.");
        }
        else if (event.equals("PLAYER_MUST_BUILD_ROAD"))
        {
            System.out.println("Player, you must build road.");
        }
        else if (event.equals("PLAYER_MUST_BUILD_SETTLEMENT"))
        {
            System.out.println("Player, you must be settlement.");
        }
        else if (event.equals("PLAYER_MUST_BUILD_CITY"))
        {
            System.out.println("Player, you must build city.");
        }
        
        else if (event.equals("PLAYER_MUST_DISCARD_HALF_DECK"))
        {
            System.out.println("Player, you have more than 7 resource cards, /nplease discard half your deck rounded down.");
        }
        else if (event.equals("PLAYER_MUST_MOVE_ROBBER"))
        {
            System.out.println("Player, you must move robber.");
        }
        else if (event.equals("PLAYER_MUST_STEAL_RESOURCE"))
        {
            System.out.println("Player, you must steal resource.");
        }
        else if (event.equals("GAME_START"))
        {
            System.out.println("Game is starting...");
        }
        else if (event.equals("PLAYER_PLAY_ROADBUILDING"))
        {
            GameState.setActionState(GlobalVar.ACTION_RBDEV_CARD);           
        }
        else if (event.equals("PLAYER_BUILD_2ROAD"))
        {

            GameState.setActionState(GlobalVar.ACTION_RBDEV_CARD);

        }
        else if (event.equals("PLAYER_RBDEV_SUCCESS"))
        {
            GameState.setActionState(-1);
            System.out.println("Road Building Dev Card Successfully Played");
        }
        else if (event.equals("PLAYER_PLAY_MONOPOLY"))
        {
            monopolyPanel = new MonopolyPanel();
        }
        else if (event.equals("PLAYER_ACCEPT_MONOPOLY"))
        {
            GameState.getCurPlayer().alterResource(monopolyPanel.getResourceType(),monopolyPanel.getTotalOneTypeCards(), 0);
            for (Player player : GameState.players)
            {
                if (player.getID() != GameState.getCurPlayer().getID())
                {
                    System.out.println("Amount being removed: " + player.getResource(monopolyPanel.getResourceType()));
                    player.alterResource(monopolyPanel.getResourceType(),player.getResource(monopolyPanel.getResourceType()), 1);
                }
            }
            monopolyPanel.closeWindow();
        }
        else if (event.equals("PLAYER_CANCEL_MONOPOLY"))
        {
            DevelopmentCard monopolyToAdd = new DevelopmentCard(3);
            GameState.getCurPlayer().getDevCards().addCard(monopolyToAdd);
            monopolyPanel.closeWindow();
        }
        else if (event.equals("DEBUG_GIVE_MONOPOLY"))
        {
            DevelopmentCard monopolyToAdd = new DevelopmentCard(3);
            GameState.getCurPlayer().getDevCards().addCard(monopolyToAdd);
        }
        else if (event.equals("DEBUG_GIVE_ROAD"))
        {
            DevelopmentCard roadToAdd = new DevelopmentCard(2);
            GameState.getCurPlayer().getDevCards().addCard(roadToAdd); 
        }
        else if (event.equals("DEBUG_GIVE_YEAR"))
        {
            DevelopmentCard yearToAdd = new DevelopmentCard(4);
            GameState.getCurPlayer().getDevCards().addCard(yearToAdd);
        }
        
        /*
                       *These may need to be integrated into the above events but for now they are used to update the player Information panel.  They were taken out of the playerInfo class as they were the results of the concurrentmodifcation exception.
                       */
        if (event.equals("PLAYER_INITTURN_START") || event.equals("PLAYER_TURN_START"))
        {

            //playerInfo.cardTabs.removeTabAt(2);
            //playerInfo.cardTabs.addTab("Development Cards", playerInfo.playerDevelopmentPanels[GameState.getCurPlayer().getID() - 1]);
            playerInfo.changeDevelopmentCardPanel(GameState.getCurPlayer().getID(), GameState.getGamePhase());
            playerInfo.playerPanelRepaint();
            playerInfo.repaint();
            //updateUI();
            
        }
        if (event.equals("PLAYER_BUILT_SETTLEMENT") || event.equals("PLAYER_BUILT_ROAD") || event.equals("PLAYER_ROLLED_SUCCESSFULLY") || event.equals("PLAYER_TRADED") || event.equals("THIEF_DISCARD_RESOURCES") || event.equals("THIEF_STEAL_RESOURCE") || event.equals("PLAYER_ACCEPT_MONOPOLY"))
        {
        
            playerInfo.playerPanelRepaint();
            playerInfo.repaint();
            //updateUI(); 
        }
        
        if (event.equals("PLAYER_BUILD_DEV_CARD") || event.equals("PLAYER_PLAY_VPCARD") || event.equals("PLAYER_CANCEL_MONOPOLY") || event.equals("DEBUG_GIVE_MONOPOLY") || event.equals("DEBUG_GIVE_ROAD") || event.equals("DEBUG_GIVE_YEAR"))
        {
        
            playerInfo.playerPanelRepaint();
            playerInfo.displayDevCards();
            playerInfo.repaint();
            //updateUI();
        
        }

        
        
        return true;

    }
    
    public PlayerInfo getPlayerInfo()
    {
    
        return playerInfo;
    
    }
    
    
    public GamePlay()
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
        
        //Victory Point Cord Events
        EventManager.registerEvent("PLAYER_PLAY_VPCARD", this);
        
        //Monopoly Card Events
        EventManager.registerEvent("PLAYER_PLAY_MONOPOLY", this);
        EventManager.registerEvent("PLAYER_ACCEPT_MONOPOLY", this);
        EventManager.registerEvent("PLAYER_CANCEL_MONOPOLY", this);
        
        //Road Building Card Events
        EventManager.registerEvent("PLAYER_PLAY_ROADBUILDING", this);
        EventManager.registerEvent("PLAYER_BUILD_2ROAD", this);
        EventManager.registerEvent("PLAYER_RBDEV_SUCCESS", this);
        
        EventManager.registerEvent("GAME_START", this);
        EventManager.registerEvent("PLAYER_INITTURN_END", this);
        EventManager.registerEvent("PLAYER_TURN_END", this);
        EventManager.registerEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_INIT_ATTEMPT_ROAD", this);
        EventManager.registerEvent("PLAYER_ROLL", this);
        EventManager.registerEvent("PLAYER_ROLLED", this);
        EventManager.registerEvent("PLAYER_TRADED", this);
        EventManager.registerEvent("PLAYER_TRADE_PHASE_END", this);
        EventManager.registerEvent("PLAYER_BUILD_DEV_CARD", this);
        EventManager.registerEvent("PLAYER_BUILD_ROAD", this);
        EventManager.registerEvent("PLAYER_BUILD_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_BUILD_REQUEST", this);
        EventManager.registerEvent("PLAYER_PLAY_DEVELOPMENTCARD", this);
        
        //Knight Registered Events
        EventManager.registerEvent("PLAYER_KNIGHT_PLACED", this);
        EventManager.registerEvent("PLAYER_KNIGHTED_CHOSEN", this);
        gui = ContainerGUI.settlersGUI;
        
        //Debug events
        EventManager.registerEvent("DEBUG_GIVE_MONOPOLY", this);
        EventManager.registerEvent("DEBUG_GIVE_ROAD", this);
        EventManager.registerEvent("DEBUG_GIVE_YEAR", this);
        
        
                ///////////////NEW EVENTS////////////////
        EventManager.registerEvent("PLAYER_WISHESTO_START_TURN", this);
        EventManager.registerEvent("PLAYER_WISHESTO_ROLL", this);
        EventManager.registerEvent("PLAYER_ROLLED_SUCCESSFULLY", this);
        
        EventManager.registerEvent("PLAYER_WISHESTO_BUILD_ROAD", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUILD_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUILD_CITY", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUY_ROAD", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUY_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUY_CITY", this);
        EventManager.registerEvent("PLAYER_WISHESTO_BUY_DEV", this);
        EventManager.registerEvent("PLAYER_WISHESTO_TRADE", this);

        EventManager.registerEvent("PLAYER_WISHESTO_PLAY_DEV", this);
        EventManager.registerEvent("PLAYER_WISHESTO_PLAY_ROAD", this);
        EventManager.registerEvent("PLAYER_WISHESTO_PLAY_MONOPLY", this);
        EventManager.registerEvent("PLAYER_WISHESTO_PLAY_KNIGHT", this);
        EventManager.registerEvent("PLAYER_WISHESTO_PLAY_DISCOVERY", this);

        EventManager.registerEvent("PLAYER_HAS_ROLLED_A", this);
        
        EventManager.registerEvent("PLAYER_MUST_START_TURN", this);
        EventManager.registerEvent("PLAYER_MUST_ROLL", this);
        EventManager.registerEvent("PLAYER_MUST_BUILD_ROAD", this);
        EventManager.registerEvent("PLAYER_MUST_BUILD_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_MUST_BUILD_CITY", this);

        EventManager.registerEvent("PLAYER_MUST_DISCARD_HALF_DECK", this);
        EventManager.registerEvent("PLAYER_MUST_MOVE_ROBBER", this);
        EventManager.registerEvent("PLAYER_MUST_STEAL_RESOURCE", this);

        EventManager.registerEvent("GAME_START", this);
               
    }
	
	//updates the buttons on the build screen to keep available options updated for the player
	public void updateBuildButtons()
	{
		if (GameState.getCurPlayer().canBuyRoad() == false)
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_road.setEnabled(false);
		else
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_road.setEnabled(true);

		if (GameState.getCurPlayer().canBuyCity() == false)
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_city.setEnabled(false);
		else
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_city.setEnabled(true);

		if (GameState.getCurPlayer().canBuyDevCard() == false)
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_dev.setEnabled(false);
		else
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_dev.setEnabled(true);
		
		if (GameState.getCurPlayer().canBuySettlement() == false)
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_settlement.setEnabled(false);
		else
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_settlement.setEnabled(true);
		
		if (GameState.getCurPlayer().getDevCards().getSize() > 0)
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_play.setEnabled(true);
		else
			ContainerGUI.settlersGUI.getBottomPanel().getButtonPanel().build_play.setEnabled(false);
	}
    
}
