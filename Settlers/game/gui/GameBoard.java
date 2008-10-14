package settlers.game.gui;

import settlers.game.elements.*;
import settlers.game.events.EventManager;
import settlers.game.events.SettlementEvent;
import settlers.game.logic.*;
import settlers.game.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*Applet is not used at the moment
import java.applet.Applet; */
import java.awt.geom.*;
import java.awt.image.BufferedImage;

class GameBoard extends JPanel implements MouseListener, MouseMotionListener, SettlersConstants{
    private final static int RADIUS=46;
	private final static int universalEdgeLength = 54; // The universal length of every edge/road/line.
	private final static int universalStepLength = (int)( universalEdgeLength * 0.7071d ); // Geometry
    private byte action=-1;
	
	Settlement[][] vertex = new Settlement[20][20];
    
    RollBox rollBox;
    SpringLayout thisLayout;
    JLabel img;
	
    int incX=RADIUS*3;
    int incY=(int)Math.sqrt((3d/4d)*(RADIUS*RADIUS));
        
    BufferedImage bi;
    Graphics2D big;
    Rectangle area;
    boolean firstTime = true;
    boolean pressOut = false;
    GeneralPath[] hexagons;
    Ellipse2D[] numbers;
    byte[] colors;
    Tile[] tiles=null;
	
	Settlement[] tempRoad = new Settlement[2];
	Settlement tempSettlement;
    
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
    
    private void initVertex()
    {
		// Initializes blank settlement nodes (to cross link nodes, ie: topNode, bottomNode, sideNode. Which helps gives us clean code at a constant big O.
		int ax=0, ay=0;
		for (ax =0; ax < vertex.length; ax++)
			for (ay =0; ay <vertex[ax].length; ay++)
				vertex[ax][ay] = new Settlement(ax, ay);				
		
		
		
		
		/*The initialization os the HARD CODED MAP */
		/* This can be CHANGED fairly easily, but  quite obviously the map has to be hard coded or stored somewhere.*/
		for(ax = 1; ax < vertex.length-1; ax++) {
			for (ay = 1; ay < vertex.length-1; ay++) {
				if (ax%2==0 ^ ay%2 == 0)
					vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2)), ay * universalStepLength, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax-1][ay]);		
				else
					vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))+(universalStepLength/2), ay * universalStepLength, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax+1][ay]);
				if (ax == 0 || ay == 0 || ay >= 12 || ax >= 7)
					vertex[ax][ay].setOnBoard(0);
			}
		}
		
		// Initialization of the blank ROADS.
		for (ax =0; ax < vertex.length; ax++)
			for (ay =0; ay <vertex[ax].length; ay++)
				if (ax%2==0 ^ ay%2 == 0)
					vertex[ax][ay].initializeRoad();
					
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

    }
    
    public void setTiles(Tile[] _tiles){
        tiles=_tiles;
    }
    
    
    public void mousePressed(MouseEvent e){
        onClick(e.getX(),e.getY());
    }
    
    public void mouseDragged(MouseEvent e){}
    
    public void mouseReleased(MouseEvent e){}
    
    public void mouseMoved(MouseEvent e){
	if (action != -1)
		calculateTile(e.getX(),e.getY());
	}
    
    public void mouseClicked(MouseEvent e){}
    
    public void mouseExited(MouseEvent e){}
   
    public void mouseEntered(MouseEvent e){}
   
    public void updateLocation(MouseEvent e){}
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (hexagons!=null){
            update(g);
        }
    }
    
    public void update(Graphics g){
        
        Graphics2D g2 = (Graphics2D)g;
        Dimension dim = getSize();
        int w = dim.width;
        int h = dim.height;
        
        if(firstTime){
            bi = (BufferedImage)createImage(w, h);            
            big = bi.createGraphics();
            big.setColor(Color.black);
            big.setStroke(new BasicStroke(0.1f));
            big.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            area = new Rectangle(dim);
            
            firstTime = false;
        }
        
		
	
		
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
				if (( ax%2 == 0 ^ ay%2 == 0 ) && (westNode = vertex[ax-1][ay]).getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
					//drawHexagon
					if (currentNode.getSideRoad().hasRoad())
					{
						big.setStroke(new BasicStroke(5f));
						if(currentNode.hasSettlement())
							big.setPaint(currentNode.getOwner().getColor());
						else
							big.setPaint(currentNode.getSideNode().getOwner().getColor());
					}
					big.drawLine(currentNode.getXcord(), currentNode.getYcord(), westNode.getXcord(), westNode.getYcord() );
					
				}
				big.setStroke(new BasicStroke(.7f));
				big.setPaint(Color.black);

				if (southNode.getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
					if (currentNode.getBottomRoad().hasRoad())
					{
						big.setStroke(new BasicStroke(5f));
						if(currentNode.hasSettlement()) 
							big.setPaint(currentNode.getOwner().getColor());
						else
							big.setPaint(currentNode.getBottomNode().getOwner().getColor());
					}
					big.drawLine(currentNode.getXcord(), currentNode.getYcord(), southNode.getXcord(), southNode.getYcord() );
				}
				
				big.setStroke(new BasicStroke(.7f));
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
        g2.drawImage(bi, 0, 0, this);        
        
    }
 
    
    public void initialize(Tile[] _tiles){
    	this.remove(img);
    	initVertex();
    	rollBox.setVisible(true);
        hexagons=new GeneralPath[19];
        numbers=new Ellipse2D[19];
        repaint();
    }
    

	
	private void onClick(int x, int y){
	if (action == ACTION_ADD_SETTLEMENT && tempSettlement != null) {
		SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_SETTLEMENT", tempSettlement);
		EventManager.callEvent(se);
		//tempSettlement.buildSettlement();
		tempSettlement = null;
	}
	if (action == ACTION_ADD_ROAD && tempRoad[0] != null)
	{
		//tempRoad[0].buildRoad(tempRoad[1]);
		SettlementEvent se = new SettlementEvent("PLAYER_INIT_ATTEMPT_ROAD", tempRoad[0], tempRoad[1]);
		EventManager.callEvent(se);
		tempRoad[0] = null;
	}
	repaint();
	
	}
	
    private void calculateTile(int x, int y){
        
		int y1 = (int)Math.round((double)(y-100) / universalStepLength );
		if(y1 < 0)
			y1 = 0;
		int x1 = (int)Math.round((double)(x-135) / (universalEdgeLength+( universalStepLength/2 ) ));
		if(x1 < 0)
			x1 = 0;
		
		Settlement curNode = vertex[x1][y1];

		tempSettlement = null;
		if (action == ACTION_ADD_SETTLEMENT){
			if (x > curNode.getXcord() - universalEdgeLength/2 && x < curNode.getXcord() + universalEdgeLength/2 && y > curNode.getYcord() - universalEdgeLength/2 && y < curNode.getYcord() + universalEdgeLength/2)
				if (curNode.canBuildSettlement()) {
					tempSettlement = vertex[x1][y1];
				}
		}
		tempRoad[0] = null;
		
		if(action == ACTION_ADD_ROAD)
		{
			int distTop = 1000, distBottom = 1000, distSide = 1000;
			try
			{
				if(x1 != 0 && y1 != 0 && (curNode.hasSettlement() || curNode.getTopRoad().hasRoad() || curNode.getSideRoad().hasRoad() || curNode.getBottomRoad().hasRoad()))
				{
					if(curNode.getTopNode().getOnBoard() != 0)
					{
						distTop = (int)Math.sqrt( Math.pow(curNode.getTopNode().getXcord() - x,2 ) + Math.pow(curNode.getTopNode().getYcord() - y,2 ) );
					}
					
					if(curNode.getBottomNode().getOnBoard() != 0)
					{
						distBottom = (int)Math.sqrt( Math.pow(curNode.getBottomNode().getXcord() - x,2 ) + Math.pow(curNode.getBottomNode().getYcord() - y,2 ) );
					}
					
					if(curNode.getSideNode().getOnBoard() != 0)
					{
						distSide = (int)Math.sqrt( Math.pow(curNode.getSideNode().getXcord() - x,2 ) + Math.pow(curNode.getSideNode().getYcord() - y,2 ) );
					}
					
					if(distTop < distBottom && distTop < distSide)
					{
						tempRoad[0] = curNode;
						tempRoad[1] = curNode.getTopNode();			
					}
					
					else if(distBottom < distTop && distBottom < distSide)
					{
						tempRoad[0] = curNode;
						tempRoad[1] = curNode.getBottomNode();			
					}
					
					else if(distSide < distBottom && distSide < distTop)
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
}

