package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class BankTradeWindow implements ActionListener {
    //Initialize all the variables necessary for basic display
    private JFrame frame = new JFrame("Trade Window"); // creates new window titled "Trade Window"
    private JPanel resourcePanel = new JPanel();
    private JPanel offResources = new JPanel();
   // private JPanel offer2 = new JPanel();
    private JPanel askResources = new JPanel();
    private JPanel executePanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel status = new JLabel("Select the resource you want to trade.", JLabel.CENTER);
    private JButton execute = new JButton("Trade");
    private JButton display = new JButton("Confirm");
    private TradeImage offBrick = new TradeImage(GlobalVar.BRICK);
    private TradeImage offOre = new TradeImage(GlobalVar.ORE);
    private TradeImage offSheep = new TradeImage(GlobalVar.SHEEP);
    private TradeImage offWheat = new TradeImage(GlobalVar.WHEAT);
    private TradeImage offWood = new TradeImage(GlobalVar.WOOD);
    private TradeImage askBrick = new TradeImage(GlobalVar.BRICK);
    private TradeImage askOre = new TradeImage(GlobalVar.ORE);
    private TradeImage askSheep = new TradeImage(GlobalVar.SHEEP);
    private TradeImage askWheat = new TradeImage(GlobalVar.WHEAT);
    private TradeImage askWood = new TradeImage(GlobalVar.WOOD);
    private TradeImage off2Brick = new TradeImage(GlobalVar.BRICK);
    private TradeImage off2Ore = new TradeImage(GlobalVar.ORE);
    private TradeImage off2Sheep = new TradeImage(GlobalVar.SHEEP);
    private TradeImage off2Wheat = new TradeImage(GlobalVar.WHEAT);
    private TradeImage off2Wood = new TradeImage(GlobalVar.WOOD);
    private TradeImage off3Brick = new TradeImage(GlobalVar.BRICK);
    private TradeImage off3Ore = new TradeImage(GlobalVar.ORE);
    private TradeImage off3Sheep = new TradeImage(GlobalVar.SHEEP);
    private TradeImage off3Wheat = new TradeImage(GlobalVar.WHEAT);
    private TradeImage off3Wood = new TradeImage(GlobalVar.WOOD);
    private TradeImage off4Brick = new TradeImage(GlobalVar.BRICK);
    private TradeImage off4Ore = new TradeImage(GlobalVar.ORE);
    private TradeImage off4Sheep = new TradeImage(GlobalVar.SHEEP);
    private TradeImage off4Wheat = new TradeImage(GlobalVar.WHEAT);
    private TradeImage off4Wood = new TradeImage(GlobalVar.WOOD);
    private int size;
    private Player curPlayer;

    /**
          * Sets player variables so that trade window can be made appropriately
          **/    
    public BankTradeWindow() {        
        this.size = size;
        curPlayer = GameState.getCurPlayer(); // gets the current player's info
        status.setText(curPlayer.getName() + ": Select the resources to be traded.");
        
        execute.addActionListener(this);
        execute.setActionCommand("Trade");
    
        resourcePanel.setLayout(new GridLayout(1,2,15,0));
        offResources.setLayout(new GridLayout(5,1,0,5));
        askResources.setLayout(new GridLayout(5,1,0,5));

        
        offResources.add(offBrick);
        offResources.add(offOre);
        offResources.add(offSheep);
        offResources.add(offWheat);
        offResources.add(offWood);
        askResources.add(askBrick);
        askResources.add(askOre);
        askResources.add(askSheep);
        askResources.add(askWheat);
        askResources.add(askWood);
 
        setOfferVisibility();
        
        TitledBorder offBorder = new TitledBorder(new LineBorder(Color.black), "Offer (x4)");
        offBorder.setTitleColor(Color.black);
        offResources.setBorder(offBorder);
        TitledBorder askBorder = new TitledBorder(new LineBorder(Color.black), "Request");
        askBorder.setTitleColor(Color.black);
        askResources.setBorder(askBorder);
        
        resourcePanel.add(offResources);
        resourcePanel.add(askResources);
        
        executePanel.add(execute);
        buttonPanel.add(executePanel);
        frame.getContentPane().setLayout(new BorderLayout(0,5));
        frame.getContentPane().add(status, BorderLayout.NORTH);
        frame.getContentPane().add(resourcePanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    /**
          * Used to pick up all button inputs.
          * @param event the even to be checked for
          **/
    public void actionPerformed(ActionEvent event) {
        //Variable that decides what kind of error dialogue box to display (if necessary)
        int advance = 1;
        //Initial button press that the user who created the window clicks
        if (event.getActionCommand().equals("Trade")) {   
            //Series of if statements that make sure only 1 resource is offered for trade
            if (offBrick.getClicked() == true) {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true 
                || offWood.getClicked() == true)
                    advance = 0;
            }
            
            if (offOre.getClicked() == true) {
                if (offBrick.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true 
                || offWood.getClicked() == true)
                    advance = 0;          
            }
            
            if (offSheep.getClicked() == true) {
                if (offOre.getClicked() == true || offBrick.getClicked() == true || offWheat.getClicked() == true 
                || offWood.getClicked() == true)
                    advance = 0;            
            }
            
            if (offWheat.getClicked() == true) {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offBrick.getClicked() == true 
                || offWood.getClicked() == true)
                    advance = 0;            
            }
            
            if (offWood.getClicked() == true) {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true 
                || offBrick.getClicked() == true)
                    advance = 0;         
            }
            
            if (offBrick.getClicked() == false && offOre.getClicked() == false && offSheep.getClicked() == false 
            && offWheat.getClicked() == false && offWood.getClicked() == false) {
                advance = 2;
            }
            
            if (askBrick.getClicked() == false && askOre.getClicked() == false && askSheep.getClicked() == false 
            && askWheat.getClicked() == false && askWood.getClicked() == false) {
                if (advance == 2)
                    advance = 4;
                else
                    advance = 3;
            }
        
            //If statement that advances to the next player if all selections were performed properly
            if (advance == 1) {
                setResourceVisibility();
             
                if (curPlayer.getID() < size)
                    curPlayer = GameState.players.get(curPlayer.getID());
                else
                    curPlayer = GameState.players.getFirst();

                execute.setText("Cancel");
                execute.setActionCommand("Cancel");
                display.addActionListener(this);
                display.setActionCommand("Confirm");
                displayPanel.add(display);
                buttonPanel.add(displayPanel);
                
                setOfferVisibility();
            } 
            else if (advance == 0) //Displays error window because player has chosen more than one resource to offer
                JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
            else if (advance == 2) //Displays error window because player hasn't selected a resource to offer
                JOptionPane.showMessageDialog(frame, "You haven't selected a resource to offer yet!");   
            else if (advance == 3) //Displays error window because player hasn't selected a resource request
                JOptionPane.showMessageDialog(frame, "You haven't selected a resource to request yet!");
            else //Displays error window because player hasn't selected any resources at all
                JOptionPane.showMessageDialog(frame, "You haven't selected any resources yet!");
        }
            
        // this event occurs if the player chooses to cancel when the confirmation screen comes up
        if (event.getActionCommand().equals("Cancel"))   
            frame.dispose();

        if (event.getActionCommand().equals("Confirm")) {
            
            if (offBrick.getClicked() == true) {
                if (curPlayer.getBrick() < 3) {
                    JOptionPane.showMessageDialog(frame, "You must have at least 4 of a resource to trade with the bank.");
                    frame.dispose();
                }
                else {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    
                    if      (askBrick.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.BRICK);
                    else if (askOre.getClicked() == true)
                            curPlayer.giveResource(GlobalVar.ORE);
                    else if (askSheep.getClicked() == true)
                            curPlayer.giveResource(GlobalVar.SHEEP);
                    else if (askWheat.getClicked() == true)
                            curPlayer.giveResource(GlobalVar.WHEAT);
                    else if (askWood.getClicked() == true)
                            curPlayer.giveResource(GlobalVar.WOOD);
                    else {
                            curPlayer.giveResource(GlobalVar.BRICK);
                            curPlayer.giveResource(GlobalVar.BRICK);
                            curPlayer.giveResource(GlobalVar.BRICK);
                            curPlayer.giveResource(GlobalVar.BRICK);
                        }
                }
            }
            else if (offOre.getClicked() == true) {
                if (curPlayer.getOre() < 3) {
                    JOptionPane.showMessageDialog(frame, "You must have at least 4 of a resource to trade with the bank.");
                    frame.dispose();
                }
                else {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.subtractResource(GlobalVar.ORE);

                    if      (askBrick.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.BRICK);
                    else if (askOre.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.ORE);
                    else if (askSheep.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.SHEEP);
                    else if (askWheat.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WHEAT);
                    else if (askWood.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WOOD);
                    else {
                            curPlayer.giveResource(GlobalVar.ORE);
                            curPlayer.giveResource(GlobalVar.ORE);
                            curPlayer.giveResource(GlobalVar.ORE);
                            curPlayer.giveResource(GlobalVar.ORE);
                         }
                    }
            }
            else if (offSheep.getClicked() == true) {
                if (curPlayer.getSheep() < 3) {
                    JOptionPane.showMessageDialog(frame, "You must have at least 4 of a resource to trade with the bank.");
                    frame.dispose();
                }
                else {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.subtractResource(GlobalVar.SHEEP);

                    if      (askBrick.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.BRICK);
                    else if (askOre.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.ORE);
                    else if (askSheep.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.SHEEP);
                    else if (askWheat.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WHEAT);
                    else if (askWood.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WOOD);
                    else {
                            curPlayer.giveResource(GlobalVar.SHEEP);
                            curPlayer.giveResource(GlobalVar.SHEEP);
                            curPlayer.giveResource(GlobalVar.SHEEP);
                            curPlayer.giveResource(GlobalVar.SHEEP);
                         }
                    }
            }
            else if (offWheat.getClicked() == true) {
                if (curPlayer.getWheat() < 3) {
                    JOptionPane.showMessageDialog(frame, "You must have at least 4 of a resource to trade with the bank.");
                    frame.dispose();
                }
                else {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.subtractResource(GlobalVar.WHEAT);

                    if      (askBrick.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.BRICK);
                    else if (askOre.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.ORE);
                    else if (askSheep.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.SHEEP);
                    else if (askWheat.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WHEAT);
                    else if (askWood.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WOOD);
                    else {
                            curPlayer.giveResource(GlobalVar.WHEAT);
                            curPlayer.giveResource(GlobalVar.WHEAT);
                            curPlayer.giveResource(GlobalVar.WHEAT);
                            curPlayer.giveResource(GlobalVar.WHEAT);
                         }
                }
            }
            else if (offWood.getClicked() == true) {
                if (curPlayer.getWood() < 3) {
                    JOptionPane.showMessageDialog(frame, "You must have at least 4 of a resource to trade with the bank.");
                    frame.dispose();
                }
                else {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.subtractResource(GlobalVar.WOOD);

                    if      (askBrick.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.BRICK);
                    else if (askOre.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.ORE);
                    else if (askSheep.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.SHEEP);
                    else if (askWheat.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WHEAT);
                    else if (askWood.getClicked() == true)
                        curPlayer.giveResource(GlobalVar.WOOD);
                    else {
                            curPlayer.giveResource(GlobalVar.WOOD);
                            curPlayer.giveResource(GlobalVar.WOOD);
                            curPlayer.giveResource(GlobalVar.WOOD);
                            curPlayer.giveResource(GlobalVar.WOOD);
                         }
                }
            }
            
            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
            EventManager.callEvent(n);
            frame.dispose();
        }
    }
    
    public void setResourceVisibility() {
        if (offBrick.getClicked() == false)
            offBrick.setVisible(false);
        if (offOre.getClicked() == false)
            offOre.setVisible(false);
        if (offSheep.getClicked() == false)
            offSheep.setVisible(false);
        if (offWheat.getClicked() == false)
            offWheat.setVisible(false);
        if (offWood.getClicked() == false)
            offWood.setVisible(false);
        
        if (askBrick.getClicked() == false)
            askBrick.setVisible(false);
        if (askOre.getClicked() == false)
            askOre.setVisible(false);
        if (askSheep.getClicked() == false)
            askSheep.setVisible(false);
        if (askWheat.getClicked() == false)
            askWheat.setVisible(false);
        if (askWood.getClicked() == false)
            askWood.setVisible(false);
    }
    
    public void setOfferVisibility() {
        if (curPlayer.getBrick() == 0)
            offBrick.setVisible(false);
        if (curPlayer.getOre() == 0)
            offOre.setVisible(false);
        if (curPlayer.getSheep() == 0)
            offSheep.setVisible(false);
        if (curPlayer.getWheat() == 0)
            offWheat.setVisible(false);
        if (curPlayer.getWood() == 0)
            offWood.setVisible(false);
    }
}