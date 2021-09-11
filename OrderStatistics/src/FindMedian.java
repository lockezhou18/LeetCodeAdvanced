import java.util.Stack;

public class FindMedian {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        boolean flag = (len % 2 == 0); //true even, flase odd

        int k = len / 2;

        int m = 0;
        int n = 0;
        int prev = 0;
        int cur = 0;

        while (k-- > 0) {
            prev = cur;
            if (m < nums1.length && n < nums2.length) {
                cur = nums1[m] < nums2[n] ? nums1[m++] : nums2[n++];
            } else if (m < nums1.length) {
                cur = nums1[m++];
            } else {
                cur = nums2[n++];
            }
        }

        if (flag) return (double)(prev + cur) / 2;

        return cur;

    }
}
