package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.*;

public class PortTrade implements ActionListener
{
    private JFrame queryFrame, tradeFrame;
    private TradeImage brickImage = new TradeImage(GlobalVar.BRICK);
    private TradeImage oreImage = new TradeImage(GlobalVar.ORE);
    private TradeImage sheepImage = new TradeImage(GlobalVar.SHEEP);
    private TradeImage wheatImage = new TradeImage(GlobalVar.WHEAT);
    private TradeImage woodImage = new TradeImage(GlobalVar.WOOD); 
    private TradeImage brickInImage = new TradeImage(GlobalVar.BRICK);
    private TradeImage oreInImage = new TradeImage(GlobalVar.ORE);
    private TradeImage sheepInImage = new TradeImage(GlobalVar.SHEEP);
    private TradeImage wheatInImage = new TradeImage(GlobalVar.WHEAT);
    private TradeImage woodInImage = new TradeImage(GlobalVar.WOOD);
    
    private int brick = 0;
    private int ore = 0;
    private int sheep = 0;
    private int wheat = 0;
    private int wood = 0;
    private int portID;
    
    public PortTrade()
    {
        queryFrame = new JFrame("Port Trading");
        JPanel buttonPanel = new JPanel();
        JPanel brickPanel = new JPanel();
        JPanel orePanel = new JPanel();
        JPanel sheepPanel = new JPanel();
        JPanel wheatPanel = new JPanel();
        JPanel woodPanel = new JPanel();
        JPanel multiPanel = new JPanel();
        JButton brick = new JButton("Brick Port");
        JButton ore = new JButton("Ore Port");
        JButton sheep = new JButton("Sheep Port");
        JButton wheat = new JButton("Wheat Port");
        JButton wood = new JButton("Wood Port");
        JButton multi = new JButton("Multi Port");
        JLabel status = new JLabel(GameState.getCurPlayer().getName() + ": Select the type of port you want to use", JLabel.CENTER);
    
        brick.addActionListener(this);
        ore.addActionListener(this);
        sheep.addActionListener(this);
        wheat.addActionListener(this);
        wood.addActionListener(this);
        multi.addActionListener(this);
    
        queryFrame.getContentPane().setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(3,2,5,5));
        brickPanel.add(brick);
        orePanel.add(ore);
        sheepPanel.add(sheep);
        wheatPanel.add(wheat);
        woodPanel.add(wood);
        multiPanel.add(multi);
        buttonPanel.add(brickPanel);
        buttonPanel.add(orePanel);
        buttonPanel.add(sheepPanel);
        buttonPanel.add(wheatPanel);
        buttonPanel.add(woodPanel);
        buttonPanel.add(multiPanel);
        queryFrame.getContentPane().add(status, BorderLayout.NORTH);
        queryFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        queryFrame.setVisible(true);
        queryFrame.setResizable(false);
        queryFrame.pack();
    }
    
    public void PortTrade (int type)
    {
        this.portID = type;
        
        if (portID != 6)
        {
            tradeFrame = new JFrame("Port Trading");
            JLabel status = new JLabel(GameState.getCurPlayer().getName() + ": Select the resources you want to trade for", JLabel.CENTER);
            JPanel resourcePanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel addPanel = new JPanel();
            JPanel removePanel = new JPanel();
            JPanel offerPanel = new JPanel();
            JButton add = new JButton("Add");
            JButton remove = new JButton("Remove");
            JButton offer = new JButton("Offer");

            brickImage.setText("Brick: x" + brick);
            brickImage.setVerticalTextPosition(JLabel.BOTTOM);
            brickImage.setHorizontalTextPosition(JLabel.CENTER);
            oreImage.setText("Ore: x" + ore);
            oreImage.setVerticalTextPosition(JLabel.BOTTOM);
            oreImage.setHorizontalTextPosition(JLabel.CENTER);
            sheepImage.setText("Sheep: x" + sheep);
            sheepImage.setVerticalTextPosition(JLabel.BOTTOM);
            sheepImage.setHorizontalTextPosition(JLabel.CENTER);
            wheatImage.setText("Wheat: x" + wheat);
            wheatImage.setVerticalTextPosition(JLabel.BOTTOM);
            wheatImage.setHorizontalTextPosition(JLabel.CENTER);
            woodImage.setText("Wood: x" + wood);
            woodImage.setVerticalTextPosition(JLabel.BOTTOM);
            woodImage.setHorizontalTextPosition(JLabel.CENTER);
        
            add.addActionListener(this);
            remove.addActionListener(this);
            offer.addActionListener(this);
            addPanel.add(add);
            removePanel.add(remove);
            offerPanel.add(offer);
            buttonPanel.setLayout(new GridLayout(1,3,5,0));
            buttonPanel.add(addPanel);
            buttonPanel.add(offerPanel);
            buttonPanel.add(removePanel);
        
            resourcePanel.setLayout(new GridLayout(1,5,10,0));
            resourcePanel.add(brickImage);
            resourcePanel.add(oreImage);
            resourcePanel.add(sheepImage);
            resourcePanel.add(wheatImage);
            resourcePanel.add(woodImage);
            tradeFrame.getContentPane().setLayout(new BorderLayout());
            tradeFrame.getContentPane().add(status, BorderLayout.NORTH);
            tradeFrame.getContentPane().add(resourcePanel, BorderLayout.CENTER);
            tradeFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
            tradeFrame.setVisible(true);
            tradeFrame.setResizable(false);
            tradeFrame.pack();
        }
        else
        {
            tradeFrame = new JFrame("Port Trading");
            JLabel status = new JLabel(GameState.getCurPlayer().getName() + ": Select the resources you want to trade for", JLabel.CENTER);
            JPanel centerPanel = new JPanel();
            JPanel inPanel = new JPanel();
            JPanel resourcePanel = new JPanel();
            JPanel buttonPanel = new JPanel();
            JPanel addPanel = new JPanel();
            JPanel removePanel = new JPanel();
            JPanel offerPanel = new JPanel();
            JButton add = new JButton("Add");
            JButton remove = new JButton("Remove");
            JButton offer = new JButton("Offer");

            brickImage.setText("Brick: x" + brick);
            brickImage.setVerticalTextPosition(JLabel.BOTTOM);
            brickImage.setHorizontalTextPosition(JLabel.CENTER);
            oreImage.setText("Ore: x" + ore);
            oreImage.setVerticalTextPosition(JLabel.BOTTOM);
            oreImage.setHorizontalTextPosition(JLabel.CENTER);
            sheepImage.setText("Sheep: x" + sheep);
            sheepImage.setVerticalTextPosition(JLabel.BOTTOM);
            sheepImage.setHorizontalTextPosition(JLabel.CENTER);
            wheatImage.setText("Wheat: x" + wheat);
            wheatImage.setVerticalTextPosition(JLabel.BOTTOM);
            wheatImage.setHorizontalTextPosition(JLabel.CENTER);
            woodImage.setText("Wood: x" + wood);
            woodImage.setVerticalTextPosition(JLabel.BOTTOM);
            woodImage.setHorizontalTextPosition(JLabel.CENTER);
        
            brickInImage.setText("Brick");
            brickInImage.setVerticalTextPosition(JLabel.BOTTOM);
            brickInImage.setHorizontalTextPosition(JLabel.CENTER);
            oreInImage.setText("Ore");
            oreInImage.setVerticalTextPosition(JLabel.BOTTOM);
            oreInImage.setHorizontalTextPosition(JLabel.CENTER);
            sheepInImage.setText("Sheep");
            sheepInImage.setVerticalTextPosition(JLabel.BOTTOM);
            sheepInImage.setHorizontalTextPosition(JLabel.CENTER);
            wheatInImage.setText("Wheat");
            wheatInImage.setVerticalTextPosition(JLabel.BOTTOM);
            wheatInImage.setHorizontalTextPosition(JLabel.CENTER);
            woodInImage.setText("Wood");
            woodInImage.setVerticalTextPosition(JLabel.BOTTOM);
            woodInImage.setHorizontalTextPosition(JLabel.CENTER);
        
            add.addActionListener(this);
            remove.addActionListener(this);
            offer.addActionListener(this);
            addPanel.add(add);
            removePanel.add(remove);
            offerPanel.add(offer);
            buttonPanel.setLayout(new GridLayout(1,3,5,0));
            buttonPanel.add(addPanel);
            buttonPanel.add(offerPanel);
            buttonPanel.add(removePanel);
        
            resourcePanel.setLayout(new GridLayout(1,5,10,0));
            resourcePanel.add(brickImage);
            resourcePanel.add(oreImage);
            resourcePanel.add(sheepImage);
            resourcePanel.add(wheatImage);
            resourcePanel.add(woodImage);
            TitledBorder resourceBorder = new TitledBorder(new LineBorder(Color.black), "Resource(s) being traded for:");
            resourceBorder.setTitleColor(Color.black);
            resourcePanel.setBorder(resourceBorder);
            
            inPanel.setLayout(new GridLayout(1,5,10,0));
            inPanel.add(brickInImage);
            inPanel.add(oreInImage);
            inPanel.add(sheepInImage);
            inPanel.add(wheatInImage);
            inPanel.add(woodInImage);
            TitledBorder inBorder = new TitledBorder(new LineBorder(Color.black), "Resource being traded into port:");
            inBorder.setTitleColor(Color.black);
            inPanel.setBorder(inBorder);
            
            centerPanel.setLayout(new GridLayout(2,1,0,5));
            centerPanel.add(inPanel);
            centerPanel.add(resourcePanel);
            
            tradeFrame.getContentPane().setLayout(new BorderLayout());
            tradeFrame.getContentPane().add(status, BorderLayout.NORTH);
            tradeFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);
            tradeFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            tradeFrame.setVisible(true);
            tradeFrame.setResizable(false);
            tradeFrame.pack();
        }
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if(event.getActionCommand().equals("Brick Port"))
        {
            if (GameState.getCurPlayer().getBrick() < 2)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 brick resource to use this port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(1);
            }
        }
        else if(event.getActionCommand().equals("Ore Port"))
        {
            if (GameState.getCurPlayer().getOre() < 2)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 ore resource to use this port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(2);
            }
        }
        else if(event.getActionCommand().equals("Sheep Port"))
        {
            if (GameState.getCurPlayer().getSheep() < 2)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 sheep resource to use this port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(3);
            }
        }
        else if(event.getActionCommand().equals("Wheat Port"))
        {
            if (GameState.getCurPlayer().getWheat() < 2)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 wheat resource to use this port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(4);
            }
        }
        else if(event.getActionCommand().equals("Wood Port"))
        {
            if (GameState.getCurPlayer().getWood() < 2)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 woo resource to use this port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(5);
            }
        }
        else if(event.getActionCommand().equals("Multi Port"))
        {
            Player temp = GameState.getCurPlayer();
            
            if (temp.getBrick() < 3 && temp.getOre() < 3 && temp.getSheep() < 3 && temp.getWheat() < 3 && temp.getWood() < 3)
            {
                JOptionPane.showMessageDialog(null, "You need to have at least 3 of any resource to use the multi port!");
            }
            else
            {
                queryFrame.dispose();
                PortTrade(6);
            }
        }
        else if (event.getActionCommand().equals("Add"))
        {
            if (brickImage.getClicked() == true)
            {
                brick++;
                brickImage.setText("Brick: x" + brick);
            }
            if (oreImage.getClicked() == true)
            {
                ore++;
                oreImage.setText("Ore: x" + ore);
            }
            if (sheepImage.getClicked() == true)
            {
                sheep++;
                sheepImage.setText("Sheep: x" + sheep);
            }
            if (wheatImage.getClicked() == true)
            {
                wheat++;
                wheatImage.setText("Wheat: x" + wheat);
            }
            if (woodImage.getClicked() == true)
            {
                wood++;
                woodImage.setText("Wood: x" + wood);
            }
        }
        else if (event.getActionCommand().equals("Remove"))
        {
            if (brickImage.getClicked() == true)
            {
                if (brick > 0)
                {
                    brick--;
                    brickImage.setText("Brick: x" + brick);
                }
            }
            if (oreImage.getClicked() == true)
            {
                if (ore > 0)
                {
                    ore--;
                    oreImage.setText("Ore: x" + ore);
                }
            }
            if (sheepImage.getClicked() == true)
            {
                if (sheep > 0)
                {
                    sheep--;
                    sheepImage.setText("Sheep: x" + sheep);
                }
            }
            if (wheatImage.getClicked() == true)
            {
                if (wheat > 0)
                {
                    wheat--;
                    wheatImage.setText("Wheat: x" + wheat);
                }
            }
            if (woodImage.getClicked() == true)
            {
                if (wood > 0)
                {
                    wood--;
                    woodImage.setText("Wood: x" + wood);
                }
            }
        }
        else if (event.getActionCommand().equals("Offer"))
        {
            Player temp = GameState.getCurPlayer();
            
            if (brick == 0 && ore == 0 && sheep == 0 && wheat == 0 && wood == 0)
            {
                JOptionPane.showMessageDialog(null, "You haven't selected the any resources to trade for!");
            }
            else
            {
                int resourceTotal = ((brick * 2) + (ore * 2) + (sheep * 2) + (wheat * 2) +(wood * 2));
                
                if (resourceTotal == 0)
                {
                    JOptionPane.showMessageDialog(null, "You haven't selected any resources to trade for!");
                }
                else
                {
                    if (portID == 1)
                    {                    
                        if (resourceTotal > temp.getBrick())
                        {
                            JOptionPane.showMessageDialog(null, "You don't have enough brick resource to trade for the selected resource(s)!");
                        }
                        else
                        {
                            for (int i = 0; i < resourceTotal; i++)
                            {
                                temp.subtractResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < brick; i++)
                            {
                                temp.giveResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < ore; i++)
                            {
                                temp.giveResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < sheep; i++)
                            {
                                temp.giveResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < wheat; i++)
                            {
                                temp.giveResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < wood; i++)
                            {
                                temp.giveResource(GlobalVar.WOOD);
                            }
                            
                            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                            EventManager.callEvent(n);
                            tradeFrame.dispose();
                        }
                    }
                    else if (portID == 2)
                    {
                        if (resourceTotal > temp.getOre())
                        {
                            JOptionPane.showMessageDialog(null, "You don't have enough ore resource to trade for the selected resource(s)!");
                        }
                        else
                        {
                            for (int i = 0; i < resourceTotal; i++)
                            {
                                temp.subtractResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < brick; i++)
                            {
                                temp.giveResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < ore; i++)
                            {
                                temp.giveResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < sheep; i++)
                            {
                                temp.giveResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < wheat; i++)
                            {
                                temp.giveResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < wood; i++)
                            {
                                temp.giveResource(GlobalVar.WOOD);
                            }
                            
                            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                            EventManager.callEvent(n);
                            tradeFrame.dispose();
                        }
                    }
                    else if (portID == 3)
                    {
                        if (resourceTotal > temp.getSheep())
                        {
                            JOptionPane.showMessageDialog(null, "You don't have enough sheep resource to trade for the selected resource(s)!");
                        }
                        else
                        {
                            for (int i = 0; i < resourceTotal; i++)
                            {
                                temp.subtractResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < brick; i++)
                            {
                                temp.giveResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < ore; i++)
                            {
                                temp.giveResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < sheep; i++)
                            {
                                temp.giveResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < wheat; i++)
                            {
                                temp.giveResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < wood; i++)
                            {
                                temp.giveResource(GlobalVar.WOOD);
                            }
                            
                            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                            EventManager.callEvent(n);
                            tradeFrame.dispose();
                        }
                    }
                    else if (portID == 4)
                    {
                        if (resourceTotal > temp.getWheat())
                        {
                            JOptionPane.showMessageDialog(null, "You don't have enough wheat resource to trade for the selected resource(s)!");
                        }
                        else
                        {
                            for (int i = 0; i < resourceTotal; i++)
                            {
                                temp.subtractResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < brick; i++)
                            {
                                temp.giveResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < ore; i++)
                            {
                                temp.giveResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < sheep; i++)
                            {
                                temp.giveResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < wheat; i++)
                            {
                                temp.giveResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < wood; i++)
                            {
                                temp.giveResource(GlobalVar.WOOD);
                            }
                            
                            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                            EventManager.callEvent(n);
                            tradeFrame.dispose();
                        }
                    }
                    else if (portID == 5)
                    {
                        if (resourceTotal > temp.getWood())
                        {
                            JOptionPane.showMessageDialog(null, "You don't have enough wood resource to trade for the selected resource(s)!");
                        }
                        else
                        {
                            for (int i = 0; i < resourceTotal; i++)
                            {
                                temp.subtractResource(GlobalVar.WOOD);
                            }
                            for (int i = 0; i < brick; i++)
                            {
                                temp.giveResource(GlobalVar.BRICK);
                            }
                            for (int i = 0; i < ore; i++)
                            {
                                temp.giveResource(GlobalVar.ORE);
                            }
                            for (int i = 0; i < sheep; i++)
                            {
                                temp.giveResource(GlobalVar.SHEEP);
                            }
                            for (int i = 0; i < wheat; i++)
                            {
                                temp.giveResource(GlobalVar.WHEAT);
                            }
                            for (int i = 0; i < wood; i++)
                            {
                                temp.giveResource(GlobalVar.WOOD);
                            }
                            
                            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                            EventManager.callEvent(n);
                            tradeFrame.dispose();
                        }
                    }
                    else if (portID == 6)
                    {
                        resourceTotal = ((brick * 3) + (ore * 3) + (sheep * 3) + (wheat * 3) +(wood * 3));
                    
                        if (brickInImage.getClicked() == false && oreInImage.getClicked() == false && sheepInImage.getClicked() == false && wheatInImage.getClicked() == false && woodInImage.getClicked() == false)
                        {
                            JOptionPane.showMessageDialog(null, "You haven't selected a resource to trade into the port!");
                        }
                        else
                        {
                            if (brickInImage.getClicked() == true)
                            {
                                if (oreInImage.getClicked() == true || sheepInImage.getClicked() == true || wheatInImage.getClicked() == true || woodInImage.getClicked() == true)
                                {
                                    JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the port at a time!");
                                }
                                else
                                {
                                    if (resourceTotal > temp.getBrick())
                                    {
                                        JOptionPane.showMessageDialog(null, "You don't have enough brick resource to trade for the selected resource(s)!");
                                    }
                                    else
                                    {
                                    for (int i = 0; i < resourceTotal; i++)
                                    {
                                        temp.subtractResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < brick; i++)
                                    {
                                        temp.giveResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < ore; i++)
                                    {
                                        temp.giveResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < sheep; i++)
                                    {
                                        temp.giveResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < wheat; i++)
                                    {
                                        temp.giveResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < wood; i++)
                                    {
                                        temp.giveResource(GlobalVar.WOOD);
                                    }
                            
                                    PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                                    EventManager.callEvent(n);
                                    tradeFrame.dispose();
                                    }
                                }
                            }
                            else if (oreInImage.getClicked() == true)
                            {
                                if (brickInImage.getClicked() == true || sheepInImage.getClicked() == true || wheatInImage.getClicked() == true || woodInImage.getClicked() == true)
                                {
                                    JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the port at a time!");
                                }
                                else
                                {
                                    if (resourceTotal > temp.getOre())
                                    {
                                        JOptionPane.showMessageDialog(null, "You don't have enough ore resource to trade for the selected resource(s)!");
                                    }
                                    else
                                    {
                                    for (int i = 0; i < resourceTotal; i++)
                                    {
                                        temp.subtractResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < brick; i++)
                                    {
                                        temp.giveResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < ore; i++)
                                    {
                                        temp.giveResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < sheep; i++)
                                    {
                                        temp.giveResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < wheat; i++)
                                    {
                                        temp.giveResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < wood; i++)
                                    {
                                        temp.giveResource(GlobalVar.WOOD);
                                    }
                            
                                    PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                                    EventManager.callEvent(n);
                                    tradeFrame.dispose();
                                    }
                                }
                            }
                            else if (sheepInImage.getClicked() == true)
                            {
                                if (oreInImage.getClicked() == true || brickInImage.getClicked() == true || wheatInImage.getClicked() == true || woodInImage.getClicked() == true)
                                {
                                    JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the port at a time!");
                                }
                                else
                                {
                                    if (resourceTotal > temp.getSheep())
                                    {
                                        JOptionPane.showMessageDialog(null, "You don't have enough sheep resource to trade for the selected resource(s)!");
                                    }
                                    else
                                    {
                                    for (int i = 0; i < resourceTotal; i++)
                                    {
                                        temp.subtractResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < brick; i++)
                                    {
                                        temp.giveResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < ore; i++)
                                    {
                                        temp.giveResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < sheep; i++)
                                    {
                                        temp.giveResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < wheat; i++)
                                    {
                                        temp.giveResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < wood; i++)
                                    {
                                        temp.giveResource(GlobalVar.WOOD);
                                    }
                            
                                    PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                                    EventManager.callEvent(n);
                                    tradeFrame.dispose();
                                    }
                                }
                            }
                            else if (wheatInImage.getClicked() == true)
                            {
                                if (oreInImage.getClicked() == true || sheepInImage.getClicked() == true || brickInImage.getClicked() == true || woodInImage.getClicked() == true)
                                {
                                    JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the port at a time!");
                                }
                                else
                                {
                                    if (resourceTotal > temp.getWheat())
                                    {
                                        JOptionPane.showMessageDialog(null, "You don't have enough wheat resource to trade for the selected resource(s)!");
                                    }
                                    else
                                    {
                                    for (int i = 0; i < resourceTotal; i++)
                                    {
                                        temp.subtractResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < brick; i++)
                                    {
                                        temp.giveResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < ore; i++)
                                    {
                                        temp.giveResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < sheep; i++)
                                    {
                                        temp.giveResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < wheat; i++)
                                    {
                                        temp.giveResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < wood; i++)
                                    {
                                        temp.giveResource(GlobalVar.WOOD);
                                    }
                            
                                    PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                                    EventManager.callEvent(n);
                                    tradeFrame.dispose();
                                    }
                                }
                            }
                            else if (woodInImage.getClicked() == false)
                            {
                                if (oreInImage.getClicked() == true || sheepInImage.getClicked() == true || wheatInImage.getClicked() == true || brickInImage.getClicked() == true)
                                {
                                    JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the port at a time!");
                                }
                                else
                                {
                                    if (resourceTotal > temp.getWood())
                                    {
                                        JOptionPane.showMessageDialog(null, "You don't have enough wood resource to trade for the selected resource(s)!");
                                    }
                                    else
                                    {
                                    for (int i = 0; i < resourceTotal; i++)
                                    {
                                        temp.subtractResource(GlobalVar.WOOD);
                                    }
                                    for (int i = 0; i < brick; i++)
                                    {
                                        temp.giveResource(GlobalVar.BRICK);
                                    }
                                    for (int i = 0; i < ore; i++)
                                    {
                                        temp.giveResource(GlobalVar.ORE);
                                    }
                                    for (int i = 0; i < sheep; i++)
                                    {
                                        temp.giveResource(GlobalVar.SHEEP);
                                    }
                                    for (int i = 0; i < wheat; i++)
                                    {
                                        temp.giveResource(GlobalVar.WHEAT);
                                    }
                                    for (int i = 0; i < wood; i++)
                                    {
                                        temp.giveResource(GlobalVar.WOOD);
                                    }
                            
                                    PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
                                    EventManager.callEvent(n);
                                    tradeFrame.dispose();
                                    }
                                }
                            }
                        }
                    }   
                }
            }
        }
    }
}