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
import settlers.game.*;
import settlers.game.events.Event;
import settlers.game.events.EventListener;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;

public class RollBox extends JPanel implements Runnable
{   
    private BufferedImage anim[] = new BufferedImage[5];
    private int index;
    
    private boolean rolling, finalRoll;
    
    private Graphics2D g2d;
    
    private Random rand;
    private Dice dice;

    public RollBox() {
        super();
        initGUI();
    }
    
    private void initGUI() 
    {
        dice = new Dice();
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
    
    public void paint(Graphics g) {
        super.paint(g);
        
        if(rolling)
        {
            g.drawImage(anim[rand.nextInt(6)], 10, 10, null);
            g.drawImage(anim[rand.nextInt(6)], 60, 60, null);
        }
        else if(finalRoll)
        {
            g.drawImage(anim[dice.getD1() - 1], 10, 10, null);
            g.drawImage(anim[dice.getD2() - 1], 60, 60, null);
        }
        
    }
    
    public void run() 
    {
        rolling = true;
        for(int i = 0; i < 10; i++)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NullPointerException npe) {
                // TODO Auto-generated catch block
                npe.printStackTrace();
            }
            repaint();
        }
        rolling = false;
        finalRoll = true;
        
        PlayerEvent n = new PlayerEvent("PLAYER_ROLLED_SUCCESSFULLY", GameState.getCurPlayer(), new Integer(dice.getD1() + dice.getD2()));
        EventManager.callEvent(n); 
        
        repaint();
        
    }

}
