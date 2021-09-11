import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//dfs 每个点扫一遍 O(V + E) O (n^2)
public class LC547 {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        Map<Integer, List<Integer>> graph = buildGraph(isConnected);
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                count++;
                dfs(graph, visited, i);
            }
        }

        return count;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] isConnected) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    List<Integer> nexts = graph.getOrDefault(i, new ArrayList<>());
                    nexts.add(j);
                    graph.put(i, nexts);

                    nexts = graph.getOrDefault(j, new ArrayList<>());
                    nexts.add(i);
                    graph.put(j, nexts);
                }
            }
        }

        return graph;
    }

    private void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int cur) {
        if (visited[cur])
            return;

        visited[cur] = true;

        List<Integer> nexts = graph.get(cur);
        for (Integer next : nexts) {
            dfs(graph, visited, next);
        }
    }
}
