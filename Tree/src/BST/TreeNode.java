public class TreeNode {

    public int val;
    public TreeNode left, right;
    public boolean isOperator;
    public char operator;

    public TreeNode(int val) {
        this.isOperator = false;
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(char operator) {
        this.isOperator = true;
        this.operator = operator;
    }
}

