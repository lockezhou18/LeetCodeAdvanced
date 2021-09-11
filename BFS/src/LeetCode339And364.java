import java.util.*;

public class LeetCode339And364 {
    public int depthSum(List<NestedInteger> nestedList) {
        //running bfs on level by level, calculate level number as minLength

        Queue<List<NestedInteger>> queue = new LinkedList<>();
        queue.offer(nestedList);
        int level = 1;
        int sum = 0;
        Map<Integer, List<Integer>> numToLevel = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                List<NestedInteger> curList = queue.poll();

                for (NestedInteger nestedInteger : curList) {
                    if (nestedInteger.isInteger()) {
                        List<Integer> list = numToLevel.getOrDefault(nestedInteger.getInteger(), new ArrayList<>());
                        list.add(level); numToLevel.put(nestedInteger.getInteger(), list);
                    } else {
                        queue.offer(nestedInteger.getList());
                    }
                }
            }
            level++;
        }

        for (Map.Entry<Integer,List<Integer>> entry : numToLevel.entrySet()) {
            int key = entry.getKey();
            List<Integer> values = entry.getValue();

            for (int value : values) {
                sum += ((level - value) + 1) * key;
            }
        }
        return sum;
    }

//    private int calculateDepth(List<NestedInteger> nestedIntegers) {
//        List<Integer> localDepths = new ArrayList<>();
//        for (NestedInteger nestedInteger : nestedIntegers) {
//            if (nestedInteger.isInteger()) {
//                localDepths.add(1);
//            } else {
//                int localMax = calculateDepth(nestedInteger.getList());
//                localDepths.add(localMax);
//            }
//        }
//
//        return Collections.max(localDepths);
//    }
}
