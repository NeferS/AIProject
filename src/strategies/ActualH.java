package strategies;

import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.Direction;
import representations.RepresentationNode;
import util.General;

public class ActualH implements IHeuristic {

	private final int BOARDS = 12;
	private final int[] maxs_stacks  = { 2, 4, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1 };
	private final int[][] notAlloweds = { { 1,6,7 }, {0,4,5} };
	private final double max = 0.09, min = 0.01;
	
	@Override
	public double h(RepresentationNode node) {
		BitboardRepresentationNode brn = (BitboardRepresentationNode) node;

		int srcSquare, square, quantity;
		int playerOrdinal = General.gameEngine.getPlayerColor().ordinal();
		int enemyOrdinal = General.gameEngine.getEnemyColor().ordinal();
		BitSet[] ps = brn.playersPieces[playerOrdinal];
		BitSet[] es = brn.playersPieces[enemyOrdinal];
		
		int totpp = 0, totep = 0, // numero di pedine possedute da ciascun giocatore
			totpm = 0, totem = 0, //numero di mosse necessarie a far uscire tutte le pedine di ciacun giocatore
			totpc = 0, totec = 0,
			tots = 0, //numero di stack totale
			advantage = 0; //vantaggio rispetto all'avversario in termini di possibilit� di cattura (+1 se posso catturare, -1 se posso essere catturato)
		
		for (int i = 0; i < BOARDS; i++) {
			
			if (ps[i].cardinality() > 0) {
				tots += (maxs_stacks[i] - ps[i].cardinality());
				
				srcSquare = ps[i].nextSetBit(0);
				while(srcSquare != -1) {
					
					quantity = BasicGameEngine.exitDistances[playerOrdinal][srcSquare];
					totpm += (i+1) * quantity;
					
					for(Direction dir: Direction.values())
						if( dir.ordinal()!=notAlloweds[playerOrdinal][0] &&
							dir.ordinal()!=notAlloweds[playerOrdinal][1] && 
							dir.ordinal()!=notAlloweds[playerOrdinal][2]) 
							totpc += BasicGameEngine.borderDistances[dir.ordinal()][srcSquare];							
					
					
					for (int j=0; j<BOARDS; j++) {
						square = es[j].nextSetBit(0);
						while(square != -1) {
							int distance = BasicGameEngine.distances[srcSquare][square];
							if(distance != -1) {
								int diffPedine = i-j;
								if( diffPedine < 0 && j+1 >= distance && i+1<=distance )
									advantage--;
								else if(diffPedine > 0 && i+1 >= distance && j+1<=distance)
									advantage++;
							}
							square = es[j].nextSetBit(square+1);
						}	
					}
					
					srcSquare = ps[i].nextSetBit(srcSquare+1);
				}
				
				totpp += ps[i].cardinality() * (i + 1);
			}//ps[i].cardinality() > 0
			
			if (es[i].cardinality() > 0){
				
				int nextpos = es[i].nextSetBit(0);
				while(nextpos != -1) {
					
					quantity = BasicGameEngine.exitDistances[enemyOrdinal][nextpos];
					totem += (i+1) * quantity;
					
					for(Direction dir: Direction.values())
						if (dir.ordinal()!=notAlloweds[enemyOrdinal][0] &&
							dir.ordinal()!=notAlloweds[enemyOrdinal][1] && 
							dir.ordinal()!=notAlloweds[enemyOrdinal][2]) 
						totec += BasicGameEngine.borderDistances[dir.ordinal()][nextpos];
					
					nextpos = es[i].nextSetBit(nextpos+1);
				}
				
				totep += es[i].cardinality() * (i + 1);
			}
		}

		if (totpp == 0) return Double.NEGATIVE_INFINITY;
		return (totpm - totem) + advantage + (totpp - totep) + tots + ((totpc - totec)*0.4) + ((Math.random() * (max - min) ) + min);
	}
}
