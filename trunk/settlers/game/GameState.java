/**
 * This class holds the state of the game.  It will hold the players, game board, resources, cards, etc.
 * It is implemented statically: so there is no need to make it an object
 */
package settlers.game;

import java.util.*;

import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.gui.*;

public class GameState
{
    // holds all the players
    public static LinkedList<Player> players;
    private static Player curPlayer;
    private static int actionState = 0;
    private static int gamePhase = 0;
    
    private static GamePlay gamePlay;
    
    /**
     * Called to initialize the game when it is first started. 
     */
    public static void initialize()
    {
        //sets up the logic of the game - no need to keep as a variable (yet), but it needs to be an object so it can receive events
        gamePlay = new GamePlay();
        new ContainerGUI();
        // code for handling init of game goes here (making game board, setting up players, etc)
        players = new LinkedList<Player>();
    }
    
    public static void setCurPlayer(Player _curPlayer)
    {
        curPlayer = _curPlayer;
    }
    
    public static Player getCurPlayer()
    {
        return curPlayer;
    }
    
    public static int getActionState()
    {
        return actionState;
    }
    
    /**
     * Sets the current action state.&nbsp;Available actions are static ACTION_ADD_SETTLEMENT, ACTION_ADD_ROAD and ACTION_ADD_CITY from GlobalVar.
     *
     * @param action int to be set as the action
     */
    public static void setActionState(int action)
    {
        actionState = action;
    }
    
    public static int getGamePhase()
    {
        return gamePhase;
    }
    
    /**
     * Sets the current game phase.&nbsp;Available phases are static GAME_LOADING, GAME_INIT and GAME_STARTED from GlobalVar.
     *
     * @param phase int to be set as the action
     */
    public static void setGamePhase(int phase)
    {
        gamePhase = phase;
    }
    
    public static GamePlay getLogic()
    {
        return gamePlay;
    }
    
    public static GamePlay getGui()
    {
        return gamePlay;
    }
}
