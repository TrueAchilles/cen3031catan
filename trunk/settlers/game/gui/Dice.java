package settlers.game.gui;

import java.util.*;

public class Dice{

    public int D1; // first die
    public int D2; // second die
    private boolean rollSeven = false;

    /**
         * Returns an int representing the value of the first die.  Will print out an error message and return a 0 if the dice hasn't been rolled yet.
         *
         * @return     the value of the first die
         */
         
    public int getD1() { // returns the value of the first die. Will print out an error message if the dice hasn't been rolled yet (when D1 = 0)
        if (D1 == 0)
            System.out.print("Error - die hasn't been rolled yet. ");
        return D1;
    }
    
    /**
         * Returns an int representing the value of the second die.  Will print out an error message and return a 0 if the dice hasn't been rolled yet.
         *
         * @return     the value of the second die
         */
         
    public int getD2() { // returns the value of the second die. Will print out an error message if the dice hasn't been rolled yet (when D2 = 0)
        if (D2 == 0)
            System.out.print("Error - die hasn't been rolled yet. ");
        return D2;
    }
    
    /**
         *Rolls the dice, d1 and d2. This method must be called first for d1 and / or d2 to have valid values. Only d1 will be updated if one die is rolled.
         *
         *@param        numDice the number of dice to be rolled, must be a 1 or a 2.
         * @return      the value of the two dice combined
         */
         
    public int roll(int numDice) { // rolls one or two dice depending on value of d. returns dice roll.
        Random r = new Random();
        if (numDice == 1) {
            D1 = r.nextInt(5) + 1;
            D2 = 0;
            return D1;
        }
        
        else if (numDice == 2) {
            D1 = r.nextInt(5) + 1;

            if (rollSeven == true) {
                D2 = 7 - D1;
                rollSeven = false;
            }
            else {
                D2 = r.nextInt(5) + 1;
            }
            return D1 + D2;
        }
        
        System.out.print("Invalid number. Either roll one or two dice. ");
        return 0;
    }

    public int rollSeven() {
        rollSeven = true;
        return roll(2);
    }
    
}