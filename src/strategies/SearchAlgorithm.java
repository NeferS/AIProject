package strategies;

import representations.RepresentationNode;

/**Rappresenta un algoritmo di ricerca sull'albero degli stati.
 * @author Vincenzo Parrilla
 */
public abstract class SearchAlgorithm{
	/**La strategia che implementa la funzione euristica dell'algoritmo.*/
	protected IHeuristic strategy = null;
	
	/**Inizializza la strategia di questo oggetto.
	 * @param s0 la strategia iniziale
	 * @throws IllegalStateException se la strategia era gi� stata precedentemente inizializzata
	 */
	public void initStrategy(IHeuristic s0) throws IllegalStateException {
		if(s0 == null) throw new IllegalArgumentException();
		if(strategy != null) throw new IllegalStateException();
		strategy = s0;
	}
	
	
	/**Esplora lo spazio di ricerca a partire dalla radice e restituisce la migliore mossa possibile
	 * a partire dalla configurazione radice
	 * @param root radice dell'albero
	 * @param caller il thread chiamante
	 * @return la migliore mossa possibile
	 */
	public abstract String explore(RepresentationNode root, Thread caller);
	
	/**Esplora lo spazio di ricerca mentre l'avversario sta rispondendo.
	 * @param configuration configurazione di partenza
	 * @param caller il thread chiamante
	 */
	public abstract void preCompute(RepresentationNode configuration, Thread caller);
}
