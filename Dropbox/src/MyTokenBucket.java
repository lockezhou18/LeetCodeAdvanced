import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTokenBucket {
    private final int MAX_CAPACITY;
    private final int FILL_RATE;

    private int[] queue;
    private int size;
    private int head;
    private int tail;
    private long lastFillingTimeMillis;

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int element;

    public MyTokenBucket(int maxCapacity, int fillRate) {
        this.FILL_RATE = fillRate;
        this.MAX_CAPACITY = maxCapacity;
        queue = new int[MAX_CAPACITY];

        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();


    }

    public void put() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("start filling");
            while (size == MAX_CAPACITY) {
                System.out.println("put is waiting");
                notFull.await();
            }

            System.out.println("cur size is " + size);

            long curFillingTimeMillis = System.currentTimeMillis();
            long numToFill = Math.min(MAX_CAPACITY - size,
                    ((curFillingTimeMillis - lastFillingTimeMillis) / 1000) * FILL_RATE);

            Random random = new Random();
            while (numToFill-- > 0) {
                queue[tail++] = element++;
                if (tail == MAX_CAPACITY)
                    tail = 0;
                size++;
            }
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int[] get(final int n) throws InterruptedException {
        int[] res = new int[n];
        int cur = 0;
        int numToRemove = n;
        lock.lock();
        try {
            while (size < n) {
                //System.out.println("cur size is " + size);
                String curThreadName = Thread.currentThread().getName();
                System.out.println("get thread " + curThreadName + " is waiting");
                notEmpty.await();
            }

            while (numToRemove-- > 0) {
                res[cur++] = queue[head++];

                if (head == MAX_CAPACITY)
                    head = 0;
                size--;
            }
            notFull.signalAll();
            return res;
        } finally {
            lock.unlock();
        }
    }
}
