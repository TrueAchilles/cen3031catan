package settlers.game.elements;

import settlers.game.GameState;
import settlers.game.GlobalVar;

public class Settlement
{
	//defines the x and y locations of the nodes
	private int xCord=0;
	private int yCord=0;
	
	//not really sure what these do
	private int xIndex,yIndex;
	
	private int onBoard;
	
	Player player;
	
	//define roads that are connected to this node
	private Road topRoad;
	private Road sideRoad;
	private Road bottomRoad;
	
	//define nodes that are connected to this node by the appropriate roads
	private Settlement topNode;
	private Settlement sideNode;
	private Settlement bottomNode;
	
    // allows me to draw resources
    private Resource resourceDrawer;
    
	//defines whether this Node has a city
	boolean hasCity;
	
	//defines whether this node has a settlement
	boolean hasSettlement;
	private Player owner = null;
	
	//need 3 variables here to hold the tiles that are adjacent to this node
	
	//constructor which initializes all values to null
	public Settlement(int x, int y)
	{
		xIndex = x;
		yIndex = y;
		topRoad = null;
		sideRoad = null;
		bottomRoad = null;
		topNode = null;
		sideNode = null;
		bottomNode = null;
		onBoard = 0;
	}
	
	//constructor that receives the coordinates of this node
	public void updateNode(int tempI, int tempJ, int x, int y, Settlement top, Settlement bottom, Settlement side, int _onBoard)
	{
		topNode = top;
		bottomNode = bottom;
		sideNode = side;
		xIndex = tempI;
		yIndex = tempJ;
		xCord = x;
		yCord = y;
		onBoard = _onBoard;
	}
    
    public void updateNode(int tempI, int tempJ, Settlement top, Settlement bottom, Settlement side, int _onBoard)
	{
		topNode = top;
		bottomNode = bottom;
		sideNode = side;
		xIndex = tempI;
		yIndex = tempJ;
		onBoard = _onBoard;
	}
	
	//returns the x coordinate of this node
	public int getXcord()
	{
		return xCord;
	}
	
	//returns the y coordinate of this node
	public int getYcord()
	{
		return yCord;
	}
	
	// allows me to set a node as buildable
	public void setOnBoard(int a)
	{
		onBoard = a;
	}
	
	public int getOnBoard()
	{
		return onBoard;
	}
	
    
    public void giveResources(int rType)
    {
        if (owner == null)
            return;
        if (hasSettlement)
            owner.giveResource(rType);
        if (hasCity)
            owner.giveResource(rType);
    }
	
	//determines if a city can be built here
	//first checks to see if there is a city here and then builds the settlement
	public void buildCity()
	{
		if (hasSettlement)
		{
			hasCity = true;
            owner.incrementVictoryPointTotal();
		}
	}
	
	public void build()
	{
	
	}
	
	public boolean hasSettlement()
	{
		return hasSettlement;
	}
	
	public void buildSettlement()
	{
        System.out.println("Attemtping to build settlement for " + GameState.getCurPlayer().getID());
		if (onBoard == 0)
			return;
		if ( (topNode != null && topNode.hasSettlement()) || (bottomNode != null && bottomNode.hasSettlement()) || (sideNode != null && sideNode.hasSettlement() ))
			return;
		hasSettlement = true;
		owner = GameState.getCurPlayer();
        owner.incrementVictoryPointTotal();
        System.out.println("successfully built - owner has " + owner.getVictoryPointTotal() + " victory points.");
	}
	
	public boolean canBuildSettlement()
	{
		if (onBoard == 0)
			return false;
        
        
		if ( hasSettlement || (topNode == null ? false : topNode.hasSettlement() ) || (bottomNode == null ? false : bottomNode.hasSettlement()) || (sideNode == null ? false : sideNode.hasSettlement()) )
			return false;
        if ( GameState.getGamePhase() == GlobalVar.GAME_INIT )
            return true;
        Player curP = GameState.getCurPlayer();
        if ( ( topRoad == null ? false : topRoad.getOwner() == curP ) || ( bottomRoad == null ? false : bottomRoad.getOwner() == curP ) ||  ( sideRoad == null ? false : sideRoad.getOwner() == curP ) )
            return true;
        return false;
    }
	
	
	public boolean hasAdjacentUserDevelopment()
	{
		int curPlayerID = GameState.getCurPlayer().getID();
		
		if( hasSettlement && owner.getID() == curPlayerID ) 
			return true;
		if ( topRoad.hasRoad() && topRoad.getOwner().getID() == curPlayerID ) 
			return true;
		
		if ( sideRoad.hasRoad() && sideRoad.getOwner().getID() == curPlayerID )
			return true;
		
		if ( bottomRoad.hasRoad() && bottomRoad.getOwner().getID() == curPlayerID )
			return true;
		return false;
	}
	
	
	//builds a road extending upward from the node
	public void buildRoad( Settlement toSet)
	{
		if ( toSet.getYcord() > yCord )
			topRoad.build(this, toSet);
		else if (toSet.getYcord() == yCord )
			sideRoad.build(this, toSet);
		else
			bottomRoad.build(this, toSet);
	}

	//returns the node to the top of this node
	public Settlement getTopNode()
	{
		return topNode;
	}
	
	//returns the node to the side of this node
	public Settlement getSideNode()
	{
		return sideNode;
	}
	
	//returns the node to the bottom of this node
	public Settlement getBottomNode()
	{
		return bottomNode;
	}
	
	//returns the road that extends upwards from this node
	public Road getTopRoad()
	{
		return topRoad;
	}
	
	//returns the road that extends to the side from this node
	public Road getSideRoad()
	{
		return sideRoad;
	}
	
	//returns the road that extends downward from this node
	public Road getBottomRoad()
	{
		return bottomRoad;
	}
	
	//does  something
	public int roadBreadth()
	{
		return 0;
	}
	
	
	public void initializeRoad()
	{
		
			
			if (topNode != null && topRoad == null)
			{
				topRoad = new Road(this, topNode, owner);
				topNode.bottomRoad = topRoad;
			}
			if (bottomNode != null && bottomRoad == null)
			{
				bottomRoad = new Road(this, bottomNode, owner);
				bottomNode.topRoad = bottomRoad;
			}
			if (sideNode != null && sideRoad == null)
			{
				sideRoad = new Road(this, sideNode, owner);
				sideNode.sideRoad = sideRoad;
			}
		
	}
	
	public Player getOwner()
	{
		return owner;
	}
    
    public Resource getDrawResourceHelper()
    {
        return resourceDrawer;
    }
    
    public void setDrawResourceHelper(Resource r)
    {
        resourceDrawer = r;
    }
    
    public boolean checkExtendRoad()
    {
        Player curP = GameState.getCurPlayer();
        if  ( ( sideRoad != null && curP == sideRoad.getOwner() ) ||
            ( topRoad != null && curP == topRoad.getOwner() ) ||
            ( bottomRoad != null && curP == bottomRoad.getOwner() ) )
            return true;
        else
            return false;
    }
    
    public String toString()
    {
        return 
        "("+xIndex+", " +yIndex+")...(" + xCord+", "+yCord+")"+
        ( onBoard ==1 ? "SETT" : "nott" ) +
        (topNode == null ? "t:0," : "t:1,")+
        (bottomNode == null ? "b:0," : "b:1,")+ 
		(sideNode == null ? "s:0\t" : "s:1\t");
    /*
        return 
        "Settlement "+xIndex+", " +yIndex+") at " + xCord+", "+yCord+")"+
        ( onBoard ==1? "\nOn Board" : "\nNot on board" ) +
        (topNode == null ? "\nNo Top Node " : "\nHas Top Node")+
        (bottomNode == null ? "\nNo Bottom Node " : "\nHas Bottom Node")+ 
		(sideNode == null ? "\nNo Side Node " : "\nHas Side Node");*/
    }
    
}