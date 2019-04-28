package gui;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SpaceshipStatus {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            /**
             * <<auto generated javadoc comment>>
             */
            public void run() {
                try {
                    SpaceshipStatus window = new SpaceshipStatus();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public SpaceshipStatus() {
        initialize();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(33);
        if (progressBar.getValue() < 20) 
            progressBar.setForeground(Color.RED);
        else if (progressBar.getValue() < 50)
            progressBar.setForeground(Color.YELLOW);
        else
            progressBar.setForeground(Color.GREEN);
        progressBar.setStringPainted(true);
        progressBar.setBounds(45, 340, 679, 20);
        frame.getContentPane().add(progressBar);

        JLabel lblShieldLevel = new JLabel("Shield Level");
        lblShieldLevel.setBounds(45, 277, 182, 30);
        frame.getContentPane().add(lblShieldLevel);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(45, 488, 114, 25);
        frame.getContentPane().add(btnBack);
    }
}
