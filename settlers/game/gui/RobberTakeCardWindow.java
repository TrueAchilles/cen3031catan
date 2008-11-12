package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.ListIterator;
import java.util.Enumeration;

import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class RobberTakeCardWindow implements ActionListener
{

    private JFrame frame = new JFrame("Robber Window");
    private ButtonGroup selectUserRadioGroup = new ButtonGroup();
    private JPanel radioPanel = new JPanel(new GridLayout(4,1));
    private JPanel framePanel = new JPanel(new BorderLayout());
    private JButton accept = new JButton("Take User\'s Card");

    private Player curPlayer = GameState.getCurPlayer();

    public RobberTakeCardWindow()
    {

        ListIterator<Player> playersIterator = GameState.players.listIterator(0);
        JRadioButton[] radioButtons = new JRadioButton[GameState.players.size() - 1];

        int counter = 0;
        while (playersIterator.hasNext())
        {
            Player player = playersIterator.next();

            if (curPlayer.getName() == player.getName())
            {
                continue;
            }

            radioButtons[counter] = new JRadioButton(player.getName());
            selectUserRadioGroup.add(radioButtons[counter]);
            radioPanel.add(radioButtons[counter]);
            counter++;
        }

        accept.setSize(50,100);
        accept.addActionListener(this);

        framePanel.add(radioPanel, BorderLayout.WEST);
        framePanel.add(accept, BorderLayout.SOUTH);
        frame.getContentPane().add(framePanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.requestFocus();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(150,150);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == accept)
        {
            Enumeration<AbstractButton> buttonElements = selectUserRadioGroup.getElements();
            boolean radioWasSelected = false;

            JRadioButton selected = new JRadioButton();
            while (buttonElements.hasMoreElements())
            {
                
                selected = (JRadioButton) buttonElements.nextElement();
                if (selected.isSelected() == true)
                {
                    radioWasSelected = true;
                    break;
                }
            }

            if (radioWasSelected != true)
            {
                // popup window with this message:
                // "No player was selected. Please select a player.";
                return;
            }

            ListIterator<Player> playersIterator = GameState.players.listIterator(0);
            Player player = null;
            while (playersIterator.hasNext())
            {
                player = playersIterator.next();
                if (selected.getText() == player.getName())
                {
                    break;
                }
            }


            String resource = diceRoll(player, curPlayer);

            // add code for popup here describing what resource was gained/lost
            // text of window is as follows:

            String message = new String();

            if (!resource.equals("none"))
            {
                message = new String("You have taken one " + resource + " from " + player.getName());
            }
            else
            {
                message = new String(player.getName() + "has no resources to give.");
            }

            String title = new String("Settlers of Catan");
            createDialogBox(title, message, JOptionPane.INFORMATION_MESSAGE);

            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(false);

        }
    }

    private String diceRoll(Player player, Player curPlayer)
    {
        boolean breakLoop = false;
        int rType = -1;
        String resource = " ";

        if (player.getWood() == 0 && player.getBrick() == 0 && player.getWheat() == 0 && player.getSheep() == 0 && player.getOre() == 0)
        {
            return resource = new String("none");
        }

        do {
            rType = dice.roll(1);

            switch (rType)
            {
                case GlobalVar.WOOD:
                    if (player.getWood() == 0)
                    {
                        continue;
                    }
                    resource = "Wood";
                    break;
                case GlobalVar.BRICK:
                    if (player.getWood() == 0)
                    {
                        continue;
                    }
                    resource = "Brick";
                    break;
                case GlobalVar.WHEAT:
                    if (player.getWood() == 0)
                    {
                        continue;
                    }
                    resource = "Wheat";
                    break;
                case GlobalVar.SHEEP:
                    if (player.getWood() == 0)
                    {
                        continue;
                    }
                    resource = "Sheep";
                    break;
                case GlobalVar.ORE:
                    if (player.getWood() == 0)
                    {
                        continue;
                    }
                    resource = "Ore";
                    break;
            }

            breakLoop = true;

        }
        while (breakLoop == false);

        player.subtractResource(rType);
        curPlayer.giveResource(rType);
        return resource;

    }

    public void createDialogBox(String title, String message, int messageType)
    {
        JOptionPane dialog = new JOptionPane();
        dialog.showMessageDialog(frame, message, title, messageType);
    }

}