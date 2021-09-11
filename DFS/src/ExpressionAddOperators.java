import java.util.ArrayList;
import java.util.List;

//这里我们要evaluaate +-*的算术表达式，扫一遍即可，引入val， lastVal去帮助运算
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, num, target, new StringBuilder(), 0, 0, 0);
        return res;
    }

    private void dfs(List<String> res, String num, int target, StringBuilder path, int curIdx, long val, long lastVal) { //这里相当于把eval时候while循环转化为recusion的参数
        if (curIdx == num.length()) {
            if (val == target) {
                res.add(path.toString());
            }
            return;
        }

        long curVal = 0; //这里还有传入的val， lastVal都用long，即使传入的值是integer，但我们要进行运算，有可能越界导致错误
        for (int i = curIdx; i < num.length(); i++) {
            curVal = curVal * 10 + Integer.valueOf(num.charAt(i) - '0'); //这里我们拼数，因为要转化成数字，直接substring可能会导致错误，用十进制的方法
            //different possibilites:
            int len = path.length();
            if (curIdx == 0) {
                path.append(curVal);
                dfs(res, num, target, path, i + 1, curVal, curVal);
                path.setLength(len);
                if (curVal == 0) break;
                continue;
            }

            //1. +
            path.append("+" + curVal);
            dfs(res, num, target, path, i + 1, val + curVal, curVal);
            path.setLength(len);

            //2. -
            path.append("-" + curVal);
            dfs(res, num, target, path, i + 1, val - curVal, -1 * curVal);
            path.setLength(len);

            //3. *
            path.append("*" + curVal);
            dfs(res, num, target, path, i + 1, (val - lastVal) + (lastVal * curVal), lastVal * curVal);
            path.setLength(len);

            if (curVal == 0) break; //leading zeros
        }
    }
}
