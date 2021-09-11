import java.util.Arrays;

public class LC611 {
    public int triangleNumber(int[] nums) { //a <= b <= c 只要找到满足 a + b > c的值就可以 找大于target的最小值 bs
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int idx = binarySearch(nums, j + 1, nums[i] + nums[j]);
                res += idx >= 0 ? idx - j : 0;
            }
        }

        return res;
    }

    private int binarySearch(int[] nums, int startPos, int target) { //小于target的最大值的idx
        int end = nums.length - 1;
        int start = startPos;
        while (start <= end) {
            int mid = end + (start - end) / 2;
            if (nums[mid] == target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end < startPos ? -1 : end;
    }
}
