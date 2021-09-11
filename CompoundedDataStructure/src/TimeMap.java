import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {
    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> treeMap = map.getOrDefault(key, new TreeMap<>());
        treeMap.put(timestamp, value);
        map.put(key, treeMap);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key))
            return "";

        Integer timestamp_prev = map.get(key).floorKey(timestamp);
        if (timestamp_prev == null)
            return "";

        return map.get(key).get(timestamp_prev);
    }
}
