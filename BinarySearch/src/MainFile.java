public class MainFile {
    public static void main(String args[]) {
        //testLeetCode334();
        //testFindKthSmallest();
        testLC744();
    }

//    private static void testFindKthSmallest() {
//        FindKthSmallest findKthSmallest = new FindKthSmallest();
//        int[] nums = {7, 10, 4, 3, 20, 15};
//        System.out.println(findKthSmallest.findKthSmallest(nums, 3));
//    }

    private static void testLC744() {
        LC744 lc744 = new LC744();
        char[] letters = {'c','f','j'};
        char target = 'c';
        int a = 0;

        System.out.println(lc744.nextGreatestLetter(letters, target));
    }
    private static void testLeetCode334() {
        LeetCode334 leetCode334 = new LeetCode334();
        int[] nums = {2,1,5,0,4,6};
        System.out.println(leetCode334.increasingTriplet(nums));
    }
}
