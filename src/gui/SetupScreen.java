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

import javax.swing.JSlider;

import game.GameEngine;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class SetupScreen {

    private JFrame frmCrewSetup;
    private JTextField spaceshipName;
    private JTextField crewMemberName;
    private GameEngine engine;
    private ArrayList<String> crewList;
    private ArrayList<JLabel> iconsList;
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
        initialize();
        frmCrewSetup.setVisible(true);
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void closeWindow() {
        frmCrewSetup.dispose();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void finishedWindow() {
        game.closeSetupScreen(this);
    }

    private void updateCrewMemberIcons() {		
        for (int i = 0; i < iconsList.size(); i++) {
            iconsList.get(i).setIcon(null);
            if (i >= crewList.size())
                return;
            iconsList.get(i).setName(crewList.get(i));
            if (crewList.get(i).endsWith("medic"))
                iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/medic.png")));
            else if (crewList.get(i).endsWith("explorer"))
                iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/explorer.png")));
            else if (crewList.get(i).endsWith("builder"))
                iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/builder.png")));
            else if (crewList.get(i).endsWith("hungus"))
                iconsList.get(i).setIcon(new ImageIcon(SetupScreen.class.getResource("/img/hungus.png")));
        }
    }

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

        crewList.add(0, crewMemberName.getText() + "-" + type);

        updateCrewMemberIcons();
        crewMemberName.setText("");
    }

    private void deleteCrewMember(int index) {
    	if (crewList.size() == 0)
    		return;

        crewList.remove(index);
        iconsList.get(index).setIcon(null);
        updateCrewMemberIcons();
    }

    private void updateShipPieces(int days) {
        int numPieces = engine.calculateShipPieces(days);
        numShipPieces.setText(String.valueOf(numPieces));
    }

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

        JLabel lblCrewMembers = new JLabel("Crew Members");
        lblCrewMembers.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblCrewMembers.setForeground(Color.GREEN);
        lblCrewMembers.setBounds(10, 257, 114, 36);
        frmCrewSetup.getContentPane().add(lblCrewMembers);

        JLabel lblMemberName = new JLabel("Member Name");
        lblMemberName.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblMemberName.setForeground(Color.GREEN);
        lblMemberName.setBounds(387, 41, 158, 36);
        frmCrewSetup.getContentPane().add(lblMemberName);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblType.setForeground(Color.GREEN);
        lblType.setBounds(387, 88, 158, 36);
        frmCrewSetup.getContentPane().add(lblType);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblDescription.setForeground(Color.GREEN);
        lblDescription.setBounds(387, 136, 158, 36);
        frmCrewSetup.getContentPane().add(lblDescription);

        JLabel crewTypeLabel = new JLabel("");
        crewTypeLabel.setForeground(Color.GREEN);
        crewTypeLabel.setBounds(557, 88, 158, 36);
        frmCrewSetup.getContentPane().add(crewTypeLabel);

        typeLabel = crewTypeLabel;

        JLabel crewDescLabel = new JLabel("");
        crewDescLabel.setForeground(Color.GREEN);
        crewDescLabel.setVerticalAlignment(SwingConstants.TOP);
        crewDescLabel.setBounds(557, 143, 158, 77);
        frmCrewSetup.getContentPane().add(crewDescLabel);

        descLabel = crewDescLabel;

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.GREEN);
        separator.setBounds(385, 232, 388, 24);
        frmCrewSetup.getContentPane().add(separator);

        JLabel lblGameLength = new JLabel("Game Length (days)");
        lblGameLength.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblGameLength.setForeground(Color.GREEN);
        lblGameLength.setBounds(387, 252, 158, 36);
        frmCrewSetup.getContentPane().add(lblGameLength);

        JLabel lblSpaceshipName = new JLabel("Spaceship name");
        lblSpaceshipName.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblSpaceshipName.setForeground(Color.GREEN);
        lblSpaceshipName.setBounds(387, 459, 158, 36);
        frmCrewSetup.getContentPane().add(lblSpaceshipName);

        spaceshipName = new JTextField();
        spaceshipName.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        spaceshipName.setCaretColor(Color.GREEN);
        spaceshipName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
        spaceshipName.setForeground(Color.GREEN);
        spaceshipName.setBackground(Color.BLACK);
        spaceshipName.setBounds(588, 468, 167, 19);
        spaceshipName.setText("Andromeda");
        frmCrewSetup.getContentPane().add(spaceshipName);
        spaceshipName.setColumns(10);

        crewMemberName = new JTextField();
        crewMemberName.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        crewMemberName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        crewMemberName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.GREEN));
        crewMemberName.setCaretColor(Color.GREEN);
        crewMemberName.setForeground(Color.GREEN);
        crewMemberName.setBackground(Color.BLACK);
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
        firstCrewMemberIcon.setBounds(34, 340, 90, 90);
        frmCrewSetup.getContentPane().add(firstCrewMemberIcon);

        JButton explorerBtn = new JButton("Explorer");
        explorerBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        explorerBtn.setBorderPainted(false);
        explorerBtn.setBackground(Color.DARK_GRAY);
        explorerBtn.setForeground(Color.GREEN);
        explorerBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param e <<Param Desc>>
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
             * <<auto generated javadoc comment>>
             * @param e <<Param Desc>>
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(3);
            }
        });
        fourthCrewMemberIcon.setBounds(150, 442, 90, 90);
        frmCrewSetup.getContentPane().add(fourthCrewMemberIcon);

        JLabel secondCrewMemberIcon = new JLabel("");
        secondCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * <<auto generated javadoc comment>>
             * @param e <<Param Desc>>
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(1);
            }
        });
        secondCrewMemberIcon.setBounds(150, 340, 90, 90);
        frmCrewSetup.getContentPane().add(secondCrewMemberIcon);

        JLabel thirdCrewMemberIcon = new JLabel("");
        thirdCrewMemberIcon.addMouseListener(new MouseAdapter() {
            /**
             * <<auto generated javadoc comment>>
             * @param e <<Param Desc>>
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteCrewMember(2);
            }
        });
        thirdCrewMemberIcon.setBounds(34, 442, 90, 90);
        frmCrewSetup.getContentPane().add(thirdCrewMemberIcon);

        iconsList.add(firstCrewMemberIcon);
        iconsList.add(secondCrewMemberIcon);
        iconsList.add(thirdCrewMemberIcon);
        iconsList.add(fourthCrewMemberIcon);

        JButton medicBtn = new JButton("Medic");
        medicBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        medicBtn.setForeground(Color.GREEN);
        medicBtn.setBackground(Color.DARK_GRAY);
        medicBtn.setBorderPainted(false);
        medicBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
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
        sleeperBtn.setForeground(Color.GREEN);
        sleeperBtn.setBorderPainted(false);
        sleeperBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("sleeper", crewMemberName.getText());
            }
        });
        sleeperBtn.setBounds(216, 64, 90, 90);
        frmCrewSetup.getContentPane().add(sleeperBtn);

        JButton hungusBtn = new JButton("Hungus");
        hungusBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        hungusBtn.setForeground(Color.GREEN);
        hungusBtn.setBackground(Color.DARK_GRAY);
        hungusBtn.setBorderPainted(false);
        hungusBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("hungus", crewMemberName.getText());
            }
        });
        hungusBtn.setBounds(12, 166, 90, 90);
        frmCrewSetup.getContentPane().add(hungusBtn);

        JButton actioneerBtn = new JButton("Actioneer");
        actioneerBtn.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        actioneerBtn.setForeground(Color.GREEN);
        actioneerBtn.setBackground(Color.DARK_GRAY);
        actioneerBtn.setBorderPainted(false);
        actioneerBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
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
        builderBtn.setForeground(Color.GREEN);
        builderBtn.setBorderPainted(false);
        builderBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
             */
            public void actionPerformed(ActionEvent arg0) {
                addCrewMember("builder", crewMemberName.getText());
            }
        });
        builderBtn.setBounds(216, 166, 90, 90);
        frmCrewSetup.getContentPane().add(builderBtn);

        JLabel lblNumShipPieces = new JLabel("6");
        lblNumShipPieces.setFont(new Font("Ubuntu Mono", Font.PLAIN, 12));
        lblNumShipPieces.setForeground(Color.GREEN);
        lblNumShipPieces.setBounds(588, 395, 71, 36);
        frmCrewSetup.getContentPane().add(lblNumShipPieces);

        numShipPieces = lblNumShipPieces;

        JSlider slider = new JSlider();
        slider.setForeground(Color.GREEN);
        slider.setBackground(Color.BLACK);
        slider.addChangeListener(new ChangeListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param arg0 <<Param Desc>>
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
        slider.setBounds(387, 300, 369, 58);
        frmCrewSetup.getContentPane().add(slider);

        JLabel lblSelextYourCrew = new JLabel("Select your crew members");
        lblSelextYourCrew.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblSelextYourCrew.setForeground(Color.GREEN);
        lblSelextYourCrew.setBounds(12, 16, 210, 36);
        frmCrewSetup.getContentPane().add(lblSelextYourCrew);

        JLabel lblNumberOfShip = new JLabel("Number of ship pieces");
        lblNumberOfShip.setFont(new Font("Ubuntu", Font.BOLD, 15));
        lblNumberOfShip.setForeground(Color.GREEN);
        lblNumberOfShip.setBounds(387, 395, 175, 36);
        frmCrewSetup.getContentPane().add(lblNumberOfShip);

        JButton proceedBtn = new JButton("Proceed");
        proceedBtn.setForeground(Color.GREEN);
        proceedBtn.setFont(new Font("Ubuntu", Font.BOLD, 16));
        proceedBtn.setBorderPainted(false);
        proceedBtn.setBackground(Color.DARK_GRAY);
        proceedBtn.addActionListener(new ActionListener() {
            /**
             * <<auto generated javadoc comment>>
             * @param e <<Param Desc>>
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
        lblThereIsA.setBounds(20, 292, 286, 66);
        frmCrewSetup.getContentPane().add(lblThereIsA);
        errorLabel = lblThereIsA;

    }
}
