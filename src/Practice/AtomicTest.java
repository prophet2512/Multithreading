package Practice;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class NewSharedResource {
    private final AtomicInteger count = new AtomicInteger(0);
    //int count=0;

    //ReentrantLock lock = new ReentrantLock();

    public void increment() {
//        lock.lock();
//        try {
//            count++;
//        } finally {
//            lock.unlock();
//        }
        count.incrementAndGet();
    }

    public int getCount(){
//        lock.lock();
//        try {
//            return this.count;
//        } finally {
//            lock.unlock();
//        }
        return count.get();
    }
}
public class AtomicTest {
    public static void main(String[] args) throws InterruptedException {
        NewSharedResource sharedResource = new NewSharedResource();

        Thread t1 = new Thread(()  -> {
            System.out.println("Thread1 started");
            for (int i = 0; i<5000; i++){
                sharedResource.increment();
            }
            System.out.println("Thread1 completed!");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread2 started!");
            for (int i = 0; i<5000; i++){
                sharedResource.increment();
            }
            System.out.println("Thread2 completed");
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count value post the run is: " + sharedResource.getCount());
    }
}
