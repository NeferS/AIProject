package main;

import communication.Protocol;
import player.Player;
import representations.*;
import searching.*;
import strategies.*;
import util.General;

public class Main {
	public static void main(String[] args) throws Exception {
		if(badUsage(args)) {
			System.out.println("Arguments:\n\t-h <host-name>\n\t-p <port-number>");
			System.exit(0);
		}
		General.gameEngine = new BasicGameEngine();
		Protocol p = (args[0].charAt(1) == 'h')? new Protocol(args[1], Integer.parseInt(args[3])) : new Protocol(args[3], Integer.parseInt(args[1]));
		Player pl = new Player(p, new HashMMAB(), new ActualH());
		pl.start();
	}
	
	/**Verifica che il programma sia stato lanciato correttamente.
	 * @param args gli argomenti del programma
	 * @return true se gli argomenti sono corretti, false altrimenti
	 */
	protected static boolean badUsage(String[] args) {
		return (args.length < 4) || (args[0].length() != 2 || args[0].charAt(0) != '-' || args[2].length() != 2 || args[2].charAt(0) != '-') ||
			   (args[0].charAt(1) != 'h' && args[0].charAt(1) != 'p') || (args[2].charAt(1) != 'h' &&  args[2].charAt(1) != 'p') || 
			   (args[0].charAt(1) == args[2].charAt(1));
	}
}
