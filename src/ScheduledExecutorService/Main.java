package ScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        scheduler.schedule(() -> System.out.println("Executed after 5 seconds delay "+Thread.currentThread().getName()), 5, TimeUnit.MICROSECONDS);
        scheduler.scheduleAtFixedRate(() -> System.out.println("Now this will be executed at a delay of every 1 second post first execution " + Thread.currentThread().getName()),
                7,
                1,
                TimeUnit.MILLISECONDS);
        //scheduler.shutdown();
        scheduler.scheduleWithFixedDelay(() -> System.out.println("Now this will print after a delay of say 10 seconds! "+Thread.currentThread().getName()),
                10,
                1,
                TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> {
            System.out.println("Intiating shutdown.");
            scheduler.shutdown();
        }, 11, TimeUnit.MILLISECONDS);
    }
}
