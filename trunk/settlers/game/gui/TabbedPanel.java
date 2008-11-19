package settlers.game.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import settlers.game.GameState;
import settlers.game.events.*;
import settlers.game.gui.Deck;
import settlers.game.gui.DevelopmentCard;

public class TabbedPanel extends javax.swing.JPanel implements EventListener {
    private JScrollPane gameTextSP;
    private JScrollPane rollSP;
    private JScrollPane resourcesSP;
	private JScrollPane cardSP;
    private JTabbedPane tabbedPanel;
	
    private JTextArea errorText;
    private JTextArea cardText;
    private JTextArea rollText;
    private JTextArea resourcesText;
    private JTextArea gameText;
    private JTextArea credits;
    
    public TabbedPanel() {
        super();
        initGUI();
        //These are the events we need to update the current players resources accordingly
        EventManager.registerEvent("PLAYER_INITTURN_START", this);
        EventManager.registerEvent("PLAYER_TURN_START", this);
        EventManager.registerEvent("PLAYER_ROLLED", this);
        EventManager.registerEvent("PLAYER_BUILT_DEV_CARD", this);
        EventManager.registerEvent("PLAYER_BUILT_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_BUILT_ROAD", this);
        EventManager.registerEvent("PLAYER_TRADED", this);
    }
    
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(430, 150));
            AnchorLayout thisLayout = new AnchorLayout();
            this.setLayout(thisLayout);
            this.setSize(430, 150);
            {
                tabbedPanel = new JTabbedPane();
                this.add(tabbedPanel, new AnchorConstraint(3, 1001, 1003, 1, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                tabbedPanel.setTabPlacement(JTabbedPane.LEFT);
                tabbedPanel.setPreferredSize(new java.awt.Dimension(445, 150));
                {
                    gameText = new JTextArea();
                    gameText.setEditable(false);
                    gameTextSP = new javax.swing.JScrollPane(gameText);
                    tabbedPanel.addTab("GameText", null, gameTextSP, null);
                    gameTextSP.setPreferredSize(new java.awt.Dimension(378, 140));
                    tabbedPanel.setEnabledAt(0, false);
                }
                {
                    resourcesText = new JTextArea();
                    resourcesText.setEditable(false);
                    resourcesSP = new javax.swing.JScrollPane(resourcesText);
                    tabbedPanel.addTab("Resources", null, resourcesSP, null);
                    resourcesSP.setPreferredSize(new java.awt.Dimension(378,140));
                    tabbedPanel.setEnabledAt(1,false);
                }
                {
                    rollText = new JTextArea();
                    rollText.setEditable(false);
                    rollSP = new javax.swing.JScrollPane(rollText);
                    tabbedPanel.addTab("Roll History", null, rollSP , null);
                    rollSP.setPreferredSize(new java.awt.Dimension(378, 140));
                    tabbedPanel.setEnabledAt(2, false);
                }
                {
                    errorText = new JTextArea();
                    tabbedPanel.addTab("Errors", null, errorText, null);
                    errorText.setText("Errors");
                    errorText.setEditable(false);
                    tabbedPanel.setEnabledAt(3, false);
                }
                {
					cardText = new JTextArea();
                    cardText.setEditable(false);
					cardSP = new javax.swing.JScrollPane(cardText);
                    tabbedPanel.addTab("Your Cards", null, cardSP, null);
					cardSP.setPreferredSize(new java.awt.Dimension(378,140));
                    tabbedPanel.setEnabledAt(4, false);
                }
                {
                    credits = new JTextArea();
                    JScrollPane sp2 = new javax.swing.JScrollPane(credits);
                    tabbedPanel.addTab("Credits", null, sp2, null);
                    credits.setVisible(true);
                    credits.setEditable(false);
                    makeCredits();
                }
                tabbedPanel.setSelectedIndex(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
             * Implementation of the EventListener interface, this interface is implemented in order to make sure that we (1) have players and (2)  register
             * when a new players turn starts.
             *
             * @param e event parameter that is used to catch events that are fired
             */
    public boolean eventCalled(Event e)
    {
        String event = e.getEvent();
        
        if (event.equals("PLAYER_INITTURN_START") || event.equals("PLAYER_TURN_START"))
        {
            resourcesText.setText(settlers.game.GameState.getCurPlayer().getName() + "'s Resources\n");
            //resourcesText.append("Current Resources are unavailable during \ninitialization turns");
            resourcesText.append("Wood:  " + settlers.game.GameState.getCurPlayer().getWood() + "\n");
            resourcesText.append("Ore:   " + settlers.game.GameState.getCurPlayer().getOre() + "\n");
            resourcesText.append("Brick: "  + settlers.game.GameState.getCurPlayer().getBrick() + "\n");
            resourcesText.append("Sheep: " + settlers.game.GameState.getCurPlayer().getSheep() + "\n");
            resourcesText.append("Wheat: " + settlers.game.GameState.getCurPlayer().getWheat() + "\n");
            resourcesText.append("Victory points " + GameState.getCurPlayer().getVictoryPointTotal() + "\n");
            
            displayDevCards();

            updateUI();
            
        }
        /**
        * This else if will be used for when the game is actually in round robin  and the dice are rolled.  This allows the resources to update to the screen
        * in real time.
        */
        else if (event.equals("PLAYER_BUILT_DEV_CARD") || event.equals("PLAYER_BUILT_SETTLEMENT") || event.equals("PLAYER_BUILT_ROAD") || event.equals("PLAYER_ROLLED") || event.equals("PLAYER_TRADED"))
        {
            resourcesText.setText(settlers.game.GameState.getCurPlayer().getName() + "'s Resources\n");
            resourcesText.append("Wood:  " + settlers.game.GameState.getCurPlayer().getWood() + "\n");
            resourcesText.append("Ore:   " + settlers.game.GameState.getCurPlayer().getOre() + "\n");
            resourcesText.append("Brick: "  + settlers.game.GameState.getCurPlayer().getBrick() + "\n");
            resourcesText.append("Sheep: " + settlers.game.GameState.getCurPlayer().getSheep() + "\n");
            resourcesText.append("Wheat: " + settlers.game.GameState.getCurPlayer().getWheat() + "\n");
            resourcesText.append("Victory points " + GameState.getCurPlayer().getVictoryPointTotal() + "\n");
            
            displayDevCards();
            
            updateUI(); 
        }
        return true;
    }
    
    private void displayDevCards()
    {
        Deck dev = GameState.getCurPlayer().getDevCards();
        
		cardText.setText(settlers.game.GameState.getCurPlayer().getName() + "'s Development Cards\n\n");
		
        for(int i = 1; i < 6; i++)
        {
            switch(i)
            {
                case(1):
                {
                    if(dev.hasType(1) != 0)
                        if(dev.hasType(1) > 1)
                            cardText.append("Knight x " + dev.hasType(1) + "\n");
                        else
                            cardText.append("Knight\n");
                    break;
                }
                case(2):
                {
                    if(dev.hasType(2) != 0)
                        if(dev.hasType(2) > 1)
                            cardText.append("Road Building x " + dev.hasType(2) + "\n");
                        else
                            cardText.append("Road Building\n");
                    break;
                }
                case(3):
                {
                    if(dev.hasType(3) != 0)
                        if(dev.hasType(3) > 1)
                            cardText.append("Monopoly x " + dev.hasType(3) + "\n");
                        else
                            cardText.append("Monopoly\n");
                    break;
                }
                case(4):
                {
                    if(dev.hasType(4) != 0)
                        if(dev.hasType(4) > 1)
                            cardText.append("Year of Plenty x " + dev.hasType(4) + "\n");
                        else
                            cardText.append("Year of Plenty\n");
                    break;
                }
                case(5):
                {
                    if(dev.hasType(5) != 0)
                        if(dev.hasType(5) > 1)
                            cardText.append("Palace x " + dev.hasType(5) + "\n");
                        else
                            cardText.append("Palace\n");
                    break;
                }
            }
        }
    }
    
    
    
    private void showCredits()
    {
        
    }
    
    private void makeCredits() {
        // TODO Auto-generated method stub
        credits.append("Group Leads\n");
        credits.append("\tAlvaro Salkeld (Overall Lead)\n");
        credits.append("\tEdward Brotz (Overall Lead)\n");
        credits.append("\tNick Antonelli (GUI Team Lead)\n");
        credits.append("\tRoss Nichols (Logic Team Lead)\n");
        credits.append("\nGUI Team\n");
        credits.append("\tSpencer Gall\n");
        credits.append("\tEric Hernandez\n");
        credits.append("\tAndrew Strozier\n");
        credits.append("\tAmar Shah\n");
        credits.append("\tPaul Marks\n");
        credits.append("\nLogic Team\n");
        credits.append("\tEric Mudge\n");
        credits.append("\tAlvaro Salkeld\n");
        credits.append("\tPatrick Meyer\n");
        credits.append("\tFrancesca Ramadori\n");
        credits.append("\tEdward Brotz\n");
        credits.append("\tNaveen Dhawan\n");
        credits.append("\tCarlos Estevez\n");
        credits.append("\tScott Savino\n");
        credits.append("\tNick Dunlap\n");
    }
    
   

    public void startNewGame()
    {
        gameText.setText("");
    
        tabbedPanel.setTitleAt(0, "Game Text");
        tabbedPanel.setEnabledAt(0, true);
        tabbedPanel.setEnabledAt(1, true);
        tabbedPanel.setEnabledAt(2, true);
        tabbedPanel.setEnabledAt(3, true);
        tabbedPanel.setEnabledAt(4, true);
        tabbedPanel.setEnabledAt(5, false);
        tabbedPanel.setSelectedIndex(0);
        tabbedPanel.remove(5);
    }

    public JTextArea getGameText() {
        // TODO Auto-generated method stub
        return gameText;
    }

    public JTextArea getErrorPanel() {
        // TODO Auto-generated method stub
        return this.errorText;
    }

    public JTextArea getCardText() {
        // TODO Auto-generated method stub
        return cardText;
    }
    
    public void setRandomDiceRoll(String s)
    {
        rollText.append(s);
        updateUI();
    }
    
    public void setGameText(String s) 
    {
        gameText.setText(s);
        updateUI();
    }
    

}
