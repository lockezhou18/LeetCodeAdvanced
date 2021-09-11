public class LC486 {
    public boolean PredictTheWinner(int[] nums) {
        int[][] sums = setSums(nums);
        return PredictTheWinner(nums, sums);
    }

    private int[][] setSums(int[] nums) {
        int[][] sums = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    sums[i][j] = nums[i];
                    continue;
                }

                sums[i][j] = sums[i][j - 1] + nums[j];
            }
        }
        //sums[i][j] = sums[i][j - 1] + nums[j]

        return sums;
    }

    private boolean PredictTheWinner(int[] nums, int[][] sums) {
        int[][] dp = new int[nums.length][nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j <= nums.length - 1; j++) {
                int pickI = nums[i] + (i == (nums.length - 1) ? 0 : sums[i + 1][j] - dp[i + 1][j]);
                int pickJ = nums[j] + ((j == 0) ? 0 : sums[i][j - 1] - dp[i][j - 1]);

                dp[i][j] = Math.max(pickI, pickJ);
            }
        }

        return dp[0][nums.length - 1] >= (sums[0][nums.length - 1] - dp[0][nums.length - 1]);
    }
}
