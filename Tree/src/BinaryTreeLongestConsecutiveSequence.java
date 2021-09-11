public class BinaryTreeLongestConsecutiveSequence {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) { //关键是返回什么信息，以当前root为终点的最长递增序列是多少
        if (root == null)
            return 0;

        int lftLns = dfs(root.left);
        int rghtLns = dfs(root.right);

        int crLns = 1; //at least 1

        //当左右取大，且左右不确定是否存在（树的大多数情况）， 用对左右子树分别更新max的写法，或许更formal和好理解一点
        if (root.left != null && root.val + 1 == root.left.val) {
            crLns = Math.max(lftLns + 1, crLns);
        }

        if (root.right != null && root.val + 1 == root.right.val) {
            crLns = Math.max(rghtLns + 1, crLns);
        }

        max = Math.max(max, crLns);
        return crLns;
    }
}
