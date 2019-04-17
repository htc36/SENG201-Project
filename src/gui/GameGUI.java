package gui;

import game.GameEngine;

public class GameGUI {
	
	private GameEngine engine;
	
	public GameGUI() {
		engine = new GameEngine();
	}
	
	public void launchMainScreen() {
		StrandeD mainPage = new StrandeD(this);
	}
	
	public void closeMainScreen(StrandeD mainWindow) {
		mainWindow.closeWindow();
		launchSetupScreen();
	}
	
	public void launchSetupScreen() {
		engine = new GameEngine();
		SetupScreen setupScreen = new SetupScreen(engine, this);
	}
	
	public void closeSetupScreen(SetupScreen setupScreen) {
		setupScreen.closeWindow();
		launchCommandCenter();
	}
	
	public void launchCommandCenter() {
		CommandCenter commandCenter = new CommandCenter(engine, this);
	}
	
	public void closeCommandCenter(CommandCenter commandCenter) {
		commandCenter.closeWindow();
	}
	
	public static void main(String[] args) {
		GameGUI game = new GameGUI();
		game.launchMainScreen();
	}
}
