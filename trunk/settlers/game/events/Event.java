//standard event

package settlers.game.events;

public class Event
{
    private String event = "Default Event";
    
    public Event(String e)
    {
        event = e;
    }
    
    public String getEvent()
    {
        return event;
    }
}
