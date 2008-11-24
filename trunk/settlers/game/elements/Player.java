//contains all the data for a given player

package settlers.game.elements;

import java.awt.Color;
import java.util.Random;

import settlers.game.*;
import settlers.game.events.Event;
import settlers.game.elements.LongestRoad;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.events.SettlementEvent;

import settlers.game.gui.PlayerAvatar;
import settlers.game.gui.Deck;
import settlers.game.gui.DevelopmentCard;

public class Player
{
    private static int totalNum = 0;
    private Color playerColor;
    private int id = 0;
    String name = null;
    private int resource[] = { 2, 2, 2, 2, 2, 2 }; 
    //private int devCards[] = {0, 0, 0, 0, 0};
    private int victoryPointTotal;
    boolean isComputer = false;
    private Player nextPlayer;
    private Player prevPlayer;
    private LongestRoad longRoad;
    private int numberOfResourceCards = 12;
    private int numberOfDevCards = 0;
    private boolean isAI = false;
    private PlayerAvatar playerAvatar = null;
    private PlayerProfile playerProfile = null;

    private Deck devCards;
    
    public Player(String _name, Color _color)
    {
        name = _name;
        id = ++totalNum;
        playerColor = _color;
        victoryPointTotal = 0;     
        longRoad = new LongestRoad(this);
        devCards = new Deck("player", ContainerGUI.gameBoard);
    }

    public Player(String _name, Color _color, PlayerAvatar _playerAvatar)
    {
        name = _name;
        id = ++totalNum;
        playerColor = _color;
        victoryPointTotal = 0;     
        longRoad = new LongestRoad(this);
        devCards = new Deck("player", ContainerGUI.gameBoard);
        playerAvatar = _playerAvatar;
    }

    public void setasAI()
    {
        isAI = true;
    }    
    
    public String getName()
    {
        return name;
    }
    
    public Color getColor()
    {
        return playerColor;
    }
    
    public int getID()
    {
        return id;
    }

    public PlayerAvatar getPlayerAvatar()
    {
        return playerAvatar;
    }

    public int getVictoryPointTotal()
    {
        return victoryPointTotal;
    }
    
    public int getWood()
    {
        return resource[GlobalVar.WOOD];
    }
    
    public int getOre()
    {
        return resource[GlobalVar.ORE];
    }
    
    public int getBrick()
    {
        return resource[GlobalVar.BRICK];
    }
    
    public int getSheep()
    {
        return resource[GlobalVar.SHEEP];
    }
    
    public int getWheat()
    {
        return resource[GlobalVar.WHEAT];
    }
    
    public int getNumberOfResCards()
    {
        return numberOfResourceCards;
    }
    
    public int getNumberOfDevCards()
    {
        return numberOfDevCards;
    }
    
    public void giveResource(int rType)
    {
        resource[rType]++;
        numberOfResourceCards++;
    }
    
    public void subtractResource(int rType)
    {
        resource[rType]--;
        numberOfResourceCards--;
    }

        // works like previous two functions, but permits a greater
        // addition or subtraction than 1 resource
    public void alterResource(int rType, int amountAlteration, int operationFlag)
    {
        switch(operationFlag)
        {
            case 0: // 0 == addition
                resource[rType] += amountAlteration;
                break;
            case 1: // 1 == subtraction
                resource[rType] -= amountAlteration;
                break;
        }
        resource[rType]++;
    }

    public void printResources()
    {
        System.out.println(getBrick()+"\n"+getOre()+"\n"+getSheep()+"\n"+getWheat()+"\n"+getWood()+"\n");
    }

    public void incrementVictoryPointTotal()
    {
        victoryPointTotal++;
        if(victoryPointTotal == 10)
        {
            PlayerEvent n = new PlayerEvent("GAME_END", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
    }
    
    /*
    public void addDevCard(int dType)
    {
        devCards[dType]++;
        numberofDevCards++;
        
    }
    public void removeDevCard(int dType)
    {
        devCards[dType]--;
        numberofDevCards--;
    }
    public boolean hasDevCards()
    {
        if (devCards[0] + devCards[1] + devCards[2] + devCards[3] + devCards[4] == 0) 
            return false;
       return true;
    }*/
    
    public Deck getDevCards()
    {
        return devCards;
    }
    
    public boolean canBuySettlement()
    {
        if(resource[GlobalVar.BRICK] > 0 && resource[GlobalVar.WHEAT] > 0 && resource[GlobalVar.WOOD] > 0 && resource[GlobalVar.SHEEP] > 0)
            return true;
        return false;
    }
    
    public boolean canBuyRoad()
    {
        if(resource[GlobalVar.BRICK] > 0 && resource[GlobalVar.WOOD] > 0)
            return true;
        return false;
    }
    
    public boolean canBuyCity()
    {
        if(resource[GlobalVar.ORE] > 2 && resource[GlobalVar.WHEAT] > 1)
            return true;
        return false;
    }
    
    public boolean canBuyDevCard()
    {
        if(resource[GlobalVar.ORE] > 0 && resource[GlobalVar.WHEAT] > 0 && resource[GlobalVar.SHEEP] > 0)
            return true;
        return false;
    }
    
    public void buildSettlement()
    {
        resource[GlobalVar.BRICK]--;
        resource[GlobalVar.WHEAT]--;
        resource[GlobalVar.WOOD]--;
        resource[GlobalVar.SHEEP]--;
        numberOfResourceCards -= 4;
    }
    
    public void buildRoad()
    {
        resource[GlobalVar.BRICK]--;
        resource[GlobalVar.WOOD]--;
        numberOfResourceCards -= 2;
    }
    
    public void buildCity()
    {
        resource[GlobalVar.ORE] -= 3;
        resource[GlobalVar.WHEAT] -= 2;
        numberOfResourceCards -= 5;
    }
    
    public void buildDevCard()
    {
        resource[GlobalVar.ORE]--;
        resource[GlobalVar.WHEAT]--;
        resource[GlobalVar.SHEEP]--;
        numberOfResourceCards -= 3;
    }
    
    public void setPreviousPlayer(Player p)
    {
        prevPlayer = p;
    }
    
    public Player getPreviousPlayer()
    {
        return prevPlayer;
    }
    
    public void setNextPlayer(Player p)
    {
        nextPlayer = p;
    }
    
    public Player getNextPlayer()
    {
        return nextPlayer;
    }

    public void setPlayerProfile(PlayerProfile playerProfile)
    {
        this.playerProfile = playerProfile;
    }

    public PlayerProfile getPlayerProfile()
    {
        return playerProfile;
    }

    public void addRoad(Road r)
    {
        int len = longRoad.addRoad(r);
           System.out.println("Biggest length is "+len);
    
    }
    //These are for the AI
    public void initSettlementPlacement()
    {}
    
    public void initRoadPlacement()
    {}
    
    public void startTurn()
	{}
	
	public void roll()
	{}
	
	public void rollToTrade()
	{}
	
	public void tradeToBuild()
	{}
	
	public void turnEnd()
	{}
		
	public void actBuild() 
	{}
    
    public String toString()
    {
        return name;
    }
}
