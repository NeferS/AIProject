package representations;

import java.util.List;

public interface GameEngine {
	
	/**Crea un nuovo nodo a partire dalla configurazione this.
	 * @param m la mossa da eseguire sulla configurazione
	 * @return la rappresentazione di un nuovo nodo creato applicando la mossa m alla configurazione this
	 */
	RepresentationNode result(RepresentationNode n, String m);
	/**Calcola e restituisce tutte le mosse valide a partire dalla configurazione passata come parametro.
	 * @param configuration la configurazione di partenza
	 * @return un array di mosse valide
	 */
	List<RepresentationNode> validActions(RepresentationNode configuration);
}
