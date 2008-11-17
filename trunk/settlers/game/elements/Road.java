package settlers.game.elements;
import settlers.game.GameState;

public class Road
{

    Settlement s1;
    Settlement s2;

    Player owner;
    
    //defines whether the road has been built or not
    boolean hasRoad;
    
    //defines whether the road can be built or not
    boolean isBuildable;
    
    public Road (Settlement set1, Settlement set2, Player _owner)
    {
        s1 = set1;
        s2 = set2;
        hasRoad=false;
        owner = _owner;
    }

    public Settlement getS1()
    {
        return s1;
    }
    
    public Settlement getS2()
    {
        return s2;
    }
    
    public Settlement getOtherSettlement(Settlement s)
    {
        if (s == s1)
            return s2;
        else if (s == s2)
            return s1;
        return null;
    }
    
    public boolean hasRoad() {
        return hasRoad;
    }
    
    public boolean canBuildRoad(Player p)
    {
        if (hasRoad)
            return false;
        if (s1 != null && (p == s1.getOwner() || s1.checkRoadExtension(p) ) )
            return true;
        else if (s2 != null && (p == s2.getOwner() || s2.checkRoadExtension(p) ) )
            return true;
        else
            return false;
    }
    
    public boolean canBuildRoad()
    {
        return canBuildRoad(GameState.getCurPlayer());
    }
    
    public boolean buildRoad(Player p)
    {
        if (canBuildRoad(p)) {
            owner = p;
            hasRoad = true;
            p.addRoad(this);
            return true;
        }
        return false;
    }
    public boolean buildRoad()
    {
        return buildRoad(GameState.getCurPlayer());
    }
    
    public Player getOwner()
    {
        return owner;
    }
    
    public String toString()
    {
        return "Road("+s1.toString() + "," + s2.toString()+")";
    }
}