package ExecutorFramework;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
       /* Thread[] threads = new Thread[9];
        for(int i =1; i<10; i++) {
            int finalI = i;
            threads[i-1] = new Thread( ()-> {
                long result = factorial(finalI);
                System.out.println(result);
            });
            threads[i-1].start();
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for(int i =1; i<10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });

        }
        executorService.shutdown();
        try {
            /*
            This will make sure that the execution continues only after the previous executors shutdown
            is completed.
             */
//            executorService.awaitTermination(20, TimeUnit.SECONDS);
            while(!executorService.awaitTermination(1, TimeUnit.MILLISECONDS)) {
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken is: "+ (endTime-startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for(int i = 1; i<=n; i++) {
            result *=i;
        }
        return result;
    }
}
