package settlers.game.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import settlers.game.GameState;
import settlers.game.events.Event;
import settlers.game.events.EventListener;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class RollBox extends JPanel implements Runnable, EventListener
{   
    private BufferedImage anim[] = new BufferedImage[5];
    private int index;
    
    private boolean rolling, finalRoll;
    
    private Graphics2D g2d;
    
    private Random rand;
    
    public RollBox() {
        super();
        EventManager.registerEvent("DICE_ROLLED", this);
        initGUI();
    }
    
    private void initGUI() 
    {
    	rolling = false;
    	finalRoll = false;
    	
        anim = new BufferedImage[6];
        
        rand = new Random();
    	
        this.setPreferredSize(new java.awt.Dimension(120, 120));
        this.setSize(120, 120);
        this.setBackground(new java.awt.Color(192,192,192));
        this.setBorder(new LineBorder(Color.black, 3));
        
    	for (int i = 0; i <= 5; i++) {
			try {
				anim[i] = ImageIO.read(getClass().getResource("/settlers/game/images/d" + (i+1) + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        this.setVisible(false);
    }
    
    public int roll()
    {
    	new Thread(this).start();
    	return rollDie();
    }
    
    private int rollDie()
    {
    	int rollValue = dice.roll(2);
    	System.out.println("Dice 1: " + dice.getD1() + " Dice 2: " + dice.getD2());
    	
    	animate();
    	
    	return rollValue;
    	
    }
    
    private void animate()
    {
    	rolling = true;
    	repaint();
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	if(rolling)
    	{
    		g.drawImage(anim[dice.getD1()-1], 10, 10, null);
    		g.drawImage(anim[dice.getD2()-1], 60, 60, null);
    	}
    	
    }
    
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}

	public void eventCalled(Event e) {

		String event = e.getEvent();
		
		if(event.equals("DICE_ROLLED"))
		{
			int value = roll();
			GameState.getGui().gui.getMainBoard().getGameBoard().diceRollResources(value);
			GameState.getGui().gui.getBottomPanel().getTabbedPanel().setRandomDiceRoll(GameState.getCurPlayer().getName() + " rolled: " + value +"\n");
			
			PlayerEvent n = new PlayerEvent("PLAYER_ROLLED", GameState.getCurPlayer());
            EventManager.callEvent(n);
		}
	}

}
