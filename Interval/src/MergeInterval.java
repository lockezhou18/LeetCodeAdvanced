import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MergeInterval {
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> (a.start - b.start));
        Interval prev = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);

            if (cur.start <= prev.end) { //overlapping, merge
                prev.end = Math.max(prev.end, cur.end);
            } else {// non overlapping
                prev = cur;
            }
        }
        return intervals;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        List<int[]> intervalsList = new ArrayList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= prev[1]) {
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else {
                intervalsList.add(prev.clone());
                prev = intervals[i];
            }
        }

        intervalsList.add(prev);

        int[][] res = new int[intervalsList.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = intervalsList.get(i);
        }

        return res;
    }
}
