import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC532 {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        Map<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] + k) && !map.get(nums[i] + k)) {
                res++;
                map.put(nums[i] + k, true);
            } else if (map.containsKey(nums[i] - k) && !map.get(nums[i] - k)) {
                res++;
                map.put(nums[i] - k, true);
            }

            map.put(nums[i], false);
        }
        return res;
    }

    private boolean contains(Set<Integer> set, int cur, int k) {
        return set.contains(cur + k) || set.contains(cur - k);
    }

    private void remove(Set<Integer> set, int cur, int k) {
        set.remove(cur + k);
        set.remove(cur - k);
    }
}
