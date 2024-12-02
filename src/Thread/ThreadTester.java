package Thread;

public class ThreadTester {
    public static void main(String[] args) {
        System.out.println("Inside main thread");

        /**
         * SO this method for thread creation is not recommended because
         * this method of thread creation is created by extending the Thread
         * class, and since in Java, it is impossible to inherit multiple classes
         * we will always use the other method which is about creation of the thread
         * using the implements Runnable method, the one which is implemented below this method.
         * So here onwards, we will be using only that way for creating the threads.
         */
//        Thread thread1 = new Thread1("Test1-Thread");
//        thread1.setDaemon(true);
//        thread1.start();

        Thread thread2 = new Thread(new Thread2(), "Runnable Interface Thread");
        //thread2.start();

        Thread thread3 = new Thread(new Thread(()->{

            for(int i = 0; i<5; i++) {
                System.out.printf("Inside main thread child thread with thread name %s for %d time and the status is: %s\n", Thread.currentThread().getName(), i, Thread.currentThread().getState());
                try {
                    Thread.sleep(1000);
                    System.out.printf("\nThread state is %s\n", Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " state is " + Thread.currentThread().getState());
        }), "Mera thread");
        thread3.start();

        System.out.println(thread3.getState());

        System.out.println("End of main thread");
    }
}

