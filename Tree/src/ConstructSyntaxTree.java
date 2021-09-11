import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a arthimetic infix expression, construct syntax tree
//Ref: https://zhuanlan.zhihu.com/p/54670963
public class ConstructSyntaxTree {
    //given a syntax tree, construct all possible syntax trees
    public List<TreeNode> constructAllSyntaxTrees(String expression) {
        return this.constructAllSyntaxTrees(expression, 0, expression.length() - 1);
    }

    //Operator stack : push operators
    //Opertand stack: push numbers
    //if new operator is higher than Operator stack top, pop up 2 numbers and construct tree, than push back to opertand stack
    public TreeNode constructSyntaxTreeFrominfix(String expression) {
        Stack<TreeNode> operatorStack = new Stack<>();
        Stack<TreeNode> opertandStack = new Stack<>();
        int val = 0;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (isOperator(String.valueOf(ch))) { //operator
                opertandStack.push(new TreeNode(val));
                val = 0;

                TreeNode root = new TreeNode(ch);
                if (operatorStack.isEmpty() || compareOperatorPriority(ch, operatorStack.peek().operator)) {
                    operatorStack.push(root);
                    continue;
                }

                TreeNode lastRoot = operatorStack.peek(); //here we should evaluate the peek operator
                TreeNode right = opertandStack.pop();
                TreeNode left = opertandStack.pop();
                lastRoot.left = left;
                lastRoot.right = right;
                opertandStack.push(lastRoot);
                operatorStack.push(root);
                continue;
            }

            //is digit
            if (Character.isDigit(ch)) {
                val = val * 10 + (ch - '0');
                continue;
            }
        }

        return opertandStack.pop();
    }

    public boolean compareOperatorPriority(char o1, char o2) { //b higher or equal than a -> true
        int a = getPriority(o1);
        int b = getPriority(o2);

        return b - a < 0;
    }

    private int getPriority(char ch) {
        if (ch == '+' || ch == '-')
            return 0;

        if (ch == '*' || ch == '/')
            return 1;

        if (ch == '(')
            return 2;

        throw new IllegalArgumentException("not valid operator");
    }


    //operand -> push to stack
    //operator -> pop 2 elements from the stack, construct tree and push to stack
    //As example of lc 150
    public TreeNode constructSyntaxTreeFromSufix(String[] tokens) {
        Stack<TreeNode> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            String cur = tokens[i];
            if (isOperator(cur)){
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();

                TreeNode root = new TreeNode(cur.charAt(0));
                root.left = left;
                root.right = right;

                stack.push(root);
                continue;
            }
            stack.push(new TreeNode(Integer.valueOf(cur)));
        }

        return stack.pop();
    }

    private boolean isOperator(String cur) {
        return (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/") || cur.equals("("));
    }

    private List<TreeNode> constructAllSyntaxTrees(String expression, int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            char ch = expression.charAt(i);

            if (ch == '+' || ch == '-' || ch == '*') {
                TreeNode root = new TreeNode(ch);
                List<TreeNode> lefts = constructAllSyntaxTrees(expression, start, i - 1);
                List<TreeNode> rights = constructAllSyntaxTrees(expression, i + 1, end);

                for (TreeNode left : lefts) {
                    for (TreeNode right : rights) {
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }
        }

        if (res.isEmpty()) { //[start, end] is operand
            int val = Integer.valueOf(expression.substring(start, end + 1));
            res.add(new TreeNode(val));
        }
        return res;
    }
}
