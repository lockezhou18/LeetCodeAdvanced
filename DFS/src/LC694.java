import java.util.HashSet;
import java.util.Set;

public class LC694 {
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, "Up", sb);
                set.add(sb.toString());
            }
        }

        return set.size();
    }

    private void dfs(int[][] grid, int row, int col, String direction, StringBuilder sb) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col]  == 0)
            return;

        sb.append(direction);
        dfs(grid, row - 1, col, "Up", sb);
        dfs(grid, row, col + 1, "Right", sb);
        dfs(grid, row + 1, col, "Down", sb);
        dfs(grid, row, col - 1, "Left", sb);
        sb.append("b"); //为啥这里append一个b？什么叫t型位置？
    }
}
