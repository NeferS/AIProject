package searching;

import representations.RepresentationNode;
import strategies.IHeuristic;

public class IterativeDeepening extends SearchAlgorithm {

	protected final byte MIN_CUT_L = 5, MAX_CUT_L = 15;
	protected MinMaxAlphaBeta mmab = new MinMaxAlphaBeta();
	
	@Override
	public void initStrategy(IHeuristic s0) throws IllegalStateException {
		super.initStrategy(s0);
		mmab.initStrategy(strategy);
	}
	
	@Override
	public RepresentationNode explore(RepresentationNode root, long t) {
		RepresentationNode bestMove = null;
		RepresentationNode current = null;
		int i;
		for(i=MIN_CUT_L; i<=MAX_CUT_L; i++) {
			mmab.setL(i);
			current = mmab.explore(root, t);
			if((System.currentTimeMillis() - t) >= LIMIT) {
				if(bestMove == null)
					bestMove = current;
				break;
			} else
				bestMove = current;
		}
		
		return bestMove;
	}
	
	
	@Override
	public void oneMove() { moves++; mmab.oneMove(); }
	@Override
	public byte moves() { return moves; }
	@Override
	public void updateLevel() { }

}
