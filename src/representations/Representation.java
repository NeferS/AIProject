package representations;

/**Rappresenta il singolo nodo dell'albero dello spazio di ricerca.
 * @author Vincenzo Parrilla
 */
public interface Representation {
	
	/**Crea un nuovo nodo a partire dalla configurazione this.
	 * @param m la mossa da eseguire sulla configurazione
	 * @return la rappresentazione di un nuovo nodo creato applicando la mossa m alla configurazione this
	 */
	Representation move(String m);
	/**Calcola e restituisce tutte le mosse valide a partire da questa configurazione.
	 * @return un array di mosse
	 */
	String[] validActions();
}
