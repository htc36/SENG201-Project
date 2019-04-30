package gui;

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
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Final Score");
        frame.getContentPane().setLayout(null);

        JLabel lblFinalScoreText = new JLabel("Your final score is");
        lblFinalScoreText.setHorizontalAlignment(SwingConstants.CENTER);
        lblFinalScoreText.setForeground(Color.WHITE);
        lblFinalScoreText.setFont(new Font("Tibetan Machine Uni", Font.BOLD, 18));
        lblFinalScoreText.setBounds(90, 15, 249, 54);
        frame.getContentPane().add(lblFinalScoreText);

        JLabel lblFinalScoreCount = new JLabel("finalscore");
        lblFinalScoreCount.setFont(new Font("Bitstream Vera Sans Mono", Font.BOLD, 49));
        lblFinalScoreCount.setForeground(Color.WHITE);
        lblFinalScoreCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblFinalScoreCount.setBounds(62, 180, 324, 80);
        frame.getContentPane().add(lblFinalScoreCount);
        lblFinalScoreCount.setText(String.valueOf(engine.getFinalScore()));
        
        JLabel lblNewLabel = new JLabel("Ships Name:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(12, 58, 158, 48);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Number of Days:");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(12, 105, 146, 15);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel shipPieces = new JLabel("fgf");
        shipPieces.setForeground(Color.WHITE);
        shipPieces.setBounds(12, 132, 390, 36);
        if (engine.hasFoundEnoughPieces()) 
        	shipPieces.setText("<html>Congratulations you won the game and have collected all the ship pieces</html>");
        else
        	shipPieces.setText("<html>Better luck next time, you did not collect all the ship pieces</html>");
        frame.getContentPane().add(shipPieces);
        
        JLabel shipNameDetail = new JLabel("New label");
        shipNameDetail.setForeground(Color.WHITE);
        shipNameDetail.setBounds(197, 75, 205, 15);
        shipNameDetail.setText(engine.getSpaceshipName());
        frame.getContentPane().add(shipNameDetail);
        
        JLabel days = new JLabel("New label");
        days.setForeground(Color.WHITE);
        days.setBounds(197, 105, 66, 15);
        days.setText(String.valueOf(engine.getCurrDay()));
        frame.getContentPane().add(days);
    }
}
