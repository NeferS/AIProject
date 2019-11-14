package strategies;

import util.General;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import representations.RepresentationNode;

/**Sfrutta informazioni ricavate all'iterazione (i-2)-esima per espandere l'albero all'iterazione i-esima,
 * facendo uso di hashing.
 * @author Vincenzo Parrilla
 */
public class HashMMAB extends MinMaxAlphaBeta {
	
	/**Questa mappa serve ad avere un ordine di precedenza sui nodi del livello l+2, dove l �
	 * il livello dell'iterazione corrente (in cui gioco io).*/
	private HashMap<RepresentationNode, LinkedList<RepresentationNode>> current, best;
	
	@Override
	public String explore(RepresentationNode node, Thread caller) {
		RepresentationNode[] actions;
		
		/*Se ho precedentemente salvato il figlio della mossa migliore dell'iterazione precedente (quella
		 *effettivamente inviata al server) che corrisponde alla mossa effettuata dall'avversario, ho gi� 
		 *un ordine di preferenza sui nodi figli di tale nodo.*/
		if(best != null && best.get(node) != null)
			actions = best.get(node).toArray(new RepresentationNode[0]);
		/*Altrimenti mi affido al gameEngine.*/
		else
			actions = General.gameEngine.validActions(node); //validActions deve essere ordinato per pruning efficiente
		String bestMove = actions[0].getMove(); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		if(bestMove.split(",")[2].charAt(0) == '0') return bestMove;
		
		double v = min_infinite;
		double alpha = v;
		best = null;
		for(RepresentationNode child: actions) {
			current = new HashMap<>();
			double val = valoreMin(caller, (byte)1, child, alpha, infinite);
			if(val > v) {
				v = val;
				bestMove = child.getMove();
				best = current;
			}
			if(caller.isInterrupted()) break; //TODO
			alpha = (alpha > val)? alpha : val;
		}
		return bestMove;
	}
	
	@Override
	protected double valoreMax(Thread caller, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = min_infinite;
		RepresentationNode[] actions = General.gameEngine.validActions(node);
		
		/*Ogni chiamata di questo metodo al livello 2 viene effettuata su uno dei nodi "radice" dell'iterazione
		 *successiva.*/
		if(depth == 2) 
			current.put(node, new LinkedList<>());
		
		if(caller.isInterrupted()) return beta; //TODO
		for(RepresentationNode child: actions) {
			double val = valoreMin(caller, (byte)(depth+1), child, alpha, beta);
			v = (v > val)? v : val;
			
			/*Se faccio pruning su questo nodo � perch� rappresenta una scelta che probabilmente l'avversario non
			 *compir�; per questo motivo inizio ad alleggerire la tabella hash. Inoltre, se sto facendo pruning,
			 *potrebbe essere che sto interrompendo l'espansione di questo nodo senza aver ancora visitato tutti i
			 *suoi figli.*/
			if(v >= beta) {
				current.remove(node);
				return v;
			}
			/*Se non ho fatto pruning su questo nodo, lo inserisco nella lista dei nodi sibling al suo giusto posto 
			 *(in ordine decrescente di valore euristico).*/
			child.setHeuristicValue(val);
			insertSorted(current.get(node), child);
			
			if(caller.isInterrupted()) return beta; //TODO
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	/**Inserisce l'elemento in testa alla lista, se � vuota, oppure effettua un passo di insertionSort, mantenendo la lista
	 * sempre ordinata in ordine decrescente rispetto al valore euristico degli elementi in essa contenuti.
	 * @param list la lista da espandere
	 * @param node il nodo da inserire
	 */
	private void insertSorted(LinkedList<RepresentationNode> list, RepresentationNode node) {
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