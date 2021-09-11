import java.util.Arrays;

public class ThreeSumCloest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            int curTarget = target - nums[i];
            while(start < nums.length && start < end) {
                int total = nums[i] + nums[start] + nums[end];
                if (nums[start] + nums[end] == curTarget)
                    return total;

                if (Math.abs(target - total) < diff) {
                    sum = total;
                    diff = Math.abs(target - total);
                }

                if (nums[start] + nums[end] < curTarget) {
                    start++;
                }

                if (nums[start] + nums[end] > curTarget) {
                    end--;
                }
            }
        }

        return sum;
    }
}
