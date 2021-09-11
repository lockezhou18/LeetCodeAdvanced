import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {

        return dfs(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean dfs(String s, Set<String> wordDict, int curIdx, Boolean memo[]) {
        if (curIdx == s.length()) {
            return true;
        }
        if (memo[curIdx] != null)
            return memo[curIdx];

        for (int i = curIdx; i < s.length(); i++) {
            String curWord = s.substring(curIdx, i + 1);
            if (wordDict.remove(curWord)) {
                    boolean flag = dfs(s, wordDict,i + 1, memo);
                    if (flag) {
                        memo[curIdx] = true;
                        return true;
                    }
                    wordDict.add(curWord);
            }
        }

        memo[curIdx] = false;
        return false;
    }

    private boolean eval(Set<String> wordDict, String path) {
        String[] words = path.split(",");
        for (String word : words) {
            if (!wordDict.contains(word))
                return false;
        }

        return true;
    }

//    private boolean wordBreakDp(String s, Set<String> wordDict) {
//
//    }
}
