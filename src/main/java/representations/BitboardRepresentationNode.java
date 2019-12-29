package representations;

import util.General;

import java.util.BitSet;
import java.util.List;
import java.util.Objects;

public class BitboardRepresentationNode implements RepresentationNode {

	
	public BitSet[][] playersPieces;

	private Moves moveType;
	
	private String encodedMove;
	
	private double heuristicValue;

	public BitboardRepresentationNode() { this.playersPieces = new BitSet[2][]; }
	
	public void setPlayerPieces(Color color, BitSet[] playerPieces) { this.playersPieces[color.ordinal()] = playerPieces; }
	
	public BitSet[] getPlayerPieces(Color color) { return this.playersPieces[color.ordinal()]; }
		
	public Moves getMoveType() { return moveType; }

	public void setMoveType(Moves moveType) { this.moveType = moveType; }

	@Override
	public String getMove() {
		return encodedMove;
	}

	@Override
	public void setMove(String m) {
		this.encodedMove = m;
	}

	@Override
	public void setHeuristicValue(double h) {
		this.heuristicValue = h;
	}

	@Override
	public double getHeuristicValue() {
		return heuristicValue;
	}

	public boolean steadyState(RepresentationNode boardState) {

		List<RepresentationNode> wbCaptureMoves = General.gameEngine.getWBCaptureMoves(boardState);

		return wbCaptureMoves.isEmpty();
	}



	public int hashCode() {

		return Objects.hash(this.playersPieces[0][0], this.playersPieces[0][1],
							this.playersPieces[0][2], this.playersPieces[0][3],
							this.playersPieces[0][4], this.playersPieces[0][5],
							this.playersPieces[0][6], this.playersPieces[0][7],
							this.playersPieces[0][8], this.playersPieces[0][9],
							this.playersPieces[0][10], this.playersPieces[0][11],
							this.playersPieces[1][0], this.playersPieces[1][1],
							this.playersPieces[1][2], this.playersPieces[1][3],
							this.playersPieces[1][4], this.playersPieces[1][5],
							this.playersPieces[1][6], this.playersPieces[1][7],
							this.playersPieces[1][8], this.playersPieces[1][9],
							this.playersPieces[1][10], this.playersPieces[1][11]);
	}
}
