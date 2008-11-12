package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import settlers.game.*;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class RobberRemoveCardsWindow implements ActionListener
{

    private JFrame frame = new JFrame("Robber Lose Cards Window");
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
                comboBoxPanels[currentPlayer].add(resourceComboBox[i]);
            }


            JButton openPanel = new JButton("Display Panel");
            openPanel.addActionListener(this);
            openPanel.setActionCommand("Next");

            JButton accept = new JButton("Accept Info");
            accept.addActionListener(this);
            accept.setActionCommand("Accept");


            JLabel message = new JLabel(player.getName() + ", please remove " + numberOfCardsToLose[currentPlayer] + " resources.");
            hidePanels[currentPlayer].add(message, BorderLayout.CENTER);
            hidePanels[currentPlayer].add(openPanel, BorderLayout.SOUTH);
            framePanel.add(hidePanels[currentPlayer], "hidePanel" + currentPlayer);

            JLabel playerName = new JLabel(player.getName());
            contentPanels[currentPlayer].add(playerName, BorderLayout.NORTH);
            contentPanels[currentPlayer].add(comboBoxPanels[currentPlayer], BorderLayout.CENTER);
            contentPanels[currentPlayer].add(accept, BorderLayout.SOUTH);
            framePanel.add(contentPanels[currentPlayer], "contentPanel" + currentPlayer);

            currentPlayer++;
        }

        frame.getContentPane().add(framePanel);
        frame.setVisible(true);
        frame.requestFocus();
        frame.setResizable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(600,300);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "Next")
        {
            System.out.println("ThunderCats, ho!");
            cardLayout.next(framePanel);
        }
        else if(e.getActionCommand() == "Accept")
        {

            Component[] components = framePanel.getComponents();
            JPanel currentPanel = (JPanel) components[(currentPanelNumber * 2) + 1];
            JPanel comboBoxPanel = (JPanel) currentPanel.getComponent(1);
            Component[] boxPanelComponents = comboBoxPanel.getComponents();

            int sum = 0;
            System.out.println("Must select " + numberOfCardsToLose[currentPanelNumber] + " cards");
            for (int i = 0; i < selectedBoxValues[currentPanelNumber].length; sum += selectedBoxValues[currentPanelNumber][i], i++);

            if (sum == numberOfCardsToLose[currentPanelNumber])
            {
                Player player = playerQueue.poll();
                for (int i = 5; i < boxPanelComponents.length; i++)
                {
                    JComboBox comboBox = (JComboBox) boxPanelComponents[i];
                    Integer selectedValue = (Integer) comboBox.getSelectedItem();

                    player.alterResource(i - 4, selectedValue.intValue(), 1);
                }

                String title = new String("Settlers of Catan");
                String message = new String("You have lost the following resources:\n");

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
                String title = new String("Settlers of Catan");
                String message = new String("You have selected too many cards. Please select only " + numberOfCardsToLose[currentPanelNumber] + " resources to remove.");

                createDialogBox(title, message, JOptionPane.ERROR_MESSAGE);
            }
            else if (sum < numberOfCardsToLose[currentPanelNumber])
            {
                String title = new String("Settlers of Catan");
                String message = new String("You have not selected enough cards. Please select " + numberOfCardsToLose[currentPanelNumber] + " resources to remove.");

                createDialogBox(title, message, JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getActionCommand() == "Select")
        {
            JComboBox box = (JComboBox) e.getSource();
            int selectedIndex = box.getSelectedIndex();

            Component[] components = framePanel.getComponents();
            JPanel currentPanel = (JPanel) components[(currentPanelNumber * 2) + 1];
            JPanel comboBoxPanel = (JPanel) currentPanel.getComponent(1);
            Component[] boxPanelComponents = comboBoxPanel.getComponents();
            int index = 5;
            for (int i = 5; i < boxPanelComponents.length; i++)
            {
                JComboBox comboBox = (JComboBox) boxPanelComponents[i];
                if (comboBox == box)
                {
                    index = i;
                    break;
                }
            }

            selectedBoxValues[currentPanelNumber][index - 5] = selectedIndex;
            System.out.println(selectedIndex);

        }
    }

    private void setLabelImages()
    {
        for (int i = 1; i < images.length; i++)
        {
            images[i] = new JLabel();

            if (i == GlobalVar.WOOD)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wood3.jpg")));
            }
            else if (i == GlobalVar.BRICK)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg")));
            }
            else if (i == GlobalVar.WHEAT)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg")));
            }
            else if (i == GlobalVar.SHEEP)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg")));
            }
            else if (i == GlobalVar.ORE)
            {
                images[i].setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg")));
            }

        }
    }

    public void closeWindow()
    {
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        RobberTakeCardWindow rtcw = new RobberTakeCardWindow();
        frame.setVisible(false);
    }

    public void createDialogBox(String title, String message, int messageType)
    {
        JOptionPane dialog = new JOptionPane();
        dialog.showMessageDialog(frame, message, title, messageType);
    }

}