package stocks;

public class LC122 {
    //no parameter k, if you wanna buy, just finishing sell in the last i - 1 day
    public int maxProfit(int[] prices) {
        int[] dpBuy = new int[prices.length];
        int[] dpSell = new int[prices.length];

        dpBuy[0] = Math.max(Integer.MIN_VALUE, -prices[0]);

        for (int i = 1; i < prices.length; i++) {
            dpBuy[i] = Math.max(dpBuy[i - 1], dpSell[i - 1] - prices[i]);
            dpSell[i] = Math.max(dpSell[i - 1], dpBuy[i - 1] + prices[i]);
        }

        return dpSell[dpSell.length - 1];
    }
}
