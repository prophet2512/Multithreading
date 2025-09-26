package Practice;

import java.util.concurrent.locks.ReentrantLock;

class SyncCounter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        try {
            lock.lock();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized int getCount() {
        return count;
    }
}
public class ThreadSafeCounter {
    static void main(String[] args) throws InterruptedException{
        SyncCounter syncCounter = new SyncCounter();
        Runnable task = () -> {
            for (int i = 0; i<1000; i++){
                syncCounter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter value: "+syncCounter.getCount());
    }
}
