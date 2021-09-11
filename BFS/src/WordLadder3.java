import java.util.*;

//find any path
//Record one edge when running bfs (next -> cur)
//while loop to recover from end vertex since we only need 1 path
public class WordLadder3 {
    public List<String> findLadders(String beginWord, String endWord, Set<String> dict) {
        //c.c

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dict.remove(beginWord);

        Map<String, String> graph = new HashMap<>();
        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur);

                for (String next : nexts) {
                    if (dict.remove(next)) {
                        graph.put(next, cur);
                        if (next.equals(endWord)) {
                            return recoverPath(endWord, beginWord, graph);
                        }
                        queue.offer(next);
                    }
                }
            }
        }
        throw new RuntimeException("answer does not exist");
    }

    private List<String> convert(String cur) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < cur.length(); i++) {
            char[] chars = cur.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                if (chars[i] != c) {
                    chars[i] = c;
                    res.add(String.valueOf(chars));
                }
            }
        }
        return res;
    }

    private List<String> recoverPath(String beginWord, String endWord, Map<String, String> graph) {
        List<String> res = new LinkedList<>();

        String cur = beginWord;

        while(cur != null) {
            res.add(0, cur);
            if (cur.equals(endWord))
                return res;
            cur = graph.get(cur);
        }

        throw new RuntimeException("Ans does not exist");
    }
}
