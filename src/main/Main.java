package main;

import communication.Protocol;
import player.Player;
import strategies.MinMaxAlphaBeta;

public class Main {
	public static void main(String[] args) throws Exception {
		if(badUsage(args)) {
			System.out.println("Arguments:\n\t-h <host-name>\n\t-p <port-number>");
			System.exit(0);
		}
		//General.setGameEngine();
		Protocol p = (args[0].charAt(1) == 'h')? new Protocol(args[1], Integer.parseInt(args[3])) : new Protocol(args[3], Integer.parseInt(args[1]));
		Player pl = new Player(p, new MinMaxAlphaBeta());
		pl.start();
	}
	
	protected static boolean badUsage(String[] args) {
		return (args.length < 4) || (args[0].length() != 2 || args[0].charAt(0) != '-' || args[2].length() != 2 || args[2].charAt(0) != '-') ||
			   (args[0].charAt(1) != 'h' && args[0].charAt(1) != 'p') || (args[2].charAt(1) != 'h' &&  args[2].charAt(1) != 'p') || 
			   (args[0].charAt(1) == args[2].charAt(1));
	}
}
