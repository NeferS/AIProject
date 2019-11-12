package strategies;

import representations.RepresentationNode;
import main.General;

public class MinMaxAlphaBeta extends SearchAlgorithm {

	private static final double infinite = Double.MAX_VALUE, min_infinite = -1;
	private static final byte L = 5; //deve essere dispari per riprendere sempre 
	
	@Override
	public String explore(RepresentationNode node, Thread caller) {
		RepresentationNode[] actions = General.getGameEngine().validActions(node); //validActions deve essere ordinato per pruning efficiente
		String bestMove = actions[0].lastMoveInPath(); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni
		if(bestMove.split(",")[2].charAt(0) == '0') return bestMove;
		
		double v = min_infinite;
		double alpha = v;
		for(RepresentationNode child: actions) {
			double val = valoreMin(caller, (byte)1, child, alpha, infinite);
			if(val > v) {
				v = val;
				bestMove = child.lastMoveInPath();
			}
			if(caller.isInterrupted()) break; //TODO
			alpha = (alpha > val)? alpha : val;
		}
		return bestMove;
	}
	
	protected double valoreMax(Thread caller, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = min_infinite;
		if(caller.isInterrupted()) return beta; //TODO
		for(RepresentationNode child: General.getGameEngine().validActions(node)) {
			double val = valoreMin(caller, (byte)(depth+1), child, alpha, beta);
			v = (v > val)? v : val;
			if(v >= beta) return v;
			if(caller.isInterrupted()) return beta; //TODO
			alpha = (alpha > v)? alpha : v;
		}
		return v;
	}
	
	protected double valoreMin(Thread caller, byte depth, RepresentationNode node, double alpha, double beta) {
		if(depth == L) return strategy.h(node);
		double v = infinite;
		if(caller.isInterrupted()) return alpha; //TODO
		for(RepresentationNode child: General.getGameEngine().validActions(node)) {
			double val = valoreMax(caller, (byte)(depth+1), child, alpha, beta);
			v = (v < val)? v : val;
			if(v <= alpha) return v;
			if(caller.isInterrupted()) return alpha; //TODO
			beta = (beta < v)? beta : v;
		}
		return v;
	}

	@Override
	public void preCompute(RepresentationNode configuration, Thread caller) {
		while(!caller.isInterrupted());
	}
	
}
