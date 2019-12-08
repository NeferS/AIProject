package controller;

import controller.Cell;
import controller.Colour;
import java.io.PrintWriter;

public class BoardDataStructure {
    private Cell[][] board;

    public Cell[][] getInternalBoard() {
        return this.board;
    }

    public int num_rows() {
        return this.board.length;
    }

    public int num_cols() {
        return this.board[0].length;
    }

    private void createStructure(int nr, int nc) {
        this.board = new Cell[nr][nc];
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                this.board[i][j] = new Cell(null);
            }
        }
    }

    public BoardDataStructure(int nr, int nc) {
        this.createStructure(nr, nc);
    }

    public BoardDataStructure(String s, int nr, int nc) {
        this.createStructure(nr, nc);
        int k = 0;
        for (int i = 0; i < nr; ++i) {
            for (int j = 0; j < nc; ++j) {
                this.board[i][j] = s.charAt(k) == 'B' ? new Cell(Colour.Black) : (s.charAt(k) == 'W' ? new Cell(Colour.White) : new Cell(null));
                ++k;
            }
        }
    }

    public Cell get(int[] t) {
        return this.board[t[0]][t[1]];
    }

    public Cell get(int i, int j) {
        if (i < 0 || i > this.board.length - 1 || j < 0 || j > this.board[0].length - 1) {
            return Cell.out_cell;
        }
        return this.board[i][j];
    }

    public void set(int i, int j, Colour col) {
        this.board[i][j].set(col);
    }

    public void set(int[] t, Colour col) {
        this.board[t[0]][t[1]].set(col);
    }

    public void add(int i, int j, Colour col, int pieces) {
        if (this.board[i][j].getColour() != null && !this.board[i][j].getColour().equals((Object)col)) {
            throw new IllegalArgumentException("Adding operation not allowed. The cell contains " + (Object)((Object)this.board[i][j].getColour()) + " pieces!");
        }
        this.board[i][j].update_cell(col, pieces);
    }

    public void add(int[] t, Colour col, int pieces) {
        this.add(t[0], t[1], col, pieces);
    }

    public void remove(int i, int j, Colour col, int pieces) {
        if (this.board[i][j].getColour() != null && !this.board[i][j].getColour().equals((Object)col)) {
            throw new IllegalArgumentException("Removing operation not allowed. The cell contains " + (Object)((Object)this.board[i][j].getColour()) + " pieces!");
        }
        if (this.board[i][j].getPieces() == pieces) {
            this.board[i][j].update_cell(null, -pieces);
        } else {
            this.board[i][j].update_cell(this.board[i][j].getColour(), -pieces);
        }
    }

    public void remove(int[] t, Colour col, int pieces) {
        this.remove(t[0], t[1], col, pieces);
    }

    public void print(String s, PrintWriter log) {
        System.out.print(s);
        if (log != null) {
            log.print(s);
        }
    }

    public void println(String s, PrintWriter log) {
        System.out.println(s);
        if (log != null) {
            log.println(s);
        }
    }

    public void printBoard(PrintWriter log) {
        int j;
        String nextcol;
        int j2;
        int col = 1;
        int row = 65;
        this.println("Board:", log);
        this.print("  ", log);
        for (j2 = 0; j2 < this.board[0].length; ++j2) {
            nextcol = "" + (col + j2);
            if (nextcol.length() < 2) {
                this.print("   " + nextcol + "  ", log);
                continue;
            }
            this.print("   " + nextcol, log);
        }
        this.println("", log);
        this.print("  ", log);
        for (j2 = 0; j2 < this.board[0].length; ++j2) {
            this.print(" \u2500\u2500\u2500\u2500\u2500", log);
        }
        this.println("", log);
        for (int i = 0; i < this.board.length; ++i) {
            char nextrow = (char)(row + i);
            this.print(String.valueOf(nextrow) + "|", log);
            for (int j3 = 0; j3 < this.board[0].length; ++j3) {
                this.print(" " + this.board[i][j3].toString(), log);
            }
            this.println(" |" + nextrow, log);
        }
        this.print("  ", log);
        for (j = 0; j < this.board[0].length; ++j) {
            this.print(" \u2500\u2500\u2500\u2500\u2500", log);
        }
        this.println("", log);
        this.print("  ", log);
        for (j = 0; j < this.board[0].length; ++j) {
            nextcol = "" + (col + j);
            if (nextcol.length() < 2) {
                this.print("   " + nextcol + "  ", log);
                continue;
            }
            this.print("   " + nextcol, log);
        }
        this.println("", log);
    }

    public void printBoard() {
        this.printBoard(null);
    }

    public int getLength(int i) {
        return this.board[i].length;
    }

    public static void main(String[] args) {
        BoardDataStructure bds = new BoardDataStructure(8, 8);
        bds.printBoard();
    }
}

