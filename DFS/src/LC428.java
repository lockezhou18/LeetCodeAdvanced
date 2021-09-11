import java.util.*;

//searilzie and deserailize n ary tree. Using preorder ser and deseral
public class LC428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    int id;

    // Encodes a tree to a single string.
    public String serialize(Node root) {
       if (root == null)
           return "null";
       StringBuilder sb = new StringBuilder();
       this.dfs(root, sb);
       return sb.toString();
    }

    private String dfs(Node root) {
        if (root == null)
            return new String("null");

        StringBuilder sb = new StringBuilder();
        sb.append(root.val + "-" + root.children.size() + ",");
        for (Node child : root.children) { //这里如果child为空，也就是没有子节点，不进入，因此，不会serialize null
            sb.append(dfs(child));
        }

        return sb.toString();
    }

    private String dfs(Node root, StringBuilder path) {
        int len = path.length();
        path.append(root.val + "-" + root.children.size() + ",");
        for (Node child : root.children) { //这里如果child为空，也就是没有子节点，不进入，因此，不会serialize null
            path.append(dfs(child, path));
        }
        String res = path.toString();
        return res;
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.equals("null"))
            return null;
        String[] ss = data.split(",");
        return deserialize(ss);
    }

    private Node deserialize(String[] ss) {
        String array[] = ss[id++].split("-");
        int len = Integer.valueOf(array[1]);

        List<Node> children = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            children.add(deserialize(ss));
        }

        return new Node(Integer.valueOf(array[0]), children);
    }
}
