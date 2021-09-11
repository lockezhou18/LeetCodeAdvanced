import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Search problem to find all the possible ways to construct trees,
// with domain knowldge that evaluate Arithmetic expressioin by syntax tree
//Syntax tree can remove parentheses(change the order of calculation),
// so the different construction of tree represents different ways to add parenthesis
public class DifferentWaystoAddParentheses {
    public List<Integer> diffWaysToCompute(String expression) {
        return diffWaysToCompute(expression, 0, expression.length() - 1);
    }

    private List<Integer> diffWaysToCompute(String expression, int start, int end) {
        if (isNumber(expression, start, end)) {
            //return new ArrayList<>(2); //constructor pass size
            return Arrays.asList(Integer.valueOf(expression.substring(start, end + 1))); //new ArrayList(); list.add() 的替代是Arrays.asList(Integer);
        }

        List<Integer> res = new ArrayList<>();
        for (int k = start; k <= end; k++) {
            char ch = expression.charAt(k);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> lefts = diffWaysToCompute(expression, start, k - 1);
                List<Integer> rights = diffWaysToCompute(expression, k + 1, end);
                for (Integer left : lefts) {
                    for (Integer right : rights) {
                        res.add(cal(ch, left, right));
                    }
                }
            }
        }

        return res;
    }

   private int cal(Character c, Integer left, Integer right) {
        if (c == '+')
            return left + right;

        else if (c == '-')
            return left - right;

        else
            return left * right;
   }

    private boolean isNumber(String expression, int start, int end) {//check [start, end], every char is digit
        for (int i = start; i <= end; i++) {
            if (!Character.isDigit(expression.charAt(i)))
                return false;
        }

        return true;
    }
}
