import java.util.*;

//HashMap + ArrayList
//HashMap key -> val, val -> idx
class RandomizedSet {
    Map<Integer, Integer> cache;
    List<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        cache = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (cache.containsKey(val))
            return false;

        list.add(val);
        cache.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!cache.containsKey(val))
            return false;

        int idx = cache.get(val);
//        if (cache.size() == 0)
//            return true;

        int lastElement = swap(idx); //swap the cur element with the last element, and remove the last element
        cache.put(lastElement, idx);
        cache.remove(val);
        return true;
    }

    private int swap(int idx) {
        int lastElement = list.get(list.size() - 1);
        list.set(idx, lastElement);
        list.remove(list.size() - 1);

        return lastElement;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIdx = random.nextInt(list.size());
        return list.get(randomIdx);
    }
}