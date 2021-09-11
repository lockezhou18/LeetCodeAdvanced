import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//t.c O(lgn + k)
//s.c O(lgn) height of tree
public class LC272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        //initial stack, target is between in left top and right top
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        TreeNode cur = root;

        while (cur != null) { //O(lgn)
            if (cur.val < target) { //go right, since target should be right
                leftStack.push(cur);
                cur = cur.right;
            } else { //go left, since target should be in left
                rightStack.push(cur);
                cur = cur.left;
            }
        }

        //left--, right++. Comparing current left val and right val. Running k times
        List<Integer> res = new ArrayList<>();
        while (k-- > 0) { //O(k)
            if (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                TreeNode leftTop = leftStack.peek();
                TreeNode rightTop = rightStack.peek();

                if (Math.abs(leftTop.val - target) < Math.abs(rightTop.val - target)) { //left--
                    res.add(leftMinus(leftStack));
                } else { //right++
                    res.add(rightPlus(rightStack));
                }
            } else if (!leftStack.isEmpty()) { //right stack is empty
                res.add(leftMinus(leftStack));
            } else if (!rightStack.isEmpty()) {
                res.add(rightPlus(rightStack));
            }
        }

        return res;
    }

    private int leftMinus(Stack<TreeNode> stack) { //find predecessor, represents its left substree in logic
        TreeNode top = stack.pop();
        TreeNode cur = top.left;

        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }

        return top.val;
    }

    private int rightPlus(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;

        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return top.val;
    }
}
