package test;

import java.io.IOException;
import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.Color;
import representations.RepresentationNode;
import searching.RandomizedMMAB;
import strategies.MyHeuristic;
import util.General;

public class TestServer {
	
	public static void main(String[] args) throws IOException {
		BitSet[] whitePieces = new BitSet[12];
		BitSet[] blackPieces = new BitSet[12];
		
		for(int i = 0; i < 11; i++) {
			whitePieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
			blackPieces[i] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		}
		
		whitePieces[11] = BitSet.valueOf(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x40});
		blackPieces[11] = BitSet.valueOf(new byte[] { (byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00});
		
		BitboardRepresentationNode node = new BitboardRepresentationNode();
		node.setPlayerPieces(Color.WHITE, whitePieces);
		node.setPlayerPieces(Color.BLACK, blackPieces);
		
		General.gameEngine = new BasicGameEngine();
		General.gameEngine.start(Color.WHITE);
		RandomizedMMAB rmmab = new RandomizedMMAB();
		rmmab.initStrategy(new MyHeuristic(Color.WHITE));
		RepresentationNode node0 = rmmab.explore(General.gameEngine.getCurrentBoardState(), System.currentTimeMillis());
		System.out.println(node0.getMove());
	}
}
