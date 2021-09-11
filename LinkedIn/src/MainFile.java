import java.util.Arrays;
import java.util.List;

public class MainFile {
    public static void main(String args[]) {
        //testLC227();
//        testLC983();
//        test367();
       // testLC611();
       // testLC360();
        //testLC254();
       // testLC76();
        //testLC68();
        testLC1235();
    }

    private static void testLC1235() {
        LC1235 lc1235 = new LC1235();
        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit = {50,10,40,70};

        System.out.println(lc1235.jobScheduling(startTime, endTime, profit));
    }

    private static void testLC68() {
        String[] words = {"for", "your", "country"};

        int maxWidth = 16;
        LC68 lc68 = new LC68(words, maxWidth);

        System.out.println(Arrays.toString(lc68.getPrefix(words)));
        List<String> spaces = lc68.getSpaces(9, 2);

        String cur = lc68.fullyJustify(0, 2);
        System.out.println(cur);

        System.out.println(lc68.getRight(6));

        System.out.println(lc68.fullJustify(words, maxWidth));
        //System.out.println(lc68.leftJustify(0, 2));
    }

    private static void testLC76() {
        LC76 lc76 = new LC76();
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println(lc76.minWindow(s, t));
    }

    private static void testLC360() {
        LC360 lc360 = new LC360();

        int[] nums = {-4,-2,2,4};
        int a = -1;
        int b = 3;
        int c = 5;

        System.out.println(lc360.sortTransformedArray(nums,a, b, c));
    }

    private static void testLC254() {
        LC254 lc254 = new LC254();

        int n = 12;
        System.out.println(lc254.getFactors(n));
    }

    public static void testLC611() {
        LC611 lc611 = new LC611();

        int[] nums = {2,2,3,4};
        System.out.println(lc611.triangleNumber(nums));
    }

    private static void test367() {
        LC367 lc367 = new LC367();

        int num = 808201;
        System.out.println(lc367.isPerfectSquare(num));
    }

    private static void testLC636() {
        LC636 lc636 = new LC636();

    }

    private static void testLC983() {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        LC983 lc983 = new LC983();
        System.out.println(lc983.mincostTickets(days, costs));
    }

    private static void testLC227() {
        LC227 lc227 = new LC227();

        String s = "12-3*4";
        int res = lc227.calculate(s);
        System.out.println(res);
    }
}
