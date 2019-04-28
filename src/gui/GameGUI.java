package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import game.GameEngine;

public class GameGUI {

    private GameEngine engine;
    private GraphicsEnvironment gameEnv;

    /**
     * <<auto generated javadoc comment>>
     */
    public GameGUI() {
        engine = new GameEngine();
        gameEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Karumbi.ttf")));

            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Quantico-Regular.ttf")));
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Quantico-Bold.ttf")));

            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/UbuntuMono-R.ttf")));
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/UbuntuMono-B.ttf")));

            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Ubuntu-R.ttf")));
            gameEnv.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Ubuntu-B.ttf")));

        } catch (FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //		for (int i = 0; i < gameEnv.getAvailableFontFamilyNames().length; i++) {
        //			System.out.println(gameEnv.getAvailableFontFamilyNames()[i]);
        //		}
    }

    /**
     * <<auto generated javadoc comment>>
     * @param mainWindow <<Param Desc>>
     */
    public void loadGame(StrandeD mainWindow) {
        mainWindow.closeWindow();
        engine = new GameEngine("./save.json");
        launchCommandCenter();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param commandCenter <<Param Desc>>
     */
    public void loadGame(CommandCenter commandCenter) {
        commandCenter.closeWindow();
        engine = new GameEngine("./save.json");
        launchCommandCenter();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void launchMainScreen() {
        new StrandeD(this);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param mainWindow <<Param Desc>>
     */
    public void closeMainScreen(StrandeD mainWindow) {
        mainWindow.closeWindow();
        launchSetupScreen();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void launchSetupScreen() {
        engine = new GameEngine();
        new SetupScreen(engine, this);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param setupScreen <<Param Desc>>
     */
    public void closeSetupScreen(SetupScreen setupScreen) {
        setupScreen.closeWindow();
        launchCommandCenter();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void launchCommandCenter() {
        new CommandCenter(engine, this);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param commandCenter <<Param Desc>>
     */
    public void closeCommandCenter(CommandCenter commandCenter) {
        commandCenter.closeWindow();
        launchFinalScreen();
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void launchFinalScreen() {
        new FinalScreen(engine, this);
    }

    /**
     * <<auto generated javadoc comment>>
     * @param finalScreen <<Param Desc>>
     */
    public void closeFinalScreen(FinalScreen finalScreen) {
        finalScreen.closeWindow();
    }

    /**
     * <<auto generated javadoc comment>>
     * @param args <<Param Desc>>
     */
    public static void main(String[] args) {
        // Load the custom font we use for the game
        GameGUI game = new GameGUI();
        game.launchMainScreen();
        //game.launchFinalScreen();
    }
}
