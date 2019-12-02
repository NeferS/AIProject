package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import view.GUI;

public class HumanClient {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	String colour = null;
	String move;
	
	String cellStart = "";
	String direction = "";
	String numPiece = "";
	
	public HumanClient(String serverAddress, int port) throws Exception {
		this.socket = new Socket(serverAddress, port);
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
	}

	
	public void playGUI() {
		BoardDataStructure board = new BoardDataStructure(8, 8);
		GUI.showGUI(board);
		board.add(7,4,Colour.White, 12);
		board.add(0,3,Colour.Black, 12);
		GUI.refreshBoard(board);
		GUI.refreshBoard(board);
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

	public static void main(String[] args) throws Exception {
		String serverAddress = args.length == 0 ? "localhost" : args[0];
		int serverPort = args.length == 0 ? 8901 : Integer.valueOf(args[1]);
		HumanClient client = new HumanClient(serverAddress, serverPort);
		client.play();
	}

}
