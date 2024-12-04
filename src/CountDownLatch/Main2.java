package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        for (int i = 0; i<numberOfServices; i++) {
            new Thread(new NewDependentService(latch), String.format("Thread%d", i)).start();
        }
        latch.await();
        System.out.println("Main");
    }
}

class NewDependentService implements Runnable {

    private final CountDownLatch latch;

    public NewDependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " started executing");
            Thread.sleep(2000);
        } catch (Exception e) {

        } finally {
            latch.countDown();
        }
    }
}
