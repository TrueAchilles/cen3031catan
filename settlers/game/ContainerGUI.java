package settlers.game;

import java.awt.*;
import settlers.game.gui.*;

/**
 * Contains all of the aliases for all of the values used throughout the game.
 */
public class ContainerGUI
{

    public static MainBoard mainBoard;
    public static GameBoard gameBoard;
    public static BottomPanel bottomPanel;
    public static SettlersGUI settlersGUI;


    public ContainerGUI()
    {
        settlersGUI = new SettlersGUI();
        // by some insane cascading effects, the rest of the variables are set.

    }

    public void setGameBoard(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    public void setMainBoard(MainBoard mainBoard)
    {
        this.mainBoard = mainBoard;
    }

    public void setBottomPanel(BottomPanel bottomPanel)
    {
        this.bottomPanel = bottomPanel;
    }

    public void resetContainer()
    {
        mainBoard = null;
        gameBoard = null;
        bottomPanel = null;
    }

}

