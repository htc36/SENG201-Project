package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameEngine;

public class GameGUI {

    private GameEngine engine;
    private GraphicsEnvironment gameEnv;

    /**
     * Constructor for game gui
     */
    public GameGUI() {
        engine = new GameEngine();
        gameEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
        	URL fontPath = getClass().getResource("/font/Karumbi.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));

            fontPath = getClass().getResource("/font/Quantico-Regular.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));
            fontPath = getClass().getResource("/font/Quantico-Bold.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));

            fontPath = getClass().getResource("/font/UbuntuMono-R.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));
            fontPath = getClass().getResource("/font/UbuntuMono-B.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));

            fontPath = getClass().getResource("/font/Ubuntu-R.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));
            fontPath = getClass().getResource("/font/Ubuntu-B.ttf");
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, fontPath.openStream()));

        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 


    }

    /**
     * Loads the game from previously saved game
     * @param mainWindow main window
     */
    public void loadGame(StrandeD mainWindow) {
        mainWindow.closeWindow();
        String homeEnv = System.getenv("HOME");
        try {
			engine = new GameEngine(homeEnv + "/.save.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            return;
		}
        launchCommandCenter();
    }

    /**
     * Loads game from command center
     * @param commandCenter command center
     */
    public void loadGame(CommandCenter commandCenter) {
        commandCenter.closeWindow();
        String homeEnv = System.getenv("HOME");
        try {
			engine = new GameEngine(homeEnv + "/.save.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            return;
		}
        launchCommandCenter();
    }

    /**
     * Launches the main screen
     */
    public void launchMainScreen() {
        new StrandeD(this);
    }

    /**
     * Closes the main screen
     * @param mainWindow main window
     */
    public void closeMainScreen(StrandeD mainWindow) {
        mainWindow.closeWindow();
        launchSetupScreen();
    }

    /**
     * Launches setup screen
     */
    public void launchSetupScreen() {
        engine = new GameEngine();
        new SetupScreen(engine, this);
    }

    /**
     * Closes setup screen
     * @param setupScreen setup screen
     */
    public void closeSetupScreen(SetupScreen setupScreen) {
        setupScreen.closeWindow();
        launchCommandCenter();
    }

    /**
     * launches command center
     */
    public void launchCommandCenter() {
        new CommandCenter(engine, this);
    }

    /**
     * Closes command center
     * @param commandCenter Command center
     */
    public void closeCommandCenter(CommandCenter commandCenter) {
        commandCenter.closeWindow();
        launchFinalScreen();
    }

    /**
     * Launches final screen
     */
    public void launchFinalScreen() {
        new FinalScreen(engine, this);
    }

    /**
     * Closes final screen
     * @param finalScreen final screen
     */
    public void closeFinalScreen(FinalScreen finalScreen) {
        finalScreen.closeWindow();
    }

    /**
     * Creates new game engine and launches the main screen
     * @param args 
     */
    public static void main(String[] args) {
        // Load the custom font we use for the game
        GameGUI game = new GameGUI();
        game.launchMainScreen();
        //game.launchFinalScreen();
    }
}
