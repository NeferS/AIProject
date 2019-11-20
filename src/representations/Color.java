package representations;

public enum Color {
	WHITE, BLACK;
	
	
	public static Color otherColor(Color color) {
		
		Color otherColor = null;
		if(color == Color.WHITE) otherColor = Color.BLACK;
		else if(color == Color.BLACK) otherColor = Color.WHITE;
		
		return otherColor;
	}
}
