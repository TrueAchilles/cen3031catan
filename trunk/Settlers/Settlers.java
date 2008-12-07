// this class simply serves as the gateway to the rest of the game
package settlers;

import settlers.game.*;

class Settlers
{
    public static void main(String[] args)
    {
        settlers.game.GameState.initialize();
    }
}
