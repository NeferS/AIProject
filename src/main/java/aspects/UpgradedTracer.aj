package aspects;


import java.util.LinkedList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public aspect UpgradedTracer {

    private boolean trace = true;

    //private int boardUpdatesCount = 0;

    private int numberOfSearches = 0;

    private int[] depths;

    private int currentIteration = 0;

    private int targetIterations;

    private List<Integer> numberOfStartedIterationsCollection;

    private int numberOfStartedIterations;

    //private int numberOfCompletedIterations;

    private List<Integer> numberOfCompletedIterationsCollection;

    private Integer[] numberOfGeneratedNodes;
    private List<Integer[]> numberOfGeneratedNodesCollection;

    private Integer[] numberOfMMSteps;
    private List<Integer[]> numberOfMMStepsCollection;

    private Long[] searchIterationTime;
    private List<Long[]> searchIterationTimeCollection;

    private long searchStartTime = 0;
    private List<Long> searchTimeCollection;

    private Integer[] numberOfNodesAtTargetDepth;
    private List<Integer[]> numberOfNodesAtTargetDepthCollection;

    private Integer[] numberOfNodesBelowTargetDepth;
    private List<Integer[]> numberOfNodesBelowTargetDepthCollection;

    private Integer[] maxExtendedDepth;
    private List<Integer[]> maxExtendedDepthCollection;


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
        */
    }


    after() returning(representations.RepresentationNode newBoardState): enemyMakeMove() {
        if(!trace) return;

        /*
        System.out.println(boardUpdatesCount + " " + newBoardState.hashCode());
        boardUpdatesCount++;
        */
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
        this.numberOfStartedIterationsCollection = new LinkedList<Integer>();
        this.searchIterationTimeCollection = new LinkedList<Long[]>();

        this.numberOfGeneratedNodesCollection = new LinkedList<Integer[]>();
        this.numberOfMMStepsCollection = new LinkedList<Integer[]>();
        this.numberOfNodesAtTargetDepthCollection = new LinkedList<Integer[]>();
        this.numberOfNodesBelowTargetDepthCollection = new LinkedList<Integer[]>();
        this.maxExtendedDepthCollection = new LinkedList<Integer[]>();



        //Local search data

        this.searchStartTime = 0;
        this.currentIteration = 0;
        this.numberOfStartedIterations = 0;


        //Local search iteration data

        /*
        this.searchIterationTime = new Long[targetIterations];
        this.numberOfGeneratedNodes = new Integer[targetIterations];
        this.numberOfMMSteps = new Integer[targetIterations];
        this.numberOfNodesAtTargetDepth = new Integer[targetIterations];
        this.numberOfNodesBelowTargetDepth = new Integer[targetIterations];
        this.maxExtendedDepth = new Integer[targetIterations];
        */

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
        numberOfStartedIterations = 0;
        searchStartTime = currentTimeMillis();

        this.searchIterationTime = new Long[targetIterations];
        this.numberOfGeneratedNodes = new Integer[targetIterations];
        this.numberOfMMSteps = new Integer[targetIterations];
        this.numberOfNodesAtTargetDepth = new Integer[targetIterations];
        this.numberOfNodesBelowTargetDepth = new Integer[targetIterations];
        this.maxExtendedDepth = new Integer[targetIterations];

        for(int i = 0; i < targetIterations; i++) {
            numberOfGeneratedNodes[i] = 0;
            numberOfMMSteps[i] = 0;

            numberOfNodesAtTargetDepth[i] = 0;
            numberOfNodesBelowTargetDepth[i] = 0;

            maxExtendedDepth[i] = depths[i];
        }


    }



    //Misurazione della durata di un'iterazione dell'algoritmo
    before(representations.RepresentationNode boardState, long t): iterativeDeepeningIteration(boardState, t) {
        if(!trace) return;




        searchIterationTime[currentIteration] = currentTimeMillis();
        numberOfStartedIterations++;
        //this.boardState = boardState;



    }

    after() returning(representations.RepresentationNode res): iterativeDeepeningIteration(representations.RepresentationNode, long) {
        if(!trace) return;

        searchIterationTime[currentIteration] = currentTimeMillis() - searchIterationTime[currentIteration];
        /*
        System.out.print("currentIteration = " + currentIteration + " => ");
        for(int i = 0; i < searchIterationTime.length; i++) {
            System.out.print(searchIterationTime[i] + " ");
        }
        */

        //System.out.println();

        currentIteration++;



    }



    before(int completedIterations): completedIterations(completedIterations) {
        if(!trace) return;
        numberOfCompletedIterationsCollection.add(completedIterations);
        numberOfStartedIterationsCollection.add(numberOfStartedIterations);
    }


    after(): iterativeDeepening() {
        if(!trace) return;
        //numberOfCompletedIterationsCollection.add(numberOfCompletedIterations);
        searchTimeCollection.add(currentTimeMillis() - searchStartTime);
        numberOfSearches++;


        //for(int i = 0; i < searchIterationTime.length; i++) System.out.println(searchIterationTime[i]);

        searchIterationTimeCollection.add(searchIterationTime);
        numberOfGeneratedNodesCollection.add(numberOfGeneratedNodes);
        numberOfMMStepsCollection.add(numberOfMMSteps);
        numberOfNodesAtTargetDepthCollection.add(numberOfNodesAtTargetDepth);
        numberOfNodesBelowTargetDepthCollection.add(numberOfNodesBelowTargetDepth);
        maxExtendedDepthCollection.add(maxExtendedDepth);



    }


    before(List<representations.RepresentationNode> boardStates): seeBestMoves(boardStates) {
        if(!trace) return;

        /*
        for(int i = 0; i < boardStates.size(); i++) {
            System.out.println(boardStates.size());
        }
    */

    }

    after(representations.RepresentationNode boardState, representations.Moves movesType, boolean playerTurn, LinkedList<representations.RepresentationNode> nextBoardStates) returning(LinkedList<representations.RepresentationNode> res): generationStep(boardState, movesType, playerTurn, nextBoardStates) {
        if(!trace) return;

        /*
        applyLegalMovesCount++;
        System.out.println(applyLegalMovesCount);
        System.out.println(((BitboardRepresentationNode)boardState).getPlayerPieces(Color.WHITE));
        System.out.println(((BitboardRepresentationNode)boardState).getPlayerPieces(Color.BLACK));
        */
        numberOfGeneratedNodes[currentIteration] += res.size();

    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): minSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        numberOfMMSteps[currentIteration]++;

        //System.out.println("currentIteration = " + currentIteration);
        if(depth == depths[currentIteration]) numberOfNodesAtTargetDepth[currentIteration]++;
        else if(depth > depths[currentIteration]) {
            numberOfNodesBelowTargetDepth[currentIteration]++;
            if(depth > maxExtendedDepth[currentIteration]) {
                this.maxExtendedDepth[currentIteration] = (int) depth;
            }
        }

    }

    before(long t, byte depth, representations.RepresentationNode boardState, double alpha, double beta): maxSearchStep(t, depth, boardState, alpha, beta) {
        if(!trace) return;

        numberOfMMSteps[currentIteration]++;

        if(depth == depths[currentIteration]) numberOfNodesAtTargetDepth[currentIteration]++;
        else if(depth > depths[currentIteration]) {
            numberOfNodesBelowTargetDepth[currentIteration]++;
            if(depth > maxExtendedDepth[currentIteration]) {
                this.maxExtendedDepth[currentIteration] = (int) depth;
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
        */
        for(int j = 0; j < numberOfSearches; j++) {
            System.out.print(j + " search => ");
            System.out.print(searchTimeCollection.get(j) + " msec , " + numberOfCompletedIterationsCollection.get(j) + "/" + targetIterations + " completed iterations\n");


            for(int i = 0; i < numberOfCompletedIterationsCollection.get(j); i++) {
                System.out.print("     " + (i+1) + " iteration, " + depths[i] + "max depth\n");
                System.out.print("         " + searchIterationTimeCollection.get(j)[i] + "msec, ");
                System.out.print(numberOfGeneratedNodesCollection.get(j)[i] + " generated nodes, ");
                System.out.print(numberOfNodesAtTargetDepthCollection.get(j)[i] + " nodes at level " + depths[i] + ", ");
                System.out.print(numberOfNodesBelowTargetDepthCollection.get(j)[i] + " nodes below level " + depths[i] + ", ");
                System.out.print(maxExtendedDepthCollection.get(j)[i] + " max depth reached in extension points\n");
            }

        }

        //System.out.println(sb);

    }




}
