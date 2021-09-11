import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), k, n, 1,0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int k, int n, int sum, int curNumber) {
        if (curNumber < 10 && path.size() == k && sum == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (curNumber == 10)
            return;

        dfs(res, path, k, n, sum, curNumber + 1);

        path.add(curNumber);
        dfs(res, path, k, n, sum + curNumber, curNumber + 1);
        path.remove(path.size() - 1);
    }
}
