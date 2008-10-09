/*
 * Settlers.java
 *
 * Created on 4 de enero de 2003, 15:49
 */

package Settlers;

public class Settlers {

    /** Creates new Settlers */
    public Settlers() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        SettlersController sc=new SettlersController();
        SettlersGui gui = new SettlersGui(sc);
        sc.setGui(gui);
        gui.initialize(sc.getTiles());
        
    }
    
    
}
