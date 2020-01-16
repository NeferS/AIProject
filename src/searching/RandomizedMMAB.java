package searching;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import representations.RepresentationNode;
import util.General;

public class RandomizedMMAB extends MinMaxAlphaBeta {

	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), moves); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		List<RepresentationNode> equalVals = new LinkedList<>();
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			if(val > v) {
				child.setHeuristicValue(val);
				v = val;
				bestMove = child;
				equalVals.clear();
				equalVals.add(child);
			} else if(val == v)
				equalVals.add(child);
			
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}
		
		if(equalVals.size() > 1) 
			bestMove = equalVals.get(new Random().nextInt(equalVals.size()));
		
		return bestMove;
	}
	
}
