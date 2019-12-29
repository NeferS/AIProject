package representations;

public enum Color {
	WHITE, BLACK;
	
	
	public static Color otherColor(Color color) {
		if(color == Color.WHITE) return Color.BLACK;
		return Color.WHITE;
	}
}
