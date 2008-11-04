package settlers.game.gui;

import java.util.Random;
import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.PlayerEvent;
import settlers.game.events.SettlementEvent;
import settlers.game.events.RoadEvent;
import settlers.game.logic.*;
import settlers.game.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class GameBoard extends JPanel implements MouseListener, MouseMotionListener{

    
    Random r = new Random();
    private int xPadding, yPadding;
    private boolean resized;
    int edgeLength , stepLength;
    
    Settlement[][] vertex = null;
    
    RollBox rollBox;
    SpringLayout thisLayout;
    JLabel img;
    
    Resource[] resource;
    
    BufferedImage bi;
    Graphics2D big;
    Rectangle area;
    boolean firstTime = true;
    
    Road tempRoad;
    Settlement tempSettlement;
    Settlement tempRobber;
    
    Settlement robber;
    
     Graphics g2;
     
    /**
    * Constructor for the gameboard.&  Adds a listener for the mouse and sets 
    * the layout for the board.&  Also displays the splash screen and
    * makes the roll box visible and sets the background color
    * @param _parent the mainboard that the gameboard is displayed inside
    */
    public GameBoard(MainBoard _parent){        

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
    // Initializes blank settlement nodes (to cross link nodes, ie: topNode, bottomNode, sideNode.)
    private void clearMap()
    {
        vertex = new Settlement[GlobalVar.MAP.length][GlobalVar.MAP[0].length];
        for (int ay =0; ay < vertex.length; ay++) {
            for (int ax =0; ax <vertex[ay].length; ax++) {
                vertex[ay][ax] = new Settlement(ay, ax);                
            }
        }
    }
    
    private void linkMap()
    {
        Dimension dim = getSize();
        int w = dim.width;
        int h = dim.height;
        System.out.println(w + "\n" + h );
        System.out.println(GlobalVar.MAP.length);
        //if ( h / (( GlobalVar.MAP.length / 2) + 1 ) < 
        edgeLength = (int) ( h /( ( GlobalVar.MAP.length / 2 )+2) );
        stepLength = (int)(edgeLength * 0.7071d);
        yPadding = (int)((h - ( (edgeLength + (edgeLength * .7071d))*(GlobalVar.MAP.length-3)/2) )/2);
        xPadding = (int)(  (w - ( ((edgeLength * 2 * .7071d) + edgeLength)/ 2) * (GlobalVar.MAP[0].length+1) )/2);
        
        
        for(int ay = 0; ay < vertex.length; ay++) {
            for (int ax = 0; ax < vertex[ay].length; ax++) {
                if (GlobalVar.MAP[ay][ax] == 0) {
                    vertex[ay][ax].setOnBoard(0);   
                } else {
                    if (ax%2== GlobalVar.ODD_SWITCH ^ ay%2 == GlobalVar.ODD_SWITCH) {
                        vertex[ay][ax].updateNode(ax, ay, ax*(edgeLength+(stepLength/2))  + xPadding, ay * stepLength + yPadding, ( GlobalVar.MAP[ay-1][ax] == 0 ? null : vertex[ay-1][ax]), ( GlobalVar.MAP[ay+1][ax] == 0 ? null : vertex[ay+1][ax]), ( GlobalVar.MAP[ay][ax-1] == 0 ? null : vertex[ay][ax-1]), 1);        
                        vertex[ay][ax].initializeRoad();
                    } else {
                        vertex[ay][ax].updateNode(ax, ay, ax*(edgeLength+(stepLength/2))+(stepLength/2)  + xPadding, ay * stepLength + yPadding, ( GlobalVar.MAP[ay-1][ax] == 0 ? null : vertex[ay-1][ax]), ( GlobalVar.MAP[ay+1][ax] == 0 ? null : vertex[ay+1][ax]), ( GlobalVar.MAP[ay][ax+1] == 0 ? null : vertex[ay][ax+1]), 1);
                    }
                    
                }
            }
        }
    }
    

    private void resourceMap()
    {
        resource = new Resource[13];
        int rType[] = GlobalVar.RESOURCE_TYPE_ARRAY;
        int rNum[] = GlobalVar.RESOURCE_NUMBER_ARRAY;
        int i = 0, index;
        boolean placedDesert = false;
        shuffleArray(rType);
        shuffleArray(rNum);
        

        for (int ay =0; ay < vertex.length; ay++) {
            for (int ax =0; ax <vertex[ay].length; ax++) {
                if ( GlobalVar.MAP[ay][ax] == 2 ) {
                    index = (placedDesert ? i - 1 : i);  
                    if (resource[ rNum[index] ] == null)
                        vertex[ay][ax].setDrawResourceHelper( resource[ rNum[index] ] = new Resource( (rType[i] == GlobalVar.DESERT ? 0 : rNum[index]), rType[i], vertex[ay][ax], vertex[ay+1][ax], vertex[ay+2][ax], vertex[ay+2][ax+1], vertex[ay+1][ax+1], vertex[ay][ax+1]));
                    else
                        vertex[ay][ax].setDrawResourceHelper( resource[rNum[index]].setNext((rType[i] == GlobalVar.DESERT ? 0 : rNum[index]), rType[i], vertex[ay][ax], vertex[ay+1][ax], vertex[ay+2][ax], vertex[ay+2][ax+1], vertex[ay+1][ax+1], vertex[ay][ax+1]));
                    if (rType[i] == GlobalVar.DESERT){
                        placedDesert = true;
                        robber = vertex[ay][ax]; 
                    }
                    i++;
                }
            }
        }
    }
    
    private void paintMap()
    {
        for(int ay = 0; ay < vertex.length; ay++) { //7
            for (int ax = 0; ax < vertex[ay].length; ax++) { //12
                if (GlobalVar.MAP[ay][ax] == 0)
                    continue;
                    
                big.setPaint(Color.black );
                if ( GlobalVar.MAP[ay][ax] == 2)
                {
                    big.drawImage( Toolkit.getDefaultToolkit().getImage( this.getClass().getResource("/settlers/game/images/resource"+vertex[ay][ax].getDrawResourceHelper().getResourceType() + ".png") ) , vertex[ay+1][ax].getXcord(), vertex[ay][ax].getYcord(),edgeLength + (int)(edgeLength * .7071d), (int)(edgeLength * 2 * .7071d), null);
                    drawNumber(new Ellipse2D.Double(vertex[ay][ax].getXcord()+(stepLength*.5), vertex[ay][ax].getYcord()+(stepLength*2/3), (stepLength/2),(stepLength/2)),  vertex[ay][ax].getDrawResourceHelper().getResourceNumber()); 
                }
                
                Settlement currentNode = vertex[ay][ax];
                Settlement bottomNode = vertex[ay+1][ax];
                Settlement westNode = null;
                big.setPaint(Color.black );
                
                if (( ax%2 == 0 ^ ay%2 == 0 ) && (westNode = vertex[ay][ax-1]).getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
                    
                    if (currentNode.getSideRoad().hasRoad())
                    {
                        big.setStroke(new BasicStroke(5f));
                        //if(currentNode.hasSettlement())
                        big.setPaint(currentNode.getSideRoad().getOwner().getColor());
                        //else
                        // big.setPaint(currentNode.getSideNode().getOwner().getColor());
                        
                    }
                    big.drawLine(currentNode.getXcord(), currentNode.getYcord(), westNode.getXcord(), westNode.getYcord() );
                    big.setStroke(new BasicStroke(0.7f));
                    
                }
                big.setStroke(new BasicStroke(1.5f));
                big.setPaint(Color.black);

                if (bottomNode.getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
                    if (currentNode.getBottomRoad().hasRoad())
                    {
                        big.setStroke(new BasicStroke(5f));
                        big.setPaint(currentNode.getBottomRoad().getOwner().getColor());
                    }
                    big.drawLine(currentNode.getXcord(), currentNode.getYcord(), bottomNode.getXcord(), bottomNode.getYcord() );
                    big.setStroke(new BasicStroke(0.7f));
                    
                }
            }
        }
        
        
        /*The initialization of the HARD CODED MAP */
        /* This can be CHANGED fairly easily, but  quite obviously the map has to be hard coded or stored somewhere.*/
        
        
  
        //This function sets tiles that are in the sea to disappear from sight.

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
        super.paintComponent(g);
        if (vertex != null){
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
        
        paintMap();
        
        for(ay = 0; ay < vertex.length; ay++) { //7
            for (ax = 0; ax < vertex[ay].length; ax++) { //12
                if (GlobalVar.MAP[ay][ax] == 0)
                    continue;
                    
                Settlement currentNode = vertex[ay][ax];
                Settlement bottomNode = vertex[ay+1][ax];
                Settlement westNode = null;
                big.setPaint(Color.black );
               
                
                big.setStroke(new BasicStroke(1.5f));
                big.setPaint(Color.black);
                
                if (currentNode.hasSettlement()) {
                    big.setPaint(currentNode.getOwner().getColor());
                    big.fillOval(currentNode.getXcord()-7,currentNode.getYcord()-7,14,14);
                }
                
                
                
            }
        }
        if (tempRoad != null) {
            big.setPaint(GameState.getCurPlayer().getColor() );
            big.setStroke(new BasicStroke(5f));
            big.drawLine( tempRoad.getS1().getXcord(), tempRoad.getS1().getYcord(), tempRoad.getS2().getXcord(), tempRoad.getS2().getYcord() );
            big.setStroke(new BasicStroke(0.7f));
        }        
        
        if (tempSettlement != null) {
            big.setPaint(GameState.getCurPlayer().getColor());
            big.setStroke(new BasicStroke(5f));
            big.drawOval( tempSettlement.getXcord()-10, tempSettlement.getYcord()-10, 20, 20 );
            big.setStroke(new BasicStroke(0.7f));
        }        
        
        if (robber != null) {
            big.drawImage( Toolkit.getDefaultToolkit().getImage( this.getClass().getResource("/Settlers/game/images/sheep.jpg") ) , robber.getXcord()+(stepLength/2), robber.getYcord()+(stepLength*2/3), (int)(stepLength/2), (int)(stepLength / 2), null);
            //big.fillOval(robber.getXcord()-7,robber.getYcord()-7,14,14);
        }
        
        if (tempRobber != null) {
            big.setPaint(GameState.getCurPlayer().getColor() );
            big.setStroke(new BasicStroke(5f));
            big.drawLine( tempRobber.getXcord(), tempRobber.getYcord(), tempRobber.getSideNode().getXcord(), tempRobber.getSideNode().getYcord() );
            big.drawLine( tempRobber.getXcord(), tempRobber.getYcord(), tempRobber.getBottomNode().getXcord(), tempRobber.getBottomNode().getYcord() );
            big.drawLine( tempRobber.getBottomNode().getXcord(), tempRobber.getBottomNode().getYcord(), tempRobber.getBottomNode().getBottomNode().getXcord(), tempRobber.getBottomNode().getBottomNode().getYcord() );
            big.drawLine( tempRobber.getBottomNode().getBottomNode().getXcord(), tempRobber.getBottomNode().getBottomNode().getYcord(), tempRobber.getBottomNode().getBottomNode().getSideNode().getXcord(), tempRobber.getBottomNode().getBottomNode().getSideNode().getYcord() );
            big.drawLine( tempRobber.getSideNode().getXcord(), tempRobber.getSideNode().getYcord(), tempRobber.getSideNode().getBottomNode().getXcord(), tempRobber.getSideNode().getBottomNode().getYcord() );
            big.drawLine( tempRobber.getSideNode().getBottomNode().getXcord(), tempRobber.getSideNode().getBottomNode().getYcord(), tempRobber.getSideNode().getBottomNode().getBottomNode().getXcord(), tempRobber.getSideNode().getBottomNode().getBottomNode().getYcord() );
            big.setStroke(new BasicStroke(0.7f));
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
        
        clearMap();
        linkMap();
        resourceMap();
        //paintMap();
        
                
        //if (resized == true) 
        //    resizeSmaller();
        
        rollBox.setVisible(true);
        repaint();
    }
    
    public void startNewMap(){
        clearMap();
        linkMap();
        resourceMap();
    }

    
    private void onClick(int x, int y){
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_SETTLEMENT && tempSettlement != null) {
            if (GameState.getGamePhase() == GlobalVar.GAME_INIT) {
            SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", tempSettlement);
            EventManager.callEvent(se);
            }
            else {
                tempSettlement.buildSettlement();
                PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_SETTLEMENT", GameState.getCurPlayer());
                EventManager.callEvent(pe);
                }
            
            tempSettlement = null;
        }
        if (GameState.getActionState() == GlobalVar.ACTION_MOVE_ROBBER && tempRobber != null)
        {
            robber.getDrawResourceHelper().removeThief();
            robber = tempRobber;
            robber.getDrawResourceHelper().placeThief();
            tempRobber = null;
            GameState.setActionState(0);
            
        }
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_ROAD && tempRoad != null)
        {
            if (GameState.getGamePhase() == GlobalVar.GAME_INIT)
            {
                RoadEvent se = new RoadEvent("PLAYER_INIT_ATTEMPT_ROAD", tempRoad);
                
                EventManager.callEvent(se);
            }
            else { 
                tempRoad.buildRoad(); 
                PlayerEvent pe = new PlayerEvent("PLAYER_BUILD_ROAD", GameState.getCurPlayer());
                EventManager.callEvent(pe);
                
            }
            tempRoad = null;

        }
        repaint();
        
    }
    
    private int calculateDistance(int x, int y, int i, int j)
    {

        return (int)Math.sqrt( Math.pow( x - vertex[j][i].getXcord(),2) + Math.pow( y - vertex[j][i].getYcord(),2) );
    }
    
    private void calculateTile(int x, int y){
        tempSettlement = null;
        tempRoad = null;
        tempRobber = null;
        int lowEstimateX = (int)((x - xPadding - (edgeLength/2)) /  (edgeLength+(stepLength/2)) );
        int lowEstimateY = (int)((y - yPadding - (stepLength/2)) / (stepLength));
        if (lowEstimateX < 0 || lowEstimateY < 0)
        {
            lowEstimateX = 0;
            lowEstimateY = 0;
        } else if ( lowEstimateY >= vertex.length || lowEstimateX >= vertex[0].length ) {
            lowEstimateY = vertex.length-1;
            lowEstimateX = vertex[0].length-1;
        }
        
        Settlement lowEstimateNode = vertex[lowEstimateY][lowEstimateX];
        
        
       
        if (GameState.getActionState() == GlobalVar.ACTION_ADD_SETTLEMENT){
            int m=0, n=0; 
            int min=1000, tempMin;
            for (int j = lowEstimateY; j < lowEstimateY+3; j++) {
                for (int i = lowEstimateX; i < lowEstimateX + 2; i++) {
                    if (j < vertex.length && i < vertex[0].length && (tempMin = calculateDistance(x,y,i,j)) < min)
                    {
                        min = tempMin;
                        m = i;
                        n = j;
                    }
                }
            }
            if (vertex[n][m].canBuildSettlement())
                tempSettlement = vertex[n][m];
        }
        
        
        if(GameState.getActionState() == GlobalVar.ACTION_ADD_ROAD)
        {
            Road tRoad = null;
            int min=1000, tempMin;
            for (int j = lowEstimateY; j < lowEstimateY+3; j++) {
                for (int i = lowEstimateX; i < lowEstimateX + 2; i++) {
                    if (j < vertex.length && i < vertex[0].length && vertex[j][i].getOnBoard() ==1 && (tempMin = calculateDistance(x,y,i,j)) < min)
                    {
                        min = tempMin;
                        int distanceSide = calculateDistance(x,y,i-1,j);
                        int distanceNorth = calculateDistance(x,y,i,j-1);
                        int distanceSouth = calculateDistance(x,y,i,j+1);
                        if (distanceSide < distanceNorth && distanceSide < distanceSouth)
                        {
                            tRoad = vertex[j][i].getSideRoad();
                        } else if (distanceNorth < distanceSide && distanceNorth < distanceSouth) {
                            tRoad = vertex[j][i].getTopRoad();
                        } else {
                            tRoad = vertex[j][i].getBottomRoad();
                        }
                    }
                }
            }
            if ( tRoad != null && tRoad.canBuildRoad())
                tempRoad = tRoad;
        }
        
        if(GameState.getActionState() == GlobalVar.ACTION_MOVE_ROBBER)
        {   
            int m=0, n=0; 
            int min=1000, tempMin;
            for (int j = lowEstimateY; j < lowEstimateY+3; j++) {
                for (int i = lowEstimateX; i < lowEstimateX + 2; i++) {
                    if (j < vertex.length && i < vertex[0].length && (tempMin = calculateDistance(x,y,i,j)) < min)
                    {
                        min = tempMin;
                        m = i;
                        n = j;
                    }
                }
            }
            if (GlobalVar.MAP[n][m] == 2 && robber != vertex[n][m])
                tempRobber = vertex[n][m];
         
        
         
        }
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
            linkMap();
            paintMap();
            repaint();
        }
        
    }

    public void resizeLarger() {
        // TODO Auto-generated method stub
        resized = false;
        if (GameState.getGamePhase() != GlobalVar.GAME_LOADING)
        {
            linkMap();
            paintMap();
            repaint();
        }
        
    }
    
    public void diceRollResources(int roll)
    {
        if (roll == 7) {
            GameState.setActionState(GlobalVar.ACTION_MOVE_ROBBER);
        }
        else if (resource[roll] != null) {
            resource[roll].giveResources();
        }
    }
    
    
    public RollBox getRollBox()
    {
    	return rollBox;
    }
    
    
        
    private void shuffleArray(int[] ar)
    {
        //--- Shuffle by exchanging each element randomly
        for (int i=0; i<ar.length; i++) {
            int randomPosition = r.nextInt(ar.length);
            int temp = ar[i];
            ar[i] = ar[randomPosition];
            ar[randomPosition] = temp;
        }
    }
    
    public void getDevCard()
    {
        DevCard dev = new DevCard(this);
    }
  
}
