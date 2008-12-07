package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class TradeWindow implements ActionListener
{
    //Initialize all the variables necessary for basic display
    private JFrame frame = new JFrame("Trade Window");
    private JPanel resourcePanel = new JPanel();
    private JPanel offResources = new JPanel();
    private JPanel offer2 = new JPanel();
    private JPanel offer3 = new JPanel();
    private JPanel offer4 = new JPanel();
    private JPanel askResources = new JPanel();
    private JPanel executePanel = new JPanel();
    private JPanel displayPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel status = new JLabel("Select the resource(s) you want to trade.", JLabel.CENTER);
    private JButton execute = new JButton("Query");
    private JButton display = new JButton("Show Resources");
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
    
    //Initialize variables necessary to track the players
    private int size;
    private int counter = 1;
    private Player curPlayer;
    private Player initPlayer;

    public TradeWindow(int size)
    {        
        //Set player variables so that trade window can be made appropriately
        this.size = size;
        initPlayer = GameState.getCurPlayer();
        curPlayer = GameState.getCurPlayer();
        status.setText(curPlayer.getName() + ": Select the resource(s) you want to trade.");
        
        //Build trade window for only two players
        if (size == 2)
        {
            execute.addActionListener(this);
            execute.setActionCommand("Query");
        
            resourcePanel.setLayout(new GridLayout(1,3,15,0));
            offResources.setLayout(new GridLayout(5,1,0,5));
            askResources.setLayout(new GridLayout(5,1,0,5));
            offer2.setLayout(new GridLayout(5,1,0,5));
            
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
            offer2.add(off2Brick);
            offer2.add(off2Ore);
            offer2.add(off2Sheep);
            offer2.add(off2Wheat);
            offer2.add(off2Wood);
            
            setOfferVisibility(counter);
            
            TitledBorder offBorder = new TitledBorder(new LineBorder(Color.black), "Offer");
            offBorder.setTitleColor(Color.black);
            offResources.setBorder(offBorder);
            TitledBorder askBorder = new TitledBorder(new LineBorder(Color.black), "Request");
            askBorder.setTitleColor(Color.black);
            askResources.setBorder(askBorder);
            TitledBorder off2Border = new TitledBorder(new LineBorder(Color.black), "Offer 1");
            off2Border.setTitleColor(Color.black);
            offer2.setBorder(off2Border);
            
            resourcePanel.add(offResources);
            resourcePanel.add(askResources);
            resourcePanel.add(offer2);
            
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
            offer2.setVisible(false);
        }
        
        //Build trade window for three players
        else if (size == 3)
        {
            execute.addActionListener(this);
            execute.setActionCommand("Query");
        
            resourcePanel.setLayout(new GridLayout(1,4,15,0));
            offResources.setLayout(new GridLayout(5,1,0,5));
            askResources.setLayout(new GridLayout(5,1,0,5));
            offer2.setLayout(new GridLayout(5,1,0,5));
            offer3.setLayout(new GridLayout(5,1,0,5));
            
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
            offer2.add(off2Brick);
            offer2.add(off2Ore);
            offer2.add(off2Sheep);
            offer2.add(off2Wheat);
            offer2.add(off2Wood);
            offer3.add(off3Brick);
            offer3.add(off3Ore);
            offer3.add(off3Sheep);
            offer3.add(off3Wheat);
            offer3.add(off3Wood);
            
            setOfferVisibility(counter);
            
            TitledBorder offBorder = new TitledBorder(new LineBorder(Color.black), "Offer");
            offBorder.setTitleColor(Color.black);
            offResources.setBorder(offBorder);
            TitledBorder askBorder = new TitledBorder(new LineBorder(Color.black), "Request");
            askBorder.setTitleColor(Color.black);
            askResources.setBorder(askBorder);
            TitledBorder off2Border = new TitledBorder(new LineBorder(Color.black), "Offer 1");
            off2Border.setTitleColor(Color.black);
            offer2.setBorder(off2Border);
            TitledBorder off3Border = new TitledBorder(new LineBorder(Color.black), "Offer 2");
            off3Border.setTitleColor(Color.black);
            offer3.setBorder(off3Border);
            
            resourcePanel.add(offResources);
            resourcePanel.add(askResources);
            resourcePanel.add(offer2);
            resourcePanel.add(offer3);
            
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
            offer2.setVisible(false);
            offer3.setVisible(false);
        }
        
        //Build trade window for four players
        else if (size == 4)
        {
            execute.addActionListener(this);
            execute.setActionCommand("Query");
        
            resourcePanel.setLayout(new GridLayout(1,4,15,0));
            offResources.setLayout(new GridLayout(5,1,0,5));
            askResources.setLayout(new GridLayout(5,1,0,5));
            offer2.setLayout(new GridLayout(5,1,0,5));
            offer3.setLayout(new GridLayout(5,1,0,5));
            offer4.setLayout(new GridLayout(5,1,0,5));
            
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
            offer2.add(off2Brick);
            offer2.add(off2Ore);
            offer2.add(off2Sheep);
            offer2.add(off2Wheat);
            offer2.add(off2Wood);
            offer3.add(off3Brick);
            offer3.add(off3Ore);
            offer3.add(off3Sheep);
            offer3.add(off3Wheat);
            offer3.add(off3Wood);
            offer4.add(off4Brick);
            offer4.add(off4Ore);
            offer4.add(off4Sheep);
            offer4.add(off4Wheat);
            offer4.add(off4Wood);
            
            setOfferVisibility(counter);
            
            TitledBorder offBorder = new TitledBorder(new LineBorder(Color.black), "Offer");
            offBorder.setTitleColor(Color.black);
            offResources.setBorder(offBorder);
            TitledBorder askBorder = new TitledBorder(new LineBorder(Color.black), "Request");
            askBorder.setTitleColor(Color.black);
            askResources.setBorder(askBorder);
            TitledBorder off2Border = new TitledBorder(new LineBorder(Color.black), "Offer 1");
            off2Border.setTitleColor(Color.black);
            offer2.setBorder(off2Border);
            TitledBorder off3Border = new TitledBorder(new LineBorder(Color.black), "Offer 2");
            off3Border.setTitleColor(Color.black);
            offer3.setBorder(off3Border);
            TitledBorder off4Border = new TitledBorder(new LineBorder(Color.black), "Offer 3");
            off4Border.setTitleColor(Color.black);
            offer4.setBorder(off4Border);
            
            resourcePanel.add(offResources);
            resourcePanel.add(askResources);
            resourcePanel.add(offer2);
            resourcePanel.add(offer3);
            resourcePanel.add(offer4);
            
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
            offer2.setVisible(false);
            offer3.setVisible(false);
            offer4.setVisible(false);
        }
    }
    
    //Used to pick up all button inputs
    public void actionPerformed(ActionEvent event)
    {
        //Variable that decides what kind of error dialogue box to display (if necessary)
        int advance = 1;
    
        //Initial button press that the user who created the window clicks
        if (event.getActionCommand().equals("Query"))
        {   
            //Series of if statements that make sure only 1 resource is offered for trade
            if (offBrick.getClicked() == true)
            {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true || offWood.getClicked() == true)
                {
                    advance = 0;
                }
            }
            
            if (offOre.getClicked() == true)
            {
                if (offBrick.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true || offWood.getClicked() == true)
                {
                    advance = 0;
                }            
            }
            
            if (offSheep.getClicked() == true)
            {
                if (offOre.getClicked() == true || offBrick.getClicked() == true || offWheat.getClicked() == true || offWood.getClicked() == true)
                {
                    advance = 0;
                }            
            }
            
            if (offWheat.getClicked() == true)
            {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offBrick.getClicked() == true || offWood.getClicked() == true)
                {
                    advance = 0;
                }            
            }
            
            if (offWood.getClicked() == true)
            {
                if (offOre.getClicked() == true || offSheep.getClicked() == true || offWheat.getClicked() == true || offBrick.getClicked() == true)
                {
                    advance = 0;
                }            
            }
            
            if (offBrick.getClicked() == false && offOre.getClicked() == false && offSheep.getClicked() == false && offWheat.getClicked() == false && offWood.getClicked() == false)
            {
                advance = 2;
            }
            
            if (askBrick.getClicked() == false && askOre.getClicked() == false && askSheep.getClicked() == false && askWheat.getClicked() == false && askWood.getClicked() == false)
            {
                if (advance == 2)
                {
                    advance = 4;
                }
                else
                {
                    advance = 3;
                }
            }
        
            //If statement that advances to the next player if all selections were performed properly
            if (advance == 1)
            {
                setResourceVisibility(counter);
             
                if (curPlayer.getID() < size)
                {
                    curPlayer = GameState.players.get(curPlayer.getID());
                }
                else
                {
                    curPlayer = GameState.players.getFirst();
                }
                
                //Update the counter to reflect the next player
                counter++;
                
                //Sets the status bar to reflect the new player and change the buttons functions
                status.setText(curPlayer.getName() + ": Press the 'Show Resources' button.");
                execute.setText("Offer Resources");
                execute.setActionCommand("Next Player");
                execute.setEnabled(false);
                display.addActionListener(this);
                display.setActionCommand("Show");
                displayPanel.add(display);
                buttonPanel.add(displayPanel);
                
                setOfferVisibility(counter);
            }
            
            //Displays error window because player has chosen more than one resource to offer
            else if (advance == 0)
            {
                JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
            }
            
            //Displays error window because player hasn't selected a resource to offer
            else if (advance == 2)
            {
                JOptionPane.showMessageDialog(frame, "You haven't selected a resource to offer yet!");
            }
            
            //Displays error window because player hasn't selected a resource request
            else if (advance == 3)
            {
                JOptionPane.showMessageDialog(frame, "You haven't selected a resource to request yet!");
            }
            
            //Displays error window because player hasn't selected any resources at all
            else
            {
                JOptionPane.showMessageDialog(frame, "You haven't selected any resources yet!");
            }
        }
        
        //Event fired when a player selects a resource to offer the initial player
        if (event.getActionCommand().equals("Next Player"))
        {     
            boolean click = checkOneClick(counter);
            
            if (click == true)
            {
                if (counter == size)
                {
                    if (curPlayer.getID() < size)
                    {
                        curPlayer = GameState.players.get(curPlayer.getID());
                    }
                    else
                    {
                        curPlayer = GameState.players.getFirst();
                    }
            
                    status.setText(curPlayer.getName() + ": Select a trade option.");
            
                    if (counter == 2)
                    {
                        setResourceVisibility(counter);
                        changeCommandPanel(counter);
                    }
                    else if (counter == 3)
                    {
                        setResourceVisibility(counter);
                        changeCommandPanel(counter);
                    }
                    else if (counter == 4)
                    {
                        setResourceVisibility(counter);
                        changeCommandPanel(counter);
                    }
                }
                else
                {
                    if (counter == 2)
                    {
                        setResourceVisibility(counter);
                    
                        if (curPlayer.getID() < size)
                        {
                            curPlayer = GameState.players.get(curPlayer.getID());
                        }
                        else
                        {
                            curPlayer = GameState.players.getFirst();
                        }
   
                        counter++;
                        
                        //Sets the status bar to reflect the new player and change the buttons functions
                        status.setText(curPlayer.getName() + ": Press the 'Show Resources' button.");
                        display.setEnabled(true);
                        execute.setEnabled(false);
                    
                        setOfferVisibility(counter);
                    }
                    else if (counter == 3)
                    {
                        setResourceVisibility(counter);
                    
                        if (curPlayer.getID() < size)
                        {
                            curPlayer = GameState.players.get(curPlayer.getID());
                        }
                        else
                        {
                            curPlayer = GameState.players.getFirst();
                        }
                   
                        counter++;
                        
                        //Sets the status bar to reflect the new player and change the buttons functions
                        status.setText(curPlayer.getName() + ": Press the 'Show Resources' button.");
                        display.setEnabled(true);
                        execute.setEnabled(false);
                    
                        setOfferVisibility(counter);
                    }
                    else if (counter == 4)
                    {
                        setResourceVisibility(counter);
                    
                        if (curPlayer.getID() < size)
                        {
                            curPlayer = GameState.players.get(curPlayer.getID());
                        }
                        else
                        {
                            curPlayer = GameState.players.getFirst();
                        }
                    
                        counter++;
                    
                        //Sets the status bar to reflect the new player and change the buttons functions
                        status.setText(curPlayer.getName() + ": Press the 'Show Resources' button.");
                        display.setEnabled(true);
                        execute.setEnabled(false);
                    
                        setOfferVisibility(counter);
                    }
                }
            }
        }
        
        //Event fired when a player wants to make their resoruces visible so they can select them for trade
        if (event.getActionCommand().equals("Show"))
        {
            status.setText(curPlayer.getName() + ": Select the resource you are willing to trade.");
            display.setEnabled(false);
            execute.setEnabled(true);
            
            if (counter == 2)
            {
                offer2.setVisible(true);
            }
            else if (counter == 3)
            {
                offer3.setVisible(true);
            }
            else if (counter == 4)
            {
                offer4.setVisible(true);
            }
        }
        
        if (event.getActionCommand().equals("Reject"))
        {
            frame.dispose();
        }
        
        if (event.getActionCommand().equals("P2"))
        {
            Player temp = new Player("a",Color.black);
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
            }
            else
            {
                temp = GameState.players.getFirst();
            }
        
            if (offBrick.getClicked() == true)
            {
                if (off2Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off2Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off2Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off2Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WHEAT);
            
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off2Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    
                    temp.giveResource(GlobalVar.BRICK);
                }
            }
            else if (offOre.getClicked() == true)
            {
                if (off2Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off2Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off2Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off2Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off2Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.ORE);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    
                    temp.giveResource(GlobalVar.ORE);
                }
            }
            else if (offSheep.getClicked() == true)
            {
                if (off2Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off2Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off2Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off2Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off2Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    
                    temp.giveResource(GlobalVar.SHEEP);
                }
            }
            else if (offWheat.getClicked() == true)
            {
                if (off2Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off2Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off2Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off2Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off2Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    
                    temp.giveResource(GlobalVar.WHEAT);
                }
            }
            else if (offWood.getClicked() == true)
            {
                if (off2Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off2Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off2Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off2Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off2Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    
                    temp.giveResource(GlobalVar.WOOD);
                }
            }
            
            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
            EventManager.callEvent(n);
            frame.dispose();
        }
        
        if (event.getActionCommand().equals("P3"))
        {
            Player temp = new Player("a",Color.black);
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
                        
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
            }
            else
            {
                temp = GameState.players.getFirst();
                
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
            }
        
            if (offBrick.getClicked() == true)
            {
                if (off3Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off3Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off3Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off3Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off3Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    
                    temp.giveResource(GlobalVar.BRICK);
                }
            }
            else if (offOre.getClicked() == true)
            {
                if (off3Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off3Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off3Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off3Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off3Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.ORE);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    
                    temp.giveResource(GlobalVar.ORE);
                }
            }
            else if (offSheep.getClicked() == true)
            {
                if (off3Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off3Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off3Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off3Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off3Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    
                    temp.giveResource(GlobalVar.SHEEP);
                }
            }
            else if (offWheat.getClicked() == true)
            {
                if (off3Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off3Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off3Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off3Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off3Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    
                    temp.giveResource(GlobalVar.WHEAT);
                }
            }
            else if (offWood.getClicked() == true)
            {
                if (off3Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off3Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off3Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off3Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off3Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    
                    temp.giveResource(GlobalVar.WOOD);
                }
            }
            
            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
            EventManager.callEvent(n);
            
            frame.dispose();
        }
        
        if (event.getActionCommand().equals("P4"))
        {
            Player temp = new Player("a",Color.black);
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
                
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
                
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
            }
            else
            {
                temp = GameState.players.getFirst();
                
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
                    
                if (temp.getID() < size)
                {
                    temp = GameState.players.get(temp.getID());
                }
                else
                {
                    temp = GameState.players.getFirst();
                }
            }
        
            if (offBrick.getClicked() == true)
            {
                if (off4Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off4Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off4Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off4Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else if (off4Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.BRICK);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.BRICK);
                    
                    temp.giveResource(GlobalVar.BRICK);
                }
            }
            else if (offOre.getClicked() == true)
            {
                if (off4Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off4Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off4Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off4Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.ORE);
                }
                else if (off4Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.ORE);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.ORE);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.ORE);

                    temp.giveResource(GlobalVar.ORE);
                }
            }
            else if (offSheep.getClicked() == true)
            {
                if (off4Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off4Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off4Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off4Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else if (off4Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.SHEEP);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.SHEEP);
                    
                    temp.giveResource(GlobalVar.SHEEP);
                }
            }
            else if (offWheat.getClicked() == true)
            {
                if (off4Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off4Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off4Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off4Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else if (off4Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WHEAT);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WHEAT);
                    
                    temp.giveResource(GlobalVar.WHEAT);
                }
            }
            else if (offWood.getClicked() == true)
            {
                if (off4Brick.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.BRICK);
                    
                    temp.subtractResource(GlobalVar.BRICK);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off4Ore.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.ORE);
                    
                    temp.subtractResource(GlobalVar.ORE);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off4Sheep.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.SHEEP);
                    
                    temp.subtractResource(GlobalVar.SHEEP);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off4Wheat.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WHEAT);
                    
                    temp.subtractResource(GlobalVar.WHEAT);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else if (off4Wood.getClicked() == true)
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    curPlayer.giveResource(GlobalVar.WOOD);
                    
                    temp.subtractResource(GlobalVar.WOOD);
                    temp.giveResource(GlobalVar.WOOD);
                }
                else
                {
                    curPlayer.subtractResource(GlobalVar.WOOD);
                    
                    temp.giveResource(GlobalVar.WOOD);
                }
            }
            
            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
            EventManager.callEvent(n);
            
            frame.dispose();
        }
    }
    
    public boolean checkOneClick(int counter)
    {
        if (counter == 2)
        {
            if (off2Brick.getClicked() == true)
            {
                if (off2Ore.getClicked() == true || off2Sheep.getClicked() == true || off2Wheat.getClicked() == true || off2Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }
            }
            
            if (off2Ore.getClicked() == true)
            {
                if (off2Brick.getClicked() == true || off2Sheep.getClicked() == true || off2Wheat.getClicked() == true || off2Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off2Sheep.getClicked() == true)
            {
                if (off2Ore.getClicked() == true || off2Brick.getClicked() == true || off2Wheat.getClicked() == true || off2Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off2Wheat.getClicked() == true)
            {
                if (off2Ore.getClicked() == true || off2Sheep.getClicked() == true || off2Brick.getClicked() == true || off2Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off2Wood.getClicked() == true)
            {
                if (off2Ore.getClicked() == true || off2Sheep.getClicked() == true || off2Wheat.getClicked() == true || off2Brick.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
        }
        else if (counter == 3)
        {
            if (off3Brick.getClicked() == true)
            {
                if (off3Ore.getClicked() == true || off3Sheep.getClicked() == true || off3Wheat.getClicked() == true || off3Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }
            }
            
            if (off3Ore.getClicked() == true)
            {
                if (off3Brick.getClicked() == true || off3Sheep.getClicked() == true || off3Wheat.getClicked() == true || off3Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off3Sheep.getClicked() == true)
            {
                if (off3Ore.getClicked() == true || off3Brick.getClicked() == true || off3Wheat.getClicked() == true || off3Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off3Wheat.getClicked() == true)
            {
                if (off3Ore.getClicked() == true || off3Sheep.getClicked() == true || off3Brick.getClicked() == true || off3Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off3Wood.getClicked() == true)
            {
                if (off3Ore.getClicked() == true || off3Sheep.getClicked() == true || off3Wheat.getClicked() == true || off3Brick.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
        }
        else if (counter == 4)
        {
            if (off4Brick.getClicked() == true)
            {
                if (off4Ore.getClicked() == true || off4Sheep.getClicked() == true || off4Wheat.getClicked() == true || off4Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }
            }
            
            if (off4Ore.getClicked() == true)
            {
                if (off4Brick.getClicked() == true || off4Sheep.getClicked() == true || off4Wheat.getClicked() == true || off4Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off4Sheep.getClicked() == true)
            {
                if (off4Ore.getClicked() == true || off4Brick.getClicked() == true || off4Wheat.getClicked() == true || off4Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off4Wheat.getClicked() == true)
            {
                if (off4Ore.getClicked() == true || off4Sheep.getClicked() == true || off4Brick.getClicked() == true || off4Wood.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
            
            if (off4Wood.getClicked() == true)
            {
                if (off4Ore.getClicked() == true || off4Sheep.getClicked() == true || off4Wheat.getClicked() == true || off4Brick.getClicked() == true)
                {
                    JOptionPane.showMessageDialog(frame, "You may only offer 1 resource at a time.");
                    return false;
                }            
            }
        }
        
        return true;
    }
    
    public void setResourceVisibility(int counter)
    {
        if (counter == 1)
        {
            if (offBrick.getClicked() == false)
            {
                offBrick.setVisible(false);
            }
            if (offOre.getClicked() == false)
            {
                offOre.setVisible(false);
            }
            if (offSheep.getClicked() == false)
            {
                offSheep.setVisible(false);
            }
            if (offWheat.getClicked() == false)
            {
                offWheat.setVisible(false);
            }
            if (offWood.getClicked() == false)
            {
                offWood.setVisible(false);
            }
            
            if (askBrick.getClicked() == false)
            {
                askBrick.setVisible(false);
            }
            if (askOre.getClicked() == false)
            {
                askOre.setVisible(false);
            }
            if (askSheep.getClicked() == false)
            {
                askSheep.setVisible(false);
            }
            if (askWheat.getClicked() == false)
            {
                askWheat.setVisible(false);
            }
            if (askWood.getClicked() == false)
            {
                askWood.setVisible(false);
            }
        }
        else if (counter == 2)
        {
            if (off2Brick.getClicked() == false)
            {
                off2Brick.setVisible(false);
            }
            if (off2Ore.getClicked() == false)
            {
                off2Ore.setVisible(false);
            }
            if (off2Sheep.getClicked() == false)
            {
                off2Sheep.setVisible(false);
            }
            if (off2Wheat.getClicked() == false)
            {
                off2Wheat.setVisible(false);
            }
            if (off2Wood.getClicked() == false)
            {
                off2Wood.setVisible(false);
            }        
        }
        else if (counter == 3)
        {
            if (off3Brick.getClicked() == false)
            {
                off3Brick.setVisible(false);
            }
            if (off3Ore.getClicked() == false)
            {
                off3Ore.setVisible(false);
            }
            if (off3Sheep.getClicked() == false)
            {
                off3Sheep.setVisible(false);
            }
            if (off3Wheat.getClicked() == false)
            {
                off3Wheat.setVisible(false);
            }
            if (off3Wood.getClicked() == false)
            {
                off3Wood.setVisible(false);
            }        
        }
        else if (counter == 4)
        {
            if (off4Brick.getClicked() == false)
            {
                off4Brick.setVisible(false);
            }
            if (off4Ore.getClicked() == false)
            {
                off4Ore.setVisible(false);
            }
            if (off4Sheep.getClicked() == false)
            {
                off4Sheep.setVisible(false);
            }
            if (off4Wheat.getClicked() == false)
            {
                off4Wheat.setVisible(false);
            }
            if (off4Wood.getClicked() == false)
            {
                off4Wood.setVisible(false);
            }
        }
    }
    
    public void setOfferVisibility(int counter)
    {
        if (counter == 1)
        {
            if (curPlayer.getBrick() == 0)
            {
                offBrick.setVisible(false);
            }
            if (curPlayer.getOre() == 0)
            {
                offOre.setVisible(false);
            }
            if (curPlayer.getSheep() == 0)
            {
                offSheep.setVisible(false);
            }
            if (curPlayer.getWheat() == 0)
            {
                offWheat.setVisible(false);
            }
            if (curPlayer.getWood() == 0)
            {
                offWood.setVisible(false);
            }
        }
        else if (counter == 2)
        {
            if (curPlayer.getBrick() == 0)
            {
                off2Brick.setVisible(false);
            }
            if (curPlayer.getOre() == 0)
            {
                off2Ore.setVisible(false);
            }
            if (curPlayer.getSheep() == 0)
            {
                off2Sheep.setVisible(false);
            }
            if (curPlayer.getWheat() == 0)
            {
                off2Wheat.setVisible(false);
            }
            if (curPlayer.getWood() == 0)
            {
                off2Wood.setVisible(false);
            }
        }
        else if (counter == 3)
        {
            if (curPlayer.getBrick() == 0)
            {
                off3Brick.setVisible(false);
            }
            if (curPlayer.getOre() == 0)
            {
                off3Ore.setVisible(false);
            }
            if (curPlayer.getSheep() == 0)
            {
                off3Sheep.setVisible(false);
            }
            if (curPlayer.getWheat() == 0)
            {
                off3Wheat.setVisible(false);
            }
            if (curPlayer.getWood() == 0)
            {
                off3Wood.setVisible(false);
            }
        }
        else if (counter == 4)
        {
            if (curPlayer.getBrick() == 0)
            {
                off4Brick.setVisible(false);
            }
            if (curPlayer.getOre() == 0)
            {
                off4Ore.setVisible(false);
            }
            if (curPlayer.getSheep() == 0)
            {
                off4Sheep.setVisible(false);
            }
            if (curPlayer.getWheat() == 0)
            {
                off4Wheat.setVisible(false);
            }
            if (curPlayer.getWood() == 0)
            {
                off4Wood.setVisible(false);
            }
        }
    }
    
    public void changeCommandPanel(int counter)
    {
        Player temp;
        buttonPanel.remove(executePanel);
        buttonPanel.remove(displayPanel);
    
        if (counter == 2)
        {
            JPanel rejectPanel = new JPanel();
            JPanel p2Panel = new JPanel();
            JButton reject = new JButton("Reject Offer");
            JButton p2OK = new  JButton("Accept Offer 1");
            
            buttonPanel.setLayout(new GridLayout(1,2,5,0));
            rejectPanel.add(reject);
            p2Panel.add(p2OK);
            buttonPanel.add(rejectPanel);
            buttonPanel.add(p2Panel);
            
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            reject.setToolTipText("Reject all offers.");
            
            reject.addActionListener(this);
            reject.setActionCommand("Reject");
            p2OK.addActionListener(this);
            p2OK.setActionCommand("P2");
        }
        else if (counter == 3)
        {
            JPanel rejectPanel = new JPanel();
            JPanel p2Panel = new JPanel();
            JPanel p3Panel = new JPanel();
            JButton reject = new JButton("Reject Offers");
            JButton p2OK = new  JButton("Accept Offer 1");
            JButton p3OK = new JButton("Accept Offer 2");
            
            buttonPanel.setLayout(new GridLayout(1,3,5,0));
            rejectPanel.add(reject);
            p2Panel.add(p2OK);
            p3Panel.add(p3OK);
            buttonPanel.add(rejectPanel);
            buttonPanel.add(p2Panel);
            buttonPanel.add(p3Panel);
            
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            
            if (temp.getID() < size)
            {
                temp = GameState.players.get(temp.getID());
                p3OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p3OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            reject.setToolTipText("Reject all offers.");
            
            reject.addActionListener(this);
            reject.setActionCommand("Reject");
            p2OK.addActionListener(this);
            p2OK.setActionCommand("P2");
            p3OK.addActionListener(this);
            p3OK.setActionCommand("P3");
        }
        else if (counter == 4)
        {
            JPanel rejectPanel = new JPanel();
            JPanel p2Panel = new JPanel();
            JPanel p3Panel = new JPanel();
            JPanel p4Panel = new JPanel();
            JButton reject = new JButton("Reject Offers");
            JButton p2OK = new  JButton("Accept Offer 1");
            JButton p3OK = new JButton("Accept Offer 2");
            JButton p4OK = new JButton("Accept Offer 3");
            
            buttonPanel.setLayout(new GridLayout(1,4,5,0));
            rejectPanel.add(reject);
            p2Panel.add(p2OK);
            p3Panel.add(p3OK);
            p4Panel.add(p4OK);
            buttonPanel.add(rejectPanel);
            buttonPanel.add(p2Panel);
            buttonPanel.add(p3Panel);
            buttonPanel.add(p4Panel);
            
            if (curPlayer.getID() < size)
            {
                temp = GameState.players.get(curPlayer.getID());
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p2OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            
            if (temp.getID() < size)
            {
                temp = GameState.players.get(temp.getID());
                p3OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p3OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            
            if (temp.getID() < size)
            {
                temp = GameState.players.get(temp.getID());
                p4OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            else
            {
                temp = GameState.players.getFirst();
                p4OK.setToolTipText("Player " + temp.getName() + "'s trade offer.");
            }
            reject.setToolTipText("Reject all offers.");
            
            reject.addActionListener(this);
            reject.setActionCommand("Reject");
            p2OK.addActionListener(this);
            p2OK.setActionCommand("P2");
            p3OK.addActionListener(this);
            p3OK.setActionCommand("P3");
            p4OK.addActionListener(this);
            p4OK.setActionCommand("P4");
        }
    }
}