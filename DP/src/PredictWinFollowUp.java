/*
每个玩家只能从最左边拿1个或者2个
 */
public class PredictWinFollowUp {
    public boolean PredictTheWinner(int[] nums) {
        int[] dp = new int[nums.length];

        dp[nums.length - 1] = nums[nums.length - 1];
        int sum = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            sum += nums[i];
            int a = i + 2 >= nums.length ? 0 : dp[i + 2];
            int b = i + 3 >= nums.length ? 0 : dp[i + 3];
            int c = i + 4 >= nums.length ? 0 : dp[i + 4];

            dp[i] = Math.max((nums[i] + Math.min(a, b)), nums[i] + nums[i + 1] + Math.min(b, c));
        }

        return dp[0] >= sum - dp[0];
    }
}
