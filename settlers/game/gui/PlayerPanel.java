package settlers.game.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import java.awt.GridLayout;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import settlers.game.GameState;
import settlers.game.elements.Player;

public class PlayerPanel extends JPanel {
    private ArrayList<PlayerIcon> playerIcons = new ArrayList<PlayerIcon>();
    private ArrayList<JLabel> playerLabels = new ArrayList<JLabel>();
    private ArrayList<JMenuItem[]> playerHistories = new ArrayList<JMenuItem[]>();
    private ArrayList<JPopupMenu> playerPopups = new ArrayList<JPopupMenu>();

    private PlayerIcon[] array1 = new PlayerIcon[8];
    private JLabel[] array2 = new JLabel[8];
    private int index = 0;

    public PlayerPanel()
    {
        super();
        initGUI();
    }
    
    private void initGUI()
    {
        this.setPreferredSize(new java.awt.Dimension(65, 525));
        this.setSize(65, 525);
        this.setVisible(true);
        this.setLayout(new GridLayout(16, 1));

        for (int i = 0; i < 8; i++)
        {
            array1[i] = new PlayerIcon(Color.RED);
            array1[i].setVisible(false);
            array2[i] = new JLabel("Player " + (i + 1));
            array2[i].setVisible(false);
        }

    }
    
    /**
    * Auto-generated method for setting the popup menu for a component
    */
    private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu)
    {
        parent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                if(e.isPopupTrigger())
                    menu.show(parent, e.getX(), e.getY());
            }
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if(e.isPopupTrigger())
                    menu.show(parent, e.getX(), e.getY());
            }
        });
    }

    public void addPlayer(Player newPlayer)
    {
        JLabel playerLabel = new JLabel(newPlayer.getName());
        playerLabel.setPreferredSize(new java.awt.Dimension(65, 16));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        PlayerIcon playerIcon = new PlayerIcon(newPlayer.getColor());
        playerIcon.setSize(65,65);
        playerIcon.setBackground(new Color(newPlayer.getColor().getRGB()));

        array1[index].setColor(newPlayer.getColor());
        array1[index].setSize(65,65);
        array2[index].setText(newPlayer.getName());
        array2[index].setSize(65,16);
        array2[index].setHorizontalAlignment(SwingConstants.CENTER);
        array2[index].setHorizontalTextPosition(SwingConstants.CENTER);

        array1[index].setVisible(true);
        array2[index].setVisible(true);

        this.add(array2[index]);
        this.add(array1[index]);

        index++;

        playerLabels.add(playerLabel);
        playerIcons.add(playerIcon);

        JPopupMenu popup = new JPopupMenu();
        setComponentPopupMenu(playerIcon, popup);

        JMenuItem[] menuItems = new JMenuItem[2];
        menuItems[0] = new JMenuItem(newPlayer.getName() + " 's History");
        popup.add(menuItems[0]);
        menuItems[1] = new JMenuItem(newPlayer.getName() + " 's Rolls");
        popup.add(menuItems[1]);

        playerLabels.add(playerLabel);
        playerIcons.add(playerIcon);
        playerPopups.add(popup);
        playerHistories.add(menuItems);

        playerLabel.setVisible(true);
        playerIcon.setVisible(true);

//        this.add(playerLabel);
//        this.add(playerIcon);

    }

}
