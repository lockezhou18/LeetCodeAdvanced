
//首先判断nums[i](third 是否是valid)
public class LeetCode334 {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] <= first) {
                first = nums[i];
            } else {
                second = nums[i];
            }
        }

        return false;
    }
}
