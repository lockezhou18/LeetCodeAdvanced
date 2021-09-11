import java.util.BitSet;

public class PhoneDirectorySegmentTree2 {
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    private BitSet bits;
    private final int SIZE;
    private final int MAX_NUMBER;
    public PhoneDirectorySegmentTree2(int maxNumbers) {
        this.MAX_NUMBER = maxNumbers;
        this.SIZE = 2 * MAX_NUMBER - 1;
        this.bits = new BitSet(SIZE);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int idx = find();
        if (idx == -1)
            return -1;

        update(idx, 1);
        return idx;
    }

    private int find() { //find idx of avaliable number
        int cur = 0;
        while (cur < SIZE) {
            if (cur >= MAX_NUMBER - 1 && !bits.get(cur))
                return cur;

            int left = cur * 2 + 1;
            if (!bits.get(left))
                cur = left;
            else cur = left + 1; //right
        }

        return -1;
    }

    private void update(int idx, int val) { //update tree base on the val set on the idx
        if (val == 1)
            bits.set(idx);
        else
            bits.clear(idx);

        int cur = (idx - 1) / 2;

        while (cur > 0) {
            int left = cur / 2 + 1;
            if (bits.get(left) && bits.get(left + 1))
                bits.set(cur);
            else
                bits.clear(cur);

            cur = (cur - 1) / 2;
        }
    }

    private int getIdxById(int id) {
        return id + MAX_NUMBER - 1;
    }

    private int getIdByIdx(int idx) {
        return idx - MAX_NUMBER - 1;
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
