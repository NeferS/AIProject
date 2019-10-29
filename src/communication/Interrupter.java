package communication;


import java.util.TimerTask;

/**Interrompe il thread ricevuto nel costruttore ogni volta che viene eseguito.
 * @author Vincenzo Parrilla
 */
public class Interrupter extends TimerTask {
	/**Il thread da interrompere.*/
	private Thread t;
	public Interrupter(Thread t) {
		if(t == null) throw new IllegalArgumentException();
		this.t = t;
	}
	@Override
	public void run() { t.interrupt(); }
}
