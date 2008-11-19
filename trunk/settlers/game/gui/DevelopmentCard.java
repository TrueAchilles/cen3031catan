package settlers.game.gui;

import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import settlers.game.GameState;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

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
				System.out.println("Player ??? has turned over a soldier card");
			else if (cardType == 2)
				System.out.println("Player ??? has turned over a 2 free roads card");
			else if (cardType == 3)
				System.out.println("Player ??? has turned over a monopoly card");
			else if (cardType == 4)
				System.out.println("Player ??? has turned over a year of plenty card");
			else if (cardType == 5)
				System.out.println("Player ??? has turned over a victory point card");
		}
	}
	
	//returns the type of the Development Card
	public int getType()
	{
		return cardType;
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