/*
 * Settlers.java
 *
 * Created on 4 de enero de 2003, 15:49
 */


package SettlersGUI;

public class Settlers {

    /** Creates new Settlers */
    public Settlers() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        SettlersController sc=new SettlersController();
        SettlersGUI gui = new SettlersGUI(sc);
        sc.setGui(gui);
    }
}
