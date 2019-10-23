package communication;

import proj.Player;

/**Resta tutto il tempo in ascolto di messaggi in arrivo dal server.
 * @author Vincenzo Parrilla
 */
public class Listener extends Thread {
	
	/**Il canale d'ascolto sul server.*/
	protected Protocol pr;
	/**Il giocatore automatico al quale comunicare le azioni del server.*/
	protected Player pl;
	
	/**Costruttore di base.
	 * @param pr canale d'ascolto sul server
	 * @param pl giocatore automatico da notificare
	 */
	public Listener(Protocol pr, Player pl) {
		if(pr == null || pl == null) throw new IllegalArgumentException();
		this.pr = pr;
		this.pl = pl;
	}
	
	@Override
	public void run() {
		
	}
}
