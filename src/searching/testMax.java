package searching;

import java.util.BitSet;
import java.util.List;

import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import util.General;

public class testMax extends MinMaxAlphaBeta {
	
	public int goals = 0;
	
	/*Esegue il primo passo della funzione valoreMax, ma si applica solo al nodo radice in quanto esegue
	 *operazioni specifiche (come, ad esempio, tenere traccia della migliore mossa fino ad un generico istante t.*/
	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getEnemyColor(), (byte)0); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			child.setHeuristicValue(val);
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
		isGoal(node);
		if(depth == L) return 0.0;
		
		
		double v = min_infinite;
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getEnemyColor(), depth);
		System.out.println("Livello: "+depth+", actions: "+actions.size());
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
			child.setHeuristicValue(val);
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
		isGoal(node);
		if(depth == L || node.getMove().split(",")[2].equals("0")) return 0.0;


		double v = infinite;
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), depth);
		System.out.println("Livello: "+depth+", actions: "+actions.size());
		
		for(RepresentationNode child: actions) {
			double val = valoreMax(t, (byte)(depth+1), child, alpha, beta);
			child.setHeuristicValue(val);
		}
		return v;
	}
	
	@Override
	public boolean isGoal(RepresentationNode node) {
		BitSet[] enemyPieces = ((BitboardRepresentationNode)node).playersPieces[General.gameEngine.getEnemyColor().ordinal()];
		for(BitSet bitset: enemyPieces)
			if(bitset.cardinality() > 0)
				return false;
		goals += 1;
		System.out.println(goals);
		return true;
	}
	
	/**Cambia il livello massimo al quale considerare i nodi come foglie.
	 * @param level il nuovo livello massimo
	 */
	public void setLevel(int level) { L = (byte)level; }
	

}
