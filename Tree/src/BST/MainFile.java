package BST;

public class MainFile {
    public static void main(String args[]) {
        testScoreBoard();
    }

    private static void testScoreBoard() {
        int[] testData = {435,2,2,314,5,7,3,21,24,245,3,12,8,574};

        ScoreBoardS3 scoreBoardS3 = new ScoreBoardS3();
        for (Integer num : testData) {
            scoreBoardS3.add(num);
            scoreBoardS3.print();
        }
    }
}
