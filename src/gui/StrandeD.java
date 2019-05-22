package gui;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

/**
 * Defines the first window that shows the title, start and load button
 */
public class StrandeD {

    /**
     * Main frame that holds everything in this main screen
     */
    private JFrame frmWelcomeScreen;

    /**
     * Engine that runs and keeps track of the game state
     */
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
     * Closes the Stranded window
     */
    public void closeWindow() {
        frmWelcomeScreen.dispose();
    }

    /**
     * Loads the game from the save file
     */
    public void loadGame() {
        game.loadGame(this);
    }

    /**
     * Closes the Stranded window
     */
    public void finishedWindow() {
        game.closeMainScreen(this);
    }

    /**
     * Launches the Stranded window
     */
    private void initialize() {
        frmWelcomeScreen = new JFrame();
        frmWelcomeScreen.setResizable(false);
        frmWelcomeScreen.setAutoRequestFocus(false);
        frmWelcomeScreen.getContentPane().setBackground(Color.WHITE);
        frmWelcomeScreen.getContentPane().setLayout(null);
        frmWelcomeScreen.setBackground(Color.WHITE);
        frmWelcomeScreen.setTitle("StrandeD");
        frmWelcomeScreen.setBounds(100, 100, 800, 600);
        frmWelcomeScreen.setLocationRelativeTo(null);

        JLabel lblGameTitleShadow = new JLabel("StrandeD");
        lblGameTitleShadow.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitleShadow.setHorizontalTextPosition(SwingConstants.CENTER);
        lblGameTitleShadow.setBackground(Color.WHITE);
        lblGameTitleShadow.setForeground(UIManager.getColor("CheckBoxMenuItem.disabledForeground"));
        lblGameTitleShadow.setBounds(250, 181, 286, 107);
        lblGameTitleShadow.setFont(new Font("Karumbi", Font.PLAIN, 99));
        frmWelcomeScreen.getContentPane().add(lblGameTitleShadow);

        JLabel lblGameTitleFront = new JLabel("StrandeD");
        lblGameTitleFront.setHorizontalTextPosition(SwingConstants.CENTER);
        lblGameTitleFront.setHorizontalAlignment(SwingConstants.CENTER);
        lblGameTitleFront.setForeground(UIManager.getColor("PasswordField.selectionForeground"));
        lblGameTitleFront.setFont(new Font("Karumbi", Font.PLAIN, 90));
        lblGameTitleFront.setBackground(Color.WHITE);
        lblGameTitleFront.setBounds(263, 196, 258, 77);
        frmWelcomeScreen.getContentPane().add(lblGameTitleFront);

        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font("Ubuntu", Font.BOLD, 16));
        btnStart.setForeground(UIManager.getColor("Panel.background"));
        btnStart.addActionListener(new ActionListener() {
            /**
             * Initialize the contents of the frame.
             */
            public void actionPerformed(ActionEvent arg0) {
                finishedWindow();
            }
        });
        btnStart.setBorder(null);
        btnStart.setBackground(Color.DARK_GRAY);
        btnStart.setBounds(336, 300, 110, 42);
        frmWelcomeScreen.getContentPane().add(btnStart);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            /**
             * Loads the previously saved game
             * @param arg0 Action Event
             */
            public void actionPerformed(ActionEvent arg0) {
                loadGame();
            }
        });
        btnLoad.setForeground(UIManager.getColor("Button.background"));
        btnLoad.setFont(new Font("Ubuntu", Font.BOLD, 16));
        btnLoad.setBorder(null);
        btnLoad.setBackground(Color.DARK_GRAY);
        btnLoad.setBounds(336, 364, 110, 42);
        frmWelcomeScreen.getContentPane().add(btnLoad);

        JLabel lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(StrandeD.class.getResource("/img/setupbg.jpg")));
        lblBackground.setBounds(0, 0, 800, 563);
        frmWelcomeScreen.getContentPane().add(lblBackground);
        frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
