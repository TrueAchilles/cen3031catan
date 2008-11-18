package settlers.game.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import settlers.game.GameState;
import settlers.game.events.*;
import settlers.game.elements.*;

public class PlayerInfo extends javax.swing.JPanel implements EventListener, ActionListener
{

   
    
     public SettlersGUI parent;
     //Boolean variable that will determine if the gui has been initialized once the game have started
     private boolean playerInfoInitialized = false;
     
     public int gameSize = GameState.players.size();
    // New JFrame that will hold all of the player information
     JFrame playerInfo = new JFrame("Player Information");
     //Tab pane container for each of the player information areas
     JTabbedPane cardTabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
     // Create three new panels, onr for development cards, one for resources and one for the over all player view
     JTextArea developmentPanel     = new JTextArea();
     JPanel resourcesPanel          = new JPanel();
     JTextArea playersPanel         = new JTextArea();
     
     //Titled boarders for two of the three Panels.  They are placed up here to they can me modified on repaint calls.
     //playersPanel is currently not on here as it needs to be written in a special manner to account for all of the players
     //Though I believe it can be placed here as well if I have enough time to rework this code, but it works at the moment
     TitledBorder resourceBorder;
     TitledBorder developmentBorder;
     
     //
     JLabel lblBrick;
     JLabel lblOre;
     JLabel lblWheat;
     JLabel lblWood;
     JLabel lblWool;
     
     JButton lblKnight;
     JButton lblMonopoly;
     JButton lblPalace;
     JButton lblRoad_Building;
     JButton lblYear_Of_Plenty;

     
     // All tabbed panes are going to be scroll panels as well
     JScrollPane developmentScroll  = new JScrollPane(developmentPanel);
     JScrollPane resourcesScroll    = new JScrollPane(resourcesPanel);
     JScrollPane playersScroll      = new JScrollPane(playersPanel);
     
     //Array of JTextAreas  so we can update the screen in real-time
     JTextArea[] playerPanels;

     //Pciture Locations
     ImageIcon brick3    = new ImageIcon("/settlers/game/images/brick3.jpg");
     ImageIcon ore3      = new ImageIcon("/settlers/game/images/ore3.jpg");
     ImageIcon wheat3    = new ImageIcon("/settlers/game/images/wheat3.jpg");
     ImageIcon wood3     = new ImageIcon("/settlers/game/images/wood3.jpg");
     ImageIcon wool3     = new ImageIcon("/settlers/game/images/wool3.jpg");
     
     ImageIcon knight    = new ImageIcon("/settlers/game/images/Knight.jpg");
	 ImageIcon monopoly  = new ImageIcon("/settlers/game/images/Monopoly.jpg");
	 ImageIcon palace    = new ImageIcon("/settlers/game/images/Palace.jpg");
	 ImageIcon road      = new ImageIcon("/settlers/game/images/Road Building.jpg");
	 ImageIcon plenty    = new ImageIcon("/settlers/game/images/Year of Plenty.jpg");
     
     //Counters
     int playerCounter = 0;
     int repaintPlayerCounter = 0;
     

    /**
             *Public method to initialize the Player information GUI panel.
             */
    public PlayerInfo()
    {
        super();
        populateArray(gameSize);
        initGUI(gameSize);
        
        EventManager.registerEvent("PLAYER_INITTURN_START", this);
        EventManager.registerEvent("PLAYER_TURN_START", this);
        EventManager.registerEvent("PLAYER_ROLLED", this);
        EventManager.registerEvent("PLAYER_BUILT_DEV_CARD", this);
        EventManager.registerEvent("PLAYER_BUILT_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_BUILT_ROAD", this);
        EventManager.registerEvent("PLAYER_TRADED", this);
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
        
        EventManager.registerEvent("PLAYER_INITTURN_START", this);
        EventManager.registerEvent("PLAYER_TURN_START", this);
        EventManager.registerEvent("PLAYER_ROLLED", this);
        EventManager.registerEvent("PLAYER_BUILT_DEV_CARD", this);
        EventManager.registerEvent("PLAYER_BUILT_SETTLEMENT", this);
        EventManager.registerEvent("PLAYER_BUILT_ROAD", this);
        EventManager.registerEvent("PLAYER_TRADED", this);
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
    
    /**
            *Initializes the playerPanels array with 8 new JTextAreas this method is not necessarily nessessary but it is in place for future needs if need be.
            *
            *@param numOfPlayers The number of players in the game
            */
    private void populateArray(int numOfPlayers)
    {
    
    playerPanels = new JTextArea[numOfPlayers];
    
    for (int i = 0; i < numOfPlayers; i++)
        {
            playerPanels[i] = new JTextArea();
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
            playerPanels[playerCounter].append ("Development Cards: x" + currPlayer.getNumberOfDevCards());

            //Add the panel (JTextArea) to the playersPanels
            playersPanel.add(playerPanels[playerCounter]);
            //Increment to the next player and loop
            playerCounter++;

        }
                

		
		//We are only going to run this code once and that is upon initialization.  Otherwhise we just want to set the text to the current information
        /*
                        * All of this code needs to be reworked somewhat to allow for it to be more modular and future proof, right now it is very much hard coded
                        */
        //if (!(playerInfoInitialized))
        {
            Player currPlayer = GameState.getCurPlayer();
            //Create a new Titled border with the first players name set as the title
            resourceBorder = new TitledBorder(new LineBorder(Color.black), currPlayer.getName());
            resourceBorder.setTitleColor(Color.black);
            resourcesPanel.setBorder(resourceBorder);
            resourcesPanel.setLayout(new GridLayout(3,2,5,5));
            //Create the initial JLabels with the pictures of the resources and the amount that the first player has
            lblBrick = new JLabel("Brick: x" + currPlayer.getBrick(), brick3, JLabel.LEFT);
            lblOre = new JLabel("Ore: x" + currPlayer.getOre(), ore3, JLabel.LEFT);
            lblWheat = new JLabel("Wheat: x" + currPlayer.getWheat() , wheat3, JLabel.LEFT);
            lblWood = new JLabel("Wood: x" + currPlayer.getWood(), wood3, JLabel.LEFT);
            lblWool = new JLabel("Wool: x" + currPlayer.getSheep(), wool3, JLabel.LEFT);
            //Add the labels to the planel 
            resourcesPanel.add(lblBrick);
            resourcesPanel.add(lblOre);
            resourcesPanel.add(lblWheat);
            resourcesPanel.add(lblWood);
            resourcesPanel.add(lblWool);

            developmentBorder = new TitledBorder(new LineBorder(Color.black), currPlayer.getName());
            developmentBorder.setTitleColor(Color.black);
            developmentPanel.setBorder(developmentBorder);
            developmentPanel.setLayout(new GridLayout(1,5,0,5));

            lblKnight = new JButton("Knight: x",knight);
            lblMonopoly = new JButton("Monopoly: x:",monopoly);
            lblPalace = new JButton("Palace: x",palace);
            lblRoad_Building = new JButton("Road Bulding: x",road);
            lblYear_Of_Plenty = new JButton("Year Of Plenty: x",plenty);
            

            
            lblKnight.addActionListener(this);
            lblMonopoly.addActionListener(this);
            lblPalace.addActionListener(this);
            lblRoad_Building.addActionListener(this);
            lblYear_Of_Plenty.addActionListener(this);
		
            developmentPanel.add(lblKnight);
            developmentPanel.add(lblMonopoly);
            developmentPanel.add(lblPalace);
            developmentPanel.add(lblRoad_Building);
            developmentPanel.add(lblYear_Of_Plenty);
		}
		
		playerInfo.add(cardTabs);
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
		playerInfo.setVisible(true);
		playerInfo.setResizable(true);
        playerInfo.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
        
    }   catch (Exception e) 
        {
             e.printStackTrace();
        }
       
    }

    
    public SettlersGUI getGUI()
    {
        return parent;
    }
    
    
    
    public void playerPanelRepaint()
    {
    
        Player currPlayer = GameState.getCurPlayer();
    
        while (repaintPlayerCounter <= (gameSize - 1))
        {

            playerPanels[repaintPlayerCounter].setText("Resource Cards:    x" + GameState.players.get(repaintPlayerCounter).getNumberOfResCards() + "\n");
            playerPanels[repaintPlayerCounter].append ("Development Cards: x" + GameState.players.get(repaintPlayerCounter).getNumberOfDevCards());
            
            repaintPlayerCounter++;

        }
        
        
            resourceBorder.setTitle(currPlayer.getName());
            lblBrick.setText("Brick: x" + currPlayer.getBrick());
            lblOre.setText("Ore: x" + currPlayer.getOre());
            lblWheat.setText("Wheat: x" + currPlayer.getWheat());
            lblWood.setText("Wood: x" + currPlayer.getWood());
            lblWool.setText("Wool: x" + currPlayer.getSheep());
            repaintPlayerCounter = 0;
            playerInfo.repaint();

    }
    

    public void actionPerformed(ActionEvent e)
    {
    
    
    System.out.println(e.getActionCommand().toString());
    if (e.getActionCommand().equals("exit"))
        {
        this.setVisible(false);
        }
    
    }
    
    
    public void eventCalled(Event e)
    {
        String event = e.getEvent();

        if (event.equals("PLAYER_INITTURN_START") || event.equals("PLAYER_TURN_START"))
        {
        
            playerPanelRepaint();
            playerInfo.repaint();
            updateUI();
            
        }
        /**
                        * This else if will be used for when the game is actually in round robin  and the dice are rolled.  This allows the resources to update to the screen
                        * in real time.
                        */
        else if (event.equals("PLAYER_BUILT_DEV_CARD") || event.equals("PLAYER_BUILT_SETTLEMENT") || event.equals("PLAYER_BUILT_ROAD") || event.equals("PLAYER_ROLLED") || event.equals("PLAYER_TRADED"))
        {
            
            playerPanelRepaint();                   
            playerInfo.repaint();
            updateUI(); 
        }
    }
    


}

