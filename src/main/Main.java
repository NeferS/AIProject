package main;

import java.io.IOException;
import java.util.LinkedList;

import communication.Protocol;
import player.Player;
import representations.RepresentationNode;
import strategies.SearchAlgorithm;

public class Main {
	public static void main(String[] args) throws IOException {
		Protocol p = new Protocol("localhost", 1099);
		Player pl = new Player(p, new SearchAlgorithm() {
			@Override
			protected String iterate(LinkedList<RepresentationNode> queue, Thread caller) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void updateDataStructure(Object... params) {
				// TODO Auto-generated method stub
				
			}
		});
		pl.start();
	}
}
