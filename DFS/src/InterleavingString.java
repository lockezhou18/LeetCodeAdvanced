public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        //return dfs(s1, 0, s2, 0, s3, new Boolean[s1.length() + 1][s2.length() + 1]);
        return isInterleaveDP(s1, s2, s3);
    }

    private boolean isInterleaveDP(String s1, String s2, String s3) {
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = s1.length(); i >= 0; i--) {
            for (int j = s2.length(); j >= 0; j--) {
                if (i == s1.length()) {
                    dp[i][j] = s2.substring(j, s2.length()).equals(s3.substring(i + j, s3.length()));
                    continue;
                }

                if (j == s2.length()) {
                    dp[i][j] = s1.substring(i, s1.length()).equals(s3.substring(i + j, s3.length()));
                    continue;
                }

                if (s1.charAt(i) == s3.charAt(i + j))
                    dp[i][j] = dp[i + 1][j];

                if (s2.charAt(j) == s3.charAt(i + j))
                    dp[i][j] |= dp[i][j + 1];
            }
        }
        return dp[0][0];
    }

    //[i, len - 1], [j, len - 1] fully match [i + j, len - 1]
    private boolean dfs(String s1, int idx1, String s2, int idx2, String s3, Boolean[][] memo) {
        //i reach the end, just check if [j, len -1] can fully match [i + j, len - 1]
        if (idx1 == s1.length()) {
            return s2.substring(idx2, s2.length()).equals(s3.substring(idx1 + idx2, s3.length()));
        }

        //j reach to the end, just check if [i, len - 1] can fully match [i + j, len - 1]
        if (idx2 == s2.length()) {
            return s1.substring(idx1, s1.length()).equals(s3.substring(idx1 + idx2, s3.length()));
        }

        if (memo[idx1][idx2] != null)
            return memo[idx1][idx2];

        memo[idx1][idx2] = false;

        char target = s3.charAt(idx1 + idx2);
        if (s1.charAt(idx1) == target) {
            memo[idx1][idx2] = dfs(s1, idx1 + 1, s2, idx2, s3, memo);
        }

        if (s2.charAt(idx2) == target) {
            memo[idx1][idx2] |= dfs(s1, idx1, s2, idx2 + 1, s3, memo);
        }

        return memo[idx1][idx2];
    }
}
