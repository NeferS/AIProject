package strategies;

import representations.Moves;
import representations.RepresentationNode;
import util.General;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UpgradedRMMAB implements SearchAlgorithm {

    private int targetDepth;
    private int extendedDepth;
    private IHeuristic h;
    private final long LIMIT = 700;
    private boolean interrupted;

    public UpgradedRMMAB(int targetDepth, IHeuristic h) {
        this.targetDepth = targetDepth;
        this.extendedDepth = Integer.MAX_VALUE;
        this.h = h;
        this.interrupted = false;
    }

    @Override
    public RepresentationNode getBestMove(RepresentationNode node, long t) {

        LinkedList<RepresentationNode> actions = General.gameEngine.applyLegalMoves(node, Moves.ALL, true, null);
        RepresentationNode bestMove = null;

        double v = -Double.MAX_VALUE;
        double alpha = v;
        List<RepresentationNode> equalVals = new LinkedList<RepresentationNode>();

        if(actions.isEmpty()) {
            General.gameEngine.applyLegalMoves(node, Moves.EMPTY, true, actions);
        }
        else {
            for(RepresentationNode child: actions) {
                double val = min(t, (byte)1, child, alpha, Double.MAX_VALUE);

                if(val > v) {
                    equalVals.clear();
                    v = val;
                    bestMove = child;
                    equalVals.add(child);
                } else if(Math.abs(val - v) < 1e-8)
                    equalVals.add(child);


                if((System.currentTimeMillis() - t) >= LIMIT) {
                    this.interrupted = true;
                    break;
                }
                alpha = (alpha > val)? alpha : val;
            }
        }



        seeBestMoves(equalVals);
        //bestMove = equalVals.get(new Random().nextInt(equalVals.size()));


        for(RepresentationNode move: equalVals) {
            if(move.getMoveType() == Moves.CAPTURE) {
                bestMove = move;
                break;
            }
        }

        if(bestMove == null) {
            for(RepresentationNode move: equalVals) {
                if(move.getMoveType() == Moves.NONCAPTURE) {
                    bestMove = move;
                    break;
                }
            }
        }

        /*
        if(bestMove == null) {
            for(RepresentationNode move: equalVals) {
                if(move.getMoveType() == Moves.CAPTURE) {
                    bestMove = move;
                    break;
                }
            }
        }
        */

        if(bestMove == null) {
            for(RepresentationNode move: equalVals) {
                if(move.getMoveType() == Moves.EXIT) {
                    bestMove = move;
                    break;
                }
            }
        }

        if(bestMove == null) {
            for(RepresentationNode move: equalVals) {
                if(move.getMoveType() == Moves.EMPTY) {
                    bestMove = move;
                    break;
                }
            }
        }

        return bestMove;
    }

    private double max(long t, byte depth, RepresentationNode boardState, double alpha, double beta) {

        double v = -Double.MAX_VALUE;
        LinkedList<RepresentationNode> nextBoardStates = null;
        LinkedList<RepresentationNode> tmp = null;

        if((System.currentTimeMillis() - t) >= LIMIT) {
            interrupted = true;
            return beta;
        }


        if(depth >= targetDepth && depth <= extendedDepth) {

            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, true, null);
            //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.BASIC_DEFENSE, true, nextBoardStates);
            /*
            tmp = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, false, null);

            if(!tmp.isEmpty()) {
                nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, true, nextBoardStates);
                nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, true, nextBoardStates);
            }
            */
            if(nextBoardStates.isEmpty() || boardState.getMoveType() == Moves.EMPTY) {
                return h.h(boardState);
            }
            else {
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, true, nextBoardStates);
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, true, nextBoardStates);
            }


             //return h.h(boardState);
        }
        else {
            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.ALL, true, null);

        }

        if(nextBoardStates.isEmpty())
            return h.h(boardState);

        for(RepresentationNode child: nextBoardStates) {
            double val = min(t, (byte)(depth+1), child, alpha, beta);
            v = (v > val)? v : val;
            if(v >= beta) return v;
            if((System.currentTimeMillis() - t) >= LIMIT) {
                this.interrupted = true;
                return beta;
            }
            alpha = (alpha > v)? alpha : v;
        }
        return v;
    }


    private double min(long t, byte depth, RepresentationNode boardState, double alpha, double beta) {

        double v = Double.MAX_VALUE;
        LinkedList<RepresentationNode> nextBoardStates = null;
        LinkedList<RepresentationNode> tmp = null;

        if((System.currentTimeMillis() - t) >= LIMIT) {
            this.interrupted = true;
            return alpha;
        }

        if(depth >= targetDepth && depth <= extendedDepth) {

            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, false, null);
            //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.BASIC_DEFENSE, false, nextBoardStates);
            /*
            tmp = General.gameEngine.applyLegalMoves(boardState, Moves.CAPTURE, true, null);

            if(!tmp.isEmpty()) {
                nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, false, nextBoardStates);
                nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, false, nextBoardStates);
            }
            */

            if(nextBoardStates.isEmpty() || boardState.getMoveType() == Moves.EMPTY) {
                return h.h(boardState);
            }
            else {
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.NONCAPTURE, false, nextBoardStates);
                //nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.EXIT, false, nextBoardStates);
            }


            //return h.h(boardState);
        }
        else {
            nextBoardStates = General.gameEngine.applyLegalMoves(boardState, Moves.ALL, false, null);
        }

        if(nextBoardStates.isEmpty())
            return h.h(boardState);

        for(RepresentationNode child: nextBoardStates) {
            double val = max(t, (byte)(depth+1), child, alpha, beta);
            v = (v < val)? v : val;
            if(v <= alpha) return v;
            if((System.currentTimeMillis() - t) >= LIMIT) {
                this.interrupted = true;
                return v;//return alpha;
            }
            beta = (beta < v)? beta : v;
        }
        return v;
    }


    private void seeBestMoves(List<RepresentationNode> nextBoardStates) {}

    public boolean interrupted() {

        boolean res = false;

        if(this.interrupted) {
            res = true;
            this.interrupted = false;
        }
        else {
            res = false;
        }

        return res;
    }

}
