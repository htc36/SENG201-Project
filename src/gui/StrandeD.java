package gui;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StrandeD {

	private JFrame frmWelcomeScreen;
	private GameGUI game;

	/**
	 * Create the application.
	 */
	public StrandeD(GameGUI game) {
		this.game = game;
		initialize();
		frmWelcomeScreen.setVisible(true);
	}
	
 /**
  * <<auto generated javadoc comment>>
  */
	public void closeWindow() {
		frmWelcomeScreen.dispose();
	}
	
 /**
  * <<auto generated javadoc comment>>
  */
	public void finishedWindow() {
		game.closeMainScreen(this);
	}

	private void initialize() {
		frmWelcomeScreen = new JFrame();
		frmWelcomeScreen.setResizable(false);
		frmWelcomeScreen.setAutoRequestFocus(false);
		frmWelcomeScreen.getContentPane().setBackground(Color.WHITE);
		frmWelcomeScreen.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("StrandeD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 158, 776, 137);
		lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 99));
		frmWelcomeScreen.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
	/**
	 * Initialize the contents of the frame.
	 */
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(336, 300, 110, 42);
		frmWelcomeScreen.getContentPane().add(btnNewButton);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
   /**
    * <<auto generated javadoc comment>>
    * @param arg0 <<Param Desc>>
    */
			public void actionPerformed(ActionEvent arg0) {
				finishedWindow();
			}
		});
		btnLoad.setBorder(null);
		btnLoad.setBackground(Color.WHITE);
		btnLoad.setBounds(336, 362, 110, 42);
		frmWelcomeScreen.getContentPane().add(btnLoad);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(StrandeD.class.getResource("/img/star.png")));
		lblNewLabel_1.setBounds(0, 0, 800, 563);
		frmWelcomeScreen.getContentPane().add(lblNewLabel_1);
		frmWelcomeScreen.setBackground(Color.WHITE);
		frmWelcomeScreen.setTitle("StrandeD");
		frmWelcomeScreen.setBounds(100, 100, 800, 600);
		frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
