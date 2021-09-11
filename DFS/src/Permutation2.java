import java.util.*;

public class Permutation2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new ArrayList<>(), nums, 0, new HashSet<>());
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int position, Set<Integer> visited) {
        if (position == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited.contains(nums[i])) {
                path.add(nums[i]);
                visited.add(nums[i]);
                dfs(res, path, nums, position + 1, visited);
                path.remove(path.size() - 1);
                visited.remove(nums[i]);
            }
        }
    }
}
