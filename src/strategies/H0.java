package strategies;

import java.util.BitSet;

import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import util.General;

public class H0 implements IHeuristic {
	
	private final int BOARDS = 12;
	private final int[] maxs_stacks  = { 2, 4, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1 };
	
	@Override
	public double h(RepresentationNode node) {
		BitboardRepresentationNode brn = (BitboardRepresentationNode)node;
		
		
		BitSet[] ps = brn.playersPieces[General.gameEngine.getPlayerColor().ordinal()];
		BitSet[] es = brn.playersPieces[General.gameEngine.getEnemyColor().ordinal()];
		//double h = 0.0;
		int totpp = 0, totep = 0, //numero di pedine possedute da ciascun giocatore
			totpm = 0, totem = 0, //numero di mosse necessarie a far uscire tutte le pedine di ciacun giocatore
			tots = 0;
		
		
		for(int i=0; i<BOARDS; i++) {
			//player
			totpp += ps[i].cardinality() * (i+1);
			if(ps[i].cardinality() > 0) {
				int nextpos = ps[i].nextSetBit(0);
				while(nextpos != -1) {
					totpm += (i+1) * (nextpos/4 + 1);
					nextpos = ps[i].nextSetBit(nextpos+1);
				}
				tots += (maxs_stacks[i] - ps[i].cardinality());
			}
			
			//enemy
			totep += es[i].cardinality() * (i+1);
			if(es[i].cardinality() > 0) {
				int nextpos = es[i].nextSetBit(0);
				while(nextpos != -1) {
					totem += (i+1) * (8 - nextpos/4);
					nextpos = es[i].nextSetBit(nextpos+1);
				}
			}
		}
		
		if(totpp == 0) return Double.NEGATIVE_INFINITY;
		if(totep == 0) return Double.POSITIVE_INFINITY;
		return (totpm - totem) + (totpp - totep) + tots;
	}

}
