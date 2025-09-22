package Practice;

public class Revision {
    public static Object sharedObject = new Object();
    public static void main(String[] args) {
        Runnable runnable1 = () -> {
            System.out.println("Starting thread1!");
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println(sharedObject.hashCode());
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread execution completed for thread1");

        };

        Runnable runnable2 = () -> {
            System.out.println("Starting thread2!");
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getState());
            System.out.println(sharedObject.hashCode());
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread execution completed for thread2");

        };
        Thread thread1 = new Thread(runnable1, "Thread1");
        thread1.start();

        Thread thread2 = new Thread(runnable2, "Thread2");
        thread2.start();

        System.out.println("Main thread ended!");

    }
}
