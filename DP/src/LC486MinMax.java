
//3034932153
public class LC486MinMax {
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            for (int j = i; j <= nums.length - 1; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                    continue;
                }

                int a = i + 2 <= j ? dp[i + 2][j] : 0;
                int b = i + 1 <= j ? dp[i + 1][j] : 0;
                int c = i <= j - 2 ? dp[i][j - 2] : 0;

                dp[i][j] = Math.max(
                        nums[i] + Math.min(a, b),
                        nums[j] + Math.min(b, c));
            }
        }
        return dp[0][nums.length - 1] >= sum - dp[0][nums.length - 1];
    }
}
