package communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**Classe che implementa l'agente che si occupa di comunicare con il server.
 * @author Vincenzo Parrilla
 */
public class Protocol {
	/**Iniziale del colore nero*/
	public static final char black = 'B';
	/**Possibili direzioni lungo le quali una o più pedine possono essere mosse.*/
	public static final char N = 'N', E = 'E', S = 'S', W = 'W';
	/**Lunghezze di tutti i prefissi dei messaggi inviati dal server.*/
	public static final byte message = 7, opponent_move = 13, your_turn = 9, valid_move = 10,
							 illegal_move = 12, timeout = 7, victory = 7, tie = 3, defeat = 6;
	/**Iniziali dei prefissi con la stessa lunghezza (7).*/
	public static final char msg = 'M', tout = 'T', win = 'V';
	
	/**Il socket client che si connette al server.*/
	protected Socket socket;
	/**Usato per leggere sul canale di comunicazione con il server.*/
	protected Scanner sc;
	/**Usato per scrivere sul canale di comunicazione con il server.*/
	protected PrintWriter pw;
	
	/**Costruttore di base. Crea una nuova istanza di Protocol, inizializzando un socket sull'indirizzo ip e la porta ricevuti
	 * come parametri, effettuando una "connect()" ed inizializzando gli oggetti per l'I/O sul canale di comunicazione con il
	 * server.
	 * @param ip indirizzo del server
 	 * @param port porta sulla quale il server è in ascolto
	 * @throws IOException se le operazioni con i socket non vanno a buon fine
	 */
	public Protocol(String ip, int port) throws IOException {
		if(ip == null || ip.isEmpty() || port < 0) throw new IllegalArgumentException();
		socket = new Socket();
		socket.connect(new InetSocketAddress(ip, port));
		pw = new PrintWriter(socket.getOutputStream());
		sc = new Scanner(socket.getInputStream());
	}
	/**Legge un messaggio da parte del server.
	 * @return una stringa contenente il messaggio
	 */
	public String recv() { return sc.nextLine(); }
	/**Invia un messaggio al server.
	 * @param msg il messaggio da inviare
	 */
	public void send(String msg) { pw.println(msg); pw.flush(); }
}
