package settlers.game.events;

import settlers.game.elements.*;

public class RoadEvent extends Event
{
    public Road road;

    public RoadEvent(String e, Road _s)
    {
        super(e);
        road = _s;
    }
}
