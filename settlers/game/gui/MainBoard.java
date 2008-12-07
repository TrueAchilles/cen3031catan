package settlers.game.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import settlers.game.*;

@SuppressWarnings("serial")
public class MainBoard extends javax.swing.JPanel {
    
    private static JLabel statusBar;
    private static PlayerPanel playerPanel;
    
    private boolean playerPanel_created = false;

    public MainBoard()
    {
        super();
        initGUI();
    }
    
    public MainBoard(SettlersGUI _parent) 
    {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try 
        {
            this.setBackground(new java.awt.Color(255,255,255));
            this.setPreferredSize(new java.awt.Dimension(800, 600));
            this.setSize(800, 600);
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            this.setBackground(new java.awt.Color(255,255,255));
            ContainerGUI.gameBoard = new GameBoard(this);
            ContainerGUI.gameBoard.setPreferredSize(new java.awt.Dimension(802, 574));    //732, 574
            statusBar = new JLabel();
            this.add(statusBar, BorderLayout.NORTH);
            statusBar.setText("Status Bar");
            statusBar.setHorizontalAlignment(SwingConstants.CENTER);
            statusBar.setHorizontalTextPosition(SwingConstants.CENTER);
            statusBar.setFont(new java.awt.Font("Tahoma",0,14));
            statusBar.setPreferredSize(new java.awt.Dimension(409, 26));
            statusBar.setBorder(new LineBorder(new java.awt.Color(0,0,0), 2, false));
            this.add(ContainerGUI.gameBoard, BorderLayout.CENTER);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public GameBoard getGameBoard()
    {
        return ContainerGUI.gameBoard;
    }
    
    public RollBox getRollBox()
    {
        return ContainerGUI.gameBoard.getRollBox();
    }
    
    public PlayerPanel getPlayerPanel()
    {
        return playerPanel;
    }
    
    public static JLabel getStatusBar()
    {
        return statusBar;
    }
    
    public void makePlayerPanel()
    {
        playerPanel = new PlayerPanel();
        this.add(playerPanel, BorderLayout.WEST);
        this.playerPanel_created = true;
    }
    
    public boolean isPlayerPanel()
    {
        return this.playerPanel_created;
    }
    
    public void resizeLarger()
    {
        this.setPreferredSize(new Dimension(800, 600));
        ContainerGUI.gameBoard.resizeLarger();
        
    }

    public void resizeSmaller()
    {
        this.setPreferredSize(new Dimension(800, 400));
        ContainerGUI.gameBoard.resizeSmaller();

    }

}