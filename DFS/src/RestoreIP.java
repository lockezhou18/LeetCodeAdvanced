import java.util.ArrayList;
import java.util.List;

public class RestoreIP {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), s, 0, 0);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String s, int curIdx, int num) {
        if (num == 4) {
            if (curIdx == s.length()) {
                path.setLength(path.length() - 1);
                res.add(path.toString());
            }
            return;
        }

        for (int l = 1; l <= 3; l++) { //length of append
            if (curIdx + l > s.length())
                break;

            int val = Integer.valueOf(s.substring(curIdx, curIdx + l));
            int len = path.length();
            if (val >= 0 && val <= 255) { //valid option, go dfs
                path.append(val + ".");
                dfs(res, path, s, curIdx + l, num + 1);
                path.setLength(len);
            }

            //leading zero
            if (val == 0) break;
        }
    }
}
