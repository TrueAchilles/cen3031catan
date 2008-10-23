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
    
    public Resource (int resourceNumber, int resourceType,
                        Settlement southWest, Settlement west, Settlement northWest, 
                        Settlement northEast, Settlement east, Settlement southEast) {
        this.southWest = southWest;
        this.west = west;
        this.northWest = northWest;
        this.northEast = northEast;
        this.east = east;
        this.southEast = southEast;
        this.resourceType = resourceType;
        this.resourceNumber = resourceNumber;
    }
    
    public Resource setNext (int resourceNumber, int resourceType,
                        Settlement southWest, Settlement west, Settlement northWest, 
                        Settlement northEast, Settlement east, Settlement southEast) {
        if (next == null)
            return next = new Resource(resourceNumber, resourceType, southWest, west, northWest, northEast, east, southEast);
        else
            return next.setNext(resourceNumber, resourceType, southWest, west, northWest, northEast, east, southEast);
    }
    
    public void giveResources()
    {
        if (hasTheif == 0)
        {
            southWest.giveResources(resourceType);
            west.giveResources(resourceType);
            northWest.giveResources(resourceType);
            northEast.giveResources(resourceType);
            east.giveResources(resourceType);
            southEast.giveResources(resourceType);
        }
        if (next != null)
            next.giveResources();
    }
    
    public int getResourceType()
    {
        return resourceType;
    }
    
    public int hasTheif()
    {
        return hasTheif;
    }
    
    public int getResourceNumber()
    {
        return resourceNumber;
    }
    
    
    
}
    