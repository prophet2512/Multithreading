package CountDownLatch;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfServices = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        /*
        Future<String> future1 = executor.submit(new DependentService());
        Future<String> future2 = executor.submit(new DependentService());
        Future<String> future3 = executor.submit(new DependentService());

        future1.get();
        future2.get();
        future3.get();
        System.out.println("All dependent services finished. Starting main service...");
        */
        executor.submit(new NewDependentService(latch));
        executor.submit(new NewDependentService(latch));
        executor.submit(new NewDependentService(latch));
        latch.await();

        System.out.println("Main");
        executor.shutdown();

    }
}

class DependentService implements Callable<String> {

    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try{
            System.out.println(Thread.currentThread().getName() + " service started.");
            Thread.sleep(2000);
        } finally {
            latch.countDown();
        }
        return "ok";
    }
}
