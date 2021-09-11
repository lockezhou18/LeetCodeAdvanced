//lc132
public class PalidromePartition {
    public int minCut(String s) {
        boolean[][] palidromDP = this.getPalidromeMatrix(s);
        return minCutDP(s, palidromDP);
    }

    //brute froce dfs
    private int minCut(String s, int curIdx, boolean[][] isPalidrome) {
        if (curIdx == s.length())
            return 0;

        if (isPalidrome[curIdx][s.length() - 1]) //base case is substring from curIdx to end is palidrome itself, no need to cut
            return 0;

        int min = Integer.MAX_VALUE;
        for (int i = curIdx; i < s.length(); i++) {
            if (isPalidrome[curIdx][i]) {
                min = Math.min(min, minCut(s, i + 1, isPalidrome) + 1);
            }
        }

        return min;
    }

    private int minCutDP (String s, boolean[][] isPalidrome) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (isPalidrome[i][j]) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                }
            }
        }

        return dp[0] - 1;
    }

    public boolean[][] getPalidromeMatrix(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (i == j - 1 || dp[i + 1][j - 1])) //每次移动两位的错位，首先j >= i + 1,
                    dp[i][j] = true;
            }
        }

        return dp;
    }

    public boolean isPalidrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;

            start++;
            end--;
        }

        return true;
    }
}
