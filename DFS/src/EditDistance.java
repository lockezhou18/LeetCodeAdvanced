public class EditDistance {
    public int minDistance(String word1, String word2) {
        return minDistanceDP(word1, word2);
    }

    private int minDistanceDP(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) { //dp i, j = string i j + 1
            for (int j = 1; j <= word2.length(); j++) {
                char ch1 = word1.charAt(i - 1);
                char ch2 = word2.charAt(j - 1);

                if (ch1 == ch2)
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    int min = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
                    dp[i][j] = min + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
