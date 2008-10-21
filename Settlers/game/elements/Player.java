//contains all the data for a given player

package settlers.game.elements;

import java.awt.Color;
import settlers.game.*;

public class Player
{
	private static int totalNum = 0;
	private Color playerColor;
	private int id = 0;
	String name = null;
	private int resource[] = { 0, 0, 0, 0, 0, 0 }; 
	
	public Player(String _name, Color _color)
	{
		name = _name;
		id = ++totalNum;
		playerColor = _color;
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
    
    //public void add()
	
}
