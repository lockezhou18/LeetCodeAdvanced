//lc115
public class DistinctSebsequences {
    public int numDistinct(String s, String t) {
//        int[][]memo = new int[s.length()][t.length()];
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = 0; j < t.length(); j++) {
//                memo[i][j] = -1;
//            }
//        }
//        return dfs(s, 0, t, 0, memo);

        return numDistinctDP(s, t);

    }

    private int dfs(String s, int i, String t, int j, int[][] memo) {
        if (j == t.length())
            return 1;

        if (i == s.length())
            return 0;

        if (memo[i][j] >= 0)
            return memo[i][j];


        memo[i][j] = dfs(s, i + 1, t, j, memo);

        if (s.charAt(i) == t.charAt(j))
            memo[i][j] += dfs(s, i + 1, t, j + 1, memo);


        return memo[i][j];
    }

    private int numDistinctDP(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
        return dp[s.length()][t.length()];
    }
}
