package strategies;

import representations.Representation;

/**Rappresenta un algoritmo di ricerca sull'albero degli stati.
 * @author Vincenzo Parrilla
 */
public abstract class SearchAlgorithm implements IStrategy {
	
	/*Rimane astratto, viene lasciato all'implementazione dell'algoritmo specifico.*/
	public abstract double h(Representation node);
	
	/**Esplora lo spazio di ricerca a partire dalla radice.
	 * @param root radice dell'albero
	 * @return una mossa (es: "F5,N,2")
	 */
	public <T extends Thread> String explore(Representation root, T caller) { return null; } //TODO: trovare la struttura dell'algoritmo
	
	
}
