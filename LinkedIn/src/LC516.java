public class LC516 {
    //palidrome, 从两端往中间思考 区间i， j
    //dp[i][j] = dp[i + 1][j - 1] + 2 if i == j
    //dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = s.length() - 2; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
