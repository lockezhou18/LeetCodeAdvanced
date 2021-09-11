public class LC1780 {
    int max = 0;
    int count = 0;
    public boolean checkPowersOfThree(int n) {
        int product = 1;
        for (int i = 0; i < n; i++) {
            product *= 3;
            if (product > n) {
                max = i + 1;
                break;
            }
        }

        return dfs(n, 0, 0, new Boolean[n + 1]);
    }

    private boolean dfs(int n, int curPower, int sum, Boolean[] memo) {
        if (curPower == max + 1 || sum > n)
            return false;

        if (sum == n)
            return true;

        if (memo[sum] != null) {
            count++;
            return memo[sum];
        }

        if (dfs(n, curPower + 1, sum, memo))
            memo[sum] = true;
        else
            memo[sum] = dfs(n, curPower + 1, sum + (int)Math.pow(3, curPower), memo);

        return memo[sum];
    }
}
