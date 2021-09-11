import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//BFS get min distance, 8 directions, graph, visited to dedup
class Solution {
    class Node {
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode(){
            return (23 * 31 + x) * 31 + y;
        }

        public boolean equals(Object that) {
            if (!(that instanceof Node))
                return false;

            Node thatNode = (Node) that;
            return this.x ==  thatNode.x && this.y == thatNode.y;
        }
    }
    int[][] DIRECTIONS = {{2, 1}, {1, 2}, {-2, 1}, {2, -1}, {-2, -1}, {-1, 2}, {1, -2}, {-1, -2}};
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0)
            return 0;

        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0);
        queue.offer(start);

        Set<Node> set = new HashSet<>();
        set.add(start);

        int minLen = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Node cur = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int ii = cur.x + direction[0];
                    int jj = cur.y + direction[1];
                    if (ii == x && jj == y)
                        return minLen;
                    Node next = new Node(ii, jj);
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
            minLen++;
        }
        throw new IllegalArgumentException();
    }
}
