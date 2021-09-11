import java.util.*;

import static java.util.Collections.reverse;

public class FindKPairswithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return kSmallestPairsOptimal(nums1, nums2, k);
    }

    public List<List<Integer>> kSmallestPairsBruteForce(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>(k, (a, b) -> {
            return (b.get(0) + b.get(1)) - (a.get(0) + a.get(1));
        });

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                //keep k size heap, always add when meet new element, pop up the top element when k is k + 1
                maxHeap.offer(Arrays.asList(nums1[i], nums2[j]));
                if (maxHeap.size() == k + 1)
                    maxHeap.poll();
            }
        }

        List<List<Integer>> res = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            res.add(0, maxHeap.poll());
        }
        return res;
    }

    //https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    //k way merge sort
    public List<List<Integer>> kSmallestPairsOptimal(int[] nums1, int[] nums2, int k) {
        if (nums1.length * nums2.length < k)
            return new ArrayList<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]])); //{nums1 idx, nums2 idx, currently nums1 associated nums2 idx)
        int step = k;
        int idx1 = 0;
        while (idx1 < nums1.length && step-- > 0) {
            minHeap.offer(new int[]{idx1++, 0});
        }

        step = k;
        List<List<Integer>> res = new ArrayList<>();
        while (!minHeap.isEmpty() && step-- > 0) {
            int[] cur = minHeap.poll();
            res.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));

            if (isInBoundary(cur[0], cur[1] + 1, nums1, nums2)) {
                minHeap.offer(new int[]{cur[0], cur[1] + 1});
            }
        }

        return res;
    }

    private boolean isInBoundary(int i, int j, int[] nums1, int[] nums2) {
        return i < nums1.length && j < nums2.length;
    }
}
