import java.util.ArrayList;
import java.util.List;

public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        //dfs(res, nums, new ArrayList<Integer>(), 0);
        dfsForFormat(res, nums, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> path, int curIdx) {
        if (curIdx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
            //do not pick the current element
            dfs(res, nums, path, curIdx + 1);

            //pick the current element
            path.add(nums[curIdx]);
            dfs(res, nums, path, curIdx + 1);
            path.remove(path.size() - 1);

    }

    private void dfsForFormat(List<List<Integer>> res, int[] nums, List<Integer> path, int curIdx) {
        if (curIdx == nums.length)
            return;

        for (int i = curIdx; i < nums.length; i++) {
            path.add(nums[i]);
            res.add(new ArrayList<>(path));
            dfsForFormat(res, nums, path, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
