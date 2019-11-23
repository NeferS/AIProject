package representations;

import java.util.BitSet;

public class BoardStateBuilder {
	
	
	//Date le bitboard che individuano i pezzi del giocatore, quelli dell'avversario, le posizioni occupate dal giocatore,
	//il numero n + 1 di pedine posizionate sulla casella srcSquare e la casella di destinazione, il metodo costruisce un
	//nuovo RepresentationNode che rappresenta il nuovo stato della scacchiera dopo aver effettuato la mossa individuata.
	public static BitboardRepresentationNode calculateNonCaptureMove(BitSet[] playerPieces, 
															   BitSet[] enemyPieces, 
															   BitSet playerOccupiedSquares,
															   int playerStackSize, 
															   int srcSquare, 
															   int dstSquare,
															   String encodedSrcSquare,
															   String direction,
															   int distance,
															   Color playerColor,
															   Color enemyColor) {
		
		
		BitboardRepresentationNode validMove = new BitboardRepresentationNode();
		BitSet[] newPlayerPieces = playerPieces.clone();
		

		
		String encodedMove = encodedSrcSquare
							 .concat(",")
							 .concat(direction)
							 .concat(",")
							 .concat(Integer.toString(distance));
		
		
		//eventuali k - 1 pedine rimanenti su srcSquare
		int k = playerStackSize - distance;
		//m + 1 pedine che si spostano da srcSquare a dstSquare
		int m = distance - 1;
		//eventuali o + 1 pedine dello stesso colore sulla casella di destinazione
		int o = -1;
		

		
		//Sicuramente non ci sono più playerStackSize + 1 pedine su srcSquare, per cui
		//aggiorno la bitboard che rappresenta le posizioni degli stack
		//di n + 1 pedine settando a 0 il bit srcSquare. 
		newPlayerPieces[playerStackSize] = (BitSet) newPlayerPieces[playerStackSize].clone();
		newPlayerPieces[playerStackSize].flip(srcSquare);
		//Se su srcSquare rimangono k + 1 pedine dopo lo spostamento,
		//aggiorno la bitboard che rappresenta le posizioni degli
		//stack di k pedine settando a 1 il bit srcSquare.
		if(k >= 0) {
			newPlayerPieces[k] = (BitSet) newPlayerPieces[k].clone();
			newPlayerPieces[k].flip(srcSquare);
		}
		
		
		//Se la casella di destinazione (dstSquare) contiene già pedine 
		if(playerOccupiedSquares.get(dstSquare)) {
			for(int i = 0; i < 12; i++) {
				if(newPlayerPieces[i].get(dstSquare)) {
					//o + 1 pedine dello stesso colore sulla casella di destinazione
					o = i;
					break;
				}
			}
		}
		
		//Se la casella di destinazione non contiene pedine, aggiorno
		//la bitboard che rappresenta le posizioni degli stack con m + 1
		//pedine settando a 1 il bit dstSquare
		if(o < 0) {
			newPlayerPieces[m] = (BitSet) newPlayerPieces[m].clone();
			newPlayerPieces[m].flip(dstSquare);
		}
		//Se la casella di destinazione contiene pedine, aggiorno
		//la bitboard che rappresenta le posizioni degli stack con 
		//o + distance + 1 pedine settando a 1 il bit dstSquare
		else {
			if(o + distance > 11) {
				System.out.println(srcSquare);
				System.out.println(dstSquare);
				System.out.println(direction);
				System.out.println(distance);
				System.out.println("o: "+o);
			}
			//Fixed
			newPlayerPieces[o] = (BitSet) newPlayerPieces[o].clone();
			newPlayerPieces[o].flip(dstSquare);
			//Fixed
			newPlayerPieces[o + distance] = (BitSet) newPlayerPieces[o + distance].clone();
			newPlayerPieces[o + distance].flip(dstSquare);
		}
		
		validMove.setPlayerPieces(playerColor, newPlayerPieces);
		validMove.setPlayerPieces(enemyColor, enemyPieces);
		validMove.setMove(encodedMove);
		
		return validMove;
	}
	
	public static BitboardRepresentationNode calculateCaptureMove(BitSet[] playerPieces, 
																  BitSet[] enemyPieces, 
																  int playerStackSize,
																  int enemyStackSize,
																  int srcSquare, 
																  int dstSquare,
																  String encodedSrcSquare,
																  String direction,
																  int distance,
																  Color playerColor,
																  Color enemyColor) {


		BitboardRepresentationNode validMove = new BitboardRepresentationNode();
		BitSet[] newPlayerPieces = playerPieces.clone();
		BitSet[] newEnemyPieces = enemyPieces.clone();
		
		//Sono indeciso se spostare queste variabili nei parametri del metodo
		//String encodedSrcSquare = this.encodedSquares[srcSquare];
		//String direction = this.exitMovesDirections[this.playerColor.ordinal()][srcSquare];
		//int distance = this.distanceMatrix[srcSquare][dstSquare];
		
		String encodedMove = encodedSrcSquare
		.concat(",")
		.concat(direction)
		.concat(",")
		.concat(Integer.toString(distance));
		
		
		//eventuali remainingStackSize + 1 pedine rimanenti su srcSquare
		int remainingStackSize = playerStackSize - distance;
		//movedStackSize + 1 pedine che si spostano da srcSquare a dstSquare
		int movedStackSize = distance - 1;
		
		
		
		newPlayerPieces[playerStackSize] = (BitSet) newPlayerPieces[playerStackSize].clone();
		newPlayerPieces[playerStackSize].flip(srcSquare);
		if(remainingStackSize >= 0) {
			newPlayerPieces[remainingStackSize] = (BitSet) newPlayerPieces[remainingStackSize].clone();
			newPlayerPieces[remainingStackSize].flip(srcSquare);
		}
		newPlayerPieces[movedStackSize] = (BitSet) newPlayerPieces[movedStackSize].clone();
		newPlayerPieces[movedStackSize].flip(dstSquare);
		
		
		//Disintegro le pedine catturate
		newEnemyPieces[enemyStackSize] = (BitSet) newEnemyPieces[enemyStackSize].clone();
		newEnemyPieces[enemyStackSize].flip(dstSquare);
		
		validMove.setPlayerPieces(playerColor, newPlayerPieces);
		validMove.setPlayerPieces(enemyColor, newEnemyPieces);
		validMove.setMove(encodedMove);
		
		return validMove;

	}
	
	public static BitboardRepresentationNode calculateExitMove(BitSet[] playerPieces, 
				 											   BitSet[] enemyPieces, 
				 											   int stackSize,
				 											   int srcSquare,
				 											   String encodedSrcSquare,
				 											   String direction,
				 											   int distance,
				 											   Color playerColor,
				 											   Color enemyColor) {


		BitboardRepresentationNode validMove = new BitboardRepresentationNode();
		BitSet[] newPlayerPieces = playerPieces.clone();
		
		
		
		String encodedMove = encodedSrcSquare
		.concat(",")
		.concat(direction)
		.concat(",")
		.concat(Integer.toString(distance));
		
		newPlayerPieces[stackSize] = (BitSet)newPlayerPieces[stackSize].clone();
		newPlayerPieces[stackSize].flip(srcSquare);
		
		int remainingStackSize = stackSize - distance;
		if(remainingStackSize >= 0) {
			newPlayerPieces[remainingStackSize] = (BitSet)newPlayerPieces[remainingStackSize].clone();
			newPlayerPieces[remainingStackSize].flip(srcSquare); 
		}
		
		validMove.setPlayerPieces(playerColor, newPlayerPieces);
		validMove.setPlayerPieces(enemyColor, enemyPieces);
		validMove.setMove(encodedMove);
		
		return validMove;

	}

}
