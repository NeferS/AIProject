package player;

import communication.Listener;
import communication.Protocol;
import util.Semaphores;
import util.General;
import representations.Color;
import representations.RepresentationNode;
import strategies.HashMMAB;
import strategies.MinMaxAlphaBeta;
import strategies.MyHeuristic;
import strategies.NewHeuristic;
import strategies.RandomizedMMAB;
import strategies.SearchAlgorithm;

public class Player extends Thread {
	
	protected Protocol protocol;
	protected SearchAlgorithm algorithm;
	protected boolean sent;
	protected final String MOVE = "MOVE ";
	
	public Player(Protocol p, SearchAlgorithm a) {
		if(p == null || a == null) throw new IllegalArgumentException();
		protocol = p;
		algorithm = a;
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
		algorithm.initStrategy(new NewHeuristic(General.isWhite? Color.WHITE : Color.BLACK)); //MyHeuristic(General.isWhite? Color.WHITE : Color.BLACK));
		System.out.println(protocol.recv()); //MESSAGE Group n, please wait for the opponent
		protocol.recv(); //MESSAGE All players connected
		
		Listener l = new Listener(protocol, this);
		l.start();
	}
	
	protected void warmup() {  }//TODO
	
	/**Gioca la partita.
	 * @throws InterruptedException if this thread is interrupted*/
	protected void play() throws InterruptedException {
		
		if(!General.isWhite) {
			//algorithm.preCompute(General.gameEngine.getCurrentBoardState(), this);
			//while(Thread.interrupted());
		}
		
		while(true) {
			long t = Semaphores.waitACK();
			RepresentationNode configuration = algorithm.explore(General.gameEngine.getCurrentBoardState(), t);
			General.gameEngine.playerMakeMove(configuration);
			protocol.send(MOVE+configuration.getMove());
			if(General.isWhite) System.out.println("White : " + configuration.getMove());
			else System.out.println("Black : " + configuration.getMove());
			sent = true;
			Semaphores.waitACK();
			
			//Thread.interrupted();
			//algorithm.preCompute(General.gameEngine.getCurrentBoardState(), this);
			//Thread.interrupted();
		}
	}
	
	/**Indica se il giocatore ha inviato la risposta al server.
	 * @return true se il giocatore ha inviato la sua risposta
	 */
	/*
	public boolean didSend() {
		if(sent) {
			sent = false;
			return true;
		}
		return false;
	}
	*/
}
