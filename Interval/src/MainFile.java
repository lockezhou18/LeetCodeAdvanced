import java.util.Arrays;

public class MainFile {
    public static void main(String args[]) {
        //testMergeInterval();
        testMeetingRooms2();
    }

    private static void testMeetingRooms2() {
        MeetingRooms2 meetingRooms2 = new MeetingRooms2();
        int[][] testData = {{0,30},{5,10},{15,20}};
        System.out.println(meetingRooms2.minMeetingRooms(testData));
    }

    private static void testMeetingRooms() {
        MeetingRooms meetingRooms = new MeetingRooms();

    }

    private static void testMergeInterval() {
        MergeInterval mergeInterval = new MergeInterval();

        int[][] testData = {{1,3},{2,6},{8,10},{15,18}};
        int[][] testData2 = {{1,4},{4,5}};
        for (int[] data : mergeInterval.merge(testData2)) {
            System.out.println(Arrays.toString(data));
        }
    }
}
