package settlers.game.gui;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class BottomPanel extends javax.swing.JPanel {
    
    private ButtonPanel buttonPanel;
    public SettlersGUI parent;
    private TabbedPanel tabbedPanel;
    
    public BottomPanel(SettlersGUI _parent) {
        super();
        parent = _parent;
        initGUI();
    }
    
    private void initGUI() {
        try {
            this.setPreferredSize(new java.awt.Dimension(795, 150));
            AnchorLayout thisLayout = new AnchorLayout();
            this.setLayout(thisLayout);
            this.setSize(795, 150);
            {
                tabbedPanel = new TabbedPanel();
                this.add(tabbedPanel, new AnchorConstraint(3, 985, 990, 439, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
                tabbedPanel.setPreferredSize(new java.awt.Dimension(434, 148));
            }
            {
                buttonPanel = new ButtonPanel(this, parent.getSettlersEvent());
                this.add(buttonPanel, new AnchorConstraint(0, 440, 0, -1, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_ABS, AnchorConstraint.ANCHOR_ABS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ButtonPanel getButtonPanel()
    {
        return buttonPanel;
    }
        
    public TabbedPanel getTabbedPanel()
    {
        return tabbedPanel;
    }
    
    public SettlersGUI getGUI()
    {
        return parent;
    }
}
