
import java.util.Collections;
import java.util.List;

//物品可分割，根据val/v 比值从大到小排序，greedy思路，从左向右装, 返回装的最大价值
public class KnapsackProblemDivsible {
    public double getMaxValue(List<Item> items, int V) {
        Collections.sort(items, (a, b) -> ((int)(b.val/b.V) - (int)(a.val/a.V)));
        double val = 0;
        for (Item item : items) {
            if (V >= item.V) {
                val += item.val;
                V -= item.V;
            } else {
                double fraction = (double)V / (double)item.V;
                val += item.val * fraction;
                V -= item.V * fraction;
                break;
            }
        }

        return val;
    }
}
