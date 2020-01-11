package representations;

import java.util.BitSet;

public class BitboardRepresentationNode implements RepresentationNode{

	
	public BitSet[][] playersPieces;
	
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
	
	public String toString() {
		if(playersPieces[0] == null || playersPieces[1] == null) return "";
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<8; i++) {
			if(i%2 == 0)
				sb.append("---  ");
			for(int j=0; j<4; j++) {
				boolean found = false;
				for(int k=0; k<12; k++) 
					if(playersPieces[0][k].nextSetBit((i*4)+j) != -1 && playersPieces[0][k].nextSetBit((i*4)+j) == (i*4)+j) {
						String firstChar = ((k+1)>9)? "" : "0";
						sb.append(firstChar+(k+1)+"W  ");
						found = true;
						break;
					}
					else if(playersPieces[1][k].nextSetBit((i*4)+j) != -1 && playersPieces[1][k].nextSetBit((i*4)+j) == (i*4)+j) {
						String firstChar = ((k+1)>9)? "" : "0";
						sb.append(firstChar+(k+1)+"B  ");
						found = true;
						break;
					}
				if(!found)
					sb.append("---  ");
				if(j != 3)
					sb.append("---  ");
			}
			if(i%2 != 0)
				sb.append("---  ");
			sb.append("\n");
		}
		sb.delete(sb.length()-3, sb.length());
		return sb.toString();
	}
}
