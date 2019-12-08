package strategies;

import java.util.List;

import representations.RepresentationNode;
import util.General;

/**Implementa l'algoritmo di espansione dell'albero degli stati MinMax con AlphaBeta-Pruning, limitato ad un livello
 * fissato L.
 * @author Vincenzo Parrilla
 */
public class MinMaxAlphaBeta extends SearchAlgorithm {

	protected final double infinite = Double.MAX_VALUE, min_infinite = Double.MIN_VALUE;
	protected final byte L = 5;
	
	/*Esegue il primo passo della funzione valoreMax, ma si applica solo al nodo radice in quanto esegue
	 *operazioni specifiche (come, ad esempio, tenere traccia della migliore mossa fino ad un generico istante t.*/
	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			if(val > v) {
				v = val;
				bestMove = child;
			}
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}
		return bestMove;
	}
	
	/**Restituisce il valore massimo fra le etichette dei nodi figli del nodo passato come parametro, assegnando quindi
	 * questa etichetta al nodo stesso.
	 * @param t il tempo di inizio di questa iterazione
	 * @param depth la profondit� del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come massimo fra le etichette dei nodi figli
	 */
	protected double valoreMax(long t, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = min_infinite;
		if((System.currentTimeMillis() - t) >= LIMIT) return beta;
		
		List<RepresentationNode> actions = General.gameEngine.validActions(node);
		if(actions.isEmpty())
			return strategy.h(node);
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
			v = (v > val)? v : val;
			if(v >= beta) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return beta;
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	/**Restituisce il valore minimo fra le etichette dei nodi figli del nodo passato come parametro, assegnando quindi
	 * questa etichetta al nodo stesso.
	 * @param t il tempo di inizio di questa iterazione
	 * @param depth la profondit� del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come minimo fra le etichette dei nodi figli
	 */
	protected double valoreMin(long t, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = infinite;
		if((System.currentTimeMillis() - t) >= LIMIT) return alpha;
		
		List<RepresentationNode> actions = General.gameEngine.validActions(node);
		if(actions.isEmpty())
			return strategy.h(node);
		
		for(RepresentationNode child: actions) {
			double val = valoreMax(t, (byte)(depth+1), child, alpha, beta);
			v = (v < val)? v : val;
			if(v <= alpha) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return alpha;
			beta = (beta < v)? beta : v;
		}
		return v;
	}
}
