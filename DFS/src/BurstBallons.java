import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BurstBallons {
    public int maxCoins(int[] nums) {
        return this.maxCoins(transfer(nums));
    }

    private LinkedList transfer(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            res.add(nums[i]);
        }

        return res;
    }

    //brute force dfs
    private int maxCoins(LinkedList<Integer> state) {
        if (state.size() == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < state.size(); i++) {
            int leftBallonCoin = i == 0 ? 1 : state.get(i - 1);
            int rightBallonCoin = i == state.size() - 1 ? 1 : state.get(i + 1);
            int curCoins = leftBallonCoin * state.get(i) * rightBallonCoin;

            Integer removedBallon = state.remove(i);
            max = Math.max(max, curCoins + maxCoins(state));
            state.add(i, removedBallon);
        }

        return max;
    }

    //dp 不需要初始化？？
    public int maxCoinsDP(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i; j <= nums.length - 1; j++) {
                for (int k = i; k <= j; k++) {

                    int leftCoins = i == 0 ? 1 : nums[i - 1];
                    int rightCoins = j == nums.length - 1 ? 1 : nums[j + 1];
                    int leftParts = k == 0 ? 0 : dp[i][k - 1];
                    int rightParts = k == nums.length - 1 ? 0 : dp[k + 1][j];
                    int curCoins = leftCoins * nums[k] * rightCoins + leftParts + rightParts;
                    dp[i][j] = Math.max(dp[i][j], curCoins);
                }
            }
        }

        return dp[0][nums.length - 1];
    }
}
