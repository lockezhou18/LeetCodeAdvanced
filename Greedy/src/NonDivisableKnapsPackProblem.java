import com.sun.tools.javac.jvm.Items;

import java.util.List;

//不可分割，全局搜索 - dp
public class NonDivisableKnapsPackProblem {
    public int getMaxValue(Item[] items, int V) {
        int[][] dp = new int[items.length + 1][V + 1];

        for (int i = 1; i <= items.length; i++) {
            for (int v = 0; v <= V; v++) {
                if (items[i - 1].V > v) {
                    dp[i][v] = dp[i - 1][v]; //not pick this item
                } else {
                    dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - (int)items[i - 1].V] + (int)items[i - 1].val);
                }
            }
        }

        return dp[items.length][V];
    }
}
