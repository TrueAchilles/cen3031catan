package settlers.game.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class PlayerAvatar extends ImageIcon
{

    private String pathname = new String();

    public PlayerAvatar(String pathname)
    {
        super(pathname);
        this.pathname = pathname;
    }

    public String getPathname()
    {
        return pathname;
    }

    public void resizeImage()
    {

    }

}