package strategies;

import java.util.BitSet;

import representations.BitboardRepresentationNode;
import representations.RepresentationNode;
import util.General;

public class AlternativeHeuristic implements IHeuristic {

	private final int BOARDS = 12;
	
	@Override
	public double h(RepresentationNode node) {
		BitboardRepresentationNode brn = (BitboardRepresentationNode) node;

		int playerOrdinal = General.gameEngine.getPlayerColor().ordinal();
		int enemyOrdinal = General.gameEngine.getEnemyColor().ordinal();
		BitSet[] ps = brn.playersPieces[playerOrdinal];
		BitSet[] es = brn.playersPieces[enemyOrdinal];
		
		int totpp = 0, totep = 0; // numero di pedine possedute da ciascun giocatore
			
		for (int i = 0; i < BOARDS; i++) {
			
			if (ps[i].cardinality() > 0) {
				totpp += ps[i].cardinality() * (i + 1);
			}//ps[i].cardinality() > 0
			
			if (es[i].cardinality() > 0){
				totep += es[i].cardinality() * (i + 1);
			}
		}

		if (totpp == 0) return Double.NEGATIVE_INFINITY;
		return totpp - totep ;
	}
}
