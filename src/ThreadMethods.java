public class ThreadMethods extends Thread{
    public ThreadMethods(String name) {
        super(name);
    }
    @Override
    public void run() {
//        System.out.println("Thread is running....");
//        for (int  i = 1; i<=2; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(i);
//            for (int i = 0; i<5; i++) {
//                for (int j = 0; j<100000000; j++);
//                System.out.printf("%s is the name of the thread with priority - %d\n", Thread.currentThread().getName(), Thread.currentThread().getPriority());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        for(int i = 0; i<5; i++) {
            System.out.println("Printing for the thread "+ Thread.currentThread().getName());
            Thread.yield();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        /*ThreadMethods thread = new ThreadMethods("DingDong");
        thread.start();
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println("Hello, the method will terminate now...");*/
//        ThreadMethods t1 = new ThreadMethods("Low Priority");
//        ThreadMethods t2 = new ThreadMethods("Med Priority");
//        ThreadMethods t3 = new ThreadMethods("High Priority");
//        t1.setPriority(Thread.MIN_PRIORITY);
//        t2.setPriority(Thread.NORM_PRIORITY);
//        t3.setPriority(Thread.MAX_PRIORITY);
//
//        t1.start();
//        t2.start();
//        t3.start();

        Thread t1 = new ThreadMethods("Thread1");
        Thread t2 = new ThreadMethods("Thread2");
        t1.start();
        t2.start();


    }
}

/**
 * Methods that I have understood in this class
 * start
 * sleep
 * join
 * run
 * setPriority
 */
