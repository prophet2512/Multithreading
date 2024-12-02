package Thread;

public class Thread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i<5; i++) {
            System.out.printf("Inside the %s thread for the %d time\n", Thread.currentThread().getName(), i);
        }
    }
}
