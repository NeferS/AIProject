package searching;

import util.General;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import representations.RepresentationNode;

/**Sfrutta informazioni ricavate all'iterazione (i-2)-esima per espandere l'albero all'iterazione i-esima,
 * facendo uso di hashing.
 * @author Vincenzo Parrilla
 */
public class HashMMAB extends MinMaxAlphaBeta {
	
	/**Questa mappa serve ad avere un ordine di precedenza sui nodi del livello l+2, dove l è
	 * il livello dell'iterazione corrente (in cui gioco io).*/
	protected HashMap<RepresentationNode, LinkedList<RepresentationNode>> current, best;
	
	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions;
		
		/*Se ho precedentemente salvato il figlio della mossa migliore dell'iterazione precedente (quella
		 *effettivamente inviata al server) che corrisponde alla mossa effettuata dall'avversario, ho già 
		 *un ordine di preferenza sui nodi figli di tale nodo.*/
		if(best != null && best.get(node) != null)
			actions = best.get(node);
		/*Altrimenti mi affido al gameEngine.*/
		else
			actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), (byte)0); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		best = null;
		for(RepresentationNode child: actions) {
			current = new HashMap<>();
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			child.setHeuristicValue(val);
			if(val > v) {
				v = val;
				bestMove = child;
				best = current;
			}
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}

		return bestMove;
	}
	
	@Override
	protected double valoreMax(long t, byte depth, RepresentationNode node, double alpha, double beta) {
		if(isGoal(node) || depth == L || node.getMove().split(",")[2].equals("0")) return strategy.h(node);
		
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), depth);
		if(actions.isEmpty())
			return strategy.h(node);

		double v = min_infinite;
		
		/*Ogni chiamata di questo metodo al livello 2 viene effettuata su uno dei nodi "radice" dell'iterazione
		 *successiva.*/
		if(depth == 2) 
			current.put(node, new LinkedList<>());
		
		if((System.currentTimeMillis() - t) >= LIMIT) return beta;
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
			child.setHeuristicValue(val);
			v = (v > val)? v : val;
			
			/*Se faccio pruning su questo nodo è perché rappresenta una scelta che probabilmente l'avversario non
			 *compirà; per questo motivo inizio ad alleggerire la tabella hash. Inoltre, se sto facendo pruning,
			 *potrebbe essere che sto interrompendo l'espansione di questo nodo senza aver ancora visitato tutti i
			 *suoi figli.*/
			if(v >= beta) {
				current.remove(node);
				return v;
			}
			/*Se non ho fatto pruning su questo nodo, lo inserisco nella lista dei nodi sibling al suo giusto posto 
			 *(in ordine decrescente di valore euristico).*/
			if(depth == 2) {
				child.setHeuristicValue(val);
				insertSorted(current.get(node), child);
			}
			
			if((System.currentTimeMillis() - t) >= LIMIT) return beta;
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	/**Inserisce l'elemento in testa alla lista, se è vuota, oppure effettua un passo di insertionSort, mantenendo la lista
	 * sempre ordinata in ordine decrescente rispetto al valore euristico degli elementi in essa contenuti.
	 * @param list la lista da espandere
	 * @param node il nodo da inserire
	 */
	protected void insertSorted(LinkedList<RepresentationNode> list, RepresentationNode node) {
		if(list.size() == 0)
			list.addFirst(node);
		else {
			ListIterator<RepresentationNode> lit = list.listIterator();
			boolean found = false;
			while(lit.hasNext() && !found)
				if(lit.next().getHeuristicValue() <= node.getHeuristicValue()) {
					found = true;
					lit.previous();
				}
			lit.add(node);
		}
	}
}
