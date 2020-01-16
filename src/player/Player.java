package player;

import communication.Listener;
import communication.Protocol;
import util.Semaphores;
import util.General;
import representations.Color;
import representations.RepresentationNode;
import searching.MinMaxAlphaBeta;
import searching.SearchAlgorithm;
import strategies.IHeuristic;

public class Player extends Thread {
	
	protected Protocol protocol;
	protected SearchAlgorithm algorithm;
	protected IHeuristic initialStrategy;
	protected final String MOVE = "MOVE ";
	
	public Player(Protocol p, SearchAlgorithm a, IHeuristic s) {
		if(p == null || a == null || s == null) throw new IllegalArgumentException();
		protocol = p;
		algorithm = a;
		initialStrategy = s;
	}
	
	/*Scheletro algoritmico del giocatore*/
	public void run() {
		init();
		warmup();
		try { play(); } 
		catch (InterruptedException e) { e.printStackTrace(); System.exit(-1); }
	}
	
	/**Aggiorna la rappresentazione del mondo dopo una mossa.
	 * @param move la mossa eseguita
	 */
	public void update(String move) { General.gameEngine.enemyMakeMove(move); algorithm.oneMove(); }
	
	/**Esegue operazioni di inizializzazione: riceve il messaggio di welcome ed avvia un Listener.*/
	protected void init() {
		String[] welcome = protocol.recv().split(" ");
		if(welcome[0].length() != 7) {
			System.out.println("Errore: messaggio dal server malformato.");
			System.exit(0);
		}//programma terminato
		General.isWhite = welcome[1].charAt(0) != Protocol.black;
		General.gameEngine.start((General.isWhite)? Color.WHITE : Color.BLACK);
		algorithm.initStrategy(initialStrategy);
		System.out.println(protocol.recv()); //MESSAGE Group n, please wait for the opponent
		protocol.recv(); //MESSAGE All players connected
		
		Listener l = new Listener(protocol, this);
		l.start();
	}
	
	protected void warmup() {  }//TODO
	
	/**Gioca la partita.
	 * @throws InterruptedException if this thread is interrupted*/
	protected void play() throws InterruptedException {
		while(true) {
			long t = Semaphores.waitACK();
			RepresentationNode configuration = algorithm.explore(General.gameEngine.getCurrentBoardState(), t);
			protocol.send(MOVE+configuration.getMove());
			General.gameEngine.playerMakeMove(configuration);
			algorithm.oneMove();
			if(algorithm.moves() <= 25)
				((MinMaxAlphaBeta)algorithm).updateLevel();
			
			//Da rimuovere TODO
			if(General.isWhite) System.out.println("White : " + configuration.getMove());
			else System.out.println("Black : " + configuration.getMove());
			//Fine rimozione
			
			Semaphores.waitACK();
		}
	}
}
