package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.Event;

public class RobberRemoveCardsWindow implements ActionListener
{

    private JFrame frame = new JFrame("Discard Half of All Resources - Settlers of Catan");
    private JComboBox[] resourceComboBox;
    private Integer[][] comboBoxOptions;
    private JLabel[] images = new JLabel[6];

    private CardLayout cardLayout = new CardLayout();
    private JPanel[] comboBoxPanels;
    private JPanel[] contentPanels, hidePanels;
    private JPanel framePanel = new JPanel(cardLayout);

    private ConcurrentLinkedQueue<Player> playerQueue = new ConcurrentLinkedQueue<Player>();

    private int numberOfPlayersInQueue = 0, currentPanelNumber = 0;
    private int[] numberOfCardsToLose;
    private int[][] selectedBoxValues;

    public RobberRemoveCardsWindow()
    {

        int counter = 0;
        for (Player player : GameState.players)
        {
            if (player.getWood() + player.getBrick() + player.getWheat() + player.getSheep() + player.getOre() > 7){
                numberOfPlayersInQueue++;
                playerQueue.add(player);
            }
        }

        if (playerQueue.isEmpty())
        {
            closeWindow();
        }

        numberOfCardsToLose = new int[numberOfPlayersInQueue];
        comboBoxPanels = new JPanel[numberOfPlayersInQueue];
        contentPanels = new JPanel[numberOfPlayersInQueue];
        hidePanels = new JPanel[numberOfPlayersInQueue];
        selectedBoxValues = new int[numberOfPlayersInQueue][5];

        int currentPlayer = 0;

        for (Player player : playerQueue)
        {

            setLabelImages();

            numberOfCardsToLose[currentPlayer] = ((player.getWood() + player.getBrick() + player.getWheat() + player.getSheep() + player.getOre()) / 2);

            contentPanels[currentPlayer] = new JPanel(new BorderLayout());
            hidePanels[currentPlayer] = new JPanel(new BorderLayout());

            comboBoxPanels[currentPlayer] = new JPanel(new GridLayout(2,5));
            resourceComboBox = new JComboBox[5];
            comboBoxOptions = new Integer[5][numberOfCardsToLose[currentPlayer] + 1];

            for (int i = 0; i < comboBoxOptions.length; i++)
            {
                int resourceLimit = 1;
                switch (i + 1)
                {
                    case GlobalVar.WOOD:
                        resourceLimit = player.getWood() + 1;
                        break;
                    case GlobalVar.BRICK:
                        resourceLimit = player.getBrick() + 1;
                        break;
                    case GlobalVar.WHEAT:
                        resourceLimit = player.getWheat() + 1;
                        break;
                    case GlobalVar.SHEEP:
                        resourceLimit = player.getSheep() + 1;
                        break;
                    case GlobalVar.ORE:
                        resourceLimit = player.getOre() + 1;
                        break;
                    default:
                        resourceLimit = comboBoxOptions[i].length;
                        break;
                }
                comboBoxOptions[i] = new Integer[resourceLimit];
                for(int j = 0; j < comboBoxOptions[i].length; j++)
                {
                    int limit = 0;

                    switch ((j + 1))
                    {
                        case GlobalVar.WOOD: break;
                    }

                    comboBoxOptions[i][j] = new Integer(j);
                }
            }
            for (int i = 1; i < images.length; i++)
            {
                comboBoxPanels[currentPlayer].add(images[i]);
            }
            for (int i = 0; i < resourceComboBox.length; i++)
            {
                resourceComboBox[i] = new JComboBox(comboBoxOptions[i]);
                resourceComboBox[i].addActionListener(this);
                resourceComboBox[i].setActionCommand("Select");
                resourceComboBox[i].setPreferredSize(new java.awt.Dimension(62, 25));

                JPanel selectBoxPanel = new JPanel();
                selectBoxPanel.add(resourceComboBox[i]);
                comboBoxPanels[currentPlayer].add(selectBoxPanel);
            }


            JButton openPanel = new JButton("Display Panel");
            openPanel.addActionListener(this);
            openPanel.setActionCommand("Next");

            JButton remove = new JButton("Discard Resources");
            remove.addActionListener(this);
            remove.setActionCommand("Remove");

            JButton count = new JButton("Resource Count Check");
            count.addActionListener(this);
            count.setActionCommand("Count");

            JPanel hidePanel = new JPanel();
            hidePanel.add(openPanel);

            String messageText = new String(player.getName() + ", please discard " + numberOfCardsToLose[currentPlayer] + " resources.");

            JLabel message = new JLabel(messageText);

            hidePanels[currentPlayer].add(message, BorderLayout.CENTER);
            hidePanels[currentPlayer].add(hidePanel, BorderLayout.SOUTH);
            hidePanels[currentPlayer].add(Box.createHorizontalStrut(15), BorderLayout.WEST);
            framePanel.add(hidePanels[currentPlayer], "hidePanel" + currentPlayer);

            JLabel playerName = new JLabel(messageText);
            JPanel removePanel = new JPanel();
            removePanel.add(remove);
            removePanel.add(Box.createVerticalStrut(5));
            removePanel.add(count);
    
            contentPanels[currentPlayer].add(Box.createHorizontalStrut(3), BorderLayout.NORTH);
            contentPanels[currentPlayer].add(playerName, BorderLayout.NORTH);
            contentPanels[currentPlayer].add(comboBoxPanels[currentPlayer], BorderLayout.CENTER);
            contentPanels[currentPlayer].add(removePanel, BorderLayout.SOUTH);
            framePanel.add(contentPanels[currentPlayer], "contentPanel" + currentPlayer);

            currentPlayer++;
        }

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().add(framePanel);
        frame.setVisible(true);
        frame.requestFocus();
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(345,210);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "Next")
        {
            System.out.println("ThunderCats, ho!");
            cardLayout.next(framePanel);
        }
        else if(e.getActionCommand() == "Remove")
        {

            Component[] components = framePanel.getComponents();
            JPanel currentPanel = (JPanel) components[(currentPanelNumber * 2) + 1];
            JPanel comboBoxPanel = (JPanel) currentPanel.getComponent(2);
            Component[] boxPanelComponents = comboBoxPanel.getComponents();

            int sum = 0;
            for (int i = 0; i < selectedBoxValues[currentPanelNumber].length; sum += selectedBoxValues[currentPanelNumber][i], i++);

            if (sum == numberOfCardsToLose[currentPanelNumber])
            {
                Player player = playerQueue.poll();
                for (int i = 5; i < boxPanelComponents.length; i++)
                {
                    JPanel innerComboBoxPanel = (JPanel) boxPanelComponents[i];
                    JComboBox comboBox = (JComboBox) innerComboBoxPanel.getComponent(0);
                    Integer selectedValue = (Integer) comboBox.getSelectedItem();

                    player.alterResource(i - 4, selectedValue.intValue(), 1);
                }

                Event event = new Event("THIEF_DISCARD_RESOURCES");
                EventManager.callEvent(event);

                String title = new String(player.getName() + " - Settlers of Catan");
                String message = new String(player.getName() + ", you have discarded the following resources:\n");

                for (int i = 0; i < selectedBoxValues[currentPanelNumber].length; i++)
                {

                    if (selectedBoxValues[currentPanelNumber][i] == 0)
                    {
                        continue;
                    }

                    message = message.concat("* " + selectedBoxValues[currentPanelNumber][i]);

                    switch (i + 1)
                    {
                        case GlobalVar.WOOD:
                            message = message.concat(" Wood");
                            break;
                        case GlobalVar.BRICK:
                            message = message.concat(" Brick");
                            break;
                        case GlobalVar.WHEAT:
                            message = message.concat(" Wheat");
                            break;
                        case GlobalVar.SHEEP:
                            message = message.concat(" Sheep");
                            break;
                        case GlobalVar.ORE:
                            message = message.concat(" Ore");
                            break;
                        default:
                            break;
                    }
                    message = message.concat("\n");
                }

                createDialogBox(title, message, JOptionPane.INFORMATION_MESSAGE);

                currentPanelNumber++;

                if (currentPanelNumber >= numberOfPlayersInQueue)
                {
                    closeWindow();
                }
                else
                {
                    cardLayout.next(framePanel);
                }

            }
            else if (sum > numberOfCardsToLose[currentPanelNumber])
            {
                String title = new String("Discard Resources - Settlers of Catan");
                String message = new String("You have selected too many resources. Please select only " + numberOfCardsToLose[currentPanelNumber] + " resources to remove.");

                createDialogBox(title, message, JOptionPane.ERROR_MESSAGE);
            }
            else if (sum < numberOfCardsToLose[currentPanelNumber])
            {
                String title = new String("Discard Resources - Settlers of Catan");
                String message = new String("You have not selected enough resources. Please select " + numberOfCardsToLose[currentPanelNumber] + " resources to remove.");

                createDialogBox(title, message, JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getActionCommand() == "Select")
        {
            JComboBox box = (JComboBox) e.getSource();
            int selectedIndex = box.getSelectedIndex();

            Component[] components = framePanel.getComponents();
            JPanel currentPanel = (JPanel) components[(currentPanelNumber * 2) + 1];
            JPanel comboBoxPanel = (JPanel) currentPanel.getComponent(2);
            Component[] boxPanelComponents = comboBoxPanel.getComponents();
            int index = 5;
            for (int i = 5; i < boxPanelComponents.length; i++)
            {
                JPanel innerComboBoxPanel = (JPanel) boxPanelComponents[i];
                JComboBox comboBox = (JComboBox) innerComboBoxPanel.getComponent(0);
                if (comboBox == box)
                {
                    index = i;
                    break;
                }
            }

            selectedBoxValues[currentPanelNumber][index - 5] = selectedIndex;

        }
        else if(e.getActionCommand() == "Count")
        {
            int sum = 0;
            for (int i = 0; i < selectedBoxValues[currentPanelNumber].length; sum += selectedBoxValues[currentPanelNumber][i], i++);

            String message = new String("You have selected " + sum + " resource(s).\n");

            if (sum < numberOfCardsToLose[currentPanelNumber])
            {
                message = message.concat("You still need to select " + (numberOfCardsToLose[currentPanelNumber] - sum) + " resource(s).");
            }
            else if (sum > numberOfCardsToLose[currentPanelNumber])
            {
                message = message.concat("You need to de-select " + (sum - numberOfCardsToLose[currentPanelNumber]) + " resource(s).");
            }
            else
            {
                message = message.concat("You have selected enough resources to discard.");
            }

            createDialogBox("Resource Count Check - Settlers of Catan", message, JOptionPane.INFORMATION_MESSAGE);

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
                toolTip = "Wood";
            }
            else if (i == GlobalVar.BRICK)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg")));
                toolTip = "Brick";
            }
            else if (i == GlobalVar.WHEAT)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg")));
                toolTip = "Wheat";
            }
            else if (i == GlobalVar.SHEEP)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg")));
                toolTip = "Sheep";
            }
            else if (i == GlobalVar.ORE)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg")));
                toolTip = "Ore";
            }

            images[i].setToolTipText(toolTip);

        }
    }

    public void closeWindow()
    {
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(false);
        GameState.setActionState(GlobalVar.ACTION_MOVE_ROBBER); 
    }

    public void createDialogBox(String title, String message, int messageType)
    {
        JOptionPane dialog = new JOptionPane();
        dialog.showMessageDialog(frame, message, title, messageType);
    }

}