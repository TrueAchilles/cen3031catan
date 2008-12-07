/**
Puts into play the development card that the current player used
*@param dType Type Int, which has a unique development Card Associated with it.  Ex: Knight has dType of 4
*/

package settlers.game.events;

import settlers.game.elements.*;

public class DevelopmentEvent extends Event
{
    //The development Card that was put into play.
    public int dType;

    //Sets Event Name
    //Sets DevelopmentType as an int
    public DevelopmentEvent(String e, int _s)
    {
        super(e);
        dType = _s;
    }
    
    
    public int getDType() 
    {
        return dType;
    }
}
