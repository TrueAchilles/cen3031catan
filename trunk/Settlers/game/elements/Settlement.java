package settlers.game.elements;

public class Settlement
{
	//defines the x and y locations of the nodes
	int xCord;
	int yCord;
	
	//not really sure what these do
	int xIndex,yIndex;
	
	int onBoard;
	
	int player;
	
	//define roads that are connected to this node
	Road topRoad;
	Road sideRoad;
	Road bottomRoad;
	
	//define nodes that are connected to this node by the appropriate roads
	Settlement topNode;
	Settlement sideNode;
	Settlement bottomNode;
	
	//defines whether this Node has a city
	boolean hasCity;
	
	//defines whether this node has a settlement
	boolean hasSettlement;
	
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
	public void updateNode(int tempI, int tempJ, int x, int y, Settlement top, Settlement bottom, Settlement side)
	{
		topNode = top;
		bottomNode = bottom;
		sideNode = side;
		xIndex = tempI;
		yIndex = tempJ;
		xCord = x + 125;
		yCord = y + 100;
		onBoard = 1;
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
	
	
	//determines if a city can be built here
	//first checks to see if there is a city here and then builds the settlement
	public void buildCity()
	{
		if (hasSettlement)
		{
			hasCity = true;
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
		if (onBoard == 0)
			return;
		if (topNode.hasSettlement() || bottomNode.hasSettlement() || sideNode.hasSettlement() )
			return;
		hasSettlement = true;
	}
	
	public boolean canBuildSettlement()
	{
		if (onBoard == 0)
			return false;
		if ( hasSettlement || topNode.hasSettlement() || bottomNode.hasSettlement() || sideNode.hasSettlement() )
			return false;
		return true;
	}
	
	
	//builds a road extending upward from the node
	public void buildRoadUp()
	{
		topRoad.build();
	}
	
	//builds a road extending sideways from the node
	public void buildRoadSide()
	{
		sideRoad.build();
	}
	
	//builds a road extending downward from the node
	public void buildRoadDown()
	{
		bottomRoad.build();
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
	public boolean settlementBreadth(int distance)
	{
		return false;
	}
}