import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ZumaGame {
    public int findMinStep(String board, String hand) {
        Map<Character, Integer> handMap = convert(hand);
        return dfs(board, handMap);
    }

    private Map<Character, Integer> convert(String hand) {
        Map<Character, Integer> handMap = new HashMap<>();

        for (Character c : hand.toCharArray()) {
            handMap.put(c, handMap.getOrDefault(c, 0) + 1);
        }

        return handMap;
    }
    private int dfs(String board, Map<Character, Integer> hand) {
        if (board.length() == 0)
            return 0; //本身为0， 不需要打了

        if (hand.size() == 0)
            return -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < board.length(); i++) {
            char cur = board.charAt(i);
            Integer count = hand.get(cur);
            if (count == null) { //没有球了
                continue;
            }

            if (i + 1 < board.length() && cur == board.charAt(i + 1)) { //2 balls, use 1 ball in hand && i + 1存在
                hand.remove(cur);
                if (count - 1 > 0) {
                    hand.put(cur, count - 1);
                }
                String newBoard = removeBalls(board, i - 1, i + 2);
                int val = dfs(newBoard, hand);
                if (val >= 0)
                    min = Math.min(min, val + 1);

                hand.put(cur, count);
            } else if (count >= 2) { //1 ball, use 2 balls in hand
                hand.remove(cur);
                if (count - 2 > 0) {
                    hand.put(cur, count - 2);
                }
                String newBoard = removeBalls(board, i - 1, i + 1);
                int val = dfs(newBoard, hand);
                if (val >= 0)
                    min = Math.min(min, val + 2);

                hand.put(cur, count);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    //bbbrrwwrb
    private String removeBalls(String board, int i, int j) {
        int left = i;
        int right = j;

        while(left >= 0 && right < board.length()) {
            char cur = board.charAt(left);
            int l = left;
            int count = 0;
            while (l >= 0 && board.charAt(l) == cur) {
                l--;
                count++;
            }
            int r = right;
            while (r < board.length() && board.charAt(r) == cur) {
                r++;
                count++;
            }

            if (count < 3) break; //不够连续的3个了
            else {
                left = l;
                right = r;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k <= left; k++) {
            sb.append(board.charAt(k));
        }

        for (int k = right; k < board.length(); k++) {
            sb.append(board.charAt(k));
        }

        return sb.toString();
    }

}
