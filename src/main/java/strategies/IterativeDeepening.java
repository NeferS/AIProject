package strategies;

import representations.RepresentationNode;

public class IterativeDeepening implements SearchAlgorithm {

    private UpgradedRMMAB[] searchAlgorithms;
    private IHeuristic heuristic;
    private int[] depths;
    private int nIterations;

    public IterativeDeepening(int[] depths, IHeuristic heuristic) {

        heuristic = new FirstHeuristic();
        this.nIterations = depths.length;

        searchAlgorithms = new UpgradedRMMAB[this.nIterations];
        for(int i = 0; i < this.nIterations; i++) searchAlgorithms[i] = new UpgradedRMMAB(depths[i], heuristic);
    }

    @Override
    public RepresentationNode getBestMove(RepresentationNode currentBoardState, long t) {

        RepresentationNode bestMove = null;
        RepresentationNode tmpBestMove = null;

        int nCompletedIterations = 0;

        for(int i = 0; i < this.nIterations; i++) {
            tmpBestMove = searchAlgorithms[i].getBestMove(currentBoardState, t);
            if(!searchAlgorithms[i].interrupted()) {
                bestMove = tmpBestMove;
                nCompletedIterations++;
            }
            else {
                break;
            }
        }

        completedIterations(nCompletedIterations);

        return bestMove;

    }

    private void completedIterations(int i) {}
}
