package settlers.game.elements;

import java.awt.*;
import java.util.*;
import settlers.game.*;
import settlers.game.events.Event;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.events.RoadEvent;
import settlers.game.events.SettlementEvent;
import settlers.game.gui.*;


public class AIVeryEasy extends Player
{
	boolean canBuildDevCard = false;
	boolean canBuildRoad = false;
	boolean canBuildSettlement = false;
	boolean canBuildCity = false;
	boolean canUseDevCard = false;
	
	float useDevelopmentChance;
	float useBuildRoadChance;
	float useBuildSettlementChance; 
	float useBuildCityChance;
	float useBuildDevelopmentCardChance; 
	float useTradeChance;
	
	Settlement tempBuild;
	Road tempRoad;
	
	int settlementCounter = 0;
	int roadCounter = 0;
	
	//These are ArrayLists so that we can store Rectangle2D objects
	//in them. These objects will represent the roads/settlements/cities
	Settlement settlements[] = new Settlement[10];
	Road roads[] = new Road[50];
	
	//Constructor which declares the Player values
	public AIVeryEasy(String _name, Color _color) 
	{
		super(_name, _color);
		super.isComputer = true;
	}
	
	//main controller for how the computer will play its turn 
	public void initSettlementPlacement()
	{
		int x = 0;
		int y = 0;
		boolean acted = false;
		int debug = 0;
		
		tempBuild = GameState.getGui().gui.getMainBoard().getGameBoard().vertex[x][y];
		Random nodeGen = new Random();
		
		while(acted == false)
		{
			y = nodeGen.nextInt(5)+1;
			x = (nodeGen.nextInt(10))+1;

			tempBuild = GameState.getGui().gui.getMainBoard().getGameBoard().vertex[x][y];
			
			if(tempBuild.canBuildSettlement() == true)
			{
				SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", tempBuild);
	            EventManager.callEvent(se);
				settlements[settlementCounter++] = tempBuild;
				
				acted = true;
			}
		}
	}
	
	public void initRoadPlacement()
	{
		Random nodeGen = new Random();
		tempRoad = tempBuild.getBottomRoad();
		boolean acted = false;
		while(acted == false)
		{
			int choice = nodeGen.nextInt(2);
			if(choice == 0)
			{
				tempRoad = tempBuild.getBottomRoad();
			}
			else if(choice == 1)
			{
				tempRoad = tempBuild.getSideRoad();
			}
			else if(choice == 2)
			{
				tempRoad = tempBuild.getTopRoad();
			}
			if(tempRoad != null && tempRoad.canBuildRoad())
			{
				RoadEvent se = new RoadEvent("PLAYER_INIT_ATTEMPT_ROAD", tempRoad);
				EventManager.callEvent(se);
				roads[roadCounter++] = tempRoad;
				acted = true;
			}
		}
	}
	
	public void startTurn()
	{
		PlayerEvent n = new PlayerEvent("PLAYER_ROLL_PHASE_BEGIN", GameState.getCurPlayer());
		EventManager.callEvent(n);
	}
	
	public void roll()
	{
        Event n = new Event("DICE_ROLLED");
        EventManager.callEvent(n);
	}
	
	public void rollToTrade()
	{
		PlayerEvent n = new PlayerEvent("PLAYER_TRADE_PHASE_BEGIN", GameState.getCurPlayer());
        EventManager.callEvent(n); 
	}
	
	public void tradeToBuild()
	{
		PlayerEvent E = new PlayerEvent("PLAYER_BUILD_PHASE_BEGIN", GameState.getCurPlayer());
        EventManager.callEvent(E);
	}
	
	public void turnEnd()
	{
		PlayerEvent n = new PlayerEvent("PLAYER_TURN_END", GameState.getCurPlayer());
        EventManager.callEvent(n);
	}
		
	public void actBuild() 
	{
		boolean acted = false;

		for(int y = 1; y <= 6; y++)
		{
			for(int x = 1; x <= 11; x++)
			{
				tempBuild = GameState.getGui().gui.getMainBoard().getGameBoard().vertex[x][y];
				
				if(tempBuild.canBuildSettlement() == true)
				{
					SettlementEvent se = new SettlementEvent("PLAYER_ATTEMPT_SETTLEMENT", tempBuild);
		            EventManager.callEvent(se);
					settlements[settlementCounter++] = tempBuild;
					
					acted = true;
				}
			}
		}
		if(acted == false)
		{
			Random nodeGen = new Random();
			boolean bottomFail = false;
			boolean sideFail = false;
			boolean topFail = false;
			
			for(int y = 1; y <= 6; y++)
			{
				for(int x = 1; x <= 11; x++)
				{
					bottomFail = false;
					sideFail = false;
					topFail = false;
					
					tempBuild = GameState.getGui().gui.getMainBoard().getGameBoard().vertex[x][y];
					
					if(tempBuild.canBuildSettlement() == true)
					{
						System.out.println("settlementAttempt");
						SettlementEvent se = new SettlementEvent("PLAYER_ATTEMPT_SETTLEMENT", tempBuild);
			            EventManager.callEvent(se);
						settlements[settlementCounter++] = tempBuild;
						
						acted = true;
					}
					tempRoad = tempBuild.getBottomRoad();
					int choice = nodeGen.nextInt(2);
					if(choice == 0)
					{
						if(tempBuild.getBottomRoad() != null)
						{
							tempRoad = tempBuild.getBottomRoad();
						}
						else
						{
							bottomFail = true;
						}
					}
					else if(choice == 1 || bottomFail == true)
					{
						if(tempBuild.getSideRoad() != null)
						{
							tempRoad = tempBuild.getSideRoad();
						}
						else
						{
							sideFail = true;
						}
					}
					else if(choice == 2 || (bottomFail == true && sideFail == true))
					{
						if(tempBuild.getTopRoad() != null)
						{
							tempRoad = tempBuild.getTopRoad();
						}
						else
						{
							topFail = true;
						}
					}
					
					if(topFail != true || sideFail != true || bottomFail != true)
					{
						System.out.println("roadAttempt");
						RoadEvent se = new RoadEvent("PLAYER_ATTEMPT_ROAD", tempRoad);
						EventManager.callEvent(se);
						roads[roadCounter++] = tempRoad;
						acted = true;
						break;
					}
				}
				if(acted = true)
				{
					break;
				}
			}
		}
	}
}