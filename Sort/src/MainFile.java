import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;

public class MainFile {

    private static int[][] DATA_SET = {{1}, {1, 2}, {1,2,3}, {3,2,1}, {3,2,2,1}, {4,3,2,1}, {3,52,4656,4332,4,1,0,0,6}, {1,1,1}};
    public static void main(String args[]) {
        //testMergeSort();
        testQuickSort();
    }

    private static void testQuickSort() {
        QuickSort quickSort = new QuickSort();
        for (int[] testData : DATA_SET) {
            quickSort.quickSort(testData);
            System.out.println(Arrays.toString(testData));
        }
    }

    private static void testMergeSort() {
        int[] sample1 = {1, 2, 2, 3, 5, 6};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(sample1);
        System.out.println(Arrays.toString(sample1));
    }
}
