package settlers.game.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;
import settlers.game.*;
import settlers.game.elements.Player;
import settlers.game.elements.AIVeryEasy;
import settlers.game.events.Event;
import settlers.game.events.EventListener;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.logic.Logic;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JOptionPane;



public class SettlersEvent implements EventListener 
{
    
    SettlersGUI gui;
    
    //SettlersController sc;
    
    MainBoard mainBoard;
    BottomPanel bottomPanel;
    
    public SettlersEvent(SettlersGUI _gui)
    {
        EventManager.registerEvent("PLAYER_TURN_END", this);
        EventManager.registerEvent("PLAYER_TURN_START", this);
        gui = _gui;
        init();
    }
    
    private void init()
    {
        mainBoard = gui.getMainBoard();
        bottomPanel = gui.getBottomPanel();
    }
    
    public void startNewGame()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_LOADING)
        {
            if(GameState.players.size() > 1)
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
                JOptionPane.showMessageDialog(gui, "There aren't enought players...add some!");
            }
        }
        else
        {
            int value = JOptionPane.showConfirmDialog(gui, "Do you want to start a new game?");
            if(value == JOptionPane.OK_OPTION)
            {
                GameState.setGamePhase(GlobalVar.GAME_INIT);
                this.bottomPanel.getButtonPanel().setEvent(this);
                mainBoard.getGameBoard().initialize();
            
                PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
                GameState.setCurPlayer(GameState.players.getFirst());
                EventManager.callEvent(e); 
                
                gui.getMainBoard().getGameBoard().repaint();
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
    private void createComputerPlayer(String _name, Color _color) {
        // TODO Auto-generated method stub
        if(mainBoard.isPlayerPanel() == false)
        {
            //Then we haven't made a game board yet...do so
            mainBoard.makePlayerPanel();
        }
        Player newPlayer = new AIVeryEasy(_name, _color);
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
            
            int value = JOptionPane.showConfirmDialog(gui, "Do you want to remake the board?");
            if(value == JOptionPane.OK_OPTION)
            {
                GameState.setGamePhase(GlobalVar.GAME_INIT);
                this.bottomPanel.getButtonPanel().setEvent(this);
                mainBoard.getGameBoard().initialize();
                
                PlayerEvent n = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
                GameState.setCurPlayer(GameState.players.getFirst());
                EventManager.callEvent(n); 
                
                gui.getMainBoard().getGameBoard().repaint();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(gui, "Cannot do this when the game has already started");
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
                String name = JOptionPane.showInputDialog("Please enter the name of Player " + (GameState.players.size() + 1) + ".");
                Color color = JColorChooser.showDialog(gui,"Choose Background Color for " + name.toString(),Color.black);
                Player newPlayer = new Player(name, color);
                GameState.players.add(newPlayer);
                mainBoard.getPlayerPanel().addPlayer(newPlayer);
            }
            else
            {
                JOptionPane.showMessageDialog(gui, "There are already 8 players, more cannot be added!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(gui, "Cannot add players after a game has started!");
        }
    }
    
    public void addComputerPlayer()
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_LOADING)
        {
            if(mainBoard.isPlayerPanel() == false)
            {
                //Then we haven't made a game board yet...do so
                mainBoard.makePlayerPanel();
            }
            if(GameState.players.size() < 4)
            {
                Color color = JColorChooser.showDialog(gui,"Choose Background Color",Color.black);
                Player newPlayer = new AIVeryEasy("AIVeryEasy", color);
                GameState.players.add(newPlayer);
                mainBoard.getPlayerPanel().addPlayer(newPlayer);
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
    
    
    // combines 3 add player and start new game, with no error checking (so don't use in the middle of a game!)
    public void quickStart(boolean big)
    {
        if(GameState.getGamePhase() == GlobalVar.GAME_STARTED)
        {
            JOptionPane.showMessageDialog(gui, "Can't do this since the game has started.");
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
    public void quickStartComp()
    {
    	if(GameState.getGamePhase() == GlobalVar.GAME_STARTED)
    	{
    		JOptionPane.showMessageDialog(gui, "Can't do this since the game has started.");
    		return;
    	}
        createPlayer("Eric", Color.red);
        createComputerPlayer("AIVeryEasy", Color.black);
        createComputerPlayer("AIVeryEasy", Color.green);
        
        bottomPanel.getButtonPanel().startNewGame();
        bottomPanel.getTabbedPanel().startNewGame();
        
        GameState.setGamePhase(GlobalVar.GAME_INIT);
        this.bottomPanel.getButtonPanel().setEvent(this);
        mainBoard.getGameBoard().initialize();
        
        PlayerEvent e = new PlayerEvent("GAME_START", GameState.players.getFirst()); // this event is registered by logic to begin players' turns
        GameState.setCurPlayer(GameState.players.getFirst());
        EventManager.callEvent(e);
    }

    public void eventCalled(Event e) {
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
            bottomPanel.getButtonPanel().startNewTurn();
            mainBoard.getStatusBar().setText(GameState.getCurPlayer().getName() + ": ROLL PHASE");
        }
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
