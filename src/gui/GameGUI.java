package gui;

import game.GameEngine;

public class GameGUI {
	
	private GameEngine engine;

	public void launchMainScreen() {
		StrandeD mainPage = new StrandeD();
	}
	public static void main(String[] args) {
		GameGUI game = new GameGUI();
		game.launchMainScreen();
	}
}
