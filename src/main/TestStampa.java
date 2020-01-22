package main;

import java.util.BitSet;

import representations.BasicGameEngine;
import representations.BitboardRepresentationNode;
import representations.Color;
import representations.RepresentationNode;
import searching.MinMaxAlphaBeta;
import searching.SearchAlgorithm;
import searching.testMax;
import strategies.H6;
import strategies.H4;
import util.General;

public class TestStampa {
	
	static BitSet n1 = new BitSet(32);
	static BitSet n2 = new BitSet(32);
	static BitSet n3 = new BitSet(32);
	static BitSet n4 = new BitSet(32);
	static BitSet n5 = new BitSet(32);
	static BitSet n6 = new BitSet(32);
	static BitSet n7 = new BitSet(32);
	static BitSet n8 = new BitSet(32);
	static BitSet n9 = new BitSet(32);
	static BitSet n10 = new BitSet(32);
	static BitSet n11 = new BitSet(32);
	static BitSet n12 = new BitSet(32);
	
	static BitSet b1 = new BitSet(32);
	static BitSet b2 = new BitSet(32);
	static BitSet b3 = new BitSet(32);
	static BitSet b4 = new BitSet(32);
	static BitSet b5 = new BitSet(32);
	static BitSet b6 = new BitSet(32);
	static BitSet b7 = new BitSet(32);
	static BitSet b8 = new BitSet(32);
	static BitSet b9 = new BitSet(32);
	static BitSet b10 = new BitSet(32);
	static BitSet b11 = new BitSet(32);
	static BitSet b12 = new BitSet(32);

	public static void main (String...arg) {
		initBitSet();
		BitSet[] player= {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12}; 
		BitSet[] enemy= {n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12}; 
		BitboardRepresentationNode node = new BitboardRepresentationNode();
		node.setPlayerPieces(Color.WHITE, player);
		node.setPlayerPieces(Color.BLACK, enemy);
		System.out.println(node);
		
		BasicGameEngine bge = new BasicGameEngine();
		bge.start(Color.WHITE);
		bge.playerMakeMove(node);
		General.gameEngine = bge;
		
		//for(RepresentationNode n: bge.validActions(node, Color.WHITE, (byte)38))
		//	System.out.println(n.getMove());

		testMax mmab = new testMax();
		mmab.setLevel(20);
		mmab.explore(bge.getCurrentBoardState(), System.currentTimeMillis());
		System.out.println("H5,NW,1 : "+mmab.goals);
	}
	
	public static void initBitSet() {
		n12.set(1);
		b11.set(30);
		b1.set(25);
	}

}
