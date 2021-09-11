import java.util.LinkedList;
import java.util.Queue;

public class LeetCode117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                Node cur = queue.poll();

                if (size >= 1) {
                    cur.next = queue.peek();
                }

                if (cur.left != null)
                    queue.offer(cur.left);

                if (cur.right != null)
                    queue.offer(cur.right);
            }
        }

        return root;
    }
}
