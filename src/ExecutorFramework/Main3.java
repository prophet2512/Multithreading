package ExecutorFramework;

import java.util.concurrent.*;

public class Main3 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello");
            return 42;
        });

        /*Integer i = null;

        try {
            i = future.get(1, TimeUnit.SECONDS);
            System.out.println(future.isDone());
            System.out.println(i);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Exception occurred: "+ e);
        }*/
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        future.cancel(false);

        System.out.println(future.isCancelled());
        System.out.println(future.isDone());

        executor.shutdown();
    }
}
