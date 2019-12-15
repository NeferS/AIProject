package player;

import communication.Listener;
import communication.Protocol;
import representations.Color;
import strategies.HashMMAB;
import strategies.MyHeuristic;
import strategies.NewHeuristic;
import strategies.SearchAlgorithm;
import util.General;

public class Player2 extends Player {

	public Player2(Protocol p, SearchAlgorithm a) {
		super(p, a);
	}
	
	/**Aggiorna la rappresentazione del mondo dopo una mossa.
	 * @param move la mossa eseguita
	 */
	public void update(String move) { General.gameEngine.enemyMakeMove(move); }
	
	/**Esegue operazioni di inizializzazione: riceve il messaggio di welcome ed avvia un Listener.*/
	protected void init() {
		String[] welcome = protocol.recv().split(" ");
		if(welcome[0].length() != 7) {
			System.out.println("Errore: messaggio dal server malformato.");
			System.exit(0);
		}//programma terminato
		General.isWhite = welcome[1].charAt(0) != Protocol.black;
		General.gameEngine.start((General.isWhite)? Color.WHITE : Color.BLACK);
		if(!General.isWhite)
			algorithm = new HashMMAB();//MinMaxAlphaBeta();//new RandomizedMMAB();
		algorithm.initStrategy(new MyHeuristic(General.isWhite? Color.WHITE : Color.BLACK));
		System.out.println(protocol.recv()); //MESSAGE Group n, please wait for the opponent
		protocol.recv(); //MESSAGE All players connected
		
		Listener l = new Listener(protocol, this);
		l.start();
	}
	
}
