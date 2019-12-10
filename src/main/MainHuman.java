package main;

import controller.HumanClient;
import model.BoardDataStructure;
import representations.Color;
import view.Game;

public class MainHuman {

	public static void main(String[] args) throws Exception {
		BoardDataStructure board = new BoardDataStructure(8, 8);
		HumanClient playerClient = new HumanClient("localhost", 8901);
		board.add(7,4,Color.WHITE, 12);
		board.add(0,3,Color.BLACK, 12);
		/*
		 * Avvio client senza gui
		playerClient.play();
		 */
		
		Game g = new Game(board, playerClient);
		playerClient.playGUI(g,board);
		  
		
	}
	
}
