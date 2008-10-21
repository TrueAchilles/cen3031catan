package settlers.game.gui;

import settlers.game.*;
import settlers.game.elements.Tile;
import settlers.game.logic.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class SettlersGUI extends javax.swing.JFrame implements ActionListener {

    {
        //Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private SettlersEvent event_manager;
    
    private MainBoard mainBoard;
    private JMenu gameMenu;
    private JMenuItem connectLan;
    private JCheckBoxMenuItem hideRollBox;
    private JMenuItem connectInternet;
    private JMenu connectTo;
    private JMenuItem editProfile;
    private JMenuItem makeProfile;
    private JSeparator sep4;
    private JMenuItem computerPlayer;
    private JMenuItem humanPlayer;
    private JMenu addPlayer;
    private JSeparator sep3;
    private JMenuItem exit;
    private JSeparator sep2;
    private JMenuItem saveBoard;
    private JMenu connection;
    private JMenuItem loadBoard;
    private JMenuItem remakeBoard;
    private JSeparator sep1;
    private JMenuItem loadGame;
    private JMenuItem saveGame;
    private JMenuItem newGame;
    private JMenu player;
    private JMenuBar settlersMenu;
    private BottomPanel bottomPanel;
    
    private SettlersController controller=null;
    
    public SettlersGUI()
    {
        super();
        controller = null;
        initGUI();
        event_manager = new SettlersEvent(this);
    }
    
    public SettlersGUI(SettlersController _controller) {
        super();
        controller = _controller;    
        initGUI();
        event_manager = new SettlersEvent(this);    
    }
    
    private void initGUI() {
        try {
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setPreferredSize(new java.awt.Dimension(800, 850));
            this.setTitle("Settlers of Catan");
            AnchorLayout thisLayout = new AnchorLayout();
            getContentPane().setLayout(thisLayout);
            this.setMinimumSize(new java.awt.Dimension(800, 850));
            {
                settlersMenu = new JMenuBar();
                setJMenuBar(settlersMenu);
                {
                    gameMenu = new JMenu();
                    settlersMenu.add(gameMenu);
                    gameMenu.setText("Game");
                    {
                        newGame = new JMenuItem();
                        gameMenu.add(newGame);
                        newGame.setText("New Game");
                        newGame.addActionListener(this);
                    }
                    {
                        saveGame = new JMenuItem();
                        gameMenu.add(saveGame);
                        saveGame.setText("Save Game");
                        saveGame.setEnabled(false);
                    }
                    {
                        loadGame = new JMenuItem();
                        gameMenu.add(loadGame);
                        loadGame.setText("Load Game");
                        loadGame.setEnabled(false);
                    }
                    {
                        sep1 = new JSeparator();
                        gameMenu.add(sep1);
                    }
                    {
                        remakeBoard = new JMenuItem();
                        gameMenu.add(remakeBoard);
                        remakeBoard.setText("Re-make Board");
                        remakeBoard.addActionListener(this);
                    }
                    {
                        loadBoard = new JMenuItem();
                        gameMenu.add(loadBoard);
                        loadBoard.setText("Load Board");
                        loadBoard.setEnabled(false);
                    }
                    {
                        saveBoard = new JMenuItem();
                        gameMenu.add(saveBoard);
                        saveBoard.setText("Save Board");
                        saveBoard.setEnabled(false);
                    }
                    {
                        sep2 = new JSeparator();
                        gameMenu.add(sep2);
                    }
                    {
                        hideRollBox = new JCheckBoxMenuItem();
                        gameMenu.add(hideRollBox);
                        hideRollBox.setText("Hide Roll Box");
                        hideRollBox.setState(true);
                        hideRollBox.addActionListener(this);
                    }
                    {
                        sep3 = new JSeparator();
                        gameMenu.add(sep3);
                    }
                    {
                        exit = new JMenuItem();
                        gameMenu.add(exit);
                        exit.setText("Exit");
                        exit.addActionListener(this);
                    }
                }
                {
                    player = new JMenu();
                    settlersMenu.add(player);
                    player.setText("Player");
                    {
                        addPlayer = new JMenu();
                        player.add(addPlayer);
                        addPlayer.setText("Add Player");
                        {
                            humanPlayer = new JMenuItem();
                            addPlayer.add(humanPlayer);
                            humanPlayer.setText("Add Human Player");
                            humanPlayer.addActionListener(this);
                        }
                        {
                            computerPlayer = new JMenuItem();
                            addPlayer.add(computerPlayer);
                            computerPlayer.setText("Add Computer Player");
                            computerPlayer.setEnabled(false);
                        }
                    }
                    {
                        sep4 = new JSeparator();
                        player.add(sep4);
                    }
                    {
                        makeProfile = new JMenuItem();
                        player.add(makeProfile);
                        makeProfile.setText("Make Profile");
                        makeProfile.setEnabled(false);
                    }
                    {
                        editProfile = new JMenuItem();
                        player.add(editProfile);
                        editProfile.setText("Edit Profile");
                        editProfile.setEnabled(false);
                    }
                }
                {
                    connection = new JMenu();
                    settlersMenu.add(connection);
                    connection.setText("Connection");
                    connection.setEnabled(false);
                    {
                        connectTo = new JMenu();
                        connection.add(connectTo);
                        connectTo.setText("Connect To...");
                        {
                            connectLan = new JMenuItem();
                            connectTo.add(connectLan);
                            connectLan.setText("LAN");
                        }
                        {
                            connectInternet = new JMenuItem();
                            connectTo.add(connectInternet);
                            connectInternet.setText("Internet");
                        }
                    }
                }
            }
            {
                bottomPanel = new BottomPanel(this);
                getContentPane().add(bottomPanel, new AnchorConstraint(835, 1023, 1001, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                bottomPanel.setPreferredSize(new java.awt.Dimension(802, 131));
            }
            {
                mainBoard = new MainBoard(this);
                getContentPane().add(mainBoard, new AnchorConstraint(2, 1000, 834, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                mainBoard.setPreferredSize(new java.awt.Dimension(794, 598));
                mainBoard.setBackground(new java.awt.Color(255,255,255));
                mainBoard.setSize(794, 600);
            }
            pack();
            this.setSize(800, 850);
            this.setVisible(true);
            this.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String txt){
        this.bottomPanel.getTabbedPanel().getGameText().append(txt);
    }
    
    public void initialize(Tile[] tiles){
        this.mainBoard.getGameBoard().hideBox(false);
    }
    
    public SettlersController getController(){
        return controller;
    }
    
    public JMenuBar getMenu(){
        return settlersMenu;
    }
    
    public void setAction(byte action){
        this.mainBoard.getGameBoard().setAction(action);
    }

    public void showError(String string) {
        // TODO Auto-generated method stub
        this.bottomPanel.getTabbedPanel().getErrorPanel().append(string);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        if(evt.getSource() == this.exit)
        {
            System.exit(0);
        }
        if(evt.getSource() == this.newGame)
        {
            event_manager.startNewGame();
        }
        if(evt.getSource() == this.remakeBoard)
        {
            event_manager.remakeBoard();
        }
        if(evt.getSource() == this.hideRollBox)
        {
            mainBoard.getGameBoard().hideBox(hideRollBox.isSelected());
        }
        if(evt.getSource() == this.humanPlayer)
        {
            event_manager.addPlayer();
        }
    }
    
    public SettlersEvent getSettlersEvent()
    {
        return event_manager;
    }

    public MainBoard getMainBoard() {
        // TODO Auto-generated method stub
        return mainBoard;
    }    
    
    public BottomPanel getBottomPanel()
    {
        return bottomPanel;
    }
}
