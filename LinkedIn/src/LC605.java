//种花， Greedy
//place flowers as long as it can to reach the local maxima to the current pos i.
// Then the local maxima gonna be the global maxima if i = len - 1
public class LC605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (canPlace(flowerbed, i)) {
                flowerbed[i] = 1;
                n--;
            }
            if (n == 0)
                return true;
        }
        return false;
    }

    private boolean canPlace(int[] flowerbed, int i) {

        if (flowerbed[i] == 0
                &&(i == 0 || flowerbed[i - 1] == 0)
                && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) //边界check的时候可以这么写 i== 0 || i - 1的element为想要的值

            return true;

        return false;
    }
}
