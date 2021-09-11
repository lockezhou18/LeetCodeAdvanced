public class LC921 {
    public int minAddToMakeValid(String s) {
        int delta = 0;
        int res = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == '(') {
                delta++;
                continue;
            }

            if (ch == ')') {
                delta--;
            }

            if (delta < 0) { //after finish eval current state, we check the necessaity to
                res += -1 * delta;
                delta = 0;
            }
        }

        return delta == 0 ? res : res + delta;
    }
}
