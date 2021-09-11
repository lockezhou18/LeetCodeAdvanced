import java.util.*;

public class LeetCode1162 {
    int max = Integer.MIN_VALUE;
    public int maxDistance(int[][] grid) {
        bfs(grid, new HashSet<>());
        return max;
    }

    private void bfs(int[][] grid, Set<Integer> visited) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i  < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int curIdx = i * grid[0].length + j;
                    queue.offer(curIdx);
                }
            }
        }
        int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int minLenth = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int cur = queue.poll();
                int i = cur / grid[0].length;
                int j = cur % grid[0].length;

                for (int[] direction : DIRECTIONS) {
                    int ii = i + direction[0];
                    int jj = j + direction[1];

                    if (ii >= 0 && ii < grid.length && jj >= 0 && jj < grid[0].length
                            && !visited.contains(ii * grid[0].length + jj)
                            && grid[ii][jj] != 1) {
                        max = Math.max(max, minLenth);
                        queue.offer(ii * grid[0].length + jj);
                        visited.add(ii * grid[0].length + jj);
                    }
                }
            }
            minLenth++;
        }
    }
}
