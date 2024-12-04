package ExecutorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main2 {
    public static void main(String[] args) {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println("Hello world");
//        executorService.submit(runnable);
//        executorService.shutdownNow();
        Future<String> future = executorService.submit(runnable, "Success");
        executorService.shutdownNow();
        System.out.println(future.get());*/
        /*ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> 1+2);
        Integer i = future.get();
        System.out.println("Sum is: "+i);
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
        Thread.sleep(1);
        System.out.println(executorService.isTerminated());*/

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Integer> callable1 = () -> {
            System.out.println("Task 1");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("Task 2");
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("Task 3");
            //Thread.sleep(10);
            return 3;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);

        /*List<Future<Integer>> futures = null;
        try {
            futures = executorService.invokeAll(list, 1 , TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Future<Integer> future: futures) {
            if(future.isDone()){
                try {
                    System.out.println(future.get());

                } catch (CancellationException e) {

                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        executorService.shutdownNow();*/

        try {
            Integer i = executorService.invokeAny(list);
            System.out.println(i);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdownNow();
        System.out.println("Namaskar Duniya!");

    }
}
