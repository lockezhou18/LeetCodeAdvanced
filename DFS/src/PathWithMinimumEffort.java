import java.util.PriorityQueue;

//lc1631
/*
1. state : i, j min cost from i, j to end
2. transition : (i - 1, j), (i, j - 1), (i + 1, j), (i, j + 1) + curHeightsDiff, select min
3. dfs + prunning
4.
 */
public class PathWithMinimumEffort {
    int min = Integer.MAX_VALUE;
    int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minimumEffortPath(int[][] heights) {
        //minimumEffortPath(heights, 0, 0, Integer.MIN_VALUE, new boolean[heights.length][heights[0].length]);
        //return min;

        return minEffortDijstra(heights);
    }

    private void minimumEffortPath(int[][] heights, int i, int j, int max, boolean[][] visited) {
        if (i == heights.length - 1 && j == heights[0].length - 1) {
            min = Math.min(min, max);
        }

        visited[i][j] = true;

        for (int[] direction : DIRECTIONS) {
            int ii = i + direction[0];
            int jj = j + direction[1];

            if (ii >= 0 && ii < heights.length && jj >= 0 && jj < heights[0].length && !visited[ii][jj]) {
                int curDiff = Math.abs(heights[i][j] - heights[ii][jj]);
                minimumEffortPath(heights, ii, jj, Math.max(curDiff, max), visited);
            }
        }

        visited[i][j] = false;
    }

    class Node {
        int row;
        int col; //state
        int minEffort = Integer.MAX_VALUE;

        public Node(int row, int col, int minEffort) {
            this.col = col;
            this.row = row;
            this.minEffort = minEffort;
        }
    }

    private int minEffortDijstra(int[][] heights) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> (a.minEffort - b.minEffort)); //frontier to contain the Nodes
        boolean[][] visited = new boolean[heights.length][heights[0].length]; //visited
        minHeap.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            if (isGoal(cur, heights))
                return cur.minEffort;
            visited[cur.row][cur.col] = true;
            for (int[] direction : DIRECTIONS) {
                int ii = cur.row + direction[0];
                int jj = cur.col + direction[1];

                if (isInBoundary(ii, jj, heights) && !visited[ii][jj]) {
                    Node next = expand(cur, ii, jj, heights);
                    minHeap.offer(next);
                }
            }
        }

        return -1;
    }

    private Node expand(Node cur, int i, int j, int[][] heights) {
        int startHeight = heights[cur.row][cur.col];
        int endHeight = heights[i][j];

        int actionCost = Math.abs(startHeight - endHeight);
        int minEffort = Math.max(cur.minEffort, actionCost);
        return new Node(i, j, minEffort);
    }

    private boolean isInBoundary(int i, int j, int[][] heights) {
        if (i >= 0 && i < heights.length && j >= 0 && j < heights[0].length)
            return true;

        return false;
    }

    private boolean isGoal(Node node, int[][] heights) {
        if (node.row == heights.length - 1 && node.col == heights[0].length - 1)
            return true;

        return false;
    }
}
