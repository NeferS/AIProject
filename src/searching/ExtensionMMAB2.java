package searching;

import java.util.List;

import representations.RepresentationNode;
import util.General;

public class ExtensionMMAB2 extends MinMaxAlphaBeta {
	
	@Override
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
			double oldV = v;
			v = (v > val)? v : val;
			//estensione
			if(child.getHeuristicValue() == oldV && depth == L-1) {
				byte original_L = L;
				L = (byte)(L+3);
				val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
				v = (v > val)? v : val;
				L = original_L;
			}//fine estensione
			if(v >= beta) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return val;
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	@Override
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
			double oldV = v;
			v = (v < val)? v : val;
			//estensione
			if(child.getHeuristicValue() == oldV && depth == L-1) {
				byte original_L = L;
				L = (byte)(L+3);
				val = valoreMax(t, (byte)(depth+1), child, alpha, beta);
				v = (v > val)? v : val;
				L = original_L;
			}//fine estensione
			if(v <= alpha) return v;
			if((System.currentTimeMillis() - t) >= LIMIT) return val;
			beta = (beta < v)? beta : v;
		}
		return v;
	}
}
