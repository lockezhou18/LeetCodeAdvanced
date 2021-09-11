import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFile {
    public static void main(String args[]) {
        ShareBids shareBids = new ShareBids();
        int totalShares = 3;
        List<List<Integer>> bidders = new ArrayList<>();
        bidders.add(Arrays.asList(1, 2, 5, 0));
        bidders.add(Arrays.asList(2, 1, 4, 2));
        bidders.add(Arrays.asList(3, 2, 4, 6));
//        bidders.add(Arrays.asList(4, 5, 4, 8));

        System.out.println(shareBids.getUnallottedUsers(bidders, totalShares));

        //testSewing();
    }

    private static void testSewing() {
        int[] parents = { -1, 0, 1, 2};
        int[] nums = { 1, 4, 3, 4};
        SewingSystem sewingSystem = new SewingSystem();
        System.out.println(sewingSystem.minDiff(parents, nums));
    }
}
