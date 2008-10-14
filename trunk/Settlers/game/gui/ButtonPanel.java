package settlers.game.gui;
import java.awt.BorderLayout;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ButtonPanel extends javax.swing.JPanel implements ActionListener {
	private JPanel openingButtonPanel;
	private JLabel jLabel1;
	private JLabel roll_label;
	private JButton roll_next;
	private JButton roll_roll;
	private JButton roll_thief;
	private JLabel introLabel;
	private JPanel Intro;
	private JButton build_play;
	private JButton build_dev;
	private JButton build_next;
	private JButton build_road;
	private JButton build_settlement;
	private JButton build_city;
	private JLabel jLabel2;
	private JButton trade_player;
	private JButton trade_next;
	private JButton jButton2;
	private JButton trade_bank;
	private JButton opening_road;
	private JButton opening_next;
	private JButton opening_settlement;
	private JLabel openingLabel;
	private JPanel buildButtonPanel;
	private JPanel tradeButtonPanel;
	private JPanel rollButtonPanel;

	private BottomPanel parent;
	private SettlersEvent event_manager;
	
	private CardLayout thisLayout;
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
				AnchorLayout openingPanelLayout = new AnchorLayout();
				this.add(openingButtonPanel, "OPENING");
				openingButtonPanel.setLayout(openingPanelLayout);
				openingButtonPanel.setPreferredSize(new java.awt.Dimension(350, 150));
				openingButtonPanel.setSize(350, 150);
				openingButtonPanel.setBackground(new java.awt.Color(255,255,255));
				{
					openingLabel = new JLabel();
					openingButtonPanel.add(openingLabel, new AnchorConstraint(436, 295, 543, 0, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS));
					openingLabel.setText("OPENING");
					openingLabel.setPreferredSize(new java.awt.Dimension(103, 16));
					openingLabel.setHorizontalAlignment(SwingConstants.CENTER);
					openingLabel.setHorizontalTextPosition(SwingConstants.CENTER);
				}
				{
					opening_settlement = new JButton();
					openingButtonPanel.add(opening_settlement, new AnchorConstraint(310, 532, 610, 295, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					opening_settlement.setText("SETTLEMENT");
					opening_settlement.setPreferredSize(new java.awt.Dimension(83, 45));
					opening_settlement.setToolTipText("Build a settlement");
					opening_settlement.setFont(new java.awt.Font("Segoe UI",0,10));
					opening_settlement.setEnabled(false);
				}
				{
					opening_road = new JButton();
					openingButtonPanel.add(opening_road, new AnchorConstraint(310, 755, 610, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					opening_road.setText("ROAD");
					opening_road.setPreferredSize(new java.awt.Dimension(78, 45));
					opening_road.setToolTipText("Build a road");
					opening_road.setFont(new java.awt.Font("Segoe UI",0,10));
					opening_road.setEnabled(false);
				}
				{
					opening_next = new JButton();
					openingButtonPanel.add(opening_next, new AnchorConstraint(310, 0, 610, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					opening_next.setText("NEXT");
					opening_next.setPreferredSize(new java.awt.Dimension(86, 45));
					opening_next.setToolTipText("End your turn");
					opening_next.setFont(new java.awt.Font("Segoe UI",0,10));
					opening_next.addActionListener(this);
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
					roll_next.setToolTipText("Move to the trade phase");
					roll_next.setPreferredSize(new java.awt.Dimension(86, 45));
					roll_next.addActionListener(this);
				}
				{
					roll_thief = new JButton();
					rollButtonPanel.add(roll_thief, new AnchorConstraint(336, 755, 636, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					roll_thief.setText("THIEF");
					roll_thief.setFont(new java.awt.Font("Segoe UI",0,10));
					roll_thief.setToolTipText("Move the thief");
					roll_thief.setPreferredSize(new java.awt.Dimension(78, 45));
					roll_thief.setEnabled(false);
				}
				{
					roll_roll = new JButton();
					rollButtonPanel.add(roll_roll, new AnchorConstraint(336, 532, 636, 295, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					roll_roll.setText("ROLL");
					roll_roll.setFont(new java.awt.Font("Segoe UI",0,10));
					roll_roll.setToolTipText("Roll the dice");
					roll_roll.setPreferredSize(new java.awt.Dimension(83, 45));
					roll_roll.setEnabled(false);
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
					jButton2 = new JButton();
					tradeButtonPanel.add(jButton2, new AnchorConstraint(303, 755, 603, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					jButton2.setText("TRADE: PORT");
					jButton2.setFont(new java.awt.Font("Segoe UI",0,10));
					jButton2.setToolTipText("Trade with a port");
					jButton2.setPreferredSize(new java.awt.Dimension(78,45));
					jButton2.setEnabled(false);
				}
				{
					trade_bank = new JButton();
					tradeButtonPanel.add(trade_bank, new AnchorConstraint(303, 0, 603, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					trade_bank.setText("TRADE: BANK");
					trade_bank.setFont(new java.awt.Font("Segoe UI",0,10));
					trade_bank.setToolTipText("Trade with the bank");
					trade_bank.setPreferredSize(new java.awt.Dimension(86,45));
					trade_bank.setEnabled(false);
				}
				{
					trade_next = new JButton();
					tradeButtonPanel.add(trade_next, new AnchorConstraint(610, 764, 910, 527, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					trade_next.setText("NEXT");
					trade_next.setFont(new java.awt.Font("Segoe UI",0,10));
					trade_next.setToolTipText("Move on to the build phase");
					trade_next.setPreferredSize(new java.awt.Dimension(83, 45));
					trade_next.addActionListener(this);
				}
				{
					trade_player = new JButton();
					tradeButtonPanel.add(trade_player, new AnchorConstraint(303, 527, 603, 290, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					trade_player.setText("TRADE: PLAYER");
					trade_player.setFont(new java.awt.Font("Segoe UI",0,10));
					trade_player.setToolTipText("Trade with another player");
					trade_player.setPreferredSize(new java.awt.Dimension(83, 45));
					trade_player.setEnabled(false);
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
					build_city.setEnabled(false);
				}
				{
					build_settlement = new JButton();
					buildButtonPanel.add(build_settlement, new AnchorConstraint(330, 755, 630, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					build_settlement.setText("SETTLEMENT");
					build_settlement.setFont(new java.awt.Font("Segoe UI",0,10));
					build_settlement.setToolTipText("Build a settlement");
					build_settlement.setPreferredSize(new java.awt.Dimension(78, 45));
					build_settlement.addActionListener(this);
					//build_settlement.setEnabled(false);
				}
				{
					build_road = new JButton();
					buildButtonPanel.add(build_road, new AnchorConstraint(330, 527, 630, 290, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					build_road.setText("ROAD");
					build_road.setFont(new java.awt.Font("Segoe UI",0,10));
					build_road.setToolTipText("Build a road");
					build_road.setPreferredSize(new java.awt.Dimension(83, 45));
					build_road.addActionListener(this);
					//build_road.setEnabled(false);
				}
				{
					build_next = new JButton();
					buildButtonPanel.add(build_next, new AnchorConstraint(663, 0, 963, 755, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					build_next.setText("NEXT");
					build_next.setFont(new java.awt.Font("Segoe UI",0,10));
					build_next.setToolTipText("End your turn");
					build_next.setPreferredSize(new java.awt.Dimension(86, 45));
					build_next.addActionListener(this);
				}
				{
					build_dev = new JButton();
					buildButtonPanel.add(build_dev, new AnchorConstraint(663, 755, 963, 532, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
					build_dev.setText("DEV CARD");
					build_dev.setFont(new java.awt.Font("Segoe UI",0,10));
					build_dev.setToolTipText("Draw a development card");
					build_dev.setPreferredSize(new java.awt.Dimension(78, 45));
					build_dev.addActionListener(this);
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
	}

	public void startNewGame()
	{
		thisLayout.show(this, "ROLL");
	}
	
	public void setEvent(SettlersEvent _event)
	{
		event_manager = _event;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == opening_next)
		{
			event_manager.buttonEvent(0, thisLayout);
		}
		if(evt.getSource() == roll_next)
		{
			event_manager.buttonEvent(1, thisLayout);
		}
		if(evt.getSource() == trade_next)
		{
			event_manager.buttonEvent(2, thisLayout);
		}
		if(evt.getSource() == build_next)
		{
			event_manager.buttonEvent(3, thisLayout);
		}
		if(evt.getSource() == build_dev)
		{
			event_manager.buttonEvent(4, thisLayout);
		}
		if(evt.getSource() == build_settlement)
		{
			parent.getGUI().getController().buttonSettlement();
		}
		if(evt.getSource() == build_road)
		{
			parent.getGUI().getController().buttonRoad();
		}
	}

}
