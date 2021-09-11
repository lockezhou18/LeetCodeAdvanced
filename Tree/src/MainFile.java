import java.util.List;

public class MainFile {
    public static void main(String args[]) {
        //testNBTree();
        //testSyntaxTree();
        //testEvaluateReversePolishNotation();
        //int val = Integer.valueOf("-11");
       // System.out.println(val);
        //testBasicCalculator();
        testLargestSizeBST();
    }

    private static void testLargestSizeBST() {
        TreeGenerator treeGenerator = new TreeGenerator();
        LargestSizeBST largestSizeBST = new LargestSizeBST();
        TreeNode root = treeGenerator.deserialize("10,5,15,1,8,#,7");

        System.out.println(largestSizeBST.largestBSTSubtree(root));

    }

    private static void testBasicCalculator() {
        BasicCalculator basicCalculator = new BasicCalculator();
        String s = "3+2*2";
        System.out.println(basicCalculator.calculate(s));
    }

    private static void testEvaluateReversePolishNotation() {
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();
        String[] tokens = {"2","1","+","3","*"};
        System.out.println(evaluateReversePolishNotation.evalRPN(tokens));
    }

    private static void testSyntaxTree() {
        //String expression = "2-1-1";
        String expression = "2*3-4*5";
        DifferentWaystoAddParentheses differentWaystoAddParentheses = new DifferentWaystoAddParentheses();
        System.out.println(differentWaystoAddParentheses.diffWaysToCompute(expression));
    }

    private static void testNBTree() {
        int n = 3;
        NBTree nbTree = new NBTree();
        TreeGenerator treeGenerator = new TreeGenerator();

        List<TreeNode> list = nbTree.NBTree(n);
        for (TreeNode treeNode : list) {
            System.out.println(treeGenerator.serialize(treeNode));
        }
    }
}
