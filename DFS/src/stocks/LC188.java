package stocks;

public class LC188 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0)
            return 0;

        return this.maxProfitDP(k, prices);
    }

    private int maxProfitDP(int k, int[] prices) {
        int[][] buyDP = new int[k + 1][prices.length];
        int[][] sellDP = new int[k + 1][prices.length];


        for (int kk = 1; kk <= k; kk++) {
            buyDP[kk][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                buyDP[kk][i] = Math.max(buyDP[kk][i - 1], sellDP[kk - 1][i - 1] - prices[i]);
                sellDP[kk][i] = Math.max(sellDP[kk][i - 1], buyDP[kk][i - 1] + prices[i]);
            }
        }

        return sellDP[k][prices.length - 1];
    }
}
