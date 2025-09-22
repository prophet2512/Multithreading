package Practice;

class SyncCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
public class ThreadSafeCounter {
    public static void main(String[] args) throws InterruptedException{
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
