package main;

import java.awt.Color;

import controller.BoardDataStructure;
import controller.Colour;
import controller.HumanClient;
import representations.BasicGameEngine;
import util.General;
import view.GUI;

public class MainHuman {

	public static void main(String[] args) throws Exception {
		BoardDataStructure board = new BoardDataStructure(8, 8);
		GUI.showGUI(board);
		board.add(7,4,Colour.White, 12);
		board.add(0,3,Colour.Black, 12);
		//suppongo che sto giocando come nero 
		GUI.color = Color.BLACK;
		GUI.refreshBoard(board);
		GUI.refreshBoard(board);
		
	}
	
	
	/*
	public static void main(String[] args) throws Exception {
		if(badUsage(args)) {
			System.out.println("Arguments:\n\t-h <host-name>\n\t-p <port-number>");
			System.exit(0);
		}
		General.gameEngine = new BasicGameEngine();	
		HumanClient pl = (args[0].charAt(1) == 'h')? new HumanClient(args[1], Integer.parseInt(args[3])) : new HumanClient(args[3], Integer.parseInt(args[1]));
		pl.play();
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
