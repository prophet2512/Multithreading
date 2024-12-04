package CyclicBarrier;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        /*int numberOfService = 3;
        ExecutorService service = Executors.newFixedThreadPool(numberOfService);
        CyclicBarrier barrier = new CyclicBarrier(numberOfService);

        service.submit(new DependentService(barrier));
        service.submit(new DependentService(barrier));
        service.submit(new DependentService(barrier));
        barrier.reset();
        System.out.println("Main");
        service.shutdown();*/
        int numberOfServices = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices, new Runnable() {
            @Override
            public void run() {
                System.out.println("All subsystems are up and running. System startup complete. " + Thread.currentThread().getName());
            }
        });

        Thread webServerThread = new Thread(new Subsystem("Webserver Thread", 2000, barrier));
        Thread databaseThread = new Thread(new Subsystem("Database Thread", 4000, barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache Thread", 3000, barrier));
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service Thread", 3500, barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}

class Subsystem implements Runnable {

    private String name;
    private int initialisationTime;
    private CyclicBarrier barrier;

    public Subsystem(String name, int initialisationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initialisationTime = initialisationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialisation started by "+Thread.currentThread().getName());
            Thread.sleep(initialisationTime);
            System.out.println(name + " initialisation complete.");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class DependentService implements Callable<String> {
    private final CyclicBarrier barrier;

    public DependentService(CyclicBarrier barrier) {
        this.barrier = barrier;
    }


    @Override
    public String call() throws Exception {

        System.out.println(Thread.currentThread().getName() + " service started.");
        System.out.println(barrier.getNumberWaiting());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " waiting on the barrier.");
        barrier.await();

        return "ok";
    }
}
