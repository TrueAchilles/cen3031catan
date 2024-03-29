package settlers.game.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Adjustable;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;

import settlers.game.*;
import settlers.game.gui.*;
import settlers.game.elements.*;
import settlers.game.events.*;

/**
  *The PlayerInfo Class is responsible for mainting up-to-date information about the current state of the game to the player in an east to read and understand way.  It implements both the EventListener used to
  *catch events thrown by the system and the Actionlinstener interface used to catch button events.  The information is displayes in a three tabbed panel.
  *
  *Resource Panel will hold all of the players resouce card counts and development card counts.  Often referred to as the overall resource counts.
  *Players Panel will hold the current players resources, parsed out into how many of each resource the player has.  Often refered to as the individual resource counts.
  *Development Panel will hold the current players development cards.
  */

public class PlayerInfo extends javax.swing.JFrame implements ActionListener
{

     public SettlersGUI parent;
     //Boolean variable that will determine if the gui has been initialized once the game have started
     private boolean playerInfoInitialized = false;
     
     private final int gameSize = GameState.players.size();
    // New JFrame that will hold all of the player information
     public JFrame playerInfo = new JFrame("Player Information");
     //Tab pane container for each of the player information areas
     public JTabbedPane cardTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
     // Create three new panels, onr for development cards, one for resources and one for the over all player view
     private JPanel resourcesPanel          = new JPanel();
     private JTextArea playersPanel         = new JTextArea();
     private JPanel developmentPanel        = new JPanel();
     //Titled boarders for two of the three Panels.  They are placed up here to they can me modified on repaint calls.
     //playersPanel is currently not on here as it needs to be written in a special manner to account for all of the players
     //Though I believe it can be placed here as well if I have enough time to rework this code, but it works at the moment
     TitledBorder resourceBorder;
     TitledBorder developmentBorder;
     
     //Labels for Amount of Resources a player owns
     JLabel lblBrick;
     JLabel lblOre;
     JLabel lblWheat;
     JLabel lblWood;
     JLabel lblWool;
     
     //Buttons for development cards
     JButton lblKnight;
     JButton lblMonopoly;
     JButton lblPalace;
     JButton lblRoad;
     JButton lblYear;

     
     // All tabbed panes are going to be scroll panels as well
     JScrollPane developmentScroll  = new JScrollPane(developmentPanel);
     JScrollPane resourcesScroll    = new JScrollPane(resourcesPanel);
     JScrollPane playersScroll      = new JScrollPane(playersPanel);
     
     //Array of JTextAreas /Jpanels so we can update the screen in real-time
     public JTextArea[] playerPanels;
     public JPanel[] playerDevelopmentPanels;
     //2-D array of linked lists that will house all of the players development cards
     public LinkedList<JButton>[][] playerCards;

     //Image Icons for Resources 
     ImageIcon brick;
     ImageIcon ore;      
     ImageIcon wheat;    
     ImageIcon wood;     
     ImageIcon wool;     
     //Image Icons for Development Cards
     ImageIcon knight;
	 ImageIcon monopoly;
	 ImageIcon palace;
	 ImageIcon road;     
	 ImageIcon plenty;

     ImageIcon  largestArmy = new ImageIcon(getClass().getResource("/settlers/game/images/sheep2.jpg"));;
     
     ImageIcon  longestRoad = new ImageIcon(getClass().getResource("/settlers/game/images/sheep2.jpg"));;
     
     //Counters
     int playerCounter = 0;
     int repaintPlayerCounter = 0;
     int rows = 2;
     int newRow = 8;
     
     boolean initReverse;
     
     

    /**
             *Public method to initialize the Player information GUI panel.
             */
    public PlayerInfo()
    {
        super();
        populateArray(gameSize);
        initGUI(gameSize);
                
    }
    /**
            *Public constructor that will set the parent
            *
            *@param _parent No idea what this does
            */
    public PlayerInfo(SettlersGUI _parent)
    {
        super();
        parent = _parent;
        populateArray(gameSize);
        initGUI(gameSize);
       
    }
    /**
            * Set method for turning on or off the player information panel.
            *
            *@param toSet This will set the player information panel to on or off
            */
    public void setPlayerInfoInitialized(boolean toSet)
    {
    
        playerInfoInitialized = toSet;
        
    }
    /**
            * Get method for finding out if the player information panel has been initialized.
            */
    public boolean getPlayerInfoInitialized()
    {
    
        return playerInfoInitialized;
        
    }
    
    public JPanel getDevelopmentPanel()
    {
    
        return developmentPanel;
    
    }
    
    /**
            *Initializes the arrays to match the amount of players in the game currently from 2 - 8
            *
            *@param numOfPlayers The number of players in the game
            */
    private void populateArray(int numOfPlayers)
    {
    
    playerPanels = new JTextArea[numOfPlayers];
    playerDevelopmentPanels = new JPanel[numOfPlayers];
    playerCards = new LinkedList[numOfPlayers][5];
    
    for (int i = 0; i < numOfPlayers; i++)
        {
            playerPanels[i] = new JTextArea();
            playerDevelopmentPanels[i] = new JPanel();
            playerDevelopmentPanels[i].setLayout(new GridLayout(2, 4, 10, 10));
            playerCards[i][0] = new LinkedList<JButton>();
            playerCards[i][1] = new LinkedList<JButton>();
            playerCards[i][2] = new LinkedList<JButton>();
            playerCards[i][3] = new LinkedList<JButton>();
            playerCards[i][4] = new LinkedList<JButton>();
        }
    }
    
    /**
            * Private method that create the player information panel.
            *
            *@param  numOfPlayers The number of players in the game
             */
    private void initGUI(int numOfPlayers)
    {
        try
        {
        
        // Add the three different tabs to the cardTabs frame    
		cardTabs.addTab("Player Hands", playersScroll);
		cardTabs.addTab("Current Resources", resourcesScroll);
		cardTabs.addTab("Development Cards", developmentScroll);
        //Set up a a grid layout for a maximum of 8 people at the moment.  The grid will be in the size of 2 columns and X amount of rows (1, 2, 3, or 4) depending on the amount of players in the game
        //This was done so that the panel would grow down instead of out as we are running out of real estate to display important information to the user.  The two tens are there to set spacing, see
        //GridLayout documentation for more infomation.
        double gridSize = Math.ceil( (double)(gameSize / 2.0));
        playersPanel.setLayout(new GridLayout( (int) gridSize, 2, 10,10));
        //developmentPanel.setLayout(new GridLayout( 1, 1, 10,10));


        /*
                       * This is the initial creation of the overview panel. (Showing player resource card counts and development card counts)
                       * This is iterate through the playerPanels array and add a titled border  create a simple grid lay out and add the text
                       * that will display information about the players current state
                        */
                      
        while (playerCounter <= (gameSize - 1))
        {
            //Get the players name
            Player currPlayer = GameState.players.get(playerCounter); 
            //Add the players name to the titled border and set the border to the JTextArea
            TitledBorder currPlayerBorder = new TitledBorder(new LineBorder(currPlayer.getColor()), currPlayer.getName());
            currPlayerBorder.setTitleColor(currPlayer.getColor());
            playerPanels[playerCounter].setBorder(currPlayerBorder);

            //Create a grid and add the text to the JTextArea
            playerPanels[playerCounter].setLayout(new GridLayout(2,1));
            playerPanels[playerCounter].setText("Resource Cards:    x" + currPlayer.getNumberOfResCards() + "\n");
            playerPanels[playerCounter].append ("Development Cards: x" + currPlayer.getNumberOfDevCards() + "\n");
            playerPanels[playerCounter].append ("Victory Points:    x" + currPlayer.getVictoryPointTotal());

            //Add the panel (JTextArea) to the playersPanels
            playersPanel.add(playerPanels[playerCounter]);

            //Increment to the next player and loop
            playerCounter++;

        }
        
        //Add the current player's Development card panel to the screen.
		
		//We are only going to run this code once and that is upon initialization.  Otherwhise we just want to set the text to the current information
        /*
                        * All of this code needs to be reworked somewhat to allow for it to be more modular and future proof, right now it is very much hard coded
                        */
                      
        if (!(playerInfoInitialized))
        {
            //Get the current player
            Player currPlayer = GameState.getCurPlayer();

            //Create a new Titled border with the first players name set as the title
            resourceBorder = new TitledBorder(new LineBorder(Color.black), currPlayer.getName());
            resourceBorder.setTitleColor(Color.black);
            
            developmentBorder = new TitledBorder(new LineBorder(currPlayer.getColor()), currPlayer.getName());
            developmentBorder.setTitleColor(currPlayer.getColor());
            
            developmentPanel.setBorder(developmentBorder);
            developmentPanel.add(playerDevelopmentPanels[0]);

            
            resourcesPanel.setBorder(resourceBorder);
            resourcesPanel.setLayout(new GridLayout(3,2,5,5));
            
            //Set the individual player resource pictures
            brick = new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg"));
            ore = new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg"));
            wheat = new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg"));
            wood = new ImageIcon(getClass().getResource("/settlers/game/images/wood3.jpg"));
            wool = new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg"));
            
            
            //Create the initial JLabels with the pictures of the resources and the amount that the first player has
            lblBrick = new JLabel("Brick: x" + currPlayer.getBrick(), brick, JLabel.LEFT);
            lblOre = new JLabel("Ore: x" + currPlayer.getOre(), ore, JLabel.LEFT);
            lblWheat = new JLabel("Wheat: x" + currPlayer.getWheat(), wheat, JLabel.LEFT);
            lblWood = new JLabel("Wood: x" + currPlayer.getWood(), wood, JLabel.LEFT);
            lblWool = new JLabel("Sheep: x" + currPlayer.getSheep(), wool, JLabel.LEFT);
            //Add the labels to the planel 
            resourcesPanel.add(lblBrick);
            resourcesPanel.add(lblOre);
            resourcesPanel.add(lblWheat);
            resourcesPanel.add(lblWood);
            resourcesPanel.add(lblWool);

		}
		//Add the tabs tot he JFrame 
		playerInfo.add(cardTabs);
        
        //Depending on the amount of player we want to set the length of the window appropriately
        if (gameSize <= 2)
        {
            playerInfo.setSize(455,200);
        }
        else if (gameSize <= 4)
        {
            playerInfo.setSize(455,300);
        }
        else if (gameSize <= 6 && gameSize > 4)
        {
            playerInfo.setSize(455,450);
        }
        else if (gameSize <= 8 && gameSize > 6)
        {
            playerInfo.setSize(455,600);
        }
        //Set visibility, resizability, and default close operation
		playerInfo.setVisible(true);
		playerInfo.setResizable(true);
        playerInfo.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        playerInfo.repaint();

            // add this entire window to ContainerGUI
        ContainerGUI.playerInfo = this;

            // set the location of the window
            // the x coordinate is (width of MainBoard) + 10 pixels
            // the y coordinate is a constant 200 pixels
        int x = ContainerGUI.mainBoard.getWidth();
        int y = 200;
        playerInfo.setLocation(x + 10,y);
        
    }   catch (Exception e)
        {
             e.printStackTrace();
        }
       
    }
    
    
    
    /**
            *A private repaint method for the player information panel. It's purpose is to get the new overall resource counts and the individual resource counts and displaye them to the screen
            */
    public void playerPanelRepaint()
    {
        //Get the current player
        Player currPlayer = GameState.getCurPlayer();
        //This will update the over all resource counts on the resource panel
        while (repaintPlayerCounter <= (gameSize - 1))
        {

            playerPanels[repaintPlayerCounter].setText("Resource Cards:    x" + GameState.players.get(repaintPlayerCounter).getNumberOfResCards() + "\n");
            playerPanels[repaintPlayerCounter].append ("Development Cards: x" + GameState.players.get(repaintPlayerCounter).getNumberOfDevCards() + "\n");
            playerPanels[repaintPlayerCounter].append ("Victory Points:    x" + GameState.players.get(repaintPlayerCounter).getVictoryPointTotal());
            
            //increment to the next player
            repaintPlayerCounter++;
        }
        
            //This will update the individual player panel
            resourceBorder.setTitle(currPlayer.getName());
            
            developmentBorder.setTitle(currPlayer.getName());
            developmentBorder.setTitleColor(currPlayer.getColor());
            developmentBorder.setBorder(new LineBorder(currPlayer.getColor()));
            
            lblBrick.setText("Brick: x" + currPlayer.getBrick());
            lblOre.setText("Ore: x" + currPlayer.getOre());
            lblWheat.setText("Wheat: x" + currPlayer.getWheat());
            lblWood.setText("Wood: x" + currPlayer.getWood());
            lblWool.setText("Sheep: x" + currPlayer.getSheep());
            //reset the counter
            repaintPlayerCounter = 0;
            //repaint the screen
            playerInfo.repaint();

    }
    /**
            *This method is responsible for displaying the development cards to the user in a clickable image format
            *
            *Each Switch statement will execute the following
            *
            *(1) Set the Development card image to the appropriate image
            *(2) Add the image to a Clickable Button
            *(3) Set the size of the button to the image size, currently 90 x 140 px
            *(4) Add an action listener to the button
            *(5) Add the button to the appriopriate players development card panel
            *(6) Add the JButton to the players linked list of JButtons for that particalar development card
            *
            *Key
            * 0 = Knight
            * 1 = Road Building
            * 2 = Monopoly
            * 3 = Year of Plenty
            * 4 = Victory Point card, currently we only have the Palace
             */
    public void displayDevCards()
    {
        //Get the current players development card deck
        Deck dev = GameState.getCurPlayer().getDevCards();
        //Set the number of cards in the players deck to the Player class attribute setNumberOfDevCards.  This variable is used to updatet the overall player counts
		GameState.getCurPlayer().setNumberOfDevCards(dev.getSize());
        //Get the current player.
        Player currPlayer = GameState.getCurPlayer();


        if (dev.getSize() % newRow  == 0)
        {
        
            rows++;
            newRow += 4;
            playerDevelopmentPanels[currPlayer.getID() - 1].setLayout(new GridLayout (rows, 4, 10, 10));
        
        }
        
        
        //We are going to iterate through all of the players development card, however we will only execute code that is on cards that are not shown
        for(int i = 0; i < dev.getSize(); i++)
        {
            //This makes sure that each card is only shown once
            if (!(dev.getCard(i).getIsShown()))
            {
                //set the cards attribute to true once it is shown
                dev.getCard(i).setIsShown(true);
                
                switch(dev.getCard(i).getType())
                {
                case(1):
                {    
                    knight    = new ImageIcon(getClass().getResource("/settlers/game/images/Knight.jpg"));
                    lblKnight = new JButton(knight);
                    lblKnight.setPreferredSize(new java.awt.Dimension(90, 140));
                    String lblKnightToolTip = "<html>When a player plays a Knight card, he/she must move the robber.<br>" +
                                              "The hex where the robber rests does not produce resources until<br>" + 
                                              "the robber moves off that hex. See Robber for more details. Also,<br>" + 
                                              "player who played the Knight card can steal one random resource<br>" +
                                              "card from any player with a settlement or city touching that hex.<br>" +
                                              "Unlike rolling a 7 no one has to discard cards after a Knight.<br><br>" +
                                              "Once a player finishes resolving the robber, he adds the Knight to his<br>" + 
                                              "army by placing it face-up in front of him/her. The card no longer has<br>" +
                                              "any effect, but the number of face-up Knight cards a player has is<br>" +
                                              "used to determine who gets the Largest Army bonus.</html>";
                    lblKnight.setToolTipText(lblKnightToolTip);
                    lblKnight.addActionListener(this);
                    playerDevelopmentPanels[currPlayer.getID() - 1].add(lblKnight);
                    playerCards[currPlayer.getID() - 1][0].add(lblKnight);
                    break;
                      
                }
                case(2):
                {
                    road    = new ImageIcon(getClass().getResource("/settlers/game/images/Road Building.jpg"));
                    lblRoad = new JButton(road);
                    lblRoad.setPreferredSize(new java.awt.Dimension(90, 140));
                    lblRoad.setToolTipText("<html>The Road Building card allows the Player to immediately place two<br>" + 
                                            "road segments onto the board, as if he/she had just built them.<br>" +  
                                            "These roads are free of charge.</html>");
                    lblRoad.addActionListener(this);
                    playerDevelopmentPanels[currPlayer.getID() - 1].add(lblRoad);
                    playerCards[currPlayer.getID() - 1][1].add(lblRoad);
                    break;
                } 
                case(3):
                {
                    monopoly    = new ImageIcon(getClass().getResource("/settlers/game/images/Monopoly.jpg"));
                    lblMonopoly = new JButton(monopoly);
                    lblMonopoly.setPreferredSize(new java.awt.Dimension(90, 140));
                    lblMonopoly.setToolTipText("<html>The Monopoly card allows the player to choose one type of resource.<br>" +
                                                "All other players must give the player who played the card all of<br>" + 
                                                "their resource cards of that type.</html>");
                    lblMonopoly.addActionListener(this);
                    playerDevelopmentPanels[currPlayer.getID() - 1].add(lblMonopoly);
                    playerCards[currPlayer.getID() - 1][2].add(lblMonopoly);
                    break;
                }
                case(4):
                {
                    plenty    = new ImageIcon(getClass().getResource("/settlers/game/images/Year of Plenty.jpg"));
                    lblYear = new JButton(plenty);
                    lblYear.setPreferredSize(new java.awt.Dimension(90, 140));
                    lblYear.setToolTipText("<html>The Year of Plenty card allows the player to immediately take any two <br>" +
                                            "resource cards from the bank and add them to his hand. These can be <br>" + 
                                            "two different resources or two of the same resource. They may <br>" + 
                                            "immediately be used to build. </html>");                   
                    lblYear.addActionListener(this);
                    playerDevelopmentPanels[currPlayer.getID() - 1].add(lblYear);
                    playerCards[currPlayer.getID() - 1][3].add(lblYear);
                    break;
                }
                case(5):
                {
                    palace    = new ImageIcon(getClass().getResource("/settlers/game/images/Palace.jpg"));
                    lblPalace = new JButton(palace);
                    lblPalace.setPreferredSize(new java.awt.Dimension(90, 140));
                    String lblPalaceToolTip = "<html>VP cards provide an extra Victory Point towards winning. They are <br>" + 
                                              "played differently from other developement cards. VP cards are<br>" + 
                                              "kept hidden from the other players until their owner has 10<br>" + 
                                              "Victory Points, including the cards. The owner then plays all his<br>" +  
                                              "VP cards at once to achieve victory. A player can play as many<br>" + 
                                              "VP cards as he wants (even if he has already played another<br>" + 
                                              "Developement card), and can even play them on the turn that<br>" +
                                              "they are drawn.</html?";
                    lblPalace.setToolTipText(lblPalaceToolTip);
                    lblPalace.addActionListener(this);
                    playerDevelopmentPanels[currPlayer.getID() - 1].add(lblPalace);
                    playerCards[currPlayer.getID() - 1][4].add(lblPalace);
                    break;
                }
            }
            }
        }
        
    }
    
    /**
             *Each Player will have his/her own individual development card panel, this method is in charge of changing it properly after each players turn/
             *
             *@param playerID The current player ID.
             *@param gameState The state that the game is in 2 = Normal game mode
             */
    
    public void changeDevelopmentCardPanel(int playerID, int gameState)
    {
        System.out.println(gameState);
    
        if (playerID == 1 && gameState == 2)
        {
    
            developmentPanel.remove(playerDevelopmentPanels[gameSize - 1]);
            developmentPanel.add(playerDevelopmentPanels[playerID - 1]);
    
        }
        else if (playerID != 1 && gameState == 2)
        {
        
            developmentPanel.remove(playerDevelopmentPanels[playerID - 2]);
            developmentPanel.add(playerDevelopmentPanels[playerID - 1]);
        
        }
    }
    

    
    /**
            * Implementation of the actionPerformed interface for tha action listener added to the development card buttons.
            *
            *The Set up of each if statement is as follows.
            *
            *If the event source toString contains the image name, then
            *(1) use the Development Card Deck Method .turnOver to invoke the actions to be taken by that particular development card.  See Deck.java for more information
            *(2) access the linked list of the player of that development card and remove the head node.
            *(3) repaint the player info panel to show that the development card has been removed.
            *(4) update the UI just in case.
            *
            *There is much work to be done on this as I feel it is not the best way to go about implementing this feature but for now it works.
            *
            *@param e Action Entered by the User via a Mouseclick.
            */
    public void actionPerformed(ActionEvent e)
    {
    
    //Get the Current Player
    Player currPlayer = GameState.getCurPlayer();
    //Get the current players deck
    Deck dev = GameState.getCurPlayer().getDevCards();

        if (e.getActionCommand().equals("exit"))
            {
                this.setVisible(false);
            }
    
            if (e.getSource().toString().contains("Knight"))
            {
                dev.turnOver(1);
                playerDevelopmentPanels[currPlayer.getID() - 1].remove(playerCards[currPlayer.getID() - 1][0].removeFirst());
                playerInfo.repaint();
             }
            if (e.getSource().toString().contains("Road"))
            {
                dev.turnOver(2);
                playerDevelopmentPanels[currPlayer.getID() - 1].remove(playerCards[currPlayer.getID() - 1][1].removeFirst());
                playerInfo.repaint();
            }
            if (e.getSource().toString().contains("Monopoly"))
            { 
                dev.turnOver(3);
                playerDevelopmentPanels[currPlayer.getID() - 1].remove(playerCards[currPlayer.getID() - 1][2].removeFirst());
                playerInfo.repaint();
            }
            if (e.getSource().toString().contains("Year"))
            {
                dev.turnOver(4);
                playerDevelopmentPanels[currPlayer.getID() - 1].remove(playerCards[currPlayer.getID() - 1][3].removeFirst());
                playerInfo.repaint();
            }
            if (e.getSource().toString().contains("Palace"))
            {
                dev.turnOver(5);
                playerDevelopmentPanels[currPlayer.getID() - 1].remove(playerCards[currPlayer.getID() - 1][4].removeFirst());
                playerInfo.repaint();
            }
    }
    
    /**
            *No idea what this does.  I feel it must set the parent of the JFrame to the SettlersGUI
            */
    public SettlersGUI getGUI()
    {
        return parent;
    }
    


}

