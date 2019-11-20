package representations;

import java.util.BitSet;

public class BitboardRepresentationNode implements RepresentationNode{

	
	private BitSet[][] playersPieces;
	
	private String encodedMove;
	
	private double heuristicValue;
	
	
	public BitboardRepresentationNode() { this.playersPieces = new BitSet[2][]; }
	
	public void setPlayerPieces(Color color, BitSet[] playerPieces) { this.playersPieces[color.ordinal()] = playerPieces; }
	
	public BitSet[] getPlayerPieces(Color color) { return this.playersPieces[color.ordinal()]; }
		
	
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

}
