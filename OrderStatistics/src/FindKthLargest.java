public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int kthIdx = findKthSmallest(nums, 0, nums.length - 1, nums.length - k + 1); //找第k大，就是找第 n - k + 1大
        return nums[kthIdx];
    }

    private int findKthSmallest(int[] nums, int start, int end, int k) { //kth element idx from start to end
        if (start == end)
            return start;

        int pivotIdx = partition(nums, start, end); //return pivot idx from start to end
        int rank = pivotIdx - start + 1; //here the rank means the mth smallest element from start to end
        if (rank == k) {
            return pivotIdx;
        } else if (k < rank) {
            return findKthSmallest(nums, start, pivotIdx - 1, k);
        } else {
            return findKthSmallest(nums, pivotIdx + 1, end, k - rank);
        }
    }

    private int partition(int[] nums, int start, int end) { //partition the array by nums[pivot]. nums[start,pivot] < nums[pivot], nums[pivot + 1, end] >= pivot
        int left = start - 1; //nums[start, left] < nums[pivot], nums[left + 1, i] >= nums[pivot], nums[i, end - 1] unexplored
        int pivotIdx = end;
        for (int i = start; i < end; i++) {
            if (nums[i] >= nums[pivotIdx])
                continue;

            swap(nums, i, ++left);
        }

        swap(nums, pivotIdx, ++left);
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
