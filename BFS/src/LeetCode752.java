import java.util.*;

public class LeetCode752 {
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> deadendsSet = convertToSet(deadends);
        int minLength = 0;
        while (!queue.isEmpty()) {
            int size= queue.size();

            while (size-- > 0) {
                String cur = queue.poll();

                List<String> nexts = convert(cur);

                for (String next : nexts) {
                    if (next.equals(target)) {
                        return minLength + 1;
                    }

                    if (deadendsSet.add(next)) {
                        queue.offer(next);
                    }
                }
            }
            minLength++;
        }

        return -1;
    }
    public List<String> convert(String cur) {
        //for each digit, +1 or -1 works from 0 - 9. if it is 0
        List<String> res = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            char[] chars = cur.toCharArray();
            int digit = Character.getNumericValue(chars[i]);
            int moveUp = digit + 1;
            int moveDown = digit - 1;
            if (moveUp > 9) {
                moveUp = 0;
            }

            if (moveDown < 0) {
                moveDown = 9;
            }
            chars[i] = Character.forDigit(moveUp, 10);
            res.add(String.valueOf(chars));
            chars[i] = Character.forDigit(moveDown, 10);
            res.add(String.valueOf(chars));
        }

        return res;
    }
    private Set<String> convertToSet(String[] deadends) {
        Set<String> set = new HashSet<>();

        for (String deadend : deadends) {
            set.add(deadend);
        }

        return set;
    }
}
