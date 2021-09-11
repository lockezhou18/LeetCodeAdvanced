import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class SewingSystem {
    int min = Integer.MAX_VALUE;
    class TreeNode {
        int val = 0;
        int sum = 0;
        List<TreeNode> children = new ArrayList<>();
        TreeNode left;
        TreeNode right;
        public TreeNode() {

        }
        public TreeNode(int val, int sum, List<TreeNode> children) {
            this.val = val;
            this.sum = sum;
            this.children = children;
        }

        public TreeNode(int val, int sum, TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }
    }

    public int minDiff(int[] parents, int[] nums) {
        if (parents == null || parents.length == 0
            || nums == null || nums.length == 0)
            return 0;

        TreeNode root = buildTree(parents, nums);
        if (root.children.size() == 0)
            throw new IllegalArgumentException("NOT ABLE TO CUT SINCE ONLY ONE NODE");

        subTreeSum(root);
        findMinCut(root);

        return this.min;
    }

    private List<TreeNode> initial(int size) {
        List<TreeNode> treeNodes = new ArrayList<>();
        while (size-- > 0) {
            treeNodes.add(new TreeNode());
        }
        return treeNodes;
    }

    private TreeNode buildTree(int[] parents, int[] nums) {
        List<TreeNode> treeNodes = initial(nums.length);
        TreeNode root = null;
        for (int i = 0; i < parents.length; i++) {
            TreeNode cur = treeNodes.get(i);
            cur.val = nums[i];
            if (parents[i] == -1) {
                root = cur;
                continue;
            }

            TreeNode parent = treeNodes.get(parents[i]);
            parent.children.add(cur);
        }

        return root;
    }

    //traverse tree, calculate subtree sum
    private void subTreeSum(TreeNode root) {
        if (root == null)
            return;

        int sum = 0;
        for (TreeNode next : root.children) {
            subTreeSum(next);
            sum += next.sum;
        }

        root.sum = sum + root.val;
    }

    private void findMinCut(TreeNode root) {
        if (root == null)
            return;

        for (TreeNode next : root.children) {
            findMinCut(next);
            int diff = root.sum - next.sum;
            min = Math.min(min, Math.abs(diff - next.sum));

        }
    }
}
