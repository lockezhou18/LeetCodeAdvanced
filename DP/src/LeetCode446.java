import java.util.ArrayList;
import java.util.HashMap;

public class LeetCode446 {
    public int numberOfArithmeticSlices(int[] nums) {
        //c.c
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indices = map.getOrDefault(nums[i], new ArrayList<>());
            indices.add(i);
            map.put(nums[i], indices);
        }

        int[][] dp = new int[nums.length][nums.length]; //num of sequences that ends with nums[i], nums[j]

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+ 1; j < nums.length; j++) {
                long target = 2 * (long) nums[i] - nums[j];

                if (target < Integer.MIN_VALUE || target > Integer.MAX_VALUE)
                    continue;

                for (Integer k : map.getOrDefault((int) target, new ArrayList<>())) {
                    if (k < i)
                        dp[i][j] += dp[k][i] + 1;
                }
                res += dp[i][j];
            }
        }

        return res;
    }
}
