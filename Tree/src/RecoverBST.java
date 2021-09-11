//第一次invalid的第一个数，第二次invalid的第二个数
public class RecoverBST {
    TreeNode prev = null;
    public void recoverTree(TreeNode root) {
        TreeNode[] memo = new TreeNode[2];
        recoverTree(root, memo);
        swap(memo);
    }

    private void swap(TreeNode[] memo) {
        if (memo[0] != null && memo[1] != null) {
            int temp = memo[0].val;
            memo[0].val = memo[1].val;
            memo[1].val = temp;
        }
    }

    private void recoverTree(TreeNode root, TreeNode[] memo) {
        if (root == null)
            return;

        recoverTree(root.left, memo);
        if (prev != null && root.val <= prev.val) {
            if (memo[0] == null) {
                memo[0] = prev;
            }
            memo[1] = root;
        }
        prev = root;
        recoverTree(root.right, memo);
    }
}
