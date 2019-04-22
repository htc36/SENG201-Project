package gui;

import game.GameEngine;

public class GameGUI {
	
	private GameEngine engine;
	
 /**
  * <<auto generated javadoc comment>>
  */
	public GameGUI() {
		engine = new GameEngine();
	}
	
 /**
  * <<auto generated javadoc comment>>
  */
	public void launchMainScreen() {
		StrandeD mainPage = new StrandeD(this);
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
		SetupScreen setupScreen = new SetupScreen(engine, this);
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
		CommandCenter commandCenter = new CommandCenter(engine, this);
	}
	
 /**
  * <<auto generated javadoc comment>>
  * @param commandCenter <<Param Desc>>
  */
	public void closeCommandCenter(CommandCenter commandCenter) {
		commandCenter.closeWindow();
	}
	
 /**
  * <<auto generated javadoc comment>>
  * @param args <<Param Desc>>
  */
	public static void main(String[] args) {
		GameGUI game = new GameGUI();
		game.launchMainScreen();
	}
}
