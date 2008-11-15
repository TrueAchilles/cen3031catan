package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.*;

public class PlayerTrade implements ActionListener
{
    JFrame queryFrame, tradeFrame;
    JButton offer;
    JPanel buttonPanel, addPanel, removePanel, offerPanel;
    JLabel status = new JLabel(GameState.getCurPlayer().getName() + ": Select resources to add or remove from trade", JLabel.CENTER);
    TradeImage p1BrickImage = new TradeImage(GlobalVar.BRICK);
    TradeImage p1OreImage = new TradeImage(GlobalVar.ORE);
    TradeImage p1SheepImage = new TradeImage(GlobalVar.SHEEP);
    TradeImage p1WheatImage = new TradeImage(GlobalVar.WHEAT);
    TradeImage p1WoodImage = new TradeImage(GlobalVar.WOOD);
    TradeImage p2BrickImage = new TradeImage(GlobalVar.BRICK);
    TradeImage p2OreImage = new TradeImage(GlobalVar.ORE);
    TradeImage p2SheepImage = new TradeImage(GlobalVar.SHEEP);
    TradeImage p2WheatImage = new TradeImage(GlobalVar.WHEAT);
    TradeImage p2WoodImage = new TradeImage(GlobalVar.WOOD);
    
    int size, tradeID;
    int p1Brick, p2Brick = 0;
    int p1Ore, p2Ore = 0;
    int p1Sheep, p2Sheep = 0;
    int p1Wheat, p2Wheat = 0;
    int p1Wood, p2Wood = 0;

    public PlayerTrade(int size)
    {
        this.size = size;

        JLabel status = new JLabel("Select the player you would like to trade with:", JLabel.CENTER);
        JPanel buttonsPanel = new JPanel();
        
        queryFrame = new JFrame("Trade Partners");
        queryFrame.getContentPane().setLayout(new BorderLayout(5,5));
        queryFrame.getContentPane().add(status, BorderLayout.NORTH);
        queryFrame.getContentPane().add(buttonsPanel, BorderLayout.CENTER);
        
        if (size < 5)
        {
            JButton playerButtons[] = new JButton[size];
            JPanel playerPanels[] = new JPanel[size];
        
            buttonsPanel.setLayout(new GridLayout(4,1,0,5));

            for (int i = 0; i < size; i++)
            {
                playerButtons[i] = new JButton(GameState.players.get(i).getName());
                playerPanels[i] = new JPanel();
                
                playerPanels[i].add(playerButtons[i]);
                buttonsPanel.add(playerPanels[i]);
                playerButtons[i].addActionListener(this);
                playerButtons[i].setActionCommand("P" + (i+1));
                
                if (GameState.getCurPlayer() == GameState.players.get(i))
                {
                    playerButtons[i].setEnabled(false);
                }
            }
        }
        else
        {
            JButton playerButtons[] = new JButton[size];
            JPanel playerPanels[] = new JPanel[size];
            
            buttonsPanel.setLayout(new GridLayout(4,2,5,5));
            
            for (int i = 0; i < size; i++)
            {
                playerButtons[i] = new JButton(GameState.players.get(i).getName());
                playerPanels[i] = new JPanel();
                
                playerPanels[i].add(playerButtons[i]);
                buttonsPanel.add(playerPanels[i]);
                playerButtons[i].addActionListener(this);
                playerButtons[i].setActionCommand("P" + (i+1));
                
                if (GameState.getCurPlayer() == GameState.players.get(i))
                {
                    playerButtons[i].setEnabled(false);
                }
            }
        }
        
        queryFrame.setResizable(false);
        queryFrame.setVisible(true);
        queryFrame.pack();
    }
    
    public void PlayerTrade(boolean test, int tradeID)
    {
        this.tradeID = tradeID;
    
        tradeFrame = new JFrame("P2P Trading");
        JPanel resourcesPanel = new JPanel();
        JPanel p1Resources = new JPanel();
        JPanel p2Resources = new JPanel();
        addPanel = new JPanel();
        removePanel = new JPanel();
        offerPanel = new JPanel();
        buttonPanel = new JPanel();
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        offer = new JButton("Offer >");
        
        Player p1 = GameState.getCurPlayer();
        Player p2 = GameState.players.get(tradeID - 1);
        
        p1BrickImage.setText("Brick: x" + p1Brick);
        p2BrickImage.setText("Brick: x" + p2Brick);
        p1OreImage.setText("Ore: x" + p1Ore);
        p2OreImage.setText("Ore: x" + p2Ore);
        p1SheepImage.setText("Sheep: x" + p1Sheep);
        p2SheepImage.setText("Sheep: x" + p2Sheep);
        p1WheatImage.setText("Wheat: x" + p1Wheat);
        p2WheatImage.setText("Wheat: x" + p2Wheat);
        p1WoodImage.setText("Wood: x" + p1Wood);
        p2WoodImage.setText("Wood: x" + p2Wood);
        
        p1Resources.setLayout(new GridLayout(5,1,0,5));
        p1Resources.add(p1BrickImage);
        p1Resources.add(p1OreImage);
        p1Resources.add(p1SheepImage);
        p1Resources.add(p1WheatImage);
        p1Resources.add(p1WoodImage);
        p2Resources.setLayout(new GridLayout(5,1,0,5));
        p2Resources.add(p2BrickImage);
        p2Resources.add(p2OreImage);
        p2Resources.add(p2SheepImage);
        p2Resources.add(p2WheatImage);
        p2Resources.add(p2WoodImage);
        
        p2BrickImage.setEnabled(false);
        p2OreImage.setEnabled(false);
        p2SheepImage.setEnabled(false);
        p2WheatImage.setEnabled(false);
        p2WoodImage.setEnabled(false);
        
        TitledBorder p1Border = new TitledBorder(new LineBorder(Color.black), "P" + (p1.getID()) + " Resources");
        p1Border.setTitleColor(Color.black);
        p1Resources.setBorder(p1Border);
        TitledBorder p2Border = new TitledBorder(new LineBorder(Color.black), "P" + (p2.getID()) + " Resources");
        p2Border.setTitleColor(Color.black);
        p2Resources.setBorder(p2Border);
        
        resourcesPanel.setLayout(new GridLayout(1,2,10,0));
        resourcesPanel.add(p1Resources);
        resourcesPanel.add(p2Resources);
        
        buttonPanel.setLayout(new GridLayout(1,3,5,0));
        addPanel.add(add);
        add.addActionListener(this);
        add.setActionCommand("Add");
        add.setToolTipText("Adds 1 to each selected resource");
        removePanel.add(remove);
        remove.addActionListener(this);
        remove.setActionCommand("Remove");
        remove.setToolTipText("Subtracts 1 from each selected resource");
        offerPanel.add(offer);
        offer.addActionListener(this);
        offer.setActionCommand("Offer >");
        offer.setToolTipText("Offer selected resources for trade");
        buttonPanel.add(addPanel);
        buttonPanel.add(offerPanel);
        buttonPanel.add(removePanel);
        
        tradeFrame.getContentPane().setLayout(new BorderLayout());
        tradeFrame.getContentPane().add(resourcesPanel, BorderLayout.CENTER);
        tradeFrame.getContentPane().add(status, BorderLayout.NORTH);
        tradeFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        tradeFrame.setResizable(false);
        tradeFrame.setVisible(true);
        tradeFrame.setSize(289,427);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getActionCommand().equals("P1"))
        {
            queryFrame.dispose();
            PlayerTrade(true,1);
        }
        else if (event.getActionCommand().equals("P2"))
        {
            queryFrame.dispose();
            PlayerTrade(true,2);
        }
        else if (event.getActionCommand().equals("P3"))
        {
            queryFrame.dispose();
            PlayerTrade(true,3);
        }
        else if (event.getActionCommand().equals("P4"))
        {
            queryFrame.dispose();
            PlayerTrade(true,4);
        }
        else if (event.getActionCommand().equals("P5"))
        {
            queryFrame.dispose();
            PlayerTrade(true,5);
        }
        else if (event.getActionCommand().equals("P6"))
        {
            queryFrame.dispose();
            PlayerTrade(true,6);
        }
        else if (event.getActionCommand().equals("P7"))
        {
            queryFrame.dispose();
            PlayerTrade(true,7);
        }
        else if (event.getActionCommand().equals("P8"))
        {
            queryFrame.dispose();
            PlayerTrade(true,8);
        }
        else if (event.getActionCommand().equals("Add"))
        {
            if (p1BrickImage.getClicked() == true)
            {
                p1Brick++;
                p1BrickImage.setText("Brick: x" + p1Brick);
            }
            if (p1OreImage.getClicked() == true)
            {
                p1Ore++;
                p1OreImage.setText("Ore: x" + p1Ore);
            }
            if (p1SheepImage.getClicked() == true)
            {
                p1Sheep++;
                p1SheepImage.setText("Sheep: x" + p1Sheep);
            }
            if (p1WheatImage.getClicked() == true)
            {
                p1Wheat++;
                p1WheatImage.setText("Wheat: x" + p1Wheat);
            }
            if (p1WoodImage.getClicked() == true)
            {
                p1Wood++;
                p1WoodImage.setText("Wood: x" + p1Wood);
            }
            if (p2BrickImage.getClicked() == true)
            {
                p2Brick++;
                p2BrickImage.setText("Brick: x" + p2Brick);
            }
            if (p2OreImage.getClicked() == true)
            {
                p2Ore++;
                p2OreImage.setText("Ore: x" + p2Ore);
            }
            if (p2SheepImage.getClicked() == true)
            {
                p2Sheep++;
                p2SheepImage.setText("Sheep: x" + p2Sheep);
            }
            if (p2WheatImage.getClicked() == true)
            {
                p2Wheat++;
                p2WheatImage.setText("Wheat: x" + p2Wheat);
            }
            if (p2WoodImage.getClicked() == true)
            {
                p2Wood++;
                p2WoodImage.setText("Wood: x" + p2Wood);
            }
        }
        else if (event.getActionCommand().equals("Remove"))
        {
            if (p1BrickImage.getClicked() == true)
            {
                if (p1Brick > 0)
                {
                    p1Brick--;
                    p1BrickImage.setText("Brick: x" + p1Brick);
                }
            }
            if (p1OreImage.getClicked() == true)
            {
                if (p1Ore > 0)
                {
                    p1Ore--;
                    p1OreImage.setText("Ore: x" + p1Ore);
                }
            }
            if (p1SheepImage.getClicked() == true)
            {
                if (p1Sheep > 0)
                {
                    p1Sheep--;
                    p1SheepImage.setText("Sheep: x" + p1Sheep);
                }
            }
            if (p1WheatImage.getClicked() == true)
            {
                if (p1Wheat > 0)
                {
                    p1Wheat--;
                    p1WheatImage.setText("Wheat: x" + p1Wheat);
                }
            }
            if (p1WoodImage.getClicked() == true)
            {
                if (p1Wood > 0)
                {
                    p1Wood--;
                    p1WoodImage.setText("Wood: x" + p1Wood);
                }
            }
            if (p2BrickImage.getClicked() == true)
            {
                if (p2Brick > 0)
                {
                    p2Brick--;
                    p2BrickImage.setText("Brick: x" + p2Brick);
                }
            }
            if (p2OreImage.getClicked() == true)
            {
                if (p2Ore > 0)
                {
                    p2Ore--;
                    p2OreImage.setText("Ore: x" + p2Ore);
                }
            }
            if (p2SheepImage.getClicked() == true)
            {
                if (p2Sheep > 0)
                {
                    p2Sheep--;
                    p2SheepImage.setText("Sheep: x" + p2Sheep);
                }
            }
            if (p2WheatImage.getClicked() == true)
            {
                if (p2Wheat > 0)
                {
                    p2Wheat--;
                    p2WheatImage.setText("Wheat: x" + p2Wheat);
                }
            }
            if (p2WoodImage.getClicked() == true)
            {
                if (p2Wood > 0)
                {
                    p2Wood--;
                    p2WoodImage.setText("Wood: x" + p2Wood);
                }
            }
        }
        else if (event.getActionCommand().equals("Offer >"))
        {
            p1BrickImage.setClicked(false);
            p1BrickImage.setEnabled(false);
            p1OreImage.setClicked(false);
            p1OreImage.setEnabled(false);
            p1SheepImage.setClicked(false);
            p1SheepImage.setEnabled(false);
            p1WheatImage.setClicked(false);
            p1WheatImage.setEnabled(false);
            p1WoodImage.setClicked(false);
            p1WoodImage.setEnabled(false);
            
            p2BrickImage.setEnabled(true);
            p2OreImage.setEnabled(true);
            p2SheepImage.setEnabled(true);
            p2WheatImage.setEnabled(true);
            p2WoodImage.setEnabled(true);
            
            status.setText(GameState.players.get(tradeID-1).getName() + ": Select resources to add or remove from trade");
            offer.setText("< Offer");
            offer.setActionCommand("< Offer");
        }
        else if (event.getActionCommand().equals("< Offer"))
        {
            p1BrickImage.setEnabled(true);
            p1OreImage.setEnabled(true);
            p1SheepImage.setEnabled(true);
            p1WheatImage.setEnabled(true);
            p1WoodImage.setEnabled(true);
            
            p2BrickImage.setClicked(false);
            p2OreImage.setClicked(false);
            p2SheepImage.setClicked(false);
            p2WheatImage.setClicked(false);
            p2WoodImage.setClicked(false);
            
            JPanel acceptPanel = new JPanel();
            JPanel rejectPanel = new JPanel();
            JButton accept = new JButton("Accept Offer");
            accept.addActionListener(this);
            accept.setActionCommand("Accept");
            JButton reject = new JButton("Reject Offer");
            reject.addActionListener(this);
            reject.setActionCommand("Reject");
            
            acceptPanel.add(accept);
            rejectPanel.add(reject);
            buttonPanel.remove(addPanel);
            buttonPanel.remove(offerPanel);
            buttonPanel.remove(removePanel);
            buttonPanel.setLayout(new GridLayout(1,2,5,0));
            buttonPanel.add(acceptPanel);
            buttonPanel.add(rejectPanel);
            buttonPanel.updateUI();
        }
        else if (event.getActionCommand().equals("Accept"))
        {
            Player temp = GameState.players.get(tradeID-1);
        
            for (int i = 0; i < p1Brick; i++)
            {
                GameState.getCurPlayer().subtractResource(GlobalVar.BRICK);
                temp.giveResource(GlobalVar.BRICK);
            }
            for (int i = 0; i < p1Ore; i++)
            {
                GameState.getCurPlayer().subtractResource(GlobalVar.ORE);
                temp.giveResource(GlobalVar.ORE);
            }
            for (int i = 0; i < p1Sheep; i++)
            {
                GameState.getCurPlayer().subtractResource(GlobalVar.SHEEP);
                temp.giveResource(GlobalVar.SHEEP);
            }
            for (int i = 0; i < p1Wheat; i++)
            {
                GameState.getCurPlayer().subtractResource(GlobalVar.WHEAT);
                temp.giveResource(GlobalVar.WHEAT);
            }
            for (int i = 0; i < p1Wood; i++)
            {
                GameState.getCurPlayer().subtractResource(GlobalVar.WOOD);
                temp.giveResource(GlobalVar.WOOD);
            }
            
            for (int i = 0; i < p2Brick; i++)
            {
                temp.subtractResource(GlobalVar.BRICK);
                GameState.getCurPlayer().giveResource(GlobalVar.BRICK);
            }
            for (int i = 0; i < p2Ore; i++)
            {
                temp.subtractResource(GlobalVar.ORE);
                GameState.getCurPlayer().giveResource(GlobalVar.ORE);
            }
            for (int i = 0; i < p2Sheep; i++)
            {
                temp.subtractResource(GlobalVar.SHEEP);
                GameState.getCurPlayer().giveResource(GlobalVar.SHEEP);
            }
            for (int i = 0; i < p2Wheat; i++)
            {
                temp.subtractResource(GlobalVar.WHEAT);
                GameState.getCurPlayer().giveResource(GlobalVar.WHEAT);
            }
            for (int i = 0; i < p2Wood; i++)
            {
                temp.subtractResource(GlobalVar.WOOD);
                GameState.getCurPlayer().giveResource(GlobalVar.WOOD);
            }
            
            PlayerEvent n = new PlayerEvent("PLAYER_TRADED", GameState.getCurPlayer());
            EventManager.callEvent(n);
            tradeFrame.dispose();
        }
        else if (event.getActionCommand().equals("Reject"))
        {
            tradeFrame.dispose();
        }
    }
}