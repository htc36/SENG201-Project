package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import game.GameEngine;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class FinalScreen {

	private JFrame frame;
	private GameGUI game;
	private GameEngine engine;

	/**
	 * Create the application.
	 */
	public FinalScreen(GameEngine engine, GameGUI game) {
		initialize();
	}
	/**
	 * <<auto generated javadoc comment>>
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * <<auto generated javadoc comment>>
	 */
	public void finishedWindow() {
		game.closeFinalScreen(this);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Final Score");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("finalscore");
		lblNewLabel.setFont(new Font("Bitstream Vera Sans Mono", Font.BOLD, 49));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(62, 180, 324, 80);
		frame.getContentPane().add(lblNewLabel);
		
		//lblNewLabel.setText(String.valueOf(engine.getFinalScore()));
		lblNewLabel.setText("1999");
		
		JLabel lblNewLabel_1 = new JLabel("Your final score is");
		lblNewLabel_1.setForeground(Color.GREEN);
		lblNewLabel_1.setFont(new Font("Tibetan Machine Uni", Font.BOLD, 14));
		lblNewLabel_1.setBounds(139, 15, 158, 54);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
