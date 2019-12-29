package strategies;

import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.Color;
import representations.GameEngine;
import representations.RepresentationNode;
import util.General;

public class FirstHeuristic implements IHeuristic {

	
	private int[][] maxExitDistances = {
			{
				1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8
			},
			{
				8, 8, 8, 8, 7, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1
			}
	};
	

	@Override
	public double h(RepresentationNode node) {
		
		BitboardRepresentationNode boardState = (BitboardRepresentationNode)node;
	
		double playerMainAdvantage = calculatePlayerMainAdvantage(boardState, General.gameEngine.getPlayerColor());
		double enemyMainAdvantage = calculatePlayerMainAdvantage(boardState, General.gameEngine.getEnemyColor());
		
		
		double res = playerMainAdvantage - enemyMainAdvantage;

		
		return res;
	}
	
	
	private double calculatePlayerMainAdvantage(BitboardRepresentationNode boardState, Color playerColor) {
		
		BitSet[] playerPieces = boardState.getPlayerPieces(playerColor);
		double mainAdvantage = 0;
		
		for(int i = 0; i < 12; i++) {
			int srcSquare = 0;
			while(true) {
				srcSquare = playerPieces[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				
				mainAdvantage += (i + 1) * this.maxExitDistances[playerColor.ordinal()][srcSquare];
				srcSquare++;
			}
		}
		
		return mainAdvantage;
		
	}

}
