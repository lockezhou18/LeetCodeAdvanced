
//+-*/不带括号 basic calculator 2
public class LC227 {
    //lastVal, curVal. Scan for 1 time
    int curRes = 0;
    int lastVal = 0;
    public int calculate(String s) { //
        int i = 0;
        Character operator = '~'; //default

        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') { //operator
                operator = ch;
                i++;
            } else if (ch <= '9' && ch >= '0') {
                int val = 0;
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    val = val * 10 + s.charAt(i) - '0';
                    i++;
                }
                localCalculate(operator, val); //update curRes and lastRes
            } else {
                i++;
            }
        }

        return curRes;
    }

    private void localCalculate(Character ch, int val) {
        if (ch == '~') { //default operator, the first number we meet
            curRes = val;
            lastVal = val;
            return;
        }
        if (ch == '+' || ch == '-') {
            calAdd(ch, val);
            return;
        }

        if (ch == '*' || ch == '/') {
            calMutiplication(ch, val);
            return;
        }
    }

    private void calAdd(Character operator, int val) {
        switch (operator) {
            case '+' : {
                curRes += val;
                lastVal = val;
                return;
            }

            case '-' : {
                curRes -= val;
                lastVal = -1 * val; //加负，这里lastval应该是负的curval
                return;
            }
        }
    }

    private void calMutiplication(Character operator, int val) {
        switch (operator) {
            case '*' : {
                curRes -= lastVal;
                lastVal *= val;
                curRes += lastVal;
                return;
            }
            case '/' : {
                curRes -= lastVal;
                lastVal /= val;
                curRes += lastVal;
                return;
            }
        }
    }
}
