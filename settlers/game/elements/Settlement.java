package settlers.game.elements;

import settlers.game.GameState;
import settlers.game.GlobalVar;

public class Settlement
{
    //defines the x and y locations of the nodes
    private int xCord=0;
    private int yCord=0;
    
    //uniquely identifies the settlement and its location.
    private int xIndex,yIndex;
    
    private boolean isOcean;
    
    //defines whether this Node has a city
    private boolean hasCity;
    
    //defines whether this node has a settlement
    private boolean hasSettlement;
    
    //define the owner of this settlement or city
    private Player owner = null;    
    
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
        hasSettlement = false;
        hasCity = false;
        owner = null;
        isOcean = true;
    }
    
    //constructor that receives the coordinates of this node
    public void updateNode(int tempI, int tempJ, int x, int y, Settlement top, Settlement bottom, Settlement side, boolean ocean)
    {
        topNode = top;
        bottomNode = bottom;
        sideNode = side;
        xIndex = tempI;
        yIndex = tempJ;
        xCord = x;
        yCord = y;
        isOcean = ocean;  
    }
    
    public void updateNode(int tempI, int tempJ, Settlement top, Settlement bottom, Settlement side, boolean ocean)
    {
        topNode = top;
        bottomNode = bottom;
        sideNode = side;
        xIndex = tempI;
        yIndex = tempJ;
        isOcean = ocean;
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
    public void setAsOcean(boolean a)
    {
        isOcean = a;
    }
    
    public boolean checkIfOcean()
    {
        return isOcean;
    }
    
    
    public boolean giveResources(int rType)
    {
        if (owner == null)
            return false;
        if (hasSettlement)
            owner.giveResource(rType);
        if (hasCity)
            owner.giveResource(rType);
        return true;
    }
    
    
    public boolean hasSettlement()
    {
        return hasSettlement;
    }
    
    public boolean canBuildSettlement(Player p)
    {
        if (isOcean == true)
            return false;
        if ( hasSettlement )
            return false;
        if ( (topNode == null ? false : topNode.hasSettlement() ) || (bottomNode == null ? false : bottomNode.hasSettlement()) || (sideNode == null ? false : sideNode.hasSettlement() ) )
            return false; // if there is an adjacent settlement then return false.
        if ( GameState.getGamePhase() == GlobalVar.GAME_INIT )
            return true;
        if ( ( topRoad == null ? false : topRoad.getOwner() == p ) || ( bottomRoad == null ? false : bottomRoad.getOwner() == p ) ||  ( sideRoad == null ? false : sideRoad.getOwner() == p ) )
            return true;
        return false;
    }
    
    public boolean canBuildSettlement()
    {
        return canBuildSettlement(GameState.getCurPlayer());
    }
    
    public boolean buildSettlement(Player p)
    {
        System.out.println("Attemtping to build settlement for " + p.toString());
        if ( canBuildSettlement(p) ) {
            hasSettlement = true;
            owner = p;
            owner.incrementVictoryPointTotal();
            System.out.println("successfully built - owner has " + owner.getVictoryPointTotal() + " victory points.");
            return true;
        }
        else {
            System.out.println("Failed building settlement for " + p.toString() + ".");
            return false;
        }
    }
    public boolean buildSettlement()
    {
        return buildSettlement(GameState.getCurPlayer());
    }
    
    public boolean hasCity()
    {
        return hasCity;
    }
    
    
    public boolean canBuildCity(Player p)
    {
        if ( hasSettlement && owner == p )
            return true;
        return false;
    }
    
    public boolean canBuildCity()
    {
        return canBuildCity(GameState.getCurPlayer());
    }
    
    
        //determines if a city can be built here
    //first checks to see if there is a city here and then builds the settlement
    public boolean buildCity(Player p)
    {
        if ( canBuildCity(p) )
        {
            hasCity = true;
            owner.incrementVictoryPointTotal();
            System.out.println("successfully upgraded to city - owner has " + owner.getVictoryPointTotal() + " victory points.");
            return true;
        }
        return false;
    }
    public boolean buildCity()
    {
        return buildCity(GameState.getCurPlayer());
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
    
    
    public boolean checkRoadExtension(Player p)
    {
        if  ( ( sideRoad != null && p == sideRoad.getOwner() ) ||
            ( topRoad != null && p == topRoad.getOwner() ) ||
            ( bottomRoad != null && p == bottomRoad.getOwner() ) )
            return true;
        else
            return false;
    }
    
    public boolean checkRoadExtension()
    {
        return checkRoadExtension(GameState.getCurPlayer() );
    }
    
    
    public String toString()
    {
        return 
        "Settlement("+xIndex+", " +yIndex+")";
    /*
        return 
        "Settlement "+xIndex+", " +yIndex+") at " + xCord+", "+yCord+")"+
        ( isOcean ==1? "\nOn Board" : "\nNot on board" ) +
        (topNode == null ? "\nNo Top Node " : "\nHas Top Node")+
        (bottomNode == null ? "\nNo Bottom Node " : "\nHas Bottom Node")+ 
        (sideNode == null ? "\nNo Side Node " : "\nHas Side Node");*/
    }
    
}
