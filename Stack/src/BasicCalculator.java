import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        Map<Character, Integer> optrMap = new HashMap<>();
        optrMap.put('+', 1);
        optrMap.put('-', 1);
        optrMap.put('*', 2);
        optrMap.put('/', 2);

        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        int i = 0;
        addOperator('(', operatorStack, operandStack, optrMap); //stack中剩余的元素最后均进行运算
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (optrMap.containsKey(ch) || ch == '(' || ch == ')') { //先处理符号
                addOperator(ch, operatorStack, operandStack, optrMap);
                i++;
            } else if (ch >= '0' && ch <= '9') { //数字，拼数
                int val = 0;
                while (i < s.length() &&
                        s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                operandStack.push(val);
            } else { //space
                i++;
            }
        }

        addOperator(')', operatorStack, operandStack, optrMap);
        return operandStack.peek();
    }

    //左括号，放进去
    //右括号，从左括号到右括号的这一段进行计算 while (true) 然后break
    //符号位peek >= new， 进行运算，
    private void addOperator(Character ch, Stack<Character> operatorStack, Stack<Integer> operandStack, Map<Character, Integer> optrMap) {
        if (ch == '(') {
            operatorStack.push(ch);
        } else if (ch == ')') {
            while (true) {
                if (operatorStack.isEmpty()) {
                    throw new RuntimeException("parenthesis mismatch");
                }

                char top = operatorStack.peek();
                if (top == '(') {
                    operatorStack.pop();
                    break;
                }
                localCalculate(operatorStack, operandStack); //calculate 1 time
            }
        } else { //ch is operator
            while (true) { //触发多次运算
                if (operatorStack.isEmpty())
                    break;

                char top = operatorStack.peek();
                Integer topWeight =optrMap.get(top);
                if (topWeight == null || topWeight < optrMap.get(ch))
                    break;

                localCalculate(operatorStack, operandStack);
            }
            operatorStack.push(ch);
        }
    }

    private void localCalculate(Stack<Character> operatorStack, Stack<Integer> operandStack) { //数字栈弹2个， 运算符栈弹一个，运算之后放进数字栈
        Integer num2 = operandStack.pop();
        Integer num1 = operandStack.pop();

        Character operator = operatorStack.pop();
        operandStack.push(cal(operator, num1, num2));
    }

    private int cal(Character operator, Integer num1, Integer num2) {
        switch (operator) {
            case '+' : return num1 + num2;
            case '-' : return num1 - num2;
            case '*' : return num1 * num2;
            case '/' : return num1 / num2;
        }
        throw new RuntimeException("Illegal operator");
    }
}
