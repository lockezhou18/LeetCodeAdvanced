package stocks;

public class lc121 {
        public int maxProfit(int[] prices) {
            int buy = Integer.MIN_VALUE;
            int sell = 0;
            int nextBuy = 0;
            int nextSell = 0;
            for (int i = 0; i < prices.length; i++) {
                nextBuy = Math.max(buy, -prices[i]);
                nextSell = Math.max(sell, buy + prices[i]);
                buy = nextBuy;
                sell = nextSell;
            }
            return sell;

        }
}
