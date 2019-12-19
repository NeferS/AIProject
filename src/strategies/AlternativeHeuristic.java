package strategies;

import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.Color;
import representations.RepresentationNode;
import util.General;

public class AlternativeHeuristic implements IHeuristic {


	private final int BOARDS = 12;
	private Color playerColor, enemyColor;

	public AlternativeHeuristic() { }
	
	public AlternativeHeuristic(Color playerColor) {
		this.playerColor = playerColor;
		this.enemyColor = Color.otherColor(playerColor);
	}

	@Override
	public double h(RepresentationNode node) {
		BitSet[] player = ((BitboardRepresentationNode)node).playersPieces[playerColor.ordinal()];
		BitSet[] enemy = ((BitboardRepresentationNode)node).playersPieces[enemyColor.ordinal()];
		double advantage = 0;
		int srcSquare = 0;
		int square =0;
		int totpp = 0, totep = 0;
		for(int i=0; i<BOARDS; i++) {
			srcSquare = 0;
			while(true) {
				srcSquare = player[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				for (int j=i; j<BOARDS; j++) {
					square = 0;
					while(true){
						square = enemy[j].nextSetBit(square);
						if (square == -1) break;
						int distance = ((BasicGameEngine)General.gameEngine).distances[srcSquare][square];
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
			totpp += (player[i].cardinality()*(i+1));
			totep += (enemy[i].cardinality()*(i+1));
		}	
		if(totpp == 0) return Double.NEGATIVE_INFINITY;
		if(totep == 0) return Double.POSITIVE_INFINITY;
		return advantage + (totpp - totep);
	}

	@Override
	public void color(Color c) {
		this.playerColor = c;
		this.enemyColor = Color.otherColor(c);
	}
}
/*
 * 

	@Override
	public double h(RepresentationNode node) {
		BitSet[] player = ((BitboardRepresentationNode)node).playersPieces[playerColor.ordinal()];
		BitSet[] enemy = ((BitboardRepresentationNode)node).playersPieces[enemyColor.ordinal()];
		double advantage = 0;
		int srcSquare = 0;
		int square =0;
		int totpp = 0, totep = 0;
		for(int i=0; i<BOARDS; i++) {
			srcSquare = 0;
			while(true) {
				srcSquare = player[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				for (int j=i; j<BOARDS; j++) {//conviene settare j a 0?
					square = 0;
					while(true){
						square = enemy[j].nextSetBit(square);
						if (square == -1) break;
						int distance = BasicGameEngine.distances[srcSquare][square];
						if (distance != -1) {
							int diffPedine = i-j;
							if ( diffPedine < 0 && diffPedine == distance ) {
								advantage--;
							}else if (diffPedine > 0 && diffPedine == distance) {
								advantage++;
							}
						}
						square++;
					}	
				}
				srcSquare++;
			}
			totpp += player[i].cardinality();
			totep += enemy[i].cardinality();
		}	

		return advantage + (totpp - totep);
	}

}
 */