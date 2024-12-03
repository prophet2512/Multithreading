package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment(){
        writeLock.lock();
        try {
            count++;
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample counter = new ReadWriteLockExample();

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10; i++) {
                    System.out.println(Thread.currentThread().getName() + " incremented.");
                    counter.increment();
                }
            }
        };

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10; i++) {
                    System.out.println(Thread.currentThread().getName()+ " read: "+ counter.getCount());
                }
            }
        };

        Thread writerThread = new Thread(writeTask, "WriterThread");
        Thread readerThread1 = new Thread(readTask, "ReaderThread1");
        Thread readerThread2 = new Thread(readTask, "ReaderThread2");

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        writerThread.join();
        readerThread1.join();
        readerThread2.join();
    }
}
