package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.GameEngine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CommandCenter {

	private JFrame frmCommandCenter;
	private GameEngine engine;
	private GameGUI game;

	/**
	 * Create the application.
	 */
	public CommandCenter(GameEngine engine, GameGUI game) {
		this.engine = engine;
		this.game = game;
		initialize();
		frmCommandCenter.setVisible(true);
	}
	
	public void closeWindow() {
		frmCommandCenter.dispose();
	}
	
	public void finishedWindow() {
		game.closeCommandCenter(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCommandCenter = new JFrame();
		frmCommandCenter.setTitle("Command Center");
		frmCommandCenter.setBounds(100, 100, 1024, 768);
		frmCommandCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCommandCenter.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 1000, 648);
		frmCommandCenter.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Crew Status", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblName.setBounds(86, 29, 82, 62);
		panel.add(lblName);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblType.setBounds(275, 29, 82, 62);
		panel.add(lblType);
		
		JLabel lblHealth = new JLabel("Health");
		lblHealth.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHealth.setBounds(379, 29, 82, 62);
		panel.add(lblHealth);
		
		JLabel lblLuck = new JLabel("Luck");
		lblLuck.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblLuck.setBounds(473, 29, 82, 62);
		panel.add(lblLuck);
		
		JLabel lblPlagued = new JLabel("Plagued");
		lblPlagued.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblPlagued.setBounds(567, 29, 109, 62);
		panel.add(lblPlagued);
		
		JLabel lblHunger = new JLabel("Hunger");
		lblHunger.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblHunger.setBounds(676, 29, 82, 62);
		panel.add(lblHunger);
		
		JLabel lblFatigue = new JLabel("Fatigue");
		lblFatigue.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblFatigue.setBounds(780, 29, 94, 62);
		panel.add(lblFatigue);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		lblActions.setBounds(886, 29, 82, 62);
		panel.add(lblActions);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Ubuntu", Font.BOLD, 20));
		String crewNames = "<html>";
		String crewTypes = "<html>";
		String crewHealth = "<html>";
		String crewLuck = "<html>";
		String crewPlagued = "<html>";
		String crewHunger = "<html>";
		String crewFatigue = "<html>";
		String crewActions = "<html>";
		 
		for (ArrayList<String> member : engine.getCrewMemberStatus()) {
			String name = member.get(0);
			crewNames += name;
			crewNames += "<br>";
			crewNames += "<br>";
			
			String type = member.get(7);
			crewTypes += type;
			crewTypes += "<br>";
			crewTypes += "<br>";

			String health = member.get(1);
			crewHealth += health;
			crewHealth += "<br>";
			crewHealth += "<br>";

			String luck = member.get(2);
			crewLuck += luck;
			crewLuck += "<br>";
			crewLuck += "<br>";
			
			String plagued = member.get(3);
			crewPlagued += plagued;
			crewPlagued += "<br>";
			crewPlagued += "<br>";
			
			String hunger = member.get(4);
			crewHunger += hunger;
			crewHunger += "<br>";
			crewHunger += "<br>";
			
			String fatigue = member.get(5);
			crewFatigue += fatigue;
			crewFatigue += "<br>";
			crewFatigue += "<br>";
			
			String actions = member.get(6);
			crewActions += actions;
			crewActions += "<br>";
			crewActions += "<br>";
		}
		crewNames += "</html>";
		crewTypes += "</html>";
		crewHealth += "</html>";
		crewLuck += "</html>";
		crewPlagued += "</html>";
		crewHunger += "</html>";
		crewFatigue += "</html>";
		crewActions += "</html>";
		
		label.setText(crewNames);
		label.setBounds(32, 112, 231, 471);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Type");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setText(crewTypes);
		label_1.setBounds(285, 112, 82, 471);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Type");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText(crewHealth);
		label_2.setBounds(389, 112, 82, 471);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Type");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText(crewLuck);
		label_3.setBounds(483, 112, 82, 471);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Type");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText(crewPlagued);
		label_4.setBounds(577, 112, 82, 471);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Type");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setText(crewHunger);
		label_5.setBounds(676, 112, 82, 471);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Type");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setText(crewFatigue);
		label_6.setBounds(780, 112, 82, 471);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Type");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setText(crewActions);
		label_7.setBounds(886, 112, 82, 471);
		panel.add(label_7);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Commit Actions", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Spaceship Status", null, panel_1, null);
		panel_1.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(30, 303, 923, 45);
		progressBar.setValue(engine.getSpaceshipHealth());
		progressBar.setStringPainted(true);
		panel_1.add(progressBar);
		
		JLabel lblShieldLevel = new JLabel("Shield Level");
		lblShieldLevel.setBounds(30, 276, 107, 15);
		panel_1.add(lblShieldLevel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Visit Outpost", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("End day");
		btnNewButton.setBounds(898, 673, 114, 25);
		frmCommandCenter.getContentPane().add(btnNewButton);
		
		JLabel lblShipPiecesFound = new JLabel("Ship Pieces Found");
		lblShipPiecesFound.setBounds(12, 673, 136, 25);
		frmCommandCenter.getContentPane().add(lblShipPiecesFound);
		
		JLabel lblCurrentDay = new JLabel("Current Day");
		lblCurrentDay.setBounds(359, 673, 99, 25);
		frmCommandCenter.getContentPane().add(lblCurrentDay);
		
		JLabel lblX = new JLabel("x");
		lblX.setBounds(160, 673, 55, 25);
		frmCommandCenter.getContentPane().add(lblX);
		
		JLabel lblOutOf = new JLabel("out of");
		lblOutOf.setBounds(186, 673, 55, 25);
		frmCommandCenter.getContentPane().add(lblOutOf);
		
		JLabel lblY = new JLabel("y");
		lblY.setBounds(241, 673, 55, 25);
		frmCommandCenter.getContentPane().add(lblY);
		
		JLabel label_8 = new JLabel("x");
		label_8.setBounds(456, 673, 55, 25);
		frmCommandCenter.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("out of");
		label_9.setBounds(482, 673, 55, 25);
		frmCommandCenter.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("y");
		label_10.setBounds(537, 673, 55, 25);
		frmCommandCenter.getContentPane().add(label_10);
		
		JMenuBar menuBar = new JMenuBar();
		frmCommandCenter.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnNewMenu.add(mntmLoad);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
