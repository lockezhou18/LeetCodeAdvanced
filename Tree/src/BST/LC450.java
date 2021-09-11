//delete node in BST
public class LC450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfs(root, key);
    }

    private TreeNode dfs(TreeNode root, int key) { //return 是删掉为key的node之后的当前root
        if (root == null)
            return null;

        if (root.val != key){
            if (root.val < key) {
                root.right = dfs(root.right, key);
            } else if (root.val > key) {
                root.left = dfs(root.left, key);
            }
            return root;
        }

        //current root is the key
        if (root.left == null && root.right == null)
            return null;

        if (root.left == null)
            return root.right;

        if (root.right == null)
            return root.left;

        //not null of left and right
        TreeNode cur = root.right;
        while (cur.left != null)
            cur = cur.left;

        root.val = cur.val;
        root.right = dfs(root.right, cur.val);
        return root;
    }
}

