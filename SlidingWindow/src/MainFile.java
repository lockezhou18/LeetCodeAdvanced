import java.util.Arrays;

public class MainFile {
    public static void main(String args[]) {
        //test3Sum();
        testRemoveElement();
    }

    private static void testRemoveElement() {
        RemoveElement removeElement = new RemoveElement();
        int [] nums = new int[]{0,1,2,2,3,0,4,2};
        int target = 3;
        int x = removeElement.removeElement(nums, target);
        System.out.println(Arrays.toString(nums));
        System.out.println(x);

    }

    private static void test3Sum() {
        ThreeSumCloest threeSumCloest = new ThreeSumCloest();
        int[] nums = new int[]{-1,2,1,-4};
        int target = 1;

        System.out.println(threeSumCloest.threeSumClosest(nums, target));
    }
}
