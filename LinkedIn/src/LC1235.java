import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LC1235 {
    class Job implements Comparable<Job> {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job that) {
            return this.end - that.end;
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = buildJobs(startTime, endTime, profit);
        Collections.sort(jobs);
        return dfs(jobs, 0, new Integer[startTime.length + 1]);
    }

    private List<Job> buildJobs(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(0, 0, 0));
        for (int i = 0; i < startTime.length; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }

        return jobs;
    }

    //List<Job> -> intervals sorted by startTime
    private int dfs(List<Job> jobs, int curIdx, Integer[] memo) { //max profit from job curIdx to the last job
        int max = 0;

        if (memo[curIdx] != null)
            return memo[curIdx];
        List<Integer> res = new ArrayList<>();
        for (int i = curIdx + 1; i < jobs.size(); i++) { //pick next non-overlapping job, compare the max
            if (jobs.get(i).start >= jobs.get(curIdx).end) {
                res.add(dfs(jobs, i, memo) + jobs.get(curIdx).profit);
                max = Math.max(max, dfs(jobs, i, memo) + jobs.get(curIdx).profit);
            }
        }
        if (curIdx == 4)
            System.out.println(res);
        memo[curIdx] = max;
        return max;
    }

//    private int dfs(List<Job> jobs, int curIdx, Integer[] memo, int lastEndTime) {
//        int max = 0;
//
//        if (curIdx != -1 && memo[curIdx] != null)
//            return memo[curIdx];
//
//        for (int i = curIdx + 1; i < jobs.size(); i++) {
//            int curEnd = curIdx == -1 ? 0 : jobs.get(curIdx).end;
//            int curProfit = curIdx == -1 ? 0 : jobs.get(curIdx).profit;
//            if (jobs.get(i).start >= curEnd) {
//                max = Math.max(max, dfs(jobs, i, memo, 0) + curProfit);
//            }
//        }
//        if (curIdx != -1)
//            memo[curIdx] = max;
//        return max;
//    }

}




