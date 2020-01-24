package searching;

import java.util.LinkedList;
import java.util.List;

import representations.RepresentationNode;
import strategies.AlternativeHeuristic;
import util.General;

public class ExtensionMMAB extends MinMaxAlphaBeta {

	@Override
	public RepresentationNode explore(RepresentationNode node, long t) {
		List<RepresentationNode> actions = General.gameEngine.validActions(node, General.gameEngine.getPlayerColor(), (byte)0); //validActions deve essere ordinato per pruning efficiente
		RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni

		double v = min_infinite;
		double alpha = v;
		List<RepresentationNode> equalVals = new LinkedList<>();

		for(RepresentationNode child: actions) {
			double val = valoreMin(t, (byte)1, child, alpha, infinite);
			child.setHeuristicValue(val);
			if(val > v) {
				v = val;
				bestMove = child;
				equalVals.clear();
				equalVals.add(child);
			} else if(val == v)
				equalVals.add(child);

			if((System.currentTimeMillis() - t) >= LIMIT) break;
			alpha = (alpha > val)? alpha : val;
		}

		if(equalVals.size() > 1) {
			MinMaxAlphaBeta m = new MinMaxAlphaBeta();
			m.setLevel(3);
			m.initStrategy(new AlternativeHeuristic());

			double vv = m.explore(equalVals.get(0), t).getHeuristicValue();
			bestMove = equalVals.get(0);
			for ( int i = 1; i < equalVals.size(); i++) {
				double value = m.explore(equalVals.get(i), t).getHeuristicValue();
				if ( value > vv  ) {
					vv = value;
					bestMove = equalVals.get(i);
				}
				if((System.currentTimeMillis() - t) >= LIMIT) break;
			}

		}
		//bestMove = equalVals.get(new Random().nextInt(equalVals.size()));

		return bestMove;
	}

}
