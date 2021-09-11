import sun.awt.image.ImageWatched;

import java.util.*;

//find optimal min value (bfs/dp) firstly to think if we can use bfs (can be converted to a unweighted graph) or dp
public class WordLadder1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //c.c
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> dict = convertToDict(wordList);
        dict.remove(beginWord);
        int minLength = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String cur = queue.poll();

                List<String> nexts = convert(cur);

                for (String next : nexts) {
                    if (dict.remove(next)) {
                        if (next.equals(endWord))
                            return minLength + 1;
                        queue.offer(next);
                    }
                }
            }
            minLength++;
        }

        throw new RuntimeException("result does not exist.");
    }

    private Set<String> convertToDict(List<String> wordList) {
        Set<String> dict = new HashSet<>();

        for (String word : wordList) {
            dict.add(word);
        }

        return dict;
    }

    private List<String> convert(String cur) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {
            char[] chars = cur.toCharArray();
            for (char c = 'a'; c < 'z'; c++) {
                if (chars[i] != c) {
                    chars[i] = c;
                    res.add(String.valueOf(chars));
                }
            }
        }
        return res;
    }

}
