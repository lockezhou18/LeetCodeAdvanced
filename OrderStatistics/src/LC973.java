import java.util.PriorityQueue;
import java.util.Random;

public class LC973 {
    class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            return ((this.x * this.x + this.y * this.y)
                    - (that.x * that.x + that.y * that.y));
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> heap = new PriorityQueue<>(); //maxHeap
        for (int[] point : points) {
            heap.offer(new Point(point[0], point[1]));

            if (heap.size() > K)
                heap.poll();
        }

        return convert(heap, K);
    }


    private int[][] convert(PriorityQueue<Point> heap, int K) {
        int[][] res = new int[2][K];
        int idx = 0;
        while (!heap.isEmpty()) {
            Point cur = heap.poll();

            res[0][idx] = cur.x;
            res[1][idx++] = cur.y;
        }

        return res;
    }


    private int quickSelection(Point[] points, int left, int right, int k) {
        if (left >= right)
            return left;

        int idx = partition(points, left, right);
        int rank = idx - left + 1;

        if (rank == k) {
            return idx;
        } else if (rank < k) {
            return quickSelection(points, idx + 1, right, k);
        } else {
            return quickSelection(points, left, idx - 1, k);
        }
    }

    private int partition(Point[] points, int left, int right) {
        randomSelect(points, left, right);//random select element and swap to the right
        Point pivotElement = points[right];
        int boundary = left - 1;
        for (int i = left; i < right; i++) {
            if (points[i].compareTo(pivotElement) <= 0)
                continue;

            swap(points, i, ++boundary);
        }

        swap(points, right, ++boundary);
        return boundary;
    }

    private void swap(Point[] points, int left, int right) {
        Point temp = points[left];
        points[left] = points[right];
        points[right] = temp;
    }

    private void randomSelect(Point[] points, int left, int right) {
        int idx = new Random().nextInt(right - left + 1) + left;
        swap(points, idx, right);
    }
}
