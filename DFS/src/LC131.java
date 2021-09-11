import java.util.ArrayList;
import java.util.List;

public class LC131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;

    }

    private void dfs(String s, int curIdx, List<String> path, List<List<String>> res) {
        if (curIdx == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = curIdx; i < s.length(); i++) {
            if (isPalidrome(s, curIdx, i)) {
                path.add(s.substring(curIdx, i + 1));
                dfs(s, i + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalidrome(String s, int curIdx, int i) {
        while (curIdx < i) {
            if (s.charAt(curIdx) != s.charAt(i))
                return false;

            curIdx++;
            i--;
        }

        return true;
    }
}
