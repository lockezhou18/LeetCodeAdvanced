import java.util.BitSet;

public class PhoneDirectorySegmentTree {
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    private BitSet bits;
    private int size;
    private int maxNumbers;
    public PhoneDirectorySegmentTree(int maxNumbers) {
        this.size = 2 * maxNumbers -1;
        this.maxNumbers = maxNumbers;
        bits = new BitSet(size);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int idx = find(); //find avaliable pos
        if (idx == -1)
            return -1;

        update(idx, 1);
        return getIdByIdx(idx);
    }

    private void update(int idx, int val) {
        if (val == 1)
            bits.set(idx);
        else bits.clear(idx);

        while (idx > 0) { //after evaluation 0
            int parentIdx = (idx - 1) / 2;
            if (bits.get(parentIdx * 2 + 1) && bits.get(parentIdx * 2  + 2))
                bits.set(parentIdx);
            else
                bits.clear(parentIdx);
            idx = parentIdx;
        }
    }

    private int find() {
       int curIdx = 0;
       while (curIdx < size) {
           if (curIdx >= maxNumbers && !bits.get(curIdx))
               return curIdx;

           int left = curIdx * 2 + 1;
           if (!bits.get(left))
               curIdx = left;
           else
               curIdx = left + 1;
       }

       return -1;
    }

    private int getIdByIdx(int idx) {
        return idx - (maxNumbers - 1);
    }

    private int getIdxById(int id) {
        return id + (maxNumbers - 1);
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !bits.get(getIdxById(number));
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (check(number))
            return;

        update(getIdxById(number), 0);
    }
}
