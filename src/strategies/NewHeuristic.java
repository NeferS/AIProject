package strategies;

import java.util.BitSet;

import representations.BitboardRepresentationNode;
import representations.Color;
import representations.RepresentationNode;

public class NewHeuristic implements IHeuristic {

	private final int BOARDS = 12;
	private Color playerColor, enemyColor;
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


	public NewHeuristic(Color playerColor) {
		this.playerColor = playerColor;
		this.enemyColor = Color.otherColor(playerColor);
	}


	@Override
	public double h(RepresentationNode node) {
		BitSet[] player = ((BitboardRepresentationNode)node).playersPieces[playerColor.ordinal()];
		BitSet[] enemy = ((BitboardRepresentationNode)node).playersPieces[enemyColor.ordinal()];

		double [] pa = calculateAdvantage(player, playerColor);
		double [] aa = calculateAdvantage(enemy, enemyColor);
		
		double playerAdvantage = pa[0];
		double enemyAdvantage = aa[0];

		double diffPedine =  Math.abs(pa[1]-aa[1]);
		
		double res =  enemyAdvantage - playerAdvantage;
		return res-(diffPedine*0.8);
	}



	private double [] calculateAdvantage(BitSet[] player, Color playerColor) {
		double advantage = 0;
		int srcSquare = 0;
		double numPedine = 0;
		for(int i=0; i<BOARDS; i++) {
			srcSquare = player[i].nextSetBit(0);
			if(srcSquare != -1) {
				/*
				 * In srcSquare ho il valore della cella su cui si trova lo stack.
				 * la variabile (i+1), indica quante pedine ci sono nello stack
				 */
				advantage += (i+1)*CELLWEIGHT[playerColor.ordinal()][srcSquare]; 
				numPedine += i+1;
			}
		}
		double [] ret = {advantage, numPedine};
		return ret;
	}

}
