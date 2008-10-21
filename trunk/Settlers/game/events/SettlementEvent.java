//a derivative of Event, allowing for a Player to be part of the event

package settlers.game.events;

import settlers.game.elements.*;

public class SettlementEvent extends Event
{
	public Settlement settlement, settlement2;

	public SettlementEvent(String e, Settlement _s)
	{
		super(e);
		settlement = _s;
	}
    
    public SettlementEvent(String e, Settlement _s, Settlement _s2)
	{
		super(e);
		settlement = _s;
        settlement2 = _s2;
	}
}
