package Settlers.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class SplashScreen extends Thread
{
	
	JLabel img;
	SpringLayout layout;
	GameBoard board;
	
	public SplashScreen(JLabel _img, GameBoard _board)
	{
		super();
		img = _img;
		board = _board;
		layout = (SpringLayout) board.getLayout();
	}
	
	private void init()
	{
		img.setIcon(new ImageIcon(getClass().getResource("/Settlers/gui/edsbs.jpg")));
		img.setBorder(new javax.swing.border.LineBorder(Color.black, 3));
		board.add(img);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, img, 0, SpringLayout.VERTICAL_CENTER, board);
    	layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, img, 0, SpringLayout.HORIZONTAL_CENTER, board);
		int step = 0;
		double background = board.getBackground().getRGB();
		double stepValue = (double)255/150;
		double red = 0,green = 0, blue = 0xff;
		while(step < 150)
		{
	    	try {
				sleep(30);
				red += stepValue;
				green += stepValue/2.0;
				blue -= stepValue;
				background = 0xff000000 + (Math.round(red) << 16) + (Math.round(green) << 8) + blue;
				board.setBackground(new Color((int) background));
				if(step == 75)
				{
					img.setIcon(new ImageIcon(getClass().getResource("/Settlers/gui/Settlers.jpg")));
				}
				step++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
	}

}
