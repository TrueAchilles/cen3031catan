package settlers.game.elements;

public class Road
{

	Settlement s1;
	Settlement s2;
	Player owner;
 
	//defines who owns this road
	//commented out because of lack of player class
	//Player owner;
	
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
	
	public boolean hasRoad()
	{
		return hasRoad;
	}
	
	//proper method heading that should be commmented out once player class is implemented
	//public void build(Player player)
	public void build(Settlement set1, Settlement set2)
	{

		owner = settlers.game.GameState.getCurPlayer();
		hasRoad = true;
		
	}
	
	public Player getOwner()
	{
		return owner;
	}
}