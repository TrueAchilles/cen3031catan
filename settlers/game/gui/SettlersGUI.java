package settlers.game.gui;

import settlers.game.*;

import java.awt.Point;
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

public class SettlersGUI extends javax.swing.JFrame
{

    {
        //Set Look & Feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public SettlersEvent event_manager;
    public JMenu gameMenu;
    public JCheckBoxMenuItem debug_rollaSeven;
    public JCheckBoxMenuItem debug_dontRollaSeven;
    public JMenuItem debug_giveMonopolyCard;
    public JMenuItem debug_giveRoadCard;
    public JMenuItem debug_giveYearCard;
    public JMenuItem debug_showMeTheMoney;
    public JMenuItem debug_quickStart;
    public JMenuItem debug_bigQuickStart;
    public JMenuItem debug_compQuickStart;
    public JMenu debug_menu;
    public JMenuItem connectLan;
    public JCheckBoxMenuItem hideRollBox;
    public JCheckBoxMenuItem hidePlayerInfo;
    public JMenuItem connectInternet;
    public JMenu connectTo;
    public JMenuItem editProfile;
    public JMenuItem makeProfile;
    public JSeparator sep4;
    public JMenuItem computerPlayer;
    public JMenuItem humanPlayer;
    public JMenu addPlayer;
    public JSeparator sep3;
    public JMenuItem exit;
    public JSeparator sep2;
    public JMenuItem saveBoard;
    public JMenu connection;
    public JMenuItem loadBoard;
    public JMenuItem remakeBoard;
    public JSeparator sep1;
    public JMenuItem loadGame;
    public JMenuItem saveGame;
    public JMenuItem newGame;
    public JMenu help;
    public JMenuItem howToPlay;
    public JMenuItem about;
    public JMenu frameSize;
    public JRadioButtonMenuItem standard;
    public JRadioButtonMenuItem smaller;
    public JMenu player;
    public JMenuBar settlersMenu;

    public SettlersGUI()
    {
        super();
        initGUI();
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
                        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
                        newGame.addActionListener(ContainerGUI.gameButtons);
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
                        remakeBoard.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        loadBoard = new JMenuItem();
                        gameMenu.add(loadBoard);
                        loadBoard.setText("Load Board");
                        loadBoard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
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
                        hideRollBox.setState(false);
                        hideRollBox.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        hidePlayerInfo = new JCheckBoxMenuItem();
                        gameMenu.add(hidePlayerInfo);
                        hidePlayerInfo.setText("Hide Player Info");
                        hidePlayerInfo.setState(false);
                        hidePlayerInfo.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        frameSize = new JMenu("Frame Size");
                        gameMenu.add(frameSize);
                    }
                    {
                        standard = new JRadioButtonMenuItem("800 x 800 (standard)", true);
                        frameSize.add(standard);
                        standard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
                        standard.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        smaller = new JRadioButtonMenuItem("800 x 600 (smaller)", false);
                        frameSize.add(smaller);
                        smaller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                        smaller.addActionListener(ContainerGUI.gameButtons);
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
                        exit.addActionListener(ContainerGUI.gameButtons);
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
                            humanPlayer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
                            standard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
                            humanPlayer.addActionListener(ContainerGUI.gameButtons);
                        }
                        {
                            computerPlayer = new JMenuItem();
                            addPlayer.add(computerPlayer);
                            computerPlayer.setText("Add Computer Player");
                            computerPlayer.addActionListener(ContainerGUI.gameButtons);
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
                        makeProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
                        makeProfile.setEnabled(false);
                    }
                    {
                        editProfile = new JMenuItem();
                        player.add(editProfile);
                        editProfile.setText("Edit Profile");
                        editProfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
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
                {
                    help = new JMenu("Help");
                    settlersMenu.add(help);
                    help.setEnabled(true);
                    {
                        {
                            howToPlay = new JMenuItem();
                            help.add(howToPlay);
                            howToPlay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.KEY_LOCATION_UNKNOWN));
                            howToPlay.addActionListener(ContainerGUI.gameButtons);
                            howToPlay.setText("How to Play Settlers of Catan");
                        }
                        {
                            about = new JMenuItem();
                            help.add(about);
                            about.addActionListener(ContainerGUI.gameButtons);
                            about.setText("About Java Settlers of Catan");
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
                        debug_quickStart.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_compQuickStart = new JMenuItem();
                        debug_menu.add(debug_compQuickStart);
                        debug_compQuickStart.setText("Computer Quick Start");
                        debug_compQuickStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
                        debug_compQuickStart.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_bigQuickStart = new JMenuItem();
                        debug_menu.add(debug_bigQuickStart);
                        debug_bigQuickStart.setText("Big Quick Start");
                        debug_bigQuickStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
                        debug_bigQuickStart.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_showMeTheMoney = new JMenuItem();
                        debug_menu.add(debug_showMeTheMoney);
                        debug_showMeTheMoney.setText("Show Me The Money");
                        debug_showMeTheMoney.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.SHIFT_MASK));
                        debug_showMeTheMoney.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_rollaSeven = new JCheckBoxMenuItem();
                        debug_menu.add(debug_rollaSeven);
                        debug_rollaSeven.setText("Only Roll a Seven");
                        debug_rollaSeven.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.SHIFT_MASK));
                        debug_rollaSeven.setState(false);
                        debug_rollaSeven.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_dontRollaSeven = new JCheckBoxMenuItem();
                        debug_menu.add(debug_dontRollaSeven);
                        debug_dontRollaSeven.setText("Never Roll a Seven");
                        debug_dontRollaSeven.setState(false);
                        debug_dontRollaSeven.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.SHIFT_MASK));
                        debug_dontRollaSeven.addActionListener(ContainerGUI.gameButtons);
                    }                    
                    {
                        debug_giveMonopolyCard = new JMenuItem();
                        debug_menu.add(debug_giveMonopolyCard);
                        debug_giveMonopolyCard.setText("Give Monopoly Card");
                        debug_giveMonopolyCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK));
                        debug_giveMonopolyCard.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_giveRoadCard = new JMenuItem();
                        debug_menu.add(debug_giveRoadCard); 
                        debug_giveRoadCard.setText("Give Road Card");
                        debug_giveRoadCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.SHIFT_MASK));
                        debug_giveRoadCard.addActionListener(ContainerGUI.gameButtons);
                    }
                    {
                        debug_giveYearCard = new JMenuItem();
                        debug_menu.add(debug_giveYearCard);
                        debug_giveYearCard.setText("Give Year of Plenty Card");
                        debug_giveYearCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.SHIFT_MASK));
                        debug_giveYearCard.addActionListener(ContainerGUI.gameButtons);
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
                ContainerGUI.bottomPanel = new BottomPanel(this);
                getContentPane().add(ContainerGUI.bottomPanel, new AnchorConstraint(835, 1023, 1001, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                ContainerGUI.bottomPanel.setPreferredSize(new java.awt.Dimension(802, 131));
            }
            {
                ContainerGUI.mainBoard = new MainBoard(this);
                getContentPane().add(ContainerGUI.mainBoard, new AnchorConstraint(2, 1000, 834, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                ContainerGUI.mainBoard.setPreferredSize(new java.awt.Dimension(794, 598));
                ContainerGUI.mainBoard.setBackground(new java.awt.Color(255,255,255));
                ContainerGUI.mainBoard.setSize(794, 600);
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
        ContainerGUI.bottomPanel.getTabbedPanel().getGameText().append(txt);
    }

    
    public JMenuBar getMenu(){
        return settlersMenu;
    }

    public void showError(String string) {
        // TODO Auto-generated method stub
        ContainerGUI.bottomPanel.getTabbedPanel().getErrorPanel().append(string);
    }
    
    
    public void setSizeSmaller() {
        // TODO Auto-generated method stub
        //System.out.println("Sup1");
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.validate();
        ContainerGUI.mainBoard.resizeSmaller();

    }


    public void setSizeDefault() {
        // TODO Auto-generated method stub
        //System.out.println("Sup2");
        this.setPreferredSize(new Dimension(800, 850));
        this.pack();
        this.validate();
        ContainerGUI.mainBoard.resizeLarger();
        
    }


    public SettlersEvent getSettlersEvent()
    {
        return event_manager;
    }

    public MainBoard getMainBoard() {
        // TODO Auto-generated method stub
        return ContainerGUI.mainBoard;
    }    
    
    public BottomPanel getBottomPanel()
    {
        return ContainerGUI.bottomPanel;
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