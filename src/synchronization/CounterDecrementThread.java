package synchronization;

public class CounterDecrementThread implements Runnable{
    private Counter counter;

    public CounterDecrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<1000; i++){
            counter.decrement();
        }
    }
}
