import java.util.HashMap;
import java.util.Map;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if (stones.length > 1)
            if (stones[1] != 1)
                return false;

        return canCross(stones, 0, 1, convertToMap(stones), new HashMap<>());
    }

    private Map<Integer, Integer> convertToMap(int[] stones) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], i);
        }
        return map;
    }

    private boolean canCross(int[] stones, int curIdx, int k, Map<Integer, Integer> stoneMap, Map<String, Boolean> memo) {
        if (curIdx == stones.length - 1)
            return true;

        Boolean res = memo.get(String.valueOf(curIdx) + "," + String.valueOf(k));
        if (res != null)
            return res;

        for (int l = k - 1; l <= k + 1; l++) {
            if (l == 0)
                continue;

            int target = stones[curIdx] + l;
            Integer i = stoneMap.get(target);
            if (i != null) {
                if (canCross(stones, i, l, stoneMap, memo)) {
                    memo.put(String.valueOf(curIdx) + "," + String.valueOf(k), true);
                    return true;
                }

            }
        }
        memo.put(String.valueOf(curIdx) + "," + String.valueOf(k), false);
        return false;
    }
}
