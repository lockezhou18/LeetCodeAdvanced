import java.util.Arrays;
import java.util.Random;

//Shuffle algorithm
class Solution {
    int[] nums;
    int[] numsCopy;
    public Solution(int[] nums) {
        this.nums = nums;
        this.numsCopy = Arrays.copyOf(nums, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        this.shuffle(this.numsCopy);
        return numsCopy;
    }

    private void shuffle(int[] nums) { //for every possible i, put it in the random position from [0, i],
        for (int i = 0; i < nums.length; i++) {
            int rand = new Random().nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[rand];
            nums[rand] = temp;
        }
    }
}
