import java.util.*;

public class CombinationSum1and2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, 0, res, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] candidates, int target, int curSum, int curIdx, List<List<Integer>> res, List<Integer> curRes) {
        //success case
        if (curSum == target) {
            res.add(new ArrayList<>(curRes));
            return;
        }

        //failure case
        if (curSum > target || curIdx >= candidates.length)
            return;

        for (int i = curIdx; i < candidates.length; i++) {
            curRes.add(candidates[i]);
            dfs(candidates, target, curSum + candidates[i], i + 1, res, curRes);
            curRes.remove(curRes.size() - 1);

            //have duplicates, in order to avoid the dupilicates, move to the next differnt element
            while(i + 1 < candidates.length && candidates[i] == candidates[i + 1]) i++;
        }
    }
}
