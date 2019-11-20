package strategies;

import representations.RepresentationNode;

public class IdioticHeuristic implements IHeuristic {

	private java.util.Random r = new java.util.Random();
	
	@Override
	public double h(RepresentationNode node) { return r.nextDouble(); }

}
