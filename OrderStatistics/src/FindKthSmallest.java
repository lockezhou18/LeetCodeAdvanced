import java.util.Random;

public class FindKthSmallest {
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

    public int[][] kClosest(int[][] points, int k) {
        Point[] pointsArray = convertTo(points);
        int idx = quickSelection(pointsArray, 0, points.length - 1, k);
        int[][] res = convertRes(idx, pointsArray);

        return res;
    }

    private Point[] convertTo(int[][] points) {
        Point[] pointsArray = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            pointsArray[i] = new Point(points[i][0], points[i][1]);
        }

        return pointsArray;
    }

    private int[][] convertRes(int idx, Point[] pointsArray) {
        int[][] res = new int[idx + 1][2];

        for (int i = 0; i <= idx; i++) {
            res[i][0] = pointsArray[i].x;
            res[i][1] = pointsArray[i].y;
        }

        return res;
    }

    private int quickSelection(Point[] points, int left, int right, int k) {
        if (left == right)
            return left;

        int idx = partition(points, left, right);
        int rank = idx - left + 1;

        if (rank == k) {
            return idx;
        } else if (rank < k) {
            return quickSelection(points, idx + 1, right, k - rank);
        } else {
            return quickSelection(points, left, idx - 1, k);
        }
    }

    private int partition(Point[] points, int left, int right) {
        randomSelect(points, left, right);//random select element and swap to the right
        Point pivotElement = points[right];
        int boundary = left - 1;
        for (int i = left; i < right; i++) {
            if (points[i].compareTo(pivotElement) > 0)
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
