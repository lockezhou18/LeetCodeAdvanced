public class LC983 {
    //can not do dp since it's not fullfiling value continously (means +1 or -1)
    public int mincostTickets(int[] days, int[] costs) {
        return dfs(days, costs, 0, new Integer[days.length]);
    }

    private int dfs(int[] days, int[] costs, int curIdx, Integer[] memo) {
        if (curIdx == days.length)
            return 0;

        int min = Integer.MAX_VALUE;

        if (memo[curIdx] != null)
            return memo[curIdx];

        //buy 1 day
        int oneDayCost = dfs(days, costs, curIdx + 1, memo) + costs[0];

        //buy 7 days
        int i = curIdx + 1;
        while (i < days.length) {
            if (days[i] >= days[curIdx] + 7)
                break;
            i++;
        }
        int sevenDaysCost = dfs(days, costs, i, memo) + costs[1];

        //buy 30 days
        int j = curIdx + 1;
        while (j < days.length) {
            if (days[j] >= days[curIdx] + 30)
                break;
            j++;
        }
        int thirtyDaysCost = dfs(days, costs, j, memo) + costs[2];

        memo[curIdx] = Math.min(Math.min(oneDayCost, sevenDaysCost), thirtyDaysCost);
        return memo[curIdx];
    }
}
