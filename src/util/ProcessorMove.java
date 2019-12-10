package util;

import model.Point;

public class ProcessorMove {
	
	/*
	 * ritorna la cella con gli indici della damiera, passando le coordinate della matrice come punto
	 */
	public static  String calculateCell(Point p) {
		return ""+ ((char) (65 + p.x)) + ""+ (p.y+1);
	}
	
	public static int calculateNumPos(String move) {
		String [] array = move.split(",");
		return Integer.parseInt(array[2]);
	}

	/*
	 * Dati due punti start e destination ritorna la direzione e il numero di celle che li separa
	 */
	public static String calculateDirection(Point start, Point destination) throws Exception{
		String ret = "";
		int differenceX = Math.abs(start.x - destination.x);
		int differenceY = Math.abs(start.y - destination.y);
		if(start.x > destination.x && start.y == destination.y) {
			ret = "N,"+differenceX;
		}
		if(start.x > destination.x && start.y < destination.y) {
			if (differenceX != differenceY) throw new Exception("Mossa non valida");
			ret = "NE,"+differenceX;
		}
		if(start.x == destination.x && start.y < destination.y) {
			ret = "E,"+differenceY;
		}
		if(start.x < destination.x && start.y < destination.y) {
			if (differenceX != differenceY) throw new Exception("Mossa non valida");
			ret = "SE,"+differenceX;
		}
		if(start.x < destination.x && start.y == destination.y) {
			ret = "S,"+differenceX;
		}
		if(start.x < destination.x && start.y > destination.y) {
			if (differenceX != differenceY) throw new Exception("Mossa non valida");
			ret = "SW,"+differenceX;
		}
		if(start.x == destination.x && start.y > destination.y) {
			ret = "W,"+differenceY;
		}
		if(start.x > destination.x && start.y > destination.y) {
			if (differenceX != differenceY) throw new Exception("Mossa non valida");
			ret = "NW,"+differenceX;
		}
		return ret;
	}

	/*
	 * Data una stringa contenente la mossa del tipo "A4,S,2" ritorna i due punti con le coordinate interessate
	 */
	public static Point[] calculateMove(String move) {
		String [] array = move.split(",");
		int x = (int) (array[0].charAt(0));
		int y = Character.getNumericValue(array[0].charAt(1));
		Point p1 = new Point(x-65, y-1);
		Point p2 = new Point();
		int numPos = Integer.parseInt(array[2]);
		switch(array[1]) {
		  case "N":
			  p2.x=p1.x-numPos;
			  p2.y=p1.y;
			  break;
		  case "NE":
			  p2.x=p1.x-numPos;
			  p2.y=p1.y+numPos;
			  break;
		  case "E":
			  p2.x=p1.x;
			  p2.y=p1.y+numPos;
			  break;
		  case "SE":
			  p2.x=p1.x+numPos;
			  p2.y=p1.y+numPos;
			  break;
		  case "S":
			  p2.x=p1.x+numPos;
			  p2.y=p1.y;
			  break;
		  case "SW":
			  p2.x=p1.x+numPos;
			  p2.y=p1.y-numPos;
			  break;
		  case "W":
			  p2.x=p1.x;
			  p2.y=p1.y-numPos;
			  break;
		  case "NW":
			  p2.x=p1.x-numPos;
			  p2.y=p1.y-numPos;
			  break;
		}
		Point [] ret = {p1,p2};
		return ret;
	}
	
}
