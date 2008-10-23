package settlers.game.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import settlers.game.events.*;

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
public class TabbedPanel extends javax.swing.JPanel implements EventListener {
    private JTabbedPane tabbedPanel;
    private JTextArea errorText;
    private JScrollPane sp1;
    private JPanel cardPanel;
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
                    sp1 = new javax.swing.JScrollPane(gameText);
                    tabbedPanel.addTab("GameText", null, sp1, null);
                    sp1.setPreferredSize(new java.awt.Dimension(378, 140));
                    tabbedPanel.setEnabledAt(0, false);
                }
                {
                    resourcesText = new JTextArea();
                    tabbedPanel.addTab("Resources", null, resourcesText, null);
                    resourcesText.setPreferredSize(new java.awt.Dimension(378,140));
                    resourcesText.setEditable(false);
                    tabbedPanel.setEnabledAt(1,false);
                }
                {
                    rollText = new JTextArea();
                    tabbedPanel.addTab("Roll History", null, rollText, null);
                    rollText.setText("Roll History");
                    rollText.setPreferredSize(new java.awt.Dimension(378, 140));
                    rollText.setEditable(false);
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
                    cardPanel = new JPanel();
                    tabbedPanel.addTab("Your Cards", null, cardPanel, null);
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
    public void eventCalled(Event e)
    {
        String event = e.getEvent();
        
        if (event.equals("PLAYER_INITTURN_START"))
        {
            resourcesText.setText(settlers.game.GameState.getCurPlayer().getName() + "'s Resources\n");
            //resourcesText.append("Current Resources are unavailable during \ninitialization turns");
            /**
                                This is just a proof of concept so we know what we can get the different players resources
                                */
            resourcesText.append("Wood:  " + settlers.game.GameState.getCurPlayer().getWood() + "\n");
            resourcesText.append("Ore:   " + settlers.game.GameState.getCurPlayer().getOre() + "\n");
            resourcesText.append("Brick: "  + settlers.game.GameState.getCurPlayer().getBrick() + "\n");
            resourcesText.append("Sheep: " + settlers.game.GameState.getCurPlayer().getSheep() + "\n");
            resourcesText.append("Wheat: " + settlers.game.GameState.getCurPlayer().getWheat() + "\n");
            updateUI();
            
        }
        /**
                        * This else if will be used for when the game is actually in round robin and players are going through "real" turns as opposed to initialization turns
                        */
        else if (event.equals("PLAYER_TURN_START"))
        {
            resourcesText.setText(settlers.game.GameState.getCurPlayer().getName() + "'s Resources\n");
            resourcesText.append("Wood:  " + settlers.game.GameState.getCurPlayer().getWood() + "\n");
            resourcesText.append("Ore:   " + settlers.game.GameState.getCurPlayer().getOre() + "\n");
            resourcesText.append("Brick: "  + settlers.game.GameState.getCurPlayer().getBrick() + "\n");
            resourcesText.append("Sheep: " + settlers.game.GameState.getCurPlayer().getSheep() + "\n");
            resourcesText.append("Wheat: " + settlers.game.GameState.getCurPlayer().getWheat() + "\n");
            updateUI();
        }
    }
    
    public void setResourcesText(String _resourcesText)
    {
        resourcesText.append(_resourcesText);
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

    public JPanel getCardPanel() {
        // TODO Auto-generated method stub
        return cardPanel;
    }
    
    public void setRandomDiceRoll(String s)
    {
        rollText.append(s);
        updateUI();
    }

}
