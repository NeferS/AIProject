package communication;


import java.util.TimerTask;

import player.Player;

/**Interrompe il thread ricevuto nel costruttore ogni volta che viene eseguito.
 * @author Vincenzo Parrilla
 */
public class Interrupter extends TimerTask {
	/**Il thread da interrompere.*/
	private Player p;
	public Interrupter(Player p) {
		if(p == null) throw new IllegalArgumentException();
		this.p = p;
	}
	@Override
	public void run() { 
		//if(!p.didSend()) p.interrupt(); 
		}
}

