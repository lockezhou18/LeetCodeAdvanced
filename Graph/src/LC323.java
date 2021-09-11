import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//standard way to count connected components in a graph
public class LC323 {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = convert(n, edges);
        Boolean[] visited = new Boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, graph, visited);
            }
        }
        return count;
    }

    private Map<Integer, List<Integer>> convert(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    private void dfs(int cur, Map<Integer, List<Integer>> graph, Boolean[] visited) {
        if (visited[cur])
            return;

        visited[cur] = true;
        List<Integer> nexts = graph.get(cur);

        for (Integer next : nexts) {
            dfs(next, graph, visited);
        }
    }
}
