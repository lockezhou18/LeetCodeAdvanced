import java.util.*;

/*
1. Implementation of Dijkstra, Using LC1514 as test cases -- passed
2. generate global nodes, all the data structures using the same nodes
3. Implements 2 ways for representing weighted graph:
    1. HashMap<Node, Map<Node, Weight>>
    2. Map<Node, Weight> Node.nexts;
*/

public class Dijkstra {
    class Node implements Comparable<Node> {
        int id;
        double prob; //represent the prob from start to this node
        Map<Node, Double> nexts;

        public Node(int id) {
            this.id = id;
            this.prob = Double.MIN_VALUE;
            nexts = new HashMap<>();
        }
        public Node(int id, int prob) {
            this.id = id;
            this.prob = prob;
        }

        @Override
        public int compareTo(Node that) { //max heap
            return -Double.compare(this.prob, that.prob);
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<Node> nodes = generateNodes(n);
        //Map<Node, Map<Node, Double>> graph = convert(n, edges, succProb, nodes);
        updateGraph(nodes, edges, succProb);
        Node startNode = nodes.get(start);
        Node endNode = nodes.get(end);
        startNode.prob = 1;
//        return this.findMaxByDijstra(graph, startNode, endNode);
        return this.findMaxByDijstra(startNode, endNode);
    }

    private void updateGraph(List<Node> nodes, int[][] edges, double[] succProb) {
        for (int i = 0; i < edges.length; i++) {
            Node from = nodes.get(edges[i][0]);
            Node to = nodes.get(edges[i][1]);
            Map<Node, Double> nexts = from.nexts;
            nexts.put(to, succProb[i]);
            nexts = to.nexts;
            nexts.put(from, succProb[i]);
        }
    }

    public Map<Node, Map<Node, Double>> convert(int n, int[][] edges, double[] succProb, List<Node> nodes) {
        //weighted graph, using Map<Node, Map<Node, weight>> to present
        Map<Node, Map<Node, Double>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            Node start = nodes.get(edges[i][0]);
            Node end = nodes.get(edges[i][1]);
            updateGraph(start, end, succProb[i], graph);
            updateGraph(end, start, succProb[i], graph);
        }
        return graph;
    }

    private List<Node> generateNodes(int n) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Node(i));
        }

        return list;
    }

    private void updateGraph(Node from, Node to, double weight,
                             Map<Node, Map<Node, Double>> graph) {
        Map<Node, Double> nexts = graph.getOrDefault(from, new HashMap<>());
        nexts.put(to, weight);
        graph.put(from, nexts);
    }

    private double findMaxByDijstra(Node start, Node end) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();

        heap.offer(start);

        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            visited.add(cur); //put it in visited
            if (cur == end)
                return cur.prob;
            Map<Node, Double> nexts = cur.nexts;
            for (Map.Entry<Node, Double> next: nexts.entrySet()) {
                Node nextNode = next.getKey();
                double weight = next.getValue();
                if (!visited.contains(nextNode)) {
                    if (cur.prob * weight > nextNode.prob) {
                        nextNode.prob = cur.prob * weight; //update weight from start to next node
                        decreaseKey(nextNode, heap);
                    }
                }
            }
        }
        return 0;
    }

    private double findMaxByDijstra(Map<Node, Map<Node, Double>> graph, Node start, Node end) {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        Set<Node> visited = new HashSet<>();

        heap.offer(start);

        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            visited.add(cur); //put it in visited
            if (cur == end)
                return cur.prob;
            Map<Node, Double> nexts = graph.getOrDefault(cur, new HashMap<>());
            for (Map.Entry<Node, Double> next: nexts.entrySet()) {
                Node nextNode = next.getKey();
                double weight = next.getValue();
                if (!visited.contains(nextNode)) {
                    if (cur.prob * weight > nextNode.prob) {
                        nextNode.prob = cur.prob * weight; //update weight from start to next node
                        decreaseKey(nextNode, heap);
                    }
                }
            }
        }
        return 0;
    }

    private void decreaseKey(Node node, PriorityQueue<Node> heap) {
        heap.remove(node);
        heap.offer(node);
    }
}
