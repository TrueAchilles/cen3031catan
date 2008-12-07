package settlers.game.events;

import settlers.game.elements.*;

public class BuildEvent extends Event
{
    public int buildType;

    public BuildEvent(String e, int _b)
    {
        super(e);
        buildType = _b;
    }
}