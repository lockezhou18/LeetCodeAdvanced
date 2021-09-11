import java.util.LinkedList;
import java.util.Queue;

//CSP -> 2 colorable graph
//1. bfs : neighbored isColored && same color --> false
//2. backtracking m color
public class BipartiteGraph {
    Queue<Integer> queue;
    int[] visited;
    int graph[][];

    public boolean isBipartite(int[][] graph) {
        this.queue = new LinkedList<>();
        this.visited = new int[graph.length];//0 -> unvisited, 1 -> partition 1, 2 -> partition 2
        this.graph = graph;
        int V = graph.length;
        //here we assume it is a connected graph, but sometimes it is not,
        // so we need to find the first vertex of the connected subgraph

        for (int v = 0; v < V; v++) {
            if (visited[v] == 0) {
                queue.offer(v);
                visited[v] = 1;
                boolean flag = bfs();

                if (!flag)
                    return false;
            }
        }
        return true; //all the nodes (v belongs to V) are assigned value
    }


    private boolean bfs() {
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();
                int[] nexts = graph[cur];

                if (visited[cur] == 0)
                    visited[cur] = 1;

                for (Integer next : nexts) {
                    if (visited[next] == 0) { //unvisited
                        queue.offer(next);
                        visited[next] = visited[cur] == 1 ? 2 : 1;
                        continue;
                    }

                    if (visited[next] == visited[cur])
                        return false;
                }
            }
        }
        return true;
    }
}

//    private Queue<Integer> putFirst(Queue<Integer> queue, int[][] graph, int[] visited) {
//        for (int i = 0; i < graph.length; i++) {
//            if (graph[i].length > 0) {
//                queue.offer(i);
//                visited[i] = 1;
//                break;
//            }
//        }
//    }

