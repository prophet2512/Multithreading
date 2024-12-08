package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void download() {
        try {
            semaphore.acquire();
            downloadData();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }

    private void downloadData() {
        try {
            Thread.sleep(1000);
            System.out.println("Download completed..." + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

public class Test {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(25);
        for (int i = 0; i<50; i++) {
            service.submit(() -> {
                Downloader.INSTANCE.download();
            });
        }
        service.shutdown();
    }
}
