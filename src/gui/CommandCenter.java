package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import game.GameEngine;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTable;

public class CommandCenter {

	private JFrame frmCommandCenter;
	private JTable table;
	private GameEngine engine;

	/**
	 * Create the application.
	 */
	public CommandCenter(GameEngine engine) {
		initialize();
		this.engine = engine;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCommandCenter = new JFrame();
		frmCommandCenter.setTitle("Command Center");
		frmCommandCenter.setBounds(100, 100, 800, 600);
		frmCommandCenter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCommandCenter.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 0, 776, 521);
		frmCommandCenter.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Crew Status", null, panel, null);
		panel.setLayout(null);
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};

		table = new JTable(data, columnNames);
		table.setBounds(12, 12, 759, 457);
		panel.add(table);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Commit Actions", null, panel_2, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Spaceship Status", null, panel_1, null);
		panel_1.setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(30, 303, 714, 14);
		panel_1.add(progressBar);
		
		JLabel lblShieldLevel = new JLabel("Shield Level");
		lblShieldLevel.setBounds(30, 276, 107, 15);
		panel_1.add(lblShieldLevel);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Visit Outpost", null, panel_3, null);
		
		JButton btnNewButton = new JButton("End day");
		btnNewButton.setBounds(674, 526, 114, 25);
		frmCommandCenter.getContentPane().add(btnNewButton);
	}
}
