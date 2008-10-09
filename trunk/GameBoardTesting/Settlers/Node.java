package Settlers;

public class Node
{

	int xCord;
	int yCord;
	int i,j;
	int onBoard;
	

	public Node(int ii, int jj, int xcord, int ycord)
	{
		i = ii;
		j = jj;
	
		xCord = xcord;
		yCord = ycord;		
		onBoard = 1;
	}
	
	public int getXcord()
	{
		return xCord;
	}
	
	public int setOnBoard(int a)
	{
		return onBoard = a;
	}
	public int getOnBoard()
	{
		return onBoard;
	}
	
	public int getYcord()
	{
		return yCord;
	}
	


	public String toString()
	{
		return String.valueOf(xCord);
	}
	
	public boolean settlementBreadth(int distancce)
	{
		return false;
	}
}
