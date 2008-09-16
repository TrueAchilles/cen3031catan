package Settlers;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class SettlersGui extends JFrame{
    Board board;
    SettlersText sText;
    SettlersMenu menu;
    JButton btnOk;
    
    private SettlersController controller=null;

    public Board getBoard(){
        return board;
    }
    SettlersGui(SettlersController _controller){	
        controller=_controller;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });            
        setTitle("Settlers of Catan");
        getContentPane().setLayout(null);
        board = new Board(this);
        board.setBackground(Color.white);        
        getContentPane().add(board);
        board.setBounds(275,0,800,485);
        sText=new SettlersText(this);
        getContentPane().add(sText);
        sText.setBounds(275,485,800,600);
        menu=new SettlersMenu(this);
        getContentPane().add(menu);
        menu.setBounds(0,0,275,600);
                
        pack();        
        setSize(new Dimension(800,600));
        setResizable(false);
        setVisible(true);
    }
    
    public void write(String txt){
        sText.write(txt);
    }
    
    public void initialize(Tile[] tiles){
        board.initialize(tiles);
    }
    
    public SettlersController getController(){
        return controller;
    }
    
    public SettlersMenu getMenu(){
        return menu;
    }
    
    public void showError (String txt){
        SettlersDialog sd=new SettlersDialog(this,true);
        sd.setText(txt);        
        sd.show();
    }
    
    public void setAction(byte action){
        board.setAction(action);
    }


}

