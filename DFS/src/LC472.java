import java.util.HashMap;
import java.util.Map;

public class LC472 {
        int totalSum;
        int count;
        public boolean canPartition(int[] nums) {
            for (int num : nums) {
                totalSum += num;
            }

            return dfs(nums, 0, 0, 0, new Boolean[nums.length][totalSum + 2]);
        }

        private boolean dfs(int[] nums, int i, int sum1, int sum2, Boolean[][] memo) {
            if (i == nums.length) {
                return sum1 == totalSum - sum1;
            }

            // String key = sum1 + " " + " " + sum2 + i;
            // if (memo.containsKey(key))
            //     return memo.get(key);
            if (memo[i][sum1] != null)
                return memo[i][sum1];

            boolean putTo1 = dfs(nums, i + 1, sum1 + nums[i], totalSum - sum1, memo);
            boolean putTo2 = dfs(nums, i + 1, sum1, totalSum - sum1 + nums[i], memo);

            //memo.put(key, putTo1 || putTo2);

            memo[i][sum1] = putTo1 || putTo2;
            return putTo1 || putTo2;

        }
}
