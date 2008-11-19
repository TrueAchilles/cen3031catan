//the interface that a class wanting to listen to events must implement

package settlers.game.events;

public interface EventListener
{
    // called when an event you've registered is fired
    public boolean eventCalled(Event e);
}
