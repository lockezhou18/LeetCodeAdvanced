import java.util.Arrays;
import java.util.Collections;

//check if overlap exists in the current intervals
public class MeetingRooms {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        Interval prev = null;
        for (Interval interval : intervals) {
            if (prev != null && interval.start <= prev.end) {
                return false;
            }
            prev = interval;
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] >= prev[1])
                return false;

            prev = cur;
        }
        return true;
    }
}
