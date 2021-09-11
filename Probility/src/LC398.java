import java.util.*;

//Hashmap with pick random. Key target element, value - List of index of this element
//T.c O(1) for pick, since constructor only used for one time
class LC398 {
    Map<Integer, List<Integer>> map;
    public LC398(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> values = map.getOrDefault(nums[i], new ArrayList<>());
            values.add(i);
            map.put(nums[i], values);
        }
    }

    public int pick(int target) {
        List<Integer> values = this.map.getOrDefault(target, new ArrayList<>());
        int rand = new Random().nextInt(values.size());
        return values.get(rand);
    }
}