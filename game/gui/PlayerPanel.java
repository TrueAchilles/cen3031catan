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
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Graphics;

import settlers.game.GameState;
import settlers.game.elements.Player;
import settlers.game.gui.PlayerAvatar;

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
//        this.setLayout(new GridLayout(16, 1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        JPanel playerInfoPanel = new JPanel();
//        playerInfoPanel.setLayout(new BoxLayout(playerInfoPanel, BoxLayout.Y_AXIS));

        JLabel playerLabel = new JLabel(newPlayer.getName());
        playerLabel.setPreferredSize(new java.awt.Dimension(65, 16));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        JPanel playerLabelPanel = new JPanel();
        playerLabelPanel.add(playerLabel);
        playerInfoPanel.add(playerLabelPanel);

        PlayerIcon playerIcon = new PlayerIcon(newPlayer.getColor());
        playerIcon.setSize(65,65);
        playerIcon.setPreferredSize(new java.awt.Dimension(65,65));
        playerIcon.setBackground(new Color(newPlayer.getColor().getRGB()));
        JPanel playerIconPanel = new JPanel();
        playerIconPanel.setSize(65,65);
        playerIconPanel.add(playerIcon);
        playerInfoPanel.add(playerIconPanel);

        if (newPlayer.getPlayerAvatar() != null)
        {
            playerIcon.setLayout(new BorderLayout());
            JLabel image = new JLabel(newPlayer.getPlayerAvatar());
            playerIcon.add(image, BorderLayout.CENTER);

        }

        this.add(playerInfoPanel);


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

        Graphics g = this.getGraphics();
        this.paintAll(g);

    }

}
