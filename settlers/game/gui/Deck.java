package settlers.game.gui;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import settlers.game.GameState;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class Deck implements Runnable
{
	//holds all the cards in the deck
	ArrayList cards;
	
	GameBoard board;
	
	JLabel img;
	
	//constructor for use in creating the board's deck or a player's deck when there are not development cards to initialize the deck with
	public Deck(String playerType, GameBoard tempBoard)
	{
		//create a fully populated deck for the use by the board
		if (playerType.equalsIgnoreCase("board"))
		{
			cards = new ArrayList();
			initializeCards();
			shuffle();
		}
		
		//create a empty deck for a player
		else if (playerType.equalsIgnoreCase("player"))
		{
			cards = new ArrayList();
		}
		
		//the identifier used was incorrect
		else
			System.out.println("Error within Deck constructor.  Illegal playerType");
			
		board = tempBoard;
		
		//makeDeck();
	}
	
	//constructor which is used when the player has their own deck of development cards.  Takes in an initial card to start the deck with.
	public Deck(String playerType)
	{
		//create a fully populated deck for the use by the board
		if (playerType.equalsIgnoreCase("board"))
		{
			cards = new ArrayList();
			initializeCards();
			shuffle();
		}
		
		//create a empty deck for a player
		else if (playerType.equalsIgnoreCase("player"))
		{
			cards = new ArrayList();
		}
		
		//the identifier used was incorrect
		else
			System.out.println("Error within Deck constructor.  Illegal playerType");
			
	}
	
	private void makeDeck()
	{
		new Thread(this).start();
	}
	
	//method to shuffle cards
	private void shuffle()
	{
		Random generator = new Random();
		int randomLocation;
		DevelopmentCard tempCard;
		for (int x = 0; x < cards.size(); x++)
		{
			randomLocation = generator.nextInt(cards.size());
			tempCard = (DevelopmentCard)cards.get(randomLocation);
			cards.set(randomLocation, cards.get(x));
			cards.set(x, tempCard);
		}
	}
	
	//method to create all the cards
	//add x number of each type of card individually with a for loop
	private void initializeCards()
	{
		//adds 14 soldier development cards to the deck
		for (int x = 0; x < 14; x++)
			cards.add(new DevelopmentCard(1));
		
		//adds 2 road building cards to the deck
		cards.add(new DevelopmentCard(2));
		cards.add(new DevelopmentCard(2));
		
		//adds  2 monopoly cards to the deck
		cards.add(new DevelopmentCard(3));
		cards.add(new DevelopmentCard(3));
		
		//adds 2 year of plenty cards to the deck
		cards.add(new DevelopmentCard(4));
		cards.add(new DevelopmentCard(4));
		
		//adds 5 victory point cards to the deck
		for (int x = 0; x < 5; x++)
			cards.add(new DevelopmentCard(5));
	}
	
	//player draws the card off the top of the deck which is removed and returned
	public DevelopmentCard drawCard()
	{
		return (DevelopmentCard)cards.remove(0);
	}
	
	//adds a development card to the deck
	//useful for when this class is keeping track of a player's deck of development cards
	public void addCard(DevelopmentCard tempCard)
	{
		cards.add(tempCard);
		makeDeck();
	}
	
	//checks to see whether the deck has any cards in it.  If the deck is empty this method returns true.
	public boolean deckIsEmpty()
	{
		if (cards.size() <= 0)
			return true;
		return false;
	}
	
	//returns true if the deck contains a certain development card as given by it's type
	public int hasType(int type)
	{
		int counter = 0;
		if (cards.size() == 0)
			return 0;
		for (int x = 0; x < cards.size(); x++)
		{
			if (((DevelopmentCard)cards.get(x)).getType() == type)
			{
				//System.out.println("Player has card!!  Type:  " + type);
				counter++;
			}
		}
		return counter;
	}
	
	//turns over one of the players cards as specified by the type number given
	public void turnOver(int type)
	{
		int x = 0;
		System.out.println("Size of myDeck:  " + cards.size());
		
		while(x < cards.size())
		{
			if (((DevelopmentCard)cards.get(x)).getType() == type && ((DevelopmentCard)cards.get(x)).isFaceUp() == false)
			{
				((DevelopmentCard)cards.get(x)).showCard();
				return;
			}
			x++;
		}

		System.out.println("Player does not have a type " + type + " card to turn over");
		return;
	}
	
	@Override
	public void run() {
		//System.out.println("Nick Deck run debug");
		// TODO Auto-generated method stub
		
		//img = new JLabel(new ImageIcon(getClass().getResource(((DevelopmentCard)cards.get(0)).getFileLocation())));
		img = new JLabel(new ImageIcon(getClass().getResource(((DevelopmentCard)cards.get(cards.size()-1)).getFileLocation())));
		
		SpringLayout layout = (SpringLayout)board.getLayout();
		
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, img, 0, SpringLayout.VERTICAL_CENTER, board);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, img, 0, SpringLayout.HORIZONTAL_CENTER, board);
		
		board.add(img);
		board.validate();
		try 
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		board.remove(img);
		board.validate();
		board.repaint();
		
    	PlayerEvent E = new PlayerEvent("PLAYER_BUILT_DEV_CARD", GameState.getCurPlayer());
    	EventManager.callEvent(E);
	}
	
	public int getSize()
	{
		if (cards == null)
			return 0;
		else
			return cards.size();
	}
	
	//prints all the development cards in the deck to the screen.
	public void print()
	{
		System.out.println("size of Deck is:  " + cards.size());
		for (int x = 0; x < cards.size(); x++)
		{
			System.out.println(((DevelopmentCard)(cards.get(x))).getType());
		}
	}
}