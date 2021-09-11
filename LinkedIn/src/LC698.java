public class LC698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = getSum(nums);
        if (sum % k != 0)
            return false;

        int upperBound = sum / k;
        return dfs(nums, 0, new int[k], upperBound);
    }

    private boolean dfs(int[] nums, int curIdx, int[] buckets, int upperBound) {
        if (curIdx == nums.length)
            return isEqual(buckets);


        for (int k = 0; k < buckets.length; k++) {
            if (buckets[k] + nums[curIdx] > upperBound)
                continue;

            buckets[k] += nums[curIdx];
            if (dfs(nums, curIdx + 1, buckets, upperBound))
                return true;
            buckets[k] -= nums[curIdx];
        }

        return false;
    }

    private boolean isEqual(int[] buckets) {
        int num = buckets[0];
        for (Integer bucket : buckets){
            if (bucket != num)
                return false;
        }

        return true;
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }

        return sum;
    }

}
