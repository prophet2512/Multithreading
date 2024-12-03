package synchronization;

public class CounterIncrementThread extends Thread{
    private Counter counter;

    public CounterIncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0; i<1000; i++) {
            counter.increment();
        }
    }
}
