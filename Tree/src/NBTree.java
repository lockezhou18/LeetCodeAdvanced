import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//NB tree is kind of tree with 0 or 2 child nodes for each node.
// Given n represent number of leaf nodes, find all the possible NBTree. n > 0
public class NBTree {
    public List<TreeNode> NBTree(int n) {
        return contructNBTree(n);
    }

    private List<TreeNode> contructNBTree(int n) {
        if (n == 0)
            return Arrays.asList(null);

        if (n == 1)
            return Arrays.asList(new TreeNode(0));

        List<TreeNode> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            List<TreeNode> lefts = contructNBTree(i);
            List<TreeNode> rights = contructNBTree(n - i);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = left;
                    cur.right = right;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
