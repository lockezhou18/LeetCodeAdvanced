import java.util.*;

//get
//extractMin - O(lgn)
//HashMap + TreeMap/Heap

//clarify can we assume the cache size > 0 ?
// can we assume ds always contains the key?
public class RetainBestCache<K, T extends Rankable> {
    int entriesToRetain;
    HashMap<K, T> cache;
    TreeMap<Long, LinkedList<K>> rankMap;
    DataSource<K, T> ds;
    int size;

    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
    //impliment here
        if (entriesToRetain <= 0)
            throw new IllegalArgumentException("invalid canche size");

        this.ds = ds;
        this.entriesToRetain = entriesToRetain;

        this.cache = new HashMap<>();
        this.rankMap = new TreeMap<>();
        this.size = 0;
    }

    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
     * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
        //impliment here
        if (cache.containsKey(key))
            return cache.get(key);

        T val = ds.get(key);
        //1. put into cache
        //2. put into rank map
        cache.put(key, val);
        long rank = val.getRank();
        LinkedList<K> list = rankMap.getOrDefault(rank, new LinkedList<>());
        list.addFirst(key);
        rankMap.put(rank, list);
        size++;

        //check if exceed upper limit
        if (size > entriesToRetain) {
            K removed = removeLowest();
            cache.remove(removed);
            size--;
        }

        //4. return val
        return val;
    }

    private K removeLowest() {//private function, map always not be empty
        Map.Entry<Long, LinkedList<K>> entry = rankMap.pollFirstEntry();
        long rank = entry.getKey();
        LinkedList<K> lowestList = entry.getValue();

        K removedKey = lowestList.removeFirst();
        if (lowestList.size() != 0) {
            rankMap.put(rank, lowestList);
        }

        return removedKey;
    }
}