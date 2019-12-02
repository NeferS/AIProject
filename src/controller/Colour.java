package controller;

public enum Colour {
    Black,
    White;
    

    public static Colour getOther(Colour col) {
    	if (col == White) {
            return Black;
        }
        return White;
    }
}

