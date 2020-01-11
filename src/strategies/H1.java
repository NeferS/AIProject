package strategies;

import java.util.BitSet;

import representations.BitboardRepresentationNode;
import representations.Color;
import representations.RepresentationNode;
import util.General;

public class H1 implements IHeuristic {

	private final int BOARDS = 12;
	//0: BIANCHI
	//1: NERI
	private final double[][] CELLWEIGHT = {	{	0.1, 0.1, 0.1, 0.1, 0.1, 0.25, 0.25, 0.25, 0.4, 0.55, 0.55, 0.25, 0.25,
												0.55, 0.85, 0.4, 0.55, 0.7, 0.7, 0.4, 0.4, 0.7, 0.7, 0.55,
												0.7, 0.7, 0.7, 0.55, 0.55, 0.55, 0.55, 0.55
											},
											{	0.55, 0.55, 0.55, 0.55, 0.55,0.7, 0.7, 0.7, 0.55, 0.7, 0.7, 0.4, 0.4, 0.7,
												0.7, 0.55, 0.4, 0.85, 0.55, 0.25, 0.25, 0.55, 0.55, 0.4, 0.25, 0.25, 0.25,
												0.1, 0.1, 0.1, 0.1, 0.1
											}};


	@Override
	public double h(RepresentationNode node) {
		BitSet[] player = ((BitboardRepresentationNode)node).playersPieces[General.gameEngine.getPlayerColor().ordinal()];
		BitSet[] enemy = ((BitboardRepresentationNode)node).playersPieces[General.gameEngine.getEnemyColor().ordinal()];

		double [] pa = calculateAdvantage(player, General.gameEngine.getPlayerColor());
		double [] aa = calculateAdvantage(enemy, General.gameEngine.getEnemyColor());
		
		double playerAdvantage = pa[0];
		double enemyAdvantage = aa[0];

		double diffPedine =  pa[1]-aa[1];
		
		if (diffPedine >0) {
			playerAdvantage += (diffPedine);
		}else {
			enemyAdvantage += (diffPedine);
		}
		
		double res =  enemyAdvantage - playerAdvantage;
		return res;
	}

	/*
	 * ritorna in posizione zero il vantaggio del giocatore in relazione alla posizione e al numero di pedine
	 * ritorna in posizione uno la differenza di pedine tra i due giocatori
	 */
	private double [] calculateAdvantage(BitSet[] player, Color playerColor) {
		double advantage = 0;
		int srcSquare = 0;
		double numPedine = 0;
		for(int i=0; i<BOARDS; i++) {
			srcSquare = 0;
			while(true) {
				srcSquare = player[i].nextSetBit(srcSquare);
				if(srcSquare == -1) break;
				/*
				 * In srcSquare ho il valore della cella su cui si trova lo stack.
				 * la variabile (i+1), indica quante pedine ci sono nello stack
				 * */
				advantage += (i+1)*CELLWEIGHT[playerColor.ordinal()][srcSquare]; 
				numPedine += i+1;
				srcSquare++;
			}
		}
		double [] ret = {advantage, numPedine};
		return ret;
	}
}
