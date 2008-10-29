package settlers.game.gui;

import java.util.Random;
import settlers.game.elements.*;
import settlers.game.events.PlayerEvent;
import settlers.game.events.EventManager;
import settlers.game.events.SettlementEvent;
import settlers.game.logic.*;
import settlers.game.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class GameBoard extends JPanel implements MouseListener, MouseMotionListener{

    private final static int universalEdgeLength = 53; // The universal length of every edge/road/line.
    private final static int universalStepLength = (int)( universalEdgeLength * 0.7071d ); // Geometry
    private byte action=-1;
    
    private int dx, dy;
    private boolean resized;
    
    Settlement[][] vertex = new Settlement[20][20];
    
    RollBox rollBox;
    SpringLayout thisLayout;
    JLabel img;
    
    Resource[] resource = new Resource[13];
    
    BufferedImage bi;
    Graphics2D big;
    Rectangle area;
    boolean firstTime = true;
    
    Settlement[] tempRoad = new Settlement[2];
    Settlement tempSettlement;
    
    Graphics g2;
    
    /*
    *constructor for the gameboard.&  Adds a listener for the mouse and sets the layout for the board.&  Also displays the splash screen and
    *makes the roll box visible and sets the background color
    *param _parent the mainboard that the gameboard is displayed inside
    */
    public GameBoard(MainBoard _parent){        

    dx = 125;
        dy = 100;
        
        resized = false;
        
        addMouseMotionListener(this);
        addMouseListener(this); 
        
        thisLayout = new SpringLayout();
        this.setLayout(thisLayout);
        
        rollBox = new RollBox();
        
        img = new JLabel();
        SplashScreen splash = new SplashScreen(img, this);
        splash.start();
        
        rollBox.setVisible(false);
        this.add(rollBox);
        
        thisLayout.putConstraint(thisLayout.EAST, rollBox, 0, thisLayout.EAST, this);
        thisLayout.putConstraint(thisLayout.NORTH, rollBox, 0, thisLayout.NORTH, this);
        
        this.setBackground(Color.blue);        
    }
    
    /*
    *Populates the vertex array with blank settlements and roads and hard codes the map into the vertex array.&  Also removes the vertices which
    *are outside the gameboard and shouldn't be seen.
    */
    private void initializeBoard()
    {

        // Initializes blank settlement nodes (to cross link nodes, ie: topNode, bottomNode, sideNode. Which helps gives us clean code at a constant big O.
        int ax=0, ay=0;
        for (ax =0; ax < vertex.length; ax++) {
        for (ay =0; ay <vertex[ax].length; ay++) {
        vertex[ax][ay] = new Settlement(ax, ay);                
        }
        }
        
        
        
        /*The initialization os the HARD CODED MAP */
        /* This can be CHANGED fairly easily, but  quite obviously the map has to be hard coded or stored somewhere.*/
        for(ax = 0; ax < vertex.length; ax++) {
            for (ay = 0; ay < vertex[ax].length; ay++) {
                if ( ax == 0 && (ay == 0 || ay == vertex[ax].length-1) )
                { }
                else if (ay == vertex[ax].length-1) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2)) + dx, ay * universalStepLength + dy, null, vertex[ax][ay-1], vertex[ax-1][ay], 1);        
                }
                else if (ay == 0) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2)) + dx, 0 + dy, vertex[ax][ay+1], null, vertex[ax-1][ay], 1);        
                }
                else if (ax == 0) {
                vertex[ax][ay].updateNode(ax, ay, 0  + dx, ay * universalStepLength + dy, vertex[ax][ay+1], vertex[ax][ay-1], null, 1);        
                }
                else if (ay == 0) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))  + dx, 0 + dy, vertex[ax][ay+1], null, vertex[ax-1][ay], 1);        
                }
                else if (ay != 0 && ay == vertex[ax].length-1) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))  + dx, ay * universalStepLength + dy, null, vertex[ax][ay-1], vertex[ax-1][ay], 1);        
                }
                else if (ax%2==0 ^ ay%2 == 0) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))  + dx, ay * universalStepLength + dy, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax-1][ay], 1);        
                }
                else if (ax == vertex.length-1) {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))+(universalStepLength/2), ay * universalStepLength + dy, vertex[ax][ay+1], vertex[ax][ay-1], null, 1);
                }
                else {
                vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))+(universalStepLength/2)  + dx, ay * universalStepLength + dy, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax+1][ay], 1);
                }
                if (ax == 0 || ay == 0 || ay >= 12 || ax >= 7)
                vertex[ax][ay].setOnBoard(0);
            }
        }
        int i = 0;
        Random r = new Random();
        int randomDessert = r.nextInt(19)+1;
        // Initialization of the blank ROADS.
        for (ax =0; ax < vertex.length; ax++)
        {
            for (ay =0; ay <vertex[ax].length; ay++)
            {
                if (ax%2==0 ^ ay%2 == 0)
                {
                    vertex[ax][ay].initializeRoad();
                }
                if ( (  ax%2 == 0 ^ ay%2 == 1  ) && ax < vertex.length-1 && ay < vertex[ax].length-2 && vertex[ax][ay].getOnBoard() ==1 && vertex[ax+1][ay+2].getOnBoard() == 1 )
                {
                    
                    int tileNum=0;
                    
                    
                    do {
                        tileNum = r.nextInt(11)+2;
                    } while (tileNum == 7);
                    i++;
                    int resourceNum = r.nextInt(5)+1;
                    if (i == randomDessert){
                        resourceNum = 0;
                        tileNum = 0;
                    }
                        
                        
                    if (resource[tileNum] == null)
                     vertex[ax][ay].setDrawResourceHelper( resource[tileNum] = new Resource(tileNum, resourceNum, vertex[ax][ay], vertex[ax][ay+1], vertex[ax][ay+2], vertex[ax+1][ay+2], vertex[ax+1][ay+1], vertex[ax+1][ay]));
                    else
                    vertex[ax][ay].setDrawResourceHelper( resource[tileNum].setNext(tileNum, resourceNum, vertex[ax][ay], vertex[ax][ay+1], vertex[ax][ay+2], vertex[ax+1][ay+2], vertex[ax+1][ay+1], vertex[ax+1][ay]));
                    
                }
            }
        }
        
        
        //This function sets tiles that are in the sea to disappear from sight.
        vertex[1][1].setOnBoard(0);
        vertex[2][1].setOnBoard(0);
        vertex[1][2].setOnBoard(0);
        
        vertex[1][10].setOnBoard(0);
        vertex[1][11].setOnBoard(0);
        vertex[2][11].setOnBoard(0);
        
        vertex[6][1].setOnBoard(0);
        vertex[5][1].setOnBoard(0);
        vertex[6][2].setOnBoard(0);
        
        vertex[6][10].setOnBoard(0);
        vertex[5][11].setOnBoard(0);
        vertex[6][11].setOnBoard(0);

        if (resized == true) 
            resizeSmaller();
    }

    
    /*
    *On a mouse click it calls the onClick method and passes the x and y coordinates of the mouse click
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mousePressed(MouseEvent e){
        onClick(e.getX(),e.getY());
    }
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary.
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseDragged(MouseEvent e){}
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseReleased(MouseEvent e){}
    
    /*
    *When the mouse is moved it figures out where the mouse is and figures out which tile it is in.&  This allows for highlighting of buildable
    *roads and settlements.
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseMoved(MouseEvent e){
        if (GameState.getActionState() > 0)
        calculateTile(e.getX(),e.getY());
    }
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary.
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseClicked(MouseEvent e){}
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary.
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseExited(MouseEvent e){}
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary.
    *@param e the mouse event that holds the information about the mouse click
    */
    public void mouseEntered(MouseEvent e){}
    
    /*
    *Typical event used when dealing with mouse events.&  Can be implemented later if necessary
    *@param e the mouse event that holds the information about the mouse click
    */
    public void updateLocation(MouseEvent e){}
    
    /*
    *
    */
    public void paintComponent(Graphics g){
    	g2 = g;
        super.paintComponent(g);
        if (vertex[0][0]!=null){
            update(g);
        }
    }
    
    public void update(Graphics g){
        
        // Clears the rectangle that was previously drawn.
        big.setColor(Color.blue);                
        big.fill(area);        
        
        // Draws and fills the newly positioned rectangle to the buffer.
        big.setStroke(new BasicStroke(0.7f));

        
        int ax, ay;
        big.setPaint(Color.black);
        for(ax = 1; ax < vertex.length-1; ax++) { //7
            for (ay = 1; ay < vertex.length-1; ay++) { //12
                
                Settlement currentNode = vertex[ax][ay];
                Settlement southNode = vertex[ax][ay-1];
                Settlement westNode = null;
                big.setPaint(Color.black );
                if ( (  ax%2 == 0 ^ ay%2 == 1  ) && ax < vertex.length-1 && ay < vertex[ax].length-2 && vertex[ax][ay].getOnBoard() ==1 && vertex[ax+1][ay+2].getOnBoard() == 1)
                {
                    big.drawImage( Toolkit.getDefaultToolkit().getImage( this.getClass().getResource("/Settlers/game/images/resource"+vertex[ax][ay].getDrawResourceHelper().getResourceType() + ".png") ) , southNode.getXcord(), currentNode.getYcord(), null);
                    drawNumber(new Ellipse2D.Double(vertex[ax][ay].getXcord()+(universalStepLength*.5), vertex[ax][ay].getYcord()+(universalStepLength*2/3), (universalStepLength/2),(universalStepLength/2)),  vertex[ax][ay].getDrawResourceHelper().getResourceNumber()); 
                }
                if (( ax%2 == 0 ^ ay%2 == 0 ) && (westNode = vertex[ax-1][ay]).getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
                    
                    if (currentNode.getSideRoad().hasRoad())
                    {
                        big.setStroke(new BasicStroke(5f));
                        //if(currentNode.hasSettlement())
                        big.setPaint(currentNode.getSideRoad().getOwner().getColor());
                        //else
                        // big.setPaint(currentNode.getSideNode().getOwner().getColor());
                        
                    }
                    big.drawLine(currentNode.getXcord(), currentNode.getYcord(), westNode.getXcord(), westNode.getYcord() );
                    
                    
                }
                big.setStroke(new BasicStroke(1.5f));
                big.setPaint(Color.black);

                if (southNode.getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
                    if (currentNode.getBottomRoad().hasRoad())
                    {
                        big.setStroke(new BasicStroke(5f));

                        big.setPaint(currentNode.getBottomRoad().getOwner().getColor());
                    }
                    big.drawLine(currentNode.getXcord(), currentNode.getYcord(), southNode.getXcord(), southNode.getYcord() );
                    
                    
                }
                
                big.setStroke(new BasicStroke(1.5f));
                big.setPaint(Color.black);
                
                if (currentNode.hasSettlement()) {
                    big.setPaint(currentNode.getOwner().getColor());
                    big.fillOval(currentNode.getXcord()-7,currentNode.getYcord()-7,14,14);
                }
                
                
                
            }
        }
        if (tempRoad[0] != null) {
            big.setPaint(Color.yellow );
            big.setStroke(new BasicStroke(5f));
            big.drawLine( tempRoad[0].getXcord(), tempRoad[0].getYcord(), tempRoad[1].getXcord(), tempRoad[1].getYcord() );
        }        
        
        if (tempSettlement != null) {
            big.setPaint(Color.yellow);
            big.setStroke(new BasicStroke(5f));
            big.drawOval( tempSettlement.getXcord()-10, tempSettlement.getYcord()-10, 20, 20 );
        }        
        
        // Draws the buffered image to the screen.
        g.drawImage(bi, 0, 0, this);        
        
    }

    
    
    // taken from the original code base.
    private void drawNumber(Ellipse2D number, int value){
        if (value==0)
            return;
        big.setPaint (Color.white);
        big.fill(number);
        big.setPaint(Color.black);
        big.draw(number);        
        int x=(int)(number.getCenterX());
        int y=(int)(number.getCenterY());
        String txt=Integer.toString(value);
        switch (value){        
            case 8:
            case 6:{                 
                 big.setFont(new Font("Dialog.plain",Font.BOLD,18));                 
                 big.setPaint (Color.red);
                 x=(int)(x-(4.5)*(txt.length()));
                 y=y+7;
                 break;
            }
            case 9:
            case 5:{
                 big.setFont(new Font("Dialog.plain",Font.BOLD,18));
                 big.setPaint (Color.black);
                 x=(int)(x-(4.5)*(txt.length()));
                 y=y+7;
                 break;
            }
            case 10:
            case 4:{
                 big.setFont(new Font("Dialog.plain",0,14));
                 big.setPaint (Color.black);
                 x=x-4*(txt.length());
                 y=y+6;
                 break;
            }
            case 11:
            case 3:{
                 big.setFont(new Font("Dialog.plain",0,12));
                 big.setPaint (Color.black);
                 x=(int)(x-(3.5)*(txt.length()));
                 y=y+5;
                 break;
            }
            case 12:
            case 2:{
                 big.setFont(new Font("Dialog.plain",0,8));
                 big.setPaint (Color.black);
                 x=x-2*(txt.length());
                 y=y+4;
                 break;
            }
        }
                
        big.drawString(txt, x, y);
    }
     
     
    
    public void initialize(){
        this.remove(img);
        Dimension dim = getSize();
        int w = dim.width;
        int h = dim.height;
        
        
        bi = (BufferedImage)createImage(w, h);            
        big = bi.createGraphics();
        big.setColor(Color.black);
        big.setStroke(new BasicStroke(0.1f));
        big.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        area = new Rectangle(dim);
        
        initializeBoard();
        rollBox.setVisible(true);
        repaint();
    }
    

    
    private void onClick(int x, int y){
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_SETTLEMENT && tempSettlement != null) {
            if (GameState.getGamePhase() == GlobalVar.GAME_INIT)
            {
	            SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", tempSettlement);
	            EventManager.callEvent(se);
            }
            else
            {
            	tempSettlement.buildSettlement();
            	PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_SETTLEMENT", GameState.getCurPlayer());
            	EventManager.callEvent(pe);
            }
            tempSettlement = null;
        }
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_ROAD && tempRoad[0] != null)
        {
            if (GameState.getGamePhase() == GlobalVar.GAME_INIT)
            {
            	SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_ROAD", tempRoad[0], tempRoad[1]);
            	EventManager.callEvent(se);
           }
           else 
           { 
        	    tempRoad[0].buildRoad(tempRoad[1]); 
	           	PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_ROAD", GameState.getCurPlayer());
	        	EventManager.callEvent(pe);
           }
            tempRoad[0] = null;
        }
        repaint();
        
    }
    
    private void calculateTile(int x, int y){
        
        int y1;
        if (!resized)
        {
            y1 = (int)Math.round((double)(y-dy) / universalStepLength );
            if(y1 < 0)
                y1 = 0;
        }
        else    //It was resized
        {
            y1 = (int)Math.round((double)(y) / universalStepLength );
            if(y1 < 0)
                y1 = 0;
        }
            
        int x1 = (int)Math.round((double)(x-dx) / (universalEdgeLength+( universalStepLength/2 ) ));
        if(x1 < 0)
        x1 = 0;
        
        Settlement curNode = vertex[x1][y1];

        tempSettlement = null;
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_SETTLEMENT){
            if (x > curNode.getXcord() - universalEdgeLength/2 && x < curNode.getXcord() + universalEdgeLength/2 && y > curNode.getYcord() - universalEdgeLength/2 && y < curNode.getYcord() + universalEdgeLength/2)
            if (curNode.canBuildSettlement()) {
                tempSettlement = vertex[x1][y1];
            }
        }
        tempRoad[0] = null;
        
        if(GameState.getActionState() == GlobalVar.ACTION_ADD_ROAD)
        {    // NEEDS TO BE REWRITTEN
            int distTop = 1000, distBottom = 1000, distSide = 1000;
            int curPID = GameState.getCurPlayer().getID();
            try
            {
                if( ( curNode.hasSettlement() && curNode.getOwner().getID() == curPID) || ( curNode.getTopRoad().hasRoad() && curNode.getTopRoad().getOwner().getID() == curPID ) || (curNode.getSideRoad().hasRoad()  && curNode.getSideRoad().getOwner().getID() == curPID ) || ( curNode.getBottomRoad().hasRoad()  && curNode.getBottomRoad().getOwner().getID() == curPID ) )
                {
                    distTop = (int)Math.sqrt( Math.pow(curNode.getTopNode().getXcord() - x,2 ) + Math.pow(curNode.getTopNode().getYcord() - y,2 ) );
                    distBottom = (int)Math.sqrt( Math.pow(curNode.getBottomNode().getXcord() - x,2 ) + Math.pow(curNode.getBottomNode().getYcord() - y,2 ) );
                    distSide = (int)Math.sqrt( Math.pow(curNode.getSideNode().getXcord() - x,2 ) + Math.pow(curNode.getSideNode().getYcord() - y,2 ) );
                        
                    if(distTop < distBottom && distTop < distSide && !curNode.getTopRoad().hasRoad() && curNode.getTopNode().getOnBoard() != 0)
                    {
                        tempRoad[0] = curNode;
                        tempRoad[1] = curNode.getTopNode();            
                    }
                    else if(distBottom < distTop && distBottom < distSide  && !curNode.getBottomRoad().hasRoad() && curNode.getBottomNode().getOnBoard() != 0)
                    {
                        tempRoad[0] = curNode;
                        tempRoad[1] = curNode.getBottomNode();            
                    } 
                    else if(distSide < distBottom && distSide < distTop && !curNode.getSideRoad().hasRoad() && curNode.getSideNode().getOnBoard() != 0)
                    {
                        tempRoad[0] = curNode;
                        tempRoad[1] = curNode.getSideNode();            
                    }            
                }    
            }
            catch(NullPointerException e) {}
        }
        repaint();
    }
    
    
    public void setAction(byte _action){ 
        tempRoad[0] = null;
        this.action= _action;
        repaint();
    }
    
    public void hideBox(boolean value)
    {
        rollBox.setVisible(value);
    }

    public void resizeSmaller() {
        // TODO Auto-generated method stub
        resized = true;
        if (GameState.getGamePhase() != GlobalVar.GAME_LOADING)
        {
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
            {
                Settlement cur = vertex[i][j];
                cur.updateNode(i, j, cur.getXcord(), cur.getYcord() - dy, cur.getTopNode(), cur.getBottomNode(), cur.getSideNode(), cur.getOnBoard());
            }
        
        repaint();
        }
    }

    public void resizeLarger() {
        // TODO Auto-generated method stub
        
        for(int i = 0; i < 20; i++)
            for(int j = 0; j < 20; j++)
            {
                Settlement cur = vertex[i][j];
                cur.updateNode(i, j, cur.getXcord(), cur.getYcord() + dy, cur.getTopNode(), cur.getBottomNode(), cur.getSideNode(), cur.getOnBoard());
            }    
        resized = false;
        repaint();
    }
    
    public void diceRollResources(int roll)
    {
        if (resource[roll] != null)
            resource[roll].giveResources();
    }
    
    public RollBox getRollBox()
    {
    	return rollBox;
    }
    
    public void getDevCard()
    {
    	DevCard dev = new DevCard(this);
    }
}
