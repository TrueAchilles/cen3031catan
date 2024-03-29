package settlers.game.gui;

import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import settlers.game.elements.*;
import settlers.game.GameState;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.events.RoadEvent;
import settlers.game.events.Event;

public class DevelopmentCard
{
	//an integer that represents the type of development card
	//1 represents a knight card
	//2 represents building of 2 roads for free
	//3 represents a monopoly
	//4 represents a year of plenty card
	//5 is a victory point card
	private int cardType;
	
	//tells whether this card is visible to other players or not
	private boolean faceUp;
    
    //tells whether card is visible to the user via the GUI
    private boolean isShown = false; 
	
	//creates a development card with type of -1 to show that this development card was initialized incorrectly
	public DevelopmentCard()
	{
		cardType = -1;
		faceUp = false;
	}
	
	//constructor that initializes the card to a certain type
	public DevelopmentCard(int type)
	{
		cardType = type;
		faceUp = false;
	}
	
	//returns a boolean value based on whether the development card is face up or face down
	public boolean isFaceUp()
	{
		return faceUp;
	}
	
	//displays the card to the other players
	public void showCard()
	{
        Player currPlayer = GameState.getCurPlayer();
		//if the card is already face up then display a message dialog that tells the user the card is already face up
		if (faceUp)
		{
			if (cardType == 1)
				JOptionPane.showMessageDialog(null,"You have already turned over all your soldier cards");
			else if (cardType == 2)
				JOptionPane.showMessageDialog(null,"You have already turned over all your 2 free roads cards");
			else if (cardType == 3)
				JOptionPane.showMessageDialog(null,"You have already turned over all your monopoly cards");
			else if (cardType == 4)
				JOptionPane.showMessageDialog(null,"You have already turned over all your year of plenty cards");
			else if (cardType == 5)
				JOptionPane.showMessageDialog(null,"You have already turned over all your victory point cards");
		}
		//turns the card face up and displays a message
		else
		{
			faceUp = true;
			
			if (cardType == 1)
			{
				System.out.println("Player " + GameState.getCurPlayer().getID() + " has turned over a soldier card");
				GameState.getCurPlayer().addKnightToArmy();
				
				boolean temp = false;
				int placeholder = 0;
				//This is keeping track of who has the largest army.
				if (GameState.getCurPlayer().getPlayerArmySize() >= 3 && !GameState.getCurPlayer().hasLargestArmy())
				{
					for (int x = 0; x < GameState.players.size(); x++)
					{
						if (GameState.getCurPlayer().getPlayerArmySize() > GameState.players.get(x).getPlayerArmySize())
						{
							placeholder = x;
							temp = true;
						}
					}
					if (temp)
					{
						JOptionPane.showMessageDialog(null,"YOU HAVE THE LARGEST ARMY\nNOW GO SCHOOL EVERYONE!");
						GameState.players.get(placeholder).setLargestArmy(false); 
						GameState.getCurPlayer().setLargestArmy(true);
						GameState.getCurPlayer().incrementVictoryPointTotal();
						GameState.players.get(placeholder).decrementVictoryPointTotal();
					}
				}
                //This will invoke the Move Robber and Stealing of 1 resource card events.
				Event e = new Event("PLAYER_PLAY_KNIGHT");
                EventManager.callEvent(e);
			}
			else if (cardType == 2)
            {
                Event e = new Event("PLAYER_PLAY_ROADBUILDING");
                EventManager.callEvent(e);
            }
			else if (cardType == 3)
            {
                Event e = new Event("PLAYER_PLAY_MONOPOLY");
                EventManager.callEvent(e);
				System.out.println("Player " + GameState.getCurPlayer().getID() + " has turned over a monopoly card");
            }
			else if (cardType == 4)
				System.out.println("Player " + GameState.getCurPlayer().getID() + " has turned over a year of plenty card");
			else if (cardType == 5)
            {
                currPlayer.incrementVictoryPointTotal();
                Event e = new Event("PLAYER_PLAY_VPCARD");
                EventManager.callEvent(e);
				System.out.println("Player " + currPlayer.getID() + " has turned over a victory point card");
            }
		}
	}
	
	//returns the type of the Development Card
	public int getType()
	{
		return cardType;
	}
    
    public void setIsShown(boolean toSet)
    {
    
        isShown = toSet;
    
    }
    
    public boolean getIsShown()
    {
    
        return isShown;
    
    }
	
	//returns a string holding the location of the card image
	public String getFileLocation()
	{
		switch(cardType)
		{
			case(1):
			{
				return new String("/settlers/game/images/Knight.jpg");
			}
			case(2):
			{
				return new String("/settlers/game/images/Road Building.jpg");
			}
			case(3):
			{
				return new String("/settlers/game/images/Monopoly.jpg");
			}
			case(4):
			{
				return new String("/settlers/game/images/Year of Plenty.jpg");
			}
			case(5):
			{
				return new String("/settlers/game/images/Palace.jpg");
			}
		}
		return "";
	}
	
	//prints this development card to the screen
	public void print()
	{
		switch (cardType)
		{
			case(1):
			{
				System.out.print("Knight");
				break;
			}
			case(2):
			{
				System.out.print("Road Building");
				break;
			}
			case(3):
			{
				System.out.print("Monopoly");
				break;
			}
			case(4):
			{
				System.out.print("Year of Plenty");
				break;
			}
			case(5):
			{
				System.out.print("Palace");
				break;
			}
		}
	}
}