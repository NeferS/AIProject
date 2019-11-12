package representations;

/**Rappresenta il singolo nodo dell'albero dello spazio di ricerca.
 * @author Vincenzo Parrilla
 */
public interface RepresentationNode {
	
	/**Restituisce la mossa che ha generato questa configurazione.
	 * @return la mossa che ha generato questa configurazione
	 */
	<T> T lastMoveInPath();
	/**Salva il valore euristico calcolato per la configurazione this.
	 * @param h il valore della funzione euristica su this
	 */
	void setHeuristicValue(double h);
	/**Recupera il valore euristico calcolato e precentemente salvato per la configurazione this.
	 * @return il valore della funzione euristica su this
	 */
	double getHeuristicValue();
}
