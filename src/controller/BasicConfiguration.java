package controller;

import controller.Colour;

public class BasicConfiguration {
    public static final int numRows = 8;
    public static final int numCols = 8;
    public static final char startRow = 'A';
    public static final int startCol = 1;
    public static final int starting_player = Colour.White.ordinal();
    public static final int blackPawns = 12;
    public static final int whitePawns = 12;
    public static final int[] black_start_coodinates;
    public static final int[] white_start_coodinates;
    public static int thres_move;
    public static int thres_time;
    public static int port;
    public static int waiting_time;
    public static int time_before_YOURTURN;
    public static int time_before_OPPONENT_MOVE;
    public static String delim;

    static {
        int[] arrn = new int[2];
        arrn[1] = 3;
        black_start_coodinates = arrn;
        white_start_coodinates = new int[]{7, 4};
        thres_move = 60;
        thres_time = 1000;
        port = 8901;
        waiting_time = 15000;
        time_before_YOURTURN = 0;
        time_before_OPPONENT_MOVE = 0;
        delim = ",";
    }
}

