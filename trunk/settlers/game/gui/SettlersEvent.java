package settlers.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import java.util.Random;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.Event;
import settlers.game.events.EventListener;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;



public class SettlersEvent implements EventListener 
{
    
    SettlersGUI gui;
    
    //SettlersController sc;
    
    MainBoard mainBoard;
    BottomPanel bottomPanel;
    int choice = 0;
    
    public SettlersEvent()
    {
        EventManager.registerEvent("PLAYER_TURN_END", this);
        EventManager.registerEvent("PLAYER_TURN_START", this);
        init();
    }
    
    private void init()
    {
        mainBoard = ContainerGUI.mainBoard;
        bottomPanel = ContainerGUI.bottomPanel;
        Profile.loadDefaultProfiles();
    }
    
    public void startNewGame()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_LOADING)
        {
            if(GameState.players.size() > 2)
            {
                bottomPanel.getButtonPanel().startNewGame();
                bottomPanel.getTabbedPanel().startNewGame();
                
                GameState.setGamePhase(GlobalVar.GAME_INIT);
                this.bottomPanel.getButtonPanel().setEvent(this);
                mainBoard.getGameBoard().initialize();
                
                PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
                GameState.setCurPlayer(GameState.players.getFirst());
                EventManager.callEvent(e);                
            }
            else
            {
                JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "There aren't enought players...add some!  You need three or more.");
            }
        }
        else
        {
            int value = JOptionPane.showConfirmDialog(ContainerGUI.settlersGUI, "Do you want to start a new game?");
            if(value == JOptionPane.OK_OPTION)
            {
                GameState.setGamePhase(GlobalVar.GAME_INIT);
                this.bottomPanel.getButtonPanel().setEvent(this);
                mainBoard.getGameBoard().initialize();
            
                PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
                GameState.setCurPlayer(GameState.players.getFirst());
                EventManager.callEvent(e); 
                
                ContainerGUI.gameBoard.repaint();
            }
        }
    }
    
    private void createPlayer(String _name, Color _color) {
        // TODO Auto-generated method stub
        if(mainBoard.isPlayerPanel() == false)
        {
            //Then we haven't made a game board yet...do so
            mainBoard.makePlayerPanel();
        }
        Player newPlayer = new Player(_name, _color);
        GameState.players.add(newPlayer);
     
        mainBoard.getPlayerPanel().addPlayer(newPlayer);
    }
    
    private void createCompPlayer(Color _color) {
        // TODO Auto-generated method stub
        if(mainBoard.isPlayerPanel() == false)
        {
            //Then we haven't made a game board yet...do so
            mainBoard.makePlayerPanel();
        }
        AIVeryEasy newPlayer = new AIVeryEasy("AIVeryEasy", _color);
        GameState.players.add(newPlayer);
     
        mainBoard.getPlayerPanel().addPlayer(newPlayer);
    }

    public void remakeBoard() {
        // TODO Auto-generated method stub
        
        if(GameState.getGamePhase() == GlobalVar.GAME_INIT)
        {
            PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
            GameState.setCurPlayer(GameState.players.getFirst());
            EventManager.callEvent(e); 
            
            int value = JOptionPane.showConfirmDialog(ContainerGUI.settlersGUI, "Do you want to remake the board?");
            if(value == JOptionPane.OK_OPTION)
            {
                GameState.setGamePhase(GlobalVar.GAME_INIT);
                this.bottomPanel.getButtonPanel().setEvent(this);
                mainBoard.getGameBoard().initialize();
                
                PlayerEvent n = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
                GameState.setCurPlayer(GameState.players.getFirst());
                EventManager.callEvent(n); 
                
                ContainerGUI.gameBoard.repaint();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "Cannot do this when the game has already started");
        }
        
    }

    public void addPlayer()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_LOADING)
        {
            if(mainBoard.isPlayerPanel() == false)
            {
                //Then we haven't made a game board yet...do so
                mainBoard.makePlayerPanel();
            }
            if(GameState.players.size() < GlobalVar.MAX_NUMBER_PLAYERS)
            {
                Profile.createPlayerDialog((GameState.players.size() + 1));
            }
            else
            {
                JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "There are already 8 players, more cannot be added!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "Cannot add players after a game has started!");
        }
    }
    
    public void addCompPlayer()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_LOADING)
        {
            if(mainBoard.isPlayerPanel() == false)
            {
                //Then we haven't made a game board yet...do so
                mainBoard.makePlayerPanel();
            }
            if(GameState.players.size() < GlobalVar.MAX_NUMBER_PLAYERS)
            {
                if(choice == 0)
                {
                	createCompPlayer(Color.black);
                }
                else if(choice == 1)
                {
                	createCompPlayer(Color.blue);
                }
                else if(choice == 2)
                {
                	createCompPlayer(Color.cyan);
                }
                else if(choice == 3)
                {
                	createCompPlayer(Color.yellow);
                }
                else if(choice == 4)
                {
                	createCompPlayer(Color.gray);
                }
                else if(choice == 5)
                {
                	createCompPlayer(Color.green);
                }
                else if(choice == 6)
                {
                	createCompPlayer(Color.pink);
                }
                else if(choice == 7)
                {
                	createCompPlayer(Color.magenta);
                }
                else if(choice == 8)
                {
                	createCompPlayer(Color.orange);
                }
                choice++;
            }
            else
            {
                JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "There are already 8 players, more cannot be added!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "Cannot add players after a game has started!");
        }
    }
    
    
    // combines 3 add player and start new game, with no error checking (so don't use in the middle of a game!)
    public void quickStart(boolean big)
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_STARTED)
        {
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "Can't do this since the game has started.");
            return;
        }
        createPlayer("Player Red", Color.red);
        createPlayer("Player Black", Color.black);
        createPlayer("Player Green", Color.green);
        if (big == true)
        {
            createPlayer("Player Orange", Color.orange);
            createPlayer("Player Blue", Color.blue);
            createPlayer("Player Magenta", Color.magenta);
        }
        bottomPanel.getButtonPanel().startNewGame();
        bottomPanel.getTabbedPanel().startNewGame();
        
        GameState.setGamePhase(GlobalVar.GAME_INIT);
        this.bottomPanel.getButtonPanel().setEvent(this);
        mainBoard.getGameBoard().initialize();
        
        PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
        GameState.setCurPlayer(GameState.players.getFirst());
        EventManager.callEvent(e);                    
    }
    
    public void compQuickStart()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_STARTED)
        {
            JOptionPane.showMessageDialog(ContainerGUI.settlersGUI, "Can't do this since the game has started.");
            return;
        }
        createPlayer("Player Red", Color.red);
        createCompPlayer(Color.black);
        createCompPlayer(Color.green);
 
        bottomPanel.getButtonPanel().startNewGame();
        bottomPanel.getTabbedPanel().startNewGame();
        
        GameState.setGamePhase(GlobalVar.GAME_INIT);
        this.bottomPanel.getButtonPanel().setEvent(this);
        mainBoard.getGameBoard().initialize();
        
        PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
        GameState.setCurPlayer(GameState.players.getFirst());
        EventManager.callEvent(e);                    
    }

    public boolean eventCalled(Event e) {
        // TODO Auto-generated method stub
        if(e.getEvent() == "PLAYER_TURN_END")
        {
            
        /*
            if(GameState.getCurPlayer().getID() == GameState.players.size())
                GameState.setCurPlayer(GameState.players.get(0));
            else
                GameState.setCurPlayer(GameState.players.get(GameState.getCurPlayer().getID()));
            //PlayerEvent n = new PlayerEvent("PLAYER_TURN_START", GameState.getCurPlayer());
            //EventManager.callEvent(n);*/
        }
        if(e.getEvent() == "PLAYER_TURN_START")
        {
            GameState.setActionState(0);
            GameState.diceHasBeenRolledDuringTurn = false;
            bottomPanel.getButtonPanel().startNewTurn();
            mainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": ROLL PHASE");
        }
        return true;
    }
    
    public void muchMoney()
    {
        int i = 0;
        for (i = 0; i < GameState.players.size(); i++)
        {
            Player p = GameState.players.get(i);
            p.alterResource(GlobalVar.WOOD, 150, 0);
            p.alterResource(GlobalVar.ORE, 150, 0);
            p.alterResource(GlobalVar.WHEAT, 150, 0);
            p.alterResource(GlobalVar.BRICK, 150, 0);
            p.alterResource(GlobalVar.SHEEP, 150, 0);
            p.alterResource(GlobalVar.WOOD, 150, 0);
        }    
        
    }
}
