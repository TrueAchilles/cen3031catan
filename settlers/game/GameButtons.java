package settlers.game;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import settlers.game.GameState;
import settlers.game.events.*;

import settlers.game.*;

import settlers.game.*;
import settlers.game.events.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import settlers.game.*;
import settlers.game.gui.*;
import settlers.game.events.*;
import settlers.game.elements.*;
import settlers.game.gui.ButtonPanel;


public class GameButtons implements ActionListener
{


    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        if(evt.getSource() == ContainerGUI.buttonPanel.roll_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TRADE_PHASE_BEGIN", GameState.getCurPlayer());
            EventManager.callEvent(n); 
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.roll_roll)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_WISHESTO_ROLL", GameState.getCurPlayer());
            EventManager.callEvent(n);

        }
        if(evt.getSource() == ContainerGUI.buttonPanel.roll_thief && GameState.getCurPlayer().getDevCards().hasType(1) > 0) 
        {
            //Throws PLAYER_PLAY_DEVELOPMENTCARD Event to Logic, with the Knight card
            DevelopmentEvent n = new DevelopmentEvent("PLAYER_PLAY_DEVELOPMENTCARD", 1);
            EventManager.callEvent(n);
        }
        ///Remove Once all dev cards are playable
        else if (evt.getSource() == ContainerGUI.buttonPanel.roll_thief && GameState.getCurPlayer().getDevCards().hasType(1) == 0)
        {
            System.out.println("You have a dev card that isn't yet playable, sorry bud.  Wait till you get a knight card :)");
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.trade_player)
        {
            PlayerTrade pt = new PlayerTrade(GameState.players.size());
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.trade_port)
        {
            PortTrade pt = new PortTrade();
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.trade_bank)
        {
            BankTradeWindow bt = new BankTradeWindow();
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.trade_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TRADE_PHASE_END", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.build_next)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_TURN_END", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.build_dev)
        {
            BuildEvent b = new BuildEvent("BUILD_REQUEST", 4);
            EventManager.callEvent(b);
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.build_settlement)
        {
            GameState.setActionState(GlobalVar.ACTION_ADD_SETTLEMENT);
        }
        if(evt.getSource() == ContainerGUI.buttonPanel.build_road)
        {
            GameState.setActionState(GlobalVar.ACTION_ADD_ROAD);
        }
        if (evt.getSource() == ContainerGUI.buttonPanel.build_city)
        {
            GameState.setActionState(GlobalVar.ACTION_ADD_CITY);
        }
        if(evt.getSource() == ContainerGUI.bottomPanel.startButton)
        {
            PlayerEvent n = new PlayerEvent("PLAYER_ROLL_PHASE_BEGIN", GameState.getCurPlayer());
            EventManager.callEvent(n);
        }
        // TODO Auto-generated method stub
        if(evt.getSource() == ContainerGUI.settlersGUI.exit)
        {
            System.exit(0);
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.newGame)
        {
            ContainerGUI.settlersEvent.startNewGame();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.remakeBoard)
        {
            ContainerGUI.settlersEvent.remakeBoard();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.hidePlayerInfo)
        {
            if (ContainerGUI.settlersGUI.hidePlayerInfo.isSelected())
            {
                ContainerGUI.playerInfo.playerInfo.setVisible(false);
            }
            else
            {
                ContainerGUI.playerInfo.playerInfo.setVisible(true);
            }
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.hideRollBox)
        {
            if (ContainerGUI.settlersGUI.hideRollBox.isSelected())
            {
                ContainerGUI.rollBox.setVisible(false);
            }
            else
            {
                ContainerGUI.rollBox.setVisible(true);
            }
            //ContainerGUI.mainBoard.getGameBoard().hideBox(hideRollBox.isSelected());
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.humanPlayer)
        {
            ContainerGUI.settlersEvent.addPlayer();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.computerPlayer)
        {
            ContainerGUI.settlersEvent.addCompPlayer();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.about)
        {
            String aboutNotice = new String(
"Java Settlers of Catan was programmed for CEN3031 Intro to Software Engineering\nat the University of Florida in Gainesville, Florida by Team E.D.S.B.S. The game was a\npre-existing open source program that was selected for the class.\nThe team was lead by Edward Brotz, Alvaro Salkeld, Nick Antonelli, and Ross Nichols and Patrick Meyer.\n\n");

            aboutNotice = aboutNotice.concat("GUI Team:\n* Nick Antonelli (GUI Team Lead)\n* Spencer Gall\n* Eric Hernandez\n* Andrew Stroizer\n* Paul Marks\n\n");
            aboutNotice = aboutNotice.concat("Logic Team\n* Eric Mudge\n* Alvaro Salkeld\n* Patrick Meyer\n* Francesca Ramadori\n* Edward Brotz\n* Naveen Dhawan\n* Carlos Estevez\n* Scott Savino\n* Nick Dunlap\n");

            javax.swing.JOptionPane.showMessageDialog(ContainerGUI.mainBoard, aboutNotice, "About Java Settlers of Catan", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
        if(evt.getSource() == ContainerGUI.settlersGUI.howToPlay)
        {
            HelpWindow jHelp = new HelpWindow();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_quickStart)
        {
            ContainerGUI.settlersEvent.quickStart(false);
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_compQuickStart)
        {
            ContainerGUI.settlersEvent.compQuickStart();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_bigQuickStart)
        {
            ContainerGUI.settlersEvent.quickStart(true);
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_showMeTheMoney)
        {
            ContainerGUI.settlersEvent.muchMoney();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_rollaSeven)
        {
            if (ContainerGUI.settlersGUI.debug_dontRollaSeven.isSelected())
                return;
            //ContainerGUI.settlersGUI.debug_rollaSeven.setSelected(!ContainerGUI.settlersGUI.debug_rollaSeven.isSelected());
            if (ContainerGUI.settlersGUI.debug_rollaSeven.isSelected())
            {
                GameState.rollHax = 1;
                System.out.println("RollHax: 1");
            }
            else {
                GameState.rollHax = 0;
                System.out.println("RollHax: 0");
            }
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.debug_dontRollaSeven)
        {
            if (ContainerGUI.settlersGUI.debug_rollaSeven.isSelected())
                return;
            //ContainerGUI.settlersGUI.debug_dontRollaSeven.setSelected(!ContainerGUI.settlersGUI.debug_dontRollaSeven.isSelected());
            if (ContainerGUI.settlersGUI.debug_dontRollaSeven.isSelected())
            {
                GameState.rollHax = 2;
                System.out.println("RollHax: 2");
            }
            else {
                GameState.rollHax = 0;
                System.out.println("RollHax: 0"); 
            }
        }
        
        if(evt.getSource() == ContainerGUI.settlersGUI.standard)
        {
            ContainerGUI.settlersGUI.smaller.setSelected(!ContainerGUI.settlersGUI.standard.isSelected());
            if(ContainerGUI.settlersGUI.standard.isSelected())
                ContainerGUI.settlersGUI.setSizeDefault();
        }
        if(evt.getSource() == ContainerGUI.settlersGUI.smaller)
        {
            ContainerGUI.settlersGUI.standard.setSelected(!ContainerGUI.settlersGUI.smaller.isSelected());
            if(ContainerGUI.settlersGUI.smaller.isSelected())
                ContainerGUI.settlersGUI.setSizeSmaller();
        }
        if (evt.getSource() == ContainerGUI.settlersGUI.debug_giveMonopolyCard)
        {
            Event debug_e = new Event("DEBUG_GIVE_MONOPOLY");
            EventManager.callEvent(debug_e);
        }
        if (evt.getSource() == ContainerGUI.settlersGUI.debug_giveRoadCard)
        {
            Event debug_e = new Event("DEBUG_GIVE_ROAD");
            EventManager.callEvent(debug_e);
        }
        if (evt.getSource() == ContainerGUI.settlersGUI.debug_giveYearCard)
        {
            Event debug_e = new Event("DEBUG_GIVE_YEAR"); 
            EventManager.callEvent(debug_e);
        }

        if(evt.getSource() == ContainerGUI.settlersGUI.hidePlayerInfo)
        {
            //Gui.playerInfo.setVisible(true);
        }
        
    
    }
}
