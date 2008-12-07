/*
*/
package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.Event;
/**
  *The MonopolyPanel Class is in charge of displaying to the user a GUI for picking a resource 
  */
public class MonopolyPanel extends JFrame implements ActionListener
{
    //This is the overall JFrame that will hold all of the information about the Monpoly card being played
    private JFrame monopolyFrame = new JFrame("Monopoly Card Panel - Settlers of Catan");
    //Titled Border that will have the player's name and color for aesthetics
    private TitledBorder playerNameBorder;
    //Combo box that will allow the user to select which resource he/she would like to steal all of
    private JComboBox resourcesPickerBox;
    //There will a picture of the the monopoly on the card
    private JLabel monopolyPicture;
    //This will update with each resource showing how many cards the player who played the card stands to gain
    private JLabel cardsToBeGainedText;
    private JLabel cardsToBeGainedNum;
    //Buttons to accept or decline selection
    private JButton acceptSelection;
    private JButton cancelMonopoly;
    
    private String[] resourcesList = {" ", "Wood", "Brick", "Wheat", "Sheep", "Ore"};
    private int resourceType;
    private int totalOneTypeCards;

    public MonopolyPanel()
    {
        
        initMonopolyPicture();
        initComboBox();
        initCardsToBeGained();
        initButtons();
        
        Player currPlayer = GameState.getCurPlayer();
        
        ///Cant add a title border to a JFrame
        //TitledBorder currPlayerBorder = new TitledBorder(new LineBorder(currPlayer.getColor()), currPlayer.getName());
        //currPlayerBorder.setTitleColor(currPlayer.getColor());
        //monopolyFrame.setBorder(currPlayerBorder);
        
        JPanel pictureBox = new JPanel();
        JPanel resourcePicker = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel cardNumBox = new JPanel();
        
        pictureBox.setSize(90, 140);
        resourcePicker.setSize(100, 18);
        buttonPanel.setSize(100, 18);
        cardNumBox.setSize(144, 144);
        
        pictureBox.setLayout(new GridLayout(1,1));
        pictureBox.add(monopolyPicture, BorderLayout.WEST);
        
        resourcePicker.add(resourcesPickerBox, BorderLayout.NORTH);
        resourcePicker.add(cardsToBeGainedText, BorderLayout.NORTH);
        
        
        cardNumBox.add(cardsToBeGainedNum, BorderLayout.CENTER);
        
        
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(acceptSelection, BorderLayout.SOUTH);
        buttonPanel.add(cancelMonopoly, BorderLayout.SOUTH);
        
       
        monopolyFrame.add(pictureBox, BorderLayout.WEST);
        monopolyFrame.add(resourcePicker, BorderLayout.NORTH);
        monopolyFrame.add(buttonPanel, BorderLayout.SOUTH);
        monopolyFrame.add(cardNumBox, BorderLayout.CENTER);
        
        monopolyFrame.setSize(new java.awt.Dimension(275, 250));
        monopolyFrame.setVisible(true);
		monopolyFrame.setResizable(false);
        monopolyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        monopolyFrame.setLocationRelativeTo(null);
        monopolyFrame.requestFocus();
        monopolyFrame.repaint();
    
    }
    
    private void initComboBox()
    {
        resourcesPickerBox = new JComboBox(resourcesList);
        resourcesPickerBox.setSelectedIndex(0);
        resourcesPickerBox.addActionListener(this);
    }
    
    private void initCardsToBeGained()
    {
        cardsToBeGainedText = new JLabel("Cards to be Gained: ", JLabel.LEFT);
        cardsToBeGainedNum = new JLabel("00");
        cardsToBeGainedNum.setFont(new Font("Times New Roman", Font.PLAIN, 144));
        cardsToBeGainedNum.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void initButtons()
    {
        acceptSelection = new JButton("Accept");
        acceptSelection.addActionListener(this);
        cancelMonopoly = new JButton("Cancel");
        cancelMonopoly.addActionListener(this);
    }
    
    private void initMonopolyPicture()
    {
        monopolyPicture = new JLabel(new ImageIcon(getClass().getResource("/settlers/game/images/Monopoly.jpg")), JLabel.LEFT);
        monopolyPicture.setPreferredSize(new java.awt.Dimension(90, 140));
        monopolyPicture.setToolTipText("<html>The Monopoly card allows the player to choose one type of resource.<br>" +
                                                "All other players must give the player who played the card all of<br>" + 
                                                "their resource cards of that type.</html>");
    }
    
    private int cardsToBeGained(int resourceToDisp)
    {
        totalOneTypeCards = 0;
        switch(resourceToDisp)
        {
            case(0):
            {
                for (Player player : GameState.players)
                {
                    totalOneTypeCards += player.getWood();
                }
            totalOneTypeCards  -= GameState.getCurPlayer().getWood(); 
            break;
            }
            
            case(1):
            {
                for (Player player : GameState.players)
                {
                    totalOneTypeCards += player.getBrick();
                }
            totalOneTypeCards  -= GameState.getCurPlayer().getBrick();
            break;
            }

            
            case(2):
            {
                for (Player player : GameState.players)
                {
                    totalOneTypeCards += player.getWheat();
                }
            totalOneTypeCards  -= GameState.getCurPlayer().getWheat();
            break;
            }

            
            case(3):
            {
                for (Player player : GameState.players)
                {
                    totalOneTypeCards += player.getSheep();
                }
            totalOneTypeCards  -= GameState.getCurPlayer().getSheep();
            break;            
            }

            case(4):
            {
                for (Player player : GameState.players)
                {
                    totalOneTypeCards += player.getOre();
                }
            totalOneTypeCards  -= GameState.getCurPlayer().getOre();  
            break;
            }

        }
        return totalOneTypeCards;
    }
    
    public int getTotalOneTypeCards()
    {
        return totalOneTypeCards;
    }
    
    public int getResourceType()
    {
        return resourceType;
    }
    
    public void closeWindow()
    {
        monopolyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        monopolyFrame.setVisible(false);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().toString().contains("Wood"))
        {
            resourceType = GlobalVar.WOOD;
            int i = cardsToBeGained(0);
            String numToPrint = "" + i;
            cardsToBeGainedNum.setText(numToPrint);
        
        }
        else if (e.getSource().toString().contains("Brick"))
        {
            resourceType = GlobalVar.BRICK;
            int i = cardsToBeGained(1);
            String numToPrint = "" + i;
            cardsToBeGainedNum.setText(numToPrint);
        
        }
        else if (e.getSource().toString().contains("Wheat"))
        {
            resourceType = GlobalVar.WHEAT;
            int i = cardsToBeGained(2); 
            String numToPrint = "" + i;
            cardsToBeGainedNum.setText(numToPrint);
        
        }
        else if (e.getSource().toString().contains("Sheep"))
        {
            resourceType = GlobalVar.SHEEP;
            int i = cardsToBeGained(3);
            String numToPrint = "" + i;
            cardsToBeGainedNum.setText(numToPrint);
        
        }
        else if (e.getSource().toString().contains("Ore"))
        {
            resourceType = GlobalVar.ORE;
            int i = cardsToBeGained(4);
            String numToPrint = "" + i;
            cardsToBeGainedNum.setText(numToPrint);
        
        }
        else if (e.getSource() == acceptSelection)
        {
            Event evt = new Event("PLAYER_ACCEPT_MONOPOLY");
            EventManager.callEvent(evt);
        }
        else if (e.getSource() == cancelMonopoly)
        {
            Event evt = new Event("PLAYER_CANCEL_MONOPOLY");
            EventManager.callEvent(evt);
        }
    }

}