import java.util.HashMap;
import java.util.Map;

public class LC149 {
    public int maxPoints(int[][] points) {
        //c.c
        if (points.length == 1)
            return 1;

        Map<String, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] pi = points[i];
            map.clear();

            for (int j = i + 1; j < points.length; j++) {
                int[] pj = points[j];
                String slope = getSlope(pi, pj);
                int count = map.getOrDefault(slope, 1);
                count++;
                map.put(slope, count);
            }

            for (int val : map.values()) {
                max = Math.max(max, val);
            }
        }

        return max;
    }

    private String getSlope(int[] pi, int[] pj) {
        int dy = pj[1] - pi[1];
        int dx = pj[0] - pi[0];
        int gcd = generateGCD(dx, dy);
        dy /= gcd;
        dx /= gcd;

        return dx + "," + dy;
    }

    private int generateGCD(int a, int b) { //辗转相除法。假设为较小的数
        if (b == 0) return a;

        return generateGCD(b, a % b);
    }
}
