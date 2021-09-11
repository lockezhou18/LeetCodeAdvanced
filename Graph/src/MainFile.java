import Sudoku.LC37;

import java.util.Map;

public class MainFile {
    public static void main(String args[]) {
        //testBipartite();
        //testMColoringDecision();
        //testDijsktra();
        //testLC261();
        testSudo();
    }

    private static void testSudo() {
        LC37 lc37 = new LC37();
        char[][] board = {{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                          {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};

        lc37.solveSudoku(board);
    }

    private static void testLC261() {
        int n = 5;
        int[][] edges = {{0,1},{0,2},{0,3},{1,4}};
        LC261 lc261 = new LC261();
        System.out.println(lc261.validTree(n, edges));
    }

    private static void testDijsktra() {
//        int n = 3;
//        int[][] edges = {{0,1},{1,2},{0,2}};
//        double[] succProb = {0.5,0.5,0.2};
//        int start = 0;
//        int end = 2;
        int n = 3;
        int[][]edges = {{0,1},{1,2},{0,2}};
        double[] succProb = {0.5,0.5,0.3};
        int start = 0;
        int end = 2;
        Dijkstra dijkstra = new Dijkstra();
        System.out.println(dijkstra.maxProbability(n, edges, succProb, 0, end));
    }

    private static void testMColoringDecision() {
        int[][] graphFalseCase = {{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] graphTrueCase = {{1,3},{0,2},{1,3},{0,2}};
        int[][] unconnectedGraphTrueCase = {{},{3},{},{1},{}};
        int[][] trueCase = {{1},{0,3},{3},{1,2}};
        int[][] failedCase = {{3},{2,4},{1},{0,4},{1,3}}; //expected true

        MColoringDecision mColoringDecision = new MColoringDecision(unconnectedGraphTrueCase, 2);
        System.out.println(mColoringDecision.isColorable());
    }

    private static void testBipartite() {
        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        int[][] graphFalseCase = {{1,2,3},{0,2},{0,1,3},{0,2}};
        int[][] graphTrueCase = {{1,3},{0,2},{1,3},{0,2}};
        int[][] unconnectedGraph = {{},{3},{},{1},{}};
        System.out.println(bipartiteGraph.isBipartite(graphFalseCase));
    }
}
