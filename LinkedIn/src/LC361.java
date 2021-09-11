import java.util.Map;

//非常好的题目
//一开始的分析方向是对的， brute force (m * n) * (m + n) 从 m + n 入手，一般可以优化成 1， 因为有reduant计算
//空间换时间，记录一下， 第一想法是hashmap，但是有墙。
//依然可以记录，当遇到墙的时候reset，重新计算
//整体来看，每个cell被摸三次 amortized o(1)
public class LC361 {
    public int maxKilledEnemies(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int rowHits = 0;
        int[] colHits = new int[cols];
        int max = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (col == 0 || grid[row][col - 1] == 'W') { //reset rowHits and re-calculate
                    int k = col;
                    rowHits = 0;
                    while (k < cols && grid[row][k] != 'W') {
                        rowHits += grid[row][k] == 'E' ? 1 : 0;
                        k++;
                    }
                }

                if (row == 0 || grid[row - 1][col] == 'W') {
                    int k = row;
                    colHits[col] = 0;
                    while (k < rows && grid[k][col] != 'W') {
                        colHits[col] += grid[k][col] == 'E' ? 1 : 0;
                        k++;
                    }
                }

                if (grid[row][col] == '0')
                    max = Math.max(max, rowHits + colHits[col]);
            }
        }

        return max;
    }

}
