package strategies;

import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import util.General;

public class ActualH implements IHeuristic {

	private final int BOARDS = 12;
	private final int[] maxs_stacks  = { 2, 4, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1 };
	
	@Override
	public double h(RepresentationNode node) {
		BitboardRepresentationNode brn = (BitboardRepresentationNode) node;

		BitSet[] ps = brn.playersPieces[General.gameEngine.getPlayerColor().ordinal()];
		BitSet[] es = brn.playersPieces[General.gameEngine.getEnemyColor().ordinal()];
		
		int totpp = 0, totep = 0, // numero di pedine possedute da ciascun giocatore
			tots = 0, //numero di stack totale 
			advantage = 0; //vantaggio rispetto all'avversario in termini di possibilità di cattura
		
		int srcSquare = 0;
		int square =0;
		
		for (int i = 0; i < BOARDS; i++) {
			if (ps[i].cardinality() > 0) 
				tots += (maxs_stacks[i] - ps[i].cardinality());
			
			srcSquare = 0;
			while(true) {
				srcSquare = ps[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				for (int j=i; j<BOARDS; j++) {
					square = 0;
					while(true){
						square = es[j].nextSetBit(square);
						if (square == -1) break;
						int distance = BasicGameEngine.distances[srcSquare][square];
						if (distance != -1) {
							int diffPedine = i-j;
							if ( diffPedine < 0 && j+1 >= distance && i+1<=distance ) {
								advantage--;
							}else if (diffPedine > 0 && i+1 >= distance && j+1<=distance) {
								advantage++;
							}
						}
						square++;
					}	
				}
				srcSquare++;
			}
			
			totpp += ps[i].cardinality() * (i + 1);
			totep += es[i].cardinality() * (i + 1);
		}

		if (totpp == 0) return Double.NEGATIVE_INFINITY;
		if (totep == 0) return Double.POSITIVE_INFINITY;
		return advantage + (totpp - totep) + tots;
	}

}
