package searching;

import java.util.BitSet;
import java.util.List;

import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import util.General;

/**Implementa l'algoritmo di espansione dell'albero degli stati MinMax con AlphaBeta-Pruning, limitato ad un livello
 * fissato L.
 * @author Vincenzo Parrilla
 */
public class MinMaxAlphaBeta extends SearchAlgorithm {

	protected final double infinite = Double.POSITIVE_INFINITY, min_infinite = Double.NEGATIVE_INFINITY;
	protected byte L = 6;
	
	/*Esegue il primo passo della funzione valoreMax, ma si applica solo al nodo radice in quanto esegue
	 *operazioni specifiche (come, ad esempio, tenere traccia della migliore mossa fino ad un generico istante t.*/
	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), (byte)0); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			child.setHeuristicValue(val);
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
	 * @param depth la profonditÓ del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come massimo fra le etichette dei nodi figli
	 */
	protected double valoreMax(long t, byte depth, RepresentationNode node, double alpha, double beta) {
		if(isGoal(node) || depth == L || node.getMove().split(",")[2].equals("0")) return strategy.h(node);
		
		if((System.currentTimeMillis() - t) >= LIMIT) return beta;
		
		double v = min_infinite;
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), depth);
		if(actions.isEmpty())
			return strategy.h(node);
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
			child.setHeuristicValue(val);
			v = (v > val)? v : val;
			if(v >= beta) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return val;
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	/**Restituisce il valore minimo fra le etichette dei nodi figli del nodo passato come parametro, assegnando quindi
	 * questa etichetta al nodo stesso.
	 * @param t il tempo di inizio di questa iterazione
	 * @param depth la profonditÓ del nodo parametro
	 * @param node il nodo a cui assegnare l'etichetta
	 * @param alpha lower bound per il pruning
	 * @param beta upper bound per il pruning
	 * @return l'etichetta del nodo passato come parametro, ricavata come minimo fra le etichette dei nodi figli
	 */
	protected double valoreMin(long t, byte depth, RepresentationNode node, double alpha, double beta) {
		if(isGoal(node) || depth == L) return strategy.h(node);
		
		if((System.currentTimeMillis() - t) >= LIMIT) return alpha;

		double v = infinite;
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getEnemyColor(), depth);
		if(actions.isEmpty())
			return strategy.h(node);
		
		for(RepresentationNode child: actions) {
			double val = valoreMax(t, (byte)(depth+1), child, alpha, beta);
			child.setHeuristicValue(val);
			v = (v < val)? v : val;
			if(v <= alpha) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return val;
			beta = (beta < v)? beta : v;
		}
		return v;
	}
	
	@Override
	public boolean isGoal(RepresentationNode node) {
		BitSet[] enemyPieces = ((BitboardRepresentationNode)node).playersPieces[General.gameEngine.getEnemyColor().ordinal()];
		for(BitSet bitset: enemyPieces)
			if(bitset.cardinality() > 0)
				return false;
		return true;
	}
	
	/**Cambia il livello massimo al quale considerare i nodi come foglie.
	 * @param level il nuovo livello massimo
	 */
	public void setLevel(int level) { L = (byte)level; }
}
