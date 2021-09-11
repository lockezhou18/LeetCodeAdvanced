import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1136 {
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = buildGraph(n, relations);
        int max = 1;
        for (int i = 1; i < n + 1; i++) {
            int curMax = dfs(graph, i, new int[n + 1]);
            if (curMax == -1) //detected a cycle
                return -1;

            max = Math.max(max, curMax);
        }

        return max;
    }

    private Map<Integer, List<Integer>> buildGraph(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) { //逻辑上来讲，given n个node，这n个node都需要再graph当中。
            graph.put(i, new ArrayList<>());
        }
        for (int[] relation : relations) {
            int from = relation[0];
            int to = relation[1];

            graph.get(from).add(to);
        }

        return graph;
    }

    private int dfs(Map<Integer, List<Integer>> graph, int cur, int[] status) {
        if (status[cur] == -1) //visiting
            return -1;

        if (status[cur] > -1)
            return status[cur];

        status[cur] = -1; //mark as visiting
        int curMax = 1;

        List<Integer> nexts = graph.get(cur);
        for (Integer next : nexts) {
            int nextMax = dfs(graph, next, status);
            if (nextMax == -1)
                return -1;

            curMax = Math.max(curMax, nextMax + 1);
        }

        status[cur] = curMax;
        return curMax;
    }
}
