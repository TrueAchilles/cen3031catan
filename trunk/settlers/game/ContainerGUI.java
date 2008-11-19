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
    public static ButtonPanel buttonPanel;
    public static SettlersGUI settlersGUI;
    public static GameButtons gameButtons;
    public static RollBox rollBox;
    public static SettlersEvent settlersEvent;


    public ContainerGUI()
    {
        gameButtons = new GameButtons();
        settlersGUI = new SettlersGUI();
        settlersEvent = new SettlersEvent();
        buttonPanel = bottomPanel.getButtonPanel();
        rollBox = gameBoard.getRollBox();
        
        
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

