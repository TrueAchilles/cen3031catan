//a derivative of Event, allowing for a Player to be part of the event

package settlers.game.events;

import settlers.game.elements.*;

public class PlayerEvent extends Event
{
    public Player player;

    public PlayerEvent(String e, Player p)
    {
        super(e);
        player = p;
    }
}
