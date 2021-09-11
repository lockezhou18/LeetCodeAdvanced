public class LC473 {
    private int sum = 0;

    public boolean makesquare(int[] matchsticks) {
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
        }
        if (sum % 4 != 0)
            return false;

        int[] bucket = new int[4];
        return dfs(matchsticks, 0, bucket);

    }

    private boolean dfs(int[] matchsticks, int curIdx, int[] bucket) {
        //base case
        if (curIdx == matchsticks.length) {
            if (isEquals(bucket))
                return true;
            return false;
        }

        if (exceedMaxLimit(bucket))
            return false;

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] += matchsticks[curIdx];
            if (dfs(matchsticks, curIdx + 1, bucket))
                return true;
            bucket[i] -= matchsticks[curIdx];
        }

        return false;
    }

    private boolean exceedMaxLimit(int[] bucket) {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > sum / 4)
                return true;
        }
        return false;
    }

    private boolean isEquals(int[] bucket) {
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != sum / 4)
                return false;
        }

        return true;
    }
}
