import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectorySetQueue{
    //queue + bitSet
    Queue<Integer> queue; //queue中的元素一定是avaliable的
    BitSet set;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectorySetQueue(int maxNumbers) {
        queue = new LinkedList<Integer>();
        set = new BitSet(maxNumbers);
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (queue.isEmpty())
            return -1;

        int idx = queue.poll();
        set.set(idx);
        return idx;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.get(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        set.clear(number);
        queue.offer(number);
    }
}
