/*
 * Decompiled with CFR 0.145.
 */
package view;

import controller.Cell;
import controller.Colour;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import view.GUI;

class Piece
extends JPanel {
    private static final long serialVersionUID = -7640741678482143083L;
    static long pc_count = 0L;
    Color bgcolor;
    Color color;
    boolean is_heading = false;
    char[] text = new char[2];
    int size;
    int fontSize = 24;
    private static /* synthetic */ int[] $SWITCH_TABLE$controller$Colour;
    

    public Piece(Color bgcolor, int size, int i, int j, boolean is_heading) {
        this.bgcolor = bgcolor;
        this.setBackground(bgcolor);
        this.color = bgcolor;
        this.size = size;
        this.setSize(size, size);
        this.setLocation(j * size, i * size);
        this.setBorder(new LineBorder(UIManager.getColor("Panel.background")));
        this.is_heading = is_heading;
    }

    public Piece(Color bgcolor, int size, int i, int j) {
        this(bgcolor, size, i, j, false);
    }

    public Piece(Color bgcolor, int size, int i, int j, char c) {
        this(bgcolor, size, i, j);
        this.text[0] = c;
    }

    public Piece(Color bgcolor, int size, int i, int j, String c) {
        this(bgcolor, size, i, j);
        this.text = c.toCharArray();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.size, this.size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diameter = this.size;
        if (this.is_heading) {
            Font f = g.getFont();
            f = new Font(f.getFontName(), 1, this.fontSize);
            g.setFont(f);
            g.setColor(new Color(0, 0, 153));
            g.drawChars(this.text, 0, this.text.length, (diameter - f.getSize()) / 2, (diameter + f.getSize()) / 2);
        } else {
            g.setColor(this.color);
            int circleSize = 2 * diameter / 3;
            int gapX = (this.size - circleSize) / 2;
            int gapY = (this.size - circleSize) / 2;
            g.fillOval(gapX, gapY, circleSize, circleSize);
            Font f = g.getFont();
            f = new Font(f.getFontName(), 1, this.fontSize - 4);
            g.setFont(f);
            if (this.color == Color.BLACK) {
                g.setColor(GUI.white_cells);
            } else {
                g.setColor(GUI.black_cells);
            }
            g.drawChars(this.text, 0, this.text.length, (diameter - f.getSize()) / 2, (diameter + f.getSize()) / 2);
        }
    }

    public void repaint(Cell cell) {
    	if (cell.getColour() != null) {

    		if (cell.getColour() == Colour.White) {
    			this.color = Color.WHITE;
    		}else {
    			this.color = Color.BLACK;}
    		/*	
        	if ($SWITCH_TABLE$controller$Colour()[cell.getColour().ordinal()]==2) {
        		this.color = Color.WHITE;
        	}else {
        		this.color = Color.BLACK;
        	}*/
    		if (cell.getPieces() > 9) {
    			this.text[0] = 49;
    			this.text[1] = ("" + (cell.getPieces() - 10)).charAt(0);
    		} else {
    			this.text[0] = 8239;
    			this.text[1] = ("" + cell.getPieces()).charAt(0);
    		}
    	} else {
    		this.color = this.bgcolor;
    	}
    	this.paintComponent(this.getGraphics());
    }

    static /* synthetic */ int[] $SWITCH_TABLE$controller$Colour() {
        if ($SWITCH_TABLE$controller$Colour != null) {
            int[] arrn = null;
            return arrn;
        }
        int[] arrn = new int[Colour.values().length];
        try {
            arrn[Colour.Black.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            arrn[Colour.White.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        $SWITCH_TABLE$controller$Colour = arrn;
        return $SWITCH_TABLE$controller$Colour;
    }
    
    public Point getPosizione () {
		Rectangle r = this.getBounds();
		Point O = r.getLocation();
		int row = O.y / r.height;
		int col = O.x / r.width;
		
		return new Point(row-1, col-1);
	}
    
    
}

