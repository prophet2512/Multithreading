public class JoinThreads {
    public static void main(String[] args) {
        System.out.println("Main is starting");
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread());
        }, "Our thread");
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main is exiting");

        System.out.println(thread.getPriority());
    }
}
