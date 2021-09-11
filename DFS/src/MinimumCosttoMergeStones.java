import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinimumCosttoMergeStones {
    public int mergeStones(int[] stones, int K) {
        return dfs(convert(stones), K);
    }

    private LinkedList<Integer> convert(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        for (Integer num : nums) {
            list.add(num);
        }

        return list;
    }

    //brute force dfs
    private int dfs(LinkedList<Integer> stones, int k) {
        if (stones.size() == 1)
            return stones.get(0);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < stones.size(); i++) {
            List<Integer> removals = suppose(stones, i, k);
            int cost = cal(removals);
            stones.add(i, cost);
            min = Math.min(min, dfs(stones, k) + cost);
            stones.remove(i);
            restore(stones, i, removals);
        }
        return min;
    }

    private int cal(List<Integer> removals) {
        int cost = 0;
        for (Integer removed : removals) {
            cost += removed;
        }

        return cost;
    }

    private List<Integer> suppose(List<Integer> stones, int curIdx, int k) {
        List<Integer> removals = new ArrayList<>();
        while(k-- > 0) {
            removals.add(stones.remove(curIdx));
        }
        return removals;
    }

    private void restore(List<Integer> stones, int curIdx, List<Integer> removals) {
        for (Integer removed : removals) {
            stones.add(curIdx, removed);
        }
    }
}
