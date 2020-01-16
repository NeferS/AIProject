package searching;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import representations.RepresentationNode;
import util.General;
import util.Pair;

public class RandomizedHashMMAB extends HashMMAB {
	
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
			actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), moves); //validActions deve essere ordinato per pruning efficiente
		
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		List<Pair<RepresentationNode, HashMap<RepresentationNode, LinkedList<RepresentationNode>>>> equalVals = new LinkedList<>();
		best = null;
		
		for(RepresentationNode child: actions) {
			current = new HashMap<>();
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			if(val > v) {
				child.setHeuristicValue(val);
				v = val;
				bestMove = child;
				best = current;
				equalVals.clear();
				equalVals.add(new Pair<>(child, best));
			} else if(val == v)
				equalVals.add(new Pair<>(child, current));
				
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}
		
		if(equalVals.size() > 1) {
			Pair<RepresentationNode, HashMap<RepresentationNode, LinkedList<RepresentationNode>>> bestPair = 
					equalVals.get(new Random().nextInt(equalVals.size()));
			bestMove = bestPair.getElement0();
			best = bestPair.getElement1();
		}
		
		return bestMove;
	}
}
