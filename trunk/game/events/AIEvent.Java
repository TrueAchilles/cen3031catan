package settlers.game.events;

import settlers.game.elements.*;

public class AIEvent extends Event
{
    public AIVeryEasy player;

    public AIEvent(String e, Player p)
    {
        super(e);
        player = p;
    }
}