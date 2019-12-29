package strategies;

import java.util.BitSet;

import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import representations.Color;

public class MyHeuristic implements IHeuristic {

	private final int BOARDS = 12;
	private final double[] wp = { -0.041, 0.25, 0.25, 0.083, 0.166, 0.041, 0.083, 0.083, 0.0, 0.083, 0.083, 0.0 },
						   we = { 0.041, 0.25, 0.25, 0.083, 0.166, 0.041, 0.083, 0.083, 0.0, 0.083, 0.083, 0.0 };
	
	private double[][] w = new double[2][];
	private Color playerColor, enemyColor;
	
	public MyHeuristic(Color playerColor) {
		this.playerColor = playerColor;
		this.enemyColor = Color.otherColor(playerColor);
		w[playerColor.ordinal()] = wp;
		w[enemyColor.ordinal()] = we;
	}
	
	@Override
	public double h(RepresentationNode node) {
		//int[] nsp = new int[BOARDS], nse = new int[BOARDS];
		BitSet[] ps = ((BitboardRepresentationNode)node).playersPieces[playerColor.ordinal()];
		BitSet[] es = ((BitboardRepresentationNode)node).playersPieces[enemyColor.ordinal()];
		double h = 0.0;
		int totpp = 0, totep = 0;
		for(int i=0; i<BOARDS; i++) {
			h += w[playerColor.ordinal()][i]*ps[i].cardinality() - w[enemyColor.ordinal()][i]*es[i].cardinality();
			totpp += ps[i].cardinality();
			totep += es[i].cardinality();
		}
		if(totpp == 0) return Double.MIN_VALUE;
		if(totep == 0) return Double.MAX_VALUE;
		return h + (totpp - totep);
	}

}
