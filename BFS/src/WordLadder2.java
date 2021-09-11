import java.util.*;

//find all paths with optimal value
//record edges when doing bfs search by using a hashmap. <next, List<>(cur)>
// since we want to have all the possible paths, so here we should record all the nebhors
// It equals we get a graph. Then running dfs on the graph to recover all the paths.
//from endword -> begin to ensure 100% utiliztion rate.
public class WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //c.c

        Queue<String> queue = new LinkedList<>();
        Set<String> dict = convertToSet(wordList);
        Map<String, List<String>> graph = new HashMap<>();
        queue.offer(beginWord);
        dict.remove(beginWord);

        boolean flag = false;
        while(!queue.isEmpty()) {
            int size = queue.size();
            Set<String> curLevelVisited = new HashSet<>();
            while(size-- > 0) {
                String cur = queue.poll();
                List<String> nexts = convert(cur);

                for (String next : nexts) {
                    if (dict.contains(next)) {
                        if (next.equals(endWord)) {
                            flag = true;
                        }
                        if (!curLevelVisited.contains(next)) {
                            queue.offer(next);
                            curLevelVisited.add(next);
                        }
                        List<String> list = graph.getOrDefault(next, new ArrayList<String>());
                        list.add(cur); graph.put(next, list);
                    }
                }
            }
            dict.removeAll(curLevelVisited); //一层之后才将本层的全部删掉
            if (flag) {
                break;
            }
        }

        List<List<String>> res = new ArrayList<>();
        List<String> path = new LinkedList<>();
        path.add(endWord);
        dfs(res, path, graph, endWord, beginWord);

        return res;
    }

    private void dfs(List<List<String>> res, List<String> path, Map<String, List<String>> graph,
                     String cur, String endWord) {
        if (cur.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        List<String> nexts = graph.get(cur);

        if (nexts == null)
            return;

        for (String next : nexts) {
            path.add(0, next);
            dfs(res, path, graph, next, endWord);
            path.remove(path.size() - 1);
        }
    }
    private List<String> convert(String cur) {
        List<String> nexts = new LinkedList<>();

        for (int i = 0; i < cur.length(); i++) {
            char[] chars = cur.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != chars[i]) {
                    chars[i] = c;
                    nexts.add(String.valueOf(chars));
                }
            }
        }
        return nexts;
    }

    private Set<String> convertToSet(List<String> wordList) {
        Set<String> set = new HashSet<>();

        for (String word : wordList) {
            set.add(word);
        }

        return set;
    }
}
