import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC322 {
    //coin change
    public int coinChange(int[] coins, int amount) {
        Integer[][] memo = new Integer[coins.length][amount + 1];
        return dfs(coins, amount, 0, memo);
    }

    private int dfs(int[] coins, int amount, int curIdx, Integer[][] memo) {
        if (amount == 0) //success
            return 0;

        if (curIdx == coins.length || amount < 0)
            return -1; //not able to pick

        if (memo[curIdx][amount] != null)
            return memo[curIdx][amount];

        int upperLimit = amount / coins[curIdx];
        int curMin = Integer.MAX_VALUE;

        for (int i = 0; i <= upperLimit; i++) {
            int nextMin = -1;
            if (amount - i * coins[curIdx] >= 0) { //constraint
                nextMin = dfs(coins, amount - i * coins[curIdx], curIdx + 1, memo);
            }

            if (nextMin != -1) {
                curMin = Math.min(curMin, nextMin + i);
            }
        }

        memo[curIdx][amount] = curMin == Integer.MAX_VALUE ? -1 : curMin;
        return memo[curIdx][amount];
    }
}
