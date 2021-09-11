import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        return canIWin(0, desiredTotal, maxChoosableInteger, new HashSet<>(), 0, new Boolean[32]);
    }

    private boolean canIWin(int sum, int desiredTotal, int maxChoosableInteger,
                            Set<Integer> visited, Integer map, Boolean[] memo) {
        // if (curSum >= desiredTotal) {
        //     return true;
        // }

        if (memo[map] != null)
            return memo[map];

        for (int i = 1; i <= maxChoosableInteger; i++) {
          if (!visited.remove(i)) {
              visited.add(i);
              if (sum + i >= desiredTotal)
                  return true;

              int mask = 1 << (i - 1);
              int newMap = map | mask;
              boolean res = canIWin(sum + i, desiredTotal, maxChoosableInteger, visited, newMap, memo);
              if (!res) {
                  memo[map] = true;
                  return true;
              }
          }
        }
        memo[map] = false;
        return false;
    }
}
