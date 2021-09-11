import java.util.Arrays;

//leetcode test problem for sorting.
public class LeetCode912 {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        int[] tempStore = Arrays.copyOf(nums, nums.length);
        mergeSort(nums, 0, nums.length - 1, tempStore);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end, int[] tempStore) {
        if (start == end)
            return;

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid, tempStore);
        mergeSort(nums, mid + 1, end, tempStore);
        if (start == 0 && end == nums.length - 1)
            System.out.println("debug");
        merge(nums, start, mid, end, tempStore);
    }

    private void merge(int[] nums, int start, int mid, int end, int[] tempStore) {
        for (int i = start; i <= end; i++) {
            tempStore[i] = nums[i];
        }
        int left = start;
        int right = mid + 1;
        int cur = start;
        while (left <= mid && right <= end) {
            if (tempStore[left] <= tempStore[right]) {
                nums[cur++] = tempStore[left++];
            } else {
                nums[cur++] = tempStore[right++];
            }
        }
//
        while (left <= mid) {
            nums[cur++] = tempStore[left++];
        }

//        while (right <= end) {
//            nums[cur++] = original[right++];
//        }
    }

}
