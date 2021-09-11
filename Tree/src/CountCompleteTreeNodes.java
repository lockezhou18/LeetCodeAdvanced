//超过中线，左full tree， 又complete tree
//不超过，左complete tree， 又full tree （少一层）
//以高度判断是否超过中间线（complete tree）
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) { //left is full tree, right is complete tree
            return 1 + fullNum(leftHeight) + dfs(root.right);
        } else {
            return 1 + fullNum(rightHeight) + dfs(root.left);
        }
    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private int fullNum(int num) {
        return (int) Math.pow(2, num) - 1;
    }
}
