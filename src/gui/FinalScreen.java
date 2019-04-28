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
     * <<auto generated javadoc comment>>
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
    }
}
