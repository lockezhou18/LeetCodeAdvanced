//cur.val == p.val, go right since successor should be > cur
//cur.val < p.val go right, same reason as above
//cur.val > p.val, cur can be candidate, and go left to see if there is a better one
public class LC285 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        TreeNode cur = root;
        TreeNode candidate = null;
        while (cur != null) {
            if (cur.val > p.val) {
                candidate = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return candidate;
    }
}
