package settlers.game.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import settlers.game.GameState;
import settlers.game.elements.Player;

public class PlayerPanel extends javax.swing.JPanel {
    private PlayerIcon player1;
    private JLabel playerLabel2;
    private JPopupMenu player2_popup;
    private JMenuItem player2_roll;
    private JMenuItem player1_history;
    private JMenuItem player1_roll;
    private JMenuItem player2_history;
    private JMenuItem player3_roll;
    private JMenuItem player3_history;
    private JMenuItem player4_roll;
    private JPopupMenu player3_popup;
    private JMenuItem player4_history;
    private JPopupMenu player4_popup;
    private JPopupMenu player1_popup;
    private JLabel playerLabel4;
    private JLabel playerLabel3;
    private JLabel playerLabel1;
    private PlayerIcon player4;
    private PlayerIcon player3;
    private PlayerIcon player2;

    
    public PlayerPanel() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(65, 525));
            this.setSize(65, 525);
            this.setOpaque(false);
            AnchorLayout thisLayout = new AnchorLayout();
            this.setLayout(thisLayout);
            {
                playerLabel4 = new JLabel();
                this.add(playerLabel4, new AnchorConstraint(846, 1007, 65, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE));
                playerLabel4.setText("Player4");
                playerLabel4.setPreferredSize(new java.awt.Dimension(65, 16));
                playerLabel4.setHorizontalAlignment(SwingConstants.CENTER);
                playerLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
                playerLabel4.setVisible(false);
            }
            {
                playerLabel3 = new JLabel();
                this.add(playerLabel3, new AnchorConstraint(576, 1007, 606, 7, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                playerLabel3.setText("Player3");
                playerLabel3.setPreferredSize(new java.awt.Dimension(65, 16));
                playerLabel3.setHorizontalAlignment(SwingConstants.CENTER);
                playerLabel3.setHorizontalTextPosition(SwingConstants.CENTER);
                playerLabel3.setVisible(false);
            }
            {
                playerLabel2 = new JLabel();
                this.add(playerLabel2, new AnchorConstraint(296, 1007, 322, 38, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                playerLabel2.setText("Player2");
                playerLabel2.setPreferredSize(new java.awt.Dimension(65, 14));
                playerLabel2.setHorizontalAlignment(SwingConstants.CENTER);
                playerLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
                playerLabel2.setVisible(false);
            }
            {
                playerLabel1 = new JLabel();
                this.add(playerLabel1, new AnchorConstraint(3, 1007, 37, 7, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                playerLabel1.setText("Player1");
                playerLabel1.setPreferredSize(new java.awt.Dimension(64, 16));
                playerLabel1.setHorizontalAlignment(SwingConstants.CENTER);
                playerLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
                playerLabel1.setVisible(false);
            }
            {
                player4 = new PlayerIcon(Color.yellow);
                this.add(player4, new AnchorConstraint(886, 976, 0, 53, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE));
                player4.setBackground(new java.awt.Color(255,255,0));
                player4.setVisible(false);
                {
                    player4_popup = new JPopupMenu();
                    setComponentPopupMenu(player4, player4_popup);
                    {
                        player4_history = new JMenuItem();
                        player4_popup.add(player4_history);
                        player4_history.setText("Player 4's History");
                    }
                    {
                        player4_roll = new JMenuItem();
                        player4_popup.add(player4_roll);
                        player4_roll.setText("Player 4's Rolls");
                    }
                }
            }
            {
                player3 = new PlayerIcon(Color.green);
                this.add(player3, new AnchorConstraint(606, 976, 734, 53, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                player3.setBackground(new java.awt.Color(255,128,0));
                player3.setVisible(false);
                {
                    player3_popup = new JPopupMenu();
                    setComponentPopupMenu(player3, player3_popup);
                    {
                        player3_history = new JMenuItem();
                        player3_popup.add(player3_history);
                        player3_history.setText("Player 3's History");
                    }
                    {
                        player3_roll = new JMenuItem();
                        player3_popup.add(player3_roll);
                        player3_roll.setText("Player 3's Rolls");
                    }
                }
            }
            {
                player2 = new PlayerIcon(Color.blue);
                this.add(player2, new AnchorConstraint(326, 976, 440, 53, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                player2.setBackground(new java.awt.Color(0,0,160));
                player2.setVisible(false);
                {
                    player2_popup = new JPopupMenu();
                    setComponentPopupMenu(player2, player2_popup);
                    {
                        player2_history = new JMenuItem();
                        player2_popup.add(player2_history);
                        player2_history.setText("Player 2's History");
                    }
                    {
                        player2_roll = new JMenuItem();
                        player2_popup.add(player2_roll);
                        player2_roll.setText("Player 2's Rolls");
                    }
                }
            }
            {
                player1 = new PlayerIcon(Color.red);
                this.add(player1, new AnchorConstraint(20, 976, 153, 53, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_NONE, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_NONE));
                player1.setBackground(new java.awt.Color(255,0,0));
                player1.setVisible(false);
                {
                    player1_popup = new JPopupMenu();
                    setComponentPopupMenu(player1, player1_popup);
                    {
                        player1_history = new JMenuItem();
                        player1_popup.add(player1_history);
                        player1_history.setText("Player 1's History");
                    }
                    {
                        player1_roll = new JMenuItem();
                        player1_popup.add(player1_roll);
                        player1_roll.setText("Player 1's Rolls");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Auto-generated method for setting the popup menu for a component
    */
    private void setComponentPopupMenu(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
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

    public void addPlayer(Player newPlayer) {
        // TODO Auto-generated method stub
        if(player1.isVisible() == false)
        {
            playerLabel1.setText(newPlayer.getName());
            playerLabel1.setVisible(true);
            player1.setColor(GameState.players.get(0).getColor());
            player1.setVisible(true);
        }
        else if(player2.isVisible() == false)
        {
            playerLabel2.setText(newPlayer.getName());
            playerLabel2.setVisible(true);
            player2.setColor(GameState.players.get(1).getColor());
            player2.setVisible(true);
        }
        else if(player3.isVisible() == false)
        {
            playerLabel3.setText(newPlayer.getName());
            playerLabel3.setVisible(true);
            player3.setColor(GameState.players.get(2).getColor());
            player3.setVisible(true);
        }
        else
        {
            playerLabel4.setText(newPlayer.getName());
            playerLabel4.setVisible(true);
            player4.setColor(GameState.players.get(3).getColor());
            player4.setVisible(true);
        }
    }

}
