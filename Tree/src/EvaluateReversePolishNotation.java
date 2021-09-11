import java.util.Stack;

public class EvaluateReversePolishNotation {
    ConstructSyntaxTree constructSyntaxTree;
    EvaluateSyntaxTree evaluateSyntaxTree;
    TreeGenerator treeGenerator;

    public EvaluateReversePolishNotation() {
        this.constructSyntaxTree = new ConstructSyntaxTree();
        this.evaluateSyntaxTree = new EvaluateSyntaxTree();
        this.treeGenerator = new TreeGenerator();
    }
    public int evalRPN(String[] tokens) {
        TreeNode root = this.constructSyntaxTree.constructSyntaxTreeFromSufix(tokens);
        System.out.println(treeGenerator.serialize(root));
        return evaluateSyntaxTree.evaluateSyntaxTree(root);
    }

    private int evalRPNByStack(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String str : tokens) {
            if (isOperator(str)) {
                Integer post = stack.pop();
                Integer prior = stack.pop();
                stack.push(cal(str.charAt(0), prior, post));
                continue;
            }

            stack.push(Integer.valueOf(str));
        }

        return stack.pop();
    }

    private boolean isOperator(String cur) {
        return (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/"));
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
