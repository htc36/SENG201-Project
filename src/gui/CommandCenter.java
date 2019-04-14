package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CommandCenter {

	private JFrame frmCommandCenter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommandCenter window = new CommandCenter();
					window.frmCommandCenter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CommandCenter() {
		initialize();
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
		
		JButton btnNewButton = new JButton("View Crew Status");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setFont(new Font("Ubuntu", Font.PLAIN, 24));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 24));
			}
		});
		btnNewButton.setSelected(true);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		btnNewButton.setBounds(340, 47, 306, 59);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		frmCommandCenter.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("View Spaceship Status");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button.setFont(new Font("Ubuntu", Font.PLAIN, 24));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 24));
			}
		});
		button.setSelected(true);
		button.setOpaque(false);
		button.setHorizontalAlignment(SwingConstants.LEADING);
		button.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setBounds(340, 118, 306, 59);
		frmCommandCenter.getContentPane().add(button);
		
		JButton button_1 = new JButton("Commit Actions");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_1.setFont(new Font("Ubuntu", Font.PLAIN, 24));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 24));
			}
		});
		button_1.setSelected(true);
		button_1.setOpaque(false);
		button_1.setHorizontalAlignment(SwingConstants.LEADING);
		button_1.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		button_1.setFocusPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setBorder(null);
		button_1.setBounds(340, 189, 306, 59);
		frmCommandCenter.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Visit Outpost");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_2.setFont(new Font("Ubuntu", Font.PLAIN, 24));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_2.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 24));
			}
		});
		button_2.setSelected(true);
		button_2.setOpaque(false);
		button_2.setHorizontalAlignment(SwingConstants.LEADING);
		button_2.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		button_2.setFocusPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setBorder(null);
		button_2.setBounds(340, 260, 306, 59);
		frmCommandCenter.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("End Day");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				button_3.setFont(new Font("Ubuntu", Font.PLAIN, 24));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_3.setFont(new Font("Ubuntu", Font.BOLD | Font.ITALIC, 24));
			}
		});
		button_3.setSelected(true);
		button_3.setOpaque(false);
		button_3.setHorizontalAlignment(SwingConstants.LEADING);
		button_3.setFont(new Font("Ubuntu", Font.PLAIN, 24));
		button_3.setFocusPainted(false);
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setBorder(null);
		button_3.setBounds(340, 331, 306, 59);
		frmCommandCenter.getContentPane().add(button_3);
	}
}
