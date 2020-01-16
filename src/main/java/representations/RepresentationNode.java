package representations;

/**Rappresenta il singolo nodo dell'albero dello spazio di ricerca.
 * @author Vincenzo Parrilla
 */
public interface RepresentationNode {
	
	/**Restituisce la mossa che ha generato questa configurazione.
	 * @return la mossa che ha generato questa configurazione
	 */
	public String getMove();
	/**Salva la mossa che ha generato questa configurazione.
	 * @param m la mossa che ha generato questa configurazione
	 */
	public void setMove(String m);
	/**Salva il valore euristico calcolato per la configurazione this.
	 * @param h il valore della funzione euristica su this
	 */
	public void setHeuristicValue(double h);
	/**Recupera il valore euristico calcolato e precentemente salvato per la configurazione this.
	 * @return il valore della funzione euristica su this
	 */
	public double getHeuristicValue();

	//public boolean steadyState(RepresentationNode boardState);

	public Moves getMoveType();
}
