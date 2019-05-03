package gui;

import javax.swing.JFrame;

import game.GameEngine;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class FinalScreen {

    /**
     * Main frame that holds everything inside the final screen
     */
    private JFrame frame;

    /**
     * The gui manager
     */
    private GameGUI game;

    /**
     * Engine that runs and keeps track of the game state
     */
    private GameEngine engine;

    /**
     * Create the application.
     */
    public FinalScreen(GameEngine engine, GameGUI game) {
        this.game = game;
        this.engine = engine;
        initialize();
    }
    /**
     * Closes window
     */
    public void closeWindow() {
        frame.dispose();
    }

    /**
     * Closes window
     */
    public void finishedWindow() {
        game.closeFinalScreen(this);
    }
    /**
     * Sets up the final screen window
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setBounds(100, 100, 350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Final Score");
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JLabel lblFinalScoreText = new JLabel("Final score:");
        lblFinalScoreText.setHorizontalAlignment(SwingConstants.LEFT);
        lblFinalScoreText.setForeground(Color.WHITE);
        lblFinalScoreText.setFont(new Font("Quantico", Font.BOLD, 18));
        lblFinalScoreText.setBounds(24, 24, 130, 54);
        frame.getContentPane().add(lblFinalScoreText);

        JLabel lblFinalScoreCount = new JLabel("");
        lblFinalScoreCount.setFont(new Font("Bitstream Vera Sans Mono", Font.BOLD, 30));
        lblFinalScoreCount.setForeground(Color.WHITE);
        lblFinalScoreCount.setHorizontalAlignment(SwingConstants.LEFT);
        lblFinalScoreCount.setBounds(197, 12, 187, 80);
        frame.getContentPane().add(lblFinalScoreCount);
        lblFinalScoreCount.setText(String.valueOf(engine.getFinalScore()));
        
        JLabel lblShipName = new JLabel("Ship's Name:");
        lblShipName.setFont(new Font("Ubuntu Light", Font.BOLD, 13));
        lblShipName.setForeground(Color.WHITE);
        lblShipName.setBounds(24, 163, 158, 25);
        frame.getContentPane().add(lblShipName);
        
        JLabel lblNumDays = new JLabel("Number of days:");
        lblNumDays.setFont(new Font("Ubuntu Light", Font.BOLD, 13));
        lblNumDays.setForeground(Color.WHITE);
        lblNumDays.setBounds(24, 212, 146, 15);
        frame.getContentPane().add(lblNumDays);
        
        JLabel lblWonGame = new JLabel("");
        lblWonGame.setHorizontalAlignment(SwingConstants.LEFT);
        lblWonGame.setForeground(Color.WHITE);
        lblWonGame.setBounds(24, 88, 314, 43);
        String template = "<html>";
        if (engine.hasFoundEnoughPieces()) {
            lblWonGame.setForeground(Color.GREEN);
        	template += "You have collected all the ship pieces<br>";
        	template += "Congratulations, you won the game!";
        }
        else {
            lblWonGame.setForeground(Color.RED);
        	template += "You did not collect all the ship pieces<br>";
        	template += "better luck next time!";
        }
        template += "</html>";
        lblWonGame.setText(template);
        frame.getContentPane().add(lblWonGame);
        
        JLabel shipNameDetail = new JLabel("");
        shipNameDetail.setForeground(Color.WHITE);
        shipNameDetail.setBounds(197, 168, 141, 15);
        shipNameDetail.setText(engine.getSpaceshipName());
        frame.getContentPane().add(shipNameDetail);
        
        JLabel days = new JLabel("");
        days.setForeground(Color.WHITE);
        days.setBounds(197, 212, 66, 15);
        days.setText(String.valueOf(engine.getCurrDay() - 1));
        frame.getContentPane().add(days);
    }
}
