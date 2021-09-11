import java.util.*;

//hashMap key String, val idx count
//求两个数组之间的最小差。greedy -> 使得差值缩小的状态转移。无分叉，双指针
public class WordDistance2 {
    Map<String, List<Integer>> map;
    public WordDistance2(String[] wordsDict) {
        map = new HashMap<>();
        initial(wordsDict);
    }

    private void initial(String[] wordDict) {
        int seq = 0;
        for (int i = 0; i < wordDict.length; i++) {
            List<Integer> list = map.getOrDefault(wordDict[i], new ArrayList<>());
            list.add(i);
            map.put(wordDict[i], list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);

        int minDiff = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) == list2.get(j)) //can not be same element to cal the diff
                continue;

            minDiff = Math.min(minDiff, Math.abs(list1.get(i) - list2.get(j)));

            if (list1.get(i) < list2.get(j)) i++;
            else j++;
        }

        while (i < list1.size()) minDiff = Math.min(minDiff, Math.abs(list1.get(i++) - list2.get(j - 1)));
        while (j < list2.size()) minDiff = Math.min(minDiff, Math.abs(list1.get(i - 1) - list2.get(j++)));

        return minDiff;
    }
}
