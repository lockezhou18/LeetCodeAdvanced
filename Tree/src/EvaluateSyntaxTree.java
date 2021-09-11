
//Given a syntax tree, evaluate it's val
public class EvaluateSyntaxTree {
    public int evaluateSyntaxTree(TreeNode root) {
        if (root == null)
            throw new IllegalArgumentException("ROOT IS NULL!!");

        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (!root.isOperator)
            return root.val;

        int left = dfs(root.left);
        int right = dfs(root.right);

        return cal(root.operator, left, right);
    }

    private int cal(Character ch, int left, int right) {
        if (ch == '+')
            return left + right;

        else if (ch == '-')
            return left - right;

        else if (ch == '*')
            return left * right;

        else
            return left / right;
    }
}
