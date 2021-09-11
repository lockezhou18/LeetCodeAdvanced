import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, new StringBuilder(), res);
        return res;

    }

    private void dfs(TreeNode root, StringBuilder path, List<String> res) {
        if (root == null)
            return;

        int len = path.length();
        if (root.left == null && root.right == null) {
            path.append(root.val);
            res.add(path.toString());
            path.setLength(len);
            return;
        }

        path.append(root.val + "->");
        dfs(root.left, path, res);
        dfs(root.right, path, res);
        path.setLength(len);
    }

}
