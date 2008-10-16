package settlers.game.elements;

import settlers.game.GameState;

public class Resource
{

	private int xCord;
	private int yCord;
	
	Settlement southWest;
	Settlement west;
	Settlement northWest;
	Settlement northEast;
	Settlement east;
	Settlement southEast;
	
	private int hasTheif;
	
	private int resourceNumber;
	
	private int resourceType;
	
	private Resource next = null;
	
	public Resource (int resourceNumber, 
						Settlement southWest, Settlement west, Settlement northWest, 
						Settlement northEast, Settlement east, Settlement southEast) {
		this.southWest = southWest;
		this.west = west;
		this.northWest = northWest;
		this.northEast = northEast;
		this.east = east;
		this.southEast = southEast;
		this.resourceNumber = resourceNumber;
	}
}
	