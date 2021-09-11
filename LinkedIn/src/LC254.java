import java.util.LinkedList;
import java.util.List;

public class LC254 {
    public List<List<Integer>> getFactors(int n) {
        return dfs(n, 2);
    }

    private List<List<Integer>> dfs(int n, int start) {

        List<List<Integer>> res = new LinkedList<>();
        int upper = (int)Math.sqrt(n);
        for (int k = start; k <= upper; k++) {
            if (n % k != 0)
                continue;

            int number = n / k;
            LinkedList<Integer> cur = new LinkedList<>();
            cur.add(k);
            cur.add(number);
            res.add(cur);
            List<List<Integer>> nexts = dfs(number, k);

            for (List<Integer> next : nexts) {
                next.add(0, k);
                res.add(next);
            }
        }

        return res;
    }

}
