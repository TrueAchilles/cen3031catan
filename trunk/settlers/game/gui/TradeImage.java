package settlers.game.gui;

import settlers.game.*;
import javax.swing.*;
import java.awt.event.*;

class TradeImage extends JLabel implements MouseListener
{
    private boolean clicked = false;
    private int resource;

    public TradeImage(int type)
    {
        this.addMouseListener(this);
        resource = type;
    
        if (resource == GlobalVar.WOOD)
        {
            this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wood3.jpg")));
        }
        else if (resource == GlobalVar.BRICK)
        {
            this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg")));
        }
        else if (resource == GlobalVar.WHEAT)
        {
            this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg")));
        }
        else if (resource == GlobalVar.SHEEP)
        {
            this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg")));
        }
        else if (resource == GlobalVar.ORE)
        {
            this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg")));
        }
    }
    
    public boolean getClicked()
    {
        return clicked;
    }
    
    public void mouseClicked(MouseEvent event)
    {
        if (clicked == false)
        {
            clicked = true;
            if (resource == GlobalVar.WOOD)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wood3S.jpg")));
            }
            else if (resource == GlobalVar.BRICK)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3S.jpg")));
            }
            else if (resource == GlobalVar.WHEAT)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3S.jpg")));
            }
            else if (resource == GlobalVar.SHEEP)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3S.jpg")));
            }
            else if (resource == GlobalVar.ORE)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3S.jpg")));
            }
        }
        else
        {
            clicked = false;
            if (resource == GlobalVar.WOOD)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wood3.jpg")));
            }
            else if (resource == GlobalVar.BRICK)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/bricks3.jpg")));
            }
            else if (resource == GlobalVar.WHEAT)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/wheat3.jpg")));
            }
            else if (resource == GlobalVar.SHEEP)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/sheep3.jpg")));
            }
            else if (resource == GlobalVar.ORE)
            {
                this.setIcon(new ImageIcon(getClass().getResource("/settlers/game/images/ore3.jpg")));
            }
        }
    }
    
    public void mouseEntered(MouseEvent event)
    {
    }
    
    public void mouseExited(MouseEvent event)
    {
    }
    
    public void mousePressed(MouseEvent event)
    {
    }
    
    public void mouseReleased(MouseEvent event)
    {
    }
}