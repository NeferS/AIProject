package controller;

import controller.Colour;

public class Cell {
    private Colour colour;
    private int pieces = 0;
    static Cell out_cell = new Cell(null);

    Cell(Colour colour) {
        this.colour = colour;
    }

    Cell(Colour colour, int pieces) {
        this.colour = colour;
        this.pieces = pieces;
    }

    public Colour getColour() {
        return this.colour;
    }

    void set(Colour colour) {
        this.colour = colour;
    }

    public int getPieces() {
        return this.pieces;
    }

    void set(int pieces) {
        this.pieces = pieces;
    }

    boolean is_out() {
        return this.equals(out_cell);
    }

    public void update_cell(Colour col, int p) {
        this.colour = col;
        this.pieces += p;
    }

    public String toString() {
        if (this.colour == null) {
            return " --- ";
        }
        return this.pieces > 9 ? " " + this.pieces + String.valueOf((Object)this.colour).charAt(0) + " " : " 0" + this.pieces + String.valueOf((Object)this.colour).charAt(0) + " ";
    }

    public boolean equals(Object cell) {
        if (this == cell) {
            return true;
        }
        if (cell == null) {
            return false;
        }
        if (this.getClass() != cell.getClass()) {
            return false;
        }
        Cell other = (Cell)cell;
        if (this.colour != other.colour) {
            return false;
        }
        return this.pieces == other.pieces;
    }
}

