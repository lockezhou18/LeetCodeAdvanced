import java.util.HashMap;
import java.util.Map;

public class FlippingGame {
    public boolean canWin(String currentState) {
        return dfs(currentState, new HashMap<>());
    }

    private boolean dfs(String currentState, Map<String, Boolean> memo) {
        Boolean res = memo.get(currentState);
        if (res != null) {
            return res;
        }

        for (int i = 0; i < currentState.length() - 1; i++) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i + 1) == '+') {
                String flipRes = flip(currentState, i, i + 1);
                if (!dfs(flipRes, memo)) {
                    memo.put(currentState, true);
                    return true;
                }
            }
        }
        memo.put(currentState, false);
        return false;
    }

    private String flip(String currentState, int i, int j) {
       char [] chars = currentState.toCharArray();
       chars[i] = '-'; chars[i + 1] = '-';
       return String.valueOf(chars);
    }
}
