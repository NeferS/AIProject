package communication;

import java.util.Timer;
import player.Player;
import util.Semaphores;

/**Resta tutto il tempo in ascolto di messaggi in arrivo dal server.
 * @author Vincenzo Parrilla
 */
public class Listener extends Thread {
	
	/**Il canale d'ascolto sul server.*/
	protected Protocol pr;
	/**Il giocatore automatico al quale comunicare le azioni del server.*/
	protected Player pl;
	/**Timer che viene attivato quando è il turno del giocatore.*/
	protected Timer timer;
	/**Tempo concesso al giocatore automatico per rispondere,*/
	private final long t = 800;
	
	/**Costruttore di base.
	 * @param pr canale d'ascolto sul server
	 * @param pl giocatore automatico da notificare
	 */
	public Listener(Protocol pr, Player pl) {
		if(pr == null || pl == null) throw new IllegalArgumentException();
		this.pr = pr;
		this.pl = pl;
		timer = new Timer();
	}
	
	@Override
	public void run() {
		while(true) {
			String[] msg = pr.recv().split(" ");
			switch(msg[0].length()) {
			case 9: //YOUR_TURN
				/*Pianifica l'interruzione dell'elaborazione per fornire una risposta al server.*/
				timer.schedule(new Interrupter(pl), t);
				printMsg(msg[0]);
				break;
			case 10: case 12://VALID_MOVE, ILLEGAL_MOVE
				Semaphores.sendACK();
				printMsg(msg[0]);
				break;
			case 13: //OPPONENT_MOVE
				/*Notifica il giocatore che può aggiornare la configurazione corrente.*/
				//pl.update(msg[1]); //TODO
				pl.interrupt();
				printMsg(msg[0]);
				break;
			case 7: //TIMEOUT, VICTORY, MESSAGE
				if(msg[0].charAt(0) == Protocol.tout)
					printMsg(msg[0]);
				else if(msg[0].charAt(0) == Protocol.msg)
					printMsg(msg);
				else {
					printMsg(msg[0]);
					System.exit(0);
				}
				break;
			case 6: case 3: //DEFEAT, TIE
				printMsg(msg[0]);
				System.exit(0);
			}
		}
	}
	
	/**Stampa un messaggio.
	 * @param msg il messaggio da stampare
	 */
	public void printMsg(String msg) { System.out.println(msg);	}
	
	/**Stampa un messaggio che era stato precedentemente splittato.
	 * @param msg il messaggio da stampare
	 */
	public void printMsg(String[] msg) {
		String s = "";
		for(String tk: msg)
			s += tk+" ";
		System.out.println(s);
	}
	
}
