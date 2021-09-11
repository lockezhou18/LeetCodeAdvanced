import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedRandomPick {
    class Range {
        int low;
        int high;
        int label;

        public Range(int low, int high, int label) {
            this.low = low;
            this.high = high;
            this.label = label;
        }
    }

    int sum;
    List<Range> ranges;

    public WeightedRandomPick(int[] w) {
        this.ranges = new ArrayList<>();
        this.sum = 0;
        initial(w);
    }

    private void initial(int[] w) {
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            Range curRange = new Range(sum, sum + w[i], i);
            ranges.add(curRange);
            sum += w[i];
        }

        this.sum = sum;
    }

    public int pickIndex() {
        //binary search
        int rand = new Random().nextInt(sum);
        Range range = binarySearch(rand);

        return range.label;
    }

    private Range binarySearch(int target) {
        int start = 0;
        int end = ranges.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            Range midRnage = ranges.get(mid);
            if (target >= midRnage.low && target < midRnage.high) //左闭右开区间 [)
                return midRnage;
            else if (target < midRnage.low) //go left
                end = mid - 1;
            else
                start = mid + 1;
        }

        throw new IllegalArgumentException();
    }
}
