package representations;

public enum Direction {
	
	N, S, W, E, NW, NE, SW, SE;
	
	
	public static Direction getDirectionByInt(int i) {
		
		switch(i) {
			case 0 : return N;
			case 1 : return S;
			case 2 : return W;
			case 3 : return E;
			case 4 : return NW;
			case 5 : return NE;
			case 6 : return SW;
			case 7 : return SE;
			
		}
		
		return null;
	}
	
}
