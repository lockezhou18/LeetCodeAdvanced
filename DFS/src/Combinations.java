import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(res, new ArrayList<Integer>(), n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int n, int k, int cur) {
        if (cur == n + 1) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        dfs(res, path, n, k, cur + 1);

        path.add(cur);
        dfs(res, path, n, k, cur + 1);
        path.remove(path.size() - 1);

    }
}
