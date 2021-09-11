import java.util.*;

//tree - 无环联通图. 有向无向无关
//cycle - 只有第一个和最后一个顶点重复的trail a - b不是有一个环
//如果给了一个图，clarify 有环无环，有向无向，连通非连通
public class LC261 {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = convert(n, edges);
        Set<Integer> visited = new HashSet();
        return dfs(0, graph, visited, -1) && visited.size() == n;
    }

    //convert to graph standard way
    //1. initial all nodes
    //2. add edges
    private Map<Integer, List<Integer>> convert(int n, int[][] edges) {
        //initial nodes
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        //add edges
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

        }
        return graph;
    }

    //除去parent外，一个点被访问多次，意味着入度不为1，有环
    private boolean dfs(int cur, Map<Integer, List<Integer>> graph, Set<Integer> visited, int parent) {
        if (visited.contains(cur))
            return false;

        visited.add(cur);
        List<Integer> edges = graph.get(cur);

        for (Integer next : edges) {
            if (next == parent)
                continue;

            if (!dfs(next, graph, visited, cur))
                return false;
        }

        return true;
    }
}
