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

        JLabel lblNewLabel = new JLabel("StrandeD");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setForeground(UIManager.getColor("CheckBoxMenuItem.disabledForeground"));
        lblNewLabel.setBounds(250, 181, 286, 107);
        lblNewLabel.setFont(new Font("Karumbi", Font.PLAIN, 99));
        frmWelcomeScreen.getContentPane().add(lblNewLabel);

        JButton btnNewButton = new JButton("Start");
        btnNewButton.setFont(new Font("Ubuntu", Font.BOLD, 16));
        btnNewButton.setForeground(UIManager.getColor("Panel.background"));
        btnNewButton.addActionListener(new ActionListener() {
            /**
             * Initialize the contents of the frame.
             */
            public void actionPerformed(ActionEvent arg0) {
                finishedWindow();
            }
        });
        btnNewButton.setBorder(null);
        btnNewButton.setBackground(Color.DARK_GRAY);
        btnNewButton.setBounds(336, 300, 110, 42);
        frmWelcomeScreen.getContentPane().add(btnNewButton);

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

        JLabel label = new JLabel("StrandeD");
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(UIManager.getColor("PasswordField.selectionForeground"));
        label.setFont(new Font("Karumbi", Font.PLAIN, 90));
        label.setBackground(Color.WHITE);
        label.setBounds(263, 196, 258, 77);
        frmWelcomeScreen.getContentPane().add(label);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(StrandeD.class.getResource("/img/setupbg.jpg")));
        lblNewLabel_1.setBounds(0, 0, 800, 563);
        frmWelcomeScreen.getContentPane().add(lblNewLabel_1);
        frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
