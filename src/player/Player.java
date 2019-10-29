package player;

import communication.Listener;
import communication.Protocol;
import communication.StaticSemaphore;
import representations.RepresentationNode;
import strategies.SearchAlgorithm;

public class Player extends Thread {
	
	protected Protocol protocol;
	protected SearchAlgorithm algorithm;
	protected RepresentationNode configuration;
	protected boolean isWhite;
	
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
		catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**Aggiorna la rappresentazione del mondo dopo una mossa.
	 * @param move la mossa eseguita
	 */
	public void update(String move) {
		if(move != null)
			configuration = configuration.move(move);
	}
	
	/**Esegue operazioni di inizializzazione: riceve il messaggio di welcome ed avvia un Listener.*/
	protected void init() {
		String[] welcome = protocol.recv().split(" ");
		if(welcome[0].length() != 7) {
			System.out.println("Errore: messaggio dal server malformato.");
			System.exit(0);
		}//programma terminato
		configuration = null;//costruisci il primo RepresentationNode passando il colore ricevuto (welcome[1])
		isWhite = welcome[1].charAt(0) != Protocol.black;
		Listener l = new Listener(protocol, this);
		l.start();
	}
	
	//TODO
	protected void warmup() {  }
	
	/**Gioca la partita.
	 * @throws InterruptedException if this thread is interrupted*/
	protected void play() throws InterruptedException {
		if(!isWhite)
			StaticSemaphore.acquire();
		while(true) {
			String bestMove = algorithm.explore(configuration, this);
			protocol.send(bestMove);
			Thread.interrupted();
			configuration = configuration.move(bestMove);
			algorithm.updateDataStructure(new Object[] { }); //punto debole, da migliorare
			StaticSemaphore.acquire();
		}
	}
}
