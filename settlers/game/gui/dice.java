package settlers.game.gui;

import java.util.*;

public abstract class dice{

	static int D1; // first die
	static int D2; // second die

	public static int getD1() { // returns the value of the first die. Will print out an error message if the dice hasn't been rolled yet (when D1 = 0)
		if (D1 == 0)
			System.out.print("Error - die hasn't been rolled yet. ");
		return D1;
	}
	
	public static int getD2() { // returns the value of the second die. Will print out an error message if the dice hasn't been rolled yet (when D2 = 0)
		if (D2 == 0)
			System.out.print("Error - die hasn't been rolled yet. ");
		return D2;
	}
	
	public static int roll(int d) { // rolls one or two dice depending on value of d. returns dice roll.
		Random r = new Random();
		if (d == 1) {
			D1 = r.nextInt(5) + 1;
			return D1;
		}
		
		else if (d == 2) {
			D1 = r.nextInt(5) + 1;
			D2 = r.nextInt(5) + 1;
			return D1 + D2;
		}
		
		System.out.print("Invalid number. Either roll one or two dice. ");
		return 0;
	}
	

	
	
	
}