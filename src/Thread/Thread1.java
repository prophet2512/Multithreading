package Thread;

public class Thread1 extends Thread{

    public Thread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0; i<5; i++) {
            System.out.printf("Inside %s for %d time\n", Thread.currentThread().getName(), i);
        }
    }

}
