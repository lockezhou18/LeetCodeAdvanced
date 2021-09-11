/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class LargestSizeBST {
    int max = 0;
    public class Result {
        int max;
        int min;
        int size;
        boolean isBST;

        public Result(int max, int min, int size, boolean isBST) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return max;
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, 0, 0, true);
        }

        Result lftRes = dfs(root.left);
        Result rghtRes = dfs(root.right);

        if (!lftRes.isBST || !rghtRes.isBST) {
            return new Result(0, 0, 0, false);
        }

        //both left subtree and right subtree are bst, now we just need to check if the cur val > left.max && cur val < right.min
        //Here we also need to consider the leaf node edge case, so we check if size is 0
        if ((lftRes.size == 0 || root.val > lftRes.max) && (rghtRes.size == 0 || root.val < rghtRes.min)) {
            int curSize = lftRes.size + rghtRes.size + 1;
            int curMin = lftRes.size == 0 ? root.val : lftRes.min;
            int curMax = rghtRes.size == 0 ? root.val : rghtRes.max;

            max = Math.max(curSize, max);
            return new Result(curMax, curMin, curSize, true);
        }

        return new Result(0, 0, 0, false);
    }
}