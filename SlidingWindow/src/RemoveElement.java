public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int dupCount = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == val) {
                int fast = i;
                while (fast < nums.length && nums[fast] == val) {
                    fast++;
                }
                if (fast != nums.length) {
                    dupCount++;
                    swap(nums, i, fast);
                }
            }
        }
        return nums.length - dupCount;
    }

    private void swap(int[] nums, int slow, int fast){
        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
    }
}
