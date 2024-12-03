package synchronization;

public class CounterTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new CounterIncrementThread(counter);
        Thread t2 = new Thread(new CounterDecrementThread(counter), "Decrement karne wala thread");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter.getCount());
    }
}
