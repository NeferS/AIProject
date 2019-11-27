package util;

import java.util.concurrent.Semaphore;

/**Classe di utilit� in cui inserire gli eventuali semafori utilizzati per la sincronizzazione
 * dei thread (fra di loro o con il server).
 * @author Vincenzo Parrilla
 */
public final class Semaphores {
	
	/**Il semaforo 'ack' viene utilizzato per segnalare un acknowledgement in seguito alla risposta
	 * del server circa la mossa inviata.*/
	private static Semaphore ack = new Semaphore(0);
	private static long t;
	
	public static long waitACK() { 
		try { ack.acquire(); }
		catch (InterruptedException e) { e.printStackTrace(); }
		return t;
	}
	public static void sendACK() { t = System.currentTimeMillis(); ack.release(); }
}
