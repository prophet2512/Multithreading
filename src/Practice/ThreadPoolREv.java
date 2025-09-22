package Practice;

import java.sql.Time;
import java.util.concurrent.*;

public class ThreadPoolREv {
    public static void main(String[] args) {
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        System.out.println("Single thread executor:");
//        for (int i = 1; i<=5; i++) {
//            final int taskId = i;
//            singleThreadExecutor.execute(() -> {
//                System.out.println("Single thread task :"+taskId+" executed by Thread: "+
//                        Thread.currentThread().getName());
//            });
//        }
//        singleThreadExecutor.shutdown();

        // Fixed Thread Executor!
//        ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(5);
//        System.out.println("Fixed thread executor:");
//        for (int i = 1; i<=15; i++) {
//            final int taskId = i;
//            fixedThreadExecutor.execute(() ->
//                    System.out.println("Fixed thread task: "+taskId + " executed by Thread: "+
//                            Thread.currentThread().getName())
//            );
//        }
//        fixedThreadExecutor.shutdown();

        //Cached Thread pool
//        ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool();
//        System.out.println("Cached thread executor:");
//        for (int i = 1; i<Integer.MAX_VALUE; i++) {
//            final int taskId = i;
//            cachedThreadExecutor.execute(() ->
//                    System.out.println("Fixed thread task: "+taskId + " executed by Thread: "+
//                            Thread.currentThread().getName())
//            );
//        }
//        cachedThreadExecutor.shutdown();

        //Cached Thread pool
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        System.out.println("Scheduled thread executor:");
        for (int i = 1; i<15; i++) {
            final int taskId = i;
            scheduledExecutorService.schedule(() -> {
                System.out.println("Fixed thread task: " + taskId + " executed by Thread: " +
                        Thread.currentThread().getName());
            }, 3, TimeUnit.SECONDS);
        }
        scheduledExecutorService.shutdown();

        ExecutorService threadPoolExecutorObject =
                new ThreadPoolExecutor(1,
                        5,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(1)
                );
        for(int i = 1; i<=5; i++){
            final int taskId = i;
            threadPoolExecutorObject.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Threadpool Executor: "+
                        " Thread task "+taskId +
                        " executed by Thread: "
                +Thread.currentThread().getName());
            });
        }
        threadPoolExecutorObject.shutdown();
    }
}
