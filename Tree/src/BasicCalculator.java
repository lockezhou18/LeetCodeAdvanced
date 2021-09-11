//+ - * / with parenthesis and non negative integers
public class BasicCalculator {
    ConstructSyntaxTree constructSyntaxTree;
    EvaluateSyntaxTree evaluateSyntaxTree;
    TreeGenerator treeGenerator;

    BasicCalculator() {
        constructSyntaxTree = new ConstructSyntaxTree();
        evaluateSyntaxTree = new EvaluateSyntaxTree();
        treeGenerator = new TreeGenerator();
    }
    public int calculate(String s) {
        TreeNode root = constructSyntaxTree.constructSyntaxTreeFrominfix(s);
        System.out.println(treeGenerator.serialize(root));
        return evaluateSyntaxTree.evaluateSyntaxTree(root);
    }
}
