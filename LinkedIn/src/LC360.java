public class LC360 {
    //merge 2 sorted array
    //两个递减序列merge， curidx = end，谁大move谁
    //一个为空，另一个有序，会直接扫一遍
    int a;
    int b;
    int c;
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        //c.c a == 0 || nums.length == 1 target <= nums[0] || target >= nums[len - 1]

        this.a = a;
        this.b = b;
        this.c = c;

        //int target = -1 *  (b / (2 * a));//TODO : DOUBLE val
        //int pivot = binarySearch(nums, target);
        return merge(nums);
    }

//    private int binarySearch(int[] nums, int target) {
//        int start = 0;
//        int end = nums.length - 1;
//
//        while (start <= end) {
//            int mid = start + (end - start) / 2;
//
//            if (nums[mid] == target) {
//                start = mid + 1;
//            } else if (nums[mid] < target) {
//                start = mid + 1;
//            } else {
//                end = mid - 1;
//            }
//        }
//
//        return start < nums.length ? start : nums.length - 1;
//    }

    private int cal(int x) {
        return  a * x * x + b * x + c;
    }

    //merge两个递减序列， 按升序排序。 cur 为end， 谁大取谁谁move
    private int[] merge(int[] nums) {
        int[] res = new int[nums.length];
        int cur = a <= 0 ? 0 : nums.length - 1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (a <= 0) {//left++, right--, cur++, who small move who
                res[cur++] = cal(nums[left]) < cal(nums[right]) ? cal(nums[left++]) : cal(nums[right--]);
            }else { //left++, right--, cur--, who bigger move who
                res[cur--] = cal(nums[left]) > cal(nums[right]) ? cal(nums[left++]) : cal(nums[right--]);
            }
        }

        return res;
    }
}
