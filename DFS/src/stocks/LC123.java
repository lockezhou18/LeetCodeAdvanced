package stocks;

public class LC123 {
    public int maxProfit(int[] prices) {
        int[][] dpBuy = new int[3][prices.length];
        int[][] dpSell = new int[3][prices.length];

        dpBuy[1][0] = -prices[0];
        dpBuy[2][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dpBuy[1][i] = Math.max(dpBuy[1][i - 1], dpSell[1][i - 1] - prices[i]);
            dpBuy[2][i] = Math.max(dpBuy[2][i - 1], dpSell[2][i - 1] - prices[i]);

            dpSell[1][i] = Math.max(dpSell[1][i - 1], dpBuy[0][i - 1] + prices[i]);
            dpSell[2][i] = Math.max(dpSell[2][i - 1], dpBuy[1][i - 1] + prices[i]);
        }

        return dpSell[2][prices.length - 1];
    }
}
