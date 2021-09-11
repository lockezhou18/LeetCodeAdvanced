import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        wordBreak(s, new HashSet<>(wordDict), 0, res, new StringBuilder());
        return res;
    }

    private void wordBreak(String s, Set<String> wordDict, int curIdx, List<String> res, StringBuilder path) {
        if (curIdx == s.length()) { //enmutate all the states
            path.setLength(path.length() - 1);
            res.add(path.toString());
            return;
        }

        int len = path.length();
        for (int i = curIdx + 1; i <= s.length(); i++) {
            String curWord = s.substring(curIdx, i);
            if (wordDict.contains(curWord)) {
                path.append(curWord + " ");
                wordBreak(s, wordDict, i, res, path);
                path.setLength(len);
            }
        }
    }
}
