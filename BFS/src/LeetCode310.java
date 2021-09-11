import java.util.*;

public class LeetCode310 {
    int min = Integer.MAX_VALUE;
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        //c.c

        //1. build graph
        Map<Integer, List<Integer>> graph = convertToGraph(edges);
        //2. run bfs on each to calculate height of each node
        int[] heights = new int[n];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = findMinHeightTree(i, graph);
        }

        //post processing, find the min height
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == min) {
                res.add(i);
            }
        }
        return res;
    }

    private int findMinHeightTree(int i, Map<Integer, List<Integer>> graph) { //need to check visited
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        Set<Integer> visited = new HashSet<>();
        visited.add(i);

        int minLength = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                List<Integer> nexts = graph.get(cur);

                for (Integer next : nexts) {
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            minLength++;
        }
        min = Math.min(min, minLength);
        return minLength;
    }

    private Map<Integer, List<Integer>> convertToGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            List<Integer> nexts = graph.getOrDefault(from, new ArrayList<>());
            nexts.add(to);
            graph.put(from, nexts);

            nexts = graph.getOrDefault(to, new ArrayList<>());
            nexts.add(from);
            graph.put(to, nexts);
        }

        return graph;
    }
}
