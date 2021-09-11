import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, new StringBuilder(), res, 0);

        return res;
    }

    private void dfs(int n, StringBuilder path, List<String> res, int delta) {
        //success case
        if (path.length() == 2 * n) { //end state use length()
            if (delta == 0) {
                res.add(path.toString());
            }
            return;
        }

        //failure case
        if (delta < 0)
            return;

        int length = path.length();
        //append left '('
        path.append("(");
        dfs(n, path, res, delta + 1);
        path.setLength(length);

        //append right ')'
        path.append(")");
        dfs(n, path, res, delta - 1);
        path.setLength(length);
    }
}
