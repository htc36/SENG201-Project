package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;
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
public class SetupScreen {

	private JFrame frmCrewSetup;
	private JTextField textField;
	private JTextField crewMemberName;
	private GameEngine engine;
	private ArrayList<String> crewList;
	private ArrayList<JLabel> iconsList;
	private JLabel typeLabel;
	private JLabel descLabel;
	private JLabel numShipPieces;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frmCrewSetup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupScreen() {
		engine = new GameEngine();
		crewList = new ArrayList<>();
		iconsList = new ArrayList<>();
		initialize();

		frmCrewSetup.setVisible(true);
	}
	
	private void updateCrewMemberIcons() {		
		for (int i = 0; i < crewList.size(); i++) {
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
		if (crewList.size() > 3) {
			crewList.remove(3);
		}
		System.out.println(crewList);

		
		if (name.length() == 0) {
			updateCrewMemberDescriptions(type);
			return;
		}
			
		crewList.add(0, crewMemberName.getText() + "-" + type);
		updateCrewMemberIcons();
		crewMemberName.setText("");
	}
	
	private void updateShipPieces(int days) {
		int numPieces = engine.calculateShipPieces(days);
		numShipPieces.setText(String.valueOf(numPieces));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrewSetup = new JFrame();
		frmCrewSetup.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmCrewSetup.setTitle("Crew Setup");
		frmCrewSetup.setResizable(false);
		frmCrewSetup.setBounds(100, 100, 800, 600);

		frmCrewSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrewSetup.getContentPane().setLayout(null);
		
		JLabel lblCrewMembers = new JLabel("Crew Members");
		lblCrewMembers.setBounds(10, 257, 152, 36);
		frmCrewSetup.getContentPane().add(lblCrewMembers);
		
		JLabel lblMemberName = new JLabel("Member Name");
		lblMemberName.setBounds(387, 41, 158, 36);
		frmCrewSetup.getContentPane().add(lblMemberName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(387, 88, 158, 36);
		frmCrewSetup.getContentPane().add(lblType);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(387, 136, 158, 36);
		frmCrewSetup.getContentPane().add(lblDescription);
		
		JLabel crewTypeLabel = new JLabel("Explorer");
		crewTypeLabel.setBounds(557, 88, 158, 36);
		frmCrewSetup.getContentPane().add(crewTypeLabel);
		
		typeLabel = crewTypeLabel;
		
		JLabel crewDescLabel = new JLabel("Extra Luck Stat");
		crewDescLabel.setVerticalAlignment(SwingConstants.TOP);
		crewDescLabel.setBounds(557, 143, 158, 77);
		frmCrewSetup.getContentPane().add(crewDescLabel);
		
		descLabel = crewDescLabel;
		
		JSeparator separator = new JSeparator();
		separator.setBounds(385, 232, 388, 24);
		frmCrewSetup.getContentPane().add(separator);
		
		JLabel lblGameLength = new JLabel("Game Length (days)");
		lblGameLength.setBounds(387, 252, 158, 36);
		frmCrewSetup.getContentPane().add(lblGameLength);
		
		JLabel lblSpaceshipName = new JLabel("Spaceship name");
		lblSpaceshipName.setBounds(387, 459, 158, 36);
		frmCrewSetup.getContentPane().add(lblSpaceshipName);
		
		textField = new JTextField();
		textField.setBounds(557, 468, 198, 19);
		textField.setText("Andromeda");
		frmCrewSetup.getContentPane().add(textField);
		textField.setColumns(10);
		
		crewMemberName = new JTextField();
		crewMemberName.setBounds(557, 50, 198, 19);
		frmCrewSetup.getContentPane().add(crewMemberName);
		crewMemberName.setColumns(10);

		
		JLabel firstCrewMemberIcon = new JLabel("");
		firstCrewMemberIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				firstCrewMemberIcon.setIcon(null);
			}
		});
		firstCrewMemberIcon.setBounds(12, 300, 120, 120);
		frmCrewSetup.getContentPane().add(firstCrewMemberIcon);
		
		JButton explorerBtn = new JButton("Explorer");
		explorerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCrewMember("explorer", crewMemberName.getText());
			}
		});
		explorerBtn.setBounds(12, 64, 90, 90);
		frmCrewSetup.getContentPane().add(explorerBtn);
		
		JLabel fourthCrewMemberIcon = new JLabel("");
		fourthCrewMemberIcon.setBounds(144, 431, 120, 120);
		frmCrewSetup.getContentPane().add(fourthCrewMemberIcon);
		
		JLabel secondCrewMemberIcon = new JLabel("");
		secondCrewMemberIcon.setBounds(144, 300, 120, 120);
		frmCrewSetup.getContentPane().add(secondCrewMemberIcon);
		
		JLabel thirdCrewMemberIcon = new JLabel("");
		thirdCrewMemberIcon.setBounds(12, 431, 120, 120);
		frmCrewSetup.getContentPane().add(thirdCrewMemberIcon);
		
		iconsList.add(firstCrewMemberIcon);
		iconsList.add(secondCrewMemberIcon);
		iconsList.add(thirdCrewMemberIcon);
		iconsList.add(fourthCrewMemberIcon);
		
		JButton medicBtn = new JButton("Medic");
		medicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCrewMember("medic", crewMemberName.getText());
			}
		});
		medicBtn.setBounds(114, 64, 90, 90);
		frmCrewSetup.getContentPane().add(medicBtn);
		
		JButton sleeperBtn = new JButton("Sleeper");
		sleeperBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCrewMember("sleeper", crewMemberName.getText());
			}
		});
		sleeperBtn.setBounds(216, 64, 90, 90);
		frmCrewSetup.getContentPane().add(sleeperBtn);
		
		JButton hungusBtn = new JButton("Hungus");
		hungusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCrewMember("hungus", crewMemberName.getText());
			}
		});
		hungusBtn.setBounds(12, 166, 90, 90);
		frmCrewSetup.getContentPane().add(hungusBtn);
		
		JButton actioneerBtn = new JButton("Actioneer");
		actioneerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCrewMember("actioneer", crewMemberName.getText());
			}
		});
		actioneerBtn.setBounds(114, 166, 90, 90);
		frmCrewSetup.getContentPane().add(actioneerBtn);
		
		JButton builderBtn = new JButton("Builder");
		builderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCrewMember("builder", crewMemberName.getText());
			}
		});
		builderBtn.setBounds(216, 166, 90, 90);
		frmCrewSetup.getContentPane().add(builderBtn);

		JLabel lblNumShipPieces = new JLabel("6");
		lblNumShipPieces.setBounds(557, 395, 158, 36);
		frmCrewSetup.getContentPane().add(lblNumShipPieces);
		
		numShipPieces = lblNumShipPieces;
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
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
		lblSelextYourCrew.setBounds(12, 16, 210, 36);
		frmCrewSetup.getContentPane().add(lblSelextYourCrew);
		
		JLabel lblNumberOfShip = new JLabel("Number of ship pieces");
		lblNumberOfShip.setBounds(387, 395, 158, 36);
		frmCrewSetup.getContentPane().add(lblNumberOfShip);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				engine.setupSpaceship(textField.getText());
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
			}
		});
		btnProceed.setBounds(659, 507, 114, 25);
		frmCrewSetup.getContentPane().add(btnProceed);
		
	}
}
