package strategies;

import representations.RepresentationNode;

public class IdioticHeuristic implements IHeuristic {

	private java.util.Random r = new java.util.Random();
	
	//private static double test = 900000000;
	
	//@Override
	//public double h(RepresentationNode node) { return r.nextDouble(); }

	
	public double h(RepresentationNode node) { 
		
		test = test - 0.1;
		
		return test;
	}
}
