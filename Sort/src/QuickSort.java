import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    int count;
    public void quickSort(int[] nums) {
        if (!(nums instanceof int[]))
            throw new IllegalArgumentException("Input type is not an integer array");
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("input array should not be null or empty");
        //System.out.println("Test data - " + Arrays.toString(nums));
        //ystem.out.println("res - " + Arrays.toString(nums) + " -> count - " + count);
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;

        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private int partition(int[] nums, int left, int right) { //index of the pivot number
        int pivotNum = nums[right];
        int boundary = left - 1;
        for (int i = left; i <= right - 1; i++) {
            if (nums[i] <= pivotNum) {
                swap(nums, i, ++boundary);
                count++;
            }
        }

        swap(nums, right, ++boundary);
        return boundary;
    }

    private void randomizePivotNumber(int[] nums, int left, int right) {
        Random random = new Random();
        int pivotIdx = random.nextInt(right - left + 1) + left;
        swap(nums, pivotIdx, right);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
