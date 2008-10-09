package Settlers;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/*Applet is not used at the moment
import java.applet.Applet; */
import java.awt.geom.*;
import java.awt.image.BufferedImage;

class Board extends JPanel implements MouseListener, MouseMotionListener, SettlersConstants{
    private final static int RADIUS=46;
	private final static int universalEdgeLength = 54; // The universal length of every edge/road/line.
	private final static int universalStepLength = (int)( universalEdgeLength * 0.7071d ); // Geometry
    private byte action=-1;
	
	Node[][] vertex = new Node[20][20];
    
    SettlersGui parent;
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
    Point[] coordinates=new Point[19];
    Image[] actions=new Image[3];
    Rectangle actionFrame;
    
    public Board(SettlersGui _parent){        
        parent=_parent;
        hexagons=null;        
        numbers=null;        
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);   
		int ax=0, ay=0;
		for(ax = 0; ax < 7; ax++)
		{
			for (ay = 0; ay < 12; ay++)
			{
				if (ax%2==0 ^ ay%2 == 0)
					vertex[ax][ay] = new Node(ax, ay, ax*(universalEdgeLength+(universalStepLength/2)), ay * universalStepLength);		
				else
					vertex[ax][ay] = new Node(ax, ay, ax*(universalEdgeLength+(universalStepLength/2))+(universalStepLength/2), ay * universalStepLength);
				if (ax == 0 || ay == 0)
					vertex[ax][ay].setOnBoard(0);
			}
		}
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
        calculateTile(e.getX(),e.getY());
    }
    
    public void mouseDragged(MouseEvent e){}
    
    public void mouseReleased(MouseEvent e){}
    
    public void mouseMoved(MouseEvent e){}
    
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
        big.setStroke(new BasicStroke(0.5f));
        for (int i=0;i<19;i++){
            drawHexagon(hexagons[i],COLORS[colors[i]]);              
            drawNumber(numbers[i],tiles[i].getProduction());
        }
        for (int i=0;i<19;i++){
            drawSettlements(hexagons[i],tiles[i]);            
            drawRoads(hexagons[i],tiles[i]);
        }
		
		int ax=1, ay=1;
		big.setPaint(Color.black);
		
		for(ax = 0; ax < 7; ax++)
		{
			for (ay = 0; ay < 12; ay++)
			{
				if (ax > 0 && ay > 0)
				{
					
					if ( ax%2 == 0 ^ ay%2 == 0 )
					{
						if (vertex[ax-1][ay].getOnBoard() == 1 && vertex[ax][ay].getOnBoard() == 1)
							big.drawLine(vertex[ax][ay].getXcord(), vertex[ax][ay].getYcord(), vertex[ax-1][ay].getXcord(), vertex[ax-1][ay].getYcord() );
						if (vertex[ax][ay-1].getOnBoard() == 1 && vertex[ax][ay].getOnBoard() == 1)
							big.drawLine(vertex[ax][ay].getXcord(), vertex[ax][ay].getYcord(), vertex[ax][ay-1].getXcord(), vertex[ax][ay-1].getYcord() );
					}
					else {
						if (vertex[ax][ay-1].getOnBoard() == 1 && vertex[ax][ay].getOnBoard() == 1)
							big.drawLine(vertex[ax][ay].getXcord(), vertex[ax][ay].getYcord(), vertex[ax][ay-1].getXcord(), vertex[ax][ay-1].getYcord() );
					}
				}	
			}
		}
				
					
        drawAction();
        
        // Draws the buffered image to the screen.
        g2.drawImage(bi, 0, 0, this);        
        
    }
    
    private GeneralPath createHexagon(int x, int y){
  /*
         *    (RADIUS/2,-incY)  ____(RADIUS*1.5,-incY)
         *                   /     \
         *                  /       \
         *            (0,0) \       /(RADIUS*2,0)
         *                   \_____/
         *      (RADIUS/2,incY)      (RADIUS*1.5,incY)
         *
         */
        int xPoints[] = {x, (int)(x+(RADIUS/2)), (int)(x+(RADIUS*1.5)), (int)(x+(RADIUS*2)), (int)(x+(RADIUS*1.5)), (int)(x+(RADIUS/2))};
        int yPoints[] = {y, y-incY, y-incY, y, y+incY, y+incY};
        
        GeneralPath hexagon = new  GeneralPath(GeneralPath.WIND_EVEN_ODD,  xPoints.length);
        hexagon.moveTo(xPoints[0], yPoints[0]);
        
        for (int i = 1; i<xPoints.length; i++) 	{
            hexagon.lineTo(xPoints[i], yPoints[i]);
            
        };
        hexagon.closePath();        
        return hexagon;
    }
    
    private Ellipse2D createNumber(int x, int y){
        return (new Ellipse2D.Double(x,y,(RADIUS/2),(RADIUS/2)));        
    }
    
    private void drawHexagon(GeneralPath hexagon,Color c){
        big.setPaint(c);
        big.fill(hexagon);        
        big.setPaint(Color.black);
        big.draw(hexagon);                
    }
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
     
    
    private void drawAction(){
        if (action>-1){
            big.setPaint (Color.orange);
            big.setStroke(new BasicStroke(3f));
            big.draw(actionFrame);
            big.drawImage(actions[action],(int)actionFrame.getMinX()+3,(int)actionFrame.getMinY()+3,this);           
        }
    }
    
    public void initialize(Tile[] _tiles){
        tiles=_tiles;
        //Create the hexagons with their appropriate colors
        int i;
        int xIni=263-RADIUS;
        int yIni=incY*2;
        int x,y;
		
		
		
		
        hexagons=new GeneralPath[19];
        numbers=new Ellipse2D[19];
         
        colors=new byte[19];
        for (i=0;i<19;i++){
            colors[i]=tiles[i].getType();
        }
        x=xIni;
        y=yIni;        
        hexagons[0]=createHexagon(x,y);        
        coordinates[0]=new Point(x,y);
        y=y+incY;
        x=xIni-(incX/2);
        for (i=1;i<3;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-incX;
        for (i=3;i<6;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-(incX/2);
        for (i=6;i<8;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-incX;
        for (i=8;i<11;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-(incX/2);
        for (i=11;i<13;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-incX;
        for (i=13;i<16;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni-(incX/2);
        for (i=16;i<18;i++){
            hexagons[i]=createHexagon(x,y);
            coordinates[i]=new Point(x,y);
            x=x+incX;
        }
        y=y+incY;
        x=xIni;
        hexagons[18]=createHexagon(x,y);        
        coordinates[18]=new Point(x,y);
        
        for (i=0;i<19;i++){
            numbers[i]=createNumber((int)hexagons[i].getCurrentPoint().getX()+(RADIUS*3/4),(int)hexagons[i].getCurrentPoint().getY()-(RADIUS/4));
        }
        
        actionFrame=new Rectangle(490,5,25,25);
        actions[0]=new javax.swing.ImageIcon(getClass().getResource("/Settlers/images/settlement.jpg")).getImage();
        actions[1]=new javax.swing.ImageIcon(getClass().getResource("/Settlers/images/city.jpg")).getImage();
        actions[2]=new javax.swing.ImageIcon(getClass().getResource("/Settlers/images/road.jpg")).getImage();
        
        repaint();
    }
    
    private void drawSettlements(GeneralPath hexagon, Tile c){
        byte[] settlements=c.getSettlements();
        int x=(int)hexagon.getCurrentPoint().getX();
        int y=(int)hexagon.getCurrentPoint().getY();
        int actX;
        int actY;
        
        
        
        if (settlements[0]!=0){
            actX=x+2*RADIUS;
            actY=y;
            drawSettlement(actX,actY,settlements[0]);               
        }
        if (settlements[1]!=0){
            actX=x+RADIUS*3/2;
            actY=y+incY;
            drawSettlement(actX,actY,settlements[1]);
        }
        if (settlements[2]!=0){
            actX=x+RADIUS/2;
            actY=y+incY;
            drawSettlement(actX,actY,settlements[2]);
        }
        if (settlements[3]!=0){
            actX=x;
            actY=y;
            drawSettlement(actX,actY,settlements[3]);
            
        }
        if (settlements[4]!=0){
            actX=x+RADIUS/2;
            actY=y-incY;
            drawSettlement(actX,actY,settlements[4]);
            
        }
        if (settlements[5]!=0){
            actX=x+RADIUS*3/2;
            actY=y-incY;
            drawSettlement(actX,actY,settlements[5]);
        }
        
        
    }
    
    private void drawSettlement(int x, int y,byte settlement){        
        for (int i=0;i<4;i++){
                if (settlement==SETTLEMENT[i]){
                    big.setPaint(PLAYER_COLORS[i]);
                    big.fillOval(x-7,y-7,14,14);
                    break;
                }
            }
            for (int i=0;i<4;i++){
                if (settlement==CITY[i]){
                    big.setPaint(PLAYER_COLORS[i]);
                    big.fillRect(x-7,y-7,14,14);
                    break;
                }
            }
    }
    
    
    private void drawRoads(GeneralPath hexagon, Tile c){
        byte[] roads=c.getRoads();
        int x=(int)hexagon.getCurrentPoint().getX();
        int y=(int)hexagon.getCurrentPoint().getY();
        int x0,y0,x1,y1;        
        
        
        
        if (roads[0]!=0){
            x0=x+2*RADIUS;
            y0=y;
            x1=x+RADIUS*3/2;
            y1=y+incY;
            drawRoad(x0,y0,x1,y1,roads[0]);               
        }
        if (roads[1]!=0){
            x0=x+RADIUS*3/2;
            y0=y+incY;
            x1=x+RADIUS/2;
            y1=y+incY;
            drawRoad(x0,y0,x1,y1,roads[1]);
        }
        if (roads[2]!=0){
            x0=x+RADIUS/2;
            y0=y+incY;
            x1=x;
            y1=y;
            drawRoad(x0,y0,x1,y1,roads[2]);
        }
        if (roads[3]!=0){
            x0=x;
            y0=y;
            x1=x+RADIUS/2;
            y1=y-incY;
            drawRoad(x0,y0,x1,y1,roads[3]);
            
        }
        if (roads[4]!=0){
            x0=x+RADIUS/2;
            y0=y-incY;
            x1=x+RADIUS*3/2;
            y1=y-incY;
            drawRoad(x0,y0,x1,y1,roads[4]);
        }
        if (roads[5]!=0){
            x0=x+RADIUS*3/2;
            y0=y-incY;
            x1=x+2*RADIUS;
            y1=y;
            drawRoad(x0,y0,x1,y1,roads[5]);
        }
        
        
    }
    
    private void drawRoad(int x0, int y0,int x1, int y1, byte road){   
        big.setStroke(new BasicStroke(5f));
        for (int i=0;i<4;i++){
                if (road==ROAD[i]){
                    big.setPaint(PLAYER_COLORS[i]);
                    big.drawLine(x0,y0,x1,y1);
                    break;
                }
            }            
    }
    
    private void calculateTile(int x, int y){
        
      
        //Based of the coordinates of x and y, indicates which tile has been clicked,
    	//and which vertex, and which edge
        
        //Calculate the tile, with a 5 pixel margin
        byte tile;
        int cx=0, cy=0;
        for (tile=0;tile<coordinates.length;tile++){
            cx=(int)coordinates[tile].getX();
            cy=(int)coordinates[tile].getY();
            if (((cx-5)<x)&&((cx+RADIUS*2+5)>x)&&((cy-incY-5)<y)&&((cy+incY+5)>y)){
                break;
            }
        }
        
        if (tile<coordinates.length){ //Estamos en una tile
             int xPoints[] = {(int)(cx+(RADIUS*2)), (int)(cx+(RADIUS*1.5)), (int)(cx+(RADIUS/2)), cx, (int)(cx+(RADIUS/2)), (int)(cx+(RADIUS*1.5))};
             int yPoints[] = {cy, cy+incY, cy+incY,cy, cy-incY, cy-incY};
             
             //Calculate the vertex, with a 10 pixel margin    
             byte vertex;
             for (vertex = 0; vertex<xPoints.length; vertex++){
                if ((Math.abs(xPoints[vertex]-x)<10)&&(Math.abs(yPoints[vertex]-y)<10)){
                    break;
                }
             }
             
             if (vertex==xPoints.length){
                 vertex=-1;
             }
            
             //Calculate the edge: Inside the rectrangle that defines the points
             //that contain it, with a 5 pixel margin
                          
             byte edge;
             int margin=(int)RADIUS/2;
             for (edge = 0; edge<xPoints.length; edge++){
                if (distanceStraightLine(xPoints[edge],yPoints[edge],xPoints[(edge+1)%6],yPoints[(edge+1)%6],x,y)<5){
                    break;
                }
             }
             
             if (edge==xPoints.length){
                 edge=-1;
             }
             
             parent.getController().press(tile,vertex,edge);
             
        }
            
        
        
    }
    
    private double distanceStraightLine(int x0, int y0, int x1, int y1, int x2, int y2){
        /* Distance from a point to a straight line: 
         * Line is points (x0,y0) (x1,y1), Point is (x2,y2)
         *
         * d=abs(A*x2+B*y2+C) / sqrt(A*A+B*B)
         *
         * With:
         *
         * A=y0-y1     B=-(x0-x1)    C=(x0-x1)*y0 - (y0-y1)*x0
         *
         */
         double a=y0-y1;
         double b=-(x0-x1);
         double c=(x0-x1)*y0-(y0-y1)*x0;
         if (b==0){
             return Double.MAX_VALUE;
         }
         double d= Math.abs(a*x2+b*y2+c) / Math.sqrt(a*a+b*b);                           
         
         return d;
    }
    
    public void setAction(byte action){        
        action=action; 
        repaint();
    }
}

