package searching;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import representations.RepresentationNode;
import util.General;

public class RandomizedMMAB extends MinMaxAlphaBeta {

	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor()); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		
		double v = min_infinite;
		double alpha = v;
		int maxElem = actions.size(), explored = 0;
		List<RepresentationNode> equalVals = null;
		
		//Stampe per vedere cosa viene generato
		/*System.out.print("depth = "+depth+" : [ ");
		for(RepresentationNode nod: actions)
			System.out.print(nod.getMove()+" ");
		System.out.println("]");*/
		
		for(RepresentationNode child: actions) {
			if(Integer.parseInt(child.getMove().split(",")[2]) > 6) continue;
			
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			if(val > v) {
				child.setHeuristicValue(val);
				v = val;
				bestMove = child;
				equalVals = new ArrayList<>(maxElem - explored);
				equalVals.add(child);
			} else if(val == v && equalVals != null)
				equalVals.add(child);
			
			explored++;
			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}
		
		if(equalVals != null && equalVals.size() > 1) 
			bestMove = equalVals.get(new Random().nextInt(equalVals.size()));
		
		return bestMove;
	}
	
}
