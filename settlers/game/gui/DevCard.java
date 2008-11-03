package settlers.game.gui;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import settlers.game.GameState;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;


public class DevCard implements Runnable
{
	GameBoard board;
	JLabel img;
	
	Random rand;
	
	public DevCard(GameBoard _board)
	{
		rand = new Random();
		board = _board;
		
		makeCard();
	}
	
	private void makeCard()
	{
		new Thread(this).start();
	}
	
	private String findCard()
	{
		String card = null;
		switch(rand.nextInt(5))
		{
			case(0):
			{
				card = new String("/settlers/game/images/Year of Plenty.jpg");
				GameState.getCurPlayer().addDevCard(0);
				break;
			}
			case(1):
			{
				card = new String("/settlers/game/images/Road Building.jpg");
				GameState.getCurPlayer().addDevCard(1);
				break;
			}
			case(2):
			{
				card = new String("/settlers/game/images/Palace.jpg");
				GameState.getCurPlayer().addDevCard(2);
				break;
			}
			case(3):
			{
				card = new String("/settlers/game/images/Monopoly.jpg");
				GameState.getCurPlayer().addDevCard(3);
				break;
			}
			case(4):
			{
				card = new String("/settlers/game/images/Knight.jpg");
				GameState.getCurPlayer().addDevCard(4);
				break;
			}
		}
		return card;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		img = new JLabel(new ImageIcon(getClass().getResource(findCard())));
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
}
