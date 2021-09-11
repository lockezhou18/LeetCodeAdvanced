import java.util.Arrays;

public class MaxAreaOfIsland {
    int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        //c.c
        int[][] gridCopy = copyGrid(grid);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < gridCopy.length; i++) {
            for (int j = 0; j < gridCopy[0].length; j++) {
                if (gridCopy[i][j] == 1) {
                    max = Math.max(max, maxAreaOfIsland(gridCopy, i, j));
                }
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    private int[][] copyGrid(int[][] grid) {
        int[][] gridCopy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }
        return gridCopy;
    }
    //state : i, j
    //initial state : i, j and 1
    // transition model : inside boundary and 4 directions and value is 1 -> itself is a bidirection cycliced graph. we should check visited. here we just set 0
    //goal test :  null, update area for current state, here we want to find area from the current state
    //step cost : 1 no weight
    //copy a new board and pass to the function
    //2d matrix itself can be converted to a graph
    //no prunning, no overlap calculation
    private int maxAreaOfIsland(int[][] grid, int i, int j) {
        System.out.println(grid);
        int row = grid.length;
        int col = grid[0].length;

        int area = 1;
        grid[i][j] = 0;

        for (int[] direction : DIRECTIONS) {
            int ii = i + direction[0];
            int jj = j + direction[1];

            if (ii >= 0 && ii < row && jj >= 0 && jj < col
                    && grid[ii][jj] != 0) {
                area += maxAreaOfIsland(grid, ii, jj);
            }
        }
        return area;
    }
}
