//event manager!

package settlers.game.events;

import java.util.*;

public class EventManager
{
    private static Hashtable<String, LinkedList<EventListener>> events = new Hashtable<String, LinkedList<EventListener>>();
    
    //call this function to register an event!
    public static void registerEvent(String event, EventListener listener)
    {
        if (events.containsKey(event))
        {
            LinkedList<EventListener> listeners = events.get(event);
            listeners.add(listener);
        }
        else
        {
            LinkedList<EventListener> listeners = new LinkedList<EventListener>();
            listeners.add(listener);
            events.put(event, listeners);
        }
    }
    
    //call this function to fire an event!
    public static void callEvent(Event e)
    {
        String event = e.getEvent();
        
        if (events.containsKey(event))
        {
            LinkedList<EventListener> listeners = events.get(event);
            
            for (EventListener listener : listeners)
            {
                listener.eventCalled(e);
            }
        }
    }
}
