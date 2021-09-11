package BST;

import java.net.Inet4Address;
import java.util.TreeSet;

//在一个区间内（window），当滑动到新的位置时，是否存在一个数<= 想要的target - search range - TreeMap
public class LC220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) { //remove first element in the window
                set.remove(Long.valueOf(nums[i - k - 1]));
            }
            long target = Long.valueOf(nums[i]) + t; //only convert element to long
            Long val = set.lower(target + 1);
            long lowerBound = Long.valueOf(nums[i]) - t;
            if (val != null && val >= lowerBound)
                return true;

            set.add(Long.valueOf(nums[i]));
        }

        return false;
    }
}
