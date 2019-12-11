package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import communication.Protocol;
import player.Player;

@SuppressWarnings("unused")
public class TestServer {
	
	private ServerSocket ss;
	private Socket client;
	private PrintWriter pw;
	private Scanner sc;
	
	public TestServer() throws IOException {
		ss = new ServerSocket(1099);
		System.out.println("Waiting...");
		client = ss.accept();
		System.out.println("Accepted");
		pw = new PrintWriter(client.getOutputStream());
		sc = new Scanner(client.getInputStream());
	}
	
	public String recv() { return sc.nextLine(); }
	
	public void send(String msg) { pw.println(msg); pw.flush(); }
	
	public static void main(String[] args) throws IOException, InterruptedException {
		TestServer ts = new TestServer();
		ts.send("WELCOME Black");
		for(int i=1; i<4; i++) {
			System.out.println(i);
			Thread.sleep(1000);
		}
		ts.send("OPPONENT_MOVE F5,N,2");
		ts.send("YOUR_TURN");
		long t = System.currentTimeMillis();
		System.out.println(ts.recv());
		t = System.currentTimeMillis() - t;
		System.out.println("t: "+t);
		/*
		int[] a = new int[12];
		for(int i=0; i<12; i++)
			a[i] = 1<<i;
		a[8] = 4194304;
		a[9] = 4194304;
		String pos = "F5,N,2";
		long t = System.currentTimeMillis();
		String[] info = pos.split(","); //va fatto sempre e comunque
		byte c = Byte.parseByte(info[0].charAt(1)+""); //va da 1 a 8, non serve un int
		int start = 1 << ((((int)info[0].charAt(0))-65)*4 + c/2 + c%2) - 1; //calcolo dello start con bitboard
		//byte j = ((int)info[0].charAt(0))-65), i = (j%2==0)? c/2-1 : c/2;  //calcolo dello start con array di array 
																			 //e scacchiera piegata di fianco
		byte spost = Byte.parseByte(info[2].charAt(0)+""); //va da 1 a 8 (quando 8 pedine in un angolo mi muovono in diagonale
														   //fuori dalla scacchiera), non serve un int
		
		switch(info[1].charAt(0)) {
		case 'N': 
			if(info[1].length() == 1) {
				for(int i=0; i<a.length; i++) {
					if(spost == 0) break;
					if((a[i]^start)==0) {
						a[i] = a[i]>>Integer.parseInt(info[2].charAt(0)+"")*4;
						spost--;
					}
				}
			}
			else { }
			break;
		}
		t = System.currentTimeMillis() - t;
		System.out.println(t);
		System.out.println(Arrays.toString(a));
		*/
	}
}
