package settlers.game.gui;

import java.io.*;
import javax.swing.event.*;
import javax.swing.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Stack;

public class HelpWindow extends JFrame implements ActionListener, HyperlinkListener
{

    private JEditorPane helpTopicPane = new JEditorPane();
    private JEditorPane helpContentPane = new JEditorPane();

    private JButton prevButton = new JButton("\u00ab Previous");
    private JButton nextButton = new JButton("Next \u00bb");
    private JButton homeButton = new JButton("Home");

    private JPanel buttonPanel = new JPanel();

    private Stack<URL> prev = new Stack<URL>();
    private Stack<URL> next = new Stack<URL>();

    private final URL startPage = ClassLoader.getSystemResource("settlers/game/help/index.html");
    private URL helpMenu = ClassLoader.getSystemResource("settlers/game/help/menu.html");

    public HelpWindow()
    {
 
        super();

        setButtonPanel();

        helpTopicPane.setEditable(false);
        helpContentPane.setEditable(false);

        JScrollPane scrollTopicPane = new JScrollPane(), scrollContentPane = new JScrollPane();

        scrollTopicPane.setViewportView(helpTopicPane);
        scrollContentPane.setViewportView(helpContentPane);


        try
        {
            helpContentPane.setPage(startPage);
            helpTopicPane.setPage(helpMenu);
        }
        catch(Exception e)
        {
            System.out.println("URL not working " + startPage.getFile());
        }

        setLayout(new BorderLayout());

        helpTopicPane.addHyperlinkListener(this);
        helpContentPane.addHyperlinkListener(this);

        scrollTopicPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTopicPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollContentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollContentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollTopicPane.setPreferredSize(new Dimension(150,600));
        scrollContentPane.setPreferredSize(new Dimension(550,600));

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(scrollTopicPane, BorderLayout.WEST);
        getContentPane().add(scrollContentPane, BorderLayout.CENTER);

        setSize(700, 600);
        setVisible(true);
        setTitle("JavaHelp: Settlers of Catan");
      
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == prevButton)
        {
            URL link = prev.pop();
            URL currentLink = helpContentPane.getPage();
/*            if (!link.equals(startPage))
            {
                next.add(link);
            }*/
            next.add(currentLink);
            checkStackSize();

            try
            {
                helpContentPane.setPage(link);
                helpContentPane.repaint();
            }
            catch (IOException ioexception)
            {}
        }
        else if (e.getSource() == nextButton)
        {
            URL link = next.pop();
            URL currentLink = helpContentPane.getPage();
            prev.add(currentLink);
            checkStackSize();

            try
            {
                helpContentPane.setPage(link);
                helpContentPane.repaint();
            }
            catch (IOException ioexception)
            {}
        }
        else if (e.getSource() == homeButton)
        {
            URL currentLink = helpContentPane.getPage();
            next = new Stack<URL>();
            prev.add(currentLink);
            checkStackSize();

            try
            {
                helpContentPane.setPage(startPage);
                helpContentPane.repaint();
            }
            catch (IOException ioexception)
            {}
        }
    }

    public void hyperlinkUpdate(HyperlinkEvent e)
    {
        try
        {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
            {
                URL link = e.getURL();
                URL currentLink = helpContentPane.getPage();
                prev.add(currentLink);

                if (!next.empty())
                {
                    next = new Stack<URL>();
                }

                if (!prevButton.isEnabled())
                {
                    prevButton.setEnabled(true);
                }

                helpContentPane.setPage(link);
                helpContentPane.repaint();

            }
        }
        catch(IOException ioexception)
        {}
    }

    public void checkStackSize()
    {
        if (prev.empty())
        {
            prevButton.setEnabled(false);
        }
        else if (!prev.empty() && !prevButton.isEnabled())
        {
            prevButton.setEnabled(true);
        }

// 47 C
        if (next.empty())
        {
            nextButton.setEnabled(false);
        }
        else if (!next.empty() && !nextButton.isEnabled())
        {
            nextButton.setEnabled(true);
        }

    }

    private void setButtonPanel()
    {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        prevButton.setEnabled(false);
        nextButton.setEnabled(false);
        homeButton.setEnabled(true);

        prevButton.addActionListener(this);
        nextButton.addActionListener(this);
        homeButton.addActionListener(this);

        buttonPanel.add(prevButton);
        buttonPanel.add(homeButton);
        buttonPanel.add(nextButton);

    }

}