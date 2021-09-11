public class LC256 {
    public int minCost(int[][] costs) {
        return dfs(costs, 0, 3, new Integer[costs.length][4]); //给一个不在界里的值就可
    }

    private int dfs(int[][] costs, int curIdx, int lastColor, Integer[][] memo) {
        if (curIdx == costs.length)
            return 0;

        if (memo[curIdx][lastColor] != null)
            return memo[curIdx][lastColor];

        int min = Integer.MAX_VALUE;

        for (int color = 0; color < 3; color++) {
            if (canPaint(lastColor, color)) {
                min = Math.min(min, dfs(costs, curIdx + 1, color, memo) + costs[curIdx][color]);
            }
        }

        memo[curIdx][lastColor] = min;
        return min;
    }

    private boolean canPaint(int a, int b) {
        return a != b;
    }

//    private int dp(int[][] costs) {
//
//    }
}
