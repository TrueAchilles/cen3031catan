package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.*;

public class BankTradeWindow implements ActionListener
{
    private JFrame tradeFrame;
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

    public BankTradeWindow()
    {
        tradeFrame = new JFrame("Bank Trading");
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
        TitledBorder inBorder = new TitledBorder(new LineBorder(Color.black), "Resource being traded into bank:");
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
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getActionCommand().equals("Add"))
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
                 int resourceTotal = ((brick * 4) + (ore * 4) + (sheep * 4) + (wheat * 4) +(wood * 4));
                    
                if (brickInImage.getClicked() == false && oreInImage.getClicked() == false && sheepInImage.getClicked() == false && wheatInImage.getClicked() == false && woodInImage.getClicked() == false)
                {
                    JOptionPane.showMessageDialog(null, "You haven't selected a resource to trade into the bank!");
                }
                else
                {
                    if (brickInImage.getClicked() == true)
                    {
                        if (oreInImage.getClicked() == true || sheepInImage.getClicked() == true || wheatInImage.getClicked() == true || woodInImage.getClicked() == true)
                        {
                            JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the bank at a time!");
                        }
                        else
                        {
                            if (resourceTotal > temp.getBrick())
                            {
                                JOptionPane.showMessageDialog(null, "You don't have enough brick to trade for the selected resource(s)!");
                            }
                            else
                            {
                                int i;
                                for (i = 0; i < resourceTotal; i++)
                                {
                                    temp.subtractResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < brick; i++)
                                {
                                    temp.giveResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < ore; i++)
                                {
                                    temp.giveResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < sheep; i++)
                                {
                                    temp.giveResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < wheat; i++)
                                {
                                    temp.giveResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < wood; i++)
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
                            JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the bank at a time!");
                        }
                        else
                        {
                            if (resourceTotal > temp.getOre())
                            {
                                JOptionPane.showMessageDialog(null, "You don't have enough ore  to trade for the selected resource(s)!");
                            }
                            else
                            {
                                int i;
                                for (i = 0; i < resourceTotal; i++)
                                {
                                    temp.subtractResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < brick; i++)
                                {
                                    temp.giveResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < ore; i++)
                                {
                                    temp.giveResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < sheep; i++)
                                {
                                    temp.giveResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < wheat; i++)
                                {
                                    temp.giveResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < wood; i++)
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
                            JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the bank at a time!");
                        }
                        else
                        {
                            if (resourceTotal > temp.getSheep())
                            {
                                JOptionPane.showMessageDialog(null, "You don't have enough sheep to trade for the selected resource(s)!");
                            }
                            else
                            {
                                int i;
                                for (i = 0; i < resourceTotal; i++)
                                {
                                    temp.subtractResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < brick; i++)
                                {
                                    temp.giveResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < ore; i++)
                                {
                                    temp.giveResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < sheep; i++)
                                {
                                    temp.giveResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < wheat; i++)
                                {
                                    temp.giveResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < wood; i++)
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
                            JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the bank at a time!");
                        }
                        else
                        {
                            if (resourceTotal > temp.getWheat())
                            {
                                JOptionPane.showMessageDialog(null, "You don't have enough wheat resource to trade for the selected resource(s)!");
                            }
                            else
                            {
                                int i;
                                for (i = 0; i < resourceTotal; i++)
                                {
                                    temp.subtractResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < brick; i++)
                                {
                                    temp.giveResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < ore; i++)
                                {
                                    temp.giveResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < sheep; i++)
                                {
                                    temp.giveResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < wheat; i++)
                                {
                                    temp.giveResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < wood; i++)
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
                            JOptionPane.showMessageDialog(null, "You can only trade 1 resource into the bank at a time!");
                        }
                        else
                        {
                            if (resourceTotal > temp.getWood())
                            {
                                JOptionPane.showMessageDialog(null, "You don't have enough wood resource to trade for the selected resource(s)!");
                            }
                            else
                            {
                                int i;
                                for (i = 0; i < resourceTotal; i++)
                                {
                                    temp.subtractResource(GlobalVar.WOOD);
                                }
                                for (i = 0; i < brick; i++)
                                {
                                    temp.giveResource(GlobalVar.BRICK);
                                }
                                for (i = 0; i < ore; i++)
                                {
                                    temp.giveResource(GlobalVar.ORE);
                                }
                                for (i = 0; i < sheep; i++)
                                {
                                    temp.giveResource(GlobalVar.SHEEP);
                                }
                                for (i = 0; i < wheat; i++)
                                {
                                    temp.giveResource(GlobalVar.WHEAT);
                                }
                                for (i = 0; i < wood; i++)
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
