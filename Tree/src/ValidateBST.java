public class ValidateBST {
    //Using prev
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, new TreeNode[1]);
    }

    //pass local param
    public boolean isValidBST(TreeNode root, TreeNode[] prev) {
        if (root == null)
            return true;

        if (!isValidBST(root.left, prev))
            return false;

        if (prev[0] != null && root.val <= prev[0].val)
            return false;

        prev[0] = root;

        return isValidBST(root.right, prev);
    }
}
