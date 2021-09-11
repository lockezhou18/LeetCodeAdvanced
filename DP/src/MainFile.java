import java.util.HashSet;
import java.util.Set;

public class MainFile {
    public static void main(String args[]) {
        //testLC486();
        //testPredictTheWinnerFollowUp();
        testLC64();
    }

    private static void testLC64() {
        LC64 lc64 = new LC64();
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        lc64.minPathSum(grid);
    }

    private static void testMethod() {
        Set<Integer> set = new HashSet<>();
    }

    private static void testPredictTheWinnerFollowUp() {
        int[] nums = {1, 2, 100, 3};
        PredictWinFollowUp predictWinFollowUp = new PredictWinFollowUp();
        System.out.println(predictWinFollowUp.PredictTheWinner(nums));
    }
    private static void testLC486() {
        LC486 lc486 = new LC486();
        //int[] testData = {877854,7113184,3270279,2243110,1902970,9268285,8784788,3837608,6582224,8751349,6928223,3108757,1120749,1872910,7762600,4220578,4692740,3409910,6807125,6808582};
        int[] testData = {1, 1};
        System.out.println(lc486.PredictTheWinner(testData));
    }
}
