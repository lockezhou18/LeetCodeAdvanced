package stocks;

public class LC309 {
    public int maxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;

        int[] dpBuy = new int[prices.length];
        int[] dpSell = new int[prices.length];

        dpBuy[0] = Math.max(Integer.MIN_VALUE, -prices[0]);
        dpBuy[1] = Math.max(dpBuy[0], -prices[1]);
        dpSell[1] = Math.max(0, dpBuy[0] + prices[1]);

        for (int i = 2; i < prices.length; i++) {
            dpBuy[i] = Math.max(dpBuy[i - 1], dpSell[i - 2] - prices[i]);
            dpSell[i] = Math.max(dpSell[i - 1], dpBuy[i - 1] + prices[i]);
        }

        return dpSell[dpSell.length - 1];

    }
}
