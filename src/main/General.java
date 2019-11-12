package main;

import representations.GameEngine;

public final class General {
	private static GameEngine gameEngine;
	
	public static void setGameEngine(GameEngine ge) { gameEngine = ge; }
	public static GameEngine getGameEngine() { return gameEngine; }
}
