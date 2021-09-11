class MyCircularQueue {
    int head = 0;
    int tail = -1;
    int[] queue;
    int size = 0;

    public MyCircularQueue(int k) {
        this.queue = new int[k];
    }

    public boolean enQueue(int value) {
        if (size == queue.length)
            return false;

        queue[++tail % queue.length] = value;
        size++;

        return true;
    }

    public boolean deQueue() {
        if (size == 0)
            return false;
        head++;
        size--;

        return true;
    }

    public int Front() {
        if (size == 0)
            return -1;

        return queue[head % queue.length];
    }

    public int Rear() {
        if (size == 0)
            return -1;

        return queue[tail % queue.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }
}