public class InorderSuccessorBinaryTree {
    private TreeNode prev = null;
    private boolean flag = false;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { //input, output clarify
        if (p == null)
            throw new IllegalArgumentException("p is null");

        if (flag) //p exists
            return dfs(root, p);

        throw new IllegalArgumentException("p does not exist");
    }

    private TreeNode dfs(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root == p) flag = true; //check if p exists

        TreeNode left = dfs(root.left, p);
        if (left != null)
            return left;

        if (prev == p)
            return root;
        prev = root;

        return dfs(root.right, p);
    }
}
