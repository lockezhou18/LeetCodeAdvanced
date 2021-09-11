public class MainFile {
    public static void main(String args[]) {
        testKPairs();
    }

    private static void testKPairs() {
//        int[] nums1 = {1,7,11};
//        int[] nums2 = {2,4,6};
        int[] nums1 = {1,2};
        int[] nums2 = {3};
        int k = 3;
        FindKPairswithSmallestSums findKPairswithSmallestSums = new FindKPairswithSmallestSums();
        System.out.println(findKPairswithSmallestSums.kSmallestPairs(nums1, nums2, k));
    }
}
