package settlers.game.elements;

import java.awt.*;
import java.util.*;
import settlers.game.*;
import settlers.game.events.*;
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
	int cityCounter = 0;
	
	//These are ArrayLists so that we can store Rectangle2D objects
	//in them. These objects will represent the roads/settlements/cities
	Settlement settlements[] = new Settlement[10];
	Road roads[] = new Road[50];
	Settlement cities[] = new Settlement[10];
	
	//Constructor which declares the Player values
	public AIVeryEasy(String _name, Color _color) 
	{
		super(_name, _color);
		super.isComputer = true;
	}
	
	//main controller for how the computer will play its turn 
	public void initSettlementPlacement()
	{
		int x = 1;
		int y = 1;
		boolean acted = false;
		int debug = 0;
		
		tempBuild = ContainerGUI.mainBoard.getGameBoard().vertex[x][y];
		Random nodeGen = new Random();
		
		while(acted == false)
		{
			y = nodeGen.nextInt(5)+1;
			x = (nodeGen.nextInt(10))+1;

			tempBuild = ContainerGUI.mainBoard.getGameBoard().vertex[x][y];
			
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
		GameState.diceHasBeenRolledDuringTurn = false;
		PlayerEvent n = new PlayerEvent("PLAYER_WISHESTO_ROLL", GameState.getCurPlayer());
        EventManager.callEvent(n);
	}
	
	public void rollToTrade()
	{
		GameState.diceHasBeenRolledDuringTurn = true;
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
		tempBuild = null;
		boolean acted = false;

		for(int y = 1; y <= 6; y++)
		{
			for(int x = 1; x <= 11; x++)
			{
				tempBuild = ContainerGUI.mainBoard.getGameBoard().vertex[x][y];
				
				if(tempBuild != null && tempBuild.canBuildSettlement(this))
				{
					System.out.println("settlementAttempt");
					System.out.println("Trying to build a comp settlement");
                    if(GameState.getCurPlayer().canBuySettlement())
                    {
                        System.out.println("Making the settlement...");
                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_SETTLEMENT);
                    }
		            if (GameState.getActionState() == GlobalVar.COMP_ACTION_ADD_SETTLEMENT) 
		            {
		            	System.out.println("building");
		                tempBuild.buildSettlement();
		                this.buildSettlement();		                
		                GameState.setActionState(-1);
		                settlements[settlementCounter++] = tempBuild;
						ContainerGUI.mainBoard.getGameBoard().repaint();
						acted = true;
		            }
				}
			}
		}
		while(acted == false)
		{
			if(this.canBuyRoad() == false)
			{
				break;
			}
			
			int breakCounter = 0;
			Random nodeGen = new Random();
			tempBuild = null;
			tempRoad = null;
			for(int y = 1; y <= 6; y++)
			{
				for(int x = 1; x <= 11; x++)
				{	
					tempBuild = ContainerGUI.mainBoard.getGameBoard().vertex[x][y];
					
					tempRoad = tempBuild.getBottomRoad();
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
					
					System.out.println("x-cord: " + x + " y-cord: " + y);
					
					if(tempRoad != null && tempRoad.canBuildRoad(this) == false)
					{
						breakCounter++;
					}
					
					if(tempRoad != null && tempRoad.canBuildRoad(this))
					{
						System.out.println("roadAttempt");
						System.out.println("Trying to build a comp road");
	                    if(GameState.getCurPlayer().canBuyRoad())
	                    {
	                        System.out.println("Making the road...");
	                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_ROAD);
	                    }
						if (GameState.getActionState() == GlobalVar.COMP_ACTION_ADD_ROAD)
						{
							System.out.println("building");
							tempRoad.buildRoad();
							this.buildRoad();
				            GameState.setActionState(-1);
							roads[roadCounter++] = tempRoad;
							ContainerGUI.mainBoard.getGameBoard().repaint();
							GameState.setActionState(-1);
							acted = true;
							System.out.println(roadCounter + " x-cord" + x + " y-cord" + y);
							break;
						}
					}
				}
				if(acted == true)
				{
					break;
				}
			}
			
			if(breakCounter == 200)
			{
				break;
			}
		}
		
		tempBuild = null;
		
		for(int y = 1; y <= 6; y++)
		{
			for(int x = 1; x <= 11; x++)
			{
				tempBuild = ContainerGUI.mainBoard.getGameBoard().vertex[x][y];
				
				if(tempBuild != null && tempBuild.canBuildCity(this))
				{
					System.out.println("cityAttempt");
					System.out.println("Trying to build a comp city");
                    if(GameState.getCurPlayer().canBuyCity())
                    {
                        System.out.println("Making the settlement...");
                        GameState.setActionState(GlobalVar.COMP_ACTION_ADD_CITY);
                    }
		            if (GameState.getActionState() == GlobalVar.COMP_ACTION_ADD_CITY) 
		            {
		            	System.out.println("building");
		                tempBuild.buildCity();
		                this.buildCity();		                
		                GameState.setActionState(-1);
		                cities[cityCounter++] = tempBuild;
						ContainerGUI.mainBoard.getGameBoard().repaint();
						acted = true;
		            }
				}
			}
		}
        
        PlayerEvent E = new PlayerEvent("PLAYER_BUILT_SETTLEMENT", GameState.getCurPlayer());
        EventManager.callEvent(E);
	}
}