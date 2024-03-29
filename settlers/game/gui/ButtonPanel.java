package settlers.game.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import settlers.game.*;
import settlers.game.events.*;
import settlers.game.elements.*;

public class ButtonPanel extends javax.swing.JPanel
{
    private GameButtons gameButtons;
    
    private JPanel openingButtonPanel;
    private JLabel jLabel1;
    private JLabel roll_label;
    public JButton roll_next;
    public JButton roll_roll;
    public JButton roll_thief;
    private JLabel introLabel;
    private JPanel Intro;
    public JButton build_play;
    public JButton build_dev;
    public JButton build_next;
    public JButton build_road;
    public JButton build_settlement;
    public JButton build_city;
    private JLabel jLabel2;
    public JButton trade_player;
    public JButton trade_next;
    public JButton trade_port;
    public JButton trade_bank;
    private JLabel openingLabel;
    private JPanel buildButtonPanel;
    private JPanel tradeButtonPanel;
    private JPanel rollButtonPanel;

    private BottomPanel parent;
    private SettlersEvent event_manager;
    
    public static CardLayout thisLayout;
    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    
    public ButtonPanel()
    {
        super();
        parent = null;
        initGUI();
    }
    
    public ButtonPanel(BottomPanel _parent, SettlersEvent _event) {
        super();
        parent = _parent;
        initGUI();
    }
    
    private void initGUI() {
        try {
            gameButtons = ContainerGUI.gameButtons;
            
            thisLayout = new CardLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(350, 150));
            this.setSize(350, 150);
            this.setBackground(new java.awt.Color(255,255,255));
            this.setBorder(new LineBorder(new java.awt.Color(0,0,0), 3, false));
            {
                Intro = new JPanel();
                BorderLayout IntroLayout = new BorderLayout();
                Intro.setLayout(IntroLayout);
                this.add(Intro, "INTRO");
                Intro.setBackground(new java.awt.Color(255,255,255));
                {
                    introLabel = new JLabel();
                    BorderLayout introLabelLayout = new BorderLayout();
                    introLabel.setLayout(null);
                    Intro.add(introLabel, BorderLayout.CENTER);
                    introLabel.setText("A Team EDSBS Production");
                    introLabel.setFont(new java.awt.Font("Segoe UI",2,16));
                    introLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    introLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                    introLabel.setOpaque(true);
                    introLabel.setBackground(new java.awt.Color(255,255,255));
                }
            }
            {
                openingButtonPanel = new JPanel();
                BorderLayout openingPanelLayout = new BorderLayout();
                this.add(openingButtonPanel, "OPENING");
                openingButtonPanel.setLayout(openingPanelLayout);
                openingButtonPanel.setPreferredSize(new java.awt.Dimension(350, 150));
                openingButtonPanel.setSize(350, 150);
                openingButtonPanel.setBackground(new java.awt.Color(255,255,255));
                {
                    openingLabel = new JLabel();
                    openingButtonPanel.add(openingLabel, BorderLayout.CENTER);
                    openingLabel.setText("OPENING TURN");
                    openingLabel.setPreferredSize(new java.awt.Dimension(139, 143));
                    openingLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    openingLabel.setHorizontalTextPosition(SwingConstants.CENTER);
                    openingLabel.setFont(new java.awt.Font("Tahoma",0,16));
                }
            }
            {
                rollButtonPanel = new JPanel();
                AnchorLayout rollButtonPanelLayout = new AnchorLayout();
                this.add(rollButtonPanel, "ROLL");
                rollButtonPanel.setLayout(rollButtonPanelLayout);
                rollButtonPanel.setBackground(new java.awt.Color(255,255,255));
                {
                    roll_label = new JLabel();
                    rollButtonPanel.add(roll_label, new AnchorConstraint(416, 295, 523, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                    roll_label.setText("ROLL");
                    roll_label.setPreferredSize(new java.awt.Dimension(103, 16));
                    roll_label.setHorizontalAlignment(SwingConstants.CENTER);
                    roll_label.setHorizontalTextPosition(SwingConstants.CENTER);
                }
                {
                    roll_next = new JButton();
                    rollButtonPanel.add(roll_next, new AnchorConstraint(336, 0, 636, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    roll_next.setText("NEXT");
                    roll_next.setFont(new java.awt.Font("Segoe UI",0,10));
                    roll_next.setToolTipText("Move to the trade phase.");
                    roll_next.setPreferredSize(new java.awt.Dimension(86, 45));
                    roll_next.addActionListener(gameButtons);
                    roll_next.setEnabled(false);

                }
                {
                    roll_thief = new JButton();
                    rollButtonPanel.add(roll_thief, new AnchorConstraint(336, 755, 636, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    roll_thief.setText("KNIGHT");
                    roll_thief.setFont(new java.awt.Font("Segoe UI",0,10));
                    roll_thief.setToolTipText("Move the thief.");
                    roll_thief.setPreferredSize(new java.awt.Dimension(78, 45));
                    roll_thief.setEnabled(false);
                    roll_thief.addActionListener(gameButtons);
                }
                {
                    roll_roll = new JButton();
                    rollButtonPanel.add(roll_roll, new AnchorConstraint(336, 532, 636, 295, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    roll_roll.setText("ROLL");
                    roll_roll.setFont(new java.awt.Font("Segoe UI",0,10));
                    roll_roll.setToolTipText("Roll the dice.");
                    roll_roll.setPreferredSize(new java.awt.Dimension(83, 45));
                    roll_roll.setEnabled(true);
                    roll_roll.addActionListener(gameButtons);
                }
            }
            {
                tradeButtonPanel = new JPanel();
                AnchorLayout tradeButtonPanelLayout = new AnchorLayout();
                this.add(tradeButtonPanel, "TRADE");
                tradeButtonPanel.setLayout(tradeButtonPanelLayout);
                tradeButtonPanel.setBackground(new java.awt.Color(255,255,255));
                {
                    jLabel1 = new JLabel();
                    tradeButtonPanel.add(jLabel1, new AnchorConstraint(383, 295, 490, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                    jLabel1.setText("TRADE");
                    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
                    jLabel1.setPreferredSize(new java.awt.Dimension(103, 16));
                }
                {
                    trade_port = new JButton();
                    tradeButtonPanel.add(trade_port, new AnchorConstraint(303, 755, 603, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    trade_port.setText("PORT");
                    trade_port.setFont(new java.awt.Font("Segoe UI",0,10));
                    trade_port.setToolTipText("Trade with a port.");
                    trade_port.setPreferredSize(new java.awt.Dimension(78,45));
                    trade_port.setEnabled(true);
                    trade_port.addActionListener(gameButtons);
                }
                {
                    trade_bank = new JButton();
                    tradeButtonPanel.add(trade_bank, new AnchorConstraint(303, 0, 603, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    trade_bank.setText("BANK");
                    trade_bank.setFont(new java.awt.Font("Segoe UI",0,10));
                    trade_bank.setToolTipText("Trade with the bank.");
                    trade_bank.setPreferredSize(new java.awt.Dimension(86,45));
                    trade_bank.setEnabled(true);
                    trade_bank.addActionListener(gameButtons);
                }
                {
                    trade_next = new JButton();
                    tradeButtonPanel.add(trade_next, new AnchorConstraint(610, 764, 910, 527, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    trade_next.setText("NEXT");
                    trade_next.setFont(new java.awt.Font("Segoe UI",0,10));
                    trade_next.setToolTipText("Move on to the build phase.");
                    trade_next.setPreferredSize(new java.awt.Dimension(83, 45));
                    trade_next.addActionListener(gameButtons);
                }
                {
                    trade_player = new JButton();
                    tradeButtonPanel.add(trade_player, new AnchorConstraint(303, 527, 603, 290, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    trade_player.setText("PLAYER");
                    trade_player.setFont(new java.awt.Font("Segoe UI",0,10));
                    trade_player.setToolTipText("Trade with another player.");
                    trade_player.setPreferredSize(new java.awt.Dimension(83, 45));
                    trade_player.setEnabled(true);
                    trade_player.addActionListener(gameButtons);
                }
            }
            {
                buildButtonPanel = new JPanel();
                AnchorLayout buildButtonPanelLayout = new AnchorLayout();
                buildButtonPanel.setLayout(buildButtonPanelLayout);
                this.add(buildButtonPanel, "BUILD");
                buildButtonPanel.setBackground(new java.awt.Color(255,255,255));
                {
                    jLabel2 = new JLabel();
                    buildButtonPanel.add(jLabel2, new AnchorConstraint(410, 295, 516, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
                    jLabel2.setText("BUILD");
                    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
                    jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
                    jLabel2.setPreferredSize(new java.awt.Dimension(103, 16));
                }
                {
                    build_city = new JButton();
                    buildButtonPanel.add(build_city, new AnchorConstraint(330, 0, 630, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_city.setText("CITY");
                    build_city.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_city.setToolTipText("Make a city out of an existing settlement");
                    build_city.setPreferredSize(new java.awt.Dimension(86, 45));
                    build_city.addActionListener(gameButtons);

                    //build_city.setEnabled(false);
                }
                {
                    build_settlement = new JButton();
                    buildButtonPanel.add(build_settlement, new AnchorConstraint(330, 755, 630, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_settlement.setText("SETTLEMENT");
                    build_settlement.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_settlement.setToolTipText("Build a settlement");
                    build_settlement.setPreferredSize(new java.awt.Dimension(78, 45));
                    build_settlement.addActionListener(gameButtons);
                    //build_settlement.setEnabled(false);
                }
                {
                    build_road = new JButton();
                    buildButtonPanel.add(build_road, new AnchorConstraint(330, 527, 630, 290, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_road.setText("ROAD");
                    build_road.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_road.setToolTipText("Build a road");
                    build_road.setPreferredSize(new java.awt.Dimension(83, 45));
                    build_road.addActionListener(gameButtons);
                    //build_road.setEnabled(false);
                }
                {
                    build_next = new JButton();
                    buildButtonPanel.add(build_next, new AnchorConstraint(663, 0, 963, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_next.setText("NEXT");
                    build_next.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_next.setToolTipText("End your turn");
                    build_next.setPreferredSize(new java.awt.Dimension(86, 45));
                    build_next.addActionListener(gameButtons);
                }
                {
                    build_dev = new JButton();
                    buildButtonPanel.add(build_dev, new AnchorConstraint(663, 755, 963, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_dev.setText("DEV CARD");
                    build_dev.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_dev.setToolTipText("Draw a development card");
                    build_dev.setPreferredSize(new java.awt.Dimension(78, 45));
                    build_dev.addActionListener(gameButtons);
                }
                {
                    build_play = new JButton();
                    buildButtonPanel.add(build_play, new AnchorConstraint(663, 527, 963, 290, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                    build_play.setText("PLAY DEV");
                    build_play.setFont(new java.awt.Font("Segoe UI",0,10));
                    build_play.setToolTipText("Play a development card");
                    build_play.setPreferredSize(new java.awt.Dimension(83, 45));
                    build_play.setEnabled(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }            
        //build_city.setEnabled(false);
        
    }

    public void startNewGame()
    {
        thisLayout.show(this, "OPENING");
    }
    
    public void setEvent(SettlersEvent _event)
    {
        event_manager = _event;
    }
    
    public void startNewTurn()
    {
        thisLayout.show(this, "ROLL");
    }

    
    public void switchPanel(String _pane)
    {
        thisLayout.show(this, _pane);
    }

}
