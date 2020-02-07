package searching;

import representations.RepresentationNode;
import strategies.IHeuristic;

/**Rappresenta un algoritmo di ricerca sull'albero degli stati.
 * @author Vincenzo Parrilla
 */
public abstract class SearchAlgorithm{
	/**La strategia che implementa la funzione euristica dell'algoritmo.*/
	protected IHeuristic strategy = null;
	protected final long LIMIT = 950;
	
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
	public abstract RepresentationNode explore(RepresentationNode root, long t);
	
	/**Restituisce true se il nodo passato � un goal.
	 * @param node il nodo da esaminare
	 * @return true se node � un goal, false altrimenti
	 */
	public abstract boolean isGoal(RepresentationNode node);
	
	/**Esplora lo spazio di ricerca mentre l'avversario sta rispondendo.
	 * @param configuration configurazione di partenza
	 * @param caller il thread chiamante
	 */
	public void preCompute(RepresentationNode configuration, Thread caller) { while(!caller.isInterrupted()); }
}
