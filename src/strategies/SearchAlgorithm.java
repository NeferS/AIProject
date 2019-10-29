package strategies;

import java.util.LinkedList;

import representations.RepresentationNode;

/**Rappresenta un algoritmo di ricerca sull'albero degli stati.
 * @author Vincenzo Parrilla
 */
public abstract class SearchAlgorithm{
	/**La strategia che implementa la funzione euristica dell'algoritmo.*/
	protected IStrategy strategy = null;
	/**Struttura dati che mantiene informazioni sulle mosse possibili (calcolate in anticipo).*/
	protected Object dataStructure; //TODO
	
	/**Inizializza la strategia di questo oggetto.
	 * @param s0 la strategia iniziale
	 * @throws IllegalStateException se la strategia era già stata precedentemente inizializzata
	 */
	public void initStrategy(IStrategy s0) throws IllegalStateException {
		if(s0 == null) throw new IllegalArgumentException();
		if(strategy != null) throw new IllegalStateException();
		strategy = s0;
	}
	
	
	/**Esplora lo spazio di ricerca a partire dalla radice e restituisce la migliore mossa possibile
	 * a partire dalla configurazione radice
	 * @param root radice dell'albero
	 * @return la migliore mossa possibile
	 */
	public String explore(RepresentationNode root, Thread caller) {
		String bestMove = null;
		LinkedList<RepresentationNode> queue = new LinkedList<>();
		queue.addFirst(root);
		while(!caller.isInterrupted()) {
			bestMove = iterate(queue, caller);
		}
		return bestMove;
	}
	
	/**Esegue un passo dell'algoritmo di ricerca.
	 * @param queue la struttura dati che mantiene la frontiera dell'albero.
	 * @return la migliore mossa calcolata in questa iterazione
	 */
	protected abstract String iterate(LinkedList<RepresentationNode> queue, Thread caller);
	
	/**Aggiorna la struttura dati (fa chiamate ad iterate);
	 * @param params parametri
	 */
	public abstract void updateDataStructure(Object...params);
}
