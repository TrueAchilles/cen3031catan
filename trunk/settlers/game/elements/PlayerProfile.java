package settlers.game.elements;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import settlers.game.gui.PlayerAvatar;

public class PlayerProfile
{

    private String name;
    private int numberOfWins;
    private int numberOfLosses;
    private Color color;
    private PlayerAvatar playerAvatar;
    private String filepath;


    public PlayerProfile()
    {
        name = new String();
        numberOfWins = 0;
        numberOfLosses = 0;
        color = new Color(0,0,0);
        filepath = new String();
    }

    public PlayerProfile(String name, Color color)
    {
        this.name = name;
        this.color = color;
        numberOfWins = 0;
        numberOfLosses = 0;
        filepath = new String();
    }

    public PlayerProfile(String name, Color color, PlayerAvatar playerAvatar)
    {
        this.name = name;
        this.color = color;
        this.playerAvatar = playerAvatar;
        numberOfWins = 0;
        numberOfLosses = 0;
    }

    public void addLoss()
    {
        numberOfLosses++;
    }

    public void addWin()
    {
        numberOfWins++;
    }

    public int getNumberOfWins()
    {
        return numberOfWins;
    }

    public int getNumberOfLosses()
    {
        return numberOfLosses;
    }

    public String getName()
    {
        return name;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public PlayerAvatar getPlayerAvatar()
    {
        return playerAvatar;
    }

    public Color getColor()
    {
        return color;
    }

    public void setPlayerAvatar(PlayerAvatar playerAvatar)
    {
        this.playerAvatar = playerAvatar;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

}