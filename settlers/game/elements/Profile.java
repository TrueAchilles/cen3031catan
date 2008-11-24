package settlers.game.elements;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.io.*;


import settlers.game.*;
import settlers.game.gui.PlayerAvatar;

public class Profile
{

    private static ArrayList<PlayerProfile> playerProfiles = new ArrayList<PlayerProfile>();
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public static void addPlayer(Player newPlayer, PlayerProfile playerProfile)
    {
        GameState.players.add(newPlayer);
        ContainerGUI.mainBoard.getPlayerPanel().addPlayer(newPlayer);
        newPlayer.setPlayerProfile(playerProfile);
    }

    public static void deleteProfile(String pathName)
    {
        
    }

    public static void loadProfile(String pathName)
    {
        String data = new String();
        PlayerProfile playerProfile = new PlayerProfile();

        try
        {
            File profileFile = new File(pathName);

            reader = new BufferedReader(new FileReader(profileFile));

            data = reader.readLine();

            String[] array = data.split("\\*");
            playerProfile = Profile.unserializeProfile(array);

            reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("oogdpjgd");
        }
        catch(IOException e)
        {
            System.out.println("WAKA WAKA");
        }

        playerProfiles.add(playerProfile);

    }

    public static void saveProfile(String pathName, PlayerProfile profile)
    {
        try
        {
            File serializedProfile = new File(pathName);
            writer = new BufferedWriter(new FileWriter(serializedProfile));

            String encodedProfile = serializeProfile(profile);
            System.out.println(encodedProfile);

            writer.write(encodedProfile);
            writer.newLine();

            writer.flush();
            writer.close();

        }
        catch(FileNotFoundException e)
        {}
        catch(IOException e)
        {}

    }

    public static void createPlayerDialog(int playerNumber)
    {
        ProfileDialog dialog = new ProfileDialog(playerNumber);
    }

    public static String serializeProfile(PlayerProfile profile)
    {

        String name = profile.getName();
        int numberOfWins = profile.getNumberOfWins();
        int numberOfLosses = profile.getNumberOfLosses();
        int sRGB = profile.getColor().getRGB();
        String avatarPath = "img";

        if (profile.getPlayerAvatar() != null)
        {
            avatarPath = profile.getPlayerAvatar().getPathname();
        }

        return name + "*" + numberOfWins + "*" + numberOfLosses + "*" + sRGB + "*" + avatarPath;
    }

    public static PlayerProfile unserializeProfile(String[] array)
    {

        String name = array[0];
        int numberOfWins = Integer.parseInt(array[1]);
        int numberOfLosses = Integer.parseInt(array[2]);
        Color color = new Color(Integer.parseInt(array[3]));
        PlayerAvatar playerAvatar = null;

        if (!array[4].equals("img"))
        {
            playerAvatar = new PlayerAvatar(array[4]);
        }

        if (playerAvatar != null && playerAvatar.getImageLoadStatus() != MediaTracker.COMPLETE)
        {
            String message = "The avatar for the selected profile cannot be loaded. Modify the profile to have an image.";
            JOptionPane.showMessageDialog(ContainerGUI.mainBoard, message, "Image Could Not Be Loaded - Settlers of Catan", JOptionPane.ERROR_MESSAGE);
            playerAvatar = null;
        }

        return new PlayerProfile(name, color, playerAvatar);
    }

    private static class ProfileDialog implements ActionListener, WindowListener
    {

        private JFrame dialog = new JFrame();
        private JTabbedPane tabbedPane = new JTabbedPane();

        private JPanel dialogPanel = new JPanel();
        private JPanel createPlayer = new JPanel();
        private JPanel loadPlayer = new JPanel(new GridLayout(2,1));


        private JPanel colorPanel = new JPanel();
        private JPanel imagePanel = new JPanel();
        private JPanel basicOptions = new JPanel(new GridLayout(3,2));
        private JPanel profileOptions = new JPanel();

        private JLabel nameLabel = new JLabel("Name:");
        private JLabel colorLabel = new JLabel("Color:");
        private JLabel saveUserLabel = new JLabel("Save User:");
        private JLabel profileNameLabel = new JLabel("Profile Name:");
        private JLabel imageLabel = new JLabel("Image:");
        private JLabel customImageLabel = new JLabel();

        private JTextField nameField = new JTextField();
        private JTextField profileNameField = new JTextField();

        private JButton colorChooserButton = new JButton("Choose a color...");
        private JButton imageChooserButton = new JButton("Choose a file...");
        private JButton addPlayerButton = new JButton("Add Player");
        private JButton cancelAddPlayerButton = new JButton("Cancel");
        private JButton useProfileButton = new JButton("Use Profile");
        private JButton loadProfileButton = new JButton("Load a Profile...");
        private JButton modifyProfileButton = new JButton("Modify Profile...");

        private JCheckBox saveProfileCheckbox = new JCheckBox();
        private JList listProfileOptions = new JList(new DefaultListModel());

        private JFileChooser fileChooser = new JFileChooser();

        private Color playerColor = new Color(0,0,0);
        private String name = new String();

        public ProfileDialog(int playerNumber)
        {

            setCreatePlayerTab();
            setLoadPlayerTab();

            dialog.setAlwaysOnTop(true);
            dialog.addWindowListener(this);
            dialog.setTitle("Edit Player " + playerNumber);
            dialog.setSize(300,350);
            dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            dialog.setResizable(true);
            dialog.setVisible(true);

        }

        public void setCreatePlayerTab()
        {
            createPlayer.setLayout(new BoxLayout(createPlayer, BoxLayout.Y_AXIS));
            basicOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,0)), "Basic Options", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

            JPanel namePanel = new JPanel();
            namePanel.setSize(440, 100);
            namePanel.setLayout(new BorderLayout());
            nameField.setPreferredSize(new Dimension(150, 20));

            JPanel nameFieldPanel = new JPanel(), nameLabelPanel = new JPanel();
            nameFieldPanel.add(nameField);
            nameLabelPanel.add(nameLabel);

            nameLabel.setVerticalTextPosition(SwingConstants.TOP);
            namePanel.add(nameLabelPanel, BorderLayout.WEST);
            namePanel.add(nameFieldPanel, BorderLayout.CENTER);
            basicOptions.add(namePanel);

            colorPanel.setLayout(new BorderLayout());
            JPanel colorLabelPanel = new JPanel();
            colorLabelPanel.add(colorLabel);
            colorPanel.add(colorLabelPanel, BorderLayout.WEST);
            JPanel colorPreviewPanel = new JPanel(), colorPreviewColor = new JPanel(), colorChooserButtonPanel = new JPanel();


            colorPreviewColor.setPreferredSize(new Dimension(30,30));
            colorPreviewColor.setBackground(new Color(255,0,0));
            colorPreviewPanel.add(colorPreviewColor);
            colorChooserButtonPanel.add(colorChooserButton);
            colorPreviewPanel.add(colorChooserButtonPanel);

            colorChooserButton.setActionCommand("Choose Color");
            colorChooserButton.addActionListener(this);
            colorPanel.add(colorPreviewPanel, BorderLayout.EAST);
            basicOptions.add(colorPanel);

            imagePanel.setLayout(new BorderLayout());
            JPanel imageLabelPanel = new JPanel();
            imageLabelPanel.add(imageLabel);
            imagePanel.add(imageLabelPanel, BorderLayout.WEST);
            JPanel imagePreviewPanel = new JPanel(), imagePreviewImage = new JPanel(), imageChooserButtonPanel = new JPanel();

            imagePreviewImage.setPreferredSize(new Dimension(65,65));
            imagePreviewImage.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            imagePreviewImage.add(new JLabel());
            imagePreviewPanel.add(imagePreviewImage);
            imageChooserButtonPanel.add(imageChooserButton);
            imagePreviewPanel.add(imageChooserButtonPanel);

            imageChooserButton.setActionCommand("Choose Image");
            imageChooserButton.addActionListener(this);
            imagePanel.add(imagePreviewPanel, BorderLayout.EAST);
            basicOptions.add(imagePanel);

            createPlayer.add(basicOptions);


            JPanel savePlayerPanel = new JPanel();
            savePlayerPanel.add(addPlayerButton);
            addPlayerButton.addActionListener(this);

            savePlayerPanel.add(cancelAddPlayerButton);
            cancelAddPlayerButton.addActionListener(this);
            profileOptions.setLayout(new BorderLayout());
            profileOptions.add(savePlayerPanel, BorderLayout.EAST);

            createPlayer.add(profileOptions);

            tabbedPane.addTab("Create Player", null, createPlayer);
        }

        public void setLoadPlayerTab()
        {
                // now the "Load Player" tab

            DefaultListModel listModel = (DefaultListModel) listProfileOptions.getModel();

            for (PlayerProfile playerProfile : Profile.playerProfiles)
            {
                listModel.addElement(playerProfile.getName());
            }

            JLabel selectProfileText = new JLabel("Please select a profile:");
            JPanel listOptionsPanel = new JPanel(), listPanel = new JPanel(new BorderLayout()), listButtonPanel = new JPanel();
            listButtonPanel.setLayout(new BoxLayout(listButtonPanel, BoxLayout.Y_AXIS));

            listOptionsPanel.add(listProfileOptions);
//            listOptionsPanel.setPreferredSize(new Dimension(250, 100));
            listProfileOptions.setPreferredSize(new Dimension(167, 100));
            listPanel.add(listOptionsPanel, BorderLayout.CENTER);

            listButtonPanel.add(useProfileButton);
            listButtonPanel.add(loadProfileButton);
            listButtonPanel.add(modifyProfileButton);
            useProfileButton.setEnabled(true);
            modifyProfileButton.setEnabled(true);
            listPanel.add(listButtonPanel, BorderLayout.EAST);

            useProfileButton.addActionListener(this);
            loadProfileButton.addActionListener(this);

            loadPlayer.add(listPanel);

//            profileDesc.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,0)), "Profile Description", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));

            JLabel playerProfileNameLabel = new JLabel("Name: ");
            JLabel playerProfileColorLabel = new JLabel("Color: ");
            JLabel playerProfileImageLabel = new JLabel("Image: ");
            JLabel playerProfileName = new JLabel("");
            JPanel playerProfileColor = new JPanel();
            JPanel playerProfileImage = new JPanel();

            tabbedPane.addTab("Load Player", null, loadPlayer);
            dialog.add(tabbedPane);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == colorChooserButton)
            {
                Color color = new Color(0,0,0);
                color = JColorChooser.showDialog(colorChooserButton,"Choose Background Color",Color.RED);

                if (color != null)
                {
                    playerColor = new Color(color.getRGB());
                    JPanel colorPreviewPanel = (JPanel) colorPanel.getComponent(1);
                    JPanel colorPreview = (JPanel) colorPreviewPanel.getComponent(0);
                    colorPreview.setBackground(playerColor);
                    colorPreview.repaint();
                }
            }
            else if (e.getSource() == imageChooserButton)
            {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Images (*.gif, *.png, *.jpg)", "gif", "png", "jpg"));
                int response = fileChooser.showOpenDialog(imageChooserButton);

                try
                {
                    File selectedImage = fileChooser.getSelectedFile();
                    String pathName = selectedImage.getPath();

                    PlayerAvatar icon = new PlayerAvatar(pathName);

                    JPanel imagePreviewPanel = (JPanel) imagePanel.getComponent(1);
                    JPanel imagePreview = (JPanel) imagePreviewPanel.getComponent(0);
                    JLabel image = (JLabel) imagePreview.getComponent(0);
                    image.setIcon(icon);
                    image.repaint();
                }
                catch(NullPointerException error)
                {
                }

                // Th1a90@ufl.edu

            }
            else if (e.getSource() == addPlayerButton)
            {
                Object[] selected = saveProfileCheckbox.getSelectedObjects();

                String name = new String(), pathName = new String();

                name = nameField.getText();

                if (name.length() == 0)
                {
                    JOptionPane dialog = new JOptionPane();
                    JOptionPane.showMessageDialog(dialog, "You must give the player a name.", "Need Player Name", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JPanel imagePreviewPanel = (JPanel) imagePanel.getComponent(1);
                JPanel imagePreview = (JPanel) imagePreviewPanel.getComponent(0);
                JLabel image = (JLabel) imagePreview.getComponent(0);
                PlayerAvatar icon = (PlayerAvatar) image.getIcon();

                PlayerProfile playerProfile = null;

                if (selected != null)
                {
                    initFileDialog();
                    int response = fileChooser.showSaveDialog(addPlayerButton);

                    File profile = fileChooser.getSelectedFile();

                    try
                    {
                        pathName = profile.getPath();
                    }
                    catch(Exception exception)
                    {
                        return;
                    }

                    pathName = pathName.concat(".scp");
                    playerProfile = new PlayerProfile(name, playerColor, icon);
                    Profile.saveProfile(pathName, playerProfile);
                }

                Profile.addPlayer(new Player(name, playerColor, icon), playerProfile);
                closeDialog();
            }
            else if (e.getSource() == loadProfileButton)
            {
                initFileDialog();
                int response = fileChooser.showOpenDialog(loadProfileButton);

                File profile = fileChooser.getSelectedFile();
                String pathName = new String();
                try
                {
                    pathName = profile.getPath();
                }
                catch(Exception exception)
                {
                    return;
                }

                Profile.loadProfile(pathName);

                DefaultListModel listModel = (DefaultListModel) listProfileOptions.getModel();
                PlayerProfile lastInsertedProfile = Profile.playerProfiles.get(Profile.playerProfiles.size() - 1);
                listModel.addElement(lastInsertedProfile.getName());

                listProfileOptions.repaint();
            }
            else if (e.getSource() == useProfileButton)
            {

                int index = listProfileOptions.getSelectedIndex();
                if (index < 0)
                {
                    return;
                }

                PlayerProfile playerProfile = Profile.playerProfiles.get(index);
                Player newPlayer = new Player(playerProfile.getName(), playerProfile.getColor(), playerProfile.getPlayerAvatar());
                Profile.addPlayer(newPlayer, playerProfile);
                closeDialog();
            }
            else if (e.getSource() == cancelAddPlayerButton)
            {
                dialog.setVisible(false);
            }
        }

        public void windowActivated(WindowEvent e){}

        public void windowClosed(WindowEvent e)
        {
            //ContainerGUI.settlersGUI.setEnabled(true);
        }

        public void windowClosing(WindowEvent e){}

        public void windowDeactivated(WindowEvent e){}

        public void windowDeiconified(WindowEvent e){}

        public void windowIconified(WindowEvent e){}

        public void windowOpened(WindowEvent e)
        {
            //ContainerGUI.settlersGUI.setEnabled(false);
        }

        private void initFileDialog()
        {
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Settlers of Catan Player Profile (*.scp)", "scp"));

        }

        public void closeDialog()
        {
            dialog.setVisible(false);
        }

        public void modifyProfile()
        {
            
        }

    } // end private class

}