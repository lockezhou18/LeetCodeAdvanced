public class LC367 {
    public boolean isPerfectSquare(int num) {
        long start = 1;
        long end = num / 2;

        while (start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid * mid; // 两个integer相乘， 可能会出界，用long
            if (mid * mid == num)
                return true;

            if (mid * mid < num)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return false;
    }
}
