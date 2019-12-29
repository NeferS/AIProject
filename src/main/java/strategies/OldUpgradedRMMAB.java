package strategies;

import representations.*;
import util.General;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OldUpgradedRMMAB extends OldSearchAlgorithm {

    protected final double infinite = Double.MAX_VALUE, min_infinite = -Double.MAX_VALUE;
    protected int L;
    protected final long LIMIT = 900;

    public OldUpgradedRMMAB(int L) {
        this.L = L;
    }


    private void seeBestMoves(List<RepresentationNode> nextBoardStates) {}

    @Override
    public RepresentationNode explore(RepresentationNode node, long t) {
        LinkedList<RepresentationNode> actions = General.gameEngine.applyLegalMoves(node, Moves.ALL, true, null); //validActions deve essere ordinato per pruning efficiente
        RepresentationNode bestMove = actions.get(0); //validActions deve generare la mossa "vuota" se e solo se non sono possibili altre azioni

        double v = min_infinite;
        double alpha = v;
        List<RepresentationNode> equalVals = new LinkedList<RepresentationNode>();


        for(RepresentationNode child: actions) {
            double val = valoreMin(t, (byte)1, child, alpha, infinite);

            if(val > v) {
                equalVals.clear();
                v = val;
                bestMove = child;
                equalVals.add(child);
            } else if(Math.abs(val - v) < 1e-8)
                equalVals.add(child);


            if((System.currentTimeMillis() - t) >= LIMIT) break;
            alpha = (alpha > val)? alpha : val;
        }


        seeBestMoves(equalVals);

        bestMove = equalVals.get(new Random().nextInt(equalVals.size()));

        return bestMove;
    }

    protected double valoreMax(long t, byte depth, RepresentationNode boardState, double alpha, double beta) {

        double v = min_infinite;
        LinkedList<RepresentationNode> nextBoardStates = null;
        if((System.currentTimeMillis() - t) >= LIMIT) return beta;

        if(depth >= L) {

            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, true, null);
            if(nextBoardStates.isEmpty()) {
                return strategy.h(boardState);
            }
            else {
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, true, nextBoardStates);
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, true, nextBoardStates);
            }


             //return strategy.h(boardState);
        }
        else {
            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.ALL, true, null);
        }

        if(nextBoardStates.isEmpty())
            return strategy.h(boardState);

        for(RepresentationNode child: nextBoardStates) {
            double val = valoreMin(t, (byte)(depth+1), child, alpha, beta);
            v = (v > val)? v : val;
            if(v >= beta) return v;
            if((System.currentTimeMillis() - t) >= LIMIT) return beta;
            alpha = (alpha > v)? alpha : v;
        }
        return v;
    }





    protected double valoreMin(long t, byte depth, RepresentationNode boardState, double alpha, double beta) {

        double v = infinite;
        LinkedList<RepresentationNode> nextBoardStates = null;
        if((System.currentTimeMillis() - t) >= LIMIT) return alpha;

        if(depth >= L) {

            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, false, null);
            if(nextBoardStates.isEmpty()) {
                return strategy.h(boardState);
            }
            else {
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, false, nextBoardStates);
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, false, nextBoardStates);
            }


            //return strategy.h(boardState);
        }
        else {
            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.ALL, false, null);
        }

        if(nextBoardStates.isEmpty())
            return strategy.h(boardState);

        for(RepresentationNode child: nextBoardStates) {
            double val = valoreMax(t, (byte)(depth+1), child, alpha, beta);
            v = (v < val)? v : val;
            if(v <= alpha) return v;
            if((System.currentTimeMillis() - t) >= LIMIT) return v;//return alpha;
            beta = (beta < v)? beta : v;
        }
        return v;
    }



}
