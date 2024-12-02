package synchronization;

public class Stack {
    private int[] array;
    private int stackTop;
    final Object lock1;

    final Object lock2;

    public Stack(int capacity) {
        this.array = new int[capacity];
        this.stackTop = -1;

        lock1 = new Object();
        lock2 = new Object();
    }

    public boolean push(int element) {
        synchronized(this) {
            if (isFull()) return false;
            ++stackTop;

            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }

            array[stackTop] = element;
            return true;
        }
    }

    public int pop() {
        synchronized (this) {
            if (isEmpty())
                return Integer.MIN_VALUE;
            int obj = array[stackTop];
            array[stackTop] = Integer.MIN_VALUE;

            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }

            stackTop--;
            return obj;
        }
    }

    public boolean isEmpty() {
        return this.stackTop < 0;
    }

    public boolean isFull() {
        return this.array.length-1 <= stackTop;
    }
}
