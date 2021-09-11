import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//分为两步：
//1. 确定最少删除几个左，几个右，用delta的概念
//2. dfs查找所有的可能性
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        int[] minNum = findMinRemoveNum(s);
        Set<String> resSet = new HashSet<>();
        List<String> resList = new ArrayList<>();
        //dfs(res, new StringBuilder(), s, 0, minNum[0], minNum[1], 0);
        removeDupdfs(resList, new StringBuilder(), s, 0, minNum[0], minNum[1], 0);
        return resList;
    }

    public int[] findMinRemoveNum(String s) {
        int rml = 0;
        int rmr = 0;

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == '(') {
                rml++;
            }

            if (cur == ')') {
                if (rml > 0) {
                    rml--;
                } else if (rml <= 0) {
                    rmr++;
                }
            }
        }

        return new int[]{rml, rmr};
    }

    private void dfs(Set<String> res, StringBuilder path, String s, int curIdx, int rml, int rmr, int delta) { //这里keep delta是为了保证是valid的括号序列
        if (curIdx == s.length() && delta == 0 && rml == 0 && rmr == 0) {
            res.add(path.toString());
            return;
        }

        if (curIdx > s.length() || delta < 0 || rml < 0 || rmr < 0)
            return;

        char cur = s.charAt(curIdx);
        int len = path.length();
        if (cur == '(') {
            //remove, 直接走下去, 相当于在新的拼接path里不加入
            dfs(res, path, s, curIdx + 1, rml - 1, rmr, delta);

            //keep
            path.append("(");
            dfs(res, path, s, curIdx + 1, rml, rmr, delta + 1);
            path.setLength(len);
        } else if (cur == ')') {
            //remove
            dfs(res, path, s, curIdx + 1, rml, rmr - 1, delta);

            path.append(")");
            dfs(res, path, s, curIdx + 1, rml, rmr, delta - 1);
            path.setLength(len);
        } else { //字母，只能keep
            path.append(cur);
            dfs(res, path, s, curIdx + 1, rml, rmr, delta);
            path.setLength(len);
        }
    }

    private void removeDupdfs(List<String> res, StringBuilder path, String s, int curIdx, int rml, int rmr, int delta) {
        if (curIdx == s.length() && delta == 0 && rml == 0 && rmr == 0) {
            res.add(path.toString());
            return;
        }

        if (curIdx > s.length() || delta < 0 || rml < 0 || rmr < 0)
            return;

        char cur = s.charAt(curIdx);
        int len = path.length();
        if (cur == '(') {
            //remove, 直接走下去, 相当于在新的拼接path里不加入
            removeDupdfs(res, path, s, curIdx + 1, rml - 1, rmr, delta);

            //keep
            int i = curIdx;
            while(i < s.length() && s.charAt(i) == '(') i++;
            path.append(s.substring(curIdx, i));
            removeDupdfs(res, path, s, i, rml, rmr, delta + i - curIdx);
            path.setLength(len);
        } else if (cur == ')') {
            //remove
            removeDupdfs(res, path, s, curIdx + 1, rml, rmr - 1, delta);

            int i = curIdx;
            while(i < s.length() && s.charAt(i) == ')') i++;
            path.append(s.substring(curIdx, i));
            removeDupdfs(res, path, s, i, rml, rmr, delta - i + curIdx);
            path.setLength(len);
        } else { //字母，只能keep
            path.append(cur);
            removeDupdfs(res, path, s, curIdx + 1, rml, rmr, delta);
            path.setLength(len);
        }
    }
}
