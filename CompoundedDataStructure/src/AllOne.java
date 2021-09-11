import java.util.*;

class AllOne {
    class Bucket {
        Set<String> keys; //choose set since return any of the key
        int count;
        Bucket prev;
        Bucket next;

        Bucket() {
            this.keys = new HashSet<>();
        }

        Bucket(int count) {
            this.keys = new HashSet<>();
            this.count = count;
        }
        private void addKey(String key) {
            keys.add(key);
        }

        private int getSize() {
            return keys.size();
        }

        private void deleteKey(String key) {
            keys.remove(key);
        }

        private String retrieveKey() {
            return keys.iterator().next();
        }
    }

    //hashMap + bucket
    //hashMap <key, bucket>
    Map<String, Bucket> cache; //key -> key, val -> corresponding bucketIdx
    Bucket head;
    Bucket tail;

    /** Initialize your data structure here. */
    public AllOne() {
        cache = new HashMap<>();
        head = new Bucket();
        tail = new Bucket();
        head.next = tail;
        tail.prev = head;
    }

    //insert new bucket after current bucket
    private void insertBucketAfter(Bucket bucket, Bucket newBucket) {
        newBucket.next = bucket.next;
        bucket.next.prev = newBucket;
        bucket.next = newBucket;
        newBucket.prev = bucket;
    }

    private void removeBucket(Bucket bucket) {
        bucket.next.prev = bucket.prev;
        bucket.prev.next = bucket.next;
    }

    private void moveToNext(String key, Bucket bucket) {
        if (bucket.next.count != bucket.count + 1) {//add
            insertBucketAfter(bucket, new Bucket(bucket.count + 1));
        }

        addKeyToBucket(key, bucket.next);
        removeKeyFromBucket(key, bucket);
    }

    private void addKeyToBucket(String key, Bucket bucket) {
        bucket.addKey(key);
        if ((bucket == head || bucket == tail) && bucket.getSize() > 0) //could move to head if cur count is 1. so clear
            bucket.keys.clear();
    }

    private void removeKeyFromBucket(String key, Bucket bucket) {
        if (bucket == head || bucket == tail) //strictly can not remove head or tail
            return;

        bucket.deleteKey(key);
        if (bucket.getSize() == 0)
            removeBucket(bucket);
    }

    private void moveAhead(String key, Bucket bucket) {
        if (bucket.prev.count != bucket.count - 1) {
            insertBucketAfter(bucket.prev, new Bucket(bucket.count - 1));
        }

        addKeyToBucket(key, bucket.prev);
        removeKeyFromBucket(key, bucket);
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Bucket curBucket = cache.getOrDefault(key, head);
        moveToNext(key, curBucket);
        cache.put(key, curBucket.next);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Bucket curBucket = cache.get(key);
        moveAhead(key, curBucket);

        if (curBucket.count == 1) {
            cache.remove(key);
            return;
        }

        cache.put(key,curBucket.prev);
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (cache.size() == 0)
            return "";

        return tail.prev.retrieveKey();
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (cache.size() == 0)
            return "";

        return head.next.retrieveKey();
    }
}
