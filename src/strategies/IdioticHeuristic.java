package strategies;

import representations.Color;
import representations.RepresentationNode;

public class IdioticHeuristic implements IHeuristic {

	private java.util.Random r = new java.util.Random();
	
	public double h(RepresentationNode node) { return r.nextDouble(); }

	@Override
	public void color(Color c) {
		// TODO Auto-generated method stub
		
	}

	
}
