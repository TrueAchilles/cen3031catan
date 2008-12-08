package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.Event;

public class YearOfPlentyPanel extends JFrame implements ActionListener
{

    private JFrame yearOfPlentyFrame = new JFrame("Year Of Plenty");
   
    private JComboBox[] resourceComboBoxes = new JComboBox[6]; 
    private JLabel[] images = new JLabel[6];
    private JPanel picksAndImages = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel imagePanel = new JPanel();
    private JPanel pickerPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();
    private JPanel framePanel = new JPanel(cardLayout);

    private JButton acceptSelection;
    private JButton cancelYear;
    
    private int numberOfResourcesSelected = 0;


    private String[] maxChoices = { "0", "1", "2"};


    public YearOfPlentyPanel()
    {
        setLabelImages();
        initSetComboBoxes();
       
        acceptSelection = new JButton("Accept");
        acceptSelection.addActionListener(this);
        acceptSelection.setActionCommand("accept");
        cancelYear = new JButton("Cancel");
        cancelYear.addActionListener(this);
        cancelYear.setActionCommand("cancel");
       
        buttonPanel.add(acceptSelection);
        buttonPanel.add(cancelYear);
       
        String messageText  = new String(GameState.getCurPlayer() + ", you may select two, in any combination, resources.");
        JLabel message = new JLabel(messageText, 0);
       
        for (int i = 1; i < images.length; i++)
        {
            imagePanel.add(images[i]);
        }  
        for (int i = 1; i < resourceComboBoxes.length; i++)
        {
            pickerPanel.add(resourceComboBoxes[i]);
        }          
               
        picksAndImages.add(imagePanel, BorderLayout.NORTH);
        picksAndImages.add(pickerPanel, BorderLayout.SOUTH);
       
        yearOfPlentyFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        yearOfPlentyFrame.add(message, BorderLayout.NORTH);
        yearOfPlentyFrame.add(picksAndImages, BorderLayout.CENTER);
        yearOfPlentyFrame.add(buttonPanel, BorderLayout.SOUTH);
       
        yearOfPlentyFrame.setVisible(true);
        yearOfPlentyFrame.requestFocus();
        yearOfPlentyFrame.setResizable(false);
        yearOfPlentyFrame.pack();
        yearOfPlentyFrame.setLocationRelativeTo(null);
        yearOfPlentyFrame.setSize(345,210);
    }
   
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("1"))
        {
            if (resourceComboBoxes[1].getSelectedItem().toString().equals("0"))
            {
                setComboBoxes(1, 0);
            }
            else if (resourceComboBoxes[1].getSelectedItem().toString().equals("1"))
            {
                setComboBoxes(1, 1);
            }
            else if (resourceComboBoxes[1].getSelectedItem().toString().equals("2"))
            {
                setComboBoxes(1, 2);
            }
        }
        else if (e.getActionCommand().equals("2"))
        {
            if (resourceComboBoxes[2].getSelectedItem().toString().equals("0"))
            {
                setComboBoxes(2, 0);
            }
            else if (resourceComboBoxes[2].getSelectedItem().toString().equals("1"))
            {
                setComboBoxes(2, 1);
            }
            else if (resourceComboBoxes[2].getSelectedItem().toString().equals("2"))
            {
                setComboBoxes(2, 2);
            }
        }
        else if (e.getActionCommand().equals("3"))
        {
            if (resourceComboBoxes[3].getSelectedItem().toString().equals("0"))
            {
                setComboBoxes(3,0);
            }
            else if (resourceComboBoxes[3].getSelectedItem().toString().equals("1"))
            {
                setComboBoxes(3, 1);
            }
            else if (resourceComboBoxes[3].getSelectedItem().toString().equals("2"))
            {
                setComboBoxes(3, 2);
            }
        }
        else if (e.getActionCommand().equals("4"))
        {  
            if (resourceComboBoxes[4].getSelectedItem().toString().equals("0"))
            {
                setComboBoxes(4,0);
            }
            else if (resourceComboBoxes[4].getSelectedItem().toString().equals("1"))
            {
                setComboBoxes(4, 1);
            }
            else if (resourceComboBoxes[4].getSelectedItem().toString().equals("2"))
            {
                setComboBoxes(4, 2);
            }
        }
        else if (e.getActionCommand().equals("5"))
        {
            if (resourceComboBoxes[5].getSelectedItem().toString().equals("0"))
            {
                setComboBoxes(5,0);
            }
            else if (resourceComboBoxes[5].getSelectedItem().toString().equals("1"))
            {
                setComboBoxes(5, 1);
            }
            else if (resourceComboBoxes[5].getSelectedItem().toString().equals("2"))
            {
                setComboBoxes(5, 2);
            }
        }
        else if (e.getActionCommand().equals("accept"))
        {
            Event evt = new Event("PLAYER_ACCEPT_YEAR");
            EventManager.callEvent(evt);
        }
        else if (e.getActionCommand().equals("cancel"))
        {
            Event evt = new Event("PLAYER_CANCEL_YEAR");
            EventManager.callEvent(evt);
        }
    }
   
    private void initSetComboBoxes()
    {
        for (int i = 1; i < resourceComboBoxes.length; i++)
        {
            resourceComboBoxes[i] = new JComboBox(maxChoices);
            resourceComboBoxes[i].setPreferredSize(new java.awt.Dimension(62, 25));
            resourceComboBoxes[i].addActionListener(this);
            resourceComboBoxes[i].setActionCommand(Integer.toString(i));

        }
    }
   
    private void setComboBoxes(int selectedBox, int selectedValue)
    {
        System.out.println("Number of Resources selected = " + numberOfResourcesSelected);
        for (int i = 1; i < resourceComboBoxes.length; i++)
        {
            if (i != selectedBox)
            {
                System.out.println(i);
               
               ActionListener[] al = resourceComboBoxes[i].getActionListeners();
               resourceComboBoxes[i].removeActionListener(al[0]);
               resourceComboBoxes[i].removeAllItems();
               
                if (selectedValue == 0)
                {

                    resourceComboBoxes[i].addItem("0");
                    resourceComboBoxes[i].addItem("1");
                    resourceComboBoxes[i].addItem("2");
                }
                else if (selectedValue == 1)
                {
                    resourceComboBoxes[i].addItem("0");
                    resourceComboBoxes[i].addItem("1");
                }
                else if (selectedValue == 2)
                {
                    resourceComboBoxes[i].addItem("0");
                }
               
                resourceComboBoxes[i].addActionListener(this);
                resourceComboBoxes[i].setActionCommand(Integer.toString(i));


            }
            else
            {
                System.out.println("Skipped box: " + i);
            }
        }
    }
     
   
   
    private void setLabelImages()
    {
        for (int i = 1; i < images.length; i++)
        {
            images[i] = new JLabel();
            String toolTip = new String();

            if (i == GlobalVar.WOOD)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wood3.jpg")));
                toolTip = ("<html>Wood<br>" +
                          "You have: " + GameState.getCurPlayer().getWood());
            }
            else if (i == GlobalVar.BRICK)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg")));
                toolTip = ("<html>Brick<br>" +
                          "You have: " + GameState.getCurPlayer().getBrick());
            }
            else if (i == GlobalVar.WHEAT)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg")));
                toolTip = ("<html>Wheat<br>" +
                          "You have: " + GameState.getCurPlayer().getWheat());
            }
            else if (i == GlobalVar.SHEEP)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg")));
                toolTip = ("<html>Sheep<br>" +
                          "You have: " + GameState.getCurPlayer().getSheep());
            }
            else if (i == GlobalVar.ORE)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg")));
                toolTip = ("<html>Ore<br>" +
                          "You have: " + GameState.getCurPlayer().getOre());
            }

            images[i].setToolTipText(toolTip);

        }
    }

    public void closeWindow()
    {
        yearOfPlentyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        yearOfPlentyFrame.setVisible(false);
    }    
}

