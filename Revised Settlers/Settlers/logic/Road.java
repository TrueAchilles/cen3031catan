package Settlers.logic;

public class Road
{
	//defines who owns this road
	//commented out because of lack of player class
	//Player owner;
	
	//defines whether the road has been built or not
	boolean hasRoad;
	
	//defines whether the road can be built or not
	boolean isBuildable;
	
	
	private boolean determineIsBuildable()
	{
		return hasRoad;
	}
	
	//proper method heading that should be commmented out once player class is implemented
	//public void build(Player player)
	public void build()
	{
		if (determineIsBuildable())
			hasRoad = true;
	}
}