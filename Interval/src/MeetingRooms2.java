import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms2 {

    class StartPoint implements Comparable<StartPoint> {
        int val;
        boolean isStart;
        StartPoint(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(StartPoint that) {
            if (this.val != that.val){
                return this.val - that.val;
            } else { //breking tie, end point 在前，start point在后
               return this.isStart ? 1 : -1;
            }
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        Interval[] intervalsArray = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            intervalsArray[i] = new Interval(intervals[i][0], intervals[i][1]);
        }

        return this.minMeetingRooms(intervalsArray);
    }


    public int minMeetingRooms(Interval[] intervals) {
        List<StartPoint> points = new ArrayList<>();
        for (Interval interval : intervals) {
            points.add(new StartPoint(interval.start, true));
            points.add(new StartPoint(interval.end, false));
        }
        Collections.sort(points);
        int num = 0;
        int max = 0;
        for (StartPoint point : points) {
            if (point.isStart) {
                num++;
            } else {
                num--;
            }
            max = Math.max(max, num);
        }

        return max;
    }
}

