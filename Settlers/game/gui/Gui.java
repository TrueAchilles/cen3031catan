package settlers.game.gui;

import settlers.game.SettlersController;

public class Gui {
	public Gui()
	{
	    SettlersController sc=new SettlersController();
	    SettlersGUI gui = new SettlersGUI(sc);
	    sc.setGui(gui);
	    gui.initialize(sc.getTiles());
	}
}
