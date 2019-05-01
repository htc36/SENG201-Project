package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSlider;

import game.GameEngine;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;

public class SetupScreen {

    private JFrame frmCrewSetup;
    private JTextField spaceshipName;
    private JTextField crewMemberName;
    private GameEngine engine;
    private ArrayList<String[]> crewList;
    private List<JLabel> iconsList;
    private List<JLabel> namesList;
    private JLabel typeLabel;
    private JLabel descLabel;
    private JLabel numShipPieces;
    private JLabel errorLabel;
    private GameGUI game;

    /**
     * Create the application.
     */
    public SetupScreen(GameEngine engine, GameGUI game) {
        this.engine = engine;
        this.game = game;
        crewList = new ArrayList<>();
        iconsList = new ArrayList<>();
        namesList = new ArrayList<>();
        initialize();
        frmCrewSetup.setVisible(true);
        
    }

    /**
     * Closes the window once setup is complete
     */
    public void closeWindow() {
        frmCrewSetup.dispose();
    }

    /**
     * Closes the window
     */
    public void finishedWindow() {
        game.closeSetupScreen(this);
    }
    
    /**
     * Updates crew member images
     */
    private void updateCrewMemberIcons() {		
        for (int i = 0; i < iconsList.size(); i++) {
        	System.out.println("Setting namesList " + i + " to empty string");
			iconsList.get(i).setIcon(null);
			namesList.get(i).setText("");
            if (i >= crewList.size()) {
                continue;
            }
            String name = crewList.get(i)[0];
            String type = crewList.get(i)[1];
        	System.out.println("Setting namesList " + i + " to their name " + name);
			namesList.get(i).setText("<html>" + name + "</html>");

			switch(type) {
				case "medic":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/medic.png")));
					break;
				case "explorer":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/explorer.png")));
					break;
				case "builder":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/builder.png")));
					break;
				case "hungus":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/hungus.png")));
					break;
				case "actioneer":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/actioneer.png")));
					break;
				case "sleeper":
					iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/sleeper.png")));
					break;
			}
        }
    }

    /**
     * Updates crew member description based on crew type selection
     * @param type What type of crew the user has selected
     */
    private void updateCrewMemberDescriptions(String type) {
        switch(type) {
            case "explorer":
                typeLabel.setText("Explorer");
                descLabel.setText("<html>Higher luck, good at finding ship pieces</html>");
                break;
            case "medic":
                typeLabel.setText("Medic");
                descLabel.setText("<html>Receives less damage</html>");
                break;
            case "hungus":
                typeLabel.setText("Hungus");
                descLabel.setText("<html>Benefits more from food</html>");
                break;
            case "sleeper":
                typeLabel.setText("Sleeper");
                descLabel.setText("<html>Benefits more from sleeping</html>");
                break;
            case "actioneer":
                typeLabel.setText("Actioneer");
                descLabel.setText("<html>Has extra actions per day</html>");
                break;
            case "builder":
                typeLabel.setText("Builder");
                descLabel.setText("<html>Repairs spaceship faster</html>");
                break;
        }
    }

    /**
     * Adds crew member to crew
     * @param type crew member type
     * @param name name of crew member
     */
    private void addCrewMember(String type, String name) {
        errorLabel.setText("");
        if (crewList.size() > 3) {
            crewList.remove(3);
        }

        if (name.length() == 0) {
            updateCrewMemberDescriptions(type);
            return;
        }

        if (!engine.isCrewNameValid(crewList, name)) {
            errorLabel.setText("<html>There is a crew member with that name!</html>");
            return;
        }

        crewList.add(0, new String[]{name, type});

        updateCrewMemberIcons();
        crewMemberName.setText("");
    }

    /**
     * Removes crew member from crew
     * @param index location of crew member in crewList
     */
    private void deleteCrewMember(int index) {
    	if (index >= crewList.size())
    		return;

        if (crewList.size() == 0)
            return;

        crewList.remove(index);
        updateCrewMemberIcons();
    }

    /**
     * Sets the number of ship pieces depending on the amount of days
     * @param days Number of days
     */
    private void updateShipPieces(int days) {
        int numPieces = engine.calculateShipPieces(days);
        numShipPieces.setText(String.valueOf(numPieces));
    }

    /**
     * Launches the setup screen
     */
    private void initialize() {
        frmCrewSetup = new JFrame();
        frmCrewSetup.setBackground(Color.BLACK);
        frmCrewSetup.getContentPane().setBackground(Color.BLACK);
        frmCrewSetup.setTitle("Crew Setup");
        frmCrewSetup.setResizable(false);
        frmCrewSetup.setBounds(100, 100, 800, 600);
        frmCrewSetup.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        frmCrewSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCrewSetup.getContentPane().setLayout(null);
        
        JLabel crew1Name = new JLabel("");
        crew1Name.setForeground(Color.WHITE);
        crew1Name.setBounds(34, 393, 90, 36);
        frmCrewSetup.getContentPane().add(crew1Name);
        
        JLabel crew2Name = new JLabel("");
        crew2Name.setForeground(Color.WHITE);
        crew2Name.setBounds(171, 393, 90, 36);
        frmCrewSetup.getContentPane().add(crew2Name);
        
        JLabel crew3Name = new JLabel("");
        crew3Name.setForeground(Color.WHITE);
        crew3Name.setBounds(34, 524, 90, 36);
        frmCrewSetup.getContentPane().add(crew3Name);
        
        JLabel crew4Name = new JLabel("");
        crew4Name.setForeground(Color.WHITE);
        crew4Name.setBounds(171, 524, 90, 36);
        frmCrewSetup.getContentPane().add(crew4Name);
        
        namesList.add(crew1Name);
        namesList.add(crew2Name);
        namesList.add(crew3Name);
        namesList.add(crew4Name);

        JLabel lblCrewMembers = new JLabel("Crew Members");
        lblCrewMembers.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblCrewMembers.setForeground(SystemColor.menu);
        lblCrewMembers.setBounds(10, 257, 114, 36);
        frmCrewSetup.getContentPane().add(lblCrewMembers);

        JLabel lblMemberName = new JLabel("Member Name");
        lblMemberName.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblMemberName.setForeground(SystemColor.menu);
        lblMemberName.setBounds(387, 41, 158, 36);
        frmCrewSetup.getContentPane().add(lblMemberName);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblType.setForeground(SystemColor.menu);
        lblType.setBounds(387, 88, 158, 36);
        frmCrewSetup.getContentPane().add(lblType);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblDescription.setForeground(SystemColor.menu);
        lblDescription.setBounds(387, 136, 158, 36);
        frmCrewSetup.getContentPane().add(lblDescription);

        JLabel crewTypeLabel = new JLabel("");
        crewTypeLabel.setForeground(Color.GRAY);
        crewTypeLabel.setBounds(557, 88, 158, 36);
        frmCrewSetup.getContentPane().add(crewTypeLabel);

        typeLabel = crewTypeLabel;

        JLabel crewDescLabel = new JLabel("");
        crewDescLabel.setForeground(Color.GRAY);
        crewDescLabel.setVerticalAlignment(SwingConstants.TOP);
        crewDescLabel.setBounds(557, 143, 158, 77);
        frmCrewSetup.getContentPane().add(crewDescLabel);

        descLabel = crewDescLabel;

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GRAY);
        separator.setBackground(Color.GRAY);
        separator.setBounds(385, 232, 388, 24);
        frmCrewSetup.getContentPane().add(separator);

        JLabel lblGameLength = new JLabel("Game Length (days)");
        lblGameLength.setBackground(Color.GRAY);
        lblGameLength.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblGameLength.setForeground(SystemColor.menu);
        lblGameLength.setBounds(387, 374, 158, 36);
        frmCrewSetup.getContentPane().add(lblGameLength);

        JLabel lblSpaceshipName = new JLabel("Spaceship name");
        lblSpaceshipName.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblSpaceshipName.setForeground(SystemColor.menu);
        lblSpaceshipName.setBounds(387, 322, 158, 36);
        frmCrewSetup.getContentPane().add(lblSpaceshipName);

        spaceshipName = new JTextField();
        spaceshipName.setOpaque(false);
        spaceshipName.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        spaceshipName.setCaretColor(Color.GRAY);
        spaceshipName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
        spaceshipName.setForeground(Color.GRAY);
        spaceshipName.setBounds(588, 333, 167, 19);
        spaceshipName.setText("Andromeda");
        frmCrewSetup.getContentPane().add(spaceshipName);
        spaceshipName.setColumns(10);

        crewMemberName = new JTextField();
        crewMemberName.setOpaque(false);
        crewMemberName.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        crewMemberName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        crewMemberName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GRAY));
        crewMemberName.setCaretColor(Color.WHITE);
        crewMemberName.setForeground(Color.WHITE);
        crewMemberName.setBounds(557, 50, 198, 19);
        frmCrewSetup.getContentPane().add(crewMemberName);
        crewMemberName.setColumns(10);


        JLabel firstCrewMemberIcon = new JLabel("");
        firstCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * Initialize the contents of the frame.
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(0);
            }
        });
        
        firstCrewMemberIcon.setBounds(34, 305, 90, 90);
       
        frmCrewSetup.getContentPane().add(firstCrewMemberIcon);

        JButton explorerBtn = new JButton("Explorer");
        explorerBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        explorerBtn.setBorderPainted(false);
        explorerBtn.setBackground(Color.DARK_GRAY);
        explorerBtn.setForeground(Color.WHITE);
        explorerBtn.addActionListener(new ActionListener() {
            /**
             * Selects the explorer for crew member
             * @param e Action event
             */
            public void actionPerformed(ActionEvent e) {
                addCrewMember("explorer", crewMemberName.getText());
            }
        });
        explorerBtn.setBounds(12, 64, 90, 90);
        frmCrewSetup.getContentPane().add(explorerBtn);

        JLabel fourthCrewMemberIcon = new JLabel("");
        fourthCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * Removes the fourth crew member if exists
             * @param e Mouse event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(3);
            }
        });
        fourthCrewMemberIcon.setBounds(171, 434, 90, 90);
        frmCrewSetup.getContentPane().add(fourthCrewMemberIcon);

        JLabel secondCrewMemberIcon = new JLabel("");
        secondCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * Removes the second crew member when clicked
             * @param e Mouse Event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(1);
            }
        });
        secondCrewMemberIcon.setBounds(171, 305, 90, 90);
        frmCrewSetup.getContentPane().add(secondCrewMemberIcon);

        JLabel thirdCrewMemberIcon = new JLabel("");
        thirdCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * Removes third crew member when clicked
             * @param e Mouse event
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(2);
            }
        });
        thirdCrewMemberIcon.setBounds(34, 434, 90, 90);
        frmCrewSetup.getContentPane().add(thirdCrewMemberIcon);

        iconsList.add(firstCrewMemberIcon);
        iconsList.add(secondCrewMemberIcon);
        iconsList.add(thirdCrewMemberIcon);
        iconsList.add(fourthCrewMemberIcon);

        JButton medicBtn = new JButton("Medic");
        medicBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        medicBtn.setForeground(Color.WHITE);
        medicBtn.setBackground(Color.DARK_GRAY);
        medicBtn.setBorderPainted(false);
        medicBtn.addActionListener(new ActionListener() {
            /**
             * Adds crew member of type medic if there is no name given displays details
             * @param arg0 Action event
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("medic", crewMemberName.getText());
            }
        });
        medicBtn.setBounds(114, 64, 90, 90);
        frmCrewSetup.getContentPane().add(medicBtn);

        JButton sleeperBtn = new JButton("Sleeper");

        sleeperBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        sleeperBtn.setBackground(Color.DARK_GRAY);
        sleeperBtn.setForeground(Color.WHITE);
        sleeperBtn.setBorderPainted(false);
        sleeperBtn.addActionListener(new ActionListener() {
            /**
             * Creates a new crew member of type sleeper
             * @param arg0 Action event
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("sleeper", crewMemberName.getText());
            }
        });
        sleeperBtn.setBounds(216, 64, 90, 90);
        frmCrewSetup.getContentPane().add(sleeperBtn);

        JButton hungusBtn = new JButton("Hungus");
        hungusBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        hungusBtn.setForeground(Color.WHITE);
        hungusBtn.setBackground(Color.DARK_GRAY);
        hungusBtn.setBorderPainted(false);
        hungusBtn.addActionListener(new ActionListener() {
            /**
             * Creates a new crew member of type hungus
             * @param arg0 Action event
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("hungus", crewMemberName.getText());
            }
        });
        hungusBtn.setBounds(12, 166, 90, 90);
        frmCrewSetup.getContentPane().add(hungusBtn);

        JButton actioneerBtn = new JButton("Actioneer");
        actioneerBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        actioneerBtn.setForeground(Color.WHITE);
        actioneerBtn.setBackground(Color.DARK_GRAY);
        actioneerBtn.setBorderPainted(false);
        actioneerBtn.addActionListener(new ActionListener() {
            /**
             * Creates a new crew member of type Actoineer
             * @param arg0 Action event
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("actioneer", crewMemberName.getText());
            }
        });
        actioneerBtn.setBounds(114, 166, 90, 90);
        frmCrewSetup.getContentPane().add(actioneerBtn);

        JButton builderBtn = new JButton("Builder");
        builderBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        builderBtn.setBackground(Color.DARK_GRAY);
        builderBtn.setForeground(Color.WHITE);
        builderBtn.setBorderPainted(false);
        builderBtn.addActionListener(new ActionListener() {
            /**
             * Creates a new crew member of type Builder
             * @param arg0 Action event
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("builder", crewMemberName.getText());
            }
        });
        builderBtn.setBounds(216, 166, 90, 90);
        frmCrewSetup.getContentPane().add(builderBtn);

        JLabel lblNumShipPieces = new JLabel("6");
        lblNumShipPieces.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        lblNumShipPieces.setForeground(Color.GRAY);
        lblNumShipPieces.setBounds(588, 268, 71, 36);
        frmCrewSetup.getContentPane().add(lblNumShipPieces);

        numShipPieces = lblNumShipPieces;

        JSlider slider = new JSlider();
        slider.setUI(new DarkSlider(slider));
        slider.setOpaque(false);
        slider.setForeground(SystemColor.menu);
        slider.addChangeListener(new ChangeListener() {
            /**
             * Sets the number of days for game length
             * @param arg0 Change event
             */
            public void stateChanged(ChangeEvent arg0) {
                updateShipPieces(slider.getValue());
            }
        });
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMaximum(10);
        slider.setMinimum(3);
        slider.setBounds(387, 422, 369, 58);
        frmCrewSetup.getContentPane().add(slider);

        JLabel lblSelextYourCrew = new JLabel("Select your crew members");
        lblSelextYourCrew.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblSelextYourCrew.setForeground(SystemColor.windowBorder);
        lblSelextYourCrew.setBounds(12, 16, 210, 36);
        frmCrewSetup.getContentPane().add(lblSelextYourCrew);

        JLabel lblNumberOfShip = new JLabel("Number of ship pieces");
        lblNumberOfShip.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblNumberOfShip.setForeground(SystemColor.menu);
        lblNumberOfShip.setBounds(387, 268, 175, 36);
        frmCrewSetup.getContentPane().add(lblNumberOfShip);

        JButton proceedBtn = new JButton("Proceed");
        proceedBtn.setForeground(Color.WHITE);
        proceedBtn.setFont(new Font("Ubuntu", Font.BOLD, 16));
        proceedBtn.setBorderPainted(false);
        proceedBtn.setBackground(Color.DARK_GRAY);
        proceedBtn.addActionListener(new ActionListener() {
            /**
             * Saves the settings made by user and exits the setup screen
             * then runs the game
             * @param e Action event
             */
            public void actionPerformed(ActionEvent e) {
                engine.setupSpaceship(spaceshipName.getText());
                engine.setGameLength(slider.getValue());
                if (!engine.isCrewNumberValid(crewList.size())) {
                    JOptionPane.showMessageDialog(
                            new JFrame(),
                            "Crew size must be between 2 to 4",
                            "Dialog",
                            JOptionPane.ERROR_MESSAGE
                            );
                    return;
                }

                engine.setCrewMembers(crewList);
                engine.setShipPieces();
                engine.setupCrew();
                engine.setupPlanets();
                finishedWindow();
            }
        });
        proceedBtn.setBounds(659, 507, 114, 25);
        frmCrewSetup.getContentPane().add(proceedBtn);

        JLabel lblThereIsA = new JLabel("");
        lblThereIsA.setBounds(20, 292, 286, 15);
        frmCrewSetup.getContentPane().add(lblThereIsA);
        errorLabel = lblThereIsA;

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(SetupScreen.class.getResource("/img/setupscreen.jpg")));
        lblNewLabel.setBounds(0, 0, 800, 572);
        frmCrewSetup.getContentPane().add(lblNewLabel);
        
        

    }
}
