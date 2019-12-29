package aspects;


import java.util.LinkedList;
import java.util.List;


import static java.lang.System.currentTimeMillis;

public aspect BrokenUpgradedTracer {
/*
    private boolean trace = false;

    //private int boardUpdatesCount = 0;

    private int numberOfSearches = 0;

    private int[] depths;

    private int currentIteration = 0;

    private int targetIterations;

    private int numberOfCompletedIterations = 0;

    private List<Integer> numberOfCompletedIterationsCollection;

    private int numberOfGeneratedNodes = 0;
    private List<Integer>[] numberOfGeneratedNodesCollection;

    private int numberOfMMSteps = 0;
    private List<Integer>[] numberOfMMStepsCollection;

    private long searchIterationStartTime = 0;
    private List<Long>[] searchIterationTimeCollection;

    private long searchStartTime = 0;
    private List<Long> searchTimeCollection;

    private int numberOfNodesAtTargetDepth = 0;
    private List<Integer>[] numberOfNodesAtTargetDepthCollection;

    private int numberOfNodesBelowTargetDepth = 0;
    private List<Integer>[] numberOfNodesBelowTargetDepthCollection;

    private int maxExtendedDepth = -1;
    private List<Integer>[] maxExtendedDepthCollection;


    private representations.RepresentationNode boardState;

    pointcut searchAlgorithmConstructor(int[] depths, strategies.IHeuristic heuristic):
            preinitialization(strategies.IterativeDeepening.new(int[], strategies.IHeuristic)) &&
            args(depths, heuristic);

    pointcut iterativeDeepening():
            call(representations.RepresentationNode strategies.SearchAlgorithm.getBestMove(representations.RepresentationNode, long)) &&
            target(strategies.IterativeDeepening);

    pointcut iterativeDeepeningIteration(representations.RepresentationNode boardState, long t):
            call(representations.RepresentationNode strategies.SearchAlgorithm.getBestMove(representations.RepresentationNode, long)) &&
            args(boardState, t) &&
            target(strategies.UpgradedRMMAB);

    pointcut completedIterations(int i):
            call(void strategies.IterativeDeepening.completedIterations(int)) &&
            args(i);


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

    pointcut playerMakeMove(representations.RepresentationNode newBoardState):
            call(void representations.GameEngine.playerMakeMove(representations.RepresentationNode)) &&
            args(newBoardState) &&
            target(representations.BasicGameEngine);

    pointcut enemyMakeMove():
            call(representations.RepresentationNode representations.GameEngine.enemyMakeMove(java.lang.String)) &&
                    target(representations.BasicGameEngine);

    pointcut log():
            call(void communication.Listener.log());


    before(representations.RepresentationNode newBoardState): playerMakeMove(newBoardState) {
        if(!trace) return;

        /*
        System.out.println(boardUpdatesCount + " " + newBoardState.hashCode());
        boardUpdatesCount++;
        *
    }


    after() returning(representations.RepresentationNode newBoardState): enemyMakeMove() {
        if(!trace) return;

        /*
        System.out.println(boardUpdatesCount + " " + newBoardState.hashCode());
        boardUpdatesCount++;
        *
    }


    before(int[] depths, strategies.IHeuristic heuristic): searchAlgorithmConstructor(depths, heuristic) {
        if(!trace) return;

        //Global data, common to all searches
        this.depths = depths;
        this.targetIterations = depths.length;
        this.numberOfSearches = 0;


        //Global output data
        this.searchTimeCollection = new LinkedList<Long>();
        this.numberOfCompletedIterationsCollection = new LinkedList<Integer>();
        this.searchIterationTimeCollection = new LinkedList[targetIterations];
        this.numberOfGeneratedNodesCollection = new LinkedList[targetIterations];
        this.numberOfMMStepsCollection = new LinkedList[targetIterations];
        this.numberOfNodesAtTargetDepthCollection = new LinkedList[targetIterations];
        this.numberOfNodesBelowTargetDepthCollection = new LinkedList[targetIterations];
        this.maxExtendedDepthCollection = new LinkedList[targetIterations];

        for(int i = 0; i < targetIterations; i++) {
            this.numberOfGeneratedNodesCollection[i] = new LinkedList<Integer>();
            this.numberOfMMStepsCollection[i] = new LinkedList<Integer>();
            this.searchIterationTimeCollection[i] = new LinkedList<Long>();
            this.numberOfNodesAtTargetDepthCollection[i] = new LinkedList<Integer>();
            this.numberOfNodesBelowTargetDepthCollection[i] = new LinkedList<Integer>();
            this.maxExtendedDepthCollection[i] = new LinkedList<Integer>();
        }

        //Local search data
        this.searchStartTime = 0;
        this.currentIteration = 0;
        this.numberOfCompletedIterations = 0;

        //Local search iteration data
        this.searchIterationStartTime = 0;
        this.numberOfGeneratedNodes = 0;
        this.numberOfMMSteps = 0;
        this.numberOfNodesAtTargetDepth = 0;
        this.numberOfNodesBelowTargetDepth = 0;
        this.maxExtendedDepth = -1;

        System.out.println("Target iterations = " + this.targetIterations);
        System.out.print("Depths = ");
        for(int i = 0; i < this.targetIterations; i++) {
            System.out.print(this.depths[i] + " ");
        }
        System.out.println();
    }


    before(): iterativeDeepening() {
        if(!trace) return;

        currentIteration = 0;
        searchStartTime = currentTimeMillis();
    }



    //Misurazione della durata di un'iterazione dell'algoritmo
    before(representations.RepresentationNode boardState, long t): iterativeDeepeningIteration(boardState, t) {
        if(!trace) return;

        searchIterationStartTime = currentTimeMillis();

        //this.boardState = boardState;

        numberOfGeneratedNodes = 0;
        numberOfMMSteps = 0;

        numberOfNodesAtTargetDepth = 0;
        numberOfNodesBelowTargetDepth = 0;
        maxExtendedDepth = depths[currentIteration];

    }

    after() returning(representations.RepresentationNode res): iterativeDeepeningIteration(representations.RepresentationNode, long) {
        if(!trace) return;

        searchIterationTimeCollection[currentIteration].add(currentTimeMillis() - searchIterationStartTime);
        numberOfGeneratedNodesCollection[currentIteration].add(numberOfGeneratedNodes);
        numberOfMMStepsCollection[currentIteration].add(numberOfMMSteps);
        numberOfNodesAtTargetDepthCollection[currentIteration].add(numberOfNodesAtTargetDepth);
        numberOfNodesBelowTargetDepthCollection[currentIteration].add(numberOfNodesBelowTargetDepth);
        maxExtendedDepthCollection[currentIteration].add(maxExtendedDepth);

        if(currentIteration < targetIterations -1 ) currentIteration++;
    }



    before(int completedIterations): completedIterations(completedIterations) {
        if(!trace) return;

        numberOfCompletedIterationsCollection.add(completedIterations);
    }


    after(): iterativeDeepening() {
        if(!trace) return;
        //numberOfCompletedIterationsCollection.add(numberOfCompletedIterations);
        searchTimeCollection.add(currentTimeMillis() - searchStartTime);
        numberOfSearches++;

    }


    before(List<representations.RepresentationNode> boardStates): seeBestMoves(boardStates) {
        if(!trace) return;

        /*
        for(int i = 0; i < boardStates.size(); i++) {
            System.out.println(boardStates.size());
        }
    *

    }

    after(representations.RepresentationNode boardState, representations.Moves movesType, boolean playerTurn, LinkedList<representations.RepresentationNode> nextBoardStates) returning(LinkedList<representations.RepresentationNode> res): generationStep(boardState, movesType, playerTurn, nextBoardStates) {
        if(!trace) return;

        /*
        applyLegalMovesCount++;
        System.out.println(applyLegalMovesCount);
        System.out.println(((BitboardRepresentationNode)boardState).getPlayerPieces(Color.WHITE));
        System.out.println(((BitboardRepresentationNode)boardState).getPlayerPieces(Color.BLACK));
        *
        numberOfGeneratedNodes += res.size();
    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): minSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        numberOfMMSteps++;

        //System.out.println("currentIteration = " + currentIteration);
        if(depth == depths[currentIteration]) numberOfNodesAtTargetDepth++;
        else if(depth > depths[currentIteration]) {
            numberOfNodesBelowTargetDepth++;
            if(depth > maxExtendedDepth) {
                this.maxExtendedDepth = depth;
            }
        }

    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): maxSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        numberOfMMSteps++;

        if(depth == depths[currentIteration]) numberOfNodesAtTargetDepth++;
        else if(depth > depths[currentIteration]) {
            numberOfNodesBelowTargetDepth++;
            if(depth > maxExtendedDepth) {
                this.maxExtendedDepth = depth;
            }
        }
    }

    before(): h() {
        //System.out.println("yuhuuuu");
    }




    //Stampa del log
    before(): log() {
        if(!trace) return;

        //int len = searchIterationTimeCollection[0].size();

        //if(len == 0) System.out.println("Niente da fare");

        /*
        for(int i = 0; i < iterations; i++) {
            System.out.println(times[i].size());
            System.out.println(nodesCounts[i].size());
            System.out.println(nodesAtLCollection[i].size());
            System.out.println(nodesBelowLCollection[i].size());
            System.out.println(greaterThanLCollection[i].size());

        }

        for(int j = 0; j < numberOfSearches; j++) {
            System.out.print(j + " search => ");
            System.out.print(searchTimeCollection.get(j) + " msec , " + numberOfCompletedIterationsCollection.get(j) + "/" + targetIterations + " completed iterations\n");


            for(int i = 0; i < numberOfCompletedIterationsCollection.get(j); i++) {
                System.out.print("     " + (i+1) + " iteration, " + depths[i] + "max depth\n");
                System.out.print("         " + searchIterationTimeCollection[i].get(j) + "msec, ");
                System.out.print(numberOfGeneratedNodesCollection[i].get(j) + " generated nodes, ");
                System.out.print(numberOfNodesAtTargetDepthCollection[i].get(j) + " nodes at level " + depths[i] + ", ");
                System.out.print(numberOfNodesBelowTargetDepthCollection[i].get(j) + " nodes below level " + depths[i] + ", ");
                System.out.print(maxExtendedDepthCollection[i].get(j) + " max depth reached in extension points\n");
            }

        }

        //System.out.println(sb);

    }
*/



}
