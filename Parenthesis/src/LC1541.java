

public class LC1541 {
    public int minInsertions(String s) {
        int delta = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);

            if (ch == '(') {
                delta++;
                continue;
            }

            if (ch == ')') {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    delta--;
                    i++;
                } else {
                    res++; //insert a right parenthesis to consume a left one
                    delta--;
                }
            }

            if (delta < 0) {
                res++;
                delta = 0;
            }
        }

        return delta == 0 ? res : res + delta * 2;
    }
}
