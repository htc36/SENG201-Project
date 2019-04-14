package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;

public class SetupScreen {

	private JFrame frmCrewSetup;
	private JTextField textField;
	private JTextField textField_1;

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
		initialize();
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
		
		JLabel lblExplorer = new JLabel("Explorer");
		lblExplorer.setBounds(557, 88, 158, 36);
		frmCrewSetup.getContentPane().add(lblExplorer);
		
		JLabel lblExtraLuckStat = new JLabel("Extra Luck Stat");
		lblExtraLuckStat.setBounds(557, 135, 158, 36);
		frmCrewSetup.getContentPane().add(lblExtraLuckStat);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(385, 232, 388, 24);
		frmCrewSetup.getContentPane().add(separator);
		
		JLabel lblGameLength = new JLabel("Game Length (days)");
		lblGameLength.setBounds(387, 252, 158, 36);
		frmCrewSetup.getContentPane().add(lblGameLength);
		
		JLabel lblSpaceshipName = new JLabel("Spaceship name");
		lblSpaceshipName.setBounds(387, 398, 158, 36);
		frmCrewSetup.getContentPane().add(lblSpaceshipName);
		
		textField = new JTextField();
		textField.setBounds(557, 407, 198, 19);
		frmCrewSetup.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(557, 50, 198, 19);
		frmCrewSetup.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.setBounds(659, 507, 114, 25);
		frmCrewSetup.getContentPane().add(btnNewButton);
		
		JLabel label_8 = new JLabel("");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_8.setIcon(null);
			}
		});
		label_8.setBounds(12, 300, 120, 120);
		frmCrewSetup.getContentPane().add(label_8);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(12, 64, 90, 90);
		frmCrewSetup.getContentPane().add(btnNewButton_1);
		
		JLabel label = new JLabel("");
		label.setBounds(144, 431, 120, 120);
		frmCrewSetup.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(144, 300, 120, 120);
		frmCrewSetup.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(12, 431, 120, 120);
		frmCrewSetup.getContentPane().add(label_2);
		
		JButton button = new JButton("New button");
		button.setBounds(114, 64, 90, 90);
		frmCrewSetup.getContentPane().add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(216, 64, 90, 90);
		frmCrewSetup.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(12, 166, 90, 90);
		frmCrewSetup.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(114, 166, 90, 90);
		frmCrewSetup.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("New button");
		button_4.setBounds(216, 166, 90, 90);
		frmCrewSetup.getContentPane().add(button_4);
		
		JSlider slider = new JSlider();
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
	}
}
