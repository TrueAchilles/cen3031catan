package settlers.game;

import java.awt.*;
import settlers.game.gui.*;

/**
 * Contains all of the aliases for all of the values used throughout the game.
 */
public class ContainerGUI
{

    public MainBoard mainBoard = null;
    public GameBoard gameBoard = null;
    public BottomPanel bottomPanel = null;


    public ContainerGUI()
    {

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

