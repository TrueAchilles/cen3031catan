//contains all the data for a given player

package settlers.game.elements;

import java.awt.Color;

public class Player
{
	private static int totalNum = 0;
	private Color playerColor;
	private int id = 0;
	String name = null;
	
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
}