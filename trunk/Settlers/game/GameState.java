//this class holds the state of the game.  It will hold the players, game board, resources, cards, etc.
//implemented statically: no need to make it an object

package settlers.game;

import java.util.*;

import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.gui.*;
import settlers.game.logic.Logic;

public class GameState
{
    // holds all the players
    public static LinkedList<Player> players;
    private static Player curPlayer;
    
    // called to initialize the game when it is first started
	public static void initialize()
	{
		new Logic(); //sets up the logic of the game - no need to keep as a variable (yet), but it needs to be an object so it can receive events
		new Gui();
        
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
}
