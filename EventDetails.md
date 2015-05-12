How to send and receive events:

### Receiving: ###
> First, you must import the events classes:
```
    import settlers.game.events.*;
```
> Next, your class must implement EventListener:
```
    class myClass implements EventListener
    {
        public void eventCalled(Event e)
        {
            System.out.println("Received event: " + e.getEvent());
        }
    } 
```
> Finally, you must register the events you want:
```
    public myClass
    {
        EventManager.registerEvent("EVENT_NAME", this);
    }
```
> And that's it!

### Calling: ###
> First, you must create the event object:
```
    Event e = new Event("EVENT_NAME");
```
> If you want to make an event that has arguments, you must make
> a new class in the 'events' folder that extends Event.
> See PlayerEvent.java for an example.  In its case, you'd do this:
```
    PlayerEvent e = new PlayerEvent("EVENT_NAME", playerObj);
```
> Then, you call the event!
```
    EventManager.callEvent(e);
```
> And you're done.


### Notes: ###
The Event class has only one method: getEvent().  It returns the string
used to create it.

EventManager has two methods: callEvent(Event) and registerEvent(String, EventListener).

I propose a standardized way of naming events:
```
    PART1_PART2_..._PARTN
    e.g. PLAYER_TURN_START
```
That way, code can do this:
```
    String[] parts = e.getEvent().split("_");
    if (parts[0].equals("PLAYER"))
        handlePlayerEvent(e);
```
And so on.