//a derivative of Event, allowing for a Player to be part of the event

package settlers.game.events;

import settlers.game.elements.*;

public class PlayerEvent extends Event
{
    public Player player;
    public Object object;

    public PlayerEvent(String e, Player p)
    {
        super(e);
        player = p;
    }
    
    public PlayerEvent(String e, Player p, Object o)
    {
        super(e);
        player = p;
        object = o;
    }
}
