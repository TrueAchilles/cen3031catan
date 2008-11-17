package settlers.game.gui;

import settlers.game.*;
import settlers.game.logic.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBoxMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

public class SettlersGUI extends javax.swing.JFrame implements ActionListener
{

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
    private JMenuItem debug_rollaSeven;
    private JMenuItem debug_showMeTheMoney;
    private JMenuItem debug_quickStart;
    private JMenuItem debug_bigQuickStart;
    private JMenu debug_menu;
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
    private JMenu frameSize;
    private JRadioButtonMenuItem standard;
    private JRadioButtonMenuItem smaller;
    private JMenu player;
    private JMenuBar settlersMenu;
    private BottomPanel bottomPanel;

    public SettlersGUI()
    {
        super();
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
            //this.setMinimumSize(new java.awt.Dimension(800, 850));
            {
                settlersMenu = new JMenuBar();
                setJMenuBar(settlersMenu);
                {
                    gameMenu = new JMenu("Game");
                    settlersMenu.add(gameMenu);
                    gameMenu.setMnemonic(KeyEvent.VK_G);
                    {
                        newGame = new JMenuItem();
                        gameMenu.add(newGame);
                        newGame.setText("Reset Board");
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
                        remakeBoard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
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
                        frameSize = new JMenu("Frame Size");
                        gameMenu.add(frameSize);
                    }
                    {
                        standard = new JRadioButtonMenuItem("800 x 800 (standard)", true);
                        frameSize.add(standard);
                        standard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
                        standard.addActionListener(this);
                    }
                    {
                        smaller = new JRadioButtonMenuItem("800 x 600 (smaller)", false);
                        frameSize.add(smaller);
                        smaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                        smaller.addActionListener(this);
                    }
                    {
                        sep3 = new JSeparator();
                        gameMenu.add(sep3);
                    }
                    {
                        exit = new JMenuItem();
                        gameMenu.add(exit);
                        exit.setText("Exit");
                        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
                        exit.addActionListener(this);
                    }
                }
                {
                    player = new JMenu("Player");
                    player.setMnemonic(KeyEvent.VK_P);
                    settlersMenu.add(player);
                    {
                        addPlayer = new JMenu();
                        player.add(addPlayer);
                        addPlayer.setText("Add Player");
                        {
                            humanPlayer = new JMenuItem();
                            addPlayer.add(humanPlayer);
                            humanPlayer.setText("Add Human Player");
                            standard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
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
                    connection = new JMenu("Connection");
                    settlersMenu.add(connection);
                    connection.setMnemonic(KeyEvent.VK_C);
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
                if (GlobalVar.DEBUG_MODE)
                {
                    debug_menu = new JMenu("DEBUG");
                    debug_menu.setMnemonic(KeyEvent.VK_D);
                    settlersMenu.add(debug_menu);
                    debug_menu.setEnabled(true);
                    {
                        debug_quickStart = new JMenuItem();
                        debug_menu.add(debug_quickStart);
                        debug_quickStart.setText("Quick Start");
                        debug_quickStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
                        debug_quickStart.addActionListener(this);
                    }
                    {
                        debug_bigQuickStart = new JMenuItem();
                        debug_menu.add(debug_bigQuickStart);
                        debug_bigQuickStart.setText("Big Quick Start");
                        debug_bigQuickStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
                        debug_bigQuickStart.addActionListener(this);
                    }
                    {
                        debug_showMeTheMoney = new JMenuItem();
                        debug_menu.add(debug_showMeTheMoney);
                        debug_showMeTheMoney.setText("Show Me The Money");
                        debug_showMeTheMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
                        debug_showMeTheMoney.addActionListener(this);
                    }
                    {
                        debug_rollaSeven = new JMenuItem();
                        debug_menu.add(debug_rollaSeven);
                        debug_rollaSeven.setText("Roll a Seven");
                        debug_rollaSeven.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
                        debug_rollaSeven.addActionListener(this);
                    }
                        /*{
                            connectLan = new JMenuItem();
                            connectTo.add(connectLan);
                            connectLan.setText("LAN");
                        }
                        {
                            connectInternet = new JMenuItem();
                            connectTo.add(connectInternet);
                            connectInternet.setText("Internet");
                        }*/
                    
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
            this.pack();
            this.setSize(800, 850);
            this.setVisible(true);
            this.setResizable(false);
            
            this.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(String txt){
        this.bottomPanel.getTabbedPanel().getGameText().append(txt);
    }
    
    public void initialize(){
        this.mainBoard.getGameBoard().hideBox(false);
    }

    
    public JMenuBar getMenu(){
        return settlersMenu;
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
        if(evt.getSource() == this.debug_quickStart)
        {
            event_manager.quickStart(false);
        }
        if(evt.getSource() == this.debug_bigQuickStart)
        {
            event_manager.quickStart(true);
        }
        if(evt.getSource() == this.debug_showMeTheMoney)
        {
            event_manager.muchMoney();
        }
        
        if(evt.getSource() == this.standard)
        {
            this.smaller.setSelected(!standard.isSelected());
            if(standard.isSelected())
                this.setSizeDefault();
        }
        if(evt.getSource() == this.smaller)
        {
            this.standard.setSelected(!smaller.isSelected());
            if(smaller.isSelected())
                this.setSizeSmaller();
        }
    }
    
    private void setSizeSmaller() {
        // TODO Auto-generated method stub
        //System.out.println("Sup1");
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.validate();
        this.mainBoard.resizeSmaller();

    }


    private void setSizeDefault() {
        // TODO Auto-generated method stub
        //System.out.println("Sup2");
        this.setPreferredSize(new Dimension(800, 850));
        this.pack();
        this.validate();
        this.mainBoard.resizeLarger();
        
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

    public void toggleComponent(String component, boolean enabled)
    {
        if (component.equals("Player"))
        {
            player.setEnabled(enabled);
        }
    }


//    public void keyPressed(KeyEvent arg0) 
//    {
//        //The MenuItem keys
//        if(arg0.getKeyCode() == KeyEvent.VK_N && arg0.getModifiers() == KeyEvent.CTRL_MASK)
//            debug_quickStart.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_P && arg0.getModifiers() == KeyEvent.CTRL_MASK)
//            humanPlayer.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_X && arg0.getModifiers() == KeyEvent.CTRL_MASK)
//            exit.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_S && arg0.getModifiers() == KeyEvent.CTRL_MASK)
//            smaller.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_L && arg0.getModifiers() == KeyEvent.CTRL_MASK)
//            standard.doClick();
//        
//        //Menu keys
//        if(arg0.getKeyCode() == KeyEvent.VK_G && arg0.getModifiers() == KeyEvent.ALT_MASK)
//            gameMenu.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_D && arg0.getModifiers() == KeyEvent.ALT_MASK)
//            debug_menu.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_C && arg0.getModifiers() == KeyEvent.ALT_MASK)
//            connection.doClick();
//        if(arg0.getKeyCode() == KeyEvent.VK_P && arg0.getModifiers() == KeyEvent.ALT_MASK)
//            player.doClick();
//    }
//
//
//    public void keyReleased(KeyEvent arg0) { }
//
//
//    public void keyTyped(KeyEvent arg0) { }
}