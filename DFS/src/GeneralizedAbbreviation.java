import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    int lastLen;
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        //dfs(res, new StringBuilder(), word, 0, 0);
        generateAbbreviations(word, 0, new StringBuilder(), res, false);

        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String word, int curIdx, int count) { //last value peek 的方式在这里是不合适的，不好判断，
        //用count来记录出现数字的个数，当要keep时转化
        int len = path.length();
        if (curIdx == word.length()) {
            if (count > 0)
                path.append(count);

            res.add(path.toString());
            path.setLength(len);
            return;
        }

        char c = word.charAt(curIdx);

        //keep
        if (count > 0) {
            path.append(count);
        }
        path.append(c);
        dfs(res, path, word, curIdx + 1, 0);
        path.setLength(len);

        //replace
        dfs(res, path, word, curIdx + 1, count + 1);
        //replace
//        if (lastVal > 0) {
//            path.setLength(lastLen); //remove last integer from path
//            path.append(lastVal + 1);
//            dfs(res, path, word, curIdx + 1, lastVal + 1);
//            path.setLength(len);
//        } else {
//            path.append(1);
//            dfs(res, path, word, curIdx + 1, 1);
//            path.setLength(len);
//        }
    }

    private void generateAbbreviations(String word, int curIdx, StringBuilder path, List<String> res, boolean tranfered) {
        if (curIdx == word.length()) {
            res.add(path.toString());
            return;
        }
        int len = path.length();
        //1. keep
        path.append(word.charAt(curIdx));
        generateAbbreviations(word, curIdx + 1, path, res, false);
        path.setLength(len);

        //2. transfer to word
        for (int j = curIdx; j < word.length(); j++) {
            int transferLen = j - curIdx + 1;
            if (!tranfered) {
                path.append(transferLen);
                generateAbbreviations(word, j + 1, path, res, true);
                path.setLength(len);
            }
        }
    }
}
