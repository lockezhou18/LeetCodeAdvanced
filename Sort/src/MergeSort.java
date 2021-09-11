import java.util.Arrays;

public class MergeSort {
    int[] tempStore;
    public void mergeSort(int[] nums) { //sort the input array
        if (!(nums instanceof int[]))
            throw new IllegalArgumentException("Input type is not an integer array");
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("input array should not be null or empty");
        tempStore = Arrays.copyOf(nums, nums.length);
        mergeSort(nums, 0, nums.length - 1);
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start == end)
            return;

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        if (start == 0 && end == nums.length - 1)
            System.out.println("debug");
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end) {
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

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
