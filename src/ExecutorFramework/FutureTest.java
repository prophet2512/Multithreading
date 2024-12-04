package ExecutorFramework;

import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> "Hello";
        Future<String> future = executorService.submit(callable);
        executorService.shutdown();
        System.out.println(future.get());
    }
}
