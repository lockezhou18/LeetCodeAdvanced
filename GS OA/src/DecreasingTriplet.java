public class DecreasingTriplet {
    public int decreasingTriplet(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < second) {
                total++;
            } else if (nums[i] >= first) {
                first = nums[i];
            } else {
                second = nums[i];
            }
        }

        return total;
    }
}
