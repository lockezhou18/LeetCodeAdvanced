import java.util.Stack;

//TwoSum in BST. Using 2 stacks to acheive find successor and predecessor in BST
//T.c amortized O(n)
//S.C O(lgn)
public class LC653 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null)
            return false; //should throw exception
        Stack<TreeNode> leftStack = initialLeftStack(root);
        Stack<TreeNode> rightStack = initialRightStack(root);

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            if (leftStack.peek() == rightStack.peek()) { //no meet, check at first
                return false;
            } else if (leftStack.peek().val + rightStack.peek().val == k) {
                return true;
            } else if (leftStack.peek().val + rightStack.peek().val < k) { //left++
                leftPlus(leftStack);
            } else { //right--
                rightMinus(rightStack);
            }
        }

        return false;
    }

    private Stack<TreeNode> initialLeftStack(TreeNode root) { //treeNode in stack represents its right subtree in logic
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;

        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return stack;
    }

    private Stack<TreeNode> initialRightStack(TreeNode root) {//represents its left subtree in logic
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }

        return stack;
    }

    private int leftPlus(Stack<TreeNode> stack) { //
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return top.val;
    }

    private int rightMinus(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }

        return top.val;
    }
}
