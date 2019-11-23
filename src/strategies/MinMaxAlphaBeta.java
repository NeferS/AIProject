package strategies;

import java.util.List;

import representations.RepresentationNode;
import util.General;

/**Implementa l'algoritmo di espansione dell'albero degli stati MinMax con AlphaBeta-Pruning, limitato ad un livello
 * fissato L.
 * @author Vincenzo Parrilla
 */
public class MinMaxAlphaBeta extends SearchAlgorithm {

	protected final double infinite = Double.MAX_VALUE, min_infinite = -1;
	protected final byte L = 5;
	
	/*Esegue il primo passo della funzione valoreMax, ma si applica solo al nodo radice in quanto esegue
	 *operazioni specifiche (come, ad esempio, tenere traccia della migliore mossa fino ad un generico istante t.*/
	@Override
	public RepresentationNode explore(RepresentationNode node, Thread caller) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		if(bestMove.getMove().split(",")[2].charAt(0) == '0') return bestMove;
		
		double v = min_infinite;
		double alpha = v;
		for(RepresentationNode child: actions) {
			double val = valoreMin(caller, (byte)1, child, alpha, infinite);
			if(val > v) {
				v = val;
				bestMove = child;
			}
			if(caller.isInterrupted()) break; //TODO
			alpha = (alpha > val)? alpha : val;
		}
		return bestMove;
	}
	
	/**Restituisce il valore massimo fra le etichette dei nodi figli del nodo passato come parametro, assegnando quindi
	 * questa etichetta al nodo stesso.
	 * @param caller il thread che ha invocato il metodo
	 * @param depth la profondit� del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come massimo fra le etichette dei nodi figli
	 */
	protected double valoreMax(Thread caller, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = min_infinite;
		if(caller.isInterrupted()) return beta; //TODO
		for(RepresentationNode child: General.gameEngine.validActions(node)) {
			double val = valoreMin(caller, (byte)(depth+1), child, alpha, beta);
			v = (v > val)? v : val;
			if(v >= beta) return v;
			if(caller.isInterrupted()) return beta; //TODO
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	/**Restituisce il valore minimo fra le etichette dei nodi figli del nodo passato come parametro, assegnando quindi
	 * questa etichetta al nodo stesso.
	 * @param caller il thread che ha invocato il metodo
	 * @param depth la profondit� del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come minimo fra le etichette dei nodi figli
	 */
	protected double valoreMin(Thread caller, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = infinite;
		if(caller.isInterrupted()) return alpha; //TODO
		for(RepresentationNode child: General.gameEngine.validActions(node)) {
			double val = valoreMax(caller, (byte)(depth+1), child, alpha, beta);
			v = (v < val)? v : val;
			if(v <= alpha) return v;
			if(caller.isInterrupted()) return alpha; //TODO
			beta = (beta < v)? beta : v;
		}
		return v;
	}
}
