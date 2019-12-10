package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import model.BoardDataStructure;
import model.Point;
import representations.Color;
import util.ProcessorMove;
import view.Game;


public class HumanClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Color color;
	private String colour = null;
	private String move;
	private String opponentMove;
	
	public HumanClient(String serverAddress, int port) throws Exception {
		this.socket = new Socket(serverAddress, port);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
	}
	
	public void playGUI(Game g, BoardDataStructure board) throws Exception {
		g.showGUI();
		try {
			String response = this.in.readLine();
			if (response.startsWith("WELCOME")) {
				this.colour = response.substring(8);
				if (colour.toUpperCase().equals("BLACK")){
					color = Color.BLACK;
					g.setColor(Color.BLACK);
				}else {
					color = Color.WHITE;
					g.setColor(Color.WHITE);
				}
				g.publish_server(this.colour);
			}
			do {
				if ((response = this.in.readLine()).startsWith("VALID_MOVE")) {
					g.publish_server("Valid move, please wait");
					/*
					 * Operazione di aggiornamento della scacchiera, sono sicuro che la mossa che inviato è valida
					 * poichè il server ha fatto il check
					 */
					
					Point [] points = ProcessorMove.calculateMove(move);
					int numPiece = ProcessorMove.calculateNumPos(move);
					board.remove(points[0].x, points[0].y, this.color,numPiece);
					/*
					 * Se l'operazione di add ritorna l'eccezione che viene generata nella classe, allora non devo aggiungere
					 * nulla. La add alla board genera eccezione quando si vogliono aggiungere pezzi in una cella che non fa parte della
					 * scacchiera. Questo si verifica quando vengono rimosse pedine dalla scacchiera
					 */
					try{
						if (board.get(points[1].x, points[1].y).getColour() != this.color) {//vuol dire che nella cella destinazine ci sono pedine avversarie
							//le posso rimuovere tranquillamente perchè ho già fatto il check
							board.get(points[1].x, points[1].y).remove_all();
						}
						board.add(points[1].x, points[1].y, this.color,numPiece);	
					}catch (Exception e) {}
					g.refreshBoard(board);
					continue;
				}
				if (response.startsWith("OPPONENT_MOVE")) {
					g.publish_server("[" + System.currentTimeMillis() + "] ");
					opponentMove = response.substring(14);
					g.publish_server("Opponent move: " + opponentMove);
					
					Point [] points = ProcessorMove.calculateMove(opponentMove);
					int numPiece = ProcessorMove.calculateNumPos(opponentMove);
					
					board.remove(points[0].x, points[0].y, Color.otherColor(color),numPiece);
					/*
					 * Se l'operazione di add ritorna l'eccezione che viene generata nella classe, allora non devo aggiungere
					 * nulla. La add alla board genera eccezione quando si vogliono aggiungere pezzi in una cella che non fa parte della
					 * scacchiera. Questo si verifica quando vengono rimosse pedine dalla scacchiera
					 */
					try{
						if (board.get(points[1].x, points[1].y).getColour() == this.color) {//vuol dire che nella cella destinazine ci sono pedine avversarie
							//le posso rimuovere tranquillamente perchè ho già fatto il check
							board.get(points[1].x, points[1].y).remove_all();
						}
						board.add(points[1].x, points[1].y, Color.otherColor(color),numPiece);	
					}catch (Exception e) {}
					g.refreshBoard(board);
					continue;								
					
				}
				if (response.startsWith("VICTORY")) {
					g.publish_server("You win");
					break;
				}
				if (response.startsWith("DEFEAT")) {
					g.publish_server("You lose");
					break;
				}
				if (response.startsWith("TIE")) {
					g.publish_server("You tied");
					break;
				}
				if (response.startsWith("YOUR_TURN")) {
					g.publish_server("[" + System.currentTimeMillis() + "] ");
					g.publish_server("Your move: ");
					continue;
				}
				if (response.startsWith("TIMEOUT")) {
					g.publish_server("Time out");
					continue;
				}
				if (!response.startsWith("MESSAGE")) continue;
				g.publish_server("[" + System.currentTimeMillis() + "] ");
				g.publish_server(response.substring(8));
			} while (true);
		}
		finally {
			this.socket.close();
		}
	}

	public void sendMove(String move) {
		this.move = move;
		System.out.println(move);
		this.out.println("MOVE " + this.move);
	}
	
	public void play() throws Exception {
		Scanner sc = new Scanner(System.in);
		try {
			String response = this.in.readLine();
			if (response.startsWith("WELCOME")) {
				this.colour = response.substring(8);
				System.out.println(this.colour);
			}
			do {
				if ((response = this.in.readLine()).startsWith("VALID_MOVE")) {
					System.out.println("Valid move, please wait");
					continue;
				}
				if (response.startsWith("OPPONENT_MOVE")) {
					System.out.print("[" + System.currentTimeMillis() + "] ");
					System.out.println("Opponent move: " + response.substring(14));
					continue;
				}
				if (response.startsWith("VICTORY")) {
					System.out.println("You win");
					break;
				}
				if (response.startsWith("DEFEAT")) {
					System.out.println("You lose");
					break;
				}
				if (response.startsWith("TIE")) {
					System.out.println("You tied");
					break;
				}
				if (response.startsWith("YOUR_TURN")) {
					System.out.print("[" + System.currentTimeMillis() + "] ");
					System.out.println("Your move: ");
					this.move = sc.next();
					this.out.println("MOVE " + this.move);
					continue;
				}
				if (response.startsWith("TIMEOUT")) {
					System.out.println("Time out");
					continue;
				}
				if (!response.startsWith("MESSAGE")) continue;
				System.out.print("[" + System.currentTimeMillis() + "] ");
				System.out.println(response.substring(8));
			} while (true);
		}
		finally {
			sc.close();
			this.socket.close();
		}
	}
}
