public class Deadlock {
    public static void main(String[] args) {
        System.out.println("Main is starting");
        String lock1 = "Priyank";
        String lock2 = "Verma";

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Lock acquired");
                }
            }
        }, "thread1");

        /**
         * In order to deadlock this program, interchange the locks on line 25 and 32
         */
        Thread thread2 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Lock acquired");
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main is ending");
    }
}
