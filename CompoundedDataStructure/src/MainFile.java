import java.util.ArrayList;
import java.util.Arrays;

public class MainFile {
    public static void main(String args[]) {
        //testLRUCache();
        //testAllOne();
        //testArrayList();
       // testRandomizedSet();
       // testWordDistance();
        testWeightedRandomPick();
    }

    private static void testWeightedRandomPick() {
        int[] w = {3, 14, 1, 17};
        WeightedRandomPick weightedRandomPick = new WeightedRandomPick(w);
        System.out.println((double)(3/35));
        System.out.println(14 / 35);
        System.out.println(1 / 35);
        System.out.println(17 / 35);
        int k = 10000000;
        int[] res = new int[4];

        while (k-- > 0) {
            int curRes = weightedRandomPick.pickIndex();
            res[curRes]++;
        }

        System.out.println(Arrays.toString(res));
    }

    private static void testWordDistance() {
        String[] wordDict = {"a", "c", "b", "b", "a"};
        WordDistance2 wordDistance2 = new WordDistance2(wordDict);

        System.out.println(wordDistance2.shortest("a", "b"));

    }

    private static void testRandomizedSet() {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0);
        randomizedSet.remove(0);
        randomizedSet.insert(-1);
        randomizedSet.remove(0);
        System.out.println("debug");
    }

    private static void testArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.remove(1));
    }

    private static void testAllOne() {
        AllOne allOne = new AllOne();
//        allOne.inc("a");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.inc("b");
//        allOne.dec("b");
//        allOne.dec("b");
        testCase(allOne);

        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());

        allOne.inc("leet");

        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    private static void testCase(AllOne allOne) {
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println(allOne.getMinKey());
        allOne.dec("a");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    private static void testLRUCache() {
        int capacity = 2;

        LRUCache lruCache = new LRUCache(capacity);

        lruCache.put(2, 1);
        lruCache.put(1, 1);
        //lruCache.get(1);
        lruCache.put(2, 3);
        //lruCache.get(2);
        lruCache.put(4, 1);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}
