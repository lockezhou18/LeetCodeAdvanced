import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTokenBucket2 {
    //initial data structure
    private int[] queue;
    private int size;
    private int head;
    private int tail;

    //initial lock
    Lock lock;
    Condition notEmpty;
    Condition notFull;

    //initial object related
    private long lastTime;
    private final int MAX_CAPACITY;
    private final int FILL_RATE;

    private static int element = 0;

    public MyTokenBucket2(int maxCapacity, int fillRate) {
        this.MAX_CAPACITY = maxCapacity;
        this.FILL_RATE = fillRate;

        queue = new int[maxCapacity];

        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
        lastTime = System.currentTimeMillis();
    }

    private int[] dequeue(int n) throws InterruptedException {
        int[] res = new int[n];
        lock.lock();
        try {
            while (size < n)
                notEmpty.await();

            int cur = 0;
            while (cur < n) {
                res[cur++] = queue[head++];
                if (head == MAX_CAPACITY)
                    head = 0;

                size--;
            }

            notFull.signalAll();
            System.out.println(Arrays.toString(res));
            return res;
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(int numToPut) throws InterruptedException{
        lock.lock();
        try {
            while (size + numToPut >= MAX_CAPACITY)
                notFull.await();

            int[] tokens = generateTokens(numToPut);
            int cur = 0;
            while (cur < numToPut) {
                queue[tail++] = tokens[cur++];
                if (tail == MAX_CAPACITY)
                    tail = 0;

                size++;
            }
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void put() throws InterruptedException {
        long curTimeMills = System.currentTimeMillis();
        int numToGenereate = (int)Math.min(((curTimeMills - lastTime) / 1000) * FILL_RATE, MAX_CAPACITY - size);
        lastTime = curTimeMills;

        enqueue(numToGenereate);
    }

    private int[] generateTokens(int n) {
        int[] elements = new int[Integer.valueOf((int) n)];
        for (int i = 0; i < n; i++) {
            elements[i] = element++;

        }

        return elements;
    }

    public int[] get(final int n) throws InterruptedException {
        if (element == 150)
            Thread.currentThread().interrupt();
        return dequeue(n);
    }
}
