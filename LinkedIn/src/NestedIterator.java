import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NestedIterator implements Iterator<Integer> {
    private List<Integer> res;
    int size;
    int cur;
    public NestedIterator(List<NestedInteger> nestedList) {
        res = new ArrayList<>();
        dfs(nestedList);
        size = res.size();
    }
    private void dfs(List<NestedInteger> nestedIntegers) {
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                res.add(nestedInteger.getInteger());
                continue;
            }
            dfs(nestedInteger.getList());
        }
    }

    @Override
    public Integer next() {
        return res.get(cur++);
    }

    @Override
    public boolean hasNext() {
        if (cur < size)
            return true;

        return false;
    }
}
