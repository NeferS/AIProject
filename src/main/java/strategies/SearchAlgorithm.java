package strategies;

import representations.RepresentationNode;

public interface SearchAlgorithm {

    public RepresentationNode getBestMove(RepresentationNode currentBoardState, long t);

}
