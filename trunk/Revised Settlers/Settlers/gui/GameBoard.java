package Settlers.gui;

import Settlers.logic.*;
import Settlers.*;

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
	
	Node[][] vertex = new Node[20][20];
    
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
	
	int[] tempRoad = {-1,-1,-1,-1};
	int[] tempSettlement = { -1, -1 };
    
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
		int ax=0, ay=0;
		for (ax =0; ax < vertex.length; ax++)
			for (ay =0; ay <vertex[ax].length; ay++)
				vertex[ax][ay] = new Node(ax, ay);				// Initializes the node objects.
				
		
		
		/*The rest of the initialization is the HARD CODED MAP */
		/* This can be CHANGED fairly easily, but  quite obviously the map has to be hard coded or stored somewhere.*/
		for(ax = 1; ax < 8; ax++) {
			for (ay = 1; ay < 13; ay++) {
				if (ax%2==0 ^ ay%2 == 0)
					vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2)), ay * universalStepLength, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax-1][ay]);		
				else
					vertex[ax][ay].updateNode(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))+(universalStepLength/2), ay * universalStepLength, vertex[ax][ay+1], vertex[ax][ay-1], vertex[ax+1][ay]);
				if (ax == 0 || ay == 0 || ay == 12 || ax == 7)
					vertex[ax][ay].setOnBoard(0);
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
		for(ax = 1; ax < 7; ax++) {
			for (ay = 1; ay < 12; ay++) {
				Node currentNode = vertex[ax][ay];
				Node southNode = vertex[ax][ay-1];
				Node westNode = null;
				big.setPaint(Color.black );
				if (( ax%2 == 0 ^ ay%2 == 0 ) && (westNode = vertex[ax-1][ay]).getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
					//drawHexagon
					big.drawLine(currentNode.getXcord(), currentNode.getYcord(), westNode.getXcord(), westNode.getYcord() );
				}
				if (southNode.getOnBoard() == 1 && currentNode.getOnBoard() == 1) {
					big.drawLine(currentNode.getXcord(), currentNode.getYcord(), southNode.getXcord(), southNode.getYcord() );
				}
				
				if (currentNode.hasSettlement()) {
					big.setPaint(Color.green);
					big.fillOval(currentNode.getXcord()-7,currentNode.getYcord()-7,14,14);
				}
				
				
			}
		}
		if (tempRoad[0] != -1) {
			big.setPaint(Color.yellow );
			big.setStroke(new BasicStroke(5f));
			big.drawLine( tempRoad[0], tempRoad[1], tempRoad[2], tempRoad[3] );
		}		
        
		if (tempSettlement[0] != -1) {
			big.setPaint(Color.yellow);
			big.setStroke(new BasicStroke(5f));
			big.drawOval( vertex[tempSettlement[0]][tempSettlement[1]].getXcord()-10, vertex[tempSettlement[0]][tempSettlement[1]].getYcord()-10, 20, 20 );
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
	if (action == ACTION_ADD_SETTLEMENT && tempSettlement[0] != -1) {
		vertex[tempSettlement[0]][tempSettlement[1]].buildSettlement();
		tempSettlement[0] = tempSettlement[1] = -1;
	}
	repaint();
	
	}
    private void calculateTile(int x, int y){
        
		int y1 = (int)Math.floor( (y-95) / universalStepLength );
		if(y1 < 0)
			y1 = 0;
		int x1 = (int)Math.floor( (x-120) / (universalEdgeLength+( universalStepLength/2 ) ));
		if(x1 < 0)
			x1 = 0;

		tempSettlement[0] = tempSettlement[1] = -1;
		if (action == ACTION_ADD_SETTLEMENT){
		for (int i = x1; i < x1+1; i++) 
			for (int j = y1; j < y1+1; j++) {
				if (x > vertex[i][j].getXcord() - universalEdgeLength/2 && x < vertex[i][j].getXcord() + universalEdgeLength/2 && y > vertex[i][j].getYcord() - universalEdgeLength/2 && y < vertex[i][j].getYcord() + universalEdgeLength/2)
					if (vertex[i][j].canBuildSettlement()) {
						tempSettlement[0] = i;
						tempSettlement[1] = j;
					}
			}
		}
		tempRoad[0] = tempRoad[1] = tempRoad[2] = tempRoad[3] = -1;
		
		if (action == ACTION_ADD_ROAD)
		{
			int temp = 1000;
			int temp2 = (int)Math.sqrt( Math.pow(vertex[x1][y1+1].getXcord() - x,2 ) + Math.pow(vertex[x1][y1+1].getYcord() - y,2 ) );
			if ( vertex[x1][y1].hasSettlement() || vertex[x1][y1+1].hasSettlement() )
			{
				temp=temp2;
				System.out.println("1st if (" + x1 + ", " + y1 + ")");
				tempRoad[0] = vertex[x1][y1].getXcord();
				tempRoad[1] = vertex[x1][y1].getYcord();
				tempRoad[2] = vertex[x1][y1+1].getXcord();
				tempRoad[3] = vertex[x1][y1+1].getYcord();
				
			}
			else if ( temp > (temp2 = (int)Math.sqrt( Math.pow(vertex[x1+1][y1].getXcord() - x,2 ) + Math.pow(vertex[x1+1][y1].getYcord() - y,2 ) ) ) && ( vertex[x1][y1].hasSettlement() || vertex[x1+1][y1].hasSettlement() ) )
			{
				temp = temp2;
				System.out.println("2nd if (" + x1 + ", " + y1 + ")");
				tempRoad[0] = vertex[x1+1][y1].getXcord();
				tempRoad[1] = vertex[x1+1][y1].getYcord();
				tempRoad[2] = vertex[x1][y1].getXcord();
				tempRoad[3] = vertex[x1][y1].getYcord();
				
			}
			else if (temp > (temp2 = (int)Math.sqrt( Math.pow(vertex[x1+1][y1+1].getXcord() - x ,2) + Math.pow(vertex[x1+1][y1+1].getYcord() - y,2 ) ) ) && ( vertex[x1+1][y1+1].hasSettlement() || vertex[x1][y1+1].hasSettlement() ))
			{
				temp = temp2;
				System.out.println("3rd if (" + x1 + ", " + y1 + ")");
				tempRoad[0] = vertex[x1+1][y1+1].getXcord();
				tempRoad[1] = vertex[x1+1][y1+1].getYcord();
				tempRoad[2] = vertex[x1][y1+1].getXcord();
				tempRoad[3] = vertex[x1][y1+1].getYcord();
			}
			else if (temp > (temp2 = (int)Math.sqrt( Math.pow(vertex[x1+1][y1+1].getXcord() - x ,2) + Math.pow(vertex[x1+1][y1+1].getYcord() - y ,2) ) ) && ( vertex[x1+1][y1+1].hasSettlement() || vertex[x1+1][y1].hasSettlement() ))
			{
				temp = temp2;
				System.out.println("4th if (" + x1 + ", " + y1 + ")");
				tempRoad[0] = vertex[x1+1][y1+1].getXcord();
				tempRoad[1] = vertex[x1+1][y1+1].getYcord();
				tempRoad[2] = vertex[x1+1][y1].getXcord();
				tempRoad[3] = vertex[x1+1][y1].getYcord();
			}
			else {
				tempRoad[0] = tempRoad[1] = tempRoad[2] = tempRoad[3] = -1;
			}
			
		}
		repaint();

    }
    
    
    public void setAction(byte _action){ 
		tempRoad[0] = tempRoad[1] = tempRoad[2] = tempRoad[3] = -1;
		this.action= _action;
		repaint();
    }
    
    public void hideBox(boolean value)
    {
    	rollBox.setVisible(value);
    }
}

