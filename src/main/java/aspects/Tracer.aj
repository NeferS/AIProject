package aspects;

import java.util.LinkedList;
import java.util.List;


import static java.lang.System.currentTimeMillis;

public aspect Tracer {

    /*

    private boolean trace = false;

    private int maxDepth;

    private int nodesCount = 0;
    private List<Integer> nodesCounts = new LinkedList<Integer>();

    private int searchStepsCount = 0;
    private List<Integer> searchStepsCounts = new LinkedList<Integer>();

    private long time = 0;
    private List<Long> times = new LinkedList<Long>();

    private int nodesAtL = 0;
    private List<Integer> nodesAtLCollection = new LinkedList<Integer>();

    private int nodesBelowL = 0;
    private List<Integer> nodesBelowLCollection = new LinkedList<Integer>();

    private int greaterThanL = -1;
    private List<Integer> greaterThanLCollection = new LinkedList<Integer>();



    pointcut searchAlgorithmConstructor(int maxDepth, strategies.IHeuristic heuristic):
            preinitialization(strategies.UpgradedRMMAB.new(int, strategies.IHeuristic)) &&
            args(maxDepth, heuristic);

    pointcut explore():
            call(representations.RepresentationNode strategies.SearchAlgorithm.getBestMove(representations.RepresentationNode, long)) &&
            target(strategies.UpgradedRMMAB);

    pointcut seeBestMoves(List<representations.RepresentationNode> boardStates):
            call(void strategies.UpgradedRMMAB.seeBestMoves(List<representations.RepresentationNode>)) &&
            args(boardStates);

    pointcut minSearchStep(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta):
            call(double strategies.UpgradedRMMAB.min(long, byte, representations.RepresentationNode, double, double)) &&
            args(t, depth, boardState, alpha, beta);

    pointcut maxSearchStep(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta):
            call(double strategies.UpgradedRMMAB.max(long, byte, representations.RepresentationNode, double, double)) &&
                    args(t, depth, boardState, alpha, beta);

    pointcut generationStep(representations.RepresentationNode boardState, representations.Moves movesType, boolean playerTurn, LinkedList<representations.RepresentationNode> nextBoardStates):
            call(LinkedList<representations.RepresentationNode> representations.GameEngine.applyLegalMoves(representations.RepresentationNode, representations.Moves, boolean, LinkedList<representations.RepresentationNode>)) &&
            args(boardState, movesType, playerTurn, nextBoardStates) &&
            target(representations.BasicGameEngine);

    pointcut h():
            call(double strategies.IHeuristic.h(representations.RepresentationNode)) &&
            target(strategies.FirstHeuristic);


    pointcut log():
            call(void communication.Listener.log());


    before(int maxDepth, strategies.IHeuristic heuristic): searchAlgorithmConstructor(maxDepth, heuristic) {
        if(!trace) return;

        this.maxDepth = maxDepth;
        System.out.println(this.maxDepth);
    }

    //Misurazione della durata di un'iterazione dell'algoritmo
    before(): explore() {
        if(!trace) return;

        time = currentTimeMillis();

        nodesCount = 0;
        searchStepsCount = 0;

        nodesAtL = 0;
        nodesBelowL = 0;
        greaterThanL = -1;

    }

    after(): explore() {
        if(!trace) return;

        times.add(currentTimeMillis() - time);
        nodesCounts.add(nodesCount);
        searchStepsCounts.add(searchStepsCount);
        nodesAtLCollection.add(nodesAtL);
        nodesBelowLCollection.add(nodesBelowL);
        greaterThanLCollection.add(greaterThanL);
    }

    before(List<representations.RepresentationNode> boardStates): seeBestMoves(boardStates) {
        /*
        System.out.println("0 search :");
        for(int i = 0; i < boardStates.size(); i++) {
            System.out.println(boardStates.get(i).getMove() + " => h = " + boardStates.get(i).getHeuristicValue());
        }


    }

    after(representations.RepresentationNode boardState, representations.Moves movesType, boolean playerTurn, LinkedList<representations.RepresentationNode> nextBoardStates) returning(LinkedList<representations.RepresentationNode> res): generationStep(boardState, movesType, playerTurn, nextBoardStates) {
        if(!trace) return;

        nodesCount += res.size();
    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): minSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        searchStepsCount++;

        if(depth == this.maxDepth) nodesAtL++;
        else if(depth > this.maxDepth) {
            nodesBelowL++;
            if(depth > greaterThanL) {
                this.greaterThanL = depth;
            }
        }

    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): maxSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        searchStepsCount++;

        if(depth == this.maxDepth) nodesAtL++;
        else if(depth > this.maxDepth) {
            nodesBelowL++;
            if(depth > greaterThanL) {
                this.greaterThanL = depth;
            }
        }
    }

    before(): h() {
        //System.out.println("yuhuuuu");
    }




    //Stampa del log
    before(): log() {
        if(!trace) return;

        int len = times.size();
        StringBuilder sb = new StringBuilder();

        if(len == 0) sb.append("Niente da fare");

        for(int i = 0; i < len; i++) {
            sb.append(i + " search => ");
            sb.append(times.remove(0) + "msec, ");
            sb.append(nodesCounts.remove(0) + " generated nodes, ");
            sb.append(nodesAtLCollection.remove(0) + " nodes at level " + maxDepth + ", ");
            sb.append(nodesBelowLCollection.remove(0) + " nodes below level " + maxDepth + ", ");
            sb.append(greaterThanLCollection.remove(0) + " max depth reached in extension points, ");
            sb.append("\n");
            //sb.append(searchStepsCounts.remove(0) + " evaluated nodes\n");

        }

        System.out.println(sb);

    }


*/

}
