import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

public class ShareBids {
    private static final int USERID_IDX = 0;
    private static final int SHARES_IDX = 1;
    private static final int PRICE_IDX = 2;
    private static final int TIMESTAMP_IDX = 3;
    public List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        List<Integer> res = new ArrayList<>();
        //c.c
        if (bids.size() == 0 || totalShares == 0)
            return res;

        Collections.sort(bids, (bid1, bid2) -> {
            if (bid1.get(PRICE_IDX) != bid2.get(PRICE_IDX)) {
                return bid2.get(PRICE_IDX) - bid1.get(PRICE_IDX);
            } else {
                return bid1.get(TIMESTAMP_IDX) - bid2.get(TIMESTAMP_IDX);
            }
        });

        int left = 0;
        while (left < bids.size()) {
            List<Integer> curBid = bids.get(left);
            if (totalShares <= 0) {
                res.add(curBid.get(USERID_IDX));
                left++;
                continue;
            }

            //round robin
            int size = 0;
            int totalSharesNeeded = 0;
            int right = left;
            while (right < bids.size()) {
                List<Integer> nextBid = bids.get(right);
                if (!(nextBid.get(PRICE_IDX) == curBid.get(PRICE_IDX)))
                    break;

                size++;
                totalSharesNeeded += nextBid.get(SHARES_IDX);
                right++;
            }

            //check if cur totalShares satisfying
            if (size <= totalShares) {
                totalShares = Math.max(0, totalShares - totalSharesNeeded);
            } else {
                int idx = left + totalShares;
                addRes(res, idx, right - 1, bids); //users of [idx, right - 1] not allocated
            }
            left = right;
        }

        Collections.sort(res);
        return res;
    }

    private void addRes(List<Integer> res, int start, int end, List<List<Integer>> bids) {
        for (int i = start; i <= end; i++) {
            res.add(bids.get(i).get(USERID_IDX));
        }
    }
}
