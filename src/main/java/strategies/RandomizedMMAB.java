package strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import representations.RepresentationNode;
import util.General;

public class RandomizedMMAB extends MinMaxAlphaBeta {

	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		int maxElem = actions.size(), explored = 0;
		List<RepresentationNode> equalVals = null;
		
		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			
			if(val > v) {
				v = val;
				bestMove = child;
				equalVals = new ArrayList<RepresentationNode>(maxElem - explored);
				equalVals.add(child);
			} else if(Math.abs(val - v) < 1e-8 && equalVals != null)
				equalVals.add(child);
			
			explored++;
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}
		
		if(equalVals != null && equalVals.size() > 1) {
			/*
			System.out.print("[ ");
			for(RepresentationNode n1: equalVals)
				System.out.print(n1.getMove()+","+n1.getHeuristicValue()+" ");
			System.out.println("]");
			*/

			bestMove = equalVals.get(new Random().nextInt(equalVals.size()));
		}
		return bestMove;
	}
	
}
