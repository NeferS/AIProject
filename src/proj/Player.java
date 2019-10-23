package proj;

import communication.Protocol;
import strategies.*;

public class Player extends Thread {
	
	protected Protocol p;
	protected IStrategy s;
	
	public Player(Protocol p) {
		if(p == null) throw new IllegalArgumentException();
		this.p = p;
	}
	
	/*Scheletro algoritmico del giocatore TODO*/
	public void run() {
		
	}
}
